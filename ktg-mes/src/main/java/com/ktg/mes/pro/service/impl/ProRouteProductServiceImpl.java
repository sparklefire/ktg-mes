package com.ktg.mes.pro.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.pro.mapper.ProRouteProductMapper;
import com.ktg.mes.pro.domain.ProRouteProduct;
import com.ktg.mes.pro.service.IProRouteProductService;

/**
 * 产品制程Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-14
 */
@Service
public class ProRouteProductServiceImpl implements IProRouteProductService 
{
    @Autowired
    private ProRouteProductMapper proRouteProductMapper;

    /**
     * 查询产品制程
     * 
     * @param recordId 产品制程主键
     * @return 产品制程
     */
    @Override
    public ProRouteProduct selectProRouteProductByRecordId(Long recordId)
    {
        return proRouteProductMapper.selectProRouteProductByRecordId(recordId);
    }

    /**
     * 查询产品制程列表
     * 
     * @param proRouteProduct 产品制程
     * @return 产品制程
     */
    @Override
    public List<ProRouteProduct> selectProRouteProductList(ProRouteProduct proRouteProduct)
    {
        return proRouteProductMapper.selectProRouteProductList(proRouteProduct);
    }

    @Override
    public String checkItemUnique(ProRouteProduct proRouteProduct) {
        ProRouteProduct product = proRouteProductMapper.checkItemUnique(proRouteProduct);
        Long productId = proRouteProduct.getRecordId()==null?-1L:proRouteProduct.getRecordId();
        if(StringUtils.isNotNull(product) && product.getRecordId().longValue() != productId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增产品制程
     * 
     * @param proRouteProduct 产品制程
     * @return 结果
     */
    @Override
    public int insertProRouteProduct(ProRouteProduct proRouteProduct)
    {
        proRouteProduct.setCreateTime(DateUtils.getNowDate());
        return proRouteProductMapper.insertProRouteProduct(proRouteProduct);
    }

    /**
     * 修改产品制程
     * 
     * @param proRouteProduct 产品制程
     * @return 结果
     */
    @Override
    public int updateProRouteProduct(ProRouteProduct proRouteProduct)
    {
        proRouteProduct.setUpdateTime(DateUtils.getNowDate());
        return proRouteProductMapper.updateProRouteProduct(proRouteProduct);
    }

    /**
     * 批量删除产品制程
     * 
     * @param recordIds 需要删除的产品制程主键
     * @return 结果
     */
    @Override
    public int deleteProRouteProductByRecordIds(Long[] recordIds)
    {
        return proRouteProductMapper.deleteProRouteProductByRecordIds(recordIds);
    }

    /**
     * 删除产品制程信息
     * 
     * @param recordId 产品制程主键
     * @return 结果
     */
    @Override
    public int deleteProRouteProductByRecordId(Long recordId)
    {
        return proRouteProductMapper.deleteProRouteProductByRecordId(recordId);
    }

    @Override
    public int deleteByRouteId(Long routeId) {
        return proRouteProductMapper.deleteByRouteId(routeId);
    }
}
