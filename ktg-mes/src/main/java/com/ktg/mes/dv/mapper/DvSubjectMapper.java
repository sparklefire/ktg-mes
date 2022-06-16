package com.ktg.mes.dv.mapper;

import java.util.List;
import com.ktg.mes.dv.domain.DvSubject;

/**
 * 设备点检保养项目Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-06-16
 */
public interface DvSubjectMapper 
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


    public DvSubject checkSubjectCodeUnique(DvSubject dvSubject);

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
     * 删除设备点检保养项目
     * 
     * @param subjectId 设备点检保养项目主键
     * @return 结果
     */
    public int deleteDvSubjectBySubjectId(Long subjectId);

    /**
     * 批量删除设备点检保养项目
     * 
     * @param subjectIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDvSubjectBySubjectIds(Long[] subjectIds);
}
