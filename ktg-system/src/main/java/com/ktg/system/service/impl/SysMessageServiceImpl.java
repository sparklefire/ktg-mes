package com.ktg.system.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.system.mapper.SysMessageMapper;
import com.ktg.system.domain.SysMessage;
import com.ktg.system.service.ISysMessageService;

/**
 * 消息Service业务层处理
 * 
 * @author yinjinlu
 * @date 2023-03-06
 */
@Service
public class SysMessageServiceImpl implements ISysMessageService 
{
    @Autowired
    private SysMessageMapper sysMessageMapper;

    /**
     * 查询消息
     * 
     * @param messageId 消息主键
     * @return 消息
     */
    @Override
    public SysMessage selectSysMessageByMessageId(Long messageId)
    {
        return sysMessageMapper.selectSysMessageByMessageId(messageId);
    }

    /**
     * 查询消息列表
     * 
     * @param sysMessage 消息
     * @return 消息
     */
    @Override
    public List<SysMessage> selectSysMessageList(SysMessage sysMessage)
    {
        return sysMessageMapper.selectSysMessageList(sysMessage);
    }

    /**
     * 新增消息
     * 
     * @param sysMessage 消息
     * @return 结果
     */
    @Override
    public int insertSysMessage(SysMessage sysMessage)
    {
        sysMessage.setCreateTime(DateUtils.getNowDate());
        return sysMessageMapper.insertSysMessage(sysMessage);
    }

    /**
     * 修改消息
     * 
     * @param sysMessage 消息
     * @return 结果
     */
    @Override
    public int updateSysMessage(SysMessage sysMessage)
    {
        sysMessage.setUpdateTime(DateUtils.getNowDate());
        return sysMessageMapper.updateSysMessage(sysMessage);
    }

    /**
     * 批量删除消息
     * 
     * @param messageIds 需要删除的消息主键
     * @return 结果
     */
    @Override
    public int deleteSysMessageByMessageIds(Long[] messageIds)
    {
        return sysMessageMapper.deleteSysMessageByMessageIds(messageIds);
    }

    /**
     * 删除消息信息
     * 
     * @param messageId 消息主键
     * @return 结果
     */
    @Override
    public int deleteSysMessageByMessageId(Long messageId)
    {
        return sysMessageMapper.deleteSysMessageByMessageId(messageId);
    }
}
