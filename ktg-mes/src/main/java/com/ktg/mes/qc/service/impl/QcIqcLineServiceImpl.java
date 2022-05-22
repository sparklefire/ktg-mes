package com.ktg.mes.qc.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.qc.mapper.QcIqcLineMapper;
import com.ktg.mes.qc.domain.QcIqcLine;
import com.ktg.mes.qc.service.IQcIqcLineService;

/**
 * 来料检验单行Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-19
 */
@Service
public class QcIqcLineServiceImpl implements IQcIqcLineService 
{
    @Autowired
    private QcIqcLineMapper qcIqcLineMapper;

    /**
     * 查询来料检验单行
     * 
     * @param lineId 来料检验单行主键
     * @return 来料检验单行
     */
    @Override
    public QcIqcLine selectQcIqcLineByLineId(Long lineId)
    {
        return qcIqcLineMapper.selectQcIqcLineByLineId(lineId);
    }

    /**
     * 查询来料检验单行列表
     * 
     * @param qcIqcLine 来料检验单行
     * @return 来料检验单行
     */
    @Override
    public List<QcIqcLine> selectQcIqcLineList(QcIqcLine qcIqcLine)
    {
        return qcIqcLineMapper.selectQcIqcLineList(qcIqcLine);
    }

    /**
     * 新增来料检验单行
     * 
     * @param qcIqcLine 来料检验单行
     * @return 结果
     */
    @Override
    public int insertQcIqcLine(QcIqcLine qcIqcLine)
    {
        qcIqcLine.setCreateTime(DateUtils.getNowDate());
        return qcIqcLineMapper.insertQcIqcLine(qcIqcLine);
    }

    /**
     * 修改来料检验单行
     * 
     * @param qcIqcLine 来料检验单行
     * @return 结果
     */
    @Override
    public int updateQcIqcLine(QcIqcLine qcIqcLine)
    {
        qcIqcLine.setUpdateTime(DateUtils.getNowDate());
        return qcIqcLineMapper.updateQcIqcLine(qcIqcLine);
    }

    @Override
    public int updateCrMajMinQuantity(Long iqcId,Long lineId) {
        QcIqcLine qcIqcLine = new QcIqcLine();
        qcIqcLine.setIqcId(iqcId);
        qcIqcLine.setLineId(lineId);
        return qcIqcLineMapper.updateCrMajMinQuantity(qcIqcLine);
    }

    /**
     * 批量删除来料检验单行
     * 
     * @param lineIds 需要删除的来料检验单行主键
     * @return 结果
     */
    @Override
    public int deleteQcIqcLineByLineIds(Long[] lineIds)
    {
        return qcIqcLineMapper.deleteQcIqcLineByLineIds(lineIds);
    }

    @Override
    public int deleteByIqcId(Long iqcId) {
        return qcIqcLineMapper.deleteByIqcId(iqcId);
    }

    /**
     * 删除来料检验单行信息
     * 
     * @param lineId 来料检验单行主键
     * @return 结果
     */
    @Override
    public int deleteQcIqcLineByLineId(Long lineId)
    {
        return qcIqcLineMapper.deleteQcIqcLineByLineId(lineId);
    }
}
