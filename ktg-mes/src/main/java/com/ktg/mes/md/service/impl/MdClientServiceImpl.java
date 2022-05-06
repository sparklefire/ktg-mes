package com.ktg.mes.md.service.impl;

import java.util.List;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.DateUtils;
import com.ktg.common.utils.StringUtils;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.mes.md.mapper.MdClientMapper;
import com.ktg.mes.md.domain.MdClient;
import com.ktg.mes.md.service.IMdClientService;

/**
 * 客户Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-06
 */
@Service
public class MdClientServiceImpl implements IMdClientService 
{
    @Autowired
    private MdClientMapper mdClientMapper;

    /**
     * 查询客户
     * 
     * @param clientId 客户主键
     * @return 客户
     */
    @Override
    public MdClient selectMdClientByClientId(Long clientId)
    {
        return mdClientMapper.selectMdClientByClientId(clientId);
    }

    /**
     * 查询客户列表
     * 
     * @param mdClient 客户
     * @return 客户
     */
    @Override
    public List<MdClient> selectMdClientList(MdClient mdClient)
    {
        return mdClientMapper.selectMdClientList(mdClient);
    }

    @Override
    public String checkClientCodeUnique(MdClient mdClient) {
        MdClient client = mdClientMapper.checkClientCodeUnique(mdClient);
        Long clientId = mdClient.getClientId()==null?-1L:mdClient.getClientId();
        if(StringUtils.isNotNull(client) && client.getClientId().longValue() !=clientId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkClientNameUnique(MdClient mdClient) {
        MdClient client = mdClientMapper.checkClientNameUnique(mdClient);
        Long clientId = mdClient.getClientId()==null?-1L:mdClient.getClientId();
        if(StringUtils.isNotNull(client) && client.getClientId().longValue() !=clientId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkClientNickUnique(MdClient mdClient) {
        MdClient client = mdClientMapper.checkClientNickUnique(mdClient);
        Long clientId = mdClient.getClientId()==null?-1L:mdClient.getClientId();
        if(StringUtils.isNotNull(client) && client.getClientId().longValue() !=clientId.longValue()){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增客户
     * 
     * @param mdClient 客户
     * @return 结果
     */
    @Override
    public int insertMdClient(MdClient mdClient)
    {
        mdClient.setCreateTime(DateUtils.getNowDate());
        return mdClientMapper.insertMdClient(mdClient);
    }

    /**
     * 修改客户
     * 
     * @param mdClient 客户
     * @return 结果
     */
    @Override
    public int updateMdClient(MdClient mdClient)
    {
        mdClient.setUpdateTime(DateUtils.getNowDate());
        return mdClientMapper.updateMdClient(mdClient);
    }

    /**
     * 批量删除客户
     * 
     * @param clientIds 需要删除的客户主键
     * @return 结果
     */
    @Override
    public int deleteMdClientByClientIds(Long[] clientIds)
    {
        return mdClientMapper.deleteMdClientByClientIds(clientIds);
    }

    /**
     * 删除客户信息
     * 
     * @param clientId 客户主键
     * @return 结果
     */
    @Override
    public int deleteMdClientByClientId(Long clientId)
    {
        return mdClientMapper.deleteMdClientByClientId(clientId);
    }
}
