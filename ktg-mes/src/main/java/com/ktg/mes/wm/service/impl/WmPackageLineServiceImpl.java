package com.ktg.mes.wm.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.wm.mapper.WmPackageLineMapper;
import com.ktg.mes.wm.domain.WmPackageLine;
import com.ktg.mes.wm.service.IWmPackageLineService;

/**
 * 装箱明细Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-10-11
 */
@Service
public class WmPackageLineServiceImpl implements IWmPackageLineService 
{
    @Autowired
    private WmPackageLineMapper wmPackageLineMapper;

    /**
     * 查询装箱明细
     * 
     * @param lineId 装箱明细主键
     * @return 装箱明细
     */
    @Override
    public WmPackageLine selectWmPackageLineByLineId(Long lineId)
    {
        return wmPackageLineMapper.selectWmPackageLineByLineId(lineId);
    }

    /**
     * 查询装箱明细列表
     * 
     * @param wmPackageLine 装箱明细
     * @return 装箱明细
     */
    @Override
    public List<WmPackageLine> selectWmPackageLineList(WmPackageLine wmPackageLine)
    {
        return wmPackageLineMapper.selectWmPackageLineList(wmPackageLine);
    }

    /**
     * 新增装箱明细
     * 
     * @param wmPackageLine 装箱明细
     * @return 结果
     */
    @Override
    public int insertWmPackageLine(WmPackageLine wmPackageLine)
    {
        wmPackageLine.setCreateTime(DateUtils.getNowDate());
        return wmPackageLineMapper.insertWmPackageLine(wmPackageLine);
    }

    /**
     * 修改装箱明细
     * 
     * @param wmPackageLine 装箱明细
     * @return 结果
     */
    @Override
    public int updateWmPackageLine(WmPackageLine wmPackageLine)
    {
        wmPackageLine.setUpdateTime(DateUtils.getNowDate());
        return wmPackageLineMapper.updateWmPackageLine(wmPackageLine);
    }

    /**
     * 批量删除装箱明细
     * 
     * @param lineIds 需要删除的装箱明细主键
     * @return 结果
     */
    @Override
    public int deleteWmPackageLineByLineIds(Long[] lineIds)
    {
        return wmPackageLineMapper.deleteWmPackageLineByLineIds(lineIds);
    }

    /**
     * 删除装箱明细信息
     * 
     * @param lineId 装箱明细主键
     * @return 结果
     */
    @Override
    public int deleteWmPackageLineByLineId(Long lineId)
    {
        return wmPackageLineMapper.deleteWmPackageLineByLineId(lineId);
    }
}
