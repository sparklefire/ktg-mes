package com.ktg.mes.md.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 工装夹具资源对象 md_workstation_tool
 * 
 * @author yinjinlu
 * @date 2022-05-12
 */
public class MdWorkstationTool extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long recordId;

    /** 工作站ID */
    @Excel(name = "工作站ID")
    private Long workstationId;

    /** 工装夹具类型ID */
    @Excel(name = "工装夹具类型ID")
    private Long toolTypeId;

    /** 类型编码 */
    @Excel(name = "类型编码")
    private String toolTypeCode;

    /** 类型名称 */
    @Excel(name = "类型名称")
    private String toolTypeName;

    /** 数量 */
    @Excel(name = "数量")
    private Integer quantity;

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
    public void setWorkstationId(Long workstationId) 
    {
        this.workstationId = workstationId;
    }

    public Long getWorkstationId() 
    {
        return workstationId;
    }
    public void setToolTypeId(Long toolTypeId) 
    {
        this.toolTypeId = toolTypeId;
    }

    public Long getToolTypeId() 
    {
        return toolTypeId;
    }
    public void setToolTypeCode(String toolTypeCode) 
    {
        this.toolTypeCode = toolTypeCode;
    }

    public String getToolTypeCode() 
    {
        return toolTypeCode;
    }
    public void setToolTypeName(String toolTypeName) 
    {
        this.toolTypeName = toolTypeName;
    }

    public String getToolTypeName() 
    {
        return toolTypeName;
    }
    public void setQuantity(Integer quantity) 
    {
        this.quantity = quantity;
    }

    public Integer getQuantity() 
    {
        return quantity;
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
            .append("workstationId", getWorkstationId())
            .append("toolTypeId", getToolTypeId())
            .append("toolTypeCode", getToolTypeCode())
            .append("toolTypeName", getToolTypeName())
            .append("quantity", getQuantity())
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
