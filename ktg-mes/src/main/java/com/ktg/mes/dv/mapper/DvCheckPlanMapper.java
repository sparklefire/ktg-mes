package com.ktg.mes.dv.mapper;

import java.util.List;
import com.ktg.mes.dv.domain.DvCheckPlan;

/**
 * 设备点检计划头Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-06-16
 */
public interface DvCheckPlanMapper 
{
    /**
     * 查询设备点检计划头
     * 
     * @param planId 设备点检计划头主键
     * @return 设备点检计划头
     */
    public DvCheckPlan selectDvCheckPlanByPlanId(Long planId);

    /**
     * 查询设备点检计划头列表
     * 
     * @param dvCheckPlan 设备点检计划头
     * @return 设备点检计划头集合
     */
    public List<DvCheckPlan> selectDvCheckPlanList(DvCheckPlan dvCheckPlan);

    public DvCheckPlan checkPlanCodeUnique(DvCheckPlan dvCheckPlan);

    /**
     * 新增设备点检计划头
     * 
     * @param dvCheckPlan 设备点检计划头
     * @return 结果
     */
    public int insertDvCheckPlan(DvCheckPlan dvCheckPlan);

    /**
     * 修改设备点检计划头
     * 
     * @param dvCheckPlan 设备点检计划头
     * @return 结果
     */
    public int updateDvCheckPlan(DvCheckPlan dvCheckPlan);

    /**
     * 删除设备点检计划头
     * 
     * @param planId 设备点检计划头主键
     * @return 结果
     */
    public int deleteDvCheckPlanByPlanId(Long planId);

    /**
     * 批量删除设备点检计划头
     * 
     * @param planIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDvCheckPlanByPlanIds(Long[] planIds);
}
