package com.ktg.mes.cal.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ktg.common.constant.UserConstants;
import com.ktg.mes.cal.domain.CalPlan;
import com.ktg.mes.cal.service.ICalPlanService;
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
import com.ktg.mes.cal.domain.CalShift;
import com.ktg.mes.cal.service.ICalShiftService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 计划班次Controller
 * 
 * @author yinjinlu
 * @date 2022-06-06
 */
@RestController
@RequestMapping("/mes/cal/shift")
public class CalShiftController extends BaseController
{
    @Autowired
    private ICalShiftService calShiftService;

    @Autowired
    private ICalPlanService calPlanService;

    /**
     * 查询计划班次列表
     */
    @PreAuthorize("@ss.hasPermi('mes:cal:shift:list')")
    @GetMapping("/list")
    public TableDataInfo list(CalShift calShift)
    {
        startPage();
        List<CalShift> list = calShiftService.selectCalShiftList(calShift);
        return getDataTable(list);
    }

    /**
     * 导出计划班次列表
     */
    @PreAuthorize("@ss.hasPermi('mes:cal:shift:export')")
    @Log(title = "计划班次", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CalShift calShift)
    {
        List<CalShift> list = calShiftService.selectCalShiftList(calShift);
        ExcelUtil<CalShift> util = new ExcelUtil<CalShift>(CalShift.class);
        util.exportExcel(response, list, "计划班次数据");
    }

    /**
     * 获取计划班次详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:cal:shift:query')")
    @GetMapping(value = "/{shiftId}")
    public AjaxResult getInfo(@PathVariable("shiftId") Long shiftId)
    {
        return AjaxResult.success(calShiftService.selectCalShiftByShiftId(shiftId));
    }

    /**
     * 新增计划班次
     */
    @PreAuthorize("@ss.hasPermi('mes:cal:shift:add')")
    @Log(title = "计划班次", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CalShift calShift)
    {
        int count = calShiftService.checkShiftCount(calShift.getPlanId());
        CalPlan plan = calPlanService.selectCalPlanByPlanId(calShift.getPlanId());
        if(UserConstants.CAL_SHIFT_TYPE_SINGLE.equals(plan.getShiftType())&&count>0){
            return AjaxResult.error("轮班方式为 白班 时只能有一个班次！");
        }
        if(UserConstants.CAL_SHIFT_TYPE_TWO.equals(plan.getShiftType())&&count>1){
            return AjaxResult.error("轮班方式为 两班倒 时只能有两个班次！");
        }
        if(UserConstants.CAL_SHIFT_TYPE_THREE.equals(plan.getShiftType())&&count>2){
            return AjaxResult.error("轮班方式为 三班倒 时只能有三个班次！");
        }

        return toAjax(calShiftService.insertCalShift(calShift));
    }

    /**
     * 修改计划班次
     */
    @PreAuthorize("@ss.hasPermi('mes:cal:shift:edit')")
    @Log(title = "计划班次", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CalShift calShift)
    {
        return toAjax(calShiftService.updateCalShift(calShift));
    }

    /**
     * 删除计划班次
     */
    @PreAuthorize("@ss.hasPermi('mes:cal:shift:remove')")
    @Log(title = "计划班次", businessType = BusinessType.DELETE)
	@DeleteMapping("/{shiftIds}")
    public AjaxResult remove(@PathVariable Long[] shiftIds)
    {
        return toAjax(calShiftService.deleteCalShiftByShiftIds(shiftIds));
    }
}
