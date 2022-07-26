package com.ktg.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 附件对象 sys_attachment
 * 
 * @author yinjinlu
 * @date 2022-07-26
 */
public class SysAttachment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 附件ID */
    private Long attachmentId;

    /** 关联的业务单据ID */
    @Excel(name = "关联的业务单据ID")
    private Long sourceDocId;

    /** 业务单据类型 */
    @Excel(name = "业务单据类型")
    private String sourceDocType;

    /** 访问URL */
    @Excel(name = "访问URL")
    private String fileUrl;

    /** 域名 */
    @Excel(name = "域名")
    private String basePath;

    /** 文件名 */
    @Excel(name = "文件名")
    private String fileName;

    /** 原来的文件名 */
    @Excel(name = "原来的文件名")
    private String orignalName;

    /** 文件类型 */
    @Excel(name = "文件类型")
    private String fileType;

    /** 文件大小 */
    @Excel(name = "文件大小")
    private BigDecimal fileSize;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

    public void setAttachmentId(Long attachmentId) 
    {
        this.attachmentId = attachmentId;
    }

    public Long getAttachmentId() 
    {
        return attachmentId;
    }
    public void setSourceDocId(Long sourceDocId) 
    {
        this.sourceDocId = sourceDocId;
    }

    public Long getSourceDocId() 
    {
        return sourceDocId;
    }
    public void setSourceDocType(String sourceDocType) 
    {
        this.sourceDocType = sourceDocType;
    }

    public String getSourceDocType() 
    {
        return sourceDocType;
    }
    public void setFileUrl(String fileUrl) 
    {
        this.fileUrl = fileUrl;
    }

    public String getFileUrl() 
    {
        return fileUrl;
    }
    public void setBasePath(String basePath) 
    {
        this.basePath = basePath;
    }

    public String getBasePath() 
    {
        return basePath;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setOrignalName(String orignalName) 
    {
        this.orignalName = orignalName;
    }

    public String getOrignalName() 
    {
        return orignalName;
    }
    public void setFileType(String fileType) 
    {
        this.fileType = fileType;
    }

    public String getFileType() 
    {
        return fileType;
    }
    public void setFileSize(BigDecimal fileSize) 
    {
        this.fileSize = fileSize;
    }

    public BigDecimal getFileSize() 
    {
        return fileSize;
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
            .append("attachmentId", getAttachmentId())
            .append("sourceDocId", getSourceDocId())
            .append("sourceDocType", getSourceDocType())
            .append("fileUrl", getFileUrl())
            .append("basePath", getBasePath())
            .append("fileName", getFileName())
            .append("orignalName", getOrignalName())
            .append("fileType", getFileType())
            .append("fileSize", getFileSize())
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
