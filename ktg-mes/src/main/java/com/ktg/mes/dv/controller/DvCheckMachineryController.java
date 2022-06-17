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
import com.ktg.mes.dv.domain.DvCheckMachinery;
import com.ktg.mes.dv.service.IDvCheckMachineryService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 点检设备Controller
 * 
 * @author yinjinlu
 * @date 2022-06-17
 */
@RestController
@RequestMapping("/mes/dv/checkmachinery")
public class DvCheckMachineryController extends BaseController
{
    @Autowired
    private IDvCheckMachineryService dvCheckMachineryService;

    /**
     * 查询点检设备列表
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:checkmachinery:list')")
    @GetMapping("/list")
    public TableDataInfo list(DvCheckMachinery dvCheckMachinery)
    {
        startPage();
        List<DvCheckMachinery> list = dvCheckMachineryService.selectDvCheckMachineryList(dvCheckMachinery);
        return getDataTable(list);
    }

    /**
     * 导出点检设备列表
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:checkmachinery:export')")
    @Log(title = "点检设备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DvCheckMachinery dvCheckMachinery)
    {
        List<DvCheckMachinery> list = dvCheckMachineryService.selectDvCheckMachineryList(dvCheckMachinery);
        ExcelUtil<DvCheckMachinery> util = new ExcelUtil<DvCheckMachinery>(DvCheckMachinery.class);
        util.exportExcel(response, list, "点检设备数据");
    }

    /**
     * 获取点检设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:checkmachinery:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return AjaxResult.success(dvCheckMachineryService.selectDvCheckMachineryByRecordId(recordId));
    }

    /**
     * 新增点检设备
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:checkmachinery:add')")
    @Log(title = "点检设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DvCheckMachinery dvCheckMachinery)
    {
        if(UserConstants.NOT_UNIQUE.equals(dvCheckMachineryService.checkMachineryUnique(dvCheckMachinery))){
            return AjaxResult.error("设备已设置过点检计划！");
        }
        return toAjax(dvCheckMachineryService.insertDvCheckMachinery(dvCheckMachinery));
    }

    /**
     * 修改点检设备
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:checkmachinery:edit')")
    @Log(title = "点检设备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DvCheckMachinery dvCheckMachinery)
    {
        if(UserConstants.NOT_UNIQUE.equals(dvCheckMachineryService.checkMachineryUnique(dvCheckMachinery))){
            return AjaxResult.error("设备已设置过点检计划！");
        }
        return toAjax(dvCheckMachineryService.updateDvCheckMachinery(dvCheckMachinery));
    }

    /**
     * 删除点检设备
     */
    @PreAuthorize("@ss.hasPermi('mes:dv:checkmachinery:remove')")
    @Log(title = "点检设备", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(dvCheckMachineryService.deleteDvCheckMachineryByRecordIds(recordIds));
    }
}
