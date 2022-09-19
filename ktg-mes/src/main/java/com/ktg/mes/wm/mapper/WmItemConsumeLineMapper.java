package com.ktg.mes.wm.mapper;

import java.util.List;
import com.ktg.mes.wm.domain.WmItemConsumeLine;

/**
 * 物料消耗记录行Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-09-19
 */
public interface WmItemConsumeLineMapper 
{
    /**
     * 查询物料消耗记录行
     * 
     * @param lineId 物料消耗记录行主键
     * @return 物料消耗记录行
     */
    public WmItemConsumeLine selectWmItemConsumeLineByLineId(Long lineId);

    /**
     * 查询物料消耗记录行列表
     * 
     * @param wmItemConsumeLine 物料消耗记录行
     * @return 物料消耗记录行集合
     */
    public List<WmItemConsumeLine> selectWmItemConsumeLineList(WmItemConsumeLine wmItemConsumeLine);

    /**
     * 新增物料消耗记录行
     * 
     * @param wmItemConsumeLine 物料消耗记录行
     * @return 结果
     */
    public int insertWmItemConsumeLine(WmItemConsumeLine wmItemConsumeLine);

    /**
     * 修改物料消耗记录行
     * 
     * @param wmItemConsumeLine 物料消耗记录行
     * @return 结果
     */
    public int updateWmItemConsumeLine(WmItemConsumeLine wmItemConsumeLine);

    /**
     * 删除物料消耗记录行
     * 
     * @param lineId 物料消耗记录行主键
     * @return 结果
     */
    public int deleteWmItemConsumeLineByLineId(Long lineId);

    /**
     * 批量删除物料消耗记录行
     * 
     * @param lineIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWmItemConsumeLineByLineIds(Long[] lineIds);
}
