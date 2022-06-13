package com.ktg.mes.wm.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.wm.mapper.WmRtVendorLineMapper;
import com.ktg.mes.wm.domain.WmRtVendorLine;
import com.ktg.mes.wm.service.IWmRtVendorLineService;

/**
 * 供应商退货行Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-06-13
 */
@Service
public class WmRtVendorLineServiceImpl implements IWmRtVendorLineService 
{
    @Autowired
    private WmRtVendorLineMapper wmRtVendorLineMapper;

    /**
     * 查询供应商退货行
     * 
     * @param lineId 供应商退货行主键
     * @return 供应商退货行
     */
    @Override
    public WmRtVendorLine selectWmRtVendorLineByLineId(Long lineId)
    {
        return wmRtVendorLineMapper.selectWmRtVendorLineByLineId(lineId);
    }

    /**
     * 查询供应商退货行列表
     * 
     * @param wmRtVendorLine 供应商退货行
     * @return 供应商退货行
     */
    @Override
    public List<WmRtVendorLine> selectWmRtVendorLineList(WmRtVendorLine wmRtVendorLine)
    {
        return wmRtVendorLineMapper.selectWmRtVendorLineList(wmRtVendorLine);
    }

    /**
     * 新增供应商退货行
     * 
     * @param wmRtVendorLine 供应商退货行
     * @return 结果
     */
    @Override
    public int insertWmRtVendorLine(WmRtVendorLine wmRtVendorLine)
    {
        wmRtVendorLine.setCreateTime(DateUtils.getNowDate());
        return wmRtVendorLineMapper.insertWmRtVendorLine(wmRtVendorLine);
    }

    /**
     * 修改供应商退货行
     * 
     * @param wmRtVendorLine 供应商退货行
     * @return 结果
     */
    @Override
    public int updateWmRtVendorLine(WmRtVendorLine wmRtVendorLine)
    {
        wmRtVendorLine.setUpdateTime(DateUtils.getNowDate());
        return wmRtVendorLineMapper.updateWmRtVendorLine(wmRtVendorLine);
    }

    /**
     * 批量删除供应商退货行
     * 
     * @param lineIds 需要删除的供应商退货行主键
     * @return 结果
     */
    @Override
    public int deleteWmRtVendorLineByLineIds(Long[] lineIds)
    {
        return wmRtVendorLineMapper.deleteWmRtVendorLineByLineIds(lineIds);
    }

    /**
     * 删除供应商退货行信息
     * 
     * @param lineId 供应商退货行主键
     * @return 结果
     */
    @Override
    public int deleteWmRtVendorLineByLineId(Long lineId)
    {
        return wmRtVendorLineMapper.deleteWmRtVendorLineByLineId(lineId);
    }

    @Override
    public int deleteByRtId(Long rtId) {
        return wmRtVendorLineMapper.deleteByRtId(rtId);
    }
}
