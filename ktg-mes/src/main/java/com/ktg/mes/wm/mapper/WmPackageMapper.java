package com.ktg.mes.wm.mapper;

import java.util.List;
import com.ktg.mes.wm.domain.WmPackage;

/**
 * 装箱单Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-10-10
 */
public interface WmPackageMapper 
{
    /**
     * 查询装箱单
     * 
     * @param packageId 装箱单主键
     * @return 装箱单
     */
    public WmPackage selectWmPackageByPackageId(Long packageId);

    /**
     * 查询装箱单列表
     * 
     * @param wmPackage 装箱单
     * @return 装箱单集合
     */
    public List<WmPackage> selectWmPackageList(WmPackage wmPackage);

    /**
     * 检查装箱单编号是否唯一
     * @param wmPackage
     * @return
     */
    public WmPackage checkPackgeCodeUnique(WmPackage wmPackage);

    /**
     * 新增装箱单
     * 
     * @param wmPackage 装箱单
     * @return 结果
     */
    public int insertWmPackage(WmPackage wmPackage);

    /**
     * 修改装箱单
     * 
     * @param wmPackage 装箱单
     * @return 结果
     */
    public int updateWmPackage(WmPackage wmPackage);

    /**
     * 删除装箱单
     * 
     * @param packageId 装箱单主键
     * @return 结果
     */
    public int deleteWmPackageByPackageId(Long packageId);

    /**
     * 批量删除装箱单
     * 
     * @param packageIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWmPackageByPackageIds(Long[] packageIds);
}
