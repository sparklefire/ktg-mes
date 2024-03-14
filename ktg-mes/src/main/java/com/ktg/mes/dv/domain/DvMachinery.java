package com.ktg.mes.dv.domain;

import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 设备对象 dv_machinery
 * 
 * @author yinjinlu
 * @date 2022-05-08
 */
public class DvMachinery extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备类型ID */
    private Long machineryId;

    /** 设备类型编码 */
    @Excel(name = "设备编码")
    private String machineryCode;

    /** 设备类型名称 */
    @Excel(name = "设备名称")
    private String machineryName;

    /** 品牌 */
    @Excel(name = "品牌")
    private String machineryBrand;

    /** 规格型号 */
    @Excel(name = "规格型号")
    private String machinerySpec;

    /** 设备类型ID */
    @Excel(name = "设备类型ID")
    private Long machineryTypeId;

    /** 设备类型编码 */
    @Excel(name = "设备类型编码")
    private String machineryTypeCode;

    /** 设备类型名称 */
    @Excel(name = "设备类型名称")
    private String machineryTypeName;

    /** 所属车间ID */
    @Excel(name = "所属车间ID")
    private Long workshopId;

    /** 所属车间编码 */
    @Excel(name = "所属车间编码")
    private String workshopCode;

    /** 所属车间名称 */
    @Excel(name = "所属车间名称")
    private String workshopName;

    /** 设备状态 */
    @Excel(name = "设备状态")
    private String status;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

    public void setMachineryId(Long machineryId) 
    {
        this.machineryId = machineryId;
    }

    public Long getMachineryId() 
    {
        return machineryId;
    }
    public void setMachineryCode(String machineryCode) 
    {
        this.machineryCode = machineryCode;
    }

    public String getMachineryCode() 
    {
        return machineryCode;
    }
    public void setMachineryName(String machineryName) 
    {
        this.machineryName = machineryName;
    }

    public String getMachineryName() 
    {
        return machineryName;
    }
    public void setMachineryBrand(String machineryBrand) 
    {
        this.machineryBrand = machineryBrand;
    }

    public String getMachineryBrand() 
    {
        return machineryBrand;
    }
    public void setMachinerySpec(String machinerySpec) 
    {
        this.machinerySpec = machinerySpec;
    }

    public String getMachinerySpec() 
    {
        return machinerySpec;
    }
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
    public void setWorkshopId(Long workshopId) 
    {
        this.workshopId = workshopId;
    }

    public Long getWorkshopId() 
    {
        return workshopId;
    }
    public void setWorkshopCode(String workshopCode) 
    {
        this.workshopCode = workshopCode;
    }

    public String getWorkshopCode() 
    {
        return workshopCode;
    }
    public void setWorkshopName(String workshopName) 
    {
        this.workshopName = workshopName;
    }

    public String getWorkshopName() 
    {
        return workshopName;
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
            .append("machineryId", getMachineryId())
            .append("machineryCode", getMachineryCode())
            .append("machineryName", getMachineryName())
            .append("machineryBrand", getMachineryBrand())
            .append("machinerySpec", getMachinerySpec())
            .append("machineryTypeId", getMachineryTypeId())
            .append("machineryTypeCode", getMachineryTypeCode())
            .append("machineryTypeName", getMachineryTypeName())
            .append("workshopId", getWorkshopId())
            .append("workshopCode", getWorkshopCode())
            .append("workshopName", getWorkshopName())
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
