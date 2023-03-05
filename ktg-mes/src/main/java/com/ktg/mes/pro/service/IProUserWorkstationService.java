package com.ktg.mes.pro.service;

import java.util.List;
import com.ktg.mes.pro.domain.ProUserWorkstation;

/**
 * 用户工作站绑定关系Service接口
 * 
 * @author yinjinlu
 * @date 2023-02-20
 */
public interface IProUserWorkstationService 
{
    /**
     * 查询用户工作站绑定关系
     * 
     * @param recordId 用户工作站绑定关系主键
     * @return 用户工作站绑定关系
     */
    public ProUserWorkstation selectProUserWorkstationByRecordId(Long recordId);

    /**
     * 查询用户工作站绑定关系列表
     * 
     * @param proUserWorkstation 用户工作站绑定关系
     * @return 用户工作站绑定关系集合
     */
    public List<ProUserWorkstation> selectProUserWorkstationList(ProUserWorkstation proUserWorkstation);

    /**
     * 新增用户工作站绑定关系
     * 
     * @param proUserWorkstation 用户工作站绑定关系
     * @return 结果
     */
    public int insertProUserWorkstation(ProUserWorkstation proUserWorkstation);

    /**
     * 修改用户工作站绑定关系
     * 
     * @param proUserWorkstation 用户工作站绑定关系
     * @return 结果
     */
    public int updateProUserWorkstation(ProUserWorkstation proUserWorkstation);

    /**
     * 批量删除用户工作站绑定关系
     * 
     * @param recordIds 需要删除的用户工作站绑定关系主键集合
     * @return 结果
     */
    public int deleteProUserWorkstationByRecordIds(Long[] recordIds);

    /**
     * 删除用户工作站绑定关系信息
     * 
     * @param recordId 用户工作站绑定关系主键
     * @return 结果
     */
    public int deleteProUserWorkstationByRecordId(Long recordId);


    /**
     * 根据用户名删除所有对应关系
     * @param userName
     * @return
     */
    public int deleteByUserName(String userName);
}
