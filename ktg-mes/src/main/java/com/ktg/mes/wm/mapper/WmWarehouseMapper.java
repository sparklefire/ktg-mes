package com.ktg.mes.wm.mapper;

import java.util.List;
import com.ktg.mes.wm.domain.WmWarehouse;

/**
 * 仓库设置Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-05-07
 */
public interface WmWarehouseMapper 
{
    /**
     * 查询仓库设置
     * 
     * @param warehouseId 仓库设置主键
     * @return 仓库设置
     */
    public WmWarehouse selectWmWarehouseByWarehouseId(Long warehouseId);

    /**
     * 根据编码查询仓库
     * @param warehouseCdoe
     * @return
     */
    public WmWarehouse selectWmWarehouseByWarehouseCode(String warehouseCdoe);


    /**
     * 查询仓库设置列表
     * 
     * @param wmWarehouse 仓库设置
     * @return 仓库设置集合
     */
    public List<WmWarehouse> selectWmWarehouseList(WmWarehouse wmWarehouse);

    public List<WmWarehouse> getTreeList();

    public WmWarehouse checkWarehouseCodeUnique(WmWarehouse wmWarehouse);
    public WmWarehouse checkWarehouseNameUnique(WmWarehouse wmWarehouse);

    /**
     * 新增仓库设置
     * 
     * @param wmWarehouse 仓库设置
     * @return 结果
     */
    public int insertWmWarehouse(WmWarehouse wmWarehouse);

    /**
     * 修改仓库设置
     * 
     * @param wmWarehouse 仓库设置
     * @return 结果
     */
    public int updateWmWarehouse(WmWarehouse wmWarehouse);

    /**
     * 删除仓库设置
     * 
     * @param warehouseId 仓库设置主键
     * @return 结果
     */
    public int deleteWmWarehouseByWarehouseId(Long warehouseId);

    /**
     * 批量删除仓库设置
     * 
     * @param warehouseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWmWarehouseByWarehouseIds(Long[] warehouseIds);

}
