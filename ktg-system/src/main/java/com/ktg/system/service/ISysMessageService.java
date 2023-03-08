package com.ktg.system.service;

import java.util.List;
import com.ktg.system.domain.SysMessage;

/**
 * 消息Service接口
 * 
 * @author yinjinlu
 * @date 2023-03-06
 */
public interface ISysMessageService 
{
    /**
     * 查询消息
     * 
     * @param messageId 消息主键
     * @return 消息
     */
    public SysMessage selectSysMessageByMessageId(Long messageId);

    /**
     * 查询消息列表
     * 
     * @param sysMessage 消息
     * @return 消息集合
     */
    public List<SysMessage> selectSysMessageList(SysMessage sysMessage);

    /**
     * 新增消息
     * 
     * @param sysMessage 消息
     * @return 结果
     */
    public int insertSysMessage(SysMessage sysMessage);

    /**
     * 修改消息
     * 
     * @param sysMessage 消息
     * @return 结果
     */
    public int updateSysMessage(SysMessage sysMessage);

    /**
     * 批量删除消息
     * 
     * @param messageIds 需要删除的消息主键集合
     * @return 结果
     */
    public int deleteSysMessageByMessageIds(Long[] messageIds);

    /**
     * 删除消息信息
     * 
     * @param messageId 消息主键
     * @return 结果
     */
    public int deleteSysMessageByMessageId(Long messageId);
}
