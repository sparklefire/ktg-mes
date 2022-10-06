package com.ktg.mes.wm.mapper;

import java.util.List;
import com.ktg.mes.wm.domain.WmProductSalse;
import com.ktg.mes.wm.domain.tx.ProductSalseTxBean;

/**
 * 销售出库单Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-10-04
 */
public interface WmProductSalseMapper 
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
     * 检查编号唯一性
     * @param wmProductSalse
     * @return
     */
    public WmProductSalse checkUnique(WmProductSalse wmProductSalse);

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
     * 删除销售出库单
     * 
     * @param salseId 销售出库单主键
     * @return 结果
     */
    public int deleteWmProductSalseBySalseId(Long salseId);

    /**
     * 批量删除销售出库单
     * 
     * @param salseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWmProductSalseBySalseIds(Long[] salseIds);

    /**
     * 获取产品销售出库事务Bean
     * @param salseId
     * @return
     */
    public List<ProductSalseTxBean> getTxBeans(Long salseId);
}
