package com.ktg.system.mapper;

import java.util.List;
import com.ktg.system.domain.SysMessage;

/**
 * 消息Mapper接口
 * 
 * @author yinjinlu
 * @date 2023-03-06
 */
public interface SysMessageMapper 
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
     * 删除消息
     * 
     * @param messageId 消息主键
     * @return 结果
     */
    public int deleteSysMessageByMessageId(Long messageId);

    /**
     * 批量删除消息
     * 
     * @param messageIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysMessageByMessageIds(Long[] messageIds);
}
