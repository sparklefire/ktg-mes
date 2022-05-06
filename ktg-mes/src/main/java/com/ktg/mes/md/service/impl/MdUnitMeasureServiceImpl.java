package com.ktg.mes.md.service.impl;

import java.util.List;

import com.ktg.mes.md.service.IMdUnitMeasureService;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.md.mapper.MdUnitMeasureMapper;
import com.ktg.mes.md.domain.MdUnitMeasure;

/**
 * 单位Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-27
 */
@Service
public class MdUnitMeasureServiceImpl implements IMdUnitMeasureService
{
    @Autowired
    private MdUnitMeasureMapper mdUnitMeasureMapper;

    /**
     * 查询单位
     * 
     * @param measureId 单位主键
     * @return 单位
     */
    @Override
    public MdUnitMeasure selectMdUnitMeasureByMeasureId(Long measureId)
    {
        return mdUnitMeasureMapper.selectMdUnitMeasureByMeasureId(measureId);
    }

    /**
     * 查询单位列表
     * 
     * @param mdUnitMeasure 单位
     * @return 单位
     */
    @Override
    public List<MdUnitMeasure> selectMdUnitMeasureList(MdUnitMeasure mdUnitMeasure)
    {
        return mdUnitMeasureMapper.selectMdUnitMeasureList(mdUnitMeasure);
    }

    /**
     * 新增单位
     * 
     * @param mdUnitMeasure 单位
     * @return 结果
     */
    @Override
    public int insertMdUnitMeasure(MdUnitMeasure mdUnitMeasure)
    {
        mdUnitMeasure.setCreateTime(DateUtils.getNowDate());
        return mdUnitMeasureMapper.insertMdUnitMeasure(mdUnitMeasure);
    }

    /**
     * 修改单位
     * 
     * @param mdUnitMeasure 单位
     * @return 结果
     */
    @Override
    public int updateMdUnitMeasure(MdUnitMeasure mdUnitMeasure)
    {
        mdUnitMeasure.setUpdateTime(DateUtils.getNowDate());
        return mdUnitMeasureMapper.updateMdUnitMeasure(mdUnitMeasure);
    }

    /**
     * 批量删除单位
     * 
     * @param measureIds 需要删除的单位主键
     * @return 结果
     */
    @Override
    public int deleteMdUnitMeasureByMeasureIds(Long[] measureIds)
    {
        return mdUnitMeasureMapper.deleteMdUnitMeasureByMeasureIds(measureIds);
    }

    /**
     * 删除单位信息
     * 
     * @param measureId 单位主键
     * @return 结果
     */
    @Override
    public int deleteMdUnitMeasureByMeasureId(Long measureId)
    {
        return mdUnitMeasureMapper.deleteMdUnitMeasureByMeasureId(measureId);
    }
}
