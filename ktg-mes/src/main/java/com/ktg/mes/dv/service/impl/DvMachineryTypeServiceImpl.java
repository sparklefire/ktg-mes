package com.ktg.mes.dv.service.impl;

import java.util.List;

import com.ktg.common.core.domain.entity.ItemType;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.dv.mapper.DvMachineryTypeMapper;
import com.ktg.mes.dv.domain.DvMachineryType;
import com.ktg.mes.dv.service.IDvMachineryTypeService;

/**
 * 设备类型Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-08
 */
@Service
public class DvMachineryTypeServiceImpl implements IDvMachineryTypeService 
{
    @Autowired
    private DvMachineryTypeMapper dvMachineryTypeMapper;

    /**
     * 查询设备类型
     * 
     * @param machineryTypeId 设备类型主键
     * @return 设备类型
     */
    @Override
    public DvMachineryType selectDvMachineryTypeByMachineryTypeId(Long machineryTypeId)
    {
        return dvMachineryTypeMapper.selectDvMachineryTypeByMachineryTypeId(machineryTypeId);
    }

    /**
     * 查询设备类型列表
     * 
     * @param dvMachineryType 设备类型
     * @return 设备类型
     */
    @Override
    public List<DvMachineryType> selectDvMachineryTypeList(DvMachineryType dvMachineryType)
    {
        return dvMachineryTypeMapper.selectDvMachineryTypeList(dvMachineryType);
    }

    /**
     * 新增设备类型
     * 
     * @param dvMachineryType 设备类型
     * @return 结果
     */
    @Override
    public int insertDvMachineryType(DvMachineryType dvMachineryType)
    {
        if(dvMachineryType.getParentTypeId()!= null){
            DvMachineryType parent = dvMachineryTypeMapper.selectDvMachineryTypeByMachineryTypeId(dvMachineryType.getParentTypeId());
            if(StringUtils.isNotNull(parent)){
                dvMachineryType.setAncestors(parent.getAncestors()+","+parent.getMachineryTypeId());
            }
        }
        dvMachineryType.setCreateTime(DateUtils.getNowDate());
        return dvMachineryTypeMapper.insertDvMachineryType(dvMachineryType);
    }

    /**
     * 修改设备类型
     * 
     * @param dvMachineryType 设备类型
     * @return 结果
     */
    @Override
    public int updateDvMachineryType(DvMachineryType dvMachineryType)
    {
        dvMachineryType.setUpdateTime(DateUtils.getNowDate());
        return dvMachineryTypeMapper.updateDvMachineryType(dvMachineryType);
    }

    /**
     * 批量删除设备类型
     * 
     * @param machineryTypeIds 需要删除的设备类型主键
     * @return 结果
     */
    @Override
    public int deleteDvMachineryTypeByMachineryTypeIds(Long[] machineryTypeIds)
    {
        return dvMachineryTypeMapper.deleteDvMachineryTypeByMachineryTypeIds(machineryTypeIds);
    }

    /**
     * 删除设备类型信息
     * 
     * @param machineryTypeId 设备类型主键
     * @return 结果
     */
    @Override
    public int deleteDvMachineryTypeByMachineryTypeId(Long machineryTypeId)
    {
        return dvMachineryTypeMapper.deleteDvMachineryTypeByMachineryTypeId(machineryTypeId);
    }
}
