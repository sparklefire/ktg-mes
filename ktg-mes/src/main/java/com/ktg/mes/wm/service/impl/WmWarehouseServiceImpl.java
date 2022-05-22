package com.ktg.mes.wm.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.wm.mapper.WmWarehouseMapper;
import com.ktg.mes.wm.domain.WmWarehouse;
import com.ktg.mes.wm.service.IWmWarehouseService;

/**
 * 仓库设置Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-07
 */
@Service
public class WmWarehouseServiceImpl implements IWmWarehouseService 
{
    @Autowired
    private WmWarehouseMapper wmWarehouseMapper;

    /**
     * 查询仓库设置
     * 
     * @param warehouseId 仓库设置主键
     * @return 仓库设置
     */
    @Override
    public WmWarehouse selectWmWarehouseByWarehouseId(Long warehouseId)
    {
        return wmWarehouseMapper.selectWmWarehouseByWarehouseId(warehouseId);
    }

    /**
     * 查询仓库设置列表
     * 
     * @param wmWarehouse 仓库设置
     * @return 仓库设置
     */
    @Override
    public List<WmWarehouse> selectWmWarehouseList(WmWarehouse wmWarehouse)
    {
        return wmWarehouseMapper.selectWmWarehouseList(wmWarehouse);
    }

    @Override
    public List<WmWarehouse> getTreeList() {
        return wmWarehouseMapper.getTreeList();
    }

    @Override
    public String checkWarehouseCodeUnique(WmWarehouse wmWarehouse) {
        WmWarehouse warehouse = wmWarehouseMapper.checkWarehouseCodeUnique(wmWarehouse);
        Long warehouseId = wmWarehouse.getWarehouseId()==null?-1L:wmWarehouse.getWarehouseId();
        if(StringUtils.isNotNull(warehouse) && warehouse.getWarehouseId().longValue() != warehouseId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkWarehouseNameUnique(WmWarehouse wmWarehouse) {
        WmWarehouse warehouse = wmWarehouseMapper.checkWarehouseNameUnique(wmWarehouse);
        Long warehouseId = wmWarehouse.getWarehouseId()==null?-1L:wmWarehouse.getWarehouseId();
        if(StringUtils.isNotNull(warehouse) && warehouse.getWarehouseId().longValue() != warehouseId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增仓库设置
     * 
     * @param wmWarehouse 仓库设置
     * @return 结果
     */
    @Override
    public int insertWmWarehouse(WmWarehouse wmWarehouse)
    {
        wmWarehouse.setCreateTime(DateUtils.getNowDate());
        return wmWarehouseMapper.insertWmWarehouse(wmWarehouse);
    }

    /**
     * 修改仓库设置
     * 
     * @param wmWarehouse 仓库设置
     * @return 结果
     */
    @Override
    public int updateWmWarehouse(WmWarehouse wmWarehouse)
    {
        wmWarehouse.setUpdateTime(DateUtils.getNowDate());
        return wmWarehouseMapper.updateWmWarehouse(wmWarehouse);
    }

    /**
     * 批量删除仓库设置
     * 
     * @param warehouseIds 需要删除的仓库设置主键
     * @return 结果
     */
    @Override
    public int deleteWmWarehouseByWarehouseIds(Long[] warehouseIds)
    {
        return wmWarehouseMapper.deleteWmWarehouseByWarehouseIds(warehouseIds);
    }

    /**
     * 删除仓库设置信息
     * 
     * @param warehouseId 仓库设置主键
     * @return 结果
     */
    @Override
    public int deleteWmWarehouseByWarehouseId(Long warehouseId)
    {
        return wmWarehouseMapper.deleteWmWarehouseByWarehouseId(warehouseId);
    }
}
