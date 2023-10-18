package com.ktg.mes.qc.domain;

import com.ktg.common.core.domain.BaseEntity;

public class QcPendingInspect extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long sourceDocId;

    private String sourceDocCode;

    private Long sourceLineId;

    private String recordTime;

    private String qcType;

    private Long itemId;

    private String itemCode;

    private String itemName;

    private String specification;

    private String unitOfMeasure;

    private String quantityUncheck;

    private Long workOrderId;

    private String workOrderCode;

    private String workOrderName;

    private Long workstationId;

    private String workstationCode;

    private String workstationName;

    private Long vendorClientId;

    private String vendorClientCode;

    private String vendorClientName;

    private String vendorClientNick;

    private String batchCode;

    private Long taskId;

    private String taskCode;

    private String taskName;

    private Long warehouseId;

    private String warehouseCode;

    private String warehouseName;

    private Long locationId;

    private String locationCode;

    private String locationName;

    private Long areaId;

    private String areaCode;

    private String areaName;

    private String address;

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

    public Long getSourceLineId() {
        return sourceLineId;
    }

    public void setSourceLineId(Long sourceLineId) {
        this.sourceLineId = sourceLineId;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getQcType() {
        return qcType;
    }

    public void setQcType(String qcType) {
        this.qcType = qcType;
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

    public String getQuantityUncheck() {
        return quantityUncheck;
    }

    public void setQuantityUncheck(String quantityUncheck) {
        this.quantityUncheck = quantityUncheck;
    }

    public Long getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(Long workOrderId) {
        this.workOrderId = workOrderId;
    }

    public String getWorkOrderCode() {
        return workOrderCode;
    }

    public void setWorkOrderCode(String workOrderCode) {
        this.workOrderCode = workOrderCode;
    }

    public String getWorkOrderName() {
        return workOrderName;
    }

    public void setWorkOrderName(String workOrderName) {
        this.workOrderName = workOrderName;
    }

    public Long getWorkstationId() {
        return workstationId;
    }

    public void setWorkstationId(Long workstationId) {
        this.workstationId = workstationId;
    }

    public String getWorkstationCode() {
        return workstationCode;
    }

    public void setWorkstationCode(String workstationCode) {
        this.workstationCode = workstationCode;
    }

    public String getWorkstationName() {
        return workstationName;
    }

    public void setWorkstationName(String workstationName) {
        this.workstationName = workstationName;
    }

    public Long getVendorClientId() {
        return vendorClientId;
    }

    public void setVendorClientId(Long vendorClientId) {
        this.vendorClientId = vendorClientId;
    }

    public String getVendorClientCode() {
        return vendorClientCode;
    }

    public void setVendorClientCode(String vendorClientCode) {
        this.vendorClientCode = vendorClientCode;
    }

    public String getVendorClientName() {
        return vendorClientName;
    }

    public void setVendorClientName(String vendorClientName) {
        this.vendorClientName = vendorClientName;
    }

    public String getVendorClientNick() {
        return vendorClientNick;
    }

    public void setVendorClientNick(String vendorClientNick) {
        this.vendorClientNick = vendorClientNick;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "QcPendingInspect{" +
                "sourceDocId=" + sourceDocId +
                ", sourceDocCode='" + sourceDocCode + '\'' +
                ", sourceLineId=" + sourceLineId +
                ", recordTime='" + recordTime + '\'' +
                ", qcType='" + qcType + '\'' +
                ", itemId=" + itemId +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", specification='" + specification + '\'' +
                ", unitOfMeasure='" + unitOfMeasure + '\'' +
                ", quantityUncheck='" + quantityUncheck + '\'' +
                ", workOrderId=" + workOrderId +
                ", workOrderCode='" + workOrderCode + '\'' +
                ", workOrderName='" + workOrderName + '\'' +
                ", workstationId=" + workstationId +
                ", workstationCode='" + workstationCode + '\'' +
                ", workstationName='" + workstationName + '\'' +
                ", vendorClientId=" + vendorClientId +
                ", vendorClientCode='" + vendorClientCode + '\'' +
                ", vendorClientName='" + vendorClientName + '\'' +
                ", vendorClientNick='" + vendorClientNick + '\'' +
                ", batchCode='" + batchCode + '\'' +
                ", taskId=" + taskId +
                ", taskCode='" + taskCode + '\'' +
                ", taskName='" + taskName + '\'' +
                ", warehouseId=" + warehouseId +
                ", warehouseCode='" + warehouseCode + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", locationId=" + locationId +
                ", locationCode='" + locationCode + '\'' +
                ", locationName='" + locationName + '\'' +
                ", areaId=" + areaId +
                ", areaCode='" + areaCode + '\'' +
                ", areaName='" + areaName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
