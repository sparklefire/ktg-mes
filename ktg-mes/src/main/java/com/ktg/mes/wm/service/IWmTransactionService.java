package com.ktg.mes.wm.service;

import java.util.List;
import com.ktg.mes.wm.domain.WmTransaction;

/**
 * 库存事务Service接口
 * 
 * @author yinjinlu
 * @date 2022-05-24
 */
public interface IWmTransactionService 
{

    public WmTransaction processTransaction(WmTransaction wmTransaction);


    /**
     * 查询库存事务
     * 
     * @param transactionId 库存事务主键
     * @return 库存事务
     */
    public WmTransaction selectWmTransactionByTransactionId(Long transactionId);

    /**
     * 查询库存事务列表
     * 
     * @param wmTransaction 库存事务
     * @return 库存事务集合
     */
    public List<WmTransaction> selectWmTransactionList(WmTransaction wmTransaction);

    /**
     * 新增库存事务
     * 
     * @param wmTransaction 库存事务
     * @return 结果
     */
    public int insertWmTransaction(WmTransaction wmTransaction);

    /**
     * 修改库存事务
     * 
     * @param wmTransaction 库存事务
     * @return 结果
     */
    public int updateWmTransaction(WmTransaction wmTransaction);

    /**
     * 批量删除库存事务
     * 
     * @param transactionIds 需要删除的库存事务主键集合
     * @return 结果
     */
    public int deleteWmTransactionByTransactionIds(Long[] transactionIds);

    /**
     * 删除库存事务信息
     * 
     * @param transactionId 库存事务主键
     * @return 结果
     */
    public int deleteWmTransactionByTransactionId(Long transactionId);
}
