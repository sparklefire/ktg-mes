package com.ktg.mes.wm.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.wm.mapper.WmStockTakingLineMapper;
import com.ktg.mes.wm.domain.WmStockTakingLine;
import com.ktg.mes.wm.service.IWmStockTakingLineService;

/**
 * 库存盘点明细Service业务层处理
 * 
 * @author yinjinlu
 * @date 2023-08-17
 */
@Service
public class WmStockTakingLineServiceImpl implements IWmStockTakingLineService 
{
    @Autowired
    private WmStockTakingLineMapper wmStockTakingLineMapper;

    /**
     * 查询库存盘点明细
     * 
     * @param lineId 库存盘点明细主键
     * @return 库存盘点明细
     */
    @Override
    public WmStockTakingLine selectWmStockTakingLineByLineId(Long lineId)
    {
        return wmStockTakingLineMapper.selectWmStockTakingLineByLineId(lineId);
    }

    /**
     * 查询库存盘点明细列表
     * 
     * @param wmStockTakingLine 库存盘点明细
     * @return 库存盘点明细
     */
    @Override
    public List<WmStockTakingLine> selectWmStockTakingLineList(WmStockTakingLine wmStockTakingLine)
    {
        return wmStockTakingLineMapper.selectWmStockTakingLineList(wmStockTakingLine);
    }

    /**
     * 新增库存盘点明细
     * 
     * @param wmStockTakingLine 库存盘点明细
     * @return 结果
     */
    @Override
    public int insertWmStockTakingLine(WmStockTakingLine wmStockTakingLine)
    {
        wmStockTakingLine.setCreateTime(DateUtils.getNowDate());
        return wmStockTakingLineMapper.insertWmStockTakingLine(wmStockTakingLine);
    }

    /**
     * 修改库存盘点明细
     * 
     * @param wmStockTakingLine 库存盘点明细
     * @return 结果
     */
    @Override
    public int updateWmStockTakingLine(WmStockTakingLine wmStockTakingLine)
    {
        wmStockTakingLine.setUpdateTime(DateUtils.getNowDate());
        return wmStockTakingLineMapper.updateWmStockTakingLine(wmStockTakingLine);
    }

    /**
     * 批量删除库存盘点明细
     * 
     * @param lineIds 需要删除的库存盘点明细主键
     * @return 结果
     */
    @Override
    public int deleteWmStockTakingLineByLineIds(Long[] lineIds)
    {
        return wmStockTakingLineMapper.deleteWmStockTakingLineByLineIds(lineIds);
    }

    /**
     * 删除库存盘点明细信息
     * 
     * @param lineId 库存盘点明细主键
     * @return 结果
     */
    @Override
    public int deleteWmStockTakingLineByLineId(Long lineId)
    {
        return wmStockTakingLineMapper.deleteWmStockTakingLineByLineId(lineId);
    }

    @Override
    public int deleteByTakingId(Long takingId) {
        return wmStockTakingLineMapper.deleteByTakingId(takingId);
    }


}
