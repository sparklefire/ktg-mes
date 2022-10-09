package com.ktg.mes.pro.service;

import java.util.List;

import com.ktg.mes.pro.domain.ProFeedback;
import com.ktg.mes.pro.domain.ProRouteProcess;

/**
 * 工艺组成Service接口
 * 
 * @author yinjinlu
 * @date 2022-05-13
 */
public interface IProRouteProcessService 
{
    /**
     * 查询工艺组成
     * 
     * @param recordId 工艺组成主键
     * @return 工艺组成
     */
    public ProRouteProcess selectProRouteProcessByRecordId(Long recordId);

    /**
     * 查询工艺组成列表
     * 
     * @param proRouteProcess 工艺组成
     * @return 工艺组成集合
     */
    public List<ProRouteProcess> selectProRouteProcessList(ProRouteProcess proRouteProcess);

    /**
     * 检查序号是否已经存在
     * @param proRouteProcess
     * @return
     */
    public String checkOrderNumExists(ProRouteProcess proRouteProcess);

    /**
     * 检查工序是否已经存在
     * @param proRouteProcess
     * @return
     */
    public String checkProcessExists(ProRouteProcess proRouteProcess);

    /**
     * 检查当前工艺路线中是否已经有某个工序配置了update_flag=Y
     * @param proRouteProcess
     * @return
     */
    public String checkUpdateFlagUnique(ProRouteProcess proRouteProcess);

    /**
     * 检查某个报工单对应的工序是否是关键工序
     * @param feedback
     * @return
     */
    public boolean checkKeyProcess(ProFeedback feedback);

    /**
     * 查找上一个工序
     * @param proRouteProcess
     * @return
     */
    public ProRouteProcess findPreProcess(ProRouteProcess proRouteProcess);

    /**
     * 查找下一个工序
     * @param proRouteProcess
     * @return
     */
    public ProRouteProcess findNextProcess(ProRouteProcess proRouteProcess);

    /**
     * 新增工艺组成
     * 
     * @param proRouteProcess 工艺组成
     * @return 结果
     */
    public int insertProRouteProcess(ProRouteProcess proRouteProcess);

    /**
     * 修改工艺组成
     * 
     * @param proRouteProcess 工艺组成
     * @return 结果
     */
    public int updateProRouteProcess(ProRouteProcess proRouteProcess);

    /**
     * 批量删除工艺组成
     * 
     * @param recordIds 需要删除的工艺组成主键集合
     * @return 结果
     */
    public int deleteProRouteProcessByRecordIds(Long[] recordIds);

    /**
     * 删除工艺组成信息
     * 
     * @param recordId 工艺组成主键
     * @return 结果
     */
    public int deleteProRouteProcessByRecordId(Long recordId);

    /**
     * 根据工艺路线ID删除所有工序配置
     * @param routeId
     * @return
     */
    public int deleteByRouteId(Long routeId);
}
