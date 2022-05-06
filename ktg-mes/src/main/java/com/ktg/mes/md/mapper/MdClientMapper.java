package com.ktg.mes.md.mapper;

import java.util.List;
import com.ktg.mes.md.domain.MdClient;

/**
 * 客户Mapper接口
 * 
 * @author yinjinlu
 * @date 2022-05-06
 */
public interface MdClientMapper 
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

    /**
     * 检查客户编码是否重复
     * @param mdClient
     * @return
     */
    public MdClient checkClientCodeUnique(MdClient mdClient);

    /**
     * 检查客户名称是否重复
     * @param mdClient
     * @return
     */
    public MdClient checkClientNameUnique(MdClient mdClient);

    /**
     * 检查客户简称是否重复
     * @param mdClient
     * @return
     */
    public MdClient checkClientNickUnique(MdClient mdClient);


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
     * 删除客户
     * 
     * @param clientId 客户主键
     * @return 结果
     */
    public int deleteMdClientByClientId(Long clientId);

    /**
     * 批量删除客户
     * 
     * @param clientIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMdClientByClientIds(Long[] clientIds);
}
