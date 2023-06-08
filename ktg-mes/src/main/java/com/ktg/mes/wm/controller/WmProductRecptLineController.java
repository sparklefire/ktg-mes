package com.ktg.mes.wm.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ktg.common.utils.StringUtils;
import com.ktg.mes.wm.domain.WmStorageArea;
import com.ktg.mes.wm.domain.WmStorageLocation;
import com.ktg.mes.wm.domain.WmWarehouse;
import com.ktg.mes.wm.service.IWmStorageAreaService;
import com.ktg.mes.wm.service.IWmStorageLocationService;
import com.ktg.mes.wm.service.IWmWarehouseService;
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
import com.ktg.mes.wm.domain.WmProductRecptLine;
import com.ktg.mes.wm.service.IWmProductRecptLineService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 产品入库记录行Controller
 * 
 * @author yinjinlu
 * @date 2022-09-22
 */
@RestController
@RequestMapping("/mes/wm/productrecptline")
public class WmProductRecptLineController extends BaseController
{
    @Autowired
    private IWmProductRecptLineService wmProductRecptLineService;

    @Autowired
    private IWmWarehouseService wmWarehouseService;

    @Autowired
    private IWmStorageLocationService wmStorageLocationService;

    @Autowired
    private IWmStorageAreaService wmStorageAreaService;

    /**
     * 查询产品入库记录行列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:productrecptline:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmProductRecptLine wmProductRecptLine)
    {
        startPage();
        List<WmProductRecptLine> list = wmProductRecptLineService.selectWmProductRecptLineList(wmProductRecptLine);
        return getDataTable(list);
    }

    /**
     * 导出产品入库记录行列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:productrecptline:export')")
    @Log(title = "产品入库记录行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmProductRecptLine wmProductRecptLine)
    {
        List<WmProductRecptLine> list = wmProductRecptLineService.selectWmProductRecptLineList(wmProductRecptLine);
        ExcelUtil<WmProductRecptLine> util = new ExcelUtil<WmProductRecptLine>(WmProductRecptLine.class);
        util.exportExcel(response, list, "产品入库记录行数据");
    }

    /**
     * 获取产品入库记录行详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:productrecptline:query')")
    @GetMapping(value = "/{lineId}")
    public AjaxResult getInfo(@PathVariable("lineId") Long lineId)
    {
        return AjaxResult.success(wmProductRecptLineService.selectWmProductRecptLineByLineId(lineId));
    }

    /**
     * 新增产品入库记录行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:productrecptline:add')")
    @Log(title = "产品入库记录行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmProductRecptLine wmProductRecptLine)
    {
        if(StringUtils.isNotNull(wmProductRecptLine.getWarehouseId())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseId(wmProductRecptLine.getWarehouseId());
            wmProductRecptLine.setWarehouseCode(warehouse.getWarehouseCode());
            wmProductRecptLine.setWarehouseName(warehouse.getWarehouseName());
        }
        if(StringUtils.isNotNull(wmProductRecptLine.getLocationId())){
            WmStorageLocation location = wmStorageLocationService.selectWmStorageLocationByLocationId(wmProductRecptLine.getLocationId());
            wmProductRecptLine.setLocationCode(location.getLocationCode());
            wmProductRecptLine.setLocationName(location.getLocationName());
        }
        if(StringUtils.isNotNull(wmProductRecptLine.getAreaId())){
            WmStorageArea area = wmStorageAreaService.selectWmStorageAreaByAreaId(wmProductRecptLine.getAreaId());
            wmProductRecptLine.setAreaCode(area.getAreaCode());
            wmProductRecptLine.setAreaName(area.getAreaName());
        }
        wmProductRecptLine.setCreateBy(getUsername());
        return toAjax(wmProductRecptLineService.insertWmProductRecptLine(wmProductRecptLine));
    }

    /**
     * 修改产品入库记录行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:productrecptline:edit')")
    @Log(title = "产品入库记录行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmProductRecptLine wmProductRecptLine)
    {
        if(StringUtils.isNotNull(wmProductRecptLine.getWarehouseId())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseId(wmProductRecptLine.getWarehouseId());
            wmProductRecptLine.setWarehouseCode(warehouse.getWarehouseCode());
            wmProductRecptLine.setWarehouseName(warehouse.getWarehouseName());
        }
        if(StringUtils.isNotNull(wmProductRecptLine.getLocationId())){
            WmStorageLocation location = wmStorageLocationService.selectWmStorageLocationByLocationId(wmProductRecptLine.getLocationId());
            wmProductRecptLine.setLocationCode(location.getLocationCode());
            wmProductRecptLine.setLocationName(location.getLocationName());
        }
        if(StringUtils.isNotNull(wmProductRecptLine.getAreaId())){
            WmStorageArea area = wmStorageAreaService.selectWmStorageAreaByAreaId(wmProductRecptLine.getAreaId());
            wmProductRecptLine.setAreaCode(area.getAreaCode());
            wmProductRecptLine.setAreaName(area.getAreaName());
        }
        return toAjax(wmProductRecptLineService.updateWmProductRecptLine(wmProductRecptLine));
    }

    /**
     * 删除产品入库记录行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:productrecptline:remove')")
    @Log(title = "产品入库记录行", businessType = BusinessType.DELETE)
	@DeleteMapping("/{lineIds}")
    public AjaxResult remove(@PathVariable Long[] lineIds)
    {
        return toAjax(wmProductRecptLineService.deleteWmProductRecptLineByLineIds(lineIds));
    }
}
