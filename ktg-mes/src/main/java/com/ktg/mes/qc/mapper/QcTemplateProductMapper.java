package com.ktg.mes.qc.mapper;

import java.util.List;
import com.ktg.mes.qc.domain.QcTemplateProduct;

/**
 * 检测模板-产品Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-05-18
 */
public interface QcTemplateProductMapper 
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

    public QcTemplateProduct checkProductUnique(QcTemplateProduct qcTemplateProduct);


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
     * 删除检测模板-产品
     * 
     * @param recordId 检测模板-产品主键
     * @return 结果
     */
    public int deleteQcTemplateProductByRecordId(Long recordId);

    /**
     * 批量删除检测模板-产品
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQcTemplateProductByRecordIds(Long[] recordIds);

    public int deleteByTemplateId(Long templateId);

}
