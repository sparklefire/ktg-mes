package com.ktg.mes.dv.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 点检项目对象 dv_check_subject
 * 
 * @author yinjinlu
 * @date 2022-06-18
 */
public class DvCheckSubject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private Long recordId;

    /** 计划ID */
    @Excel(name = "计划ID")
    private Long planId;

    /** 设备ID */
    @Excel(name = "设备ID")
    private Long subjectId;

    /** 项目编码 */
    @Excel(name = "项目编码")
    private String subjectCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String subjectName;

    /** 项目类型 */
    @Excel(name = "项目类型")
    private String subjectType;

    /** 项目内容 */
    @Excel(name = "项目内容")
    private String subjectContent;

    /** 标准 */
    @Excel(name = "标准")
    private String subjectStandard;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

    public void setRecordId(Long recordId) 
    {
        this.recordId = recordId;
    }

    public Long getRecordId() 
    {
        return recordId;
    }
    public void setPlanId(Long planId) 
    {
        this.planId = planId;
    }

    public Long getPlanId() 
    {
        return planId;
    }
    public void setSubjectId(Long subjectId) 
    {
        this.subjectId = subjectId;
    }

    public Long getSubjectId() 
    {
        return subjectId;
    }
    public void setSubjectCode(String subjectCode) 
    {
        this.subjectCode = subjectCode;
    }

    public String getSubjectCode() 
    {
        return subjectCode;
    }
    public void setSubjectName(String subjectName) 
    {
        this.subjectName = subjectName;
    }

    public String getSubjectName() 
    {
        return subjectName;
    }
    public void setSubjectType(String subjectType) 
    {
        this.subjectType = subjectType;
    }

    public String getSubjectType() 
    {
        return subjectType;
    }
    public void setSubjectContent(String subjectContent) 
    {
        this.subjectContent = subjectContent;
    }

    public String getSubjectContent() 
    {
        return subjectContent;
    }
    public void setSubjectStandard(String subjectStandard) 
    {
        this.subjectStandard = subjectStandard;
    }

    public String getSubjectStandard() 
    {
        return subjectStandard;
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
            .append("recordId", getRecordId())
            .append("planId", getPlanId())
            .append("subjectId", getSubjectId())
            .append("subjectCode", getSubjectCode())
            .append("subjectName", getSubjectName())
            .append("subjectType", getSubjectType())
            .append("subjectContent", getSubjectContent())
            .append("subjectStandard", getSubjectStandard())
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
