package com.ktg.mes.qc.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import com.ktg.common.constant.UserConstants;
import com.ktg.mes.qc.domain.*;
import com.ktg.mes.qc.service.*;
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

    @Autowired
    private IQcTemplateIndexService qcTemplateIndexService;

    @Autowired
    private IQcIqcLineService qcIqcLineService;

    @Autowired
    private IQcDefectRecordService qcDefectRecordService;

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
    @Transactional
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
        qcIqc.setInspector(getUsername());
        qcIqcService.insertQcIqc(qcIqc);
        generateLine(qcIqc);
        Long iqcId = qcIqc.getIqcId();
        return AjaxResult.success(iqcId); //将生成的ID返回给页面
    }

    /**
     * 修改来料检验单
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:iqc:edit')")
    @Log(title = "来料检验单", businessType = BusinessType.UPDATE)
    @Transactional
    @PutMapping
    public AjaxResult edit(@RequestBody QcIqc qcIqc)
    {
        if(UserConstants.NOT_UNIQUE.equals(qcIqcService.checkIqcCodeUnique(qcIqc))){
            return AjaxResult.error("单据编号已存在！");
        }

        return toAjax(qcIqcService.updateQcIqc(qcIqc));
    }

    /**
     * 删除来料检验单
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:iqc:remove')")
    @Log(title = "来料检验单", businessType = BusinessType.DELETE)
    @Transactional
	@DeleteMapping("/{iqcIds}")
    public AjaxResult remove(@PathVariable Long[] iqcIds)
    {
        for (Long iqcId:iqcIds
             ) {
            QcIqc iqc = qcIqcService.selectQcIqcByIqcId(iqcId);
            if(!UserConstants.ORDER_STATUS_PREPARE.equals(iqc.getStatus())){
                return AjaxResult.error("只能删除草稿状态单据！");
            }
            qcIqcLineService.deleteByIqcId(iqcId);
            QcDefectRecord p2 = new QcDefectRecord();
            p2.setQcId(iqcId);
            p2.setQcType(UserConstants.QC_TYPE_IQC);
            qcDefectRecordService.deleteByQcIdAndType(p2);//删除对应的缺陷记录
        }
        return toAjax(qcIqcService.deleteQcIqcByIqcIds(iqcIds));
    }

    /**
     * 根据头信息生成行信息
     * @param iqc
     */
    private void generateLine(QcIqc iqc){
        QcTemplateIndex param = new QcTemplateIndex();
        param.setTemplateId(iqc.getTemplateId());
        List<QcTemplateIndex > indexs = qcTemplateIndexService.selectQcTemplateIndexList(param);
        if(CollUtil.isNotEmpty(indexs)){
            for (QcTemplateIndex index:indexs
                 ) {
                QcIqcLine line = new QcIqcLine();
                line.setIqcId(iqc.getIqcId());
                line.setIndexId(index.getIndexId());
                line.setIndexCode(index.getIndexCode());
                line.setIndexName(index.getIndexName());
                line.setIndexType(index.getIndexType());
                line.setQcTool(index.getQcTool());
                line.setCheckMethod(index.getCheckMethod());
                line.setStanderVal(index.getStanderVal());
                line.setUnitOfMeasure(index.getUnitOfMeasure());
                line.setThresholdMax(index.getThresholdMax());
                line.setThresholdMin(index.getThresholdMin());
                line.setCrQuantity(0L);
                line.setMajQuantity(0L);
                line.setMajQuantity(0L);
                qcIqcLineService.insertQcIqcLine(line);
            }
        }
    }

}
