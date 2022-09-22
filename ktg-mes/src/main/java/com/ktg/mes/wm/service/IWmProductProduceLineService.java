package com.ktg.mes.wm.service;

import java.util.List;
import com.ktg.mes.wm.domain.WmProductProduceLine;

/**
 * 产品产出记录行Service接口
 * 
 * @author yinjinlu
 * @date 2022-09-22
 */
public interface IWmProductProduceLineService 
{
    /**
     * 查询产品产出记录行
     * 
     * @param lineId 产品产出记录行主键
     * @return 产品产出记录行
     */
    public WmProductProduceLine selectWmProductProduceLineByLineId(Long lineId);

    /**
     * 查询产品产出记录行列表
     * 
     * @param wmProductProduceLine 产品产出记录行
     * @return 产品产出记录行集合
     */
    public List<WmProductProduceLine> selectWmProductProduceLineList(WmProductProduceLine wmProductProduceLine);

    /**
     * 新增产品产出记录行
     * 
     * @param wmProductProduceLine 产品产出记录行
     * @return 结果
     */
    public int insertWmProductProduceLine(WmProductProduceLine wmProductProduceLine);

    /**
     * 修改产品产出记录行
     * 
     * @param wmProductProduceLine 产品产出记录行
     * @return 结果
     */
    public int updateWmProductProduceLine(WmProductProduceLine wmProductProduceLine);

    /**
     * 批量删除产品产出记录行
     * 
     * @param lineIds 需要删除的产品产出记录行主键集合
     * @return 结果
     */
    public int deleteWmProductProduceLineByLineIds(Long[] lineIds);

    /**
     * 删除产品产出记录行信息
     * 
     * @param lineId 产品产出记录行主键
     * @return 结果
     */
    public int deleteWmProductProduceLineByLineId(Long lineId);

    /**
     *
     * @param recordId
     * @return
     */
    public int deleteByRecordId(Long recordId);
}
