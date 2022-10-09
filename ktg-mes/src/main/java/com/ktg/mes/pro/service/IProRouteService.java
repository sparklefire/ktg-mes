package com.ktg.mes.pro.service;

import java.util.List;
import com.ktg.mes.pro.domain.ProRoute;

/**
 * 工艺路线Service接口
 * 
 * @author yinjinlu
 * @date 2022-05-12
 */
public interface IProRouteService 
{
    /**
     * 查询工艺路线
     * 
     * @param routeId 工艺路线主键
     * @return 工艺路线
     */
    public ProRoute selectProRouteByRouteId(Long routeId);

    /**
     * 查询工艺路线列表
     * 
     * @param proRoute 工艺路线
     * @return 工艺路线集合
     */
    public List<ProRoute> selectProRouteList(ProRoute proRoute);

    /**
     * 根据物料查找生效的工艺路线
     * @param itemId
     * @return
     */
    public ProRoute getRouteByProductId(Long itemId);

    public String checkRouteCodeUnique(ProRoute proRoute);

    /**
     * 新增工艺路线
     * 
     * @param proRoute 工艺路线
     * @return 结果
     */
    public int insertProRoute(ProRoute proRoute);

    /**
     * 修改工艺路线
     * 
     * @param proRoute 工艺路线
     * @return 结果
     */
    public int updateProRoute(ProRoute proRoute);

    /**
     * 批量删除工艺路线
     * 
     * @param routeIds 需要删除的工艺路线主键集合
     * @return 结果
     */
    public int deleteProRouteByRouteIds(Long[] routeIds);

    /**
     * 删除工艺路线信息
     * 
     * @param routeId 工艺路线主键
     * @return 结果
     */
    public int deleteProRouteByRouteId(Long routeId);
}
