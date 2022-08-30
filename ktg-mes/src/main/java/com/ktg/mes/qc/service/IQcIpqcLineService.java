package com.ktg.mes.qc.service;

import java.util.List;
import com.ktg.mes.qc.domain.QcIpqcLine;

/**
 * 过程检验单行Service接口
 * 
 * @author yinjinlu
 * @date 2022-08-30
 */
public interface IQcIpqcLineService 
{
    /**
     * 查询过程检验单行
     * 
     * @param lineId 过程检验单行主键
     * @return 过程检验单行
     */
    public QcIpqcLine selectQcIpqcLineByLineId(Long lineId);

    /**
     * 查询过程检验单行列表
     * 
     * @param qcIpqcLine 过程检验单行
     * @return 过程检验单行集合
     */
    public List<QcIpqcLine> selectQcIpqcLineList(QcIpqcLine qcIpqcLine);

    /**
     * 新增过程检验单行
     * 
     * @param qcIpqcLine 过程检验单行
     * @return 结果
     */
    public int insertQcIpqcLine(QcIpqcLine qcIpqcLine);

    /**
     * 修改过程检验单行
     * 
     * @param qcIpqcLine 过程检验单行
     * @return 结果
     */
    public int updateQcIpqcLine(QcIpqcLine qcIpqcLine);

    /**
     * 计算并更新当前行的Cr,Maj,Min的总数量
     * @param qcId
     * @param lineId
     * @return
     */
    public int updateCrMajMinQuantity(Long qcId,Long lineId);

    /**
     * 批量删除过程检验单行
     * 
     * @param lineIds 需要删除的过程检验单行主键集合
     * @return 结果
     */
    public int deleteQcIpqcLineByLineIds(Long[] lineIds);

    /**
     * 删除过程检验单行信息
     * 
     * @param lineId 过程检验单行主键
     * @return 结果
     */
    public int deleteQcIpqcLineByLineId(Long lineId);

    /***
     * 根据检验单头ID删除所有行信息
     * @param ipqcId
     * @return
     */
    public int deleteByIpqcId(Long ipqcId);
}
