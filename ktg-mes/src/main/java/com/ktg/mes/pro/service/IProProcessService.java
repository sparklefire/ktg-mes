package com.ktg.mes.pro.service;

import java.util.List;
import com.ktg.mes.pro.domain.ProProcess;

/**
 * 生产工序Service接口
 * 
 * @author yinjinlu
 * @date 2022-05-11
 */
public interface IProProcessService 
{
    /**
     * 查询生产工序
     * 
     * @param processId 生产工序主键
     * @return 生产工序
     */
    public ProProcess selectProProcessByProcessId(Long processId);

    /**
     * 查询生产工序列表
     * 
     * @param proProcess 生产工序
     * @return 生产工序集合
     */
    public List<ProProcess> selectProProcessList(ProProcess proProcess);

    public String checkProcessCodeUnique(ProProcess proProcess);
    public String checkProcessNameUnique(ProProcess proProcess);

    /**
     * 新增生产工序
     * 
     * @param proProcess 生产工序
     * @return 结果
     */
    public int insertProProcess(ProProcess proProcess);

    /**
     * 修改生产工序
     * 
     * @param proProcess 生产工序
     * @return 结果
     */
    public int updateProProcess(ProProcess proProcess);

    /**
     * 批量删除生产工序
     * 
     * @param processIds 需要删除的生产工序主键集合
     * @return 结果
     */
    public int deleteProProcessByProcessIds(Long[] processIds);

    /**
     * 删除生产工序信息
     * 
     * @param processId 生产工序主键
     * @return 结果
     */
    public int deleteProProcessByProcessId(Long processId);
}
