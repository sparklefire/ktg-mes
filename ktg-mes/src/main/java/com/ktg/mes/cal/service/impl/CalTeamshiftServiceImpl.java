package com.ktg.mes.cal.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.cal.mapper.CalTeamshiftMapper;
import com.ktg.mes.cal.domain.CalTeamshift;
import com.ktg.mes.cal.service.ICalTeamshiftService;

/**
 * 班组排班Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-06-11
 */
@Service
public class CalTeamshiftServiceImpl implements ICalTeamshiftService 
{
    @Autowired
    private CalTeamshiftMapper calTeamshiftMapper;

    /**
     * 查询班组排班
     * 
     * @param recordId 班组排班主键
     * @return 班组排班
     */
    @Override
    public CalTeamshift selectCalTeamshiftByRecordId(Long recordId)
    {
        return calTeamshiftMapper.selectCalTeamshiftByRecordId(recordId);
    }

    /**
     * 查询班组排班列表
     * 
     * @param calTeamshift 班组排班
     * @return 班组排班
     */
    @Override
    public List<CalTeamshift> selectCalTeamshiftList(CalTeamshift calTeamshift)
    {
        return calTeamshiftMapper.selectCalTeamshiftList(calTeamshift);
    }

    /**
     * 新增班组排班
     * 
     * @param calTeamshift 班组排班
     * @return 结果
     */
    @Override
    public int insertCalTeamshift(CalTeamshift calTeamshift)
    {
        calTeamshift.setCreateTime(DateUtils.getNowDate());
        return calTeamshiftMapper.insertCalTeamshift(calTeamshift);
    }

    /**
     * 修改班组排班
     * 
     * @param calTeamshift 班组排班
     * @return 结果
     */
    @Override
    public int updateCalTeamshift(CalTeamshift calTeamshift)
    {
        calTeamshift.setUpdateTime(DateUtils.getNowDate());
        return calTeamshiftMapper.updateCalTeamshift(calTeamshift);
    }

    /**
     * 批量删除班组排班
     * 
     * @param recordIds 需要删除的班组排班主键
     * @return 结果
     */
    @Override
    public int deleteCalTeamshiftByRecordIds(Long[] recordIds)
    {
        return calTeamshiftMapper.deleteCalTeamshiftByRecordIds(recordIds);
    }

    /**
     * 删除班组排班信息
     * 
     * @param recordId 班组排班主键
     * @return 结果
     */
    @Override
    public int deleteCalTeamshiftByRecordId(Long recordId)
    {
        return calTeamshiftMapper.deleteCalTeamshiftByRecordId(recordId);
    }
}
