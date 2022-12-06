package com.ktg.mes.wm.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.wm.domain.tx.TransferTxBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.wm.mapper.WmTransferMapper;
import com.ktg.mes.wm.domain.WmTransfer;
import com.ktg.mes.wm.service.IWmTransferService;

/**
 * 转移单Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-11-28
 */
@Service
public class WmTransferServiceImpl implements IWmTransferService 
{
    @Autowired
    private WmTransferMapper wmTransferMapper;

    /**
     * 查询转移单
     * 
     * @param transferId 转移单主键
     * @return 转移单
     */
    @Override
    public WmTransfer selectWmTransferByTransferId(Long transferId)
    {
        return wmTransferMapper.selectWmTransferByTransferId(transferId);
    }

    /**
     * 查询转移单列表
     * 
     * @param wmTransfer 转移单
     * @return 转移单
     */
    @Override
    public List<WmTransfer> selectWmTransferList(WmTransfer wmTransfer)
    {
        return wmTransferMapper.selectWmTransferList(wmTransfer);
    }

    @Override
    public List<TransferTxBean> getTxBeans(Long transferid) {
        return wmTransferMapper.getTxBeans(transferid);
    }

    @Override
    public String checkUnique(WmTransfer wmTransfer) {
        WmTransfer transfer = wmTransferMapper.checkUnique(wmTransfer);
        Long transferId = wmTransfer.getTransferId() == null?-1L:wmTransfer.getTransferId();
        if(StringUtils.isNotNull(transfer) && transferId.longValue() != transfer.getTransferId().longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增转移单
     * 
     * @param wmTransfer 转移单
     * @return 结果
     */
    @Override
    public int insertWmTransfer(WmTransfer wmTransfer)
    {
        wmTransfer.setCreateTime(DateUtils.getNowDate());
        return wmTransferMapper.insertWmTransfer(wmTransfer);
    }

    /**
     * 修改转移单
     * 
     * @param wmTransfer 转移单
     * @return 结果
     */
    @Override
    public int updateWmTransfer(WmTransfer wmTransfer)
    {
        wmTransfer.setUpdateTime(DateUtils.getNowDate());
        return wmTransferMapper.updateWmTransfer(wmTransfer);
    }

    /**
     * 批量删除转移单
     * 
     * @param transferIds 需要删除的转移单主键
     * @return 结果
     */
    @Override
    public int deleteWmTransferByTransferIds(Long[] transferIds)
    {
        return wmTransferMapper.deleteWmTransferByTransferIds(transferIds);
    }

    /**
     * 删除转移单信息
     * 
     * @param transferId 转移单主键
     * @return 结果
     */
    @Override
    public int deleteWmTransferByTransferId(Long transferId)
    {
        return wmTransferMapper.deleteWmTransferByTransferId(transferId);
    }
}
