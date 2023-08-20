package com.ktg.mes.pro.controller.mobile;

import cn.hutool.core.collection.CollectionUtil;
import com.ktg.common.annotation.Log;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.core.page.TableDataInfo;
import com.ktg.common.enums.BusinessType;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.md.domain.MdWorkstation;
import com.ktg.mes.md.service.IMdWorkstationService;
import com.ktg.mes.pro.domain.ProFeedback;
import com.ktg.mes.pro.domain.ProRouteProcess;
import com.ktg.mes.pro.service.IProFeedbackService;
import com.ktg.mes.pro.service.IProRouteProcessService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api("生产报工")
@RestController
@RequestMapping("/mobile/pro/feedback")
public class ProFeedBackMobController extends BaseController {

    @Autowired
    private IProFeedbackService proFeedbackService;

    @Autowired
    private IMdWorkstationService mdWorkstationService;

    @Autowired
    private IProRouteProcessService proRouteProcessService;

    /**
     * 新增生产报工记录
     */
    @ApiOperation("新增报工单接口")
    @PreAuthorize("@ss.hasPermi('mes:pro:feedback:add')")
    @Log(title = "生产报工记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProFeedback proFeedback)
    {
        MdWorkstation workstation = mdWorkstationService.selectMdWorkstationByWorkstationId(proFeedback.getWorkstationId());
        if(StringUtils.isNotNull(workstation)){
            proFeedback.setProcessId(workstation.getProcessId());
            proFeedback.setProcessCode(workstation.getProcessCode());
            proFeedback.setProcessName(workstation.getProcessName());
        }else {
            return AjaxResult.error("当前生产任务对应的工作站不存在！");
        }

        //检查对应的工艺路线和工序配置
        if(StringUtils.isNotNull(proFeedback.getRouteId())&& StringUtils.isNotNull(proFeedback.getProcessId())){
            ProRouteProcess param = new ProRouteProcess();
            param.setRouteId(proFeedback.getRouteId());
            param.setProcessId(proFeedback.getProcessId());
            List<ProRouteProcess> processes = proRouteProcessService.selectProRouteProcessList(param);
            if(CollectionUtil.isEmpty(processes)){
                return AjaxResult.error("未找到生产任务对应的工艺工序配置！");
            }
        }else {
            return AjaxResult.error("当前生产任务对应的工艺工序配置无效！");
        }

        //检查数量
        if(UserConstants.YES.equals(proFeedback.getIsCheck())){
            if(!StringUtils.isNotNull(proFeedback.getQuantityUncheck())){
                return AjaxResult.error("当前工作站报工需要经过质检确认，请输入待检测数量!");
            }
        }else {
            if(!StringUtils.isNotNull(proFeedback.getQuantityQualified()) || !StringUtils.isNotNull(proFeedback.getQuantityUnquanlified())){
                return AjaxResult.error("请输入合格品和不良品数量！");
            }
        }

        proFeedback.setCreateBy(getUsername());
        proFeedbackService.insertProFeedback(proFeedback);
        return AjaxResult.success(proFeedback);
    }

    /**
     * 查询生产报工记录列表
     */
    @ApiOperation("查询报工单清单-全部")
    @PreAuthorize("@ss.hasPermi('mes:pro:feedback:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProFeedback proFeedback)
    {
        List<ProFeedback> list = proFeedbackService.selectProFeedbackList(proFeedback);
        return getDataTable(list);
    }

    /**
     * 查询生产报工记录列表
     */
    @ApiOperation("查询报工单清单-未审批通过的")
    @PreAuthorize("@ss.hasPermi('mes:pro:feedback:list')")
    @GetMapping("/listUnApproved")
    public TableDataInfo listUnApproved(ProFeedback proFeedback)
    {
        List<ProFeedback> all = new ArrayList<ProFeedback>();
        proFeedback.setStatus(UserConstants.ORDER_STATUS_PREPARE);
        List<ProFeedback> list1 = proFeedbackService.selectProFeedbackList(proFeedback);
        all.addAll(list1);
        proFeedback.setStatus(UserConstants.ORDER_STATUS_APPROVING);
        List<ProFeedback> list2 = proFeedbackService.selectProFeedbackList(proFeedback);
        all.addAll(list2);
        return getDataTable(all);
    }


    /**
     * 查询生产报工记录列表
     */
    @ApiOperation("查询报工单清单-已审批通过的")
    @PreAuthorize("@ss.hasPermi('mes:pro:feedback:list')")
    @GetMapping("/listApproved")
    public TableDataInfo listApproved(ProFeedback proFeedback)
    {
        proFeedback.setStatus(UserConstants.ORDER_STATUS_FINISHED);
        List<ProFeedback> list = proFeedbackService.selectProFeedbackList(proFeedback);
        return getDataTable(list);
    }

    /**
     * 修改生产报工记录
     */
    @ApiOperation("报工修改接口")
    @PreAuthorize("@ss.hasPermi('mes:pro:feedback:edit')")
    @Log(title = "生产报工记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProFeedback proFeedback)
    {
        MdWorkstation workstation = mdWorkstationService.selectMdWorkstationByWorkstationId(proFeedback.getWorkstationId());
        if(StringUtils.isNotNull(workstation)){
            proFeedback.setProcessId(workstation.getProcessId());
            proFeedback.setProcessCode(workstation.getProcessCode());
            proFeedback.setProcessName(workstation.getProcessName());
        }else {
            return AjaxResult.error("当前生产任务对应的工作站不存在！");
        }

        //检查对应的工艺路线和工序配置
        if(StringUtils.isNotNull(proFeedback.getRouteId())&& StringUtils.isNotNull(proFeedback.getProcessId())){
            ProRouteProcess param = new ProRouteProcess();
            param.setRouteId(proFeedback.getRouteId());
            param.setProcessId(proFeedback.getProcessId());
            List<ProRouteProcess> processes = proRouteProcessService.selectProRouteProcessList(param);
            if(CollectionUtil.isEmpty(processes)){
                return AjaxResult.error("未找到生产任务对应的工艺工序配置！");
            }
        }else {
            return AjaxResult.error("当前生产任务对应的工艺工序配置无效！");
        }

        //检查数量
        if(UserConstants.YES.equals(proFeedback.getIsCheck())){
            if(!StringUtils.isNotNull(proFeedback.getQuantityUncheck())){
                return AjaxResult.error("当前工作站报工需要经过质检确认，请输入待检测数量!");
            }
        }else {
            if(!StringUtils.isNotNull(proFeedback.getQuantityQualified()) || !StringUtils.isNotNull(proFeedback.getQuantityUnquanlified())){
                return AjaxResult.error("请输入合格品和不良品数量！");
            }
        }

        return toAjax(proFeedbackService.updateProFeedback(proFeedback));
    }

    /**
     * 删除生产报工记录
     */
    @ApiOperation("删除报工单")
    @PreAuthorize("@ss.hasPermi('mes:pro:feedback:remove')")
    @Log(title = "生产报工记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(proFeedbackService.deleteProFeedbackByRecordIds(recordIds));
    }
}
