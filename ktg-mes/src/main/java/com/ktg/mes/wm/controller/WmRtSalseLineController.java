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
import com.ktg.mes.wm.domain.WmRtSalseLine;
import com.ktg.mes.wm.service.IWmRtSalseLineService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 产品销售退货行Controller
 * 
 * @author yinjinlu
 * @date 2022-10-06
 */
@RestController
@RequestMapping("/mes/wm/rtsalseline")
public class WmRtSalseLineController extends BaseController
{
    @Autowired
    private IWmRtSalseLineService wmRtSalseLineService;

    @Autowired
    private IWmWarehouseService wmWarehouseService;

    @Autowired
    private IWmStorageLocationService wmStorageLocationService;

    @Autowired
    private IWmStorageAreaService wmStorageAreaService;

    /**
     * 查询产品销售退货行列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtsalse:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmRtSalseLine wmRtSalseLine)
    {
        startPage();
        List<WmRtSalseLine> list = wmRtSalseLineService.selectWmRtSalseLineList(wmRtSalseLine);
        return getDataTable(list);
    }

    /**
     * 导出产品销售退货行列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtsalse:export')")
    @Log(title = "产品销售退货行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmRtSalseLine wmRtSalseLine)
    {
        List<WmRtSalseLine> list = wmRtSalseLineService.selectWmRtSalseLineList(wmRtSalseLine);
        ExcelUtil<WmRtSalseLine> util = new ExcelUtil<WmRtSalseLine>(WmRtSalseLine.class);
        util.exportExcel(response, list, "产品销售退货行数据");
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
    @PreAuthorize("@ss.hasPermi('mes:wm:rtsalse:add')")
    @Log(title = "产品销售退货行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmRtSalseLine wmRtSalseLine)
    {
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
    @PreAuthorize("@ss.hasPermi('mes:wm:rtsalse:edit')")
    @Log(title = "产品销售退货行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmRtSalseLine wmRtSalseLine)
    {
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
    @PreAuthorize("@ss.hasPermi('mes:wm:rtsalse:remove')")
    @Log(title = "产品销售退货行", businessType = BusinessType.DELETE)
	@DeleteMapping("/{lineIds}")
    public AjaxResult remove(@PathVariable Long[] lineIds)
    {
        return toAjax(wmRtSalseLineService.deleteWmRtSalseLineByLineIds(lineIds));
    }
}
