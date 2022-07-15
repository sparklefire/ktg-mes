package com.ktg.mes.wm.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.exception.BussinessException;
import com.ktg.common.utils.bean.BeanUtils;
import com.ktg.mes.wm.domain.WmTransaction;
import com.ktg.mes.wm.domain.tx.IssueTxBean;
import com.ktg.mes.wm.domain.tx.ItemRecptTxBean;
import com.ktg.mes.wm.domain.tx.RtVendorTxBean;
import com.ktg.mes.wm.service.IStorageCoreService;
import com.ktg.mes.wm.service.IWmTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
public class StorageCoreServiceImpl implements IStorageCoreService {

    @Autowired
    private IWmTransactionService wmTransactionService;

    /**
     * 处理入库单行
     * @param lines
     */
    @Override
    public void processItemRecpt(List<ItemRecptTxBean> lines) {
        String transactionType = UserConstants.TRANSACTION_TYPE_ITEM_RECPT;
        if(CollUtil.isEmpty(lines)){
            throw new BussinessException("没有需要处理的入库单行");
        }

        for (int i =0;i<lines.size();i++){
            ItemRecptTxBean line = lines.get(i);
            WmTransaction transaction = new WmTransaction();
            transaction.setTransactionType(transactionType);
            BeanUtils.copyBeanProp(transaction,line);
            transaction.setTransactionFlag(1); //库存增加
            transaction.setTransactionDate(new Date());
            wmTransactionService.processTransaction(transaction);
//            transaction.setItemId(line.getItemId());
//            transaction.setItemCode(line.getItemCode());
//            transaction.setItemName(line.getItemName());
//            transaction.setSpecification(line.getSpecification());
//            transaction.setUnitOfMeasure(line.getUnitOfMeasure());
//            transaction.setBatchCode(line.getBatchCode());
//            transaction.setWarehouseId(line.getWarehouseId());
//            transaction.setWarehouseCode(line.getWarehouseCode());
//            transaction.setWarehouseName(line.getWarehouseName());
//            transaction.setLocationId(line.getLocationId());
        }

    }

    @Override
    public void processRtVendor(List<RtVendorTxBean> lines) {
        String transactionType = UserConstants.TRANSACTION_TYPE_ITEM_RTV;
        if(CollUtil.isEmpty(lines)){
            throw new BussinessException("没有需要处理的退货单行");
        }

        for(int i=0;i<lines.size();i++){
            RtVendorTxBean line = lines.get(i);
            WmTransaction transaction = new WmTransaction();
            transaction.setTransactionType(transactionType);
            BeanUtils.copyBeanProp(transaction,line);
            transaction.setTransactionFlag(-1); //库存减少
            transaction.setTransactionDate(new Date());
            wmTransactionService.processTransaction(transaction);
        }

    }

    @Override
    public void processIssue(List<IssueTxBean> lines) {
        String transactionType = UserConstants.TRANSACTION_TYPE_ITEM_ISSUE;
        if(CollUtil.isEmpty(lines)){
            throw new BussinessException("没有需要处理的领料单行");
        }

        for(int i=0;i<lines.size();i++){
            IssueTxBean line = lines.get(i);
            WmTransaction transaction = new WmTransaction();
            transaction.setTransactionType(transactionType);
            BeanUtils.copyBeanProp(transaction,line);
            transaction.setTransactionFlag(-1);//库存减少
            transaction.setTransactionDate(new Date());
            wmTransactionService.processTransaction(transaction);
        }
    }
}
