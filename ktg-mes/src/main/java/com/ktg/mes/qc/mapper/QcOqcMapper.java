package com.ktg.mes.qc.mapper;

import java.util.List;
import com.ktg.mes.qc.domain.QcOqc;

/**
 * 出货检验单Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-08-31
 */
public interface QcOqcMapper 
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
    public QcOqc checkOqcCodeUnique(QcOqc qcOqc);

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
     * @param oqc
     * @return
     */
    public int updateCrMajMinQuaAndRate(Long oqc);

    /**
     * 删除出货检验单
     * 
     * @param oqcId 出货检验单主键
     * @return 结果
     */
    public int deleteQcOqcByOqcId(Long oqcId);

    /**
     * 批量删除出货检验单
     * 
     * @param oqcIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQcOqcByOqcIds(Long[] oqcIds);
}
