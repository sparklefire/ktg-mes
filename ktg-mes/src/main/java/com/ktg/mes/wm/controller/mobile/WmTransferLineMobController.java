package com.ktg.mes.wm.controller.mobile;

import com.ktg.common.annotation.Log;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.core.page.TableDataInfo;
import com.ktg.common.enums.BusinessType;
import com.ktg.common.utils.StringUtils;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.mes.wm.domain.*;
import com.ktg.mes.wm.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api("转移调拨明细")
@RestController
@RequestMapping("/mobile/wm/transferline")
public class WmTransferLineMobController extends BaseController {
    @Autowired
    private IWmTransferLineService wmTransferLineService;

    @Autowired
    private IWmWarehouseService wmWarehouseService;

    @Autowired
    private IWmStorageLocationService wmStorageLocationService;

    @Autowired
    private IWmStorageAreaService wmStorageAreaService;

    @Autowired
    private IWmMaterialStockService wmMaterialStockService;

    /**
     * 查询转移单行列表
     */
    @ApiOperation("查询转移调拨单明细清单接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:transfer:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmTransferLine wmTransferLine)
    {
        startPage();
        List<WmTransferLine> list = wmTransferLineService.selectWmTransferLineList(wmTransferLine);
        return getDataTable(list);
    }


    /**
     * 获取转移单行详细信息
     */
    @ApiOperation("获取转移调拨单明细详情接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:transfer:query')")
    @GetMapping(value = "/{lineId}")
    public AjaxResult getInfo(@PathVariable("lineId") Long lineId)
    {
        return AjaxResult.success(wmTransferLineService.selectWmTransferLineByLineId(lineId));
    }

    /**
     * 新增转移单行
     */
    @ApiOperation("新增转移调拨单明细信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:transfer:add')")
    @Log(title = "转移单行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmTransferLine wmTransferLine)
    {
        if(StringUtils.isNotNull(wmTransferLine.getMaterialStockId())){
            WmMaterialStock stock = wmMaterialStockService.selectWmMaterialStockByMaterialStockId(wmTransferLine.getMaterialStockId());
            wmTransferLine.setItemId(stock.getItemId());
            wmTransferLine.setItemCode(stock.getItemCode());
            wmTransferLine.setItemName(stock.getItemName());
            wmTransferLine.setSpecification(stock.getSpecification());
            wmTransferLine.setUnitOfMeasure(stock.getUnitOfMeasure());
            wmTransferLine.setBatchCode(stock.getBatchCode());
            wmTransferLine.setVendorId(stock.getVendorId());
            wmTransferLine.setVendorCode(stock.getVendorCode());
            wmTransferLine.setVendorName(stock.getVendorName());
            wmTransferLine.setVendorNick(stock.getVendorNick());
            wmTransferLine.setWorkorderId(stock.getWorkorderId());
            wmTransferLine.setWorkorderCode(stock.getWorkorderCode());
        }

        if(StringUtils.isNotNull(wmTransferLine.getFromWarehouseId())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseId(wmTransferLine.getFromWarehouseId());
            wmTransferLine.setFromWarehouseCode(warehouse.getWarehouseCode());
            wmTransferLine.setFromWarehouseName(warehouse.getWarehouseName());
        }
        if(StringUtils.isNotNull(wmTransferLine.getFromLocationId())){
            WmStorageLocation location = wmStorageLocationService.selectWmStorageLocationByLocationId(wmTransferLine.getFromLocationId());
            wmTransferLine.setFromLocationCode(location.getLocationCode());
            wmTransferLine.setFromLocationName(location.getLocationName());
        }
        if(StringUtils.isNotNull(wmTransferLine.getFromAreaId())){
            WmStorageArea area = wmStorageAreaService.selectWmStorageAreaByAreaId(wmTransferLine.getFromAreaId());
            wmTransferLine.setFromAreaCode(area.getAreaCode());
            wmTransferLine.setFromAreaName(area.getAreaName());
        }
        if(StringUtils.isNotNull(wmTransferLine.getToWarehouseId())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseId(wmTransferLine.getToWarehouseId());
            wmTransferLine.setToWarehouseCode(warehouse.getWarehouseCode());
            wmTransferLine.setToWarehouseName(warehouse.getWarehouseName());
        }
        if(StringUtils.isNotNull(wmTransferLine.getFromLocationId())){
            WmStorageLocation location = wmStorageLocationService.selectWmStorageLocationByLocationId(wmTransferLine.getToLocationId());
            wmTransferLine.setToLocationCode(location.getLocationCode());
            wmTransferLine.setToLocationName(location.getLocationName());
        }
        if(StringUtils.isNotNull(wmTransferLine.getFromAreaId())){
            WmStorageArea area = wmStorageAreaService.selectWmStorageAreaByAreaId(wmTransferLine.getToAreaId());
            wmTransferLine.setToAreaCode(area.getAreaCode());
            wmTransferLine.setToAreaName(area.getAreaName());
        }
        wmTransferLine.setCreateBy(getUsername());
        wmTransferLineService.insertWmTransferLine(wmTransferLine);
        return AjaxResult.success(wmTransferLine);
    }

    /**
     * 修改转移单行
     */
    @ApiOperation("编辑转移调拨单明细信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:transfer:edit')")
    @Log(title = "转移单行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmTransferLine wmTransferLine)
    {
        if(StringUtils.isNotNull(wmTransferLine.getMaterialStockId())){
            WmMaterialStock stock = wmMaterialStockService.selectWmMaterialStockByMaterialStockId(wmTransferLine.getMaterialStockId());
            wmTransferLine.setItemId(stock.getItemId());
            wmTransferLine.setItemCode(stock.getItemCode());
            wmTransferLine.setItemName(stock.getItemName());
            wmTransferLine.setSpecification(stock.getSpecification());
            wmTransferLine.setUnitOfMeasure(stock.getUnitOfMeasure());
            wmTransferLine.setBatchCode(stock.getBatchCode());
            wmTransferLine.setVendorId(stock.getVendorId());
            wmTransferLine.setVendorCode(stock.getVendorCode());
            wmTransferLine.setVendorName(stock.getVendorName());
            wmTransferLine.setVendorNick(stock.getVendorNick());
            wmTransferLine.setWorkorderId(stock.getWorkorderId());
            wmTransferLine.setWorkorderCode(stock.getWorkorderCode());
        }

        if(StringUtils.isNotNull(wmTransferLine.getFromWarehouseId())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseId(wmTransferLine.getFromWarehouseId());
            wmTransferLine.setFromWarehouseCode(warehouse.getWarehouseCode());
            wmTransferLine.setFromWarehouseName(warehouse.getWarehouseName());
        }
        if(StringUtils.isNotNull(wmTransferLine.getFromLocationId())){
            WmStorageLocation location = wmStorageLocationService.selectWmStorageLocationByLocationId(wmTransferLine.getFromLocationId());
            wmTransferLine.setFromLocationCode(location.getLocationCode());
            wmTransferLine.setFromLocationName(location.getLocationName());
        }
        if(StringUtils.isNotNull(wmTransferLine.getFromAreaId())){
            WmStorageArea area = wmStorageAreaService.selectWmStorageAreaByAreaId(wmTransferLine.getFromAreaId());
            wmTransferLine.setFromAreaCode(area.getAreaCode());
            wmTransferLine.setFromAreaName(area.getAreaName());
        }
        if(StringUtils.isNotNull(wmTransferLine.getToWarehouseId())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseId(wmTransferLine.getToWarehouseId());
            wmTransferLine.setToWarehouseCode(warehouse.getWarehouseCode());
            wmTransferLine.setToWarehouseName(warehouse.getWarehouseName());
        }
        if(StringUtils.isNotNull(wmTransferLine.getFromLocationId())){
            WmStorageLocation location = wmStorageLocationService.selectWmStorageLocationByLocationId(wmTransferLine.getToLocationId());
            wmTransferLine.setToLocationCode(location.getLocationCode());
            wmTransferLine.setToLocationName(location.getLocationName());
        }
        if(StringUtils.isNotNull(wmTransferLine.getFromAreaId())){
            WmStorageArea area = wmStorageAreaService.selectWmStorageAreaByAreaId(wmTransferLine.getToAreaId());
            wmTransferLine.setToAreaCode(area.getAreaCode());
            wmTransferLine.setToAreaName(area.getAreaName());
        }
        return toAjax(wmTransferLineService.updateWmTransferLine(wmTransferLine));
    }

    /**
     * 删除转移单行
     */
    @ApiOperation("删除转移调拨单明细信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:transfer:remove')")
    @Log(title = "转移单行", businessType = BusinessType.DELETE)
    @DeleteMapping("/{lineIds}")
    public AjaxResult remove(@PathVariable Long[] lineIds)
    {
        return toAjax(wmTransferLineService.deleteWmTransferLineByLineIds(lineIds));
    }
}
