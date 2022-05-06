package com.ktg.mes.md.domain;

import com.ktg.common.core.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MdItem extends BaseEntity {

    private static final long serialVersionUID = 1L;


    private Long itemId;
    private String itemCode;
    private String itemName;
    private String specification;
    private String unitOfMeasure;
    private String itemOrProduct;
    private Long itemTypeId;
    private String itemTypeCode;
    private String itemTypeName;
    private String enableFlag;
    private String safeStockFlag;
    private Double minStock;
    private Double maxStock;
    private String attr1;
    private String attr2;
    private String attr3;
    private String attr4;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    @NotBlank(message = "物料产品编码不能为空")
    @Size(min = 0,max = 64,message = "物料产品编码长度不能超过64个字符")
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    @NotBlank(message = "物料产品名称不能为空")
    @Size(min = 0,max = 255,message = "物料产品编码名称不能超过255个字符")
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Size(min = 0,max = 500,message = "规格型号不能超过255个字符")
    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    @NotBlank(message = "单位不能为空")
    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }


    public String getItemOrProduct() {
        return itemOrProduct;
    }

    public void setItemOrProduct(String itemOrProduct) {
        this.itemOrProduct = itemOrProduct;
    }

    @NotNull(message = "物料类型不能为空")
    public Long getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Long itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public String getItemTypeCode() {
        return itemTypeCode;
    }

    public void setItemTypeCode(String itemTypeCode) {
        this.itemTypeCode = itemTypeCode;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getSafeStockFlag() {
        return safeStockFlag;
    }

    public void setSafeStockFlag(String safeStockFlag) {
        this.safeStockFlag = safeStockFlag;
    }

    public Double getMinStock() {
        return minStock;
    }

    public void setMinStock(Double minStock) {
        this.minStock = minStock;
    }

    public Double getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(Double maxStock) {
        this.maxStock = maxStock;
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public String getAttr3() {
        return attr3;
    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    public String getAttr4() {
        return attr4;
    }

    public void setAttr4(String attr4) {
        this.attr4 = attr4;
    }


    @Override
    public String toString() {
        return "MdItem{" +
                "itemId=" + itemId +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", specification='" + specification + '\'' +
                ", unitOfMeasure='" + unitOfMeasure + '\'' +
                ", itemOrProduct='" + itemOrProduct + '\'' +
                ", itemTypeId=" + itemTypeId +
                ", itemTypeCode='" + itemTypeCode + '\'' +
                ", itemTypeName='" + itemTypeName + '\'' +
                ", enableFlag='" + enableFlag + '\'' +
                ", safeStockFlag='" + safeStockFlag + '\'' +
                ", minStock=" + minStock +
                ", maxStock=" + maxStock +
                ", attr1='" + attr1 + '\'' +
                ", attr2='" + attr2 + '\'' +
                ", attr3='" + attr3 + '\'' +
                ", attr4='" + attr4 + '\'' +
                '}';
    }
}
