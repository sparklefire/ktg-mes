package com.ktg.mes.wm.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.wm.mapper.WmProductProduceMapper;
import com.ktg.mes.wm.domain.WmProductProduce;
import com.ktg.mes.wm.service.IWmProductProduceService;

/**
 * 产品产出记录Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-09-21
 */
@Service
public class WmProductProduceServiceImpl implements IWmProductProduceService 
{
    @Autowired
    private WmProductProduceMapper wmProductProduceMapper;

    /**
     * 查询产品产出记录
     * 
     * @param recordId 产品产出记录主键
     * @return 产品产出记录
     */
    @Override
    public WmProductProduce selectWmProductProduceByRecordId(Long recordId)
    {
        return wmProductProduceMapper.selectWmProductProduceByRecordId(recordId);
    }

    /**
     * 查询产品产出记录列表
     * 
     * @param wmProductProduce 产品产出记录
     * @return 产品产出记录
     */
    @Override
    public List<WmProductProduce> selectWmProductProduceList(WmProductProduce wmProductProduce)
    {
        return wmProductProduceMapper.selectWmProductProduceList(wmProductProduce);
    }

    /**
     * 新增产品产出记录
     * 
     * @param wmProductProduce 产品产出记录
     * @return 结果
     */
    @Override
    public int insertWmProductProduce(WmProductProduce wmProductProduce)
    {
        wmProductProduce.setCreateTime(DateUtils.getNowDate());
        return wmProductProduceMapper.insertWmProductProduce(wmProductProduce);
    }

    /**
     * 修改产品产出记录
     * 
     * @param wmProductProduce 产品产出记录
     * @return 结果
     */
    @Override
    public int updateWmProductProduce(WmProductProduce wmProductProduce)
    {
        wmProductProduce.setUpdateTime(DateUtils.getNowDate());
        return wmProductProduceMapper.updateWmProductProduce(wmProductProduce);
    }

    /**
     * 批量删除产品产出记录
     * 
     * @param recordIds 需要删除的产品产出记录主键
     * @return 结果
     */
    @Override
    public int deleteWmProductProduceByRecordIds(Long[] recordIds)
    {
        return wmProductProduceMapper.deleteWmProductProduceByRecordIds(recordIds);
    }

    /**
     * 删除产品产出记录信息
     * 
     * @param recordId 产品产出记录主键
     * @return 结果
     */
    @Override
    public int deleteWmProductProduceByRecordId(Long recordId)
    {
        return wmProductProduceMapper.deleteWmProductProduceByRecordId(recordId);
    }
}
