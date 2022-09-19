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
import com.ktg.mes.wm.domain.WmItemConsumeLine;
import com.ktg.mes.wm.service.IWmItemConsumeLineService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 物料消耗记录行Controller
 * 
 * @author yinjinlu
 * @date 2022-09-19
 */
@RestController
@RequestMapping("/mes/wm/itemconsumeline")
public class WmItemConsumeLineController extends BaseController
{
    @Autowired
    private IWmItemConsumeLineService wmItemConsumeLineService;

    /**
     * 查询物料消耗记录行列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:itemconsumeline:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmItemConsumeLine wmItemConsumeLine)
    {
        startPage();
        List<WmItemConsumeLine> list = wmItemConsumeLineService.selectWmItemConsumeLineList(wmItemConsumeLine);
        return getDataTable(list);
    }

    /**
     * 导出物料消耗记录行列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:itemconsumeline:export')")
    @Log(title = "物料消耗记录行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmItemConsumeLine wmItemConsumeLine)
    {
        List<WmItemConsumeLine> list = wmItemConsumeLineService.selectWmItemConsumeLineList(wmItemConsumeLine);
        ExcelUtil<WmItemConsumeLine> util = new ExcelUtil<WmItemConsumeLine>(WmItemConsumeLine.class);
        util.exportExcel(response, list, "物料消耗记录行数据");
    }

    /**
     * 获取物料消耗记录行详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:itemconsumeline:query')")
    @GetMapping(value = "/{lineId}")
    public AjaxResult getInfo(@PathVariable("lineId") Long lineId)
    {
        return AjaxResult.success(wmItemConsumeLineService.selectWmItemConsumeLineByLineId(lineId));
    }

    /**
     * 新增物料消耗记录行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:itemconsumeline:add')")
    @Log(title = "物料消耗记录行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmItemConsumeLine wmItemConsumeLine)
    {
        return toAjax(wmItemConsumeLineService.insertWmItemConsumeLine(wmItemConsumeLine));
    }

    /**
     * 修改物料消耗记录行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:itemconsumeline:edit')")
    @Log(title = "物料消耗记录行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmItemConsumeLine wmItemConsumeLine)
    {
        return toAjax(wmItemConsumeLineService.updateWmItemConsumeLine(wmItemConsumeLine));
    }

    /**
     * 删除物料消耗记录行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:itemconsumeline:remove')")
    @Log(title = "物料消耗记录行", businessType = BusinessType.DELETE)
	@DeleteMapping("/{lineIds}")
    public AjaxResult remove(@PathVariable Long[] lineIds)
    {
        return toAjax(wmItemConsumeLineService.deleteWmItemConsumeLineByLineIds(lineIds));
    }
}
