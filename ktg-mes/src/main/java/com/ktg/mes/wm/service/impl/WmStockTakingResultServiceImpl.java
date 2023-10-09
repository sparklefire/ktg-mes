package com.ktg.mes.wm.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.wm.mapper.WmStockTakingResultMapper;
import com.ktg.mes.wm.domain.WmStockTakingResult;
import com.ktg.mes.wm.service.IWmStockTakingResultService;

/**
 * 库存盘点结果Service业务层处理
 * 
 * @author yinjinlu
 * @date 2023-08-22
 */
@Service
public class WmStockTakingResultServiceImpl implements IWmStockTakingResultService 
{
    @Autowired
    private WmStockTakingResultMapper wmStockTakingResultMapper;

    /**
     * 查询库存盘点结果
     * 
     * @param resultId 库存盘点结果主键
     * @return 库存盘点结果
     */
    @Override
    public WmStockTakingResult selectWmStockTakingResultByResultId(Long resultId)
    {
        return wmStockTakingResultMapper.selectWmStockTakingResultByResultId(resultId);
    }

    /**
     * 查询库存盘点结果列表
     * 
     * @param wmStockTakingResult 库存盘点结果
     * @return 库存盘点结果
     */
    @Override
    public List<WmStockTakingResult> selectWmStockTakingResultList(WmStockTakingResult wmStockTakingResult)
    {
        return wmStockTakingResultMapper.selectWmStockTakingResultList(wmStockTakingResult);
    }

    @Override
    public void calculateOpenWmStockTakingResult(Long takingId) {
         wmStockTakingResultMapper.calculateOpenWmStockTakingResult(takingId);
    }

    @Override
    public void calculateWmStockTakingResult(Long takingId) {
         wmStockTakingResultMapper.calculateWmStockTakingResult(takingId);
    }


    /**
     * 新增库存盘点结果
     * 
     * @param wmStockTakingResult 库存盘点结果
     * @return 结果
     */
    @Override
    public int insertWmStockTakingResult(WmStockTakingResult wmStockTakingResult)
    {
        wmStockTakingResult.setCreateTime(DateUtils.getNowDate());
        return wmStockTakingResultMapper.insertWmStockTakingResult(wmStockTakingResult);
    }

    /**
     * 修改库存盘点结果
     * 
     * @param wmStockTakingResult 库存盘点结果
     * @return 结果
     */
    @Override
    public int updateWmStockTakingResult(WmStockTakingResult wmStockTakingResult)
    {
        wmStockTakingResult.setUpdateTime(DateUtils.getNowDate());
        return wmStockTakingResultMapper.updateWmStockTakingResult(wmStockTakingResult);
    }

    /**
     * 批量删除库存盘点结果
     * 
     * @param resultIds 需要删除的库存盘点结果主键
     * @return 结果
     */
    @Override
    public int deleteWmStockTakingResultByResultIds(Long[] resultIds)
    {
        return wmStockTakingResultMapper.deleteWmStockTakingResultByResultIds(resultIds);
    }

    /**
     * 删除库存盘点结果信息
     * 
     * @param resultId 库存盘点结果主键
     * @return 结果
     */
    @Override
    public int deleteWmStockTakingResultByResultId(Long resultId)
    {
        return wmStockTakingResultMapper.deleteWmStockTakingResultByResultId(resultId);
    }

    @Override
    public int deleteWmStockTakingResultByTakingId(Long takingId) {
        return wmStockTakingResultMapper.deleteWmStockTakingResultByTakingId(takingId);
    }
}
