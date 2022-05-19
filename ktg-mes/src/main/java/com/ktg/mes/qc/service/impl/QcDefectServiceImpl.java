package com.ktg.mes.qc.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.qc.mapper.QcDefectMapper;
import com.ktg.mes.qc.domain.QcDefect;
import com.ktg.mes.qc.service.IQcDefectService;

/**
 * 常见缺陷Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-19
 */
@Service
public class QcDefectServiceImpl implements IQcDefectService 
{
    @Autowired
    private QcDefectMapper qcDefectMapper;

    /**
     * 查询常见缺陷
     * 
     * @param defectId 常见缺陷主键
     * @return 常见缺陷
     */
    @Override
    public QcDefect selectQcDefectByDefectId(Long defectId)
    {
        return qcDefectMapper.selectQcDefectByDefectId(defectId);
    }

    /**
     * 查询常见缺陷列表
     * 
     * @param qcDefect 常见缺陷
     * @return 常见缺陷
     */
    @Override
    public List<QcDefect> selectQcDefectList(QcDefect qcDefect)
    {
        return qcDefectMapper.selectQcDefectList(qcDefect);
    }

    /**
     * 新增常见缺陷
     * 
     * @param qcDefect 常见缺陷
     * @return 结果
     */
    @Override
    public int insertQcDefect(QcDefect qcDefect)
    {
        qcDefect.setCreateTime(DateUtils.getNowDate());
        return qcDefectMapper.insertQcDefect(qcDefect);
    }

    /**
     * 修改常见缺陷
     * 
     * @param qcDefect 常见缺陷
     * @return 结果
     */
    @Override
    public int updateQcDefect(QcDefect qcDefect)
    {
        qcDefect.setUpdateTime(DateUtils.getNowDate());
        return qcDefectMapper.updateQcDefect(qcDefect);
    }

    /**
     * 批量删除常见缺陷
     * 
     * @param defectIds 需要删除的常见缺陷主键
     * @return 结果
     */
    @Override
    public int deleteQcDefectByDefectIds(Long[] defectIds)
    {
        return qcDefectMapper.deleteQcDefectByDefectIds(defectIds);
    }

    /**
     * 删除常见缺陷信息
     * 
     * @param defectId 常见缺陷主键
     * @return 结果
     */
    @Override
    public int deleteQcDefectByDefectId(Long defectId)
    {
        return qcDefectMapper.deleteQcDefectByDefectId(defectId);
    }
}
