package com.ktg.mes.wm.controller.mobile;

import com.ktg.common.annotation.Log;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.core.page.TableDataInfo;
import com.ktg.common.enums.BusinessType;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.mes.wm.domain.WmStockTakingResult;
import com.ktg.mes.wm.service.IWmStockTakingResultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api("物资盘点结果")
@RestController
@RequestMapping("/mobile/wm/stocktakingresult")
public class WmStockTakingResultMobController extends BaseController {

    @Autowired
    private IWmStockTakingResultService wmStockTakingResultService;

    /**
     * 查询库存盘点结果列表
     */
    @ApiOperation("查询库存盘点结果列表接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:stocktaking:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmStockTakingResult wmStockTakingResult)
    {
        startPage();
        List<WmStockTakingResult> list = wmStockTakingResultService.selectWmStockTakingResultList(wmStockTakingResult);
        return getDataTable(list);
    }


    /**
     * 获取库存盘点结果详细信息
     */
    @ApiOperation("查询库存盘点结果详情接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:stocktaking:query')")
    @GetMapping(value = "/{resultId}")
    public AjaxResult getInfo(@PathVariable("resultId") Long resultId)
    {
        return AjaxResult.success(wmStockTakingResultService.selectWmStockTakingResultByResultId(resultId));
    }

    /**
     * 新增库存盘点结果
     */
    @ApiOperation("新增库存盘点结果接口")
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
    @ApiOperation("修改库存盘点结果接口")
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
    @ApiOperation("删除库存盘点结果接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:stocktaking:remove')")
    @Log(title = "库存盘点结果", businessType = BusinessType.DELETE)
    @DeleteMapping("/{resultIds}")
    public AjaxResult remove(@PathVariable Long[] resultIds)
    {
        return toAjax(wmStockTakingResultService.deleteWmStockTakingResultByResultIds(resultIds));
    }

}
