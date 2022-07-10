package com.ktg.mes.pro.mapper;

import java.util.List;
import com.ktg.mes.pro.domain.ProFeedback;

/**
 * 生产报工记录Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-07-10
 */
public interface ProFeedbackMapper 
{
    /**
     * 查询生产报工记录
     * 
     * @param recordId 生产报工记录主键
     * @return 生产报工记录
     */
    public ProFeedback selectProFeedbackByRecordId(Long recordId);

    /**
     * 查询生产报工记录列表
     * 
     * @param proFeedback 生产报工记录
     * @return 生产报工记录集合
     */
    public List<ProFeedback> selectProFeedbackList(ProFeedback proFeedback);

    /**
     * 新增生产报工记录
     * 
     * @param proFeedback 生产报工记录
     * @return 结果
     */
    public int insertProFeedback(ProFeedback proFeedback);

    /**
     * 修改生产报工记录
     * 
     * @param proFeedback 生产报工记录
     * @return 结果
     */
    public int updateProFeedback(ProFeedback proFeedback);

    /**
     * 删除生产报工记录
     * 
     * @param recordId 生产报工记录主键
     * @return 结果
     */
    public int deleteProFeedbackByRecordId(Long recordId);

    /**
     * 批量删除生产报工记录
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProFeedbackByRecordIds(Long[] recordIds);
}
