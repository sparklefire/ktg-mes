package com.ktg.mes.cal.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.cal.mapper.CalPlanMapper;
import com.ktg.mes.cal.domain.CalPlan;
import com.ktg.mes.cal.service.ICalPlanService;

/**
 * 排班计划Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-06-06
 */
@Service
public class CalPlanServiceImpl implements ICalPlanService 
{
    @Autowired
    private CalPlanMapper calPlanMapper;

    /**
     * 查询排班计划
     * 
     * @param planId 排班计划主键
     * @return 排班计划
     */
    @Override
    public CalPlan selectCalPlanByPlanId(Long planId)
    {
        return calPlanMapper.selectCalPlanByPlanId(planId);
    }

    /**
     * 查询排班计划列表
     * 
     * @param calPlan 排班计划
     * @return 排班计划
     */
    @Override
    public List<CalPlan> selectCalPlanList(CalPlan calPlan)
    {
        return calPlanMapper.selectCalPlanList(calPlan);
    }

    /**
     * 新增排班计划
     * 
     * @param calPlan 排班计划
     * @return 结果
     */
    @Override
    public int insertCalPlan(CalPlan calPlan)
    {
        calPlan.setCreateTime(DateUtils.getNowDate());
        return calPlanMapper.insertCalPlan(calPlan);
    }

    /**
     * 修改排班计划
     * 
     * @param calPlan 排班计划
     * @return 结果
     */
    @Override
    public int updateCalPlan(CalPlan calPlan)
    {
        calPlan.setUpdateTime(DateUtils.getNowDate());
        return calPlanMapper.updateCalPlan(calPlan);
    }

    /**
     * 批量删除排班计划
     * 
     * @param planIds 需要删除的排班计划主键
     * @return 结果
     */
    @Override
    public int deleteCalPlanByPlanIds(Long[] planIds)
    {
        return calPlanMapper.deleteCalPlanByPlanIds(planIds);
    }

    /**
     * 删除排班计划信息
     * 
     * @param planId 排班计划主键
     * @return 结果
     */
    @Override
    public int deleteCalPlanByPlanId(Long planId)
    {
        return calPlanMapper.deleteCalPlanByPlanId(planId);
    }
}
