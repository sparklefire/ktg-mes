package com.ktg.mes.wm.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ktg.common.exception.BussinessException;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.md.domain.MdItem;
import com.ktg.mes.md.mapper.MdItemMapper;
import com.ktg.mes.wm.domain.WmMaterialStock;
import com.ktg.mes.wm.mapper.WmMaterialStockMapper;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private WmMaterialStockMapper wmMaterialStockMapper;

    @Autowired
    private MdItemMapper mdItemMapper;

    @Override
    public synchronized WmTransaction processTransaction(WmTransaction wmTransaction) {
        WmMaterialStock stock = new WmMaterialStock();

        validate(wmTransaction);
        initStock(wmTransaction,stock);

        WmMaterialStock ms =wmMaterialStockMapper.loadMaterialStock(stock);
        BigDecimal quantity = wmTransaction.getTransactionQuantity().multiply(new BigDecimal(wmTransaction.getTransactionFlag()));
        if(StringUtils.isNotNull(ms)){
            //MS已存在
            BigDecimal resultQuantity =ms.getQuantityOnhand().add(quantity);
            if(wmTransaction.isStorageCheckFlag() && resultQuantity.compareTo(new BigDecimal(0))<0){
                throw new BussinessException("库存数量不足！");
            }
            stock.setQuantityOnhand(resultQuantity);
            stock.setMaterialStockId(ms.getMaterialStockId());
            wmMaterialStockMapper.updateWmMaterialStock(stock);
        }else {
            //MS不存在
            stock.setQuantityOnhand(quantity);
            wmMaterialStockMapper.insertWmMaterialStock(stock);
        }
        wmTransaction.setMaterialStockId(stock.getMaterialStockId());
        wmTransaction.setTransactionQuantity(quantity);
        wmTransactionMapper.insertWmTransaction(wmTransaction);
        return wmTransaction;
    }


    private void validate(WmTransaction transaction){
        if(StringUtils.isNull(transaction.getTransactionType())){
            throw new BussinessException("库存事务不能为空");
        }

        if(StringUtils.isNull(transaction.getTransactionQuantity())){
            throw new BussinessException("事务数量不能为空");
        }

        if(StringUtils.isNull(transaction.getSourceDocCode())){
            throw new BussinessException("来源单据号不能为空");
        }

        if(StringUtils.isNull(transaction.getSourceDocLineId())){
            throw new BussinessException("来源单据行ID不能为空");
        }

        if(StringUtils.isNull(transaction.getTransactionDate())){
            transaction.setTransactionDate(new Date());
        }
    }

    public void initStock(WmTransaction transaction,WmMaterialStock stock){

        if(StringUtils.isNotNull(transaction.getMaterialStockId())){
            WmMaterialStock st = wmMaterialStockMapper.selectWmMaterialStockByMaterialStockId(transaction.getMaterialStockId());
            BeanUtils.copyProperties(st,stock);
        }else{
            MdItem item =mdItemMapper.selectMdItemById(transaction.getItemId());
            stock.setItemTypeId(item.getItemTypeId());
            stock.setItemId(transaction.getItemId());
            stock.setItemCode(transaction.getItemCode());
            stock.setItemName(transaction.getItemName());
            stock.setSpecification(transaction.getSpecification());
            stock.setUnitOfMeasure(transaction.getUnitOfMeasure());
            stock.setBatchCode(transaction.getBatchCode());
            stock.setWarehouseId(transaction.getWarehouseId());
            stock.setWarehouseCode(transaction.getWarehouseCode());
            stock.setWarehouseName(transaction.getWarehouseName());
            stock.setLocationId(transaction.getLocationId());
            stock.setLocationCode(transaction.getLocationCode());
            stock.setLocationName(transaction.getLocationName());
            if(StringUtils.isNotNull(transaction.getAreaId())){
                stock.setAreaId(transaction.getAreaId());
                stock.setAreaCode(transaction.getAreaCode());
                stock.setAreaName(transaction.getAreaName());
            }
            if(StringUtils.isNotNull(transaction.getVendorId())){
                stock.setVendorId(transaction.getVendorId());
                stock.setVendorCode(transaction.getVendorCode());
                stock.setVendorName(transaction.getVendorName());
                stock.setVendorNick(transaction.getVendorNick());
            }
            //使用库存事务日期初始化入库日期
            //一般在入库的时候才会涉及到materialStock的新增，出库的时候都是出的现有库存
            if(StringUtils.isNotNull(transaction.getRecptDate())){
                stock.setRecptDate(transaction.getRecptDate());
            }else{
                stock.setRecptDate(new Date());
            }

            //使用库存事务上的生产工单初始化库存记录上的生产工单，以支持某些情况下库存需要标记生产工单的情况
            if(StringUtils.isNotNull(transaction.getWorkorderId())){
                stock.setWorkorderId(transaction.getWorkorderId());
                stock.setWorkorderCode(transaction.getWorkorderCode());
            }
            stock.setExpireDate(transaction.getExpireDate());
        }
    }


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
