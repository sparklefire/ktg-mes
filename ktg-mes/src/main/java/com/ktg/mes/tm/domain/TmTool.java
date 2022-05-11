package com.ktg.mes.tm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 工装夹具清单对象 tm_tool
 * 
 * @author yinjinlu
 * @date 2022-05-11
 */
public class TmTool extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工装夹具ID */
    private Long toolId;

    /** 工装夹具编码 */
    @Excel(name = "工装夹具编码")
    private String toolCode;

    /** 工装夹具名称 */
    @Excel(name = "工装夹具名称")
    private String toolName;

    /** 品牌 */
    @Excel(name = "品牌")
    private String brand;

    /** 型号 */
    @Excel(name = "型号")
    private String spec;

    /** 工装夹具类型ID */
    @Excel(name = "工装夹具类型ID")
    private Long toolTypeId;

    /** 工装夹具类型编码 */
    @Excel(name = "工装夹具类型编码")
    private String toolTypeCode;

    /** 工装夹具类型名称 */
    @Excel(name = "工装夹具类型名称")
    private String toolTypeName;

    /** 是否单独编码管理 */
    @Excel(name = "是否单独编码管理")
    private String codeFlag;

    /** 数量 */
    @Excel(name = "数量")
    private Long quantity;

    /** 可用数量 */
    @Excel(name = "可用数量")
    private Long quantityAvail;

    /** 保养维护类型 */
    @Excel(name = "保养维护类型")
    private String maintenType;

    /** 下一次保养周期 */
    @Excel(name = "下一次保养周期")
    private Long nextMaintenPeriod;

    /** 下一次保养日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下一次保养日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date nextMaintenDate;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

    public void setToolId(Long toolId) 
    {
        this.toolId = toolId;
    }

    public Long getToolId() 
    {
        return toolId;
    }
    public void setToolCode(String toolCode) 
    {
        this.toolCode = toolCode;
    }

    public String getToolCode() 
    {
        return toolCode;
    }
    public void setToolName(String toolName) 
    {
        this.toolName = toolName;
    }

    public String getToolName() 
    {
        return toolName;
    }
    public void setBrand(String brand) 
    {
        this.brand = brand;
    }

    public String getBrand() 
    {
        return brand;
    }
    public void setSpec(String spec) 
    {
        this.spec = spec;
    }

    public String getSpec() 
    {
        return spec;
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
    public void setCodeFlag(String codeFlag) 
    {
        this.codeFlag = codeFlag;
    }

    public String getCodeFlag() 
    {
        return codeFlag;
    }
    public void setQuantity(Long quantity) 
    {
        this.quantity = quantity;
    }

    public Long getQuantity() 
    {
        return quantity;
    }
    public void setQuantityAvail(Long quantityAvail) 
    {
        this.quantityAvail = quantityAvail;
    }

    public Long getQuantityAvail() 
    {
        return quantityAvail;
    }
    public void setMaintenType(String maintenType) 
    {
        this.maintenType = maintenType;
    }

    public String getMaintenType() 
    {
        return maintenType;
    }
    public void setNextMaintenPeriod(Long nextMaintenPeriod) 
    {
        this.nextMaintenPeriod = nextMaintenPeriod;
    }

    public Long getNextMaintenPeriod() 
    {
        return nextMaintenPeriod;
    }
    public void setNextMaintenDate(Date nextMaintenDate) 
    {
        this.nextMaintenDate = nextMaintenDate;
    }

    public Date getNextMaintenDate() 
    {
        return nextMaintenDate;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
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
            .append("toolId", getToolId())
            .append("toolCode", getToolCode())
            .append("toolName", getToolName())
            .append("brand", getBrand())
            .append("spec", getSpec())
            .append("toolTypeId", getToolTypeId())
            .append("toolTypeCode", getToolTypeCode())
            .append("toolTypeName", getToolTypeName())
            .append("codeFlag", getCodeFlag())
            .append("quantity", getQuantity())
            .append("quantityAvail", getQuantityAvail())
            .append("maintenType", getMaintenType())
            .append("nextMaintenPeriod", getNextMaintenPeriod())
            .append("nextMaintenDate", getNextMaintenDate())
            .append("status", getStatus())
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
