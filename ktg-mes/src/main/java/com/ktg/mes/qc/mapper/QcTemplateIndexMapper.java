package com.ktg.mes.qc.mapper;

import java.util.List;
import com.ktg.mes.qc.domain.QcTemplateIndex;

/**
 * 检测模板-检测项Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-05-18
 */
public interface QcTemplateIndexMapper 
{
    /**
     * 查询检测模板-检测项
     * 
     * @param recordId 检测模板-检测项主键
     * @return 检测模板-检测项
     */
    public QcTemplateIndex selectQcTemplateIndexByRecordId(Long recordId);

    /**
     * 查询检测模板-检测项列表
     * 
     * @param qcTemplateIndex 检测模板-检测项
     * @return 检测模板-检测项集合
     */
    public List<QcTemplateIndex> selectQcTemplateIndexList(QcTemplateIndex qcTemplateIndex);

    /**
     * 新增检测模板-检测项
     * 
     * @param qcTemplateIndex 检测模板-检测项
     * @return 结果
     */
    public int insertQcTemplateIndex(QcTemplateIndex qcTemplateIndex);

    /**
     * 修改检测模板-检测项
     * 
     * @param qcTemplateIndex 检测模板-检测项
     * @return 结果
     */
    public int updateQcTemplateIndex(QcTemplateIndex qcTemplateIndex);

    /**
     * 删除检测模板-检测项
     * 
     * @param recordId 检测模板-检测项主键
     * @return 结果
     */
    public int deleteQcTemplateIndexByRecordId(Long recordId);

    /**
     * 批量删除检测模板-检测项
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQcTemplateIndexByRecordIds(Long[] recordIds);

    /**
     * 根据检测模板ID删除所有检测项
     * @param templateId
     * @return
     */
    public int deleteByTemplateId(Long templateId);
}
