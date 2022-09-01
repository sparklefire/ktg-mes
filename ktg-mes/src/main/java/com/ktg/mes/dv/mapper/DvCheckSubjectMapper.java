package com.ktg.mes.dv.mapper;

import java.util.List;
import com.ktg.mes.dv.domain.DvCheckSubject;

/**
 * 点检项目Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-06-18
 */
public interface DvCheckSubjectMapper 
{
    /**
     * 查询点检项目
     * 
     * @param recordId 点检项目主键
     * @return 点检项目
     */
    public DvCheckSubject selectDvCheckSubjectByRecordId(Long recordId);

    /**
     * 查询点检项目列表
     * 
     * @param dvCheckSubject 点检项目
     * @return 点检项目集合
     */
    public List<DvCheckSubject> selectDvCheckSubjectList(DvCheckSubject dvCheckSubject);


    public DvCheckSubject checkSubjectUnique(DvCheckSubject dvCheckSubject);

    /**
     * 新增点检项目
     * 
     * @param dvCheckSubject 点检项目
     * @return 结果
     */
    public int insertDvCheckSubject(DvCheckSubject dvCheckSubject);

    /**
     * 修改点检项目
     * 
     * @param dvCheckSubject 点检项目
     * @return 结果
     */
    public int updateDvCheckSubject(DvCheckSubject dvCheckSubject);

    /**
     * 删除点检项目
     * 
     * @param recordId 点检项目主键
     * @return 结果
     */
    public int deleteDvCheckSubjectByRecordId(Long recordId);

    /**
     * 批量删除点检项目
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDvCheckSubjectByRecordIds(Long[] recordIds);

    /**
     * 根据计划ID删除对应的计划项目
     * @param planId
     * @return
     */
    public int deleteByPlanId(Long planId);
}
