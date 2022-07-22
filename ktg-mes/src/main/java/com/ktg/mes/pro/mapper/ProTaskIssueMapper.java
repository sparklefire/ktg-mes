package com.ktg.mes.pro.mapper;

import java.util.List;
import com.ktg.mes.pro.domain.ProTaskIssue;

/**
 * 生产任务投料Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-07-22
 */
public interface ProTaskIssueMapper 
{
    /**
     * 查询生产任务投料
     * 
     * @param recordId 生产任务投料主键
     * @return 生产任务投料
     */
    public ProTaskIssue selectProTaskIssueByRecordId(Long recordId);

    /**
     * 查询生产任务投料列表
     * 
     * @param proTaskIssue 生产任务投料
     * @return 生产任务投料集合
     */
    public List<ProTaskIssue> selectProTaskIssueList(ProTaskIssue proTaskIssue);

    public ProTaskIssue checkUnique(ProTaskIssue proTaskIssue);

    /**
     * 新增生产任务投料
     * 
     * @param proTaskIssue 生产任务投料
     * @return 结果
     */
    public int insertProTaskIssue(ProTaskIssue proTaskIssue);

    /**
     * 修改生产任务投料
     * 
     * @param proTaskIssue 生产任务投料
     * @return 结果
     */
    public int updateProTaskIssue(ProTaskIssue proTaskIssue);

    /**
     * 删除生产任务投料
     * 
     * @param recordId 生产任务投料主键
     * @return 结果
     */
    public int deleteProTaskIssueByRecordId(Long recordId);

    /**
     * 批量删除生产任务投料
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProTaskIssueByRecordIds(Long[] recordIds);
}
