package com.ktg.mes.report.controller;

import com.ktg.common.annotation.Log;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.core.page.TableDataInfo;
import com.ktg.common.enums.BusinessType;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.mes.report.domain.UreportFileTbl;
import com.ktg.mes.report.service.IUreportFileTblService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 报表管理Controller
 *
 * @author yanshikui
 * @date 2022-10-07
 */
@RestController
@RequestMapping("/ureportM")
public class UreportFileTblController extends BaseController
{
    @Autowired
    private IUreportFileTblService ureportFileTblService;

    /**
     * 查询报表管理列表
     */
    @PreAuthorize("@ss.hasPermi('ureport:list')")
    @GetMapping("/list")
    public TableDataInfo list(UreportFileTbl ureportFileTbl)
    {
        startPage();
        List<UreportFileTbl> list = ureportFileTblService.selectUreportFileTblList(ureportFileTbl);
        return getDataTable(list);
    }

    /**
     * 导出报表管理列表
     */
    @PreAuthorize("@ss.hasPermi('ureport:export')")
    @Log(title = "报表管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UreportFileTbl ureportFileTbl)
    {
        List<UreportFileTbl> list = ureportFileTblService.selectUreportFileTblList(ureportFileTbl);
        ExcelUtil<UreportFileTbl> util = new ExcelUtil<UreportFileTbl>(UreportFileTbl.class);
        util.exportExcel(response, list, "报表管理数据");
    }

    /**
     * 获取报表管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('ureport:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(ureportFileTblService.selectUreportFileTblById(id));
    }

    /**
     * 新增报表管理
     */
    @PreAuthorize("@ss.hasPermi('ureport:add')")
    @Log(title = "报表管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UreportFileTbl ureportFileTbl)
    {
        return toAjax(ureportFileTblService.insertUreportFileTbl(ureportFileTbl));
    }

    /**
     * 修改报表管理
     */
    @PreAuthorize("@ss.hasPermi('ureport:edit')")
    @Log(title = "报表管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UreportFileTbl ureportFileTbl)
    {
        return toAjax(ureportFileTblService.updateUreportFileTbl(ureportFileTbl));
    }

    /**
     * 删除报表管理
     */
    @PreAuthorize("@ss.hasPermi('ureport:remove')")
    @Log(title = "报表管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ureportFileTblService.deleteUreportFileTblByIds(ids));
    }
}
