package com.ktg.mes.pro.mapper;

import java.util.List;
import com.ktg.mes.pro.domain.ProRouteProduct;

/**
 * 产品制程Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-05-14
 */
public interface ProRouteProductMapper 
{
    /**
     * 查询产品制程
     * 
     * @param recordId 产品制程主键
     * @return 产品制程
     */
    public ProRouteProduct selectProRouteProductByRecordId(Long recordId);

    /**
     * 查询产品制程列表
     * 
     * @param proRouteProduct 产品制程
     * @return 产品制程集合
     */
    public List<ProRouteProduct> selectProRouteProductList(ProRouteProduct proRouteProduct);

    public ProRouteProduct checkItemUnique(ProRouteProduct proRouteProduct);

    /**
     * 新增产品制程
     * 
     * @param proRouteProduct 产品制程
     * @return 结果
     */
    public int insertProRouteProduct(ProRouteProduct proRouteProduct);

    /**
     * 修改产品制程
     * 
     * @param proRouteProduct 产品制程
     * @return 结果
     */
    public int updateProRouteProduct(ProRouteProduct proRouteProduct);

    /**
     * 删除产品制程
     * 
     * @param recordId 产品制程主键
     * @return 结果
     */
    public int deleteProRouteProductByRecordId(Long recordId);

    /**
     * 批量删除产品制程
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProRouteProductByRecordIds(Long[] recordIds);

    /**
     * 根据工艺路线ID删除对应的产品配置
     * @param routeId
     * @return
     */
    public int deleteByRouteId(Long routeId);
}
