package com.ktg.mes.wm.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.wm.mapper.WmTransactionMapper;
import com.ktg.mes.wm.domain.WmTransaction;
import com.ktg.mes.wm.service.IWmTransactionService;

/**
 * 库存事务Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-24
 */
@Service
public class WmTransactionServiceImpl implements IWmTransactionService 
{
    @Autowired
    private WmTransactionMapper wmTransactionMapper;

    /**
     * 查询库存事务
     * 
     * @param transactionId 库存事务主键
     * @return 库存事务
     */
    @Override
    public WmTransaction selectWmTransactionByTransactionId(Long transactionId)
    {
        return wmTransactionMapper.selectWmTransactionByTransactionId(transactionId);
    }

    /**
     * 查询库存事务列表
     * 
     * @param wmTransaction 库存事务
     * @return 库存事务
     */
    @Override
    public List<WmTransaction> selectWmTransactionList(WmTransaction wmTransaction)
    {
        return wmTransactionMapper.selectWmTransactionList(wmTransaction);
    }

    /**
     * 新增库存事务
     * 
     * @param wmTransaction 库存事务
     * @return 结果
     */
    @Override
    public int insertWmTransaction(WmTransaction wmTransaction)
    {
        wmTransaction.setCreateTime(DateUtils.getNowDate());
        return wmTransactionMapper.insertWmTransaction(wmTransaction);
    }

    /**
     * 修改库存事务
     * 
     * @param wmTransaction 库存事务
     * @return 结果
     */
    @Override
    public int updateWmTransaction(WmTransaction wmTransaction)
    {
        wmTransaction.setUpdateTime(DateUtils.getNowDate());
        return wmTransactionMapper.updateWmTransaction(wmTransaction);
    }

    /**
     * 批量删除库存事务
     * 
     * @param transactionIds 需要删除的库存事务主键
     * @return 结果
     */
    @Override
    public int deleteWmTransactionByTransactionIds(Long[] transactionIds)
    {
        return wmTransactionMapper.deleteWmTransactionByTransactionIds(transactionIds);
    }

    /**
     * 删除库存事务信息
     * 
     * @param transactionId 库存事务主键
     * @return 结果
     */
    @Override
    public int deleteWmTransactionByTransactionId(Long transactionId)
    {
        return wmTransactionMapper.deleteWmTransactionByTransactionId(transactionId);
    }
}
