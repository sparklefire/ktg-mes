package com.ktg.mes.wm.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.wm.mapper.WmTransferLineMapper;
import com.ktg.mes.wm.domain.WmTransferLine;
import com.ktg.mes.wm.service.IWmTransferLineService;

/**
 * 转移单行Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-11-28
 */
@Service
public class WmTransferLineServiceImpl implements IWmTransferLineService 
{
    @Autowired
    private WmTransferLineMapper wmTransferLineMapper;

    /**
     * 查询转移单行
     * 
     * @param lineId 转移单行主键
     * @return 转移单行
     */
    @Override
    public WmTransferLine selectWmTransferLineByLineId(Long lineId)
    {
        return wmTransferLineMapper.selectWmTransferLineByLineId(lineId);
    }

    /**
     * 查询转移单行列表
     * 
     * @param wmTransferLine 转移单行
     * @return 转移单行
     */
    @Override
    public List<WmTransferLine> selectWmTransferLineList(WmTransferLine wmTransferLine)
    {
        return wmTransferLineMapper.selectWmTransferLineList(wmTransferLine);
    }

    /**
     * 新增转移单行
     * 
     * @param wmTransferLine 转移单行
     * @return 结果
     */
    @Override
    public int insertWmTransferLine(WmTransferLine wmTransferLine)
    {
        wmTransferLine.setCreateTime(DateUtils.getNowDate());
        return wmTransferLineMapper.insertWmTransferLine(wmTransferLine);
    }

    /**
     * 修改转移单行
     * 
     * @param wmTransferLine 转移单行
     * @return 结果
     */
    @Override
    public int updateWmTransferLine(WmTransferLine wmTransferLine)
    {
        wmTransferLine.setUpdateTime(DateUtils.getNowDate());
        return wmTransferLineMapper.updateWmTransferLine(wmTransferLine);
    }

    /**
     * 批量删除转移单行
     * 
     * @param lineIds 需要删除的转移单行主键
     * @return 结果
     */
    @Override
    public int deleteWmTransferLineByLineIds(Long[] lineIds)
    {
        return wmTransferLineMapper.deleteWmTransferLineByLineIds(lineIds);
    }

    /**
     * 删除转移单行信息
     * 
     * @param lineId 转移单行主键
     * @return 结果
     */
    @Override
    public int deleteWmTransferLineByLineId(Long lineId)
    {
        return wmTransferLineMapper.deleteWmTransferLineByLineId(lineId);
    }

    @Override
    public int deleteByTransferId(Long transferId) {
        return wmTransferLineMapper.deleteByTransferId(transferId);
    }
}
