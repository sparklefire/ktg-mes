package com.ktg.mes.pro.mapper;

import java.util.List;
import com.ktg.mes.pro.domain.ProTransConsume;

/**
 * 物料消耗记录Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-07-24
 */
public interface ProTransConsumeMapper 
{
    /**
     * 查询物料消耗记录
     * 
     * @param recordId 物料消耗记录主键
     * @return 物料消耗记录
     */
    public ProTransConsume selectProTransConsumeByRecordId(Long recordId);

    /**
     * 查询物料消耗记录列表
     * 
     * @param proTransConsume 物料消耗记录
     * @return 物料消耗记录集合
     */
    public List<ProTransConsume> selectProTransConsumeList(ProTransConsume proTransConsume);

    /**
     * 新增物料消耗记录
     * 
     * @param proTransConsume 物料消耗记录
     * @return 结果
     */
    public int insertProTransConsume(ProTransConsume proTransConsume);

    /**
     * 修改物料消耗记录
     * 
     * @param proTransConsume 物料消耗记录
     * @return 结果
     */
    public int updateProTransConsume(ProTransConsume proTransConsume);

    /**
     * 删除物料消耗记录
     * 
     * @param recordId 物料消耗记录主键
     * @return 结果
     */
    public int deleteProTransConsumeByRecordId(Long recordId);

    /**
     * 批量删除物料消耗记录
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProTransConsumeByRecordIds(Long[] recordIds);
}
