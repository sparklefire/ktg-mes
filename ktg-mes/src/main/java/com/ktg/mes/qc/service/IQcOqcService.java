package com.ktg.mes.qc.service;

import java.util.List;
import com.ktg.mes.qc.domain.QcOqc;

/**
 * 出货检验单Service接口
 * 
 * @author yinjinlu
 * @date 2022-08-31
 */
public interface IQcOqcService 
{
    /**
     * 查询出货检验单
     * 
     * @param oqcId 出货检验单主键
     * @return 出货检验单
     */
    public QcOqc selectQcOqcByOqcId(Long oqcId);

    /**
     * 查询出货检验单列表
     * 
     * @param qcOqc 出货检验单
     * @return 出货检验单集合
     */
    public List<QcOqc> selectQcOqcList(QcOqc qcOqc);


    /**
     * 检查出货检验单号是否唯一
     * @param qcOqc
     * @return
     */
    public String checkOqcCodeUnique(QcOqc qcOqc);

    /**
     * 新增出货检验单
     * 
     * @param qcOqc 出货检验单
     * @return 结果
     */
    public int insertQcOqc(QcOqc qcOqc);

    /**
     * 修改出货检验单
     * 
     * @param qcOqc 出货检验单
     * @return 结果
     */
    public int updateQcOqc(QcOqc qcOqc);

    /**
     * 根据缺陷记录更新头上的缺陷数量和缺陷比率
     * @param oqcId
     * @return
     */
    public int updateCrMajMinQuaAndRate(Long oqcId);

    /**
     * 批量删除出货检验单
     * 
     * @param oqcIds 需要删除的出货检验单主键集合
     * @return 结果
     */
    public int deleteQcOqcByOqcIds(Long[] oqcIds);

    /**
     * 删除出货检验单信息
     * 
     * @param oqcId 出货检验单主键
     * @return 结果
     */
    public int deleteQcOqcByOqcId(Long oqcId);
}
