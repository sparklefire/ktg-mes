package com.ktg.mes.md.service;

import java.util.List;
import com.ktg.mes.md.domain.MdClient;

/**
 * 客户Service接口
 * 
 * @author yinjinlu
 * @date 2022-05-06
 */
public interface IMdClientService 
{
    /**
     * 查询客户
     * 
     * @param clientId 客户主键
     * @return 客户
     */
    public MdClient selectMdClientByClientId(Long clientId);

    /**
     * 查询客户列表
     * 
     * @param mdClient 客户
     * @return 客户集合
     */
    public List<MdClient> selectMdClientList(MdClient mdClient);

    public String checkClientCodeUnique(MdClient mdClient);

    public String checkClientNameUnique(MdClient mdClient);

    public String checkClientNickUnique(MdClient mdClient);

    /**
     * 新增客户
     * 
     * @param mdClient 客户
     * @return 结果
     */
    public int insertMdClient(MdClient mdClient);

    /**
     * 修改客户
     * 
     * @param mdClient 客户
     * @return 结果
     */
    public int updateMdClient(MdClient mdClient);

    /**
     * 批量删除客户
     * 
     * @param clientIds 需要删除的客户主键集合
     * @return 结果
     */
    public int deleteMdClientByClientIds(Long[] clientIds);

    /**
     * 删除客户信息
     * 
     * @param clientId 客户主键
     * @return 结果
     */
    public int deleteMdClientByClientId(Long clientId);
}
