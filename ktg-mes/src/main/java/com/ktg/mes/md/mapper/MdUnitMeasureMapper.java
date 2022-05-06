package com.ktg.mes.md.mapper;

import com.ktg.mes.md.domain.MdUnitMeasure;

import java.util.List;

/**
 * 单位Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-27
 */
public interface MdUnitMeasureMapper 
{
    /**
     * 查询单位
     * 
     * @param measureId 单位主键
     * @return 单位
     */
    public MdUnitMeasure selectMdUnitMeasureByMeasureId(Long measureId);

    /**
     * 查询单位列表
     * 
     * @param mdUnitMeasure 单位
     * @return 单位集合
     */
    public List<MdUnitMeasure> selectMdUnitMeasureList(MdUnitMeasure mdUnitMeasure);

    /**
     * 新增单位
     * 
     * @param mdUnitMeasure 单位
     * @return 结果
     */
    public int insertMdUnitMeasure(MdUnitMeasure mdUnitMeasure);

    /**
     * 修改单位
     * 
     * @param mdUnitMeasure 单位
     * @return 结果
     */
    public int updateMdUnitMeasure(MdUnitMeasure mdUnitMeasure);

    /**
     * 删除单位
     * 
     * @param measureId 单位主键
     * @return 结果
     */
    public int deleteMdUnitMeasureByMeasureId(Long measureId);

    /**
     * 批量删除单位
     * 
     * @param measureIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMdUnitMeasureByMeasureIds(Long[] measureIds);
}
