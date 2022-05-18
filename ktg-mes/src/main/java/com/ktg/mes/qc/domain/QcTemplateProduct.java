package com.ktg.mes.qc.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 检测模板-产品对象 qc_template_product
 * 
 * @author yinjinlu
 * @date 2022-05-18
 */
public class QcTemplateProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long recordId;

    /** 检测模板ID */
    @Excel(name = "检测模板ID")
    private Long templateId;

    /** 产品物料ID */
    @Excel(name = "产品物料ID")
    private Long itemId;

    /** 产品物料编码 */
    @Excel(name = "产品物料编码")
    private String itemCode;

    /** 产品物料名称 */
    @Excel(name = "产品物料名称")
    private String itemName;

    /** 规格型号 */
    @Excel(name = "规格型号")
    private String specification;

    /** 单位 */
    @Excel(name = "单位")
    private String unitOfMeasure;

    /** 最低检测数 */
    @Excel(name = "最低检测数")
    private Long quantityCheck;

    /** 最大不合格数 */
    @Excel(name = "最大不合格数")
    private Long quantityUnqualified;

    /** 最大致命缺陷率 */
    @Excel(name = "最大致命缺陷率")
    private BigDecimal crRate;

    /** 最大严重缺陷率 */
    @Excel(name = "最大严重缺陷率")
    private BigDecimal majRate;

    /** 最大轻微缺陷率 */
    @Excel(name = "最大轻微缺陷率")
    private BigDecimal minRate;

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
    public void setTemplateId(Long templateId) 
    {
        this.templateId = templateId;
    }

    public Long getTemplateId() 
    {
        return templateId;
    }
    public void setItemId(Long itemId) 
    {
        this.itemId = itemId;
    }

    public Long getItemId() 
    {
        return itemId;
    }
    public void setItemCode(String itemCode) 
    {
        this.itemCode = itemCode;
    }

    public String getItemCode() 
    {
        return itemCode;
    }
    public void setItemName(String itemName) 
    {
        this.itemName = itemName;
    }

    public String getItemName() 
    {
        return itemName;
    }
    public void setSpecification(String specification) 
    {
        this.specification = specification;
    }

    public String getSpecification() 
    {
        return specification;
    }
    public void setUnitOfMeasure(String unitOfMeasure) 
    {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getUnitOfMeasure() 
    {
        return unitOfMeasure;
    }
    public void setQuantityCheck(Long quantityCheck) 
    {
        this.quantityCheck = quantityCheck;
    }

    public Long getQuantityCheck() 
    {
        return quantityCheck;
    }
    public void setQuantityUnqualified(Long quantityUnqualified) 
    {
        this.quantityUnqualified = quantityUnqualified;
    }

    public Long getQuantityUnqualified() 
    {
        return quantityUnqualified;
    }
    public void setCrRate(BigDecimal crRate) 
    {
        this.crRate = crRate;
    }

    public BigDecimal getCrRate() 
    {
        return crRate;
    }
    public void setMajRate(BigDecimal majRate) 
    {
        this.majRate = majRate;
    }

    public BigDecimal getMajRate() 
    {
        return majRate;
    }
    public void setMinRate(BigDecimal minRate) 
    {
        this.minRate = minRate;
    }

    public BigDecimal getMinRate() 
    {
        return minRate;
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
            .append("templateId", getTemplateId())
            .append("itemId", getItemId())
            .append("itemCode", getItemCode())
            .append("itemName", getItemName())
            .append("specification", getSpecification())
            .append("unitOfMeasure", getUnitOfMeasure())
            .append("quantityCheck", getQuantityCheck())
            .append("quantityUnqualified", getQuantityUnqualified())
            .append("crRate", getCrRate())
            .append("majRate", getMajRate())
            .append("minRate", getMinRate())
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
