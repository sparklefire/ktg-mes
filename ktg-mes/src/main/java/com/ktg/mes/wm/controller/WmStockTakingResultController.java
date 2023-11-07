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
import com.ktg.mes.wm.domain.WmStockTakingResult;
import com.ktg.mes.wm.service.IWmStockTakingResultService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 库存盘点结果Controller
 * 
 * @author yinjinlu
 * @date 2023-08-22
 */
@RestController
@RequestMapping("/wm/stocktakingresult")
public class WmStockTakingResultController extends BaseController
{
    @Autowired
    private IWmStockTakingResultService wmStockTakingResultService;

    /**
     * 查询库存盘点结果列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:stocktaking:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmStockTakingResult wmStockTakingResult)
    {
        startPage();
        List<WmStockTakingResult> list = wmStockTakingResultService.selectWmStockTakingResultList(wmStockTakingResult);
        return getDataTable(list);
    }

    /**
     * 导出库存盘点结果列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:stocktaking:export')")
    @Log(title = "库存盘点结果", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmStockTakingResult wmStockTakingResult)
    {
        List<WmStockTakingResult> list = wmStockTakingResultService.selectWmStockTakingResultList(wmStockTakingResult);
        ExcelUtil<WmStockTakingResult> util = new ExcelUtil<WmStockTakingResult>(WmStockTakingResult.class);
        util.exportExcel(response, list, "库存盘点结果数据");
    }

    /**
     * 获取库存盘点结果详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:stocktaking:query')")
    @GetMapping(value = "/{resultId}")
    public AjaxResult getInfo(@PathVariable("resultId") Long resultId)
    {
        return AjaxResult.success(wmStockTakingResultService.selectWmStockTakingResultByResultId(resultId));
    }

    /**
     * 新增库存盘点结果
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:stocktaking:add')")
    @Log(title = "库存盘点结果", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmStockTakingResult wmStockTakingResult)
    {
        return toAjax(wmStockTakingResultService.insertWmStockTakingResult(wmStockTakingResult));
    }

    /**
     * 修改库存盘点结果
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:stocktaking:edit')")
    @Log(title = "库存盘点结果", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmStockTakingResult wmStockTakingResult)
    {
        return toAjax(wmStockTakingResultService.updateWmStockTakingResult(wmStockTakingResult));
    }

    /**
     * 删除库存盘点结果
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:stocktaking:remove')")
    @Log(title = "库存盘点结果", businessType = BusinessType.DELETE)
	@DeleteMapping("/{resultIds}")
    public AjaxResult remove(@PathVariable Long[] resultIds)
    {
        return toAjax(wmStockTakingResultService.deleteWmStockTakingResultByResultIds(resultIds));
    }
}
