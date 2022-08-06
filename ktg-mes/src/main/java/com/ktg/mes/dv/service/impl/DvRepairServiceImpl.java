package com.ktg.mes.dv.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.dv.mapper.DvRepairMapper;
import com.ktg.mes.dv.domain.DvRepair;
import com.ktg.mes.dv.service.IDvRepairService;

/**
 * 设备维修单Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-08-06
 */
@Service
public class DvRepairServiceImpl implements IDvRepairService 
{
    @Autowired
    private DvRepairMapper dvRepairMapper;

    /**
     * 查询设备维修单
     * 
     * @param repairId 设备维修单主键
     * @return 设备维修单
     */
    @Override
    public DvRepair selectDvRepairByRepairId(Long repairId)
    {
        return dvRepairMapper.selectDvRepairByRepairId(repairId);
    }

    /**
     * 查询设备维修单列表
     * 
     * @param dvRepair 设备维修单
     * @return 设备维修单
     */
    @Override
    public List<DvRepair> selectDvRepairList(DvRepair dvRepair)
    {
        return dvRepairMapper.selectDvRepairList(dvRepair);
    }

    @Override
    public String checkCodeUnique(DvRepair dvRepair) {
        DvRepair rp = dvRepairMapper.checkCodeUnique(dvRepair);
        Long repairId = dvRepair.getRepairId() ==null?-1L: dvRepair.getRepairId();
        if(StringUtils.isNotNull(rp) && repairId.longValue() != rp.getRepairId().longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增设备维修单
     * 
     * @param dvRepair 设备维修单
     * @return 结果
     */
    @Override
    public int insertDvRepair(DvRepair dvRepair)
    {
        dvRepair.setCreateTime(DateUtils.getNowDate());
        return dvRepairMapper.insertDvRepair(dvRepair);
    }

    /**
     * 修改设备维修单
     * 
     * @param dvRepair 设备维修单
     * @return 结果
     */
    @Override
    public int updateDvRepair(DvRepair dvRepair)
    {
        dvRepair.setUpdateTime(DateUtils.getNowDate());
        return dvRepairMapper.updateDvRepair(dvRepair);
    }

    /**
     * 批量删除设备维修单
     * 
     * @param repairIds 需要删除的设备维修单主键
     * @return 结果
     */
    @Override
    public int deleteDvRepairByRepairIds(Long[] repairIds)
    {
        return dvRepairMapper.deleteDvRepairByRepairIds(repairIds);
    }

    /**
     * 删除设备维修单信息
     * 
     * @param repairId 设备维修单主键
     * @return 结果
     */
    @Override
    public int deleteDvRepairByRepairId(Long repairId)
    {
        return dvRepairMapper.deleteDvRepairByRepairId(repairId);
    }
}
