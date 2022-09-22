package com.ktg.mes.wm.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.wm.mapper.WmProductProduceLineMapper;
import com.ktg.mes.wm.domain.WmProductProduceLine;
import com.ktg.mes.wm.service.IWmProductProduceLineService;

/**
 * 产品产出记录行Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-09-22
 */
@Service
public class WmProductProduceLineServiceImpl implements IWmProductProduceLineService 
{
    @Autowired
    private WmProductProduceLineMapper wmProductProduceLineMapper;

    /**
     * 查询产品产出记录行
     * 
     * @param lineId 产品产出记录行主键
     * @return 产品产出记录行
     */
    @Override
    public WmProductProduceLine selectWmProductProduceLineByLineId(Long lineId)
    {
        return wmProductProduceLineMapper.selectWmProductProduceLineByLineId(lineId);
    }

    /**
     * 查询产品产出记录行列表
     * 
     * @param wmProductProduceLine 产品产出记录行
     * @return 产品产出记录行
     */
    @Override
    public List<WmProductProduceLine> selectWmProductProduceLineList(WmProductProduceLine wmProductProduceLine)
    {
        return wmProductProduceLineMapper.selectWmProductProduceLineList(wmProductProduceLine);
    }

    /**
     * 新增产品产出记录行
     * 
     * @param wmProductProduceLine 产品产出记录行
     * @return 结果
     */
    @Override
    public int insertWmProductProduceLine(WmProductProduceLine wmProductProduceLine)
    {
        wmProductProduceLine.setCreateTime(DateUtils.getNowDate());
        return wmProductProduceLineMapper.insertWmProductProduceLine(wmProductProduceLine);
    }

    /**
     * 修改产品产出记录行
     * 
     * @param wmProductProduceLine 产品产出记录行
     * @return 结果
     */
    @Override
    public int updateWmProductProduceLine(WmProductProduceLine wmProductProduceLine)
    {
        wmProductProduceLine.setUpdateTime(DateUtils.getNowDate());
        return wmProductProduceLineMapper.updateWmProductProduceLine(wmProductProduceLine);
    }

    /**
     * 批量删除产品产出记录行
     * 
     * @param lineIds 需要删除的产品产出记录行主键
     * @return 结果
     */
    @Override
    public int deleteWmProductProduceLineByLineIds(Long[] lineIds)
    {
        return wmProductProduceLineMapper.deleteWmProductProduceLineByLineIds(lineIds);
    }

    /**
     * 删除产品产出记录行信息
     * 
     * @param lineId 产品产出记录行主键
     * @return 结果
     */
    @Override
    public int deleteWmProductProduceLineByLineId(Long lineId)
    {
        return wmProductProduceLineMapper.deleteWmProductProduceLineByLineId(lineId);
    }

    @Override
    public int deleteByRecordId(Long recordId) {
        return wmProductProduceLineMapper.deleteByRecordId(recordId);
    }
}
