package com.ktg.mes.wm.service;

import com.ktg.mes.wm.domain.WmMaterialStock;

import java.util.List;

/**
 * 库存记录Service接口
 * 
 * @author yinjinlu
 * @date 2022-05-24
 */
public interface IWmMaterialStockService 
{
    /**
     * 查询库存记录
     * 
     * @param materialStockId 库存记录主键
     * @return 库存记录
     */
    public WmMaterialStock selectWmMaterialStockByMaterialStockId(Long materialStockId);



    /**
     * 查询库存记录列表
     * 
     * @param wmMaterialStock 库存记录
     * @return 库存记录集合
     */
    public List<WmMaterialStock> selectWmMaterialStockList(WmMaterialStock wmMaterialStock);


    /**
     * 模糊查询库存记录列表
     *
     * @param wmMaterialStock 库存记录
     * @return 库存记录集合
     */
    public List<WmMaterialStock> queryWmMaterialStockList(WmMaterialStock wmMaterialStock);


    /**
     * 新增库存记录
     * 
     * @param wmMaterialStock 库存记录
     * @return 结果
     */
    public int insertWmMaterialStock(WmMaterialStock wmMaterialStock);

    /**
     * 修改库存记录
     * 
     * @param wmMaterialStock 库存记录
     * @return 结果
     */
    public int updateWmMaterialStock(WmMaterialStock wmMaterialStock);

    /**
     * 批量删除库存记录
     * 
     * @param materialStockIds 需要删除的库存记录主键集合
     * @return 结果
     */
    public int deleteWmMaterialStockByMaterialStockIds(Long[] materialStockIds);

    /**
     * 删除库存记录信息
     * 
     * @param materialStockId 库存记录主键
     * @return 结果
     */
    public int deleteWmMaterialStockByMaterialStockId(Long materialStockId);
}
