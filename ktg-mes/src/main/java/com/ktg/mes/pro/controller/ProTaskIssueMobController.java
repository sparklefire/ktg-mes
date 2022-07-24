package com.ktg.mes.pro.controller;

import cn.hutool.core.collection.CollUtil;
import com.ktg.common.annotation.Log;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.enums.BusinessType;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.pro.domain.ProTaskIssue;
import com.ktg.mes.pro.service.IProTaskIssueService;
import com.ktg.mes.wm.domain.WmIssueHeader;
import com.ktg.mes.wm.domain.WmIssueLine;
import com.ktg.mes.wm.service.IWmIssueHeaderService;
import com.ktg.mes.wm.service.IWmIssueLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/mobile/pro/taskissue")
public class ProTaskIssueMobController extends BaseController {

    @Autowired
    private IProTaskIssueService proTaskIssueService;

    @Autowired
    private IWmIssueHeaderService wmIssueHeaderService;

    @Autowired
    private IWmIssueLineService wmIssueLineService;

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

        return AjaxResult.success(issueList);
    }

    /**
     * 通过新增或者扫码的方式添加某个流转单或者领料单到当前工作站、当前任务的投料清单中
     * 此接口只支持一次性添加一行物料。如果要添加整个领料单，则可在领料单上指定工作站和任务；或者使用addIssue接口
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:taskissue:add')")
    @Log(title = "生产任务投料", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody ProTaskIssue proTaskIssue)
    {
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
    @DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(proTaskIssueService.deleteProTaskIssueByRecordIds(recordIds));
    }

}
