package com.ktg.mes.wm.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import com.ktg.mes.wm.domain.WmMaterialStock;
import com.ktg.mes.wm.mapper.WmMaterialStockMapper;
import com.ktg.mes.wm.service.IWmMaterialStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 库存记录Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-24
 */
@Service
public class WmMaterialStockServiceImpl implements IWmMaterialStockService
{
    @Autowired
    private WmMaterialStockMapper wmMaterialStockMapper;

    /**
     * 查询库存记录
     * 
     * @param materialStockId 库存记录主键
     * @return 库存记录
     */
    @Override
    public WmMaterialStock selectWmMaterialStockByMaterialStockId(Long materialStockId)
    {
        return wmMaterialStockMapper.selectWmMaterialStockByMaterialStockId(materialStockId);
    }


    /**
     * 查询库存记录列表
     * 
     * @param wmMaterialStock 库存记录
     * @return 库存记录
     */
    @Override
    public List<WmMaterialStock> selectWmMaterialStockList(WmMaterialStock wmMaterialStock)
    {
        return wmMaterialStockMapper.selectWmMaterialStockList(wmMaterialStock);
    }

    @Override
    public List<WmMaterialStock> queryWmMaterialStockList(WmMaterialStock wmMaterialStock) {
        return wmMaterialStockMapper.queryWmMaterialStockList(wmMaterialStock);
    }

    /**
     * 新增库存记录
     * 
     * @param wmMaterialStock 库存记录
     * @return 结果
     */
    @Override
    public int insertWmMaterialStock(WmMaterialStock wmMaterialStock)
    {
        wmMaterialStock.setCreateTime(DateUtils.getNowDate());
        return wmMaterialStockMapper.insertWmMaterialStock(wmMaterialStock);
    }

    /**
     * 修改库存记录
     * 
     * @param wmMaterialStock 库存记录
     * @return 结果
     */
    @Override
    public int updateWmMaterialStock(WmMaterialStock wmMaterialStock)
    {
        wmMaterialStock.setUpdateTime(DateUtils.getNowDate());
        return wmMaterialStockMapper.updateWmMaterialStock(wmMaterialStock);
    }

    /**
     * 批量删除库存记录
     * 
     * @param materialStockIds 需要删除的库存记录主键
     * @return 结果
     */
    @Override
    public int deleteWmMaterialStockByMaterialStockIds(Long[] materialStockIds)
    {
        return wmMaterialStockMapper.deleteWmMaterialStockByMaterialStockIds(materialStockIds);
    }

    /**
     * 删除库存记录信息
     * 
     * @param materialStockId 库存记录主键
     * @return 结果
     */
    @Override
    public int deleteWmMaterialStockByMaterialStockId(Long materialStockId)
    {
        return wmMaterialStockMapper.deleteWmMaterialStockByMaterialStockId(materialStockId);
    }
}
