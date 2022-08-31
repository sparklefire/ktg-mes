package com.ktg.mes.qc.mapper;

import java.util.List;
import com.ktg.mes.qc.domain.QcIpqcLine;

/**
 * 过程检验单行Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-08-30
 */
public interface QcIpqcLineMapper 
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
     * 根据缺陷记录更新过程检验单行上的缺陷数量
     * @param qcIpqcLine
     * @return
     */
    public int updateCrMajMinQuantity(QcIpqcLine qcIpqcLine);

    /**
     * 删除过程检验单行
     * 
     * @param lineId 过程检验单行主键
     * @return 结果
     */
    public int deleteQcIpqcLineByLineId(Long lineId);

    /**
     * 批量删除过程检验单行
     * 
     * @param lineIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQcIpqcLineByLineIds(Long[] lineIds);

    /***
     * 根据检验单头ID删除所有行信息
     * @param ipqcId
     * @return
     */
    public int deleteByIpqcId(Long ipqcId);
}
