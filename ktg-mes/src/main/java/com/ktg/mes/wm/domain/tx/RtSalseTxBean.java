package com.ktg.mes.wm.domain.tx;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ktg.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class RtSalseTxBean extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 产品物料ID */
    private Long itemId;

    /** 产品物料编码 */
    private String itemCode;

    /** 产品物料名称 */
    private String itemName;

    /** 规格型号 */
    private String specification;

    /** 单位 */
    private String unitOfMeasure;

    /** 入库批次号 */
    private String batchCode;

    /** 仓库ID */
    private Long warehouseId;

    /** 仓库编码 */
    private String warehouseCode;

    /** 仓库名称 */
    private String warehouseName;

    /** 库区ID */
    private Long locationId;

    /** 库区编码 */
    private String locationCode;

    /** 库区名称 */
    private String locationName;

    /** 库位ID */
    private Long areaId;

    /** 库位编码 */
    private String areaCode;

    /** 库位名称 */
    private String areaName;

    /** 供应商ID */
    private Long clientId;

    /** 客户编号 */
    private String clientCode;

    /** 客户名称 */
    private String clientName;

    /** 客户简称 */
    private String clientNick;

    /** 单据类型 */
    private String sourceDocType;

    /** 单据ID */
    private Long sourceDocId;

    /** 单据编号 */
    private String sourceDocCode;

    /** 单据行ID */
    private Long sourceDocLineId;

    /** 事务数量 */
    private BigDecimal transactionQuantity;

    /** 退货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date rtDate;

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

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientNick() {
        return clientNick;
    }

    public void setClientNick(String clientNick) {
        this.clientNick = clientNick;
    }

    public String getSourceDocType() {
        return sourceDocType;
    }

    public void setSourceDocType(String sourceDocType) {
        this.sourceDocType = sourceDocType;
    }

    public Long getSourceDocId() {
        return sourceDocId;
    }

    public void setSourceDocId(Long sourceDocId) {
        this.sourceDocId = sourceDocId;
    }

    public String getSourceDocCode() {
        return sourceDocCode;
    }

    public void setSourceDocCode(String sourceDocCode) {
        this.sourceDocCode = sourceDocCode;
    }

    public Long getSourceDocLineId() {
        return sourceDocLineId;
    }

    public void setSourceDocLineId(Long sourceDocLineId) {
        this.sourceDocLineId = sourceDocLineId;
    }

    public BigDecimal getTransactionQuantity() {
        return transactionQuantity;
    }

    public void setTransactionQuantity(BigDecimal transactionQuantity) {
        this.transactionQuantity = transactionQuantity;
    }

    public Date getRtDate() {
        return rtDate;
    }

    public void setRtDate(Date rtDate) {
        this.rtDate = rtDate;
    }

    @Override
    public String toString() {
        return "RtSalseTxBean{" +
                "itemId=" + itemId +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", specification='" + specification + '\'' +
                ", unitOfMeasure='" + unitOfMeasure + '\'' +
                ", batchCode='" + batchCode + '\'' +
                ", warehouseId=" + warehouseId +
                ", warehouseCode='" + warehouseCode + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", locationId=" + locationId +
                ", locationCode='" + locationCode + '\'' +
                ", locationName='" + locationName + '\'' +
                ", areaId=" + areaId +
                ", areaCode='" + areaCode + '\'' +
                ", areaName='" + areaName + '\'' +
                ", clientId=" + clientId +
                ", clientCode='" + clientCode + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientNick='" + clientNick + '\'' +
                ", sourceDocType='" + sourceDocType + '\'' +
                ", sourceDocId=" + sourceDocId +
                ", sourceDocCode='" + sourceDocCode + '\'' +
                ", sourceDocLineId=" + sourceDocLineId +
                ", transactionQuantity=" + transactionQuantity +
                ", rtDate=" + rtDate +
                '}';
    }
}
