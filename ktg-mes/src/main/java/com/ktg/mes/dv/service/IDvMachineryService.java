package com.ktg.mes.dv.service;

import java.util.List;
import com.ktg.mes.dv.domain.DvMachinery;

/**
 * 设备Service接口
 * 
 * @author yinjinlu
 * @date 2022-05-08
 */
public interface IDvMachineryService 
{
    /**
     * 查询设备
     * 
     * @param machineryId 设备主键
     * @return 设备
     */
    public DvMachinery selectDvMachineryByMachineryId(Long machineryId);

    /**
     * 查询设备列表
     * 
     * @param dvMachinery 设备
     * @return 设备集合
     */
    public List<DvMachinery> selectDvMachineryList(DvMachinery dvMachinery);

    /**
     * 新增设备
     * 
     * @param dvMachinery 设备
     * @return 结果
     */
    public int insertDvMachinery(DvMachinery dvMachinery);

    /**
     * 修改设备
     * 
     * @param dvMachinery 设备
     * @return 结果
     */
    public int updateDvMachinery(DvMachinery dvMachinery);

    /**
     * 批量删除设备
     * 
     * @param machineryIds 需要删除的设备主键集合
     * @return 结果
     */
    public int deleteDvMachineryByMachineryIds(Long[] machineryIds);

    /**
     * 删除设备信息
     * 
     * @param machineryId 设备主键
     * @return 结果
     */
    public int deleteDvMachineryByMachineryId(Long machineryId);
}
