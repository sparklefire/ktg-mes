package com.ktg.mes.qc.service;

import java.util.List;
import com.ktg.mes.qc.domain.QcDefect;

/**
 * 常见缺陷Service接口
 * 
 * @author yinjinlu
 * @date 2022-05-19
 */
public interface IQcDefectService 
{
    /**
     * 查询常见缺陷
     * 
     * @param defectId 常见缺陷主键
     * @return 常见缺陷
     */
    public QcDefect selectQcDefectByDefectId(Long defectId);

    /**
     * 查询常见缺陷列表
     * 
     * @param qcDefect 常见缺陷
     * @return 常见缺陷集合
     */
    public List<QcDefect> selectQcDefectList(QcDefect qcDefect);

    /**
     * 新增常见缺陷
     * 
     * @param qcDefect 常见缺陷
     * @return 结果
     */
    public int insertQcDefect(QcDefect qcDefect);

    /**
     * 修改常见缺陷
     * 
     * @param qcDefect 常见缺陷
     * @return 结果
     */
    public int updateQcDefect(QcDefect qcDefect);

    /**
     * 批量删除常见缺陷
     * 
     * @param defectIds 需要删除的常见缺陷主键集合
     * @return 结果
     */
    public int deleteQcDefectByDefectIds(Long[] defectIds);

    /**
     * 删除常见缺陷信息
     * 
     * @param defectId 常见缺陷主键
     * @return 结果
     */
    public int deleteQcDefectByDefectId(Long defectId);
}
