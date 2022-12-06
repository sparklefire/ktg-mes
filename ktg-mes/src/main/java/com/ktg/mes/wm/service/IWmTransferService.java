package com.ktg.mes.wm.service;

import java.util.List;
import com.ktg.mes.wm.domain.WmTransfer;
import com.ktg.mes.wm.domain.tx.TransferTxBean;

/**
 * 转移单Service接口
 * 
 * @author yinjinlu
 * @date 2022-11-28
 */
public interface IWmTransferService 
{
    /**
     * 查询转移单
     * 
     * @param transferId 转移单主键
     * @return 转移单
     */
    public WmTransfer selectWmTransferByTransferId(Long transferId);

    /**
     * 查询转移单列表
     * 
     * @param wmTransfer 转移单
     * @return 转移单集合
     */
    public List<WmTransfer> selectWmTransferList(WmTransfer wmTransfer);


    public List<TransferTxBean> getTxBeans(Long transferid);

    /**
     * 检查编码是否唯一
     * @param wmTransfer
     * @return
     */
    public String checkUnique(WmTransfer wmTransfer);

    /**
     * 新增转移单
     * 
     * @param wmTransfer 转移单
     * @return 结果
     */
    public int insertWmTransfer(WmTransfer wmTransfer);

    /**
     * 修改转移单
     * 
     * @param wmTransfer 转移单
     * @return 结果
     */
    public int updateWmTransfer(WmTransfer wmTransfer);

    /**
     * 批量删除转移单
     * 
     * @param transferIds 需要删除的转移单主键集合
     * @return 结果
     */
    public int deleteWmTransferByTransferIds(Long[] transferIds);

    /**
     * 删除转移单信息
     * 
     * @param transferId 转移单主键
     * @return 结果
     */
    public int deleteWmTransferByTransferId(Long transferId);
}
