package com.ktg.print.controller;

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
import com.ktg.print.domain.PrintTemplate;
import com.ktg.print.service.IPrintTemplateService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 打印模板配置Controller
 * 
 * @author yinjinlu
 * @date 2024-04-17
 */
@RestController
@RequestMapping("/print/template")
public class PrintTemplateController extends BaseController
{
    @Autowired
    private IPrintTemplateService printTemplateService;

    /**
     * 查询打印模板配置列表
     */
    @PreAuthorize("@ss.hasPermi('print:template:list')")
    @GetMapping("/list")
    public TableDataInfo list(PrintTemplate printTemplate)
    {
        startPage();
        List<PrintTemplate> list = printTemplateService.selectPrintTemplateList(printTemplate);
        return getDataTable(list);
    }

    /**
     * 导出打印模板配置列表
     */
    @PreAuthorize("@ss.hasPermi('print:template:export')")
    @Log(title = "打印模板配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PrintTemplate printTemplate)
    {
        List<PrintTemplate> list = printTemplateService.selectPrintTemplateList(printTemplate);
        ExcelUtil<PrintTemplate> util = new ExcelUtil<PrintTemplate>(PrintTemplate.class);
        util.exportExcel(response, list, "打印模板配置数据");
    }

    /**
     * 获取打印模板配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('print:template:query')")
    @GetMapping(value = "/{templateId}")
    public AjaxResult getInfo(@PathVariable("templateId") Long templateId)
    {
        return AjaxResult.success(printTemplateService.selectPrintTemplateByTemplateId(templateId));
    }

    /**
     * 新增打印模板配置
     */
    @PreAuthorize("@ss.hasPermi('print:template:add')")
    @Log(title = "打印模板配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PrintTemplate printTemplate)
    {
        printTemplateService.insertPrintTemplate(printTemplate);
        return AjaxResult.success(printTemplate);
    }

    /**
     * 修改打印模板配置
     */
    @PreAuthorize("@ss.hasPermi('print:template:edit')")
    @Log(title = "打印模板配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PrintTemplate printTemplate)
    {
        return toAjax(printTemplateService.updatePrintTemplate(printTemplate));
    }

    /**
     * 删除打印模板配置
     */
    @PreAuthorize("@ss.hasPermi('print:template:remove')")
    @Log(title = "打印模板配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{templateIds}")
    public AjaxResult remove(@PathVariable Long[] templateIds)
    {
        return toAjax(printTemplateService.deletePrintTemplateByTemplateIds(templateIds));
    }
}
