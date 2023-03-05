package com.ktg.mes.pro.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.pro.mapper.ProWorkrecordMapper;
import com.ktg.mes.pro.domain.ProWorkrecord;
import com.ktg.mes.pro.service.IProWorkrecordService;

/**
 * 上下工记录Service业务层处理
 * 
 * @author yinjinlu
 * @date 2023-02-20
 */
@Service
public class ProWorkrecordServiceImpl implements IProWorkrecordService 
{
    @Autowired
    private ProWorkrecordMapper proWorkrecordMapper;

    /**
     * 查询上下工记录
     * 
     * @param recordId 上下工记录主键
     * @return 上下工记录
     */
    @Override
    public ProWorkrecord selectProWorkrecordByRecordId(Long recordId)
    {
        return proWorkrecordMapper.selectProWorkrecordByRecordId(recordId);
    }

    /**
     * 查询上下工记录列表
     * 
     * @param proWorkrecord 上下工记录
     * @return 上下工记录
     */
    @Override
    public List<ProWorkrecord> selectProWorkrecordList(ProWorkrecord proWorkrecord)
    {
        return proWorkrecordMapper.selectProWorkrecordList(proWorkrecord);
    }

    /**
     * 新增上下工记录
     * 
     * @param proWorkrecord 上下工记录
     * @return 结果
     */
    @Override
    public int insertProWorkrecord(ProWorkrecord proWorkrecord)
    {
        proWorkrecord.setCreateTime(DateUtils.getNowDate());
        return proWorkrecordMapper.insertProWorkrecord(proWorkrecord);
    }

    /**
     * 修改上下工记录
     * 
     * @param proWorkrecord 上下工记录
     * @return 结果
     */
    @Override
    public int updateProWorkrecord(ProWorkrecord proWorkrecord)
    {
        proWorkrecord.setUpdateTime(DateUtils.getNowDate());
        return proWorkrecordMapper.updateProWorkrecord(proWorkrecord);
    }

    /**
     * 批量删除上下工记录
     * 
     * @param recordIds 需要删除的上下工记录主键
     * @return 结果
     */
    @Override
    public int deleteProWorkrecordByRecordIds(Long[] recordIds)
    {
        return proWorkrecordMapper.deleteProWorkrecordByRecordIds(recordIds);
    }

    /**
     * 删除上下工记录信息
     * 
     * @param recordId 上下工记录主键
     * @return 结果
     */
    @Override
    public int deleteProWorkrecordByRecordId(Long recordId)
    {
        return proWorkrecordMapper.deleteProWorkrecordByRecordId(recordId);
    }
}
