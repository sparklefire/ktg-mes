package com.ktg.mes.dv.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ktg.common.constant.UserConstants;
import com.ktg.mes.wm.utils.WmBarCodeUtil;
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
import com.ktg.mes.dv.domain.DvMachinery;
import com.ktg.mes.dv.service.IDvMachineryService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 设备Controller
 * 
 * @author yinjinlu
 * @date 2022-05-08
 */
@RestController
@RequestMapping("/mes/dv/machinery")
public class DvMachineryController extends BaseController
{
    @Autowired
    private IDvMachineryService dvMachineryService;

    @Autowired
    private WmBarCodeUtil wmBarCodeUtil;

    /**
     * 查询设备列表
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:machinery:list')")
    @GetMapping("/list")
    public TableDataInfo list(DvMachinery dvMachinery)
    {
        startPage();
        List<DvMachinery> list = dvMachineryService.selectDvMachineryList(dvMachinery);
        return getDataTable(list);
    }

    /**
     * 导出设备列表
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:machinery:export')")
    @Log(title = "设备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DvMachinery dvMachinery)
    {
        List<DvMachinery> list = dvMachineryService.selectDvMachineryList(dvMachinery);
        ExcelUtil<DvMachinery> util = new ExcelUtil<DvMachinery>(DvMachinery.class);
        util.exportExcel(response, list, "设备数据");
    }

    /**
     * 获取设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:machinery:query')")
    @GetMapping(value = "/{machineryId}")
    public AjaxResult getInfo(@PathVariable("machineryId") Long machineryId)
    {
        return AjaxResult.success(dvMachineryService.selectDvMachineryByMachineryId(machineryId));
    }

    /**
     * 新增设备
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:machinery:add')")
    @Log(title = "设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DvMachinery dvMachinery)
    {
        dvMachineryService.insertDvMachinery(dvMachinery);
        wmBarCodeUtil.generateBarCode(UserConstants.BARCODE_TYPE_MACHINERY,dvMachinery.getMachineryId(),dvMachinery.getMachineryCode(),dvMachinery.getMachineryName());
        return AjaxResult.success(dvMachinery.getMachineryId());
    }

    /**
     * 修改设备
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:machinery:edit')")
    @Log(title = "设备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DvMachinery dvMachinery)
    {
        return toAjax(dvMachineryService.updateDvMachinery(dvMachinery));
    }

    /**
     * 删除设备
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:machinery:remove')")
    @Log(title = "设备", businessType = BusinessType.DELETE)
	@DeleteMapping("/{machineryIds}")
    public AjaxResult remove(@PathVariable Long[] machineryIds)
    {
        return toAjax(dvMachineryService.deleteDvMachineryByMachineryIds(machineryIds));
    }
}
