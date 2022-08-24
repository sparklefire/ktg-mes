package com.ktg.mes.md.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.md.mapper.MdWorkstationMachineMapper;
import com.ktg.mes.md.domain.MdWorkstationMachine;
import com.ktg.mes.md.service.IMdWorkstationMachineService;

/**
 * 设备资源Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-12
 */
@Service
public class MdWorkstationMachineServiceImpl implements IMdWorkstationMachineService
{
    @Autowired
    private MdWorkstationMachineMapper mdWorkstationMachineMapper;

    /**
     * 查询设备资源
     * 
     * @param recordId 设备资源主键
     * @return 设备资源
     */
    @Override
    public MdWorkstationMachine selectMdWorkstationMachineByRecordId(Long recordId)
    {
        return mdWorkstationMachineMapper.selectMdWorkstationMachineByRecordId(recordId);
    }

    /**
     * 查询设备资源列表
     * 
     * @param mdWorkstationMachine 设备资源
     * @return 设备资源
     */
    @Override
    public List<MdWorkstationMachine> selectMdWorkstationMachineList(MdWorkstationMachine mdWorkstationMachine)
    {
        return mdWorkstationMachineMapper.selectMdWorkstationMachineList(mdWorkstationMachine);
    }

    /**
     * 检查机器资源是否已经被占用
     * 一台机器只能被分配到一个工作站中，且只能分配一次
     * @param mdWorkstationMachine
     * @return
     */
    @Override
    public MdWorkstationMachine checkMachineryExists(MdWorkstationMachine mdWorkstationMachine) {
        MdWorkstationMachine machine = mdWorkstationMachineMapper.checkMachineryExists(mdWorkstationMachine);
        return machine;
    }

    /**
     * 新增设备资源
     * 
     * @param mdWorkstationMachine 设备资源
     * @return 结果
     */
    @Override
    public int insertMdWorkstationMachine(MdWorkstationMachine mdWorkstationMachine)
    {
        mdWorkstationMachine.setCreateTime(DateUtils.getNowDate());
        return mdWorkstationMachineMapper.insertMdWorkstationMachine(mdWorkstationMachine);
    }

    /**
     * 修改设备资源
     * 
     * @param mdWorkstationMachine 设备资源
     * @return 结果
     */
    @Override
    public int updateMdWorkstationMachine(MdWorkstationMachine mdWorkstationMachine)
    {
        mdWorkstationMachine.setUpdateTime(DateUtils.getNowDate());
        return mdWorkstationMachineMapper.updateMdWorkstationMachine(mdWorkstationMachine);
    }

    /**
     * 批量删除设备资源
     * 
     * @param recordIds 需要删除的设备资源主键
     * @return 结果
     */
    @Override
    public int deleteMdWorkstationMachineByRecordIds(Long[] recordIds)
    {
        return mdWorkstationMachineMapper.deleteMdWorkstationMachineByRecordIds(recordIds);
    }

    /**
     * 删除设备资源信息
     * 
     * @param recordId 设备资源主键
     * @return 结果
     */
    @Override
    public int deleteMdWorkstationMachineByRecordId(Long recordId)
    {
        return mdWorkstationMachineMapper.deleteMdWorkstationMachineByRecordId(recordId);
    }

    @Override
    public int deleteByWorkstationId(Long workstationId) {
        return mdWorkstationMachineMapper.deleteByWorkstationId(workstationId);
    }
}
