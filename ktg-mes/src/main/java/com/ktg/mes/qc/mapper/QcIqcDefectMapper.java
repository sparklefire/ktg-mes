package com.ktg.mes.qc.mapper;

import java.util.List;
import com.ktg.mes.qc.domain.QcIqcDefect;

/**
 * 来料检验单缺陷记录Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-05-19
 */
public interface QcIqcDefectMapper 
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
     * 删除来料检验单缺陷记录
     * 
     * @param recordId 来料检验单缺陷记录主键
     * @return 结果
     */
    public int deleteQcIqcDefectByRecordId(Long recordId);

    public int deleteByIqcIdLineId(QcIqcDefect qcIqcDefect);

    public int deleteByIqcId(Long iqcId);

    /**
     * 批量删除来料检验单缺陷记录
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQcIqcDefectByRecordIds(Long[] recordIds);
}
