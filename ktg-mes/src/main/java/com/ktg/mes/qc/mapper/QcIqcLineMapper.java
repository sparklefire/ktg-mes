package com.ktg.mes.qc.mapper;

import java.util.List;
import com.ktg.mes.qc.domain.QcIqcLine;

/**
 * 来料检验单行Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-05-19
 */
public interface QcIqcLineMapper 
{
    /**
     * 查询来料检验单行
     * 
     * @param lineId 来料检验单行主键
     * @return 来料检验单行
     */
    public QcIqcLine selectQcIqcLineByLineId(Long lineId);

    /**
     * 查询来料检验单行列表
     * 
     * @param qcIqcLine 来料检验单行
     * @return 来料检验单行集合
     */
    public List<QcIqcLine> selectQcIqcLineList(QcIqcLine qcIqcLine);

    /**
     * 新增来料检验单行
     * 
     * @param qcIqcLine 来料检验单行
     * @return 结果
     */
    public int insertQcIqcLine(QcIqcLine qcIqcLine);

    /**
     * 修改来料检验单行
     * 
     * @param qcIqcLine 来料检验单行
     * @return 结果
     */
    public int updateQcIqcLine(QcIqcLine qcIqcLine);


    public int updateCrMajMinQuantity(QcIqcLine qcIqcLine);

    /**
     * 删除来料检验单行
     * 
     * @param lineId 来料检验单行主键
     * @return 结果
     */
    public int deleteQcIqcLineByLineId(Long lineId);

    public int deleteByIqcId(Long iqcId);

    /**
     * 批量删除来料检验单行
     * 
     * @param lineIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQcIqcLineByLineIds(Long[] lineIds);
}
