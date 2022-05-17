package com.ktg.mes.qc.mapper;

import java.util.List;
import com.ktg.mes.qc.domain.QcIndex;

/**
 * 检测项Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-05-17
 */
public interface QcIndexMapper 
{
    /**
     * 查询检测项
     * 
     * @param indexId 检测项主键
     * @return 检测项
     */
    public QcIndex selectQcIndexByIndexId(Long indexId);

    /**
     * 查询检测项列表
     * 
     * @param qcIndex 检测项
     * @return 检测项集合
     */
    public List<QcIndex> selectQcIndexList(QcIndex qcIndex);


    public QcIndex checkIndexCodeUnique(QcIndex qcIndex);
    public QcIndex checkIndexNameUnique(QcIndex qcIndex);


    /**
     * 新增检测项
     * 
     * @param qcIndex 检测项
     * @return 结果
     */
    public int insertQcIndex(QcIndex qcIndex);

    /**
     * 修改检测项
     * 
     * @param qcIndex 检测项
     * @return 结果
     */
    public int updateQcIndex(QcIndex qcIndex);

    /**
     * 删除检测项
     * 
     * @param indexId 检测项主键
     * @return 结果
     */
    public int deleteQcIndexByIndexId(Long indexId);

    /**
     * 批量删除检测项
     * 
     * @param indexIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQcIndexByIndexIds(Long[] indexIds);
}
