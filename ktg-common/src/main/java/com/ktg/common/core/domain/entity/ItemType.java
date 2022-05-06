package com.ktg.common.core.domain.entity;

import com.ktg.common.core.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ItemType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long itemTypeId;
    private String itemTypeCode;
    private String itemTypeName;
    private Long parentTypeId;
    private String ancestors;
    private String itemOrProduct;
    private Integer orderNum;
    private String enableFlag;
    private String attr1;
    private String attr2;
    private Integer attr3;
    private Integer attr4;
    private List<ItemType> children = new ArrayList<ItemType>();


    public Long getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Long itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    @Size(min = 0,max = 64,message = "物料分类编码长度不能超过64个字符")
    public String getItemTypeCode() {
        return itemTypeCode;
    }

    public void setItemTypeCode(String itemTypeCode) {
        this.itemTypeCode = itemTypeCode;
    }

    @NotBlank(message = "物料分类名称不能为空")
    @Size(min = 0,max = 255,message = "物料分类名称长度不能超过255个字符")
    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    public Long getParentTypeId() {
        return parentTypeId;
    }

    public void setParentTypeId(Long parentTypeId) {
        this.parentTypeId = parentTypeId;
    }

    public String getAncestors() {
        return ancestors;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    @NotBlank(message = "请指定是物料分类还是产品分类")
    public String getItemOrProduct() {
        return itemOrProduct;
    }

    public void setItemOrProduct(String itemOrProduct) {
        this.itemOrProduct = itemOrProduct;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @NotBlank(message = "请设置是否启用")
    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
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

    public Integer getAttr3() {
        return attr3;
    }

    public void setAttr3(Integer attr3) {
        this.attr3 = attr3;
    }

    public Integer getAttr4() {
        return attr4;
    }

    public void setAttr4(Integer attr4) {
        this.attr4 = attr4;
    }

    public List<ItemType> getChildren() {
        return children;
    }

    public void setChildren(List<ItemType> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "ItemType{" +
                "itemTypeId=" + itemTypeId +
                ", itemTypeCode='" + itemTypeCode + '\'' +
                ", itemTypeName='" + itemTypeName + '\'' +
                ", parentTypeId=" + parentTypeId +
                ", itemOrProduct='" + itemOrProduct + '\'' +
                ", orderNum=" + orderNum +
                ", enableFlag='" + enableFlag + '\'' +
                ", attr1='" + attr1 + '\'' +
                ", attr2='" + attr2 + '\'' +
                ", attr3=" + attr3 +
                ", attr4=" + attr4 +
                '}';
    }
}
