package com.ktg.mes.wm.controller.mobile;

import com.ktg.common.annotation.Log;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.core.page.TableDataInfo;
import com.ktg.common.enums.BusinessType;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.wm.domain.WmMaterialStock;
import com.ktg.mes.wm.domain.WmProductSalseLine;
import com.ktg.mes.wm.service.IWmMaterialStockService;
import com.ktg.mes.wm.service.IWmProductSalseLineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("产品销售出库明细")
@RestController
@RequestMapping("/mobile/wm/productsalseline")
public class WmProductSalseLineMobController extends BaseController {
    @Autowired
    private IWmProductSalseLineService wmProductSalseLineService;

    @Autowired
    private IWmMaterialStockService wmMaterialStockService;

    /**
     *
     * 查询产品销售出库行列表
     */
    @ApiOperation("查询销售出库行列表接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:productsalse:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmProductSalseLine wmProductSalseLine)
    {
        startPage();
        List<WmProductSalseLine> list = wmProductSalseLineService.selectWmProductSalseLineList(wmProductSalseLine);
        return getDataTable(list);
    }


    /**
     * 获取产品销售出库行详细信息
     */
    @ApiOperation("获取销售出库行信息明细接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:productsalse:query')")
    @GetMapping(value = "/{lineId}")
    public AjaxResult getInfo(@PathVariable("lineId") Long lineId)
    {
        return AjaxResult.success(wmProductSalseLineService.selectWmProductSalseLineByLineId(lineId));
    }

    /**
     * 新增产品销售出库行
     */
    @ApiOperation("新增销售出库信息明细信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:productsalse:add')")
    @Log(title = "产品销售出库行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmProductSalseLine wmProductSalseLine)
    {
        if(StringUtils.isNotNull(wmProductSalseLine.getMaterialStockId())){
            WmMaterialStock stock = wmMaterialStockService.selectWmMaterialStockByMaterialStockId(wmProductSalseLine.getMaterialStockId());
            wmProductSalseLine.setItemId(stock.getItemId());
            wmProductSalseLine.setItemCode(stock.getItemCode());
            wmProductSalseLine.setItemName(stock.getItemName());
            wmProductSalseLine.setSpecification(stock.getSpecification());
            wmProductSalseLine.setUnitOfMeasure(stock.getUnitOfMeasure());
            wmProductSalseLine.setBatchCode(stock.getBatchCode());
            wmProductSalseLine.setWarehouseId(stock.getWarehouseId());
            wmProductSalseLine.setWarehouseCode(stock.getWarehouseCode());
            wmProductSalseLine.setWarehouseName(stock.getWarehouseName());
            wmProductSalseLine.setLocationId(stock.getLocationId());
            wmProductSalseLine.setLocationCode(stock.getLocationCode());
            wmProductSalseLine.setLocationName(stock.getLocationName());
            wmProductSalseLine.setAreaId(stock.getAreaId());
            wmProductSalseLine.setAreaCode(stock.getAreaCode());
            wmProductSalseLine.setAreaName(stock.getAreaName());
        }

        wmProductSalseLine.setCreateBy(getUsername());
        wmProductSalseLineService.insertWmProductSalseLine(wmProductSalseLine);
        return AjaxResult.success(wmProductSalseLine);
    }

    /**
     * 修改产品销售出库行
     */
    @ApiOperation("编辑销售出库明细信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:productsalse:edit')")
    @Log(title = "产品销售出库行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmProductSalseLine wmProductSalseLine)
    {
        if(StringUtils.isNotNull(wmProductSalseLine.getMaterialStockId())){
            WmMaterialStock stock = wmMaterialStockService.selectWmMaterialStockByMaterialStockId(wmProductSalseLine.getMaterialStockId());
            wmProductSalseLine.setItemId(stock.getItemId());
            wmProductSalseLine.setItemCode(stock.getItemCode());
            wmProductSalseLine.setItemName(stock.getItemName());
            wmProductSalseLine.setSpecification(stock.getSpecification());
            wmProductSalseLine.setUnitOfMeasure(stock.getUnitOfMeasure());
            wmProductSalseLine.setBatchCode(stock.getBatchCode());
            wmProductSalseLine.setWarehouseId(stock.getWarehouseId());
            wmProductSalseLine.setWarehouseCode(stock.getWarehouseCode());
            wmProductSalseLine.setWarehouseName(stock.getWarehouseName());
            wmProductSalseLine.setLocationId(stock.getLocationId());
            wmProductSalseLine.setLocationCode(stock.getLocationCode());
            wmProductSalseLine.setLocationName(stock.getLocationName());
            wmProductSalseLine.setAreaId(stock.getAreaId());
            wmProductSalseLine.setAreaCode(stock.getAreaCode());
            wmProductSalseLine.setAreaName(stock.getAreaName());
        }

        return toAjax(wmProductSalseLineService.updateWmProductSalseLine(wmProductSalseLine));
    }

    /**
     * 删除产品销售出库行
     */
    @ApiOperation("删除销售出库明细信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:productsalse:remove')")
    @Log(title = "产品销售出库行", businessType = BusinessType.DELETE)
    @DeleteMapping("/{lineIds}")
    public AjaxResult remove(@PathVariable Long[] lineIds)
    {
        return toAjax(wmProductSalseLineService.deleteWmProductSalseLineByLineIds(lineIds));
    }
}
