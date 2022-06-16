package com.ktg.mes.dv.service;

import java.util.List;
import com.ktg.mes.dv.domain.DvSubject;

/**
 * 设备点检保养项目Service接口
 * 
 * @author yinjinlu
 * @date 2022-06-16
 */
public interface IDvSubjectService 
{
    /**
     * 查询设备点检保养项目
     * 
     * @param subjectId 设备点检保养项目主键
     * @return 设备点检保养项目
     */
    public DvSubject selectDvSubjectBySubjectId(Long subjectId);

    /**
     * 查询设备点检保养项目列表
     * 
     * @param dvSubject 设备点检保养项目
     * @return 设备点检保养项目集合
     */
    public List<DvSubject> selectDvSubjectList(DvSubject dvSubject);

    /**
     * 检查项目编码是否重复
     * @param dvSubject
     * @return
     */
    public String checkSubjectCodeUnique(DvSubject dvSubject);

    /**
     * 新增设备点检保养项目
     * 
     * @param dvSubject 设备点检保养项目
     * @return 结果
     */
    public int insertDvSubject(DvSubject dvSubject);

    /**
     * 修改设备点检保养项目
     * 
     * @param dvSubject 设备点检保养项目
     * @return 结果
     */
    public int updateDvSubject(DvSubject dvSubject);

    /**
     * 批量删除设备点检保养项目
     * 
     * @param subjectIds 需要删除的设备点检保养项目主键集合
     * @return 结果
     */
    public int deleteDvSubjectBySubjectIds(Long[] subjectIds);

    /**
     * 删除设备点检保养项目信息
     * 
     * @param subjectId 设备点检保养项目主键
     * @return 结果
     */
    public int deleteDvSubjectBySubjectId(Long subjectId);
}
