package com.ktg.mes.wm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 供应商退货对象 wm_rt_vendor
 * 
 * @author yinjinlu
 * @date 2022-06-13
 */
public class WmRtVendor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 退货单ID */
    private Long rtId;

    /** 退货单编号 */
    @Excel(name = "退货单编号")
    private String rtCode;

    /** 退货单名称 */
    @Excel(name = "退货单名称")
    private String rtName;

    /** 采购订单编号 */
    @Excel(name = "采购订单编号")
    private String poCode;

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

    @Excel(name = "批次号" )
    private String batchCode;

    /** 退货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "退货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date rtDate;

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

    public void setRtId(Long rtId) 
    {
        this.rtId = rtId;
    }

    public Long getRtId() 
    {
        return rtId;
    }
    public void setRtCode(String rtCode) 
    {
        this.rtCode = rtCode;
    }

    public String getRtCode() 
    {
        return rtCode;
    }
    public void setRtName(String rtName) 
    {
        this.rtName = rtName;
    }

    public String getRtName() 
    {
        return rtName;
    }
    public void setPoCode(String poCode) 
    {
        this.poCode = poCode;
    }

    public String getPoCode() 
    {
        return poCode;
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
    public void setRtDate(Date rtDate) 
    {
        this.rtDate = rtDate;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public Date getRtDate()
    {
        return rtDate;
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
        return "WmRtVendor{" +
                "rtId=" + rtId +
                ", rtCode='" + rtCode + '\'' +
                ", rtName='" + rtName + '\'' +
                ", poCode='" + poCode + '\'' +
                ", vendorId=" + vendorId +
                ", vendorCode='" + vendorCode + '\'' +
                ", vendorName='" + vendorName + '\'' +
                ", vendorNick='" + vendorNick + '\'' +
                ", batchCode='" + batchCode + '\'' +
                ", rtDate=" + rtDate +
                ", status='" + status + '\'' +
                ", attr1='" + attr1 + '\'' +
                ", attr2='" + attr2 + '\'' +
                ", attr3=" + attr3 +
                ", attr4=" + attr4 +
                '}';
    }
}
