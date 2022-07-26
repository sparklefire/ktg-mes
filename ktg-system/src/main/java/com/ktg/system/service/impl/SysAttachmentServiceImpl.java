package com.ktg.system.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.system.mapper.SysAttachmentMapper;
import com.ktg.system.domain.SysAttachment;
import com.ktg.system.service.ISysAttachmentService;

/**
 * 附件Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-07-26
 */
@Service
public class SysAttachmentServiceImpl implements ISysAttachmentService 
{
    @Autowired
    private SysAttachmentMapper sysAttachmentMapper;

    /**
     * 查询附件
     * 
     * @param attachmentId 附件主键
     * @return 附件
     */
    @Override
    public SysAttachment selectSysAttachmentByAttachmentId(Long attachmentId)
    {
        return sysAttachmentMapper.selectSysAttachmentByAttachmentId(attachmentId);
    }

    /**
     * 查询附件列表
     * 
     * @param sysAttachment 附件
     * @return 附件
     */
    @Override
    public List<SysAttachment> selectSysAttachmentList(SysAttachment sysAttachment)
    {
        return sysAttachmentMapper.selectSysAttachmentList(sysAttachment);
    }

    /**
     * 新增附件
     * 
     * @param sysAttachment 附件
     * @return 结果
     */
    @Override
    public int insertSysAttachment(SysAttachment sysAttachment)
    {
        sysAttachment.setCreateTime(DateUtils.getNowDate());
        return sysAttachmentMapper.insertSysAttachment(sysAttachment);
    }

    /**
     * 修改附件
     * 
     * @param sysAttachment 附件
     * @return 结果
     */
    @Override
    public int updateSysAttachment(SysAttachment sysAttachment)
    {
        sysAttachment.setUpdateTime(DateUtils.getNowDate());
        return sysAttachmentMapper.updateSysAttachment(sysAttachment);
    }

    /**
     * 批量删除附件
     * 
     * @param attachmentIds 需要删除的附件主键
     * @return 结果
     */
    @Override
    public int deleteSysAttachmentByAttachmentIds(Long[] attachmentIds)
    {
        return sysAttachmentMapper.deleteSysAttachmentByAttachmentIds(attachmentIds);
    }

    /**
     * 删除附件信息
     * 
     * @param attachmentId 附件主键
     * @return 结果
     */
    @Override
    public int deleteSysAttachmentByAttachmentId(Long attachmentId)
    {
        return sysAttachmentMapper.deleteSysAttachmentByAttachmentId(attachmentId);
    }
}
