package com.ktg.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 消息对象 sys_message
 * 
 * @author yinjinlu
 * @date 2023-03-06
 */
public class SysMessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 附件ID */
    private Long messageId;

    /** 消息类型 */
    @Excel(name = "消息类型")
    private String messageType;

    /** 消息级别 */
    @Excel(name = "消息级别")
    private String messageLevel;

    /** 标题 */
    @Excel(name = "标题")
    private String messageTitle;

    /** 内容 */
    @Excel(name = "内容")
    private String messageContent;

    /** 发送人ID */
    @Excel(name = "发送人ID")
    private Long senderId;

    /** 发送人名称 */
    @Excel(name = "发送人名称")
    private String senderName;

    /** 发送人昵称 */
    @Excel(name = "发送人昵称")
    private String senderNick;

    /** 接收人ID */
    @Excel(name = "接收人ID")
    private Long recipientId;

    /** 接收人名称 */
    @Excel(name = "接收人名称")
    private String recipientName;

    /** 接收人昵称 */
    @Excel(name = "接收人昵称")
    private String recipientNick;

    /** 处理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date processTime;

    /** 回调地址 */
    @Excel(name = "回调地址")
    private String callBack;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private String deletedFlag;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

    public void setMessageId(Long messageId) 
    {
        this.messageId = messageId;
    }

    public Long getMessageId() 
    {
        return messageId;
    }
    public void setMessageType(String messageType) 
    {
        this.messageType = messageType;
    }

    public String getMessageType() 
    {
        return messageType;
    }
    public void setMessageLevel(String messageLevel) 
    {
        this.messageLevel = messageLevel;
    }

    public String getMessageLevel() 
    {
        return messageLevel;
    }
    public void setMessageTitle(String messageTitle) 
    {
        this.messageTitle = messageTitle;
    }

    public String getMessageTitle() 
    {
        return messageTitle;
    }
    public void setMessageContent(String messageContent) 
    {
        this.messageContent = messageContent;
    }

    public String getMessageContent() 
    {
        return messageContent;
    }
    public void setSenderId(Long senderId) 
    {
        this.senderId = senderId;
    }

    public Long getSenderId() 
    {
        return senderId;
    }
    public void setSenderName(String senderName) 
    {
        this.senderName = senderName;
    }

    public String getSenderName() 
    {
        return senderName;
    }
    public void setSenderNick(String senderNick) 
    {
        this.senderNick = senderNick;
    }

    public String getSenderNick() 
    {
        return senderNick;
    }
    public void setRecipientId(Long recipientId) 
    {
        this.recipientId = recipientId;
    }

    public Long getRecipientId() 
    {
        return recipientId;
    }
    public void setRecipientName(String recipientName) 
    {
        this.recipientName = recipientName;
    }

    public String getRecipientName() 
    {
        return recipientName;
    }
    public void setRecipientNick(String recipientNick) 
    {
        this.recipientNick = recipientNick;
    }

    public String getRecipientNick() 
    {
        return recipientNick;
    }
    public void setProcessTime(Date processTime) 
    {
        this.processTime = processTime;
    }

    public Date getProcessTime() 
    {
        return processTime;
    }
    public void setCallBack(String callBack) 
    {
        this.callBack = callBack;
    }

    public String getCallBack() 
    {
        return callBack;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDeletedFlag(String deletedFlag) 
    {
        this.deletedFlag = deletedFlag;
    }

    public String getDeletedFlag() 
    {
        return deletedFlag;
    }
    public void setAttr1(String attr1) 
    {
        this.attr1 = attr1;
    }

    public String getAttr1() 
    {
        return attr1;
    }
    public void setAttr2(String attr2) 
    {
        this.attr2 = attr2;
    }

    public String getAttr2() 
    {
        return attr2;
    }
    public void setAttr3(Long attr3) 
    {
        this.attr3 = attr3;
    }

    public Long getAttr3() 
    {
        return attr3;
    }
    public void setAttr4(Long attr4) 
    {
        this.attr4 = attr4;
    }

    public Long getAttr4() 
    {
        return attr4;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("messageId", getMessageId())
            .append("messageType", getMessageType())
            .append("messageLevel", getMessageLevel())
            .append("messageTitle", getMessageTitle())
            .append("messageContent", getMessageContent())
            .append("senderId", getSenderId())
            .append("senderName", getSenderName())
            .append("senderNick", getSenderNick())
            .append("recipientId", getRecipientId())
            .append("recipientName", getRecipientName())
            .append("recipientNick", getRecipientNick())
            .append("processTime", getProcessTime())
            .append("callBack", getCallBack())
            .append("status", getStatus())
            .append("deletedFlag", getDeletedFlag())
            .append("remark", getRemark())
            .append("attr1", getAttr1())
            .append("attr2", getAttr2())
            .append("attr3", getAttr3())
            .append("attr4", getAttr4())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
