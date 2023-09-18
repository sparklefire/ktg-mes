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
import com.ktg.mes.wm.domain.WmRtIssueLine;
import com.ktg.mes.wm.service.IWmRtIssueLineService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 生产退料单行Controller
 * 
 * @author yinjinlu
 * @date 2022-09-15
 */
@RestController
@RequestMapping("/mes/wm/rtissueline")
public class WmRtIssueLineController extends BaseController
{
    @Autowired
    private IWmRtIssueLineService wmRtIssueLineService;

    @Autowired
    private IWmWarehouseService wmWarehouseService;

    @Autowired
    private IWmStorageLocationService wmStorageLocationService;

    @Autowired
    private IWmStorageAreaService wmStorageAreaService;

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
     * 导出生产退料单行列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtissue:export')")
    @Log(title = "生产退料单行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmRtIssueLine wmRtIssueLine)
    {
        List<WmRtIssueLine> list = wmRtIssueLineService.selectWmRtIssueLineList(wmRtIssueLine);
        ExcelUtil<WmRtIssueLine> util = new ExcelUtil<WmRtIssueLine>(WmRtIssueLine.class);
        util.exportExcel(response, list, "生产退料单行数据");
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
        if(!StringUtils.isNotNull(wmRtIssueLine.getMaterialStockId())){
            return AjaxResult.error("请从库存现有量中选择退料的物资！");
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
        return toAjax(wmRtIssueLineService.insertWmRtIssueLine(wmRtIssueLine));
    }

    /**
     * 修改生产退料单行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtissue:edit')")
    @Log(title = "生产退料单行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmRtIssueLine wmRtIssueLine)
    {
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
