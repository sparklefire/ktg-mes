package com.ktg.mes.wm.mapper;

import java.util.List;
import com.ktg.mes.wm.domain.WmProductProduce;
import com.ktg.mes.wm.domain.tx.ProductProductTxBean;

/**
 * 产品产出记录Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-09-21
 */
public interface WmProductProduceMapper 
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
     * 删除产品产出记录
     * 
     * @param recordId 产品产出记录主键
     * @return 结果
     */
    public int deleteWmProductProduceByRecordId(Long recordId);

    /**
     * 批量删除产品产出记录
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWmProductProduceByRecordIds(Long[] recordIds);

    public List<ProductProductTxBean> getTxBeans(Long recordId);
}
