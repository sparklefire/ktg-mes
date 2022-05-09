package com.ktg.mes.md.service;

import java.util.List;
import com.ktg.mes.md.domain.MdProductBom;

/**
 * 产品BOM关系Service接口
 * 
 * @author yinjinlu
 * @date 2022-05-09
 */
public interface IMdProductBomService 
{
    /**
     * 查询产品BOM关系
     * 
     * @param bomId 产品BOM关系主键
     * @return 产品BOM关系
     */
    public MdProductBom selectMdProductBomByBomId(Long bomId);

    /**
     * 查询产品BOM关系列表
     * 
     * @param mdProductBom 产品BOM关系
     * @return 产品BOM关系集合
     */
    public List<MdProductBom> selectMdProductBomList(MdProductBom mdProductBom);

    /**
     * 新增产品BOM关系
     * 
     * @param mdProductBom 产品BOM关系
     * @return 结果
     */
    public int insertMdProductBom(MdProductBom mdProductBom);

    /**
     * 修改产品BOM关系
     * 
     * @param mdProductBom 产品BOM关系
     * @return 结果
     */
    public int updateMdProductBom(MdProductBom mdProductBom);

    /**
     * 批量删除产品BOM关系
     * 
     * @param bomIds 需要删除的产品BOM关系主键集合
     * @return 结果
     */
    public int deleteMdProductBomByBomIds(Long[] bomIds);

    /**
     * 删除产品BOM关系信息
     * 
     * @param bomId 产品BOM关系主键
     * @return 结果
     */
    public int deleteMdProductBomByBomId(Long bomId);
}
