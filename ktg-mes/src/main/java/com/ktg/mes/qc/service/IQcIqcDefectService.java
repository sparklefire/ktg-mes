package com.ktg.mes.qc.service;

import java.util.List;
import com.ktg.mes.qc.domain.QcIqcDefect;

/**
 * 来料检验单缺陷记录Service接口
 * 
 * @author yinjinlu
 * @date 2022-05-19
 */
public interface IQcIqcDefectService 
{
    /**
     * 查询来料检验单缺陷记录
     * 
     * @param recordId 来料检验单缺陷记录主键
     * @return 来料检验单缺陷记录
     */
    public QcIqcDefect selectQcIqcDefectByRecordId(Long recordId);

    /**
     * 查询来料检验单缺陷记录列表
     * 
     * @param qcIqcDefect 来料检验单缺陷记录
     * @return 来料检验单缺陷记录集合
     */
    public List<QcIqcDefect> selectQcIqcDefectList(QcIqcDefect qcIqcDefect);

    /**
     * 新增来料检验单缺陷记录
     * 
     * @param qcIqcDefect 来料检验单缺陷记录
     * @return 结果
     */
    public int insertQcIqcDefect(QcIqcDefect qcIqcDefect);

    /**
     * 修改来料检验单缺陷记录
     * 
     * @param qcIqcDefect 来料检验单缺陷记录
     * @return 结果
     */
    public int updateQcIqcDefect(QcIqcDefect qcIqcDefect);

    /**
     * 批量删除来料检验单缺陷记录
     * 
     * @param recordIds 需要删除的来料检验单缺陷记录主键集合
     * @return 结果
     */
    public int deleteQcIqcDefectByRecordIds(Long[] recordIds);

    /**
     * 删除来料检验单下所有缺陷记录
     * @param iqcID
     * @return
     */
    public int deleteByIqcId(Long iqcID);

    /**
     * 删除行下所有缺陷记录
     * @param qcIqcDefect
     * @return
     */
    public int deleteByIqcIdLineId(QcIqcDefect qcIqcDefect);

    /**
     * 删除来料检验单缺陷记录信息
     * 
     * @param recordId 来料检验单缺陷记录主键
     * @return 结果
     */
    public int deleteQcIqcDefectByRecordId(Long recordId);
}
