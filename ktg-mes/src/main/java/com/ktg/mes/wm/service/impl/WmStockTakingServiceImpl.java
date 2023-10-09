package com.ktg.mes.wm.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.wm.mapper.WmStockTakingMapper;
import com.ktg.mes.wm.domain.WmStockTaking;
import com.ktg.mes.wm.service.IWmStockTakingService;

/**
 * 库存盘点记录Service业务层处理
 * 
 * @author yinjinlu
 * @date 2023-08-17
 */
@Service
public class WmStockTakingServiceImpl implements IWmStockTakingService 
{
    @Autowired
    private WmStockTakingMapper wmStockTakingMapper;

    /**
     * 查询库存盘点记录
     * 
     * @param takingId 库存盘点记录主键
     * @return 库存盘点记录
     */
    @Override
    public WmStockTaking selectWmStockTakingByTakingId(Long takingId)
    {
        return wmStockTakingMapper.selectWmStockTakingByTakingId(takingId);
    }

    /**
     * 查询库存盘点记录列表
     * 
     * @param wmStockTaking 库存盘点记录
     * @return 库存盘点记录
     */
    @Override
    public List<WmStockTaking> selectWmStockTakingList(WmStockTaking wmStockTaking)
    {
        return wmStockTakingMapper.selectWmStockTakingList(wmStockTaking);
    }

    /**
     * 检查编码是否唯一
     * @param stockTaking
     * @return
     */
    @Override
    public String checkUnique(WmStockTaking stockTaking){
        WmStockTaking taking = wmStockTakingMapper.checkUnique(stockTaking);
        Long takingId = stockTaking.getTakingId() == null ? -1L: stockTaking.getTakingId();
        if(StringUtils.isNotNull(taking) && taking.getTakingId().longValue() != takingId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增库存盘点记录
     * 
     * @param wmStockTaking 库存盘点记录
     * @return 结果
     */
    @Override
    public int insertWmStockTaking(WmStockTaking wmStockTaking)
    {
        wmStockTaking.setCreateTime(DateUtils.getNowDate());
        return wmStockTakingMapper.insertWmStockTaking(wmStockTaking);
    }

    /**
     * 修改库存盘点记录
     * 
     * @param wmStockTaking 库存盘点记录
     * @return 结果
     */
    @Override
    public int updateWmStockTaking(WmStockTaking wmStockTaking)
    {
        wmStockTaking.setUpdateTime(DateUtils.getNowDate());
        return wmStockTakingMapper.updateWmStockTaking(wmStockTaking);
    }

    /**
     * 批量删除库存盘点记录
     * 
     * @param takingIds 需要删除的库存盘点记录主键
     * @return 结果
     */
    @Override
    public int deleteWmStockTakingByTakingIds(Long[] takingIds)
    {
        return wmStockTakingMapper.deleteWmStockTakingByTakingIds(takingIds);
    }

    /**
     * 删除库存盘点记录信息
     * 
     * @param takingId 库存盘点记录主键
     * @return 结果
     */
    @Override
    public int deleteWmStockTakingByTakingId(Long takingId)
    {
        return wmStockTakingMapper.deleteWmStockTakingByTakingId(takingId);
    }
}
