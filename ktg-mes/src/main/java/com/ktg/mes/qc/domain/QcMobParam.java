package com.ktg.mes.qc.domain;

import com.ktg.common.core.domain.BaseEntity;

/**
 * 专门用于移动端质量管理各种数据查询的参数传递
 */
public class QcMobParam extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 检验单的ID，可以是IQC检验单、PQC过程检验单、OQC出货检验单
     */
    private Long qcId;

    /**
     * 对应的物料/产品ID
     */
    private Long itemId;

    /**
     * 对应的物料/产品编码
     */
    private String itemCode;

    /**
     * 对应的物料/产品名称
     */
    private String itemName;

    /**
     * 检验类型：IQC、FIRST、FINAL、PATROL、CHECK、SELF、FQC、OQC
     */
    private String qcType;

    /**
     * 检验模板ID
     */
    private String templateId;

    /**
     * 检验单行ID
     */
    private Long lineId;

    public Long getQcId() {
        return qcId;
    }

    public void setQcId(Long qcId) {
        this.qcId = qcId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQcType() {
        return qcType;
    }

    public void setQcType(String qcType) {
        this.qcType = qcType;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    @Override
    public String toString() {
        return "QcMobParam{" +
                "qcId=" + qcId +
                ", itemId=" + itemId +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", qcType='" + qcType + '\'' +
                ", templateId='" + templateId + '\'' +
                ", lineId=" + lineId +
                '}';
    }
}
