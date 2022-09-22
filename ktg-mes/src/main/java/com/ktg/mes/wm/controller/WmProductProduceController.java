package com.ktg.mes.wm.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ktg.mes.wm.service.IWmProductProduceLineService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
import com.ktg.mes.wm.domain.WmProductProduce;
import com.ktg.mes.wm.service.IWmProductProduceService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 产品产出记录Controller
 * 
 * @author yinjinlu
 * @date 2022-09-21
 */
@RestController
@RequestMapping("/mes/wm/productproduce")
public class WmProductProduceController extends BaseController
{
    @Autowired
    private IWmProductProduceService wmProductProduceService;

    @Autowired
    private IWmProductProduceLineService wmProductProduceLineService;
    /**
     * 查询产品产出记录列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:productproduce:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmProductProduce wmProductProduce)
    {
        startPage();
        List<WmProductProduce> list = wmProductProduceService.selectWmProductProduceList(wmProductProduce);
        return getDataTable(list);
    }

    /**
     * 导出产品产出记录列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:productproduce:export')")
    @Log(title = "产品产出记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmProductProduce wmProductProduce)
    {
        List<WmProductProduce> list = wmProductProduceService.selectWmProductProduceList(wmProductProduce);
        ExcelUtil<WmProductProduce> util = new ExcelUtil<WmProductProduce>(WmProductProduce.class);
        util.exportExcel(response, list, "产品产出记录数据");
    }

    /**
     * 获取产品产出记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:productproduce:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return AjaxResult.success(wmProductProduceService.selectWmProductProduceByRecordId(recordId));
    }

    /**
     * 新增产品产出记录
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:productproduce:add')")
    @Log(title = "产品产出记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmProductProduce wmProductProduce)
    {
        return toAjax(wmProductProduceService.insertWmProductProduce(wmProductProduce));
    }

    /**
     * 修改产品产出记录
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:productproduce:edit')")
    @Log(title = "产品产出记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmProductProduce wmProductProduce)
    {
        return toAjax(wmProductProduceService.updateWmProductProduce(wmProductProduce));
    }

    /**
     * 删除产品产出记录
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:productproduce:remove')")
    @Log(title = "产品产出记录", businessType = BusinessType.DELETE)
    @Transactional
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        for (Long recordId: recordIds
             ) {
            wmProductProduceLineService.deleteByRecordId(recordId);
        }
        return toAjax(wmProductProduceService.deleteWmProductProduceByRecordIds(recordIds));
    }
}
