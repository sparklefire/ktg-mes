package com.ktg.mes.md.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 产品BOM关系对象 md_product_bom
 * 
 * @author yinjinlu
 * @date 2022-05-09
 */
public class MdProductBom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private Long bomId;

    /** 物料产品ID */
    @Excel(name = "物料产品ID")
    private Long itemId;

    /** BOM物料ID */
    @Excel(name = "BOM物料ID")
    private Long bomItemId;

    /** BOM物料编码 */
    @Excel(name = "BOM物料编码")
    private String bomItemCode;

    /** BOM物料名称 */
    @Excel(name = "BOM物料名称")
    private String bomItemName;

    /** BOM物料规格 */
    @Excel(name = "BOM物料规格")
    private String bomItemSpec;

    /** BOM物料单位 */
    @Excel(name = "BOM物料单位")
    private String unitOfMeasure;

    /** 产品物料标识 */
    @Excel(name = "产品物料标识")
    private String itemOrProduct;

    /** 物料使用比例 */
    @Excel(name = "物料使用比例")
    private BigDecimal quantity;

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

    public void setBomId(Long bomId) 
    {
        this.bomId = bomId;
    }

    public Long getBomId() 
    {
        return bomId;
    }
    public void setItemId(Long itemId) 
    {
        this.itemId = itemId;
    }

    public Long getItemId() 
    {
        return itemId;
    }
    public void setBomItemId(Long bomItemId) 
    {
        this.bomItemId = bomItemId;
    }

    public Long getBomItemId() 
    {
        return bomItemId;
    }
    public void setBomItemCode(String bomItemCode) 
    {
        this.bomItemCode = bomItemCode;
    }

    public String getBomItemCode() 
    {
        return bomItemCode;
    }
    public void setBomItemName(String bomItemName) 
    {
        this.bomItemName = bomItemName;
    }

    public String getBomItemName() 
    {
        return bomItemName;
    }
    public void setBomItemSpec(String bomItemSpec) 
    {
        this.bomItemSpec = bomItemSpec;
    }

    public String getBomItemSpec() 
    {
        return bomItemSpec;
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
            .append("bomId", getBomId())
            .append("itemId", getItemId())
            .append("bomItemId", getBomItemId())
            .append("bomItemCode", getBomItemCode())
            .append("bomItemName", getBomItemName())
            .append("bomItemSpec", getBomItemSpec())
            .append("unitOfMeasure", getUnitOfMeasure())
            .append("itemOrProduct", getItemOrProduct())
            .append("quantity", getQuantity())
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
