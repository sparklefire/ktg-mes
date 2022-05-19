package com.ktg.mes.qc.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import com.ktg.common.constant.UserConstants;
import com.ktg.mes.qc.domain.QcTemplateProduct;
import com.ktg.mes.qc.service.IQcTemplateProductService;
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
import com.ktg.mes.qc.domain.QcIqc;
import com.ktg.mes.qc.service.IQcIqcService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 来料检验单Controller
 * 
 * @author yinjinlu
 * @date 2022-05-19
 */
@RestController
@RequestMapping("/mes/qc/iqc")
public class QcIqcController extends BaseController
{
    @Autowired
    private IQcIqcService qcIqcService;

    @Autowired
    private IQcTemplateProductService qcTemplateProductService;

    /**
     * 查询来料检验单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:iqc:list')")
    @GetMapping("/list")
    public TableDataInfo list(QcIqc qcIqc)
    {
        startPage();
        List<QcIqc> list = qcIqcService.selectQcIqcList(qcIqc);
        return getDataTable(list);
    }

    /**
     * 导出来料检验单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:iqc:export')")
    @Log(title = "来料检验单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QcIqc qcIqc)
    {
        List<QcIqc> list = qcIqcService.selectQcIqcList(qcIqc);
        ExcelUtil<QcIqc> util = new ExcelUtil<QcIqc>(QcIqc.class);
        util.exportExcel(response, list, "来料检验单数据");
    }

    /**
     * 获取来料检验单详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:iqc:query')")
    @GetMapping(value = "/{iqcId}")
    public AjaxResult getInfo(@PathVariable("iqcId") Long iqcId)
    {
        return AjaxResult.success(qcIqcService.selectQcIqcByIqcId(iqcId));
    }

    /**
     * 新增来料检验单
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:iqc:add')")
    @Log(title = "来料检验单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QcIqc qcIqc)
    {
        if(UserConstants.NOT_UNIQUE.equals(qcIqcService.checkIqcCodeUnique(qcIqc))){
            return AjaxResult.error("单据编号已存在！");
        }

        QcTemplateProduct param = new QcTemplateProduct();
        param.setItemId(qcIqc.getItemId());
        List<QcTemplateProduct> templates = qcTemplateProductService.selectQcTemplateProductList(param);
        if(CollUtil.isNotEmpty(templates)){
            qcIqc.setTemplateId(templates.get(0).getTemplateId());
        }else{
            return AjaxResult.error("当前产品未配置检测模板！");
        }
        return toAjax(qcIqcService.insertQcIqc(qcIqc));
    }

    /**
     * 修改来料检验单
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:iqc:edit')")
    @Log(title = "来料检验单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QcIqc qcIqc)
    {
        if(UserConstants.NOT_UNIQUE.equals(qcIqcService.checkIqcCodeUnique(qcIqc))){
            return AjaxResult.error("单据编号已存在！");
        }
        QcTemplateProduct param = new QcTemplateProduct();
        param.setItemId(qcIqc.getItemId());
        List<QcTemplateProduct> templates = qcTemplateProductService.selectQcTemplateProductList(param);
        if(CollUtil.isNotEmpty(templates)){
            qcIqc.setTemplateId(templates.get(0).getTemplateId());
        }else{
            return AjaxResult.error("当前产品未配置检测模板！");
        }
        return toAjax(qcIqcService.updateQcIqc(qcIqc));
    }

    /**
     * 删除来料检验单
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:iqc:remove')")
    @Log(title = "来料检验单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{iqcIds}")
    public AjaxResult remove(@PathVariable Long[] iqcIds)
    {
        return toAjax(qcIqcService.deleteQcIqcByIqcIds(iqcIds));
    }
}
