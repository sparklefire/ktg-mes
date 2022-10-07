package com.ktg.mes.wm.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.wm.mapper.WmRtSalseLineMapper;
import com.ktg.mes.wm.domain.WmRtSalseLine;
import com.ktg.mes.wm.service.IWmRtSalseLineService;

/**
 * 产品销售退货行Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-10-06
 */
@Service
public class WmRtSalseLineServiceImpl implements IWmRtSalseLineService 
{
    @Autowired
    private WmRtSalseLineMapper wmRtSalseLineMapper;

    /**
     * 查询产品销售退货行
     * 
     * @param lineId 产品销售退货行主键
     * @return 产品销售退货行
     */
    @Override
    public WmRtSalseLine selectWmRtSalseLineByLineId(Long lineId)
    {
        return wmRtSalseLineMapper.selectWmRtSalseLineByLineId(lineId);
    }

    /**
     * 查询产品销售退货行列表
     * 
     * @param wmRtSalseLine 产品销售退货行
     * @return 产品销售退货行
     */
    @Override
    public List<WmRtSalseLine> selectWmRtSalseLineList(WmRtSalseLine wmRtSalseLine)
    {
        return wmRtSalseLineMapper.selectWmRtSalseLineList(wmRtSalseLine);
    }

    /**
     * 新增产品销售退货行
     * 
     * @param wmRtSalseLine 产品销售退货行
     * @return 结果
     */
    @Override
    public int insertWmRtSalseLine(WmRtSalseLine wmRtSalseLine)
    {
        wmRtSalseLine.setCreateTime(DateUtils.getNowDate());
        return wmRtSalseLineMapper.insertWmRtSalseLine(wmRtSalseLine);
    }

    /**
     * 修改产品销售退货行
     * 
     * @param wmRtSalseLine 产品销售退货行
     * @return 结果
     */
    @Override
    public int updateWmRtSalseLine(WmRtSalseLine wmRtSalseLine)
    {
        wmRtSalseLine.setUpdateTime(DateUtils.getNowDate());
        return wmRtSalseLineMapper.updateWmRtSalseLine(wmRtSalseLine);
    }

    /**
     * 批量删除产品销售退货行
     * 
     * @param lineIds 需要删除的产品销售退货行主键
     * @return 结果
     */
    @Override
    public int deleteWmRtSalseLineByLineIds(Long[] lineIds)
    {
        return wmRtSalseLineMapper.deleteWmRtSalseLineByLineIds(lineIds);
    }

    /**
     * 删除产品销售退货行信息
     * 
     * @param lineId 产品销售退货行主键
     * @return 结果
     */
    @Override
    public int deleteWmRtSalseLineByLineId(Long lineId)
    {
        return wmRtSalseLineMapper.deleteWmRtSalseLineByLineId(lineId);
    }

    @Override
    public int deleteByRtId(Long rtId) {
        return wmRtSalseLineMapper.deleteByRtId(rtId);
    }
}
