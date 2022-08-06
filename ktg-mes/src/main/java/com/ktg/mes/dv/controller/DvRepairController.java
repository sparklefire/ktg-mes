package com.ktg.mes.dv.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ktg.common.constant.UserConstants;
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
import com.ktg.mes.dv.domain.DvRepair;
import com.ktg.mes.dv.service.IDvRepairService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 设备维修单Controller
 * 
 * @author yinjinlu
 * @date 2022-08-06
 */
@RestController
@RequestMapping("/mes/dv/repair")
public class DvRepairController extends BaseController
{
    @Autowired
    private IDvRepairService dvRepairService;

    /**
     * 查询设备维修单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:repair:list')")
    @GetMapping("/list")
    public TableDataInfo list(DvRepair dvRepair)
    {
        startPage();
        List<DvRepair> list = dvRepairService.selectDvRepairList(dvRepair);
        return getDataTable(list);
    }

    /**
     * 导出设备维修单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:repair:export')")
    @Log(title = "设备维修单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DvRepair dvRepair)
    {
        List<DvRepair> list = dvRepairService.selectDvRepairList(dvRepair);
        ExcelUtil<DvRepair> util = new ExcelUtil<DvRepair>(DvRepair.class);
        util.exportExcel(response, list, "设备维修单数据");
    }

    /**
     * 获取设备维修单详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:repair:query')")
    @GetMapping(value = "/{repairId}")
    public AjaxResult getInfo(@PathVariable("repairId") Long repairId)
    {
        return AjaxResult.success(dvRepairService.selectDvRepairByRepairId(repairId));
    }

    /**
     * 新增设备维修单
     */
    @PreAuthorize("@ss.hasPermi('dv:repair:add')")
    @Log(title = "设备维修单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DvRepair dvRepair)
    {
        if(UserConstants.NOT_UNIQUE.equals(dvRepairService.checkCodeUnique(dvRepair))){
            return AjaxResult.error("维修单编号已存！");
        }
        return toAjax(dvRepairService.insertDvRepair(dvRepair));
    }

    /**
     * 修改设备维修单
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:repair:edit')")
    @Log(title = "设备维修单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DvRepair dvRepair)
    {
        if(UserConstants.NOT_UNIQUE.equals(dvRepairService.checkCodeUnique(dvRepair))){
            return AjaxResult.error("维修单编号已存！");
        }
        return toAjax(dvRepairService.updateDvRepair(dvRepair));
    }

    /**
     * 删除设备维修单
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:repair:remove')")
    @Log(title = "设备维修单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{repairIds}")
    public AjaxResult remove(@PathVariable Long[] repairIds)
    {
        return toAjax(dvRepairService.deleteDvRepairByRepairIds(repairIds));
    }
}
