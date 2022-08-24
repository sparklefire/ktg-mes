package com.ktg.mes.md.service;

import java.util.List;
import com.ktg.mes.md.domain.MdWorkstationMachine;

/**
 * 设备资源Service接口
 * 
 * @author yinjinlu
 * @date 2022-05-12
 */
public interface IMdWorkstationMachineService 
{
    /**
     * 查询设备资源
     * 
     * @param recordId 设备资源主键
     * @return 设备资源
     */
    public MdWorkstationMachine selectMdWorkstationMachineByRecordId(Long recordId);

    /**
     * 查询设备资源列表
     * 
     * @param mdWorkstationMachine 设备资源
     * @return 设备资源集合
     */
    public List<MdWorkstationMachine> selectMdWorkstationMachineList(MdWorkstationMachine mdWorkstationMachine);

    public MdWorkstationMachine checkMachineryExists(MdWorkstationMachine mdWorkstationMachine);

    /**
     * 新增设备资源
     * 
     * @param mdWorkstationMachine 设备资源
     * @return 结果
     */
    public int insertMdWorkstationMachine(MdWorkstationMachine mdWorkstationMachine);

    /**
     * 修改设备资源
     * 
     * @param mdWorkstationMachine 设备资源
     * @return 结果
     */
    public int updateMdWorkstationMachine(MdWorkstationMachine mdWorkstationMachine);

    /**
     * 批量删除设备资源
     * 
     * @param recordIds 需要删除的设备资源主键集合
     * @return 结果
     */
    public int deleteMdWorkstationMachineByRecordIds(Long[] recordIds);

    /**
     * 删除设备资源信息
     * 
     * @param recordId 设备资源主键
     * @return 结果
     */
    public int deleteMdWorkstationMachineByRecordId(Long recordId);

    /**
     * 根据工作站ID删除对应的设备列表
     * @param workstationId
     * @return
     */
    public int deleteByWorkstationId(Long workstationId);
}
