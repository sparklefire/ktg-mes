package com.ktg.mes.qc.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.qc.mapper.QcTemplateProductMapper;
import com.ktg.mes.qc.domain.QcTemplateProduct;
import com.ktg.mes.qc.service.IQcTemplateProductService;

/**
 * 检测模板-产品Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-18
 */
@Service
public class QcTemplateProductServiceImpl implements IQcTemplateProductService 
{
    @Autowired
    private QcTemplateProductMapper qcTemplateProductMapper;

    /**
     * 查询检测模板-产品
     * 
     * @param recordId 检测模板-产品主键
     * @return 检测模板-产品
     */
    @Override
    public QcTemplateProduct selectQcTemplateProductByRecordId(Long recordId)
    {
        return qcTemplateProductMapper.selectQcTemplateProductByRecordId(recordId);
    }

    /**
     * 查询检测模板-产品列表
     * 
     * @param qcTemplateProduct 检测模板-产品
     * @return 检测模板-产品
     */
    @Override
    public List<QcTemplateProduct> selectQcTemplateProductList(QcTemplateProduct qcTemplateProduct)
    {
        return qcTemplateProductMapper.selectQcTemplateProductList(qcTemplateProduct);
    }

    @Override
    public String checkProductUnique(QcTemplateProduct qcTemplateProduct) {
        QcTemplateProduct product = qcTemplateProductMapper.checkProductUnique(qcTemplateProduct);
        Long recordId = qcTemplateProduct.getRecordId() ==null? -1L:qcTemplateProduct.getRecordId();
        if(StringUtils.isNotNull(product) && product.getRecordId().longValue()!=recordId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增检测模板-产品
     * 
     * @param qcTemplateProduct 检测模板-产品
     * @return 结果
     */
    @Override
    public int insertQcTemplateProduct(QcTemplateProduct qcTemplateProduct)
    {
        qcTemplateProduct.setCreateTime(DateUtils.getNowDate());
        return qcTemplateProductMapper.insertQcTemplateProduct(qcTemplateProduct);
    }

    /**
     * 修改检测模板-产品
     * 
     * @param qcTemplateProduct 检测模板-产品
     * @return 结果
     */
    @Override
    public int updateQcTemplateProduct(QcTemplateProduct qcTemplateProduct)
    {
        qcTemplateProduct.setUpdateTime(DateUtils.getNowDate());
        return qcTemplateProductMapper.updateQcTemplateProduct(qcTemplateProduct);
    }

    /**
     * 批量删除检测模板-产品
     * 
     * @param recordIds 需要删除的检测模板-产品主键
     * @return 结果
     */
    @Override
    public int deleteQcTemplateProductByRecordIds(Long[] recordIds)
    {
        return qcTemplateProductMapper.deleteQcTemplateProductByRecordIds(recordIds);
    }

    /**
     * 删除检测模板-产品信息
     * 
     * @param recordId 检测模板-产品主键
     * @return 结果
     */
    @Override
    public int deleteQcTemplateProductByRecordId(Long recordId)
    {
        return qcTemplateProductMapper.deleteQcTemplateProductByRecordId(recordId);
    }

    @Override
    public int deleteByTemplateId(Long templateId) {
        return qcTemplateProductMapper.deleteByTemplateId(templateId);
    }
}
