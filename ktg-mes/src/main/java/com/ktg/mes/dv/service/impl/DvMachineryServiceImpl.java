package com.ktg.mes.dv.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.dv.mapper.DvMachineryMapper;
import com.ktg.mes.dv.domain.DvMachinery;
import com.ktg.mes.dv.service.IDvMachineryService;

/**
 * 设备Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-08
 */
@Service
public class DvMachineryServiceImpl implements IDvMachineryService 
{
    @Autowired
    private DvMachineryMapper dvMachineryMapper;

    /**
     * 查询设备
     * 
     * @param machineryId 设备主键
     * @return 设备
     */
    @Override
    public DvMachinery selectDvMachineryByMachineryId(Long machineryId)
    {
        return dvMachineryMapper.selectDvMachineryByMachineryId(machineryId);
    }

    /**
     * 查询设备列表
     * 
     * @param dvMachinery 设备
     * @return 设备
     */
    @Override
    public List<DvMachinery> selectDvMachineryList(DvMachinery dvMachinery)
    {
        return dvMachineryMapper.selectDvMachineryList(dvMachinery);
    }

    /**
     * 新增设备
     * 
     * @param dvMachinery 设备
     * @return 结果
     */
    @Override
    public int insertDvMachinery(DvMachinery dvMachinery)
    {
        dvMachinery.setCreateTime(DateUtils.getNowDate());
        return dvMachineryMapper.insertDvMachinery(dvMachinery);
    }

    /**
     * 修改设备
     * 
     * @param dvMachinery 设备
     * @return 结果
     */
    @Override
    public int updateDvMachinery(DvMachinery dvMachinery)
    {
        dvMachinery.setUpdateTime(DateUtils.getNowDate());
        return dvMachineryMapper.updateDvMachinery(dvMachinery);
    }

    /**
     * 批量删除设备
     * 
     * @param machineryIds 需要删除的设备主键
     * @return 结果
     */
    @Override
    public int deleteDvMachineryByMachineryIds(Long[] machineryIds)
    {
        return dvMachineryMapper.deleteDvMachineryByMachineryIds(machineryIds);
    }

    /**
     * 删除设备信息
     * 
     * @param machineryId 设备主键
     * @return 结果
     */
    @Override
    public int deleteDvMachineryByMachineryId(Long machineryId)
    {
        return dvMachineryMapper.deleteDvMachineryByMachineryId(machineryId);
    }
}
