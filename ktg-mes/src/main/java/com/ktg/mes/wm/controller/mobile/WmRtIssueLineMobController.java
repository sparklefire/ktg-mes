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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api("生产退料")
@RestController
@RequestMapping("/mobile/wm/rtissueline")
public class WmRtIssueLineMobController extends BaseController {

    @Autowired
    private IWmRtIssueLineService wmRtIssueLineService;

    @Autowired
    private IWmWarehouseService wmWarehouseService;

    @Autowired
    private IWmStorageLocationService wmStorageLocationService;

    @Autowired
    private IWmStorageAreaService wmStorageAreaService;

    @Autowired
    private IWmMaterialStockService wmMaterialStockService;

    /**
     * 查询生产退料单行列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtissue:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmRtIssueLine wmRtIssueLine)
    {
        startPage();
        List<WmRtIssueLine> list = wmRtIssueLineService.selectWmRtIssueLineList(wmRtIssueLine);
        return getDataTable(list);
    }


    /**
     * 获取生产退料单行详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtissue:query')")
    @GetMapping(value = "/{lineId}")
    public AjaxResult getInfo(@PathVariable("lineId") Long lineId)
    {
        return AjaxResult.success(wmRtIssueLineService.selectWmRtIssueLineByLineId(lineId));
    }

    /**
     * 新增生产退料单行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtissue:add')")
    @Log(title = "生产退料单行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmRtIssueLine wmRtIssueLine)
    {
        if(StringUtils.isNotNull(wmRtIssueLine.getMaterialStockId())){
            WmMaterialStock stock = wmMaterialStockService.selectWmMaterialStockByMaterialStockId(wmRtIssueLine.getMaterialStockId());
            wmRtIssueLine.setItemId(stock.getItemId());
            wmRtIssueLine.setItemCode(stock.getItemCode());
            wmRtIssueLine.setItemName(stock.getItemName());
            wmRtIssueLine.setSpecification(stock.getSpecification());
            wmRtIssueLine.setUnitOfMeasure(stock.getUnitOfMeasure());
            wmRtIssueLine.setBatchCode(stock.getBatchCode());
        }

        if(StringUtils.isNotNull(wmRtIssueLine.getWarehouseId())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseId(wmRtIssueLine.getWarehouseId());
            wmRtIssueLine.setWarehouseCode(warehouse.getWarehouseCode());
            wmRtIssueLine.setWarehouseName(warehouse.getWarehouseName());
        }
        if(StringUtils.isNotNull(wmRtIssueLine.getLocationId())){
            WmStorageLocation location = wmStorageLocationService.selectWmStorageLocationByLocationId(wmRtIssueLine.getLocationId());
            wmRtIssueLine.setLocationCode(location.getLocationCode());
            wmRtIssueLine.setLocationName(location.getLocationName());
        }
        if(StringUtils.isNotNull(wmRtIssueLine.getAreaId())){
            WmStorageArea area = wmStorageAreaService.selectWmStorageAreaByAreaId(wmRtIssueLine.getAreaId());
            wmRtIssueLine.setAreaCode(area.getAreaCode());
            wmRtIssueLine.setAreaName(area.getAreaName());
        }
        wmRtIssueLine.setCreateBy(getUsername());
        wmRtIssueLineService.insertWmRtIssueLine(wmRtIssueLine);
        return AjaxResult.success(wmRtIssueLine);
    }

    /**
     * 修改生产退料单行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtissue:edit')")
    @Log(title = "生产退料单行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmRtIssueLine wmRtIssueLine)
    {
        if(StringUtils.isNotNull(wmRtIssueLine.getMaterialStockId())){
            WmMaterialStock stock = wmMaterialStockService.selectWmMaterialStockByMaterialStockId(wmRtIssueLine.getMaterialStockId());
            wmRtIssueLine.setItemId(stock.getItemId());
            wmRtIssueLine.setItemCode(stock.getItemCode());
            wmRtIssueLine.setItemName(stock.getItemName());
            wmRtIssueLine.setSpecification(stock.getSpecification());
            wmRtIssueLine.setUnitOfMeasure(stock.getUnitOfMeasure());
            wmRtIssueLine.setBatchCode(stock.getBatchCode());
        }

        if(StringUtils.isNotNull(wmRtIssueLine.getWarehouseId())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseId(wmRtIssueLine.getWarehouseId());
            wmRtIssueLine.setWarehouseCode(warehouse.getWarehouseCode());
            wmRtIssueLine.setWarehouseName(warehouse.getWarehouseName());
        }
        if(StringUtils.isNotNull(wmRtIssueLine.getLocationId())){
            WmStorageLocation location = wmStorageLocationService.selectWmStorageLocationByLocationId(wmRtIssueLine.getLocationId());
            wmRtIssueLine.setLocationCode(location.getLocationCode());
            wmRtIssueLine.setLocationName(location.getLocationName());
        }
        if(StringUtils.isNotNull(wmRtIssueLine.getAreaId())){
            WmStorageArea area = wmStorageAreaService.selectWmStorageAreaByAreaId(wmRtIssueLine.getAreaId());
            wmRtIssueLine.setAreaCode(area.getAreaCode());
            wmRtIssueLine.setAreaName(area.getAreaName());
        }
        return toAjax(wmRtIssueLineService.updateWmRtIssueLine(wmRtIssueLine));
    }

    /**
     * 删除生产退料单行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtissue:remove')")
    @Log(title = "生产退料单行", businessType = BusinessType.DELETE)
    @DeleteMapping("/{lineIds}")
    public AjaxResult remove(@PathVariable Long[] lineIds)
    {
        return toAjax(wmRtIssueLineService.deleteWmRtIssueLineByLineIds(lineIds));
    }
}
