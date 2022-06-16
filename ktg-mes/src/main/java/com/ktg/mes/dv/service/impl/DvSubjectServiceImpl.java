package com.ktg.mes.dv.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.dv.mapper.DvSubjectMapper;
import com.ktg.mes.dv.domain.DvSubject;
import com.ktg.mes.dv.service.IDvSubjectService;

/**
 * 设备点检保养项目Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-06-16
 */
@Service
public class DvSubjectServiceImpl implements IDvSubjectService 
{
    @Autowired
    private DvSubjectMapper dvSubjectMapper;

    /**
     * 查询设备点检保养项目
     * 
     * @param subjectId 设备点检保养项目主键
     * @return 设备点检保养项目
     */
    @Override
    public DvSubject selectDvSubjectBySubjectId(Long subjectId)
    {
        return dvSubjectMapper.selectDvSubjectBySubjectId(subjectId);
    }

    /**
     * 查询设备点检保养项目列表
     * 
     * @param dvSubject 设备点检保养项目
     * @return 设备点检保养项目
     */
    @Override
    public List<DvSubject> selectDvSubjectList(DvSubject dvSubject)
    {
        return dvSubjectMapper.selectDvSubjectList(dvSubject);
    }

    @Override
    public String checkSubjectCodeUnique(DvSubject dvSubject) {
        DvSubject subject = dvSubjectMapper.checkSubjectCodeUnique(dvSubject);
        Long subjectId = dvSubject.getSubjectId()==null?-1L:dvSubject.getSubjectId();
        if(StringUtils.isNotNull(subject) && subject.getSubjectId().longValue() == subjectId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增设备点检保养项目
     * 
     * @param dvSubject 设备点检保养项目
     * @return 结果
     */
    @Override
    public int insertDvSubject(DvSubject dvSubject)
    {
        dvSubject.setCreateTime(DateUtils.getNowDate());
        return dvSubjectMapper.insertDvSubject(dvSubject);
    }

    /**
     * 修改设备点检保养项目
     * 
     * @param dvSubject 设备点检保养项目
     * @return 结果
     */
    @Override
    public int updateDvSubject(DvSubject dvSubject)
    {
        dvSubject.setUpdateTime(DateUtils.getNowDate());
        return dvSubjectMapper.updateDvSubject(dvSubject);
    }

    /**
     * 批量删除设备点检保养项目
     * 
     * @param subjectIds 需要删除的设备点检保养项目主键
     * @return 结果
     */
    @Override
    public int deleteDvSubjectBySubjectIds(Long[] subjectIds)
    {
        return dvSubjectMapper.deleteDvSubjectBySubjectIds(subjectIds);
    }

    /**
     * 删除设备点检保养项目信息
     * 
     * @param subjectId 设备点检保养项目主键
     * @return 结果
     */
    @Override
    public int deleteDvSubjectBySubjectId(Long subjectId)
    {
        return dvSubjectMapper.deleteDvSubjectBySubjectId(subjectId);
    }
}
