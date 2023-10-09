package com.ktg.mes.wm.mapper;

import java.util.List;
import com.ktg.mes.wm.domain.WmStockTakingLine;

/**
 * 库存盘点明细Mapper接口
 * 
 * @author yinjinlu
 * @date 2023-08-17
 */
public interface WmStockTakingLineMapper 
{
    /**
     * 查询库存盘点明细
     * 
     * @param lineId 库存盘点明细主键
     * @return 库存盘点明细
     */
    public WmStockTakingLine selectWmStockTakingLineByLineId(Long lineId);

    /**
     * 查询库存盘点明细列表
     * 
     * @param wmStockTakingLine 库存盘点明细
     * @return 库存盘点明细集合
     */
    public List<WmStockTakingLine> selectWmStockTakingLineList(WmStockTakingLine wmStockTakingLine);

    /**
     * 新增库存盘点明细
     * 
     * @param wmStockTakingLine 库存盘点明细
     * @return 结果
     */
    public int insertWmStockTakingLine(WmStockTakingLine wmStockTakingLine);

    /**
     * 修改库存盘点明细
     * 
     * @param wmStockTakingLine 库存盘点明细
     * @return 结果
     */
    public int updateWmStockTakingLine(WmStockTakingLine wmStockTakingLine);

    /**
     * 删除库存盘点明细
     * 
     * @param lineId 库存盘点明细主键
     * @return 结果
     */
    public int deleteWmStockTakingLineByLineId(Long lineId);

    /**
     * 批量删除库存盘点明细
     * 
     * @param lineIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWmStockTakingLineByLineIds(Long[] lineIds);

    public int deleteByTakingId(Long takingId);
}
