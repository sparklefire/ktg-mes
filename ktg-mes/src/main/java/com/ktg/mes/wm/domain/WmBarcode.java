package com.ktg.mes.wm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 条码清单对象 wm_barcode
 * 
 * @author yinjinlu
 * @date 2022-08-01
 */
public class WmBarcode extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 条码ID */
    private Long barcodeId;

    /** 条码格式 */
    @Excel(name = "条码格式")
    private String barcodeFormart;

    /** 条码类型 */
    @Excel(name = "条码类型")
    private String barcodeType;

    /** 产品物料ID */
    @Excel(name = "产品物料ID")
    private String barcodeContent;

    private Long bussinessId;

    /** 业务编码 */
    @Excel(name = "业务编码")
    private String bussinessCode;

    /** 业务名称 */
    @Excel(name = "业务编码")
    private String bussinessName;

    /** 条码地址 */
    @Excel(name = "条码地址")
    private String barcodeUrl;

    /** 是否生效 */
    @Excel(name = "是否生效")
    private String enableFlag;

    /** 预留字段1 */
    @Excel(name = "预留字段1")
    private String attr1;

    /** 预留字段2 */
    @Excel(name = "预留字段2")
    private String attr2;

    /** 预留字段3 */
    @Excel(name = "预留字段3")
    private Long attr3;

    /** 预留字段4 */
    @Excel(name = "预留字段4")
    private Long attr4;

    public void setBarcodeId(Long barcodeId) 
    {
        this.barcodeId = barcodeId;
    }

    public Long getBarcodeId() 
    {
        return barcodeId;
    }
    public void setBarcodeFormart(String barcodeFormart) 
    {
        this.barcodeFormart = barcodeFormart;
    }

    public String getBarcodeFormart() 
    {
        return barcodeFormart;
    }
    public void setBarcodeType(String barcodeType) 
    {
        this.barcodeType = barcodeType;
    }

    public String getBarcodeType() 
    {
        return barcodeType;
    }
    public void setBarcodeContent(String barcodeContent) 
    {
        this.barcodeContent = barcodeContent;
    }

    public String getBarcodeContent() 
    {
        return barcodeContent;
    }

    public Long getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(Long bussinessId) {
        this.bussinessId = bussinessId;
    }

    public String getBussinessCode() {
        return bussinessCode;
    }

    public void setBussinessCode(String bussinessCode) {
        this.bussinessCode = bussinessCode;
    }

    public String getBussinessName() {
        return bussinessName;
    }

    public void setBussinessName(String bussinessName) {
        this.bussinessName = bussinessName;
    }

    public String getBarcodeUrl() {
        return barcodeUrl;
    }

    public void setBarcodeUrl(String barcodeUrl) {
        this.barcodeUrl = barcodeUrl;
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
        return "WmBarcode{" +
                "barcodeId=" + barcodeId +
                ", barcodeFormart='" + barcodeFormart + '\'' +
                ", barcodeType='" + barcodeType + '\'' +
                ", barcodeContent='" + barcodeContent + '\'' +
                ", bussinessId=" + bussinessId +
                ", bussinessCode='" + bussinessCode + '\'' +
                ", bussinessName='" + bussinessName + '\'' +
                ", barcodeUrl='" + barcodeUrl + '\'' +
                ", enableFlag='" + enableFlag + '\'' +
                ", attr1='" + attr1 + '\'' +
                ", attr2='" + attr2 + '\'' +
                ", attr3=" + attr3 +
                ", attr4=" + attr4 +
                '}';
    }
}
