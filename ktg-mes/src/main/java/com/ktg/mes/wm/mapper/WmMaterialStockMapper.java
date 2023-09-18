package com.ktg.mes.wm.mapper;

import com.ktg.mes.wm.domain.WmMaterialStock;

import java.util.List;

/**
 * 库存记录Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-05-24
 */
public interface WmMaterialStockMapper 
{
    /**
     * 查询库存记录
     * 
     * @param materialStockId 库存记录主键
     * @return 库存记录
     */
    public WmMaterialStock selectWmMaterialStockByMaterialStockId(Long materialStockId);


    public WmMaterialStock loadMaterialStock(WmMaterialStock stock);

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
     * 删除库存记录
     * 
     * @param materialStockId 库存记录主键
     * @return 结果
     */
    public int deleteWmMaterialStockByMaterialStockId(Long materialStockId);

    /**
     * 批量删除库存记录
     * 
     * @param materialStockIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWmMaterialStockByMaterialStockIds(Long[] materialStockIds);
}
