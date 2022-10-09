package com.ktg.mes.pro.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.pro.mapper.ProRouteMapper;
import com.ktg.mes.pro.domain.ProRoute;
import com.ktg.mes.pro.service.IProRouteService;

/**
 * 工艺路线Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-12
 */
@Service
public class ProRouteServiceImpl implements IProRouteService 
{
    @Autowired
    private ProRouteMapper proRouteMapper;

    /**
     * 查询工艺路线
     * 
     * @param routeId 工艺路线主键
     * @return 工艺路线
     */
    @Override
    public ProRoute selectProRouteByRouteId(Long routeId)
    {
        return proRouteMapper.selectProRouteByRouteId(routeId);
    }

    /**
     * 查询工艺路线列表
     * 
     * @param proRoute 工艺路线
     * @return 工艺路线
     */
    @Override
    public List<ProRoute> selectProRouteList(ProRoute proRoute)
    {
        return proRouteMapper.selectProRouteList(proRoute);
    }

    @Override
    public ProRoute getRouteByProductId(Long itemId) {
        return proRouteMapper.getRouteByProductId(itemId);
    }


    @Override
    public String checkRouteCodeUnique(ProRoute proRoute) {
        ProRoute route = proRouteMapper.checkRouteCodeUnique(proRoute);
        Long routeId = proRoute.getRouteId()==null?-1L:proRoute.getRouteId();
        if(StringUtils.isNotNull(route) && route.getRouteId().longValue() != routeId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增工艺路线
     * 
     * @param proRoute 工艺路线
     * @return 结果
     */
    @Override
    public int insertProRoute(ProRoute proRoute)
    {
        proRoute.setCreateTime(DateUtils.getNowDate());
        return proRouteMapper.insertProRoute(proRoute);
    }

    /**
     * 修改工艺路线
     * 
     * @param proRoute 工艺路线
     * @return 结果
     */
    @Override
    public int updateProRoute(ProRoute proRoute)
    {
        proRoute.setUpdateTime(DateUtils.getNowDate());
        return proRouteMapper.updateProRoute(proRoute);
    }

    /**
     * 批量删除工艺路线
     * 
     * @param routeIds 需要删除的工艺路线主键
     * @return 结果
     */
    @Override
    public int deleteProRouteByRouteIds(Long[] routeIds)
    {
        return proRouteMapper.deleteProRouteByRouteIds(routeIds);
    }

    /**
     * 删除工艺路线信息
     * 
     * @param routeId 工艺路线主键
     * @return 结果
     */
    @Override
    public int deleteProRouteByRouteId(Long routeId)
    {
        return proRouteMapper.deleteProRouteByRouteId(routeId);
    }
}
