package com.ktg.mes.pro.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.pro.domain.*;
import com.ktg.mes.pro.service.IProProcessService;
import com.ktg.mes.pro.service.IProRouteService;
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
    private IProRouteService proRouteService;

    @Autowired
    private AutoCodeUtil autoCodeUtil;

    /**
     * 查询生产任务列表
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:protask:list')")
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
    @PreAuthorize("@ss.hasPermi('mes:pro:protask:export')")
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
    @PreAuthorize("@ss.hasPermi('mes:pro:protask:query')")
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
    @PreAuthorize("@ss.hasPermi('mes:pro:protask:list')")
    @GetMapping("/listGanttTaskList")
    public AjaxResult getGanttTaskList(ProWorkorder proWorkorder){
        GanttTask ganttTask = new GanttTask();
        List<GanttData> ganttData = new ArrayList<GanttData>();
        List<GanttLink> ganttLinks = new ArrayList<GanttLink>();

        //查询所有的WorkOrder
        List<ProWorkorder> workorders = proWorkorderService.selectProWorkorderList(proWorkorder);

        //为每个workOrder生成type=project的GanttData
        //为每个proTask生产type=task的GanttData
        ProTask param = new ProTask();
        if(CollUtil.isNotEmpty(workorders)){
            for (ProWorkorder workorder: workorders
                 ) {
                //先添加当前的生产工单TASK
                GanttData wdata = new GanttData();
                wdata.setId("MO"+workorder.getWorkorderId().toString());
                wdata.setText(new StringBuilder().append(workorder.getProductName()).append(workorder.getQuantity().stripTrailingZeros().toPlainString()).append(workorder.getUnitOfMeasure()).toString());//默认使用“[产品]+[数量]+[单位]”格式。
                wdata.setProduct(workorder.getProductName());
                wdata.setQuantity(workorder.getQuantity());
                if(workorder.getParentId().longValue()!=0L){
                    wdata.setParent("MO"+workorder.getParentId().toString());
                }
                BigDecimal produced = workorder.getQuantityProduced();
                BigDecimal quantitiy = workorder.getQuantity();
                wdata.setProgress( produced.divide(quantitiy,BigDecimal.ROUND_HALF_UP).floatValue());
                wdata.setDuration(0L);
                wdata.setType(UserConstants.GANTT_TASK_TYPE_PROJECT);
                ganttData.add(wdata);

                //查询当前生产工单下所有的生产任务
                param.setWorkorderId(workorder.getWorkorderId());
                List<ProTask> proTasks = proTaskService.selectProTaskList(param);
                if(CollUtil.isNotEmpty(proTasks)){
                    for (ProTask task:proTasks
                         ) {
                        GanttData data = new GanttData();
                        data.setId(task.getTaskId().toString());//使用生产任务的ID作为甘特图TASK的ID
                        data.setText(new StringBuilder().append(task.getItemName()).append(task.getQuantity().stripTrailingZeros().toPlainString()).append(task.getUnitOfMeasure()).toString()); //默认使用“[产品]+[数量]+[单位]”格式。
                        data.setColor(task.getColorCode());
                        data.setDuration(task.getDuration());
                        data.setStart_date(task.getStartTime());
                        data.setParent("MO"+workorder.getWorkorderId().toString());//这里要设置为"MO+生产工单ID"的格式
                        data.setProduct(task.getItemName());
                        data.setQuantity(task.getQuantity());
                        data.setProcess(task.getProcessName());
                        data.setWorkstation(task.getWorkstationName());
                        BigDecimal taskproduced = task.getQuantityProduced();
                        BigDecimal taskquantitiy = task.getQuantity();
                        data.setProgress(taskproduced.divide(taskquantitiy,BigDecimal.ROUND_HALF_UP).floatValue());
                        data.setType(UserConstants.GANTT_TASK_TYPE_TASK);
                        ganttData.add(data);
                    }
                }
            }
        }

        ganttTask.setData(ganttData);
        ganttTask.setLinks(ganttLinks);
        return AjaxResult.success(ganttTask);
    }


    /**
     * 新增生产任务
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:protask:add')")
    @Log(title = "生产任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProTask proTask)
    {
        if(proTask.getQuantity().compareTo(BigDecimal.ZERO) !=1){
            return AjaxResult.error("排产数量必须大于0！");
        }

        if(!StringUtils.isNotNull(proTask.getWorkstationId())){
            return AjaxResult.error("请选择工作站！");
        }

        if(proTask.getDuration()<=0){
            return AjaxResult.error("生产时长必须大于0！");
        }

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

        //工艺信息
        if(StringUtils.isNotNull(proTask.getRouteId())){
            ProRoute route = proRouteService.selectProRouteByRouteId(proTask.getRouteId());
            if(StringUtils.isNotNull(route)){
                proTask.setRouteCode(route.getRouteCode());
            }else {
                return AjaxResult.error("当前生产任务对应的工艺路线信息无效！"+proTask.getRouteId());
            }
        }

        //工序信息
        ProProcess process = proProcessService.selectProProcessByProcessId(proTask.getProcessId());
        if(StringUtils.isNotNull(process)){
            proTask.setProcessId(process.getProcessId());
            proTask.setProcessCode(process.getProcessCode());
            proTask.setProcessName(process.getProcessName());
        }else{
            return AjaxResult.error("当前生产任务对应的工序信息无效！"+proTask.getProcessId());
        }


        //自动生成任务编号和名称
        proTask.setTaskCode(autoCodeUtil.genSerialCode(UserConstants.TASK_CODE,null));
        proTask.setTaskName(new StringBuilder().append(proTask.getItemName()).append("【").append(proTask.getQuantity().toString()).append("】").append(proTask.getUnitOfMeasure()).toString());



        return toAjax(proTaskService.insertProTask(proTask));
    }

    /**
     * 修改生产任务
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:protask:edit')")
    @Log(title = "生产任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProTask proTask)
    {
        if(proTask.getQuantity().compareTo(BigDecimal.ZERO) !=1){
            return AjaxResult.error("排产数量必须大于0！");
        }

        if(!StringUtils.isNotNull(proTask.getWorkstationId())){
            return AjaxResult.error("请选择工作站！");
        }

        if(proTask.getDuration()<=0){
            return AjaxResult.error("生产时长必须大于0！");
        }

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

        //工艺信息
        if(StringUtils.isNotNull(proTask.getRouteId())){
            ProRoute route = proRouteService.selectProRouteByRouteId(proTask.getRouteId());
            if(StringUtils.isNotNull(route)){
                proTask.setRouteCode(route.getRouteCode());
            }else {
                return AjaxResult.error("当前生产任务对应的工艺路线信息无效！"+proTask.getRouteId());
            }
        }

        //工序信息
        ProProcess process = proProcessService.selectProProcessByProcessId(proTask.getProcessId());
        if(StringUtils.isNotNull(process)){
            proTask.setProcessId(process.getProcessId());
            proTask.setProcessCode(process.getProcessCode());
            proTask.setProcessName(process.getProcessName());
        }else{
            return AjaxResult.error("当前生产任务对应的工序信息无效！"+proTask.getProcessId());
        }

        return toAjax(proTaskService.updateProTask(proTask));
    }

    /**
     * 删除生产任务
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:protask:remove')")
    @Log(title = "生产任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds)
    {
        return toAjax(proTaskService.deleteProTaskByTaskIds(taskIds));
    }
}
