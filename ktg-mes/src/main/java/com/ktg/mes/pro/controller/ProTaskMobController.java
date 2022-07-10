package com.ktg.mes.pro.controller;

import com.ktg.common.annotation.Log;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.enums.BusinessType;
import com.ktg.mes.md.domain.MdWorkstation;
import com.ktg.mes.md.service.IMdWorkstationService;
import com.ktg.mes.pro.domain.ProFeedback;
import com.ktg.mes.pro.domain.ProTask;
import com.ktg.mes.pro.service.IProFeedbackService;
import com.ktg.mes.pro.service.IProTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/mobile/pro/protask")
public class ProTaskMobController extends BaseController {

    @Autowired
    private IProTaskService proTaskService;

    @Autowired
    private IProFeedbackService proFeedbackService;

    @Autowired
    private IMdWorkstationService mdWorkstationService;

    /**
     * 查询工作站的生产任务
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:protask:list')")
    @GetMapping("/getTaskList")
    public AjaxResult list(ProTask proTask)
    {
        List<ProTask> list = proTaskService.selectProTaskList(proTask);
        return AjaxResult.success(list);
    }

    /**
     * 获取生产任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:protask:query')")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId)
    {
        return AjaxResult.success(proTaskService.selectProTaskByTaskId(taskId));
    }


    /**
     * 修改生产任务状态
     */
    @Log(title = "生产任务", businessType = BusinessType.UPDATE)
    @PostMapping("/change")
    @ResponseBody
    public AjaxResult changeStatus(ProTask proTask)
    {
        return toAjax(proTaskService.updateProTask(proTask));
    }

    @Log(title = "生产报工", businessType = BusinessType.INSERT)
    @PostMapping("/feedBack")
    @ResponseBody
    public AjaxResult feedBack( ProFeedback feedback){
        MdWorkstation workstation = mdWorkstationService.selectMdWorkstationByWorkstationId(feedback.getWorkstationId());
        feedback.setWorkstationCode(workstation.getWorkstationCode());
        feedback.setWorkstationName(workstation.getWorkstationName());

        ProTask task = proTaskService.selectProTaskByTaskId(feedback.getTaskId());
        feedback.setTaskCode(task.getTaskCode());
        feedback.setWorkorderId(task.getWorkorderId());
        feedback.setWorkorderCode(task.getWorkorderCode());
        feedback.setWorkorderName(task.getWorkorderName());
        feedback.setQuantity(task.getQuantity());
        feedback.setFeedbackTime(new Date());
        return toAjax(proFeedbackService.insertProFeedback(feedback));
    }

}
