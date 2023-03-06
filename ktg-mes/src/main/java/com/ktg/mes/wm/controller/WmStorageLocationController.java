package com.ktg.mes.wm.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ktg.common.constant.UserConstants;
import com.ktg.mes.wm.service.IWmStorageAreaService;
import com.ktg.mes.wm.utils.WmBarCodeUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
import com.ktg.mes.wm.domain.WmStorageLocation;
import com.ktg.mes.wm.service.IWmStorageLocationService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 库区设置Controller
 * 
 * @author yinjinlu
 * @date 2022-05-07
 */
@RestController
@RequestMapping("/mes/wm/location")
public class WmStorageLocationController extends BaseController
{
    @Autowired
    private IWmStorageLocationService wmStorageLocationService;

    @Autowired
    private IWmStorageAreaService wmStorageAreaService;

    @Autowired
    private WmBarCodeUtil wmBarCodeUtil;

    /**
     * 查询库区设置列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:location:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmStorageLocation wmStorageLocation)
    {
        startPage();
        List<WmStorageLocation> list = wmStorageLocationService.selectWmStorageLocationList(wmStorageLocation);
        return getDataTable(list);
    }

    /**
     * 导出库区设置列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:location:export')")
    @Log(title = "库区设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmStorageLocation wmStorageLocation)
    {
        List<WmStorageLocation> list = wmStorageLocationService.selectWmStorageLocationList(wmStorageLocation);
        ExcelUtil<WmStorageLocation> util = new ExcelUtil<WmStorageLocation>(WmStorageLocation.class);
        util.exportExcel(response, list, "库区设置数据");
    }

    /**
     * 获取库区设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:location:query')")
    @GetMapping(value = "/{locationId}")
    public AjaxResult getInfo(@PathVariable("locationId") Long locationId)
    {
        return AjaxResult.success(wmStorageLocationService.selectWmStorageLocationByLocationId(locationId));
    }

    /**
     * 新增库区设置
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:location:add')")
    @Log(title = "库区设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmStorageLocation wmStorageLocation)
    {
        if(UserConstants.NOT_UNIQUE.equals(wmStorageLocationService.checkLocationCodeUnique(wmStorageLocation))){
            return AjaxResult.error("库区编码已存在!");
        }
        if(UserConstants.NOT_UNIQUE.equals(wmStorageLocationService.checkLocationNameUnique(wmStorageLocation))){
            return AjaxResult.error("库区名称已存在!");
        }
        wmStorageLocationService.insertWmStorageLocation(wmStorageLocation);
        wmBarCodeUtil.generateBarCode(UserConstants.BARCODE_TYPE_WAREHOUSE,wmStorageLocation.getLocationId(),wmStorageLocation.getLocationCode(),wmStorageLocation.getLocationName());
        return AjaxResult.success(wmStorageLocation.getLocationId());
    }

    /**
     * 修改库区设置
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:location:edit')")
    @Log(title = "库区设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmStorageLocation wmStorageLocation)
    {
        return toAjax(wmStorageLocationService.updateWmStorageLocation(wmStorageLocation));
    }

    /**
     * 删除库区设置
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:location:remove')")
    @Log(title = "库区设置", businessType = BusinessType.DELETE)
    @Transactional
	@DeleteMapping("/{locationIds}")
    public AjaxResult remove(@PathVariable Long[] locationIds)
    {
        for (Long locationId: locationIds
             ) {
            wmStorageAreaService.deleteByLocationId(locationId);
        }
        return toAjax(wmStorageLocationService.deleteWmStorageLocationByLocationIds(locationIds));
    }
}
