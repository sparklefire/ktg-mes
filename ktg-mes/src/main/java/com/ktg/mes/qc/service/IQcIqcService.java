package com.ktg.mes.qc.service;

import java.util.List;
import com.ktg.mes.qc.domain.QcIqc;

/**
 * 来料检验单Service接口
 * 
 * @author yinjinlu
 * @date 2022-05-19
 */
public interface IQcIqcService 
{
    /**
     * 查询来料检验单
     * 
     * @param iqcId 来料检验单主键
     * @return 来料检验单
     */
    public QcIqc selectQcIqcByIqcId(Long iqcId);

    /**
     * 查询来料检验单列表
     * 
     * @param qcIqc 来料检验单
     * @return 来料检验单集合
     */
    public List<QcIqc> selectQcIqcList(QcIqc qcIqc);

    /**
     * 检查来料检验单号是否重复
     * @param qcIqc
     * @return
     */
    public String checkIqcCodeUnique(QcIqc qcIqc);

    /**
     * 新增来料检验单
     * 
     * @param qcIqc 来料检验单
     * @return 结果
     */
    public int insertQcIqc(QcIqc qcIqc);

    /**
     * 修改来料检验单
     * 
     * @param qcIqc 来料检验单
     * @return 结果
     */
    public int updateQcIqc(QcIqc qcIqc);

    public int updateCrMajMinQuaAndRate(Long iqcId);

    /**
     * 批量删除来料检验单
     * 
     * @param iqcIds 需要删除的来料检验单主键集合
     * @return 结果
     */
    public int deleteQcIqcByIqcIds(Long[] iqcIds);

    /**
     * 删除来料检验单信息
     * 
     * @param iqcId 来料检验单主键
     * @return 结果
     */
    public int deleteQcIqcByIqcId(Long iqcId);
}
