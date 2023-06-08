package com.ktg.mes.pro.controller.mobile;

import cn.hutool.core.collection.CollUtil;
import com.ktg.common.annotation.Log;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.enums.BusinessType;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.md.domain.MdProductSop;
import com.ktg.mes.md.domain.MdWorkstation;
import com.ktg.mes.md.service.IMdProductSopService;
import com.ktg.mes.md.service.IMdWorkstationService;
import com.ktg.mes.pro.domain.ProTask;
import com.ktg.mes.pro.domain.ProTaskIssue;
import com.ktg.mes.pro.domain.ProTransOrder;
import com.ktg.mes.pro.service.IProTaskIssueService;
import com.ktg.mes.pro.service.IProTaskService;
import com.ktg.mes.pro.service.IProTransOrderService;
import com.ktg.mes.wm.domain.WmIssueHeader;
import com.ktg.mes.wm.domain.WmIssueLine;
import com.ktg.mes.wm.service.IWmIssueHeaderService;
import com.ktg.mes.wm.service.IWmIssueLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mobile/pro/taskissue")
public class ProTaskIssueMobController extends BaseController {

    @Autowired
    private IProTaskIssueService proTaskIssueService;

    @Autowired
    private IProTaskService proTaskService;

    @Autowired
    private IProTransOrderService proTransOrderService;


    @Autowired
    private IWmIssueHeaderService wmIssueHeaderService;

    @Autowired
    private IWmIssueLineService wmIssueLineService;

    @Autowired
    private IMdProductSopService mdProductSopService;

    /**
     * 获取当前产品的SOP
     * @param mdProdutSop
     * @return
     */
    @GetMapping("getSopList")
    public AjaxResult getSopList(MdProductSop mdProdutSop){
        List<MdProductSop> list = mdProductSopService.selectMdProductSopList(mdProdutSop);
        return AjaxResult.success(list);
    }

    /**
     * 查询当前工作站、当前任务的投料清单
     * 至少提供workstationId、taskId两个参数
     */
    ///@PreAuthorize("@ss.hasPermi('mes:pro:taskissue:list')")
    @GetMapping("/getlist")
    public AjaxResult getIssueList(ProTaskIssue proTaskIssue) {
        List<ProTaskIssue> list = proTaskIssueService.selectProTaskIssueList(proTaskIssue);
        return AjaxResult.success(list);
    }

    /**
     * 查询当前工作站、当前任务可用的的领料清单
     * 如果某个领料单是领出到当前工作站或者当前任务的，则可以查询到
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:taskissue:list')")
    @GetMapping("/getReserveIssueList")
    public AjaxResult getReserveIssueList(ProTaskIssue proTaskIssue){
        WmIssueHeader param = new WmIssueHeader();
        //领料单上指定了工作站
        if(StringUtils.isNotNull(proTaskIssue.getWorkstationId())){
            param.setWorkstationId(proTaskIssue.getWorkstationId());
        }

        //领料单上指定了生产工单
        if(StringUtils.isNotNull(proTaskIssue.getWorkorderId())){
            param.setWorkorderId(proTaskIssue.getWorkorderId());
        }

        //领料单上指定了生产任务
        if(StringUtils.isNotNull(proTaskIssue.getTaskId())){
            param.setTaskId(proTaskIssue.getTaskId());
        }

        List<WmIssueHeader> issueList = wmIssueHeaderService.selectWmIssueHeaderList(param);

        List<WmIssueLine> lines = new ArrayList<WmIssueLine>();
        if(CollUtil.isNotEmpty(issueList)){
            WmIssueLine p = new WmIssueLine();
            for (WmIssueHeader header: issueList
                 ) {
                p.setIssueId(header.getIssueId());
                lines.addAll(wmIssueLineService.selectWmIssueLineList(p));
            }
        }

        return AjaxResult.success(lines);
    }

    /**
     * 通过新增或者扫码的方式添加某个流转单或者领料单到当前工作站、当前任务的投料清单中
     * 此接口只支持一次性添加一行物料。如果要添加整个领料单，则可在领料单上指定工作站和任务；或者使用addIssue接口
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:taskissue:add')")
    @Log(title = "生产任务投料", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(ProTaskIssue proTaskIssue)
    {
        //前端至少会传递taskId、workstationId、sourceLineId、sourceDocType几个字段过来
        ProTask task = proTaskService.selectProTaskByTaskId(proTaskIssue.getTaskId());
        proTaskIssue.setWorkorderId(task.getWorkorderId());

        //如果是领料单
        if(UserConstants.TASK_ISSUE_DOC_TYPE_ISSUE.equals(proTaskIssue.getSourceDocType())){
            WmIssueLine line = wmIssueLineService.selectWmIssueLineByLineId(proTaskIssue.getSourceLineId());
            WmIssueHeader header = wmIssueHeaderService.selectWmIssueHeaderByIssueId(line.getIssueId());
            proTaskIssue.setSourceDocId(line.getIssueId());//设置领料单ID
            proTaskIssue.setSourceDocCode(header.getIssueCode());//设置领料单编号
            proTaskIssue.setBatchCode(line.getBatchCode());
            proTaskIssue.setItemId(line.getItemId());
            proTaskIssue.setItemCode(line.getItemCode());
            proTaskIssue.setItemName(line.getItemName());
            proTaskIssue.setSpecification(line.getSpecification());
            proTaskIssue.setUnitOfMeasure(line.getUnitOfMeasure());
            proTaskIssue.setQuantityIssued(line.getQuantityIssued());
        }else{
            //如果是流转单
            ProTransOrder transOrder = proTransOrderService.selectProTransOrderByTransOrderId(proTaskIssue.getSourceDocId());
            proTaskIssue.setTaskId(transOrder.getTaskId());
            proTaskIssue.setWorkorderId(transOrder.getWorkorderId());
            proTaskIssue.setSourceDocCode(transOrder.getTransOrderCode());
            proTaskIssue.setBatchCode(transOrder.getBatchCode());
            proTaskIssue.setSourceLineId(transOrder.getTransOrderId());//这里直接使用头ID作为source_line_id，因为流转单不是头行结构
            proTaskIssue.setItemId(transOrder.getItemId());
            proTaskIssue.setItemCode(transOrder.getItemCode());
            proTaskIssue.setItemName(transOrder.getItemName());
            proTaskIssue.setUnitOfMeasure(transOrder.getUnitOfMeasure());
            proTaskIssue.setQuantityIssued(transOrder.getQuantityTransfered());//流转单的流转数量作为投料数量
        }
        //不能重复添加
        if(UserConstants.NOT_UNIQUE.equals(proTaskIssueService.checkUnique(proTaskIssue))){
            return AjaxResult.error("物料已添加过");
        }
        return toAjax(proTaskIssueService.insertProTaskIssue(proTaskIssue));
    }


    /**
     * 通过新增或者扫码的方式添加某个流转单或者领料单到当前工作站、当前任务的投料清单中
     * 此接口专门用于一次性添加整个领料单的场景，传递的proTaskIssue参数需要source_doc_id为对应的领料单头ID
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:taskissue:add')")
    @Log(title = "生产任务投料", businessType = BusinessType.INSERT)
    @PostMapping("/addIssue")
    public AjaxResult addIssue(@RequestBody ProTaskIssue proTaskIssue)
    {
        if(!UserConstants.TASK_ISSUE_DOC_TYPE_ISSUE.equals(proTaskIssue.getSourceDocType())){
            return AjaxResult.error("请选择或扫描生产领料单！");//这里只支持添加整个领料单
        }
        Long issueId = proTaskIssue.getSourceDocId();

        WmIssueLine param = new WmIssueLine();
        param.setIssueId(issueId);
        List<WmIssueLine> issueLines = wmIssueLineService.selectWmIssueLineList(param);

        if(CollUtil.isEmpty(issueLines)){
            return AjaxResult.error("领料单行为空");
        }

        for (WmIssueLine line: issueLines
             ) {
            ProTaskIssue taskIssue = new ProTaskIssue();
            taskIssue.setTaskId(proTaskIssue.getTaskId());
            taskIssue.setWorkstationId(proTaskIssue.getWorkstationId());
            taskIssue.setWorkorderId(proTaskIssue.getWorkorderId());
            taskIssue.setSourceDocType(UserConstants.TASK_ISSUE_DOC_TYPE_ISSUE);
            taskIssue.setSourceDocId(issueId);
            //taskIssue.setSourceDocCode(); //领料单编号先不设置，需要的时候关联查询即可
            taskIssue.setBatchCode(line.getBatchCode());
            taskIssue.setSourceLineId(line.getLineId());
            taskIssue.setItemId(line.getItemId());
            taskIssue.setItemCode(line.getItemCode());
            taskIssue.setItemName(line.getItemName());
            taskIssue.setSpecification(line.getSpecification());
            taskIssue.setUnitOfMeasure(line.getUnitOfMeasure());
            taskIssue.setQuantityIssued(line.getQuantityIssued());
            //taskIssue.setQuantityAvailable(); //可用数量，如果需要则要实时计算
            taskIssue.setQuantityUsed(new BigDecimal(0)); //新添加的都默认为0；添加后删除再次添加也是0；实际使用量应该根据流转单计算

            //不能重复添加
            if(UserConstants.NOT_UNIQUE.equals(proTaskIssueService.checkUnique(taskIssue))){
                return AjaxResult.error("物料已添加过");
            }
            proTaskIssueService.insertProTaskIssue(taskIssue);
        }

        return AjaxResult.success();
    }

    /**
     * 删除生产任务投料
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:taskissue:remove')")
    @Log(title = "生产任务投料", businessType = BusinessType.DELETE)
    @PostMapping("/{recordId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable Long recordId)
    {
        return toAjax(proTaskIssueService.deleteProTaskIssueByRecordId(recordId));
    }

}
