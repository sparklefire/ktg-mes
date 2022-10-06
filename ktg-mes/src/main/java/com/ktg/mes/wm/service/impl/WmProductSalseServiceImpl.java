package com.ktg.mes.wm.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.wm.domain.tx.ProductSalseTxBean;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.wm.mapper.WmProductSalseMapper;
import com.ktg.mes.wm.domain.WmProductSalse;
import com.ktg.mes.wm.service.IWmProductSalseService;

/**
 * 销售出库单Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-10-04
 */
@Service
public class WmProductSalseServiceImpl implements IWmProductSalseService 
{
    @Autowired
    private WmProductSalseMapper wmProductSalseMapper;

    /**
     * 查询销售出库单
     * 
     * @param salseId 销售出库单主键
     * @return 销售出库单
     */
    @Override
    public WmProductSalse selectWmProductSalseBySalseId(Long salseId)
    {
        return wmProductSalseMapper.selectWmProductSalseBySalseId(salseId);
    }

    /**
     * 查询销售出库单列表
     * 
     * @param wmProductSalse 销售出库单
     * @return 销售出库单
     */
    @Override
    public List<WmProductSalse> selectWmProductSalseList(WmProductSalse wmProductSalse)
    {
        return wmProductSalseMapper.selectWmProductSalseList(wmProductSalse);
    }

    @Override
    public List<ProductSalseTxBean> getTxBeans(Long salseId) {
        return wmProductSalseMapper.getTxBeans(salseId);
    }

    @Override
    public String checkUnique(WmProductSalse wmProductSalse) {
        WmProductSalse salse = wmProductSalseMapper.checkUnique(wmProductSalse);
        Long salseId = wmProductSalse.getSalseId() ==null? -1L:wmProductSalse.getSalseId();
        if(StringUtils.isNotNull(salse) && salseId.longValue() != salse.getSalseId().longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增销售出库单
     * 
     * @param wmProductSalse 销售出库单
     * @return 结果
     */
    @Override
    public int insertWmProductSalse(WmProductSalse wmProductSalse)
    {
        wmProductSalse.setCreateTime(DateUtils.getNowDate());
        return wmProductSalseMapper.insertWmProductSalse(wmProductSalse);
    }

    /**
     * 修改销售出库单
     * 
     * @param wmProductSalse 销售出库单
     * @return 结果
     */
    @Override
    public int updateWmProductSalse(WmProductSalse wmProductSalse)
    {
        wmProductSalse.setUpdateTime(DateUtils.getNowDate());
        return wmProductSalseMapper.updateWmProductSalse(wmProductSalse);
    }

    /**
     * 批量删除销售出库单
     * 
     * @param salseIds 需要删除的销售出库单主键
     * @return 结果
     */
    @Override
    public int deleteWmProductSalseBySalseIds(Long[] salseIds)
    {
        return wmProductSalseMapper.deleteWmProductSalseBySalseIds(salseIds);
    }

    /**
     * 删除销售出库单信息
     * 
     * @param salseId 销售出库单主键
     * @return 结果
     */
    @Override
    public int deleteWmProductSalseBySalseId(Long salseId)
    {
        return wmProductSalseMapper.deleteWmProductSalseBySalseId(salseId);
    }


}
