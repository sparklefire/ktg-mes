package com.ktg.mes.pro.service;

import java.util.List;
import com.ktg.mes.pro.domain.ProTask;

/**
 * 生产任务Service接口
 * 
 * @author yinjinlu
 * @date 2022-05-14
 */
public interface IProTaskService 
{
    /**
     * 查询生产任务
     * 
     * @param taskId 生产任务主键
     * @return 生产任务
     */
    public ProTask selectProTaskByTaskId(Long taskId);

    /**
     * 查询生产任务列表
     * 
     * @param proTask 生产任务
     * @return 生产任务集合
     */
    public List<ProTask> selectProTaskList(ProTask proTask);

    /**
     * 新增生产任务
     * 
     * @param proTask 生产任务
     * @return 结果
     */
    public int insertProTask(ProTask proTask);

    /**
     * 修改生产任务
     * 
     * @param proTask 生产任务
     * @return 结果
     */
    public int updateProTask(ProTask proTask);

    /**
     * 批量删除生产任务
     * 
     * @param taskIds 需要删除的生产任务主键集合
     * @return 结果
     */
    public int deleteProTaskByTaskIds(Long[] taskIds);

    /**
     * 删除生产任务信息
     * 
     * @param taskId 生产任务主键
     * @return 结果
     */
    public int deleteProTaskByTaskId(Long taskId);
}
