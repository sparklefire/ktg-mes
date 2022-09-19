package com.ktg.mes.wm.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.wm.mapper.WmItemConsumeLineMapper;
import com.ktg.mes.wm.domain.WmItemConsumeLine;
import com.ktg.mes.wm.service.IWmItemConsumeLineService;

/**
 * 物料消耗记录行Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-09-19
 */
@Service
public class WmItemConsumeLineServiceImpl implements IWmItemConsumeLineService 
{
    @Autowired
    private WmItemConsumeLineMapper wmItemConsumeLineMapper;

    /**
     * 查询物料消耗记录行
     * 
     * @param lineId 物料消耗记录行主键
     * @return 物料消耗记录行
     */
    @Override
    public WmItemConsumeLine selectWmItemConsumeLineByLineId(Long lineId)
    {
        return wmItemConsumeLineMapper.selectWmItemConsumeLineByLineId(lineId);
    }

    /**
     * 查询物料消耗记录行列表
     * 
     * @param wmItemConsumeLine 物料消耗记录行
     * @return 物料消耗记录行
     */
    @Override
    public List<WmItemConsumeLine> selectWmItemConsumeLineList(WmItemConsumeLine wmItemConsumeLine)
    {
        return wmItemConsumeLineMapper.selectWmItemConsumeLineList(wmItemConsumeLine);
    }

    /**
     * 新增物料消耗记录行
     * 
     * @param wmItemConsumeLine 物料消耗记录行
     * @return 结果
     */
    @Override
    public int insertWmItemConsumeLine(WmItemConsumeLine wmItemConsumeLine)
    {
        wmItemConsumeLine.setCreateTime(DateUtils.getNowDate());
        return wmItemConsumeLineMapper.insertWmItemConsumeLine(wmItemConsumeLine);
    }

    /**
     * 修改物料消耗记录行
     * 
     * @param wmItemConsumeLine 物料消耗记录行
     * @return 结果
     */
    @Override
    public int updateWmItemConsumeLine(WmItemConsumeLine wmItemConsumeLine)
    {
        wmItemConsumeLine.setUpdateTime(DateUtils.getNowDate());
        return wmItemConsumeLineMapper.updateWmItemConsumeLine(wmItemConsumeLine);
    }

    /**
     * 批量删除物料消耗记录行
     * 
     * @param lineIds 需要删除的物料消耗记录行主键
     * @return 结果
     */
    @Override
    public int deleteWmItemConsumeLineByLineIds(Long[] lineIds)
    {
        return wmItemConsumeLineMapper.deleteWmItemConsumeLineByLineIds(lineIds);
    }

    /**
     * 删除物料消耗记录行信息
     * 
     * @param lineId 物料消耗记录行主键
     * @return 结果
     */
    @Override
    public int deleteWmItemConsumeLineByLineId(Long lineId)
    {
        return wmItemConsumeLineMapper.deleteWmItemConsumeLineByLineId(lineId);
    }
}
