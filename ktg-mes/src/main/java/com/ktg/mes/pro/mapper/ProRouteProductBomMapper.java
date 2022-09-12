package com.ktg.mes.pro.mapper;

import java.util.List;
import com.ktg.mes.pro.domain.ProRouteProductBom;

/**
 * 产品制程物料BOMMapper接口
 * 
 * @author yinjinlu
 * @date 2022-09-12
 */
public interface ProRouteProductBomMapper 
{
    /**
     * 查询产品制程物料BOM
     * 
     * @param recordId 产品制程物料BOM主键
     * @return 产品制程物料BOM
     */
    public ProRouteProductBom selectProRouteProductBomByRecordId(Long recordId);

    /**
     * 查询产品制程物料BOM列表
     * 
     * @param proRouteProductBom 产品制程物料BOM
     * @return 产品制程物料BOM集合
     */
    public List<ProRouteProductBom> selectProRouteProductBomList(ProRouteProductBom proRouteProductBom);


    /**
     * 检测是否重复配置
     * @param proRouteProductBom
     * @return
     */
    public ProRouteProductBom checkUnique(ProRouteProductBom proRouteProductBom);

    /**
     * 新增产品制程物料BOM
     * 
     * @param proRouteProductBom 产品制程物料BOM
     * @return 结果
     */
    public int insertProRouteProductBom(ProRouteProductBom proRouteProductBom);

    /**
     * 修改产品制程物料BOM
     * 
     * @param proRouteProductBom 产品制程物料BOM
     * @return 结果
     */
    public int updateProRouteProductBom(ProRouteProductBom proRouteProductBom);

    /**
     * 删除产品制程物料BOM
     * 
     * @param recordId 产品制程物料BOM主键
     * @return 结果
     */
    public int deleteProRouteProductBomByRecordId(Long recordId);

    /**
     * 批量删除产品制程物料BOM
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProRouteProductBomByRecordIds(Long[] recordIds);

    /**
     * 根据工艺路线ID删除所有产品BOM设置
     * @param routeId
     * @return
     */
    public int deleteByRouteId(Long routeId);

    /**
     * 根据工艺路线ID和产品ID删除BOM设置
     * @param proRouteProductBom
     * @return
     */
    public int deleteByRouteIdAndProductId(ProRouteProductBom proRouteProductBom);
}
