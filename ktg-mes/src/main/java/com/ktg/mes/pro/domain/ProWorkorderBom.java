package com.ktg.mes.pro.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 生产工单BOM组成对象 pro_workorder_bom
 * 
 * @author yinjinlu
 * @date 2022-05-09
 */
public class ProWorkorderBom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** BOM行ID */
    private Long lineId;

    /** 生产工单ID */
    @Excel(name = "生产工单ID")
    private Long workorderId;

    /** BOM物料ID */
    @Excel(name = "BOM物料ID")
    private Long itemId;

    /** BOM物料编号 */
    @Excel(name = "BOM物料编号")
    private String itemCode;

    /** BOM物料名称 */
    @Excel(name = "BOM物料名称")
    private String itemName;

    /** 规格型号 */
    @Excel(name = "规格型号")
    private String itemSpc;

    /** 单位 */
    @Excel(name = "单位")
    private String unitOfMeasure;

    /** 物料产品标识 */
    @Excel(name = "物料产品标识")
    private String itemOrProduct;

    /** 预计使用量 */
    @Excel(name = "预计使用量")
    private BigDecimal quantity;

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
    public void setWorkorderId(Long workorderId) 
    {
        this.workorderId = workorderId;
    }

    public Long getWorkorderId() 
    {
        return workorderId;
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
    public void setItemSpc(String itemSpc) 
    {
        this.itemSpc = itemSpc;
    }

    public String getItemSpc() 
    {
        return itemSpc;
    }
    public void setUnitOfMeasure(String unitOfMeasure) 
    {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getUnitOfMeasure() 
    {
        return unitOfMeasure;
    }
    public void setItemOrProduct(String itemOrProduct) 
    {
        this.itemOrProduct = itemOrProduct;
    }

    public String getItemOrProduct() 
    {
        return itemOrProduct;
    }
    public void setQuantity(BigDecimal quantity) 
    {
        this.quantity = quantity;
    }

    public BigDecimal getQuantity() 
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
            .append("lineId", getLineId())
            .append("workorderId", getWorkorderId())
            .append("itemId", getItemId())
            .append("itemCode", getItemCode())
            .append("itemName", getItemName())
            .append("itemSpc", getItemSpc())
            .append("unitOfMeasure", getUnitOfMeasure())
            .append("itemOrProduct", getItemOrProduct())
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
