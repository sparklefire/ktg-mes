package com.ktg.mes.wm.service;

import java.util.List;
import com.ktg.mes.wm.domain.WmStockTakingLine;

/**
 * 库存盘点明细Service接口
 * 
 * @author yinjinlu
 * @date 2023-08-17
 */
public interface IWmStockTakingLineService 
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
     * 批量删除库存盘点明细
     * 
     * @param lineIds 需要删除的库存盘点明细主键集合
     * @return 结果
     */
    public int deleteWmStockTakingLineByLineIds(Long[] lineIds);

    /**
     * 删除库存盘点明细信息
     * 
     * @param lineId 库存盘点明细主键
     * @return 结果
     */
    public int deleteWmStockTakingLineByLineId(Long lineId);

    public int deleteByTakingId(Long takingId);
}
