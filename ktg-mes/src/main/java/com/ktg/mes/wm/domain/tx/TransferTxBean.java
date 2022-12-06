package com.ktg.mes.wm.domain.tx;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class TransferTxBean extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long materialStockId;

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

    private String workorderId;

    private String workorderCode;

    /** 供应商ID */
    private Long vendorId;

    /** 供应商编号 */
    private String vendorCode;

    /** 供应商名称 */
    private String vendorName;

    /** 供应商简称 */
    private String vendorNick;

    /** 移出仓库ID */
    private Long fromWarehouseId;

    /** 移出仓库编码 */
    private String fromWarehouseCode;

    /** 移出仓库名称 */
    private String fromWarehouseName;

    /** 移出库区ID */
    private Long fromLocationId;

    /** 移出库区编码 */
    private String fromLocationCode;

    /** 移出库区名称 */
    private String fromLocationName;

    /** 移出库位ID */
    private Long fromAreaId;

    /** 移出库位编码 */
    private String fromAreaCode;

    /** 移出库位名称 */
    private String fromAreaName;

    /** 移入仓库ID */
    private Long toWarehouseId;

    /** 移入仓库编码 */
    private String toWarehouseCode;

    /** 移入仓库名称 */
    private String toWarehouseName;

    /** 移入库区ID */
    private Long toLocationId;

    /** 移入库区编码 */
    private String toLocationCode;

    /** 移入库区名称 */
    private String toLocationName;

    /** 移入库位ID */
    private Long toAreaId;

    /** 移入库位编码 */
    private String toAreaCode;

    /** 移入库位名称 */
    private String toAreaName;

    private Date recptDate;

    /** 有效期 */
    private Date expireDate;

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

    public Long getMaterialStockId() {
        return materialStockId;
    }

    public void setMaterialStockId(Long materialStockId) {
        this.materialStockId = materialStockId;
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

    public String getWorkorderId() {
        return workorderId;
    }

    public void setWorkorderId(String workorderId) {
        this.workorderId = workorderId;
    }

    public String getWorkorderCode() {
        return workorderCode;
    }

    public void setWorkorderCode(String workorderCode) {
        this.workorderCode = workorderCode;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorNick() {
        return vendorNick;
    }

    public void setVendorNick(String vendorNick) {
        this.vendorNick = vendorNick;
    }

    public Long getFromWarehouseId() {
        return fromWarehouseId;
    }

    public void setFromWarehouseId(Long fromWarehouseId) {
        this.fromWarehouseId = fromWarehouseId;
    }

    public String getFromWarehouseCode() {
        return fromWarehouseCode;
    }

    public void setFromWarehouseCode(String fromWarehouseCode) {
        this.fromWarehouseCode = fromWarehouseCode;
    }

    public String getFromWarehouseName() {
        return fromWarehouseName;
    }

    public void setFromWarehouseName(String fromWarehouseName) {
        this.fromWarehouseName = fromWarehouseName;
    }

    public Long getFromLocationId() {
        return fromLocationId;
    }

    public void setFromLocationId(Long fromLocationId) {
        this.fromLocationId = fromLocationId;
    }

    public String getFromLocationCode() {
        return fromLocationCode;
    }

    public void setFromLocationCode(String fromLocationCode) {
        this.fromLocationCode = fromLocationCode;
    }

    public String getFromLocationName() {
        return fromLocationName;
    }

    public void setFromLocationName(String fromLocationName) {
        this.fromLocationName = fromLocationName;
    }

    public Long getFromAreaId() {
        return fromAreaId;
    }

    public void setFromAreaId(Long fromAreaId) {
        this.fromAreaId = fromAreaId;
    }

    public String getFromAreaCode() {
        return fromAreaCode;
    }

    public void setFromAreaCode(String fromAreaCode) {
        this.fromAreaCode = fromAreaCode;
    }

    public String getFromAreaName() {
        return fromAreaName;
    }

    public void setFromAreaName(String fromAreaName) {
        this.fromAreaName = fromAreaName;
    }

    public Long getToWarehouseId() {
        return toWarehouseId;
    }

    public void setToWarehouseId(Long toWarehouseId) {
        this.toWarehouseId = toWarehouseId;
    }

    public String getToWarehouseCode() {
        return toWarehouseCode;
    }

    public void setToWarehouseCode(String toWarehouseCode) {
        this.toWarehouseCode = toWarehouseCode;
    }

    public String getToWarehouseName() {
        return toWarehouseName;
    }

    public void setToWarehouseName(String toWarehouseName) {
        this.toWarehouseName = toWarehouseName;
    }

    public Long getToLocationId() {
        return toLocationId;
    }

    public void setToLocationId(Long toLocationId) {
        this.toLocationId = toLocationId;
    }

    public String getToLocationCode() {
        return toLocationCode;
    }

    public void setToLocationCode(String toLocationCode) {
        this.toLocationCode = toLocationCode;
    }

    public String getToLocationName() {
        return toLocationName;
    }

    public void setToLocationName(String toLocationName) {
        this.toLocationName = toLocationName;
    }

    public Long getToAreaId() {
        return toAreaId;
    }

    public void setToAreaId(Long toAreaId) {
        this.toAreaId = toAreaId;
    }

    public String getToAreaCode() {
        return toAreaCode;
    }

    public void setToAreaCode(String toAreaCode) {
        this.toAreaCode = toAreaCode;
    }

    public String getToAreaName() {
        return toAreaName;
    }

    public void setToAreaName(String toAreaName) {
        this.toAreaName = toAreaName;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Date getRecptDate() {
        return recptDate;
    }

    public void setRecptDate(Date recptDate) {
        this.recptDate = recptDate;
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

    @Override
    public String toString() {
        return "TransferTxBean{" +
                "materialStockId=" + materialStockId +
                ", itemId=" + itemId +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", specification='" + specification + '\'' +
                ", unitOfMeasure='" + unitOfMeasure + '\'' +
                ", batchCode='" + batchCode + '\'' +
                ", workorderId='" + workorderId + '\'' +
                ", workorderCode='" + workorderCode + '\'' +
                ", vendorId=" + vendorId +
                ", vendorCode='" + vendorCode + '\'' +
                ", vendorName='" + vendorName + '\'' +
                ", vendorNick='" + vendorNick + '\'' +
                ", fromWarehouseId=" + fromWarehouseId +
                ", fromWarehouseCode='" + fromWarehouseCode + '\'' +
                ", fromWarehouseName='" + fromWarehouseName + '\'' +
                ", fromLocationId=" + fromLocationId +
                ", fromLocationCode='" + fromLocationCode + '\'' +
                ", fromLocationName='" + fromLocationName + '\'' +
                ", fromAreaId=" + fromAreaId +
                ", fromAreaCode='" + fromAreaCode + '\'' +
                ", fromAreaName='" + fromAreaName + '\'' +
                ", toWarehouseId=" + toWarehouseId +
                ", toWarehouseCode='" + toWarehouseCode + '\'' +
                ", toWarehouseName='" + toWarehouseName + '\'' +
                ", toLocationId=" + toLocationId +
                ", toLocationCode='" + toLocationCode + '\'' +
                ", toLocationName='" + toLocationName + '\'' +
                ", toAreaId=" + toAreaId +
                ", toAreaCode='" + toAreaCode + '\'' +
                ", toAreaName='" + toAreaName + '\'' +
                ", recptDate=" + recptDate +
                ", expireDate=" + expireDate +
                ", sourceDocType='" + sourceDocType + '\'' +
                ", sourceDocId=" + sourceDocId +
                ", sourceDocCode='" + sourceDocCode + '\'' +
                ", sourceDocLineId=" + sourceDocLineId +
                ", transactionQuantity=" + transactionQuantity +
                '}';
    }
}
