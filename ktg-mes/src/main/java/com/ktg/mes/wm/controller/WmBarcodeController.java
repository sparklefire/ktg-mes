package com.ktg.mes.wm.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ktg.mes.wm.domain.WmBarcode;
import com.ktg.mes.wm.service.IWmBarcodeService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 条码清单Controller
 * 
 * @author yinjinlu
 * @date 2022-08-01
 */
@RestController
@RequestMapping("/mes/wm/barcode")
public class WmBarcodeController extends BaseController
{
    @Autowired
    private IWmBarcodeService wmBarcodeService;

    /**
     * 查询条码清单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:barcode:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmBarcode wmBarcode)
    {
        startPage();
        List<WmBarcode> list = wmBarcodeService.selectWmBarcodeList(wmBarcode);
        return getDataTable(list);
    }

    /**
     * 导出条码清单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:barcode:export')")
    @Log(title = "条码清单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmBarcode wmBarcode)
    {
        List<WmBarcode> list = wmBarcodeService.selectWmBarcodeList(wmBarcode);
        ExcelUtil<WmBarcode> util = new ExcelUtil<WmBarcode>(WmBarcode.class);
        util.exportExcel(response, list, "条码清单数据");
    }

    /**
     * 获取条码清单详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:barcode:query')")
    @GetMapping(value = "/{barcodeId}")
    public AjaxResult getInfo(@PathVariable("barcodeId") Long barcodeId)
    {
        return AjaxResult.success(wmBarcodeService.selectWmBarcodeByBarcodeId(barcodeId));
    }

    /**
     * 新增条码清单
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:barcode:add')")
    @Log(title = "条码清单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmBarcode wmBarcode)
    {
        return toAjax(wmBarcodeService.insertWmBarcode(wmBarcode));
    }

    /**
     * 修改条码清单
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:barcode:edit')")
    @Log(title = "条码清单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmBarcode wmBarcode)
    {
        return toAjax(wmBarcodeService.updateWmBarcode(wmBarcode));
    }

    /**
     * 删除条码清单
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:barcode:remove')")
    @Log(title = "条码清单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{barcodeIds}")
    public AjaxResult remove(@PathVariable Long[] barcodeIds)
    {
        return toAjax(wmBarcodeService.deleteWmBarcodeByBarcodeIds(barcodeIds));
    }
}
