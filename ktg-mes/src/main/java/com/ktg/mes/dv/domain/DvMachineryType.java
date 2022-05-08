package com.ktg.mes.dv.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.TreeEntity;

/**
 * 设备类型对象 dv_machinery_type
 * 
 * @author yinjinlu
 * @date 2022-05-08
 */
public class DvMachineryType extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备类型ID */
    private Long machineryTypeId;

    /** 设备类型编码 */
    @Excel(name = "设备类型编码")
    private String machineryTypeCode;

    /** 设备类型名称 */
    @Excel(name = "设备类型名称")
    private String machineryTypeName;

    /** 父类型ID */
    @Excel(name = "父类型ID")
    private Long parentTypeId;

    /** 是否启用 */
    @Excel(name = "是否启用")
    private String enableFlag;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

    public void setMachineryTypeId(Long machineryTypeId) 
    {
        this.machineryTypeId = machineryTypeId;
    }

    public Long getMachineryTypeId() 
    {
        return machineryTypeId;
    }
    public void setMachineryTypeCode(String machineryTypeCode) 
    {
        this.machineryTypeCode = machineryTypeCode;
    }

    public String getMachineryTypeCode() 
    {
        return machineryTypeCode;
    }
    public void setMachineryTypeName(String machineryTypeName) 
    {
        this.machineryTypeName = machineryTypeName;
    }

    public String getMachineryTypeName() 
    {
        return machineryTypeName;
    }
    public void setParentTypeId(Long parentTypeId) 
    {
        this.parentTypeId = parentTypeId;
    }

    public Long getParentTypeId() 
    {
        return parentTypeId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("machineryTypeId", getMachineryTypeId())
            .append("machineryTypeCode", getMachineryTypeCode())
            .append("machineryTypeName", getMachineryTypeName())
            .append("parentTypeId", getParentTypeId())
            .append("ancestors", getAncestors())
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
            .toString();
    }
}
