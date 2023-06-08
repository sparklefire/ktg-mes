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
import com.ktg.mes.wm.domain.WmTransferLine;
import com.ktg.mes.wm.service.IWmTransferLineService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 转移单行Controller
 * 
 * @author yinjinlu
 * @date 2022-11-28
 */
@RestController
@RequestMapping("/mes/wm/transferline")
public class WmTransferLineController extends BaseController
{
    @Autowired
    private IWmTransferLineService wmTransferLineService;

    @Autowired
    private IWmWarehouseService wmWarehouseService;

    @Autowired
    private IWmStorageLocationService wmStorageLocationService;

    @Autowired
    private IWmStorageAreaService wmStorageAreaService;
    /**
     * 查询转移单行列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:transferline:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmTransferLine wmTransferLine)
    {
        startPage();
        List<WmTransferLine> list = wmTransferLineService.selectWmTransferLineList(wmTransferLine);
        return getDataTable(list);
    }

    /**
     * 导出转移单行列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:transferline:export')")
    @Log(title = "转移单行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmTransferLine wmTransferLine)
    {
        List<WmTransferLine> list = wmTransferLineService.selectWmTransferLineList(wmTransferLine);
        ExcelUtil<WmTransferLine> util = new ExcelUtil<WmTransferLine>(WmTransferLine.class);
        util.exportExcel(response, list, "转移单行数据");
    }

    /**
     * 获取转移单行详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:transferline:query')")
    @GetMapping(value = "/{lineId}")
    public AjaxResult getInfo(@PathVariable("lineId") Long lineId)
    {
        return AjaxResult.success(wmTransferLineService.selectWmTransferLineByLineId(lineId));
    }

    /**
     * 新增转移单行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:transferline:add')")
    @Log(title = "转移单行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmTransferLine wmTransferLine)
    {
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
        return toAjax(wmTransferLineService.insertWmTransferLine(wmTransferLine));
    }

    /**
     * 修改转移单行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:transferline:edit')")
    @Log(title = "转移单行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmTransferLine wmTransferLine)
    {
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
    @PreAuthorize("@ss.hasPermi('mes:wm:transferline:remove')")
    @Log(title = "转移单行", businessType = BusinessType.DELETE)
	@DeleteMapping("/{lineIds}")
    public AjaxResult remove(@PathVariable Long[] lineIds)
    {
        return toAjax(wmTransferLineService.deleteWmTransferLineByLineIds(lineIds));
    }
}
