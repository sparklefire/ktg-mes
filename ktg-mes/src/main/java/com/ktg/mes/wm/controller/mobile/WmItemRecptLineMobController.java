package com.ktg.mes.wm.controller.mobile;

import com.ktg.common.annotation.Log;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.core.page.TableDataInfo;
import com.ktg.common.enums.BusinessType;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.wm.domain.WmItemRecptLine;
import com.ktg.mes.wm.domain.WmStorageArea;
import com.ktg.mes.wm.domain.WmStorageLocation;
import com.ktg.mes.wm.domain.WmWarehouse;
import com.ktg.mes.wm.service.IWmItemRecptLineService;
import com.ktg.mes.wm.service.IWmStorageAreaService;
import com.ktg.mes.wm.service.IWmStorageLocationService;
import com.ktg.mes.wm.service.IWmWarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("采购入库明细")
@RestController
@RequestMapping("/mobile/wm/itemrecptline")
public class WmItemRecptLineMobController extends BaseController {

    @Autowired
    private IWmItemRecptLineService wmItemRecptLineService;


    @Autowired
    private IWmWarehouseService wmWarehouseService;

    @Autowired
    private IWmStorageLocationService wmStorageLocationService;

    @Autowired
    private IWmStorageAreaService wmStorageAreaService;

    /**
     * 查询物料入库单行列表
     */
    @ApiOperation("查询采购入库单明细信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:itemrecptline:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmItemRecptLine wmItemRecptLine)
    {
        startPage();
        List<WmItemRecptLine> list = wmItemRecptLineService.selectWmItemRecptLineList(wmItemRecptLine);
        return getDataTable(list);
    }

    /**
     * 获取物料入库单行详细信息
     */
    @ApiOperation("查看采购入库单明细信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:itemrecptline:query')")
    @GetMapping(value = "/{lineId}")
    public AjaxResult getInfo(@PathVariable("lineId") Long lineId)
    {
        return AjaxResult.success(wmItemRecptLineService.selectWmItemRecptLineByLineId(lineId));
    }

    /**
     * 新增物料入库单行
     */
    @ApiOperation("新增采购入库单明细信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:itemrecptline:add')")
    @Log(title = "物料入库单行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmItemRecptLine wmItemRecptLine)
    {
        if(StringUtils.isNotNull(wmItemRecptLine.getWarehouseId())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseId(wmItemRecptLine.getWarehouseId());
            wmItemRecptLine.setWarehouseCode(warehouse.getWarehouseCode());
            wmItemRecptLine.setWarehouseName(warehouse.getWarehouseName());
        }else if(StringUtils.isNotNull(wmItemRecptLine.getWarehouseCode())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseCode(wmItemRecptLine.getWarehouseCode());
            wmItemRecptLine.setWarehouseId(warehouse.getWarehouseId());
            wmItemRecptLine.setWarehouseCode(warehouse.getWarehouseCode());
            wmItemRecptLine.setWarehouseName(warehouse.getWarehouseName());
        }

        if(StringUtils.isNotNull(wmItemRecptLine.getLocationId())){
            WmStorageLocation location = wmStorageLocationService.selectWmStorageLocationByLocationId(wmItemRecptLine.getLocationId());
            wmItemRecptLine.setLocationCode(location.getLocationCode());
            wmItemRecptLine.setLocationName(location.getLocationName());
        } else if(StringUtils.isNotNull(wmItemRecptLine.getLocationCode())){
            WmStorageLocation location = wmStorageLocationService.selectWmStorageLocationByLocationCode(wmItemRecptLine.getLocationCode());
            wmItemRecptLine.setLocationId(location.getLocationId());
            wmItemRecptLine.setLocationCode(location.getLocationCode());
            wmItemRecptLine.setLocationName(location.getLocationName());
        }

        if(StringUtils.isNotNull(wmItemRecptLine.getAreaId())){
            WmStorageArea area = wmStorageAreaService.selectWmStorageAreaByAreaId(wmItemRecptLine.getAreaId());
            wmItemRecptLine.setAreaCode(area.getAreaCode());
            wmItemRecptLine.setAreaName(area.getAreaName());
        } else if(StringUtils.isNotNull(wmItemRecptLine.getAreaCode())){
            WmStorageArea area = wmStorageAreaService.selectWmStorageAreaByAreaCode(wmItemRecptLine.getAreaCode());
            wmItemRecptLine.setAreaId(area.getAreaId());
            wmItemRecptLine.setAreaCode(area.getAreaCode());
            wmItemRecptLine.setAreaName(area.getAreaName());
        }
        wmItemRecptLine.setCreateBy(getUsername());
        wmItemRecptLineService.insertWmItemRecptLine(wmItemRecptLine);
        return AjaxResult.success(wmItemRecptLine);
    }

    /**
     * 修改物料入库单行
     */
    @ApiOperation("修改采购入库单明细信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:itemrecptline:edit')")
    @Log(title = "物料入库单行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmItemRecptLine wmItemRecptLine)
    {
        if(StringUtils.isNotNull(wmItemRecptLine.getWarehouseId())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseId(wmItemRecptLine.getWarehouseId());
            wmItemRecptLine.setWarehouseCode(warehouse.getWarehouseCode());
            wmItemRecptLine.setWarehouseName(warehouse.getWarehouseName());
        }else if(StringUtils.isNotNull(wmItemRecptLine.getWarehouseCode())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseCode(wmItemRecptLine.getWarehouseCode());
            wmItemRecptLine.setWarehouseId(warehouse.getWarehouseId());
            wmItemRecptLine.setWarehouseCode(warehouse.getWarehouseCode());
            wmItemRecptLine.setWarehouseName(warehouse.getWarehouseName());
        }

        if(StringUtils.isNotNull(wmItemRecptLine.getLocationId())){
            WmStorageLocation location = wmStorageLocationService.selectWmStorageLocationByLocationId(wmItemRecptLine.getLocationId());
            wmItemRecptLine.setLocationCode(location.getLocationCode());
            wmItemRecptLine.setLocationName(location.getLocationName());
        } else if(StringUtils.isNotNull(wmItemRecptLine.getLocationCode())){
            WmStorageLocation location = wmStorageLocationService.selectWmStorageLocationByLocationCode(wmItemRecptLine.getLocationCode());
            wmItemRecptLine.setLocationId(location.getLocationId());
            wmItemRecptLine.setLocationCode(location.getLocationCode());
            wmItemRecptLine.setLocationName(location.getLocationName());
        }

        if(StringUtils.isNotNull(wmItemRecptLine.getAreaId())){
            WmStorageArea area = wmStorageAreaService.selectWmStorageAreaByAreaId(wmItemRecptLine.getAreaId());
            wmItemRecptLine.setAreaCode(area.getAreaCode());
            wmItemRecptLine.setAreaName(area.getAreaName());
        } else if(StringUtils.isNotNull(wmItemRecptLine.getAreaCode())){
            WmStorageArea area = wmStorageAreaService.selectWmStorageAreaByAreaCode(wmItemRecptLine.getAreaCode());
            wmItemRecptLine.setAreaId(area.getAreaId());
            wmItemRecptLine.setAreaCode(area.getAreaCode());
            wmItemRecptLine.setAreaName(area.getAreaName());
        }
        return toAjax(wmItemRecptLineService.updateWmItemRecptLine(wmItemRecptLine));
    }


    /**
     * 删除物料入库单行
     */
    @ApiOperation("删除采购入库单明细信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:itemrecptline:remove')")
    @Log(title = "物料入库单行", businessType = BusinessType.DELETE)
    @DeleteMapping("/{lineIds}")
    public AjaxResult remove(@PathVariable Long[] lineIds)
    {
        return toAjax(wmItemRecptLineService.deleteWmItemRecptLineByLineIds(lineIds));
    }

}
