package com.ktg.mes.qc.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.qc.mapper.QcIqcDefectMapper;
import com.ktg.mes.qc.domain.QcIqcDefect;
import com.ktg.mes.qc.service.IQcIqcDefectService;

/**
 * 来料检验单缺陷记录Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-19
 */
@Service
public class QcIqcDefectServiceImpl implements IQcIqcDefectService 
{
    @Autowired
    private QcIqcDefectMapper qcIqcDefectMapper;

    /**
     * 查询来料检验单缺陷记录
     * 
     * @param recordId 来料检验单缺陷记录主键
     * @return 来料检验单缺陷记录
     */
    @Override
    public QcIqcDefect selectQcIqcDefectByRecordId(Long recordId)
    {
        return qcIqcDefectMapper.selectQcIqcDefectByRecordId(recordId);
    }

    /**
     * 查询来料检验单缺陷记录列表
     * 
     * @param qcIqcDefect 来料检验单缺陷记录
     * @return 来料检验单缺陷记录
     */
    @Override
    public List<QcIqcDefect> selectQcIqcDefectList(QcIqcDefect qcIqcDefect)
    {
        return qcIqcDefectMapper.selectQcIqcDefectList(qcIqcDefect);
    }

    /**
     * 新增来料检验单缺陷记录
     * 
     * @param qcIqcDefect 来料检验单缺陷记录
     * @return 结果
     */
    @Override
    public int insertQcIqcDefect(QcIqcDefect qcIqcDefect)
    {
        qcIqcDefect.setCreateTime(DateUtils.getNowDate());
        return qcIqcDefectMapper.insertQcIqcDefect(qcIqcDefect);
    }

    /**
     * 修改来料检验单缺陷记录
     * 
     * @param qcIqcDefect 来料检验单缺陷记录
     * @return 结果
     */
    @Override
    public int updateQcIqcDefect(QcIqcDefect qcIqcDefect)
    {
        qcIqcDefect.setUpdateTime(DateUtils.getNowDate());
        return qcIqcDefectMapper.updateQcIqcDefect(qcIqcDefect);
    }

    /**
     * 批量删除来料检验单缺陷记录
     * 
     * @param recordIds 需要删除的来料检验单缺陷记录主键
     * @return 结果
     */
    @Override
    public int deleteQcIqcDefectByRecordIds(Long[] recordIds)
    {
        return qcIqcDefectMapper.deleteQcIqcDefectByRecordIds(recordIds);
    }

    @Override
    public int deleteByIqcId(Long iqcID) {
        return qcIqcDefectMapper.deleteByIqcId(iqcID);
    }

    @Override
    public int deleteByIqcIdLineId(QcIqcDefect qcIqcDefect) {
        return qcIqcDefectMapper.deleteByIqcIdLineId(qcIqcDefect);
    }

    /**
     * 删除来料检验单缺陷记录信息
     * 
     * @param recordId 来料检验单缺陷记录主键
     * @return 结果
     */
    @Override
    public int deleteQcIqcDefectByRecordId(Long recordId)
    {
        return qcIqcDefectMapper.deleteQcIqcDefectByRecordId(recordId);
    }
}
