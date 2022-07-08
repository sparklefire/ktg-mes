package com.ktg.mes.pro.controller;

import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.mes.pro.domain.ProTask;
import com.ktg.mes.pro.service.IProTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mobile/pro/protask")
public class ProTaskMobController extends BaseController {

    @Autowired
    private IProTaskService proTaskService;

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
}
