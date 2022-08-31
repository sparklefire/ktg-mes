package com.ktg.mes.qc.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.qc.mapper.QcIpqcLineMapper;
import com.ktg.mes.qc.domain.QcIpqcLine;
import com.ktg.mes.qc.service.IQcIpqcLineService;

/**
 * 过程检验单行Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-08-30
 */
@Service
public class QcIpqcLineServiceImpl implements IQcIpqcLineService 
{
    @Autowired
    private QcIpqcLineMapper qcIpqcLineMapper;

    /**
     * 查询过程检验单行
     * 
     * @param lineId 过程检验单行主键
     * @return 过程检验单行
     */
    @Override
    public QcIpqcLine selectQcIpqcLineByLineId(Long lineId)
    {
        return qcIpqcLineMapper.selectQcIpqcLineByLineId(lineId);
    }

    /**
     * 查询过程检验单行列表
     * 
     * @param qcIpqcLine 过程检验单行
     * @return 过程检验单行
     */
    @Override
    public List<QcIpqcLine> selectQcIpqcLineList(QcIpqcLine qcIpqcLine)
    {
        return qcIpqcLineMapper.selectQcIpqcLineList(qcIpqcLine);
    }

    /**
     * 新增过程检验单行
     * 
     * @param qcIpqcLine 过程检验单行
     * @return 结果
     */
    @Override
    public int insertQcIpqcLine(QcIpqcLine qcIpqcLine)
    {
        qcIpqcLine.setCreateTime(DateUtils.getNowDate());
        return qcIpqcLineMapper.insertQcIpqcLine(qcIpqcLine);
    }

    /**
     * 修改过程检验单行
     * 
     * @param qcIpqcLine 过程检验单行
     * @return 结果
     */
    @Override
    public int updateQcIpqcLine(QcIpqcLine qcIpqcLine)
    {
        qcIpqcLine.setUpdateTime(DateUtils.getNowDate());
        return qcIpqcLineMapper.updateQcIpqcLine(qcIpqcLine);
    }

    @Override
    public int updateCrMajMinQuantity(Long qcId, Long lineId) {
        QcIpqcLine line = new QcIpqcLine();
        line.setIpqcId(qcId);
        line.setLineId(lineId);
        return qcIpqcLineMapper.updateCrMajMinQuantity(line);
    }

    /**
     * 批量删除过程检验单行
     * 
     * @param lineIds 需要删除的过程检验单行主键
     * @return 结果
     */
    @Override
    public int deleteQcIpqcLineByLineIds(Long[] lineIds)
    {
        return qcIpqcLineMapper.deleteQcIpqcLineByLineIds(lineIds);
    }

    /**
     * 删除过程检验单行信息
     * 
     * @param lineId 过程检验单行主键
     * @return 结果
     */
    @Override
    public int deleteQcIpqcLineByLineId(Long lineId)
    {
        return qcIpqcLineMapper.deleteQcIpqcLineByLineId(lineId);
    }

    @Override
    public int deleteByIpqcId(Long ipqcId) {
        return qcIpqcLineMapper.deleteByIpqcId(ipqcId);
    }
}
