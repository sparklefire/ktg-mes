package com.ktg.mes.qc.mapper;

import java.util.List;
import com.ktg.mes.qc.domain.QcDefectRecord;

/**
 * 检验单缺陷记录Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-08-30
 */
public interface QcDefectRecordMapper 
{
    /**
     * 查询检验单缺陷记录
     * 
     * @param recordId 检验单缺陷记录主键
     * @return 检验单缺陷记录
     */
    public QcDefectRecord selectQcDefectRecordByRecordId(Long recordId);

    /**
     * 查询检验单缺陷记录列表
     * 
     * @param qcDefectRecord 检验单缺陷记录
     * @return 检验单缺陷记录集合
     */
    public List<QcDefectRecord> selectQcDefectRecordList(QcDefectRecord qcDefectRecord);

    /**
     * 新增检验单缺陷记录
     * 
     * @param qcDefectRecord 检验单缺陷记录
     * @return 结果
     */
    public int insertQcDefectRecord(QcDefectRecord qcDefectRecord);

    /**
     * 修改检验单缺陷记录
     * 
     * @param qcDefectRecord 检验单缺陷记录
     * @return 结果
     */
    public int updateQcDefectRecord(QcDefectRecord qcDefectRecord);

    /**
     * 删除检验单缺陷记录
     * 
     * @param recordId 检验单缺陷记录主键
     * @return 结果
     */
    public int deleteQcDefectRecordByRecordId(Long recordId);

    /**
     * 批量删除检验单缺陷记录
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQcDefectRecordByRecordIds(Long[] recordIds);

    /**
     * 根据检测单ID和对应的类型删除
     * @param qcDefectRecord
     * @return
     */
    public int deleteByQcIdAndType(QcDefectRecord qcDefectRecord);
}
