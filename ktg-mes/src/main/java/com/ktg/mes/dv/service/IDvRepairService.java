package com.ktg.mes.dv.service;

import java.util.List;
import com.ktg.mes.dv.domain.DvRepair;

/**
 * 设备维修单Service接口
 * 
 * @author yinjinlu
 * @date 2022-08-06
 */
public interface IDvRepairService 
{
    /**
     * 查询设备维修单
     * 
     * @param repairId 设备维修单主键
     * @return 设备维修单
     */
    public DvRepair selectDvRepairByRepairId(Long repairId);

    /**
     * 查询设备维修单列表
     * 
     * @param dvRepair 设备维修单
     * @return 设备维修单集合
     */
    public List<DvRepair> selectDvRepairList(DvRepair dvRepair);

    /**
     * 检测维修单编号是否唯一
     * @param dvRepair
     * @return
     */
    public String checkCodeUnique(DvRepair dvRepair);

    /**
     * 新增设备维修单
     * 
     * @param dvRepair 设备维修单
     * @return 结果
     */
    public int insertDvRepair(DvRepair dvRepair);

    /**
     * 修改设备维修单
     * 
     * @param dvRepair 设备维修单
     * @return 结果
     */
    public int updateDvRepair(DvRepair dvRepair);

    /**
     * 批量删除设备维修单
     * 
     * @param repairIds 需要删除的设备维修单主键集合
     * @return 结果
     */
    public int deleteDvRepairByRepairIds(Long[] repairIds);

    /**
     * 删除设备维修单信息
     * 
     * @param repairId 设备维修单主键
     * @return 结果
     */
    public int deleteDvRepairByRepairId(Long repairId);
}
