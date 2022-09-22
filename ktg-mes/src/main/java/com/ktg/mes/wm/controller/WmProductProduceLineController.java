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
import com.ktg.mes.wm.domain.WmProductProduceLine;
import com.ktg.mes.wm.service.IWmProductProduceLineService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 产品产出记录行Controller
 * 
 * @author yinjinlu
 * @date 2022-09-22
 */
@RestController
@RequestMapping("/mes/wm/productproduceline")
public class WmProductProduceLineController extends BaseController
{
    @Autowired
    private IWmProductProduceLineService wmProductProduceLineService;

    /**
     * 查询产品产出记录行列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:productproduceline:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmProductProduceLine wmProductProduceLine)
    {
        startPage();
        List<WmProductProduceLine> list = wmProductProduceLineService.selectWmProductProduceLineList(wmProductProduceLine);
        return getDataTable(list);
    }

    /**
     * 导出产品产出记录行列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:productproduceline:export')")
    @Log(title = "产品产出记录行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmProductProduceLine wmProductProduceLine)
    {
        List<WmProductProduceLine> list = wmProductProduceLineService.selectWmProductProduceLineList(wmProductProduceLine);
        ExcelUtil<WmProductProduceLine> util = new ExcelUtil<WmProductProduceLine>(WmProductProduceLine.class);
        util.exportExcel(response, list, "产品产出记录行数据");
    }

    /**
     * 获取产品产出记录行详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:productproduceline:query')")
    @GetMapping(value = "/{lineId}")
    public AjaxResult getInfo(@PathVariable("lineId") Long lineId)
    {
        return AjaxResult.success(wmProductProduceLineService.selectWmProductProduceLineByLineId(lineId));
    }

    /**
     * 新增产品产出记录行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:productproduceline:add')")
    @Log(title = "产品产出记录行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmProductProduceLine wmProductProduceLine)
    {
        return toAjax(wmProductProduceLineService.insertWmProductProduceLine(wmProductProduceLine));
    }

    /**
     * 修改产品产出记录行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:productproduceline:edit')")
    @Log(title = "产品产出记录行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmProductProduceLine wmProductProduceLine)
    {
        return toAjax(wmProductProduceLineService.updateWmProductProduceLine(wmProductProduceLine));
    }

    /**
     * 删除产品产出记录行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:productproduceline:remove')")
    @Log(title = "产品产出记录行", businessType = BusinessType.DELETE)
	@DeleteMapping("/{lineIds}")
    public AjaxResult remove(@PathVariable Long[] lineIds)
    {
        return toAjax(wmProductProduceLineService.deleteWmProductProduceLineByLineIds(lineIds));
    }
}
