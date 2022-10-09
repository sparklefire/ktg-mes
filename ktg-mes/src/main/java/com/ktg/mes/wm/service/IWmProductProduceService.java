package com.ktg.mes.wm.service;

import java.util.List;

import com.ktg.mes.pro.domain.ProFeedback;
import com.ktg.mes.wm.domain.WmProductProduce;
import com.ktg.mes.wm.domain.tx.ProductProductTxBean;
import com.ktg.mes.wm.domain.tx.ProductRecptTxBean;

/**
 * 产品产出记录Service接口
 * 
 * @author yinjinlu
 * @date 2022-09-21
 */
public interface IWmProductProduceService 
{
    /**
     * 查询产品产出记录
     * 
     * @param recordId 产品产出记录主键
     * @return 产品产出记录
     */
    public WmProductProduce selectWmProductProduceByRecordId(Long recordId);

    /**
     * 查询产品产出记录列表
     * 
     * @param wmProductProduce 产品产出记录
     * @return 产品产出记录集合
     */
    public List<WmProductProduce> selectWmProductProduceList(WmProductProduce wmProductProduce);

    /**
     * 新增产品产出记录
     * 
     * @param wmProductProduce 产品产出记录
     * @return 结果
     */
    public int insertWmProductProduce(WmProductProduce wmProductProduce);

    /**
     * 修改产品产出记录
     * 
     * @param wmProductProduce 产品产出记录
     * @return 结果
     */
    public int updateWmProductProduce(WmProductProduce wmProductProduce);

    /**
     * 批量删除产品产出记录
     * 
     * @param recordIds 需要删除的产品产出记录主键集合
     * @return 结果
     */
    public int deleteWmProductProduceByRecordIds(Long[] recordIds);

    /**
     * 删除产品产出记录信息
     * 
     * @param recordId 产品产出记录主键
     * @return 结果
     */
    public int deleteWmProductProduceByRecordId(Long recordId);

    /**
     * 根据报工记录生成对应的产品产出记录
     * @param feedback
     */
    public WmProductProduce generateProductProduce(ProFeedback feedback);

    public List<ProductProductTxBean> getTxBeans(Long recordId);

}
