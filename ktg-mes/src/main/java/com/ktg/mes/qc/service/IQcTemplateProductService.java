package com.ktg.mes.qc.service;

import java.util.List;
import com.ktg.mes.qc.domain.QcTemplateProduct;

/**
 * 检测模板-产品Service接口
 * 
 * @author yinjinlu
 * @date 2022-05-18
 */
public interface IQcTemplateProductService 
{
    /**
     * 查询检测模板-产品
     * 
     * @param recordId 检测模板-产品主键
     * @return 检测模板-产品
     */
    public QcTemplateProduct selectQcTemplateProductByRecordId(Long recordId);

    /**
     * 查询检测模板-产品列表
     * 
     * @param qcTemplateProduct 检测模板-产品
     * @return 检测模板-产品集合
     */
    public List<QcTemplateProduct> selectQcTemplateProductList(QcTemplateProduct qcTemplateProduct);

    public String checkProductUnique(QcTemplateProduct qcTemplateProduct);

    /**
     * 新增检测模板-产品
     * 
     * @param qcTemplateProduct 检测模板-产品
     * @return 结果
     */
    public int insertQcTemplateProduct(QcTemplateProduct qcTemplateProduct);

    /**
     * 修改检测模板-产品
     * 
     * @param qcTemplateProduct 检测模板-产品
     * @return 结果
     */
    public int updateQcTemplateProduct(QcTemplateProduct qcTemplateProduct);

    /**
     * 批量删除检测模板-产品
     * 
     * @param recordIds 需要删除的检测模板-产品主键集合
     * @return 结果
     */
    public int deleteQcTemplateProductByRecordIds(Long[] recordIds);

    /**
     * 删除检测模板-产品信息
     * 
     * @param recordId 检测模板-产品主键
     * @return 结果
     */
    public int deleteQcTemplateProductByRecordId(Long recordId);

    /**
     * 根据检测模板ID删除产品
     * @param templateId
     * @return
     */
    public int deleteByTemplateId(Long templateId);
}
