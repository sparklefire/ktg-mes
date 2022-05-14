package com.ktg.mes.pro.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ktg.common.annotation.Log;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.enums.BusinessType;
import com.ktg.mes.pro.domain.ProTask;
import com.ktg.mes.pro.service.IProTaskService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 生产任务Controller
 * 
 * @author yinjinlu
 * @date 2022-05-14
 */
@RestController
@RequestMapping("/pro/protask")
public class ProTaskController extends BaseController
{
    @Autowired
    private IProTaskService proTaskService;

    /**
     * 查询生产任务列表
     */
    @PreAuthorize("@ss.hasPermi('pro:protask:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProTask proTask)
    {
        startPage();
        List<ProTask> list = proTaskService.selectProTaskList(proTask);
        return getDataTable(list);
    }

    /**
     * 导出生产任务列表
     */
    @PreAuthorize("@ss.hasPermi('pro:protask:export')")
    @Log(title = "生产任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProTask proTask)
    {
        List<ProTask> list = proTaskService.selectProTaskList(proTask);
        ExcelUtil<ProTask> util = new ExcelUtil<ProTask>(ProTask.class);
        util.exportExcel(response, list, "生产任务数据");
    }

    /**
     * 获取生产任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('pro:protask:query')")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId)
    {
        return AjaxResult.success(proTaskService.selectProTaskByTaskId(taskId));
    }

    /**
     * 新增生产任务
     */
    @PreAuthorize("@ss.hasPermi('pro:protask:add')")
    @Log(title = "生产任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProTask proTask)
    {
        return toAjax(proTaskService.insertProTask(proTask));
    }

    /**
     * 修改生产任务
     */
    @PreAuthorize("@ss.hasPermi('pro:protask:edit')")
    @Log(title = "生产任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProTask proTask)
    {
        return toAjax(proTaskService.updateProTask(proTask));
    }

    /**
     * 删除生产任务
     */
    @PreAuthorize("@ss.hasPermi('pro:protask:remove')")
    @Log(title = "生产任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds)
    {
        return toAjax(proTaskService.deleteProTaskByTaskIds(taskIds));
    }
}
