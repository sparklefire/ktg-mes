package com.ktg.mes.pro.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.pro.mapper.ProUserWorkstationMapper;
import com.ktg.mes.pro.domain.ProUserWorkstation;
import com.ktg.mes.pro.service.IProUserWorkstationService;

/**
 * 用户工作站绑定关系Service业务层处理
 * 
 * @author yinjinlu
 * @date 2023-02-20
 */
@Service
public class ProUserWorkstationServiceImpl implements IProUserWorkstationService 
{
    @Autowired
    private ProUserWorkstationMapper proUserWorkstationMapper;

    /**
     * 查询用户工作站绑定关系
     * 
     * @param recordId 用户工作站绑定关系主键
     * @return 用户工作站绑定关系
     */
    @Override
    public ProUserWorkstation selectProUserWorkstationByRecordId(Long recordId)
    {
        return proUserWorkstationMapper.selectProUserWorkstationByRecordId(recordId);
    }

    /**
     * 查询用户工作站绑定关系列表
     * 
     * @param proUserWorkstation 用户工作站绑定关系
     * @return 用户工作站绑定关系
     */
    @Override
    public List<ProUserWorkstation> selectProUserWorkstationList(ProUserWorkstation proUserWorkstation)
    {
        return proUserWorkstationMapper.selectProUserWorkstationList(proUserWorkstation);
    }

    /**
     * 新增用户工作站绑定关系
     * 
     * @param proUserWorkstation 用户工作站绑定关系
     * @return 结果
     */
    @Override
    public int insertProUserWorkstation(ProUserWorkstation proUserWorkstation)
    {
        proUserWorkstation.setCreateTime(DateUtils.getNowDate());
        return proUserWorkstationMapper.insertProUserWorkstation(proUserWorkstation);
    }

    /**
     * 修改用户工作站绑定关系
     * 
     * @param proUserWorkstation 用户工作站绑定关系
     * @return 结果
     */
    @Override
    public int updateProUserWorkstation(ProUserWorkstation proUserWorkstation)
    {
        proUserWorkstation.setUpdateTime(DateUtils.getNowDate());
        return proUserWorkstationMapper.updateProUserWorkstation(proUserWorkstation);
    }

    /**
     * 批量删除用户工作站绑定关系
     * 
     * @param recordIds 需要删除的用户工作站绑定关系主键
     * @return 结果
     */
    @Override
    public int deleteProUserWorkstationByRecordIds(Long[] recordIds)
    {
        return proUserWorkstationMapper.deleteProUserWorkstationByRecordIds(recordIds);
    }

    /**
     * 删除用户工作站绑定关系信息
     * 
     * @param recordId 用户工作站绑定关系主键
     * @return 结果
     */
    @Override
    public int deleteProUserWorkstationByRecordId(Long recordId)
    {
        return proUserWorkstationMapper.deleteProUserWorkstationByRecordId(recordId);
    }


    /**
     * 根据用户名删除所有对应关系
     * @param userName
     * @return
     */
    public int deleteByUserName(String userName){
        return proUserWorkstationMapper.deleteByUserName(userName);
    }
}
