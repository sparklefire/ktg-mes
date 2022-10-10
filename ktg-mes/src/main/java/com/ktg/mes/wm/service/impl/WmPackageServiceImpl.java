package com.ktg.mes.wm.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.wm.mapper.WmPackageMapper;
import com.ktg.mes.wm.domain.WmPackage;
import com.ktg.mes.wm.service.IWmPackageService;

/**
 * 装箱单Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-10-10
 */
@Service
public class WmPackageServiceImpl implements IWmPackageService 
{
    @Autowired
    private WmPackageMapper wmPackageMapper;

    /**
     * 查询装箱单
     * 
     * @param packageId 装箱单主键
     * @return 装箱单
     */
    @Override
    public WmPackage selectWmPackageByPackageId(Long packageId)
    {
        return wmPackageMapper.selectWmPackageByPackageId(packageId);
    }

    /**
     * 查询装箱单列表
     * 
     * @param wmPackage 装箱单
     * @return 装箱单
     */
    @Override
    public List<WmPackage> selectWmPackageList(WmPackage wmPackage)
    {
        return wmPackageMapper.selectWmPackageList(wmPackage);
    }

    @Override
    public String checkPackgeCodeUnique(WmPackage wmPackage) {
        WmPackage pack = wmPackageMapper.checkPackgeCodeUnique(wmPackage);
        Long packgeId = wmPackage.getPackageId() ==null?-1L:wmPackage.getPackageId();
        if(StringUtils.isNotNull(pack) && packgeId.longValue()!=pack.getPackageId()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增装箱单
     * 
     * @param wmPackage 装箱单
     * @return 结果
     */
    @Override
    public int insertWmPackage(WmPackage wmPackage)
    {
        wmPackage.setCreateTime(DateUtils.getNowDate());
        return wmPackageMapper.insertWmPackage(wmPackage);
    }

    /**
     * 修改装箱单
     * 
     * @param wmPackage 装箱单
     * @return 结果
     */
    @Override
    public int updateWmPackage(WmPackage wmPackage)
    {
        wmPackage.setUpdateTime(DateUtils.getNowDate());
        return wmPackageMapper.updateWmPackage(wmPackage);
    }

    /**
     * 批量删除装箱单
     * 
     * @param packageIds 需要删除的装箱单主键
     * @return 结果
     */
    @Override
    public int deleteWmPackageByPackageIds(Long[] packageIds)
    {
        return wmPackageMapper.deleteWmPackageByPackageIds(packageIds);
    }

    /**
     * 删除装箱单信息
     * 
     * @param packageId 装箱单主键
     * @return 结果
     */
    @Override
    public int deleteWmPackageByPackageId(Long packageId)
    {
        return wmPackageMapper.deleteWmPackageByPackageId(packageId);
    }
}
