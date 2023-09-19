package com.ktg.mes.qc.mapper;

import java.util.List;
import com.ktg.mes.qc.domain.QcIpqc;

/**
 * 过程检验单Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-08-29
 */
public interface QcIpqcMapper 
{
    /**
     * 查询过程检验单
     * 
     * @param ipqcId 过程检验单主键
     * @return 过程检验单
     */
    public QcIpqc selectQcIpqcByIpqcId(Long ipqcId);

    /**
     * 查询过程检验单列表
     * 
     * @param qcIpqc 过程检验单
     * @return 过程检验单集合
     */
    public List<QcIpqc> selectQcIpqcList(QcIpqc qcIpqc);

    /**
     * 验证检测编码是否唯一
     * @param qcIpqc
     * @return
     */
    public QcIpqc checkIpqcCodeUnique(QcIpqc qcIpqc);


    /**
     * 根据当前传递的过程检验单，查询更多工艺相关信息
     * @param qcIpqc
     * @return
     */
    public List<QcIpqc> getProcessInfo(QcIpqc qcIpqc);

    /**
     * 新增过程检验单
     * 
     * @param qcIpqc 过程检验单
     * @return 结果
     */
    public int insertQcIpqc(QcIpqc qcIpqc);

    /**
     * 修改过程检验单
     * 
     * @param qcIpqc 过程检验单
     * @return 结果
     */
    public int updateQcIpqc(QcIpqc qcIpqc);

    /**
     * 根据缺陷记录更新头上的缺陷数量和缺陷率
     * @param ipqcId
     * @return
     */
    public int updateCrMajMinQuaAndRate(Long ipqcId);

    /**
     * 删除过程检验单
     * 
     * @param ipqcId 过程检验单主键
     * @return 结果
     */
    public int deleteQcIpqcByIpqcId(Long ipqcId);

    /**
     * 批量删除过程检验单
     * 
     * @param ipqcIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQcIpqcByIpqcIds(Long[] ipqcIds);
}
