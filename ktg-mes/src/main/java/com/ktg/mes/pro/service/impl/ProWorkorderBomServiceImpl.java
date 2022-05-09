package com.ktg.mes.pro.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.pro.mapper.ProWorkorderBomMapper;
import com.ktg.mes.pro.domain.ProWorkorderBom;
import com.ktg.mes.pro.service.IProWorkorderBomService;

/**
 * 生产工单BOM组成Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-09
 */
@Service
public class ProWorkorderBomServiceImpl implements IProWorkorderBomService 
{
    @Autowired
    private ProWorkorderBomMapper proWorkorderBomMapper;

    /**
     * 查询生产工单BOM组成
     * 
     * @param lineId 生产工单BOM组成主键
     * @return 生产工单BOM组成
     */
    @Override
    public ProWorkorderBom selectProWorkorderBomByLineId(Long lineId)
    {
        return proWorkorderBomMapper.selectProWorkorderBomByLineId(lineId);
    }

    /**
     * 查询生产工单BOM组成列表
     * 
     * @param proWorkorderBom 生产工单BOM组成
     * @return 生产工单BOM组成
     */
    @Override
    public List<ProWorkorderBom> selectProWorkorderBomList(ProWorkorderBom proWorkorderBom)
    {
        return proWorkorderBomMapper.selectProWorkorderBomList(proWorkorderBom);
    }

    /**
     * 新增生产工单BOM组成
     * 
     * @param proWorkorderBom 生产工单BOM组成
     * @return 结果
     */
    @Override
    public int insertProWorkorderBom(ProWorkorderBom proWorkorderBom)
    {
        proWorkorderBom.setCreateTime(DateUtils.getNowDate());
        return proWorkorderBomMapper.insertProWorkorderBom(proWorkorderBom);
    }

    /**
     * 修改生产工单BOM组成
     * 
     * @param proWorkorderBom 生产工单BOM组成
     * @return 结果
     */
    @Override
    public int updateProWorkorderBom(ProWorkorderBom proWorkorderBom)
    {
        proWorkorderBom.setUpdateTime(DateUtils.getNowDate());
        return proWorkorderBomMapper.updateProWorkorderBom(proWorkorderBom);
    }

    /**
     * 批量删除生产工单BOM组成
     * 
     * @param lineIds 需要删除的生产工单BOM组成主键
     * @return 结果
     */
    @Override
    public int deleteProWorkorderBomByLineIds(Long[] lineIds)
    {
        return proWorkorderBomMapper.deleteProWorkorderBomByLineIds(lineIds);
    }

    /**
     * 删除生产工单BOM组成信息
     * 
     * @param lineId 生产工单BOM组成主键
     * @return 结果
     */
    @Override
    public int deleteProWorkorderBomByLineId(Long lineId)
    {
        return proWorkorderBomMapper.deleteProWorkorderBomByLineId(lineId);
    }

    @Override
    public int deleteProWorkorderBomByWorkorderId(Long workorderId) {
        return proWorkorderBomMapper.deleteProWorkorderBomByWorkorderId(workorderId);
    }
}
