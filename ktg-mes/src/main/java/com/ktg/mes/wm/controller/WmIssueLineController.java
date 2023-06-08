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
import com.ktg.mes.wm.domain.WmIssueLine;
import com.ktg.mes.wm.service.IWmIssueLineService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 生产领料单行Controller
 * 
 * @author yinjinlu
 * @date 2022-07-14
 */
@RestController
@RequestMapping("/mes/wm/issueline")
public class WmIssueLineController extends BaseController
{
    @Autowired
    private IWmIssueLineService wmIssueLineService;

    /**
     * 查询生产领料单行列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:issueline:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmIssueLine wmIssueLine)
    {
        startPage();
        List<WmIssueLine> list = wmIssueLineService.selectWmIssueLineList(wmIssueLine);
        return getDataTable(list);
    }

    /**
     * 导出生产领料单行列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:issueline:export')")
    @Log(title = "生产领料单行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmIssueLine wmIssueLine)
    {
        List<WmIssueLine> list = wmIssueLineService.selectWmIssueLineList(wmIssueLine);
        ExcelUtil<WmIssueLine> util = new ExcelUtil<WmIssueLine>(WmIssueLine.class);
        util.exportExcel(response, list, "生产领料单行数据");
    }

    /**
     * 获取生产领料单行详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:issueline:query')")
    @GetMapping(value = "/{lineId}")
    public AjaxResult getInfo(@PathVariable("lineId") Long lineId)
    {
        return AjaxResult.success(wmIssueLineService.selectWmIssueLineByLineId(lineId));
    }

    /**
     * 新增生产领料单行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:issueline:add')")
    @Log(title = "生产领料单行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmIssueLine wmIssueLine)
    {
        wmIssueLine.setCreateBy(getUsername());
        return toAjax(wmIssueLineService.insertWmIssueLine(wmIssueLine));
    }

    /**
     * 修改生产领料单行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:issueline:edit')")
    @Log(title = "生产领料单行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmIssueLine wmIssueLine)
    {
        return toAjax(wmIssueLineService.updateWmIssueLine(wmIssueLine));
    }

    /**
     * 删除生产领料单行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:issueline:remove')")
    @Log(title = "生产领料单行", businessType = BusinessType.DELETE)
	@DeleteMapping("/{lineIds}")
    public AjaxResult remove(@PathVariable Long[] lineIds)
    {
        return toAjax(wmIssueLineService.deleteWmIssueLineByLineIds(lineIds));
    }
}
