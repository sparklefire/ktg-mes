package com.ktg.mes.wm.service;

import java.util.List;
import com.ktg.mes.wm.domain.WmStockTakingResult;

/**
 * 库存盘点结果Service接口
 * 
 * @author yinjinlu
 * @date 2023-08-22
 */
public interface IWmStockTakingResultService 
{
    /**
     * 查询库存盘点结果
     * 
     * @param resultId 库存盘点结果主键
     * @return 库存盘点结果
     */
    public WmStockTakingResult selectWmStockTakingResultByResultId(Long resultId);

    /**
     * 查询库存盘点结果列表
     * 
     * @param wmStockTakingResult 库存盘点结果
     * @return 库存盘点结果集合
     */
    public List<WmStockTakingResult> selectWmStockTakingResultList(WmStockTakingResult wmStockTakingResult);


    /**
     * 针对明盘的盘点结果计算
     * @param takingId
     * @return
     */
    public void calculateOpenWmStockTakingResult(Long takingId);


    /**
     * 根据当前盘点记录计算盘点结果
     * @param takingId
     * @return
     */
    public void calculateWmStockTakingResult(Long takingId);

    /**
     * 新增库存盘点结果
     * 
     * @param wmStockTakingResult 库存盘点结果
     * @return 结果
     */
    public int insertWmStockTakingResult(WmStockTakingResult wmStockTakingResult);

    /**
     * 修改库存盘点结果
     * 
     * @param wmStockTakingResult 库存盘点结果
     * @return 结果
     */
    public int updateWmStockTakingResult(WmStockTakingResult wmStockTakingResult);

    /**
     * 批量删除库存盘点结果
     * 
     * @param resultIds 需要删除的库存盘点结果主键集合
     * @return 结果
     */
    public int deleteWmStockTakingResultByResultIds(Long[] resultIds);

    /**
     * 删除库存盘点结果信息
     * 
     * @param resultId 库存盘点结果主键
     * @return 结果
     */
    public int deleteWmStockTakingResultByResultId(Long resultId);

    public int deleteWmStockTakingResultByTakingId(Long takingId);
}
