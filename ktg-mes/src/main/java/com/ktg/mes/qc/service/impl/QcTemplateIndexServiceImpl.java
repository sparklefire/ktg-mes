package com.ktg.mes.qc.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.qc.mapper.QcTemplateIndexMapper;
import com.ktg.mes.qc.domain.QcTemplateIndex;
import com.ktg.mes.qc.service.IQcTemplateIndexService;

/**
 * 检测模板-检测项Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-18
 */
@Service
public class QcTemplateIndexServiceImpl implements IQcTemplateIndexService 
{
    @Autowired
    private QcTemplateIndexMapper qcTemplateIndexMapper;

    /**
     * 查询检测模板-检测项
     * 
     * @param recordId 检测模板-检测项主键
     * @return 检测模板-检测项
     */
    @Override
    public QcTemplateIndex selectQcTemplateIndexByRecordId(Long recordId)
    {
        return qcTemplateIndexMapper.selectQcTemplateIndexByRecordId(recordId);
    }

    /**
     * 查询检测模板-检测项列表
     * 
     * @param qcTemplateIndex 检测模板-检测项
     * @return 检测模板-检测项
     */
    @Override
    public List<QcTemplateIndex> selectQcTemplateIndexList(QcTemplateIndex qcTemplateIndex)
    {
        return qcTemplateIndexMapper.selectQcTemplateIndexList(qcTemplateIndex);
    }

    /**
     * 新增检测模板-检测项
     * 
     * @param qcTemplateIndex 检测模板-检测项
     * @return 结果
     */
    @Override
    public int insertQcTemplateIndex(QcTemplateIndex qcTemplateIndex)
    {
        qcTemplateIndex.setCreateTime(DateUtils.getNowDate());
        return qcTemplateIndexMapper.insertQcTemplateIndex(qcTemplateIndex);
    }

    /**
     * 修改检测模板-检测项
     * 
     * @param qcTemplateIndex 检测模板-检测项
     * @return 结果
     */
    @Override
    public int updateQcTemplateIndex(QcTemplateIndex qcTemplateIndex)
    {
        qcTemplateIndex.setUpdateTime(DateUtils.getNowDate());
        return qcTemplateIndexMapper.updateQcTemplateIndex(qcTemplateIndex);
    }

    /**
     * 批量删除检测模板-检测项
     * 
     * @param recordIds 需要删除的检测模板-检测项主键
     * @return 结果
     */
    @Override
    public int deleteQcTemplateIndexByRecordIds(Long[] recordIds)
    {
        return qcTemplateIndexMapper.deleteQcTemplateIndexByRecordIds(recordIds);
    }

    /**
     * 删除检测模板-检测项信息
     * 
     * @param recordId 检测模板-检测项主键
     * @return 结果
     */
    @Override
    public int deleteQcTemplateIndexByRecordId(Long recordId)
    {
        return qcTemplateIndexMapper.deleteQcTemplateIndexByRecordId(recordId);
    }

    @Override
    public int deleteByTemplateId(Long templateId) {
        return qcTemplateIndexMapper.deleteByTemplateId(templateId);
    }
}
