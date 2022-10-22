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
import com.ktg.mes.wm.domain.WmBarcodeConfig;
import com.ktg.mes.wm.service.IWmBarcodeConfigService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 条码配置Controller
 * 
 * @author yinjinlu
 * @date 2022-10-22
 */
@RestController
@RequestMapping("/mes/wm/barcodeconfig")
public class WmBarcodeConfigController extends BaseController
{
    @Autowired
    private IWmBarcodeConfigService wmBarcodeConfigService;

    /**
     * 查询条码配置列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:barcodeconfig:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmBarcodeConfig wmBarcodeConfig)
    {
        startPage();
        List<WmBarcodeConfig> list = wmBarcodeConfigService.selectWmBarcodeConfigList(wmBarcodeConfig);
        return getDataTable(list);
    }

    /**
     * 导出条码配置列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:barcodeconfig:export')")
    @Log(title = "条码配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmBarcodeConfig wmBarcodeConfig)
    {
        List<WmBarcodeConfig> list = wmBarcodeConfigService.selectWmBarcodeConfigList(wmBarcodeConfig);
        ExcelUtil<WmBarcodeConfig> util = new ExcelUtil<WmBarcodeConfig>(WmBarcodeConfig.class);
        util.exportExcel(response, list, "条码配置数据");
    }

    /**
     * 获取条码配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:barcodeconfig:query')")
    @GetMapping(value = "/{configId}")
    public AjaxResult getInfo(@PathVariable("configId") Long configId)
    {
        return AjaxResult.success(wmBarcodeConfigService.selectWmBarcodeConfigByConfigId(configId));
    }

    /**
     * 新增条码配置
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:barcodeconfig:add')")
    @Log(title = "条码配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmBarcodeConfig wmBarcodeConfig)
    {
        return toAjax(wmBarcodeConfigService.insertWmBarcodeConfig(wmBarcodeConfig));
    }

    /**
     * 修改条码配置
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:barcodeconfig:edit')")
    @Log(title = "条码配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmBarcodeConfig wmBarcodeConfig)
    {
        return toAjax(wmBarcodeConfigService.updateWmBarcodeConfig(wmBarcodeConfig));
    }

    /**
     * 删除条码配置
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:barcodeconfig:remove')")
    @Log(title = "条码配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{configIds}")
    public AjaxResult remove(@PathVariable Long[] configIds)
    {
        return toAjax(wmBarcodeConfigService.deleteWmBarcodeConfigByConfigIds(configIds));
    }
}
