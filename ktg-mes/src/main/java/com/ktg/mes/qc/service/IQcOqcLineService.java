package com.ktg.mes.qc.service;

import java.util.List;
import com.ktg.mes.qc.domain.QcOqcLine;

/**
 * 出货检验单行Service接口
 * 
 * @author yinjinlu
 * @date 2022-09-01
 */
public interface IQcOqcLineService 
{
    /**
     * 查询出货检验单行
     * 
     * @param lineId 出货检验单行主键
     * @return 出货检验单行
     */
    public QcOqcLine selectQcOqcLineByLineId(Long lineId);

    /**
     * 查询出货检验单行列表
     * 
     * @param qcOqcLine 出货检验单行
     * @return 出货检验单行集合
     */
    public List<QcOqcLine> selectQcOqcLineList(QcOqcLine qcOqcLine);

    /**
     * 新增出货检验单行
     * 
     * @param qcOqcLine 出货检验单行
     * @return 结果
     */
    public int insertQcOqcLine(QcOqcLine qcOqcLine);

    /**
     * 修改出货检验单行
     * 
     * @param qcOqcLine 出货检验单行
     * @return 结果
     */
    public int updateQcOqcLine(QcOqcLine qcOqcLine);

    /**
     * 计算并更新当前行的Cr,Maj,Min的总数量
     * @param qcId
     * @param lineId
     * @return
     */
    public int updateCrMajMinQuantity(Long qcId,Long lineId);

    /**
     * 批量删除出货检验单行
     * 
     * @param lineIds 需要删除的出货检验单行主键集合
     * @return 结果
     */
    public int deleteQcOqcLineByLineIds(Long[] lineIds);

    /**
     * 删除出货检验单行信息
     * 
     * @param lineId 出货检验单行主键
     * @return 结果
     */
    public int deleteQcOqcLineByLineId(Long lineId);

    /**
     * 根据出货检验单头删除相应的行信息
     * @param oqcId
     * @return
     */
    public int deleteByOqcId(Long oqcId);
}
