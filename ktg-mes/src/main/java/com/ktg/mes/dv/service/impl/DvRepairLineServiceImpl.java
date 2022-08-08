package com.ktg.mes.dv.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.dv.mapper.DvRepairLineMapper;
import com.ktg.mes.dv.domain.DvRepairLine;
import com.ktg.mes.dv.service.IDvRepairLineService;

/**
 * 设备维修单行Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-08-08
 */
@Service
public class DvRepairLineServiceImpl implements IDvRepairLineService 
{
    @Autowired
    private DvRepairLineMapper dvRepairLineMapper;

    /**
     * 查询设备维修单行
     * 
     * @param lineId 设备维修单行主键
     * @return 设备维修单行
     */
    @Override
    public DvRepairLine selectDvRepairLineByLineId(Long lineId)
    {
        return dvRepairLineMapper.selectDvRepairLineByLineId(lineId);
    }

    /**
     * 查询设备维修单行列表
     * 
     * @param dvRepairLine 设备维修单行
     * @return 设备维修单行
     */
    @Override
    public List<DvRepairLine> selectDvRepairLineList(DvRepairLine dvRepairLine)
    {
        return dvRepairLineMapper.selectDvRepairLineList(dvRepairLine);
    }

    /**
     * 新增设备维修单行
     * 
     * @param dvRepairLine 设备维修单行
     * @return 结果
     */
    @Override
    public int insertDvRepairLine(DvRepairLine dvRepairLine)
    {
        dvRepairLine.setCreateTime(DateUtils.getNowDate());
        return dvRepairLineMapper.insertDvRepairLine(dvRepairLine);
    }

    /**
     * 修改设备维修单行
     * 
     * @param dvRepairLine 设备维修单行
     * @return 结果
     */
    @Override
    public int updateDvRepairLine(DvRepairLine dvRepairLine)
    {
        dvRepairLine.setUpdateTime(DateUtils.getNowDate());
        return dvRepairLineMapper.updateDvRepairLine(dvRepairLine);
    }

    /**
     * 批量删除设备维修单行
     * 
     * @param lineIds 需要删除的设备维修单行主键
     * @return 结果
     */
    @Override
    public int deleteDvRepairLineByLineIds(Long[] lineIds)
    {
        return dvRepairLineMapper.deleteDvRepairLineByLineIds(lineIds);
    }

    /**
     * 删除设备维修单行信息
     * 
     * @param lineId 设备维修单行主键
     * @return 结果
     */
    @Override
    public int deleteDvRepairLineByLineId(Long lineId)
    {
        return dvRepairLineMapper.deleteDvRepairLineByLineId(lineId);
    }

    @Override
    public int deleteByRepairId(Long repairId) {
        return dvRepairLineMapper.deleteByRepairId(repairId);
    }
}
