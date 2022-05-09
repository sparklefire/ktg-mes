package com.ktg.mes.pro.service;

import java.util.List;
import com.ktg.mes.pro.domain.ProWorkorderBom;

/**
 * 生产工单BOM组成Service接口
 * 
 * @author yinjinlu
 * @date 2022-05-09
 */
public interface IProWorkorderBomService 
{
    /**
     * 查询生产工单BOM组成
     * 
     * @param lineId 生产工单BOM组成主键
     * @return 生产工单BOM组成
     */
    public ProWorkorderBom selectProWorkorderBomByLineId(Long lineId);

    /**
     * 查询生产工单BOM组成列表
     * 
     * @param proWorkorderBom 生产工单BOM组成
     * @return 生产工单BOM组成集合
     */
    public List<ProWorkorderBom> selectProWorkorderBomList(ProWorkorderBom proWorkorderBom);

    /**
     * 新增生产工单BOM组成
     * 
     * @param proWorkorderBom 生产工单BOM组成
     * @return 结果
     */
    public int insertProWorkorderBom(ProWorkorderBom proWorkorderBom);

    /**
     * 修改生产工单BOM组成
     * 
     * @param proWorkorderBom 生产工单BOM组成
     * @return 结果
     */
    public int updateProWorkorderBom(ProWorkorderBom proWorkorderBom);

    /**
     * 批量删除生产工单BOM组成
     * 
     * @param lineIds 需要删除的生产工单BOM组成主键集合
     * @return 结果
     */
    public int deleteProWorkorderBomByLineIds(Long[] lineIds);

    /**
     * 删除生产工单BOM组成信息
     * 
     * @param lineId 生产工单BOM组成主键
     * @return 结果
     */
    public int deleteProWorkorderBomByLineId(Long lineId);


    /**
     * 批量删除工单下所有的BOM组成数据
     * @param workorderId
     * @return
     */
    public int deleteProWorkorderBomByWorkorderId(Long workorderId);

}
