package com.ktg.mes.wm.mapper;

import java.util.List;
import com.ktg.mes.wm.domain.WmStockTaking;

/**
 * 库存盘点记录Mapper接口
 * 
 * @author yinjinlu
 * @date 2023-08-17
 */
public interface WmStockTakingMapper 
{
    /**
     * 查询库存盘点记录
     * 
     * @param takingId 库存盘点记录主键
     * @return 库存盘点记录
     */
    public WmStockTaking selectWmStockTakingByTakingId(Long takingId);

    /**
     * 查询库存盘点记录列表
     * 
     * @param wmStockTaking 库存盘点记录
     * @return 库存盘点记录集合
     */
    public List<WmStockTaking> selectWmStockTakingList(WmStockTaking wmStockTaking);

    /**
     * 检查编码是否唯一
     * @param stockTaking
     * @return
     */
    public WmStockTaking checkUnique(WmStockTaking stockTaking);

    /**
     * 新增库存盘点记录
     * 
     * @param wmStockTaking 库存盘点记录
     * @return 结果
     */
    public int insertWmStockTaking(WmStockTaking wmStockTaking);

    /**
     * 修改库存盘点记录
     * 
     * @param wmStockTaking 库存盘点记录
     * @return 结果
     */
    public int updateWmStockTaking(WmStockTaking wmStockTaking);

    /**
     * 删除库存盘点记录
     * 
     * @param takingId 库存盘点记录主键
     * @return 结果
     */
    public int deleteWmStockTakingByTakingId(Long takingId);

    /**
     * 批量删除库存盘点记录
     * 
     * @param takingIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWmStockTakingByTakingIds(Long[] takingIds);
}
