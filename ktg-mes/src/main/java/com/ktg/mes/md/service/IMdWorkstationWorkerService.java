package com.ktg.mes.md.service;

import java.util.List;
import com.ktg.mes.md.domain.MdWorkstationWorker;

/**
 * 人力资源Service接口
 * 
 * @author yinjinlu
 * @date 2022-05-12
 */
public interface IMdWorkstationWorkerService 
{
    /**
     * 查询人力资源
     * 
     * @param recordId 人力资源主键
     * @return 人力资源
     */
    public MdWorkstationWorker selectMdWorkstationWorkerByRecordId(Long recordId);

    /**
     * 查询人力资源列表
     * 
     * @param mdWorkstationWorker 人力资源
     * @return 人力资源集合
     */
    public List<MdWorkstationWorker> selectMdWorkstationWorkerList(MdWorkstationWorker mdWorkstationWorker);

    /**
     * 检查当前工作站是否已添加此岗位资源
     * @param mdWorkstationWorker
     * @return
     */
    public String checkPostExist(MdWorkstationWorker mdWorkstationWorker);

    /**
     * 新增人力资源
     * 
     * @param mdWorkstationWorker 人力资源
     * @return 结果
     */
    public int insertMdWorkstationWorker(MdWorkstationWorker mdWorkstationWorker);

    /**
     * 修改人力资源
     * 
     * @param mdWorkstationWorker 人力资源
     * @return 结果
     */
    public int updateMdWorkstationWorker(MdWorkstationWorker mdWorkstationWorker);

    /**
     * 批量删除人力资源
     * 
     * @param recordIds 需要删除的人力资源主键集合
     * @return 结果
     */
    public int deleteMdWorkstationWorkerByRecordIds(Long[] recordIds);

    /**
     * 删除人力资源信息
     * 
     * @param recordId 人力资源主键
     * @return 结果
     */
    public int deleteMdWorkstationWorkerByRecordId(Long recordId);

    /**
     * 根据工作站ID删除对应的人员信息
     * @param workstationId
     * @return
     */
    public int deleteByWorkstationId(Long workstationId);
}
