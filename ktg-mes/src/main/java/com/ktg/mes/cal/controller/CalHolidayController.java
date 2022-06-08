package com.ktg.mes.cal.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
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
import com.ktg.mes.cal.domain.CalHoliday;
import com.ktg.mes.cal.service.ICalHolidayService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 节假日设置Controller
 * 
 * @author yinjinlu
 * @date 2022-06-08
 */
@RestController
@RequestMapping("/mes/cal/calholiday")
public class CalHolidayController extends BaseController
{
    @Autowired
    private ICalHolidayService calHolidayService;

    /**
     * 查询节假日设置列表
     */
    @PreAuthorize("@ss.hasPermi('mes:cal:calholiday:list')")
    @GetMapping("/list")
    public AjaxResult list(CalHoliday calHoliday)
    {
        List<CalHoliday> list = calHolidayService.selectCalHolidayList(calHoliday);
        return AjaxResult.success(list);
    }

    /**
     * 导出节假日设置列表
     */
    @PreAuthorize("@ss.hasPermi('mes:cal:calholiday:export')")
    @Log(title = "节假日设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CalHoliday calHoliday)
    {
        List<CalHoliday> list = calHolidayService.selectCalHolidayList(calHoliday);
        ExcelUtil<CalHoliday> util = new ExcelUtil<CalHoliday>(CalHoliday.class);
        util.exportExcel(response, list, "节假日设置数据");
    }

    /**
     * 获取节假日设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:cal:calholiday:query')")
    @GetMapping(value = "/{holidayId}")
    public AjaxResult getInfo(@PathVariable("holidayId") Long holidayId)
    {
        return AjaxResult.success(calHolidayService.selectCalHolidayByHolidayId(holidayId));
    }

    /**
     * 新增节假日设置
     */
    @PreAuthorize("@ss.hasPermi('mes:cal:calholiday:add')")
    @Log(title = "节假日设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CalHoliday calHoliday)
    {
        CalHoliday param = new CalHoliday();
        param.setTheDay(calHoliday.getTheDay());
        List<CalHoliday> days = calHolidayService.selectCalHolidayList(param);
        if(CollUtil.isNotEmpty(days)){
            calHoliday.setHolidayId(days.get(0).getHolidayId());
            return toAjax(calHolidayService.updateCalHoliday(calHoliday));
        }else{
            return toAjax(calHolidayService.insertCalHoliday(calHoliday));
        }
    }

    /**
     * 修改节假日设置
     */
    @PreAuthorize("@ss.hasPermi('mes:cal:calholiday:edit')")
    @Log(title = "节假日设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CalHoliday calHoliday)
    {
        return toAjax(calHolidayService.updateCalHoliday(calHoliday));
    }

    /**
     * 删除节假日设置
     */
    @PreAuthorize("@ss.hasPermi('mes:cal:calholiday:remove')")
    @Log(title = "节假日设置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{holidayIds}")
    public AjaxResult remove(@PathVariable Long[] holidayIds)
    {
        return toAjax(calHolidayService.deleteCalHolidayByHolidayIds(holidayIds));
    }
}
