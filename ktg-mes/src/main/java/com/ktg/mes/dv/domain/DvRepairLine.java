package com.ktg.mes.dv.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 设备维修单行对象 dv_repair_line
 * 
 * @author yinjinlu
 * @date 2022-08-08
 */
public class DvRepairLine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 行ID */
    private Long lineId;

    /** 维修单ID */
    @Excel(name = "维修单ID")
    private Long repairId;

    /** 项目ID */
    @Excel(name = "项目ID")
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

    /** 故障描述 */
    @Excel(name = "故障描述")
    private String malfunction;

    /** 故障描述资源 */
    @Excel(name = "故障描述资源")
    private String malfunctionUrl;

    /** 维修情况 */
    @Excel(name = "维修情况")
    private String repairDes;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

    public void setLineId(Long lineId) 
    {
        this.lineId = lineId;
    }

    public Long getLineId() 
    {
        return lineId;
    }
    public void setRepairId(Long repairId) 
    {
        this.repairId = repairId;
    }

    public Long getRepairId() 
    {
        return repairId;
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
    public void setMalfunction(String malfunction) 
    {
        this.malfunction = malfunction;
    }

    public String getMalfunction() 
    {
        return malfunction;
    }
    public void setMalfunctionUrl(String malfunctionUrl) 
    {
        this.malfunctionUrl = malfunctionUrl;
    }

    public String getMalfunctionUrl() 
    {
        return malfunctionUrl;
    }
    public void setRepairDes(String repairDes) 
    {
        this.repairDes = repairDes;
    }

    public String getRepairDes() 
    {
        return repairDes;
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
            .append("lineId", getLineId())
            .append("repairId", getRepairId())
            .append("subjectId", getSubjectId())
            .append("subjectCode", getSubjectCode())
            .append("subjectName", getSubjectName())
            .append("subjectType", getSubjectType())
            .append("subjectContent", getSubjectContent())
            .append("subjectStandard", getSubjectStandard())
            .append("malfunction", getMalfunction())
            .append("malfunctionUrl", getMalfunctionUrl())
            .append("repairDes", getRepairDes())
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
