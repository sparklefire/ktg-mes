package com.ktg.mes.wm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 转移单对象 wm_transfer
 * 
 * @author yinjinlu
 * @date 2022-11-28
 */
public class WmTransfer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 转移单ID */
    private Long transferId;

    /** 转移单编号 */
    @Excel(name = "转移单编号")
    private String transferCode;

    /** 转移单名称 */
    @Excel(name = "转移单名称")
    private String transferName;

    /** 转移单类型 */
    @Excel(name = "转移单类型")
    private String transferType;

    /** 目的地 */
    @Excel(name = "目的地")
    private String destination;

    /** 承运商 */
    @Excel(name = "承运商")
    private String carrier;

    /** 托运单号 */
    @Excel(name = "托运单号")
    private String bookingNote;

    @Excel(name = "收货人")
    private String receiver;

    @Excel(name = "收货人名称")
    private String receiverNick;

    /** 移出仓库ID */
    @Excel(name = "移出仓库ID")
    private Long fromWarehouseId;

    /** 移出仓库编码 */
    @Excel(name = "移出仓库编码")
    private String fromWarehouseCode;

    /** 移出仓库名称 */
    @Excel(name = "移出仓库名称")
    private String fromWarehouseName;

    /** 移入仓库ID */
    @Excel(name = "移入仓库ID")
    private Long toWarehouseId;

    /** 移入仓库编码 */
    @Excel(name = "移入仓库编码")
    private String toWarehouseCode;

    /** 移入仓库名称 */
    @Excel(name = "移入仓库名称")
    private String toWarehouseName;

    /** 转移日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "转移日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date transferDate;

    /** 单据状态 */
    @Excel(name = "单据状态")
    private String status;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

    public void setTransferId(Long transferId) 
    {
        this.transferId = transferId;
    }

    public Long getTransferId() 
    {
        return transferId;
    }
    public void setTransferCode(String transferCode) 
    {
        this.transferCode = transferCode;
    }

    public String getTransferCode() 
    {
        return transferCode;
    }
    public void setTransferName(String transferName) 
    {
        this.transferName = transferName;
    }

    public String getTransferName() 
    {
        return transferName;
    }
    public void setTransferType(String transferType) 
    {
        this.transferType = transferType;
    }

    public String getTransferType() 
    {
        return transferType;
    }
    public void setDestination(String destination) 
    {
        this.destination = destination;
    }

    public String getDestination() 
    {
        return destination;
    }
    public void setCarrier(String carrier) 
    {
        this.carrier = carrier;
    }

    public String getCarrier() 
    {
        return carrier;
    }
    public void setBookingNote(String bookingNote) 
    {
        this.bookingNote = bookingNote;
    }

    public String getBookingNote() 
    {
        return bookingNote;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverNick() {
        return receiverNick;
    }

    public void setReceiverNick(String receiverNick) {
        this.receiverNick = receiverNick;
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
    public void setTransferDate(Date transferDate) 
    {
        this.transferDate = transferDate;
    }

    public Date getTransferDate() 
    {
        return transferDate;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
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
        return "WmTransfer{" +
                "transferId=" + transferId +
                ", transferCode='" + transferCode + '\'' +
                ", transferName='" + transferName + '\'' +
                ", transferType='" + transferType + '\'' +
                ", destination='" + destination + '\'' +
                ", carrier='" + carrier + '\'' +
                ", bookingNote='" + bookingNote + '\'' +
                ", receiver='" + receiver + '\'' +
                ", receiverNick='" + receiverNick + '\'' +
                ", fromWarehouseId=" + fromWarehouseId +
                ", fromWarehouseCode='" + fromWarehouseCode + '\'' +
                ", fromWarehouseName='" + fromWarehouseName + '\'' +
                ", toWarehouseId=" + toWarehouseId +
                ", toWarehouseCode='" + toWarehouseCode + '\'' +
                ", toWarehouseName='" + toWarehouseName + '\'' +
                ", transferDate=" + transferDate +
                ", status='" + status + '\'' +
                ", attr1='" + attr1 + '\'' +
                ", attr2='" + attr2 + '\'' +
                ", attr3=" + attr3 +
                ", attr4=" + attr4 +
                '}';
    }
}
