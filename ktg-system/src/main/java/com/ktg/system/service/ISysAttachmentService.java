package com.ktg.system.service;

import java.util.List;
import com.ktg.system.domain.SysAttachment;

/**
 * 附件Service接口
 * 
 * @author yinjinlu
 * @date 2022-07-26
 */
public interface ISysAttachmentService 
{
    /**
     * 查询附件
     * 
     * @param attachmentId 附件主键
     * @return 附件
     */
    public SysAttachment selectSysAttachmentByAttachmentId(Long attachmentId);

    /**
     * 查询附件列表
     * 
     * @param sysAttachment 附件
     * @return 附件集合
     */
    public List<SysAttachment> selectSysAttachmentList(SysAttachment sysAttachment);

    /**
     * 新增附件
     * 
     * @param sysAttachment 附件
     * @return 结果
     */
    public int insertSysAttachment(SysAttachment sysAttachment);

    /**
     * 修改附件
     * 
     * @param sysAttachment 附件
     * @return 结果
     */
    public int updateSysAttachment(SysAttachment sysAttachment);

    /**
     * 批量删除附件
     * 
     * @param attachmentIds 需要删除的附件主键集合
     * @return 结果
     */
    public int deleteSysAttachmentByAttachmentIds(Long[] attachmentIds);

    /**
     * 删除附件信息
     * 
     * @param attachmentId 附件主键
     * @return 结果
     */
    public int deleteSysAttachmentByAttachmentId(Long attachmentId);
}
