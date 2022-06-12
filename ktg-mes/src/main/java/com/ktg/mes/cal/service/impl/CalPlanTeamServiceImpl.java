package com.ktg.mes.cal.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.cal.mapper.CalPlanTeamMapper;
import com.ktg.mes.cal.domain.CalPlanTeam;
import com.ktg.mes.cal.service.ICalPlanTeamService;

/**
 * 计划班组Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-06-07
 */
@Service
public class CalPlanTeamServiceImpl implements ICalPlanTeamService 
{
    @Autowired
    private CalPlanTeamMapper calPlanTeamMapper;

    /**
     * 查询计划班组
     * 
     * @param recordId 计划班组主键
     * @return 计划班组
     */
    @Override
    public CalPlanTeam selectCalPlanTeamByRecordId(Long recordId)
    {
        return calPlanTeamMapper.selectCalPlanTeamByRecordId(recordId);
    }

    /**
     * 查询计划班组列表
     * 
     * @param calPlanTeam 计划班组
     * @return 计划班组
     */
    @Override
    public List<CalPlanTeam> selectCalPlanTeamList(CalPlanTeam calPlanTeam)
    {
        return calPlanTeamMapper.selectCalPlanTeamList(calPlanTeam);
    }

    @Override
    public List<CalPlanTeam> selectCalPlanTeamListByPlanId(Long plandId) {
        CalPlanTeam param = new CalPlanTeam();
        param.setPlanId(plandId);
        return calPlanTeamMapper.selectCalPlanTeamList(param);
    }

    @Override
    public String checkPlanTeamUnique(CalPlanTeam calPlanTeam) {
        CalPlanTeam team = calPlanTeamMapper.checkPlanTeamUnique(calPlanTeam);
        Long recodeId = calPlanTeam.getRecordId()==null?-1L:calPlanTeam.getRecordId();
        if(StringUtils.isNotNull(team) && team.getRecordId().longValue() != recodeId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增计划班组
     * 
     * @param calPlanTeam 计划班组
     * @return 结果
     */
    @Override
    public int insertCalPlanTeam(CalPlanTeam calPlanTeam)
    {
        calPlanTeam.setCreateTime(DateUtils.getNowDate());
        return calPlanTeamMapper.insertCalPlanTeam(calPlanTeam);
    }

    /**
     * 修改计划班组
     * 
     * @param calPlanTeam 计划班组
     * @return 结果
     */
    @Override
    public int updateCalPlanTeam(CalPlanTeam calPlanTeam)
    {
        calPlanTeam.setUpdateTime(DateUtils.getNowDate());
        return calPlanTeamMapper.updateCalPlanTeam(calPlanTeam);
    }

    /**
     * 批量删除计划班组
     * 
     * @param recordIds 需要删除的计划班组主键
     * @return 结果
     */
    @Override
    public int deleteCalPlanTeamByRecordIds(Long[] recordIds)
    {
        return calPlanTeamMapper.deleteCalPlanTeamByRecordIds(recordIds);
    }

    /**
     * 删除计划班组信息
     * 
     * @param recordId 计划班组主键
     * @return 结果
     */
    @Override
    public int deleteCalPlanTeamByRecordId(Long recordId)
    {
        return calPlanTeamMapper.deleteCalPlanTeamByRecordId(recordId);
    }

    @Override
    public int deleteByPlanId(Long plandId) {
        return calPlanTeamMapper.deleteByPlanId(plandId);
    }
}
