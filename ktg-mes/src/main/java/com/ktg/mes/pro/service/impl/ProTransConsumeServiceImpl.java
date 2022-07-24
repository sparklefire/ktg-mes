package com.ktg.mes.pro.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.pro.mapper.ProTransConsumeMapper;
import com.ktg.mes.pro.domain.ProTransConsume;
import com.ktg.mes.pro.service.IProTransConsumeService;

/**
 * 物料消耗记录Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-07-24
 */
@Service
public class ProTransConsumeServiceImpl implements IProTransConsumeService 
{
    @Autowired
    private ProTransConsumeMapper proTransConsumeMapper;

    /**
     * 查询物料消耗记录
     * 
     * @param recordId 物料消耗记录主键
     * @return 物料消耗记录
     */
    @Override
    public ProTransConsume selectProTransConsumeByRecordId(Long recordId)
    {
        return proTransConsumeMapper.selectProTransConsumeByRecordId(recordId);
    }

    /**
     * 查询物料消耗记录列表
     * 
     * @param proTransConsume 物料消耗记录
     * @return 物料消耗记录
     */
    @Override
    public List<ProTransConsume> selectProTransConsumeList(ProTransConsume proTransConsume)
    {
        return proTransConsumeMapper.selectProTransConsumeList(proTransConsume);
    }

    /**
     * 新增物料消耗记录
     * 
     * @param proTransConsume 物料消耗记录
     * @return 结果
     */
    @Override
    public int insertProTransConsume(ProTransConsume proTransConsume)
    {
        proTransConsume.setCreateTime(DateUtils.getNowDate());
        return proTransConsumeMapper.insertProTransConsume(proTransConsume);
    }

    /**
     * 修改物料消耗记录
     * 
     * @param proTransConsume 物料消耗记录
     * @return 结果
     */
    @Override
    public int updateProTransConsume(ProTransConsume proTransConsume)
    {
        proTransConsume.setUpdateTime(DateUtils.getNowDate());
        return proTransConsumeMapper.updateProTransConsume(proTransConsume);
    }

    /**
     * 批量删除物料消耗记录
     * 
     * @param recordIds 需要删除的物料消耗记录主键
     * @return 结果
     */
    @Override
    public int deleteProTransConsumeByRecordIds(Long[] recordIds)
    {
        return proTransConsumeMapper.deleteProTransConsumeByRecordIds(recordIds);
    }

    /**
     * 删除物料消耗记录信息
     * 
     * @param recordId 物料消耗记录主键
     * @return 结果
     */
    @Override
    public int deleteProTransConsumeByRecordId(Long recordId)
    {
        return proTransConsumeMapper.deleteProTransConsumeByRecordId(recordId);
    }
}
