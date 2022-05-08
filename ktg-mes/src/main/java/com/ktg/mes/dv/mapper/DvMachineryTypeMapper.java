package com.ktg.mes.dv.mapper;

import java.util.List;
import com.ktg.mes.dv.domain.DvMachineryType;

/**
 * 设备类型Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-05-08
 */
public interface DvMachineryTypeMapper 
{
    /**
     * 查询设备类型
     * 
     * @param machineryTypeId 设备类型主键
     * @return 设备类型
     */
    public DvMachineryType selectDvMachineryTypeByMachineryTypeId(Long machineryTypeId);

    /**
     * 查询设备类型列表
     * 
     * @param dvMachineryType 设备类型
     * @return 设备类型集合
     */
    public List<DvMachineryType> selectDvMachineryTypeList(DvMachineryType dvMachineryType);

    /**
     * 新增设备类型
     * 
     * @param dvMachineryType 设备类型
     * @return 结果
     */
    public int insertDvMachineryType(DvMachineryType dvMachineryType);

    /**
     * 修改设备类型
     * 
     * @param dvMachineryType 设备类型
     * @return 结果
     */
    public int updateDvMachineryType(DvMachineryType dvMachineryType);

    /**
     * 删除设备类型
     * 
     * @param machineryTypeId 设备类型主键
     * @return 结果
     */
    public int deleteDvMachineryTypeByMachineryTypeId(Long machineryTypeId);

    /**
     * 批量删除设备类型
     * 
     * @param machineryTypeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDvMachineryTypeByMachineryTypeIds(Long[] machineryTypeIds);
}
