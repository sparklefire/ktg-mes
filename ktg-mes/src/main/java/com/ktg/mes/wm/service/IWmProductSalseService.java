package com.ktg.mes.wm.service;

import java.util.List;
import com.ktg.mes.wm.domain.WmProductSalse;
import com.ktg.mes.wm.domain.tx.ProductSalseTxBean;

/**
 * 销售出库单Service接口
 * 
 * @author yinjinlu
 * @date 2022-10-04
 */
public interface IWmProductSalseService 
{
    /**
     * 查询销售出库单
     * 
     * @param salseId 销售出库单主键
     * @return 销售出库单
     */
    public WmProductSalse selectWmProductSalseBySalseId(Long salseId);

    /**
     * 查询销售出库单列表
     * 
     * @param wmProductSalse 销售出库单
     * @return 销售出库单集合
     */
    public List<WmProductSalse> selectWmProductSalseList(WmProductSalse wmProductSalse);


    /**
     * 获取产品销售出库事务Bean
     * @param salseId
     * @return
     */
    public List<ProductSalseTxBean> getTxBeans(Long salseId);

    /**
     * 检查编号唯一性
     * @param wmProductSalse
     * @return
     */
    public String checkUnique(WmProductSalse wmProductSalse);

    /**
     * 新增销售出库单
     * 
     * @param wmProductSalse 销售出库单
     * @return 结果
     */
    public int insertWmProductSalse(WmProductSalse wmProductSalse);

    /**
     * 修改销售出库单
     * 
     * @param wmProductSalse 销售出库单
     * @return 结果
     */
    public int updateWmProductSalse(WmProductSalse wmProductSalse);

    /**
     * 批量删除销售出库单
     * 
     * @param salseIds 需要删除的销售出库单主键集合
     * @return 结果
     */
    public int deleteWmProductSalseBySalseIds(Long[] salseIds);

    /**
     * 删除销售出库单信息
     * 
     * @param salseId 销售出库单主键
     * @return 结果
     */
    public int deleteWmProductSalseBySalseId(Long salseId);
}
