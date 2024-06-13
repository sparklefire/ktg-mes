package com.ktg.print.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 打印模板配置对象 print_template
 * 
 * @author yinjinlu
 * @date 2024-04-17
 */
public class PrintTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 模板ID */
    private Long templateId;

    /** 模板编号 */
    @Excel(name = "模板编号")
    private String templateCode;

    /** 模板名称 */
    @Excel(name = "模板名称")
    private String templateName;

    /** 模板类型 */
    @Excel(name = "模板类型")
    private String templateType;

    /** 模板内容 */
    @Excel(name = "模板内容")
    private String templateJson;

    private String paperType;

    private Integer templateWidth;

    private Integer templateHeight;

    /** 是否默认 */
    @Excel(name = "是否默认")
    private String isDefault;

    /** 启用状态 */
    @Excel(name = "启用状态")
    private String enableFlag;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

    /** $column.columnComment */
    @Excel(name = "启用状态")
    private String templatePic;

    public void setTemplateId(Long templateId) 
    {
        this.templateId = templateId;
    }

    public Long getTemplateId() 
    {
        return templateId;
    }
    public void setTemplateCode(String templateCode) 
    {
        this.templateCode = templateCode;
    }

    public String getTemplateCode() 
    {
        return templateCode;
    }
    public void setTemplateName(String templateName) 
    {
        this.templateName = templateName;
    }

    public String getTemplateName() 
    {
        return templateName;
    }
    public void setTemplateType(String templateType) 
    {
        this.templateType = templateType;
    }

    public String getTemplateType() 
    {
        return templateType;
    }
    public void setTemplateJson(String templateJson) 
    {
        this.templateJson = templateJson;
    }

    public String getTemplateJson() 
    {
        return templateJson;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public Integer getTemplateWidth() {
        return templateWidth;
    }

    public void setTemplateWidth(Integer templateWidth) {
        this.templateWidth = templateWidth;
    }

    public Integer getTemplateHeight() {
        return templateHeight;
    }

    public void setTemplateHeight(Integer templateHeight) {
        this.templateHeight = templateHeight;
    }

    public void setIsDefault(String isDefault)
    {
        this.isDefault = isDefault;
    }

    public String getIsDefault() 
    {
        return isDefault;
    }
    public void setEnableFlag(String enableFlag) 
    {
        this.enableFlag = enableFlag;
    }

    public String getEnableFlag() 
    {
        return enableFlag;
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
    public void setTemplatePic(String templatePic) 
    {
        this.templatePic = templatePic;
    }

    public String getTemplatePic() 
    {
        return templatePic;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("templateId", getTemplateId())
            .append("templateCode", getTemplateCode())
            .append("templateName", getTemplateName())
            .append("templateType", getTemplateType())
            .append("templateJson", getTemplateJson())
            .append("isDefault", getIsDefault())
            .append("enableFlag", getEnableFlag())
            .append("remark", getRemark())
            .append("attr1", getAttr1())
            .append("attr2", getAttr2())
            .append("attr3", getAttr3())
            .append("attr4", getAttr4())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("templatePic", getTemplatePic())
            .toString();
    }
}
