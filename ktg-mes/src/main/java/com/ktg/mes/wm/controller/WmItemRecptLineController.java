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
import com.ktg.mes.wm.domain.WmItemRecptLine;
import com.ktg.mes.wm.service.IWmItemRecptLineService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 物料入库单行Controller
 * 
 * @author yinjinlu
 * @date 2022-05-22
 */
@RestController
@RequestMapping("/mes/wm/itemrecptline")
public class WmItemRecptLineController extends BaseController
{
    @Autowired
    private IWmItemRecptLineService wmItemRecptLineService;

    /**
     * 查询物料入库单行列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:itemrecptline:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmItemRecptLine wmItemRecptLine)
    {
        startPage();
        List<WmItemRecptLine> list = wmItemRecptLineService.selectWmItemRecptLineList(wmItemRecptLine);
        return getDataTable(list);
    }

    /**
     * 导出物料入库单行列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:itemrecptline:export')")
    @Log(title = "物料入库单行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmItemRecptLine wmItemRecptLine)
    {
        List<WmItemRecptLine> list = wmItemRecptLineService.selectWmItemRecptLineList(wmItemRecptLine);
        ExcelUtil<WmItemRecptLine> util = new ExcelUtil<WmItemRecptLine>(WmItemRecptLine.class);
        util.exportExcel(response, list, "物料入库单行数据");
    }

    /**
     * 获取物料入库单行详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:itemrecptline:query')")
    @GetMapping(value = "/{lineId}")
    public AjaxResult getInfo(@PathVariable("lineId") Long lineId)
    {
        return AjaxResult.success(wmItemRecptLineService.selectWmItemRecptLineByLineId(lineId));
    }

    /**
     * 新增物料入库单行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:itemrecptline:add')")
    @Log(title = "物料入库单行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmItemRecptLine wmItemRecptLine)
    {
        return toAjax(wmItemRecptLineService.insertWmItemRecptLine(wmItemRecptLine));
    }

    /**
     * 修改物料入库单行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:itemrecptline:edit')")
    @Log(title = "物料入库单行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmItemRecptLine wmItemRecptLine)
    {
        return toAjax(wmItemRecptLineService.updateWmItemRecptLine(wmItemRecptLine));
    }

    /**
     * 删除物料入库单行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:itemrecptline:remove')")
    @Log(title = "物料入库单行", businessType = BusinessType.DELETE)
	@DeleteMapping("/{lineIds}")
    public AjaxResult remove(@PathVariable Long[] lineIds)
    {
        return toAjax(wmItemRecptLineService.deleteWmItemRecptLineByLineIds(lineIds));
    }
}
