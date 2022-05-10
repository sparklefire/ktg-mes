package com.ktg.mes.tm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 工装夹具类型对象 tm_tool_type
 * 
 * @author yinjinlu
 * @date 2022-05-10
 */
public class TmToolType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 类型ID */
    private Long toolTypeId;

    /** 类型编码 */
    @Excel(name = "类型编码")
    private String toolTypeCode;

    /** 类型名称 */
    @Excel(name = "类型名称")
    private String toolTypeName;

    /** 是否启用 */
    @Excel(name = "是否启用")
    private String codeFlag;

    /** 保养维护类型 */
    @Excel(name = "保养维护类型")
    private String maintenType;

    /** 保养周期 */
    @Excel(name = "保养周期")
    private Long maintenPeriod;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

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
    public void setCodeFlag(String codeFlag) 
    {
        this.codeFlag = codeFlag;
    }

    public String getCodeFlag() 
    {
        return codeFlag;
    }
    public void setMaintenType(String maintenType) 
    {
        this.maintenType = maintenType;
    }

    public String getMaintenType() 
    {
        return maintenType;
    }
    public void setMaintenPeriod(Long maintenPeriod) 
    {
        this.maintenPeriod = maintenPeriod;
    }

    public Long getMaintenPeriod() 
    {
        return maintenPeriod;
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
            .append("toolTypeId", getToolTypeId())
            .append("toolTypeCode", getToolTypeCode())
            .append("toolTypeName", getToolTypeName())
            .append("codeFlag", getCodeFlag())
            .append("maintenType", getMaintenType())
            .append("maintenPeriod", getMaintenPeriod())
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
