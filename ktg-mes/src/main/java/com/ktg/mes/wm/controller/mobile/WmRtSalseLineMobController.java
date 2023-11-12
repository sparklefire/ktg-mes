package com.ktg.mes.wm.controller.mobile;

import com.ktg.common.annotation.Log;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.core.page.TableDataInfo;
import com.ktg.common.enums.BusinessType;
import com.ktg.common.utils.StringUtils;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.mes.md.domain.MdItem;
import com.ktg.mes.md.service.IMdItemService;
import com.ktg.mes.wm.domain.WmRtSalseLine;
import com.ktg.mes.wm.domain.WmStorageArea;
import com.ktg.mes.wm.domain.WmStorageLocation;
import com.ktg.mes.wm.domain.WmWarehouse;
import com.ktg.mes.wm.service.IWmRtSalseLineService;
import com.ktg.mes.wm.service.IWmStorageAreaService;
import com.ktg.mes.wm.service.IWmStorageLocationService;
import com.ktg.mes.wm.service.IWmWarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.record.pivottable.PageItemRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api("销售退货明细")
@RestController
@RequestMapping("/mobile/wm/rtsalseline")
public class WmRtSalseLineMobController extends BaseController {

    @Autowired
    private IWmRtSalseLineService wmRtSalseLineService;

    @Autowired
    private IWmWarehouseService wmWarehouseService;

    @Autowired
    private IWmStorageLocationService wmStorageLocationService;

    @Autowired
    private IWmStorageAreaService wmStorageAreaService;

    @Autowired
    private IMdItemService mdItemService;

    /**
     * 查询产品销售退货行列表
     */
    @ApiOperation("查询销售退货单明细列表接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:rtsalse:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmRtSalseLine wmRtSalseLine)
    {
        startPage();
        List<WmRtSalseLine> list = wmRtSalseLineService.selectWmRtSalseLineList(wmRtSalseLine);
        return getDataTable(list);
    }


    /**
     * 获取产品销售退货行详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtsalse:query')")
    @GetMapping(value = "/{lineId}")
    public AjaxResult getInfo(@PathVariable("lineId") Long lineId)
    {
        return AjaxResult.success(wmRtSalseLineService.selectWmRtSalseLineByLineId(lineId));
    }

    /**
     * 新增产品销售退货行
     */
    @ApiOperation("新增销售退货单明细接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:rtsalse:add')")
    @Log(title = "产品销售退货行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmRtSalseLine wmRtSalseLine)
    {
        if(StringUtils.isNotNull(wmRtSalseLine.getItemId())){
            MdItem item = mdItemService.selectMdItemById(wmRtSalseLine.getItemId());
            wmRtSalseLine.setItemCode(item.getItemCode());
            wmRtSalseLine.setItemName(item.getItemName());
            wmRtSalseLine.setSpecification(item.getSpecification());
            wmRtSalseLine.setUnitOfMeasure(item.getUnitOfMeasure());
        }

        if(StringUtils.isNotNull(wmRtSalseLine.getWarehouseId())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseId(wmRtSalseLine.getWarehouseId());
            wmRtSalseLine.setWarehouseCode(warehouse.getWarehouseCode());
            wmRtSalseLine.setWarehouseName(warehouse.getWarehouseName());
        }
        if(StringUtils.isNotNull(wmRtSalseLine.getLocationId())){
            WmStorageLocation location = wmStorageLocationService.selectWmStorageLocationByLocationId(wmRtSalseLine.getLocationId());
            wmRtSalseLine.setLocationCode(location.getLocationCode());
            wmRtSalseLine.setLocationName(location.getLocationName());
        }
        if(StringUtils.isNotNull(wmRtSalseLine.getAreaId())){
            WmStorageArea area = wmStorageAreaService.selectWmStorageAreaByAreaId(wmRtSalseLine.getAreaId());
            wmRtSalseLine.setAreaCode(area.getAreaCode());
            wmRtSalseLine.setAreaName(area.getAreaName());
        }
        wmRtSalseLine.setCreateBy(getUsername());
        return toAjax(wmRtSalseLineService.insertWmRtSalseLine(wmRtSalseLine));
    }

    /**
     * 修改产品销售退货行
     */
    @ApiOperation("编辑销售退货单明细接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:rtsalse:edit')")
    @Log(title = "产品销售退货行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmRtSalseLine wmRtSalseLine)
    {
        if(StringUtils.isNotNull(wmRtSalseLine.getItemId())){
            MdItem item = mdItemService.selectMdItemById(wmRtSalseLine.getItemId());
            wmRtSalseLine.setItemCode(item.getItemCode());
            wmRtSalseLine.setItemName(item.getItemName());
            wmRtSalseLine.setSpecification(item.getSpecification());
            wmRtSalseLine.setUnitOfMeasure(item.getUnitOfMeasure());
        }

        if(StringUtils.isNotNull(wmRtSalseLine.getWarehouseId())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseId(wmRtSalseLine.getWarehouseId());
            wmRtSalseLine.setWarehouseCode(warehouse.getWarehouseCode());
            wmRtSalseLine.setWarehouseName(warehouse.getWarehouseName());
        }
        if(StringUtils.isNotNull(wmRtSalseLine.getLocationId())){
            WmStorageLocation location = wmStorageLocationService.selectWmStorageLocationByLocationId(wmRtSalseLine.getLocationId());
            wmRtSalseLine.setLocationCode(location.getLocationCode());
            wmRtSalseLine.setLocationName(location.getLocationName());
        }
        if(StringUtils.isNotNull(wmRtSalseLine.getAreaId())){
            WmStorageArea area = wmStorageAreaService.selectWmStorageAreaByAreaId(wmRtSalseLine.getAreaId());
            wmRtSalseLine.setAreaCode(area.getAreaCode());
            wmRtSalseLine.setAreaName(area.getAreaName());
        }
        return toAjax(wmRtSalseLineService.updateWmRtSalseLine(wmRtSalseLine));
    }

    /**
     * 删除产品销售退货行
     */
    @ApiOperation("删除销售退货单明细接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:rtsalse:remove')")
    @Log(title = "产品销售退货行", businessType = BusinessType.DELETE)
    @DeleteMapping("/{lineIds}")
    public AjaxResult remove(@PathVariable Long[] lineIds)
    {
        return toAjax(wmRtSalseLineService.deleteWmRtSalseLineByLineIds(lineIds));
    }

}
