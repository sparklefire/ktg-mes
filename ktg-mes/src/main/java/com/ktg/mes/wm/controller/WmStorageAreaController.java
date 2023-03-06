package com.ktg.mes.wm.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ktg.common.constant.UserConstants;
import com.ktg.mes.wm.utils.WmBarCodeUtil;
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
import com.ktg.mes.wm.domain.WmStorageArea;
import com.ktg.mes.wm.service.IWmStorageAreaService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 库位设置Controller
 * 
 * @author yinjinlu
 * @date 2022-05-08
 */
@RestController
@RequestMapping("/mes/wm/area")
public class WmStorageAreaController extends BaseController
{
    @Autowired
    private IWmStorageAreaService wmStorageAreaService;

    @Autowired
    private WmBarCodeUtil barCodeUtil;

    /**
     * 查询库位设置列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:area:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmStorageArea wmStorageArea)
    {
        startPage();
        List<WmStorageArea> list = wmStorageAreaService.selectWmStorageAreaList(wmStorageArea);
        return getDataTable(list);
    }

    /**
     * 导出库位设置列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:area:export')")
    @Log(title = "库位设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmStorageArea wmStorageArea)
    {
        List<WmStorageArea> list = wmStorageAreaService.selectWmStorageAreaList(wmStorageArea);
        ExcelUtil<WmStorageArea> util = new ExcelUtil<WmStorageArea>(WmStorageArea.class);
        util.exportExcel(response, list, "库位设置数据");
    }

    /**
     * 获取库位设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:area:query')")
    @GetMapping(value = "/{areaId}")
    public AjaxResult getInfo(@PathVariable("areaId") Long areaId)
    {
        return AjaxResult.success(wmStorageAreaService.selectWmStorageAreaByAreaId(areaId));
    }

    /**
     * 新增库位设置
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:area:add')")
    @Log(title = "库位设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmStorageArea wmStorageArea)
    {
        wmStorageAreaService.insertWmStorageArea(wmStorageArea);
        barCodeUtil.generateBarCode(UserConstants.BARCODE_TYPE_STORAGEAREA,wmStorageArea.getAreaId(),wmStorageArea.getAreaCode(),wmStorageArea.getAreaName());
        return AjaxResult.success(wmStorageArea.getAreaId());
    }

    /**
     * 修改库位设置
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:area:edit')")
    @Log(title = "库位设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmStorageArea wmStorageArea)
    {
        return toAjax(wmStorageAreaService.updateWmStorageArea(wmStorageArea));
    }

    /**
     * 删除库位设置
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:area:remove')")
    @Log(title = "库位设置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{areaIds}")
    public AjaxResult remove(@PathVariable Long[] areaIds)
    {
        return toAjax(wmStorageAreaService.deleteWmStorageAreaByAreaIds(areaIds));
    }
}
