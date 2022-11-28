package com.ktg.mes.wm.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 转移单行对象 wm_transfer_line
 * 
 * @author yinjinlu
 * @date 2022-11-28
 */
public class WmTransferLine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 明细行ID */
    private Long lineId;

    /** 装箱单ID */
    @Excel(name = "装箱单ID")
    private Long transferId;

    /** 库存记录ID */
    @Excel(name = "库存记录ID")
    private Long materialStockId;

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

    /** 装箱数量 */
    @Excel(name = "装箱数量")
    private BigDecimal quantityTransfer;

    /** 生产工单ID */
    @Excel(name = "生产工单ID")
    private Long workorderId;

    /** 生产工单编号 */
    @Excel(name = "生产工单编号")
    private String workorderCode;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchCode;

    /** 移出仓库ID */
    @Excel(name = "移出仓库ID")
    private Long fromWarehouseId;

    /** 移出仓库编码 */
    @Excel(name = "移出仓库编码")
    private String fromWarehouseCode;

    /** 移出仓库名称 */
    @Excel(name = "移出仓库名称")
    private String fromWarehouseName;

    /** 移出库区ID */
    @Excel(name = "移出库区ID")
    private Long fromLocationId;

    /** 移出库区编码 */
    @Excel(name = "移出库区编码")
    private String fromLocationCode;

    /** 移出库区名称 */
    @Excel(name = "移出库区名称")
    private String fromLocationName;

    /** 移出库位ID */
    @Excel(name = "移出库位ID")
    private Long fromAreaId;

    /** 移出库位编码 */
    @Excel(name = "移出库位编码")
    private String fromAreaCode;

    /** 移出库位名称 */
    @Excel(name = "移出库位名称")
    private String fromAreaName;

    /** 移入仓库ID */
    @Excel(name = "移入仓库ID")
    private Long toWarehouseId;

    /** 移入仓库编码 */
    @Excel(name = "移入仓库编码")
    private String toWarehouseCode;

    /** 移入仓库名称 */
    @Excel(name = "移入仓库名称")
    private String toWarehouseName;

    /** 移入库区ID */
    @Excel(name = "移入库区ID")
    private Long toLocationId;

    /** 移入库区编码 */
    @Excel(name = "移入库区编码")
    private String toLocationCode;

    /** 移入库区名称 */
    @Excel(name = "移入库区名称")
    private String toLocationName;

    /** 移入库位ID */
    @Excel(name = "移入库位ID")
    private Long toAreaId;

    /** 移入库位编码 */
    @Excel(name = "移入库位编码")
    private String toAreaCode;

    /** 移入库位名称 */
    @Excel(name = "移入库位名称")
    private String toAreaName;

    /** 有效期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expireDate;

    /** 供应商ID */
    @Excel(name = "供应商ID")
    private Long vendorId;

    /** 供应商编码 */
    @Excel(name = "供应商编码")
    private String vendorCode;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String vendorName;

    /** 供应商简称 */
    @Excel(name = "供应商简称")
    private String vendorNick;

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
    public void setTransferId(Long transferId) 
    {
        this.transferId = transferId;
    }

    public Long getTransferId() 
    {
        return transferId;
    }
    public void setMaterialStockId(Long materialStockId) 
    {
        this.materialStockId = materialStockId;
    }

    public Long getMaterialStockId() 
    {
        return materialStockId;
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
    public void setQuantityTransfer(BigDecimal quantityTransfer) 
    {
        this.quantityTransfer = quantityTransfer;
    }

    public BigDecimal getQuantityTransfer() 
    {
        return quantityTransfer;
    }
    public void setWorkorderId(Long workorderId) 
    {
        this.workorderId = workorderId;
    }

    public Long getWorkorderId() 
    {
        return workorderId;
    }
    public void setWorkorderCode(String workorderCode) 
    {
        this.workorderCode = workorderCode;
    }

    public String getWorkorderCode() 
    {
        return workorderCode;
    }
    public void setBatchCode(String batchCode) 
    {
        this.batchCode = batchCode;
    }

    public String getBatchCode() 
    {
        return batchCode;
    }
    public void setFromWarehouseId(Long fromWarehouseId) 
    {
        this.fromWarehouseId = fromWarehouseId;
    }

    public Long getFromWarehouseId() 
    {
        return fromWarehouseId;
    }
    public void setFromWarehouseCode(String fromWarehouseCode) 
    {
        this.fromWarehouseCode = fromWarehouseCode;
    }

    public String getFromWarehouseCode() 
    {
        return fromWarehouseCode;
    }
    public void setFromWarehouseName(String fromWarehouseName) 
    {
        this.fromWarehouseName = fromWarehouseName;
    }

    public String getFromWarehouseName() 
    {
        return fromWarehouseName;
    }
    public void setFromLocationId(Long fromLocationId) 
    {
        this.fromLocationId = fromLocationId;
    }

    public Long getFromLocationId() 
    {
        return fromLocationId;
    }
    public void setFromLocationCode(String fromLocationCode) 
    {
        this.fromLocationCode = fromLocationCode;
    }

    public String getFromLocationCode() 
    {
        return fromLocationCode;
    }
    public void setFromLocationName(String fromLocationName) 
    {
        this.fromLocationName = fromLocationName;
    }

    public String getFromLocationName() 
    {
        return fromLocationName;
    }
    public void setFromAreaId(Long fromAreaId) 
    {
        this.fromAreaId = fromAreaId;
    }

    public Long getFromAreaId() 
    {
        return fromAreaId;
    }
    public void setFromAreaCode(String fromAreaCode) 
    {
        this.fromAreaCode = fromAreaCode;
    }

    public String getFromAreaCode() 
    {
        return fromAreaCode;
    }
    public void setFromAreaName(String fromAreaName) 
    {
        this.fromAreaName = fromAreaName;
    }

    public String getFromAreaName() 
    {
        return fromAreaName;
    }
    public void setToWarehouseId(Long toWarehouseId) 
    {
        this.toWarehouseId = toWarehouseId;
    }

    public Long getToWarehouseId() 
    {
        return toWarehouseId;
    }
    public void setToWarehouseCode(String toWarehouseCode) 
    {
        this.toWarehouseCode = toWarehouseCode;
    }

    public String getToWarehouseCode() 
    {
        return toWarehouseCode;
    }
    public void setToWarehouseName(String toWarehouseName) 
    {
        this.toWarehouseName = toWarehouseName;
    }

    public String getToWarehouseName() 
    {
        return toWarehouseName;
    }
    public void setToLocationId(Long toLocationId) 
    {
        this.toLocationId = toLocationId;
    }

    public Long getToLocationId() 
    {
        return toLocationId;
    }
    public void setToLocationCode(String toLocationCode) 
    {
        this.toLocationCode = toLocationCode;
    }

    public String getToLocationCode() 
    {
        return toLocationCode;
    }
    public void setToLocationName(String toLocationName) 
    {
        this.toLocationName = toLocationName;
    }

    public String getToLocationName() 
    {
        return toLocationName;
    }
    public void setToAreaId(Long toAreaId) 
    {
        this.toAreaId = toAreaId;
    }

    public Long getToAreaId() 
    {
        return toAreaId;
    }
    public void setToAreaCode(String toAreaCode) 
    {
        this.toAreaCode = toAreaCode;
    }

    public String getToAreaCode() 
    {
        return toAreaCode;
    }
    public void setToAreaName(String toAreaName) 
    {
        this.toAreaName = toAreaName;
    }

    public String getToAreaName() 
    {
        return toAreaName;
    }
    public void setExpireDate(Date expireDate) 
    {
        this.expireDate = expireDate;
    }

    public Date getExpireDate() 
    {
        return expireDate;
    }
    public void setVendorId(Long vendorId) 
    {
        this.vendorId = vendorId;
    }

    public Long getVendorId() 
    {
        return vendorId;
    }
    public void setVendorCode(String vendorCode) 
    {
        this.vendorCode = vendorCode;
    }

    public String getVendorCode() 
    {
        return vendorCode;
    }
    public void setVendorName(String vendorName) 
    {
        this.vendorName = vendorName;
    }

    public String getVendorName() 
    {
        return vendorName;
    }
    public void setVendorNick(String vendorNick) 
    {
        this.vendorNick = vendorNick;
    }

    public String getVendorNick() 
    {
        return vendorNick;
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
            .append("transferId", getTransferId())
            .append("materialStockId", getMaterialStockId())
            .append("itemId", getItemId())
            .append("itemCode", getItemCode())
            .append("itemName", getItemName())
            .append("specification", getSpecification())
            .append("unitOfMeasure", getUnitOfMeasure())
            .append("quantityTransfer", getQuantityTransfer())
            .append("workorderId", getWorkorderId())
            .append("workorderCode", getWorkorderCode())
            .append("batchCode", getBatchCode())
            .append("fromWarehouseId", getFromWarehouseId())
            .append("fromWarehouseCode", getFromWarehouseCode())
            .append("fromWarehouseName", getFromWarehouseName())
            .append("fromLocationId", getFromLocationId())
            .append("fromLocationCode", getFromLocationCode())
            .append("fromLocationName", getFromLocationName())
            .append("fromAreaId", getFromAreaId())
            .append("fromAreaCode", getFromAreaCode())
            .append("fromAreaName", getFromAreaName())
            .append("toWarehouseId", getToWarehouseId())
            .append("toWarehouseCode", getToWarehouseCode())
            .append("toWarehouseName", getToWarehouseName())
            .append("toLocationId", getToLocationId())
            .append("toLocationCode", getToLocationCode())
            .append("toLocationName", getToLocationName())
            .append("toAreaId", getToAreaId())
            .append("toAreaCode", getToAreaCode())
            .append("toAreaName", getToAreaName())
            .append("expireDate", getExpireDate())
            .append("vendorId", getVendorId())
            .append("vendorCode", getVendorCode())
            .append("vendorName", getVendorName())
            .append("vendorNick", getVendorNick())
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
