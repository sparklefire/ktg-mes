package com.ktg.mes.wm.service;

import java.util.List;
import com.ktg.mes.wm.domain.WmStorageLocation;

/**
 * 库区设置Service接口
 * 
 * @author yinjinlu
 * @date 2022-05-07
 */
public interface IWmStorageLocationService 
{
    /**
     * 查询库区设置
     * 
     * @param locationId 库区设置主键
     * @return 库区设置
     */
    public WmStorageLocation selectWmStorageLocationByLocationId(Long locationId);


    /**
     * 根据库区编码查询库区
     * @param locationCode
     * @return
     */
    public WmStorageLocation selectWmStorageLocationByLocationCode(String locationCode);


    /**
     * 查询库区设置列表
     * 
     * @param wmStorageLocation 库区设置
     * @return 库区设置集合
     */
    public List<WmStorageLocation> selectWmStorageLocationList(WmStorageLocation wmStorageLocation);


    /**
     * 检查库区编码是否唯一
     * @param wmStorageLocation
     * @return
     */
    public String checkLocationCodeUnique(WmStorageLocation wmStorageLocation);

    /**
     * 检查库区名称是否唯一
     * @param wmStorageLocation
     * @return
     */
    public String checkLocationNameUnique(WmStorageLocation wmStorageLocation);

    /**
     * 新增库区设置
     * 
     * @param wmStorageLocation 库区设置
     * @return 结果
     */
    public int insertWmStorageLocation(WmStorageLocation wmStorageLocation);

    /**
     * 修改库区设置
     * 
     * @param wmStorageLocation 库区设置
     * @return 结果
     */
    public int updateWmStorageLocation(WmStorageLocation wmStorageLocation);

    /**
     * 批量删除库区设置
     * 
     * @param locationIds 需要删除的库区设置主键集合
     * @return 结果
     */
    public int deleteWmStorageLocationByLocationIds(Long[] locationIds);

    /**
     * 删除库区设置信息
     * 
     * @param locationId 库区设置主键
     * @return 结果
     */
    public int deleteWmStorageLocationByLocationId(Long locationId);

    /**
     * 根据仓库删除对应的库区
     * @param warehouseId
     * @return
     */
    public int deleteByWarehouseId(Long warehouseId);
}
