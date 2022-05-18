package com.ktg.mes.qc.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ktg.common.constant.UserConstants;
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
import com.ktg.mes.qc.domain.QcTemplateProduct;
import com.ktg.mes.qc.service.IQcTemplateProductService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 检测模板-产品Controller
 * 
 * @author yinjinlu
 * @date 2022-05-18
 */
@RestController
@RequestMapping("/mes/qc/templateproduct")
public class QcTemplateProductController extends BaseController
{
    @Autowired
    private IQcTemplateProductService qcTemplateProductService;

    /**
     * 查询检测模板-产品列表
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:templateproduct:list')")
    @GetMapping("/list")
    public TableDataInfo list(QcTemplateProduct qcTemplateProduct)
    {
        startPage();
        List<QcTemplateProduct> list = qcTemplateProductService.selectQcTemplateProductList(qcTemplateProduct);
        return getDataTable(list);
    }

    /**
     * 导出检测模板-产品列表
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:templateproduct:export')")
    @Log(title = "检测模板-产品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QcTemplateProduct qcTemplateProduct)
    {
        List<QcTemplateProduct> list = qcTemplateProductService.selectQcTemplateProductList(qcTemplateProduct);
        ExcelUtil<QcTemplateProduct> util = new ExcelUtil<QcTemplateProduct>(QcTemplateProduct.class);
        util.exportExcel(response, list, "检测模板-产品数据");
    }

    /**
     * 获取检测模板-产品详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:templateproduct:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return AjaxResult.success(qcTemplateProductService.selectQcTemplateProductByRecordId(recordId));
    }

    /**
     * 新增检测模板-产品
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:templateproduct:add')")
    @Log(title = "检测模板-产品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QcTemplateProduct qcTemplateProduct)
    {
        if(UserConstants.NOT_UNIQUE.equals(qcTemplateProductService.checkProductUnique(qcTemplateProduct))){
            return AjaxResult.error("产品已设置检测模板！");
        }
        return toAjax(qcTemplateProductService.insertQcTemplateProduct(qcTemplateProduct));
    }

    /**
     * 修改检测模板-产品
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:templateproduct:edit')")
    @Log(title = "检测模板-产品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QcTemplateProduct qcTemplateProduct)
    {
        if(UserConstants.NOT_UNIQUE.equals(qcTemplateProductService.checkProductUnique(qcTemplateProduct))){
            return AjaxResult.error("产品已设置检测模板！");
        }
        return toAjax(qcTemplateProductService.updateQcTemplateProduct(qcTemplateProduct));
    }

    /**
     * 删除检测模板-产品
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:templateproduct:remove')")
    @Log(title = "检测模板-产品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(qcTemplateProductService.deleteQcTemplateProductByRecordIds(recordIds));
    }
}
