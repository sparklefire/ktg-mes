package com.ktg.mes.md.domain;

import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 单位对象 md_unit_measure
 * 
 * @author ruoyi
 * @date 2022-04-27
 */
public class MdUnitMeasure extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 单位ID */
    private Long measureId;

    /** 单位编码 */
    @Excel(name = "单位编码")
    private String measureCode;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private String measureName;

    /** 是否是主单位 */
    @Excel(name = "是否是主单位")
    private String primaryFlag;

    /** 主单位ID */
    @Excel(name = "主单位ID")
    private Long primaryId;

    /** 与主单位换算比例 */
    @Excel(name = "与主单位换算比例")
    private BigDecimal changeRate;

    /** 是否启用 */
    @Excel(name = "是否启用")
    private String enableFlag;

    /** 预留字段1 */
    @Excel(name = "预留字段1")
    private String attr1;

    /** 预留字段2 */
    @Excel(name = "预留字段2")
    private String attr2;

    /** 预留字段3 */
    @Excel(name = "预留字段3")
    private Long attr3;

    /** 预留字段4 */
    @Excel(name = "预留字段4")
    private Long attr4;

    public void setMeasureId(Long measureId) 
    {
        this.measureId = measureId;
    }

    public Long getMeasureId() 
    {
        return measureId;
    }
    public void setMeasureCode(String measureCode) 
    {
        this.measureCode = measureCode;
    }

    public String getMeasureCode() 
    {
        return measureCode;
    }
    public void setMeasureName(String measureName) 
    {
        this.measureName = measureName;
    }

    public String getMeasureName() 
    {
        return measureName;
    }
    public void setPrimaryFlag(String primaryFlag) 
    {
        this.primaryFlag = primaryFlag;
    }

    public String getPrimaryFlag() 
    {
        return primaryFlag;
    }
    public void setPrimaryId(Long primaryId) 
    {
        this.primaryId = primaryId;
    }

    public Long getPrimaryId() 
    {
        return primaryId;
    }
    public void setChangeRate(BigDecimal changeRate) 
    {
        this.changeRate = changeRate;
    }

    public BigDecimal getChangeRate() 
    {
        return changeRate;
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
            .append("measureId", getMeasureId())
            .append("measureCode", getMeasureCode())
            .append("measureName", getMeasureName())
            .append("primaryFlag", getPrimaryFlag())
            .append("primaryId", getPrimaryId())
            .append("changeRate", getChangeRate())
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
