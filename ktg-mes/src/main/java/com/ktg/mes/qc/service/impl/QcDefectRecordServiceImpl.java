package com.ktg.mes.qc.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.qc.mapper.QcDefectRecordMapper;
import com.ktg.mes.qc.domain.QcDefectRecord;
import com.ktg.mes.qc.service.IQcDefectRecordService;

/**
 * 检验单缺陷记录Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-08-30
 */
@Service
public class QcDefectRecordServiceImpl implements IQcDefectRecordService 
{
    @Autowired
    private QcDefectRecordMapper qcDefectRecordMapper;

    /**
     * 查询检验单缺陷记录
     * 
     * @param recordId 检验单缺陷记录主键
     * @return 检验单缺陷记录
     */
    @Override
    public QcDefectRecord selectQcDefectRecordByRecordId(Long recordId)
    {
        return qcDefectRecordMapper.selectQcDefectRecordByRecordId(recordId);
    }

    /**
     * 查询检验单缺陷记录列表
     * 
     * @param qcDefectRecord 检验单缺陷记录
     * @return 检验单缺陷记录
     */
    @Override
    public List<QcDefectRecord> selectQcDefectRecordList(QcDefectRecord qcDefectRecord)
    {
        return qcDefectRecordMapper.selectQcDefectRecordList(qcDefectRecord);
    }

    /**
     * 新增检验单缺陷记录
     * 
     * @param qcDefectRecord 检验单缺陷记录
     * @return 结果
     */
    @Override
    public int insertQcDefectRecord(QcDefectRecord qcDefectRecord)
    {
        qcDefectRecord.setCreateTime(DateUtils.getNowDate());
        return qcDefectRecordMapper.insertQcDefectRecord(qcDefectRecord);
    }

    /**
     * 修改检验单缺陷记录
     * 
     * @param qcDefectRecord 检验单缺陷记录
     * @return 结果
     */
    @Override
    public int updateQcDefectRecord(QcDefectRecord qcDefectRecord)
    {
        qcDefectRecord.setUpdateTime(DateUtils.getNowDate());
        return qcDefectRecordMapper.updateQcDefectRecord(qcDefectRecord);
    }

    /**
     * 批量删除检验单缺陷记录
     * 
     * @param recordIds 需要删除的检验单缺陷记录主键
     * @return 结果
     */
    @Override
    public int deleteQcDefectRecordByRecordIds(Long[] recordIds)
    {
        return qcDefectRecordMapper.deleteQcDefectRecordByRecordIds(recordIds);
    }

    /**
     * 删除检验单缺陷记录信息
     * 
     * @param recordId 检验单缺陷记录主键
     * @return 结果
     */
    @Override
    public int deleteQcDefectRecordByRecordId(Long recordId)
    {
        return qcDefectRecordMapper.deleteQcDefectRecordByRecordId(recordId);
    }

    @Override
    public int deleteByQcIdAndType(QcDefectRecord qcDefectRecord) {
        return qcDefectRecordMapper.deleteByQcIdAndType(qcDefectRecord);
    }
}
