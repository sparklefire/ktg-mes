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
import com.ktg.mes.wm.domain.WmTransferLine;
import com.ktg.mes.wm.service.IWmTransferLineService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 转移单行Controller
 * 
 * @author yinjinlu
 * @date 2022-11-28
 */
@RestController
@RequestMapping("/wm/transferline")
public class WmTransferLineController extends BaseController
{
    @Autowired
    private IWmTransferLineService wmTransferLineService;

    /**
     * 查询转移单行列表
     */
    @PreAuthorize("@ss.hasPermi('wm:transferline:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmTransferLine wmTransferLine)
    {
        startPage();
        List<WmTransferLine> list = wmTransferLineService.selectWmTransferLineList(wmTransferLine);
        return getDataTable(list);
    }

    /**
     * 导出转移单行列表
     */
    @PreAuthorize("@ss.hasPermi('wm:transferline:export')")
    @Log(title = "转移单行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmTransferLine wmTransferLine)
    {
        List<WmTransferLine> list = wmTransferLineService.selectWmTransferLineList(wmTransferLine);
        ExcelUtil<WmTransferLine> util = new ExcelUtil<WmTransferLine>(WmTransferLine.class);
        util.exportExcel(response, list, "转移单行数据");
    }

    /**
     * 获取转移单行详细信息
     */
    @PreAuthorize("@ss.hasPermi('wm:transferline:query')")
    @GetMapping(value = "/{lineId}")
    public AjaxResult getInfo(@PathVariable("lineId") Long lineId)
    {
        return AjaxResult.success(wmTransferLineService.selectWmTransferLineByLineId(lineId));
    }

    /**
     * 新增转移单行
     */
    @PreAuthorize("@ss.hasPermi('wm:transferline:add')")
    @Log(title = "转移单行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmTransferLine wmTransferLine)
    {
        return toAjax(wmTransferLineService.insertWmTransferLine(wmTransferLine));
    }

    /**
     * 修改转移单行
     */
    @PreAuthorize("@ss.hasPermi('wm:transferline:edit')")
    @Log(title = "转移单行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmTransferLine wmTransferLine)
    {
        return toAjax(wmTransferLineService.updateWmTransferLine(wmTransferLine));
    }

    /**
     * 删除转移单行
     */
    @PreAuthorize("@ss.hasPermi('wm:transferline:remove')")
    @Log(title = "转移单行", businessType = BusinessType.DELETE)
	@DeleteMapping("/{lineIds}")
    public AjaxResult remove(@PathVariable Long[] lineIds)
    {
        return toAjax(wmTransferLineService.deleteWmTransferLineByLineIds(lineIds));
    }
}
