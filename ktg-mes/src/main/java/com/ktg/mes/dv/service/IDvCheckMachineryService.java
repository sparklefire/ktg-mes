package com.ktg.mes.dv.service;

import java.util.List;
import com.ktg.mes.dv.domain.DvCheckMachinery;

/**
 * 点检设备Service接口
 * 
 * @author yinjinlu
 * @date 2022-06-17
 */
public interface IDvCheckMachineryService 
{
    /**
     * 查询点检设备
     * 
     * @param recordId 点检设备主键
     * @return 点检设备
     */
    public DvCheckMachinery selectDvCheckMachineryByRecordId(Long recordId);

    /**
     * 查询点检设备列表
     * 
     * @param dvCheckMachinery 点检设备
     * @return 点检设备集合
     */
    public List<DvCheckMachinery> selectDvCheckMachineryList(DvCheckMachinery dvCheckMachinery);

    public String  checkMachineryUnique(DvCheckMachinery dvCheckMachinery);

    /**
     * 新增点检设备
     * 
     * @param dvCheckMachinery 点检设备
     * @return 结果
     */
    public int insertDvCheckMachinery(DvCheckMachinery dvCheckMachinery);

    /**
     * 修改点检设备
     * 
     * @param dvCheckMachinery 点检设备
     * @return 结果
     */
    public int updateDvCheckMachinery(DvCheckMachinery dvCheckMachinery);

    /**
     * 批量删除点检设备
     * 
     * @param recordIds 需要删除的点检设备主键集合
     * @return 结果
     */
    public int deleteDvCheckMachineryByRecordIds(Long[] recordIds);

    /**
     * 删除点检设备信息
     * 
     * @param recordId 点检设备主键
     * @return 结果
     */
    public int deleteDvCheckMachineryByRecordId(Long recordId);

    /**
     * 根据计划头ID删除对应的设备列表
     * @param planId
     * @return
     */
    public int deleteByPlanId(Long planId);
}
