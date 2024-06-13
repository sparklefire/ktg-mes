package com.ktg.print.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ktg.print.domain.PrintPrinterConfig;
import com.ktg.print.service.IPrintPrinterConfigService;
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

import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 打印机配置Controller
 * 
 * @author yinjinlu
 * @date 2023-09-01
 */
@RestController
@RequestMapping("/print/printerconfig")
public class PrintPrinterConfigController extends BaseController
{
    @Autowired
    private IPrintPrinterConfigService printPrinterConfigService;

    /**
     * 查询打印机配置列表
     */
    @PreAuthorize("@ss.hasPermi('print:printerconfig:list')")
    @GetMapping("/list")
    public TableDataInfo list(PrintPrinterConfig printPrinterConfig)
    {
        startPage();
        List<PrintPrinterConfig> list = printPrinterConfigService.selectPrintPrinterConfigList(printPrinterConfig);
        return getDataTable(list);
    }

    /**
     * 导出打印机配置列表
     */
    @PreAuthorize("@ss.hasPermi('print:printerconfig:export')")
    @Log(title = "打印机配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PrintPrinterConfig printPrinterConfig)
    {
        List<PrintPrinterConfig> list = printPrinterConfigService.selectPrintPrinterConfigList(printPrinterConfig);
        ExcelUtil<PrintPrinterConfig> util = new ExcelUtil<PrintPrinterConfig>(PrintPrinterConfig.class);
        util.exportExcel(response, list, "打印机配置数据");
    }

    /**
     * 获取打印机配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('print:printerconfig:query')")
    @GetMapping(value = "/{printerId}")
    public AjaxResult getInfo(@PathVariable("printerId") Long printerId)
    {
        return AjaxResult.success(printPrinterConfigService.selectPrintPrinterConfigByPrinterId(printerId));
    }

    /**
     * 新增打印机配置
     */
    @PreAuthorize("@ss.hasPermi('print:printerconfig:add')")
    @Log(title = "打印机配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PrintPrinterConfig printPrinterConfig)
    {
        return toAjax(printPrinterConfigService.insertPrintPrinterConfig(printPrinterConfig));
    }

    /**
     * 修改打印机配置
     */
    @PreAuthorize("@ss.hasPermi('print:printerconfig:edit')")
    @Log(title = "打印机配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PrintPrinterConfig printPrinterConfig)
    {
        return toAjax(printPrinterConfigService.updatePrintPrinterConfig(printPrinterConfig));
    }

    /**
     * 删除打印机配置
     */
    @PreAuthorize("@ss.hasPermi('print:printerconfig:remove')")
    @Log(title = "打印机配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{printerIds}")
    public AjaxResult remove(@PathVariable Long[] printerIds)
    {
        return toAjax(printPrinterConfigService.deletePrintPrinterConfigByPrinterIds(printerIds));
    }
}
