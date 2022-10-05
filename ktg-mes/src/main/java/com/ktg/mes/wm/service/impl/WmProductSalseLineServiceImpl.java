package com.ktg.mes.wm.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.wm.mapper.WmProductSalseLineMapper;
import com.ktg.mes.wm.domain.WmProductSalseLine;
import com.ktg.mes.wm.service.IWmProductSalseLineService;

/**
 * 产品销售出库行Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-10-05
 */
@Service
public class WmProductSalseLineServiceImpl implements IWmProductSalseLineService 
{
    @Autowired
    private WmProductSalseLineMapper wmProductSalseLineMapper;

    /**
     * 查询产品销售出库行
     * 
     * @param lineId 产品销售出库行主键
     * @return 产品销售出库行
     */
    @Override
    public WmProductSalseLine selectWmProductSalseLineByLineId(Long lineId)
    {
        return wmProductSalseLineMapper.selectWmProductSalseLineByLineId(lineId);
    }

    /**
     * 查询产品销售出库行列表
     * 
     * @param wmProductSalseLine 产品销售出库行
     * @return 产品销售出库行
     */
    @Override
    public List<WmProductSalseLine> selectWmProductSalseLineList(WmProductSalseLine wmProductSalseLine)
    {
        return wmProductSalseLineMapper.selectWmProductSalseLineList(wmProductSalseLine);
    }

    /**
     * 新增产品销售出库行
     * 
     * @param wmProductSalseLine 产品销售出库行
     * @return 结果
     */
    @Override
    public int insertWmProductSalseLine(WmProductSalseLine wmProductSalseLine)
    {
        wmProductSalseLine.setCreateTime(DateUtils.getNowDate());
        return wmProductSalseLineMapper.insertWmProductSalseLine(wmProductSalseLine);
    }

    /**
     * 修改产品销售出库行
     * 
     * @param wmProductSalseLine 产品销售出库行
     * @return 结果
     */
    @Override
    public int updateWmProductSalseLine(WmProductSalseLine wmProductSalseLine)
    {
        wmProductSalseLine.setUpdateTime(DateUtils.getNowDate());
        return wmProductSalseLineMapper.updateWmProductSalseLine(wmProductSalseLine);
    }

    /**
     * 批量删除产品销售出库行
     * 
     * @param lineIds 需要删除的产品销售出库行主键
     * @return 结果
     */
    @Override
    public int deleteWmProductSalseLineByLineIds(Long[] lineIds)
    {
        return wmProductSalseLineMapper.deleteWmProductSalseLineByLineIds(lineIds);
    }

    /**
     * 删除产品销售出库行信息
     * 
     * @param lineId 产品销售出库行主键
     * @return 结果
     */
    @Override
    public int deleteWmProductSalseLineByLineId(Long lineId)
    {
        return wmProductSalseLineMapper.deleteWmProductSalseLineByLineId(lineId);
    }

    @Override
    public int deleteBySalseId(Long salseId) {
        return wmProductSalseLineMapper.deleteBySalseId(salseId);
    }
}
