package com.ktg.mes.wm.controller.mobile;

import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.core.page.TableDataInfo;
import com.ktg.mes.wm.domain.WmMaterialStock;
import com.ktg.mes.wm.service.IWmMaterialStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("库存现有量查询")
@RestController
@RequestMapping("/mobile/wm/wmstock")
public class WmMaterialStockMobController extends BaseController {
    @Autowired
    private IWmMaterialStockService wmMaterialStockService;

    /**
     * 查询库存记录列表
     */
    @ApiOperation("查询库存现有量")
    @PreAuthorize("@ss.hasPermi('mes:wm:wmstock:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmMaterialStock wmMaterialStock)
    {
        startPage();
        List<WmMaterialStock> list = wmMaterialStockService.selectWmMaterialStockList(wmMaterialStock);
        return getDataTable(list);
    }

    /**
     * 获取库存记录详细信息
     */
    @ApiOperation("查询库存现有量明细")
    @PreAuthorize("@ss.hasPermi('mes:wm:wmstock:query')")
    @GetMapping(value = "/{materialStockId}")
    public AjaxResult getInfo(@PathVariable("materialStockId") Long materialStockId)
    {
        return AjaxResult.success(wmMaterialStockService.selectWmMaterialStockByMaterialStockId(materialStockId));
    }
}
