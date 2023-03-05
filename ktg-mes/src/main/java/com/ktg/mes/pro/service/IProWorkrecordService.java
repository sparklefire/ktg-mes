package com.ktg.mes.pro.service;

import java.util.List;
import com.ktg.mes.pro.domain.ProWorkrecord;

/**
 * 上下工记录Service接口
 * 
 * @author yinjinlu
 * @date 2023-02-20
 */
public interface IProWorkrecordService 
{
    /**
     * 查询上下工记录
     * 
     * @param recordId 上下工记录主键
     * @return 上下工记录
     */
    public ProWorkrecord selectProWorkrecordByRecordId(Long recordId);

    /**
     * 查询上下工记录列表
     * 
     * @param proWorkrecord 上下工记录
     * @return 上下工记录集合
     */
    public List<ProWorkrecord> selectProWorkrecordList(ProWorkrecord proWorkrecord);

    /**
     * 新增上下工记录
     * 
     * @param proWorkrecord 上下工记录
     * @return 结果
     */
    public int insertProWorkrecord(ProWorkrecord proWorkrecord);

    /**
     * 修改上下工记录
     * 
     * @param proWorkrecord 上下工记录
     * @return 结果
     */
    public int updateProWorkrecord(ProWorkrecord proWorkrecord);

    /**
     * 批量删除上下工记录
     * 
     * @param recordIds 需要删除的上下工记录主键集合
     * @return 结果
     */
    public int deleteProWorkrecordByRecordIds(Long[] recordIds);

    /**
     * 删除上下工记录信息
     * 
     * @param recordId 上下工记录主键
     * @return 结果
     */
    public int deleteProWorkrecordByRecordId(Long recordId);
}
