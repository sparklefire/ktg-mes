package com.ktg.mes.wm.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.exception.BussinessException;
import com.ktg.common.utils.bean.BeanUtils;
import com.ktg.mes.wm.domain.WmStorageLocation;
import com.ktg.mes.wm.domain.WmTransaction;
import com.ktg.mes.wm.domain.WmWarehouse;
import com.ktg.mes.wm.domain.tx.IssueTxBean;
import com.ktg.mes.wm.domain.tx.ItemRecptTxBean;
import com.ktg.mes.wm.domain.tx.RtVendorTxBean;
import com.ktg.mes.wm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
public class StorageCoreServiceImpl implements IStorageCoreService {

    @Autowired
    private IWmTransactionService wmTransactionService;

    @Autowired
    private IWmWarehouseService wmWarehouseService;

    @Autowired
    private IWmStorageLocationService wmStorageLocationService;

    @Autowired
    private IWmStorageAreaService wmStorageAreaService;

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
        if(CollUtil.isEmpty(lines)){
            throw new BussinessException("没有需要处理的领料单行");
        }

        String transactionType_out = UserConstants.TRANSACTION_TYPE_ITEM_ISSUE_OUT;
        String transactionType_in = UserConstants.TRANSACTION_TYPE_ITEM_ISSUE_IN;
        for(int i=0;i<lines.size();i++){
            IssueTxBean line = lines.get(i);
            //这里先构造一条原库存减少的事务
            WmTransaction transaction_out = new WmTransaction();
            transaction_out.setTransactionType(transactionType_out);
            BeanUtils.copyBeanProp(transaction_out,line);
            transaction_out.setTransactionFlag(-1);//库存减少
            transaction_out.setTransactionDate(new Date());
            wmTransactionService.processTransaction(transaction_out);

            //再构造一条目的库存增加的事务
            WmTransaction transaction_in = new WmTransaction();
            transaction_in.setTransactionType(transactionType_in);
            BeanUtils.copyBeanProp(transaction_in,line);
            transaction_in.setTransactionFlag(1);//库存增加

            //由于是新增的库存记录所以需要将查询出来的库存记录ID置为空
            transaction_in.setMaterialStockId(null);

            //这里使用系统默认生成的线边库初始化对应的入库仓库、库区、库位
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseCode(UserConstants.VIRTUAL_WH);
            transaction_in.setWarehouseId(warehouse.getWarehouseId());
            transaction_in.setWarehouseCode(warehouse.getWarehouseCode());
            transaction_in.setWarehouseName(warehouse.getWarehouseName());
            WmStorageLocation location = wmStorageLocationService.selectWmStorageLocationByLocationCode(UserConstants.VIRTUAL_WS);
            transaction_in.setLocationId(location.getLocationId());
            transaction_in.setLocationCode(location.getLocationCode());
            transaction_in.setLocationName(location.getLocationName());
            //设置入库相关联的出库事务ID
            transaction_in.setRelatedTransactionId(transaction_out.getTransactionId());
            wmTransactionService.processTransaction(transaction_in);
        }
    }
}
