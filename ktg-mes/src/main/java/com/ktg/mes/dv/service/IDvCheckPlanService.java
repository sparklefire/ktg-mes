package com.ktg.mes.dv.service;

import java.util.List;
import com.ktg.mes.dv.domain.DvCheckPlan;

/**
 * 设备点检计划头Service接口
 * 
 * @author yinjinlu
 * @date 2022-06-16
 */
public interface IDvCheckPlanService 
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

    /**
     * 检查计划编码是否唯一
     * @param dvCheckPlan
     * @return
     */
    public String checkPlanCodeUnique(DvCheckPlan dvCheckPlan);

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
     * 批量删除设备点检计划头
     * 
     * @param planIds 需要删除的设备点检计划头主键集合
     * @return 结果
     */
    public int deleteDvCheckPlanByPlanIds(Long[] planIds);

    /**
     * 删除设备点检计划头信息
     * 
     * @param planId 设备点检计划头主键
     * @return 结果
     */
    public int deleteDvCheckPlanByPlanId(Long planId);
}
