package com.ktg.mes.wm.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.wm.mapper.WmOutsourceRecptLineMapper;
import com.ktg.mes.wm.domain.WmOutsourceRecptLine;
import com.ktg.mes.wm.service.IWmOutsourceRecptLineService;

/**
 * 外协入库单行Service业务层处理
 * 
 * @author yinjinlu
 * @date 2023-10-30
 */
@Service
public class WmOutsourceRecptLineServiceImpl implements IWmOutsourceRecptLineService 
{
    @Autowired
    private WmOutsourceRecptLineMapper wmOutsourceRecptLineMapper;

    /**
     * 查询外协入库单行
     * 
     * @param lineId 外协入库单行主键
     * @return 外协入库单行
     */
    @Override
    public WmOutsourceRecptLine selectWmOutsourceRecptLineByLineId(Long lineId)
    {
        return wmOutsourceRecptLineMapper.selectWmOutsourceRecptLineByLineId(lineId);
    }

    /**
     * 查询外协入库单行列表
     * 
     * @param wmOutsourceRecptLine 外协入库单行
     * @return 外协入库单行
     */
    @Override
    public List<WmOutsourceRecptLine> selectWmOutsourceRecptLineList(WmOutsourceRecptLine wmOutsourceRecptLine)
    {
        return wmOutsourceRecptLineMapper.selectWmOutsourceRecptLineList(wmOutsourceRecptLine);
    }

    @Override
    public List<WmOutsourceRecptLine> selectWmOutsourceRecptLineByRecptId(Long recptId) {
        return wmOutsourceRecptLineMapper.selectWmOutsourceRecptLineByRecptId(recptId);
    }

    /**
     * 新增外协入库单行
     * 
     * @param wmOutsourceRecptLine 外协入库单行
     * @return 结果
     */
    @Override
    public int insertWmOutsourceRecptLine(WmOutsourceRecptLine wmOutsourceRecptLine)
    {
        wmOutsourceRecptLine.setCreateTime(DateUtils.getNowDate());
        return wmOutsourceRecptLineMapper.insertWmOutsourceRecptLine(wmOutsourceRecptLine);
    }

    /**
     * 修改外协入库单行
     * 
     * @param wmOutsourceRecptLine 外协入库单行
     * @return 结果
     */
    @Override
    public int updateWmOutsourceRecptLine(WmOutsourceRecptLine wmOutsourceRecptLine)
    {
        wmOutsourceRecptLine.setUpdateTime(DateUtils.getNowDate());
        return wmOutsourceRecptLineMapper.updateWmOutsourceRecptLine(wmOutsourceRecptLine);
    }

    /**
     * 批量删除外协入库单行
     * 
     * @param lineIds 需要删除的外协入库单行主键
     * @return 结果
     */
    @Override
    public int deleteWmOutsourceRecptLineByLineIds(Long[] lineIds)
    {
        return wmOutsourceRecptLineMapper.deleteWmOutsourceRecptLineByLineIds(lineIds);
    }

    /**
     * 删除外协入库单行信息
     * 
     * @param lineId 外协入库单行主键
     * @return 结果
     */
    @Override
    public int deleteWmOutsourceRecptLineByLineId(Long lineId)
    {
        return wmOutsourceRecptLineMapper.deleteWmOutsourceRecptLineByLineId(lineId);
    }

    @Override
    public int deleteWmOutsourceRecptLineByRecptId(Long recptId) {
        return wmOutsourceRecptLineMapper.deleteWmOutsourceRecptLineByRecptId(recptId);
    }
}
