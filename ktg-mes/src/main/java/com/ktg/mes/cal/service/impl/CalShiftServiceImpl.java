package com.ktg.mes.cal.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.cal.mapper.CalShiftMapper;
import com.ktg.mes.cal.domain.CalShift;
import com.ktg.mes.cal.service.ICalShiftService;

/**
 * 计划班次Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-06-06
 */
@Service
public class CalShiftServiceImpl implements ICalShiftService 
{
    @Autowired
    private CalShiftMapper calShiftMapper;

    /**
     * 查询计划班次
     * 
     * @param shiftId 计划班次主键
     * @return 计划班次
     */
    @Override
    public CalShift selectCalShiftByShiftId(Long shiftId)
    {
        return calShiftMapper.selectCalShiftByShiftId(shiftId);
    }

    /**
     * 查询计划班次列表
     * 
     * @param calShift 计划班次
     * @return 计划班次
     */
    @Override
    public List<CalShift> selectCalShiftList(CalShift calShift)
    {
        return calShiftMapper.selectCalShiftList(calShift);
    }

    /**
     * 新增计划班次
     * 
     * @param calShift 计划班次
     * @return 结果
     */
    @Override
    public int insertCalShift(CalShift calShift)
    {
        calShift.setCreateTime(DateUtils.getNowDate());
        return calShiftMapper.insertCalShift(calShift);
    }

    /**
     * 修改计划班次
     * 
     * @param calShift 计划班次
     * @return 结果
     */
    @Override
    public int updateCalShift(CalShift calShift)
    {
        calShift.setUpdateTime(DateUtils.getNowDate());
        return calShiftMapper.updateCalShift(calShift);
    }

    /**
     * 批量删除计划班次
     * 
     * @param shiftIds 需要删除的计划班次主键
     * @return 结果
     */
    @Override
    public int deleteCalShiftByShiftIds(Long[] shiftIds)
    {
        return calShiftMapper.deleteCalShiftByShiftIds(shiftIds);
    }

    /**
     * 删除计划班次信息
     * 
     * @param shiftId 计划班次主键
     * @return 结果
     */
    @Override
    public int deleteCalShiftByShiftId(Long shiftId)
    {
        return calShiftMapper.deleteCalShiftByShiftId(shiftId);
    }
}
