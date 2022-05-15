package com.ktg.mes.pro.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ktg.common.constant.UserConstants;
import com.ktg.mes.pro.domain.ProProcess;
import com.ktg.mes.pro.domain.ProWorkorder;
import com.ktg.mes.pro.service.IProProcessService;
import com.ktg.mes.pro.service.IProWorkorderService;
import com.ktg.system.strategy.AutoCodeUtil;
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
@RequestMapping("/mes/pro/protask")
public class ProTaskController extends BaseController
{
    @Autowired
    private IProTaskService proTaskService;

    @Autowired
    private IProWorkorderService proWorkorderService;

    @Autowired
    private IProProcessService proProcessService;

    @Autowired
    private AutoCodeUtil autoCodeUtil;

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
     * 获取甘特图中需要显示的TASK，包括三种类型的内容：
     * 1.Project：基于时间范围搜索的生产工单转换而来的Project。
     *   搜索逻辑为：默认使用当前日期作为开始时间，搜索所有需求时间大于当前时间的生产工单
     * 2.Task：基于生产工单拆分到具体工作站后的生产任务转换而来的Task。
     * 3.Link：根据工序与工序之间的依赖关系转换而来的Link。
     */
    @PreAuthorize("@ss.hasPermi('pro:protask:list')")
    @GetMapping("/listGanttTaskList")
    public AjaxResult getGanttTaskList(ProTask proTask){

        return AjaxResult.success();
    }


    /**
     * 新增生产任务
     */
    @PreAuthorize("@ss.hasPermi('pro:protask:add')")
    @Log(title = "生产任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProTask proTask)
    {

        //生产工单
        ProWorkorder order = proWorkorderService.selectProWorkorderByWorkorderId(proTask.getWorkorderId());
        proTask.setWorkorderCode(order.getWorkorderCode());
        proTask.setWorkorderName(order.getWorkorderName());
        proTask.setItemId(order.getProductId());
        proTask.setItemCode(order.getProductCode());
        proTask.setItemName(order.getProductName());
        proTask.setSpecification(order.getProductSpc());
        proTask.setUnitOfMeasure(order.getUnitOfMeasure());
        proTask.setClientId(order.getClientId());
        proTask.setClientCode(order.getClientCode());
        proTask.setClientName(order.getClientName());

        //工序信息
        ProProcess process = proProcessService.selectProProcessByProcessId(proTask.getProcessId());
        proTask.setProcessId(process.getProcessId());
        proTask.setProcessCode(process.getProcessCode());
        proTask.setProcessName(process.getProcessName());

        //自动生成任务编号和名称
        proTask.setTaskCode(autoCodeUtil.genSerialCode(UserConstants.TASK_CODE,null));
        proTask.setTaskName(new StringBuilder().append(proTask.getItemName()).append("【").append(proTask.getQuantity().toString()).append("】").append(proTask.getUnitOfMeasure()).toString());


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
