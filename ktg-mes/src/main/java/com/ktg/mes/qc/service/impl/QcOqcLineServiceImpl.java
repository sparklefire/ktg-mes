package com.ktg.mes.qc.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.qc.mapper.QcOqcLineMapper;
import com.ktg.mes.qc.domain.QcOqcLine;
import com.ktg.mes.qc.service.IQcOqcLineService;

/**
 * 出货检验单行Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-09-01
 */
@Service
public class QcOqcLineServiceImpl implements IQcOqcLineService 
{
    @Autowired
    private QcOqcLineMapper qcOqcLineMapper;

    /**
     * 查询出货检验单行
     * 
     * @param lineId 出货检验单行主键
     * @return 出货检验单行
     */
    @Override
    public QcOqcLine selectQcOqcLineByLineId(Long lineId)
    {
        return qcOqcLineMapper.selectQcOqcLineByLineId(lineId);
    }

    /**
     * 查询出货检验单行列表
     * 
     * @param qcOqcLine 出货检验单行
     * @return 出货检验单行
     */
    @Override
    public List<QcOqcLine> selectQcOqcLineList(QcOqcLine qcOqcLine)
    {
        return qcOqcLineMapper.selectQcOqcLineList(qcOqcLine);
    }

    /**
     * 新增出货检验单行
     * 
     * @param qcOqcLine 出货检验单行
     * @return 结果
     */
    @Override
    public int insertQcOqcLine(QcOqcLine qcOqcLine)
    {
        qcOqcLine.setCreateTime(DateUtils.getNowDate());
        return qcOqcLineMapper.insertQcOqcLine(qcOqcLine);
    }

    /**
     * 修改出货检验单行
     * 
     * @param qcOqcLine 出货检验单行
     * @return 结果
     */
    @Override
    public int updateQcOqcLine(QcOqcLine qcOqcLine)
    {
        qcOqcLine.setUpdateTime(DateUtils.getNowDate());
        return qcOqcLineMapper.updateQcOqcLine(qcOqcLine);
    }

    @Override
    public int updateCrMajMinQuantity(Long qcId, Long lineId) {
        QcOqcLine line = new QcOqcLine();
        line.setLineId(lineId);
        line.setOqcId(qcId);
        return qcOqcLineMapper.updateCrMajMinQuantity(line);
    }

    /**
     * 批量删除出货检验单行
     * 
     * @param lineIds 需要删除的出货检验单行主键
     * @return 结果
     */
    @Override
    public int deleteQcOqcLineByLineIds(Long[] lineIds)
    {
        return qcOqcLineMapper.deleteQcOqcLineByLineIds(lineIds);
    }

    /**
     * 删除出货检验单行信息
     * 
     * @param lineId 出货检验单行主键
     * @return 结果
     */
    @Override
    public int deleteQcOqcLineByLineId(Long lineId)
    {
        return qcOqcLineMapper.deleteQcOqcLineByLineId(lineId);
    }

    @Override
    public int deleteByOqcId(Long oqcId) {
        return qcOqcLineMapper.deleteByOqcId(oqcId);
    }
}
