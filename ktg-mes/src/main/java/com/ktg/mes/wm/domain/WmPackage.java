package com.ktg.mes.wm.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 装箱单对象 wm_package
 * 
 * @author yinjinlu
 * @date 2022-10-10
 */
public class WmPackage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 装箱单ID */
    private Long packageId;

    /** 父箱ID */
    @Excel(name = "父箱ID")
    private Long parentId;

    /** 所有父节点ID */
    @Excel(name = "所有父节点ID")
    private String ancestors;

    /** 装箱单编号 */
    @Excel(name = "装箱单编号")
    private String packageCode;

    /** 条码ID */
    @Excel(name = "条码ID")
    private Long barcodeId;

    /** 条码内容 */
    @Excel(name = "条码内容")
    private String barcodeContent;

    /** 条码地址 */
    @Excel(name = "条码地址")
    private String barcodeUrl;

    /** 装箱日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "装箱日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date packageDate;

    /** 销售订单编号 */
    @Excel(name = "销售订单编号")
    private String soCode;

    /** 发票编号 */
    @Excel(name = "发票编号")
    private String invoiceCode;

    /** 客户ID */
    @Excel(name = "客户ID")
    private Long clientId;

    /** 客户编码 */
    @Excel(name = "客户编码")
    private String clientCode;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String clientName;

    /** 客户简称 */
    @Excel(name = "客户简称")
    private String clientNick;

    /** 箱长度 */
    @Excel(name = "箱长度")
    private BigDecimal packageLength;

    /** 箱宽度 */
    @Excel(name = "箱宽度")
    private BigDecimal packageWidth;

    /** 箱高度 */
    @Excel(name = "箱高度")
    private BigDecimal packageHeight;

    /** 尺寸单位 */
    @Excel(name = "尺寸单位")
    private String sizeUnit;

    /** 净重 */
    @Excel(name = "净重")
    private BigDecimal netWeight;

    /** 毛重 */
    @Excel(name = "毛重")
    private BigDecimal crossWeight;

    /** 重量单位 */
    @Excel(name = "重量单位")
    private String weightUnit;

    /** 检查员用户名 */
    @Excel(name = "检查员用户名")
    private String inspector;

    /** 检查员名称 */
    @Excel(name = "检查员名称")
    private String inspectorName;

    /** 是否生效 */
    @Excel(name = "是否生效")
    private String enableFlag;

    /** 状态  */
    private String status;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

    public void setPackageId(Long packageId) 
    {
        this.packageId = packageId;
    }

    public Long getPackageId() 
    {
        return packageId;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setAncestors(String ancestors) 
    {
        this.ancestors = ancestors;
    }

    public String getAncestors() 
    {
        return ancestors;
    }
    public void setPackageCode(String packageCode) 
    {
        this.packageCode = packageCode;
    }

    public String getPackageCode() 
    {
        return packageCode;
    }
    public void setBarcodeId(Long barcodeId) 
    {
        this.barcodeId = barcodeId;
    }

    public Long getBarcodeId() 
    {
        return barcodeId;
    }
    public void setBarcodeContent(String barcodeContent) 
    {
        this.barcodeContent = barcodeContent;
    }

    public String getBarcodeContent() 
    {
        return barcodeContent;
    }
    public void setBarcodeUrl(String barcodeUrl) 
    {
        this.barcodeUrl = barcodeUrl;
    }

    public String getBarcodeUrl() 
    {
        return barcodeUrl;
    }
    public void setPackageDate(Date packageDate) 
    {
        this.packageDate = packageDate;
    }

    public Date getPackageDate() 
    {
        return packageDate;
    }
    public void setSoCode(String soCode) 
    {
        this.soCode = soCode;
    }

    public String getSoCode() 
    {
        return soCode;
    }
    public void setInvoiceCode(String invoiceCode) 
    {
        this.invoiceCode = invoiceCode;
    }

    public String getInvoiceCode() 
    {
        return invoiceCode;
    }
    public void setClientId(Long clientId) 
    {
        this.clientId = clientId;
    }

    public Long getClientId() 
    {
        return clientId;
    }
    public void setClientCode(String clientCode) 
    {
        this.clientCode = clientCode;
    }

    public String getClientCode() 
    {
        return clientCode;
    }
    public void setClientName(String clientName) 
    {
        this.clientName = clientName;
    }

    public String getClientName() 
    {
        return clientName;
    }
    public void setClientNick(String clientNick) 
    {
        this.clientNick = clientNick;
    }

    public String getClientNick() 
    {
        return clientNick;
    }
    public void setPackageLength(BigDecimal packageLength) 
    {
        this.packageLength = packageLength;
    }

    public BigDecimal getPackageLength() 
    {
        return packageLength;
    }
    public void setPackageWidth(BigDecimal packageWidth) 
    {
        this.packageWidth = packageWidth;
    }

    public BigDecimal getPackageWidth() 
    {
        return packageWidth;
    }
    public void setPackageHeight(BigDecimal packageHeight) 
    {
        this.packageHeight = packageHeight;
    }

    public BigDecimal getPackageHeight() 
    {
        return packageHeight;
    }
    public void setSizeUnit(String sizeUnit) 
    {
        this.sizeUnit = sizeUnit;
    }

    public String getSizeUnit() 
    {
        return sizeUnit;
    }
    public void setNetWeight(BigDecimal netWeight) 
    {
        this.netWeight = netWeight;
    }

    public BigDecimal getNetWeight() 
    {
        return netWeight;
    }
    public void setCrossWeight(BigDecimal crossWeight) 
    {
        this.crossWeight = crossWeight;
    }

    public BigDecimal getCrossWeight() 
    {
        return crossWeight;
    }
    public void setWeightUnit(String weightUnit) 
    {
        this.weightUnit = weightUnit;
    }

    public String getWeightUnit() 
    {
        return weightUnit;
    }
    public void setInspector(String inspector) 
    {
        this.inspector = inspector;
    }

    public String getInspector() 
    {
        return inspector;
    }
    public void setInspectorName(String inspectorName) 
    {
        this.inspectorName = inspectorName;
    }

    public String getInspectorName() 
    {
        return inspectorName;
    }
    public void setEnableFlag(String enableFlag) 
    {
        this.enableFlag = enableFlag;
    }

    public String getEnableFlag() 
    {
        return enableFlag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return "WmPackage{" +
                "packageId=" + packageId +
                ", parentId=" + parentId +
                ", ancestors='" + ancestors + '\'' +
                ", packageCode='" + packageCode + '\'' +
                ", barcodeId=" + barcodeId +
                ", barcodeContent='" + barcodeContent + '\'' +
                ", barcodeUrl='" + barcodeUrl + '\'' +
                ", packageDate=" + packageDate +
                ", soCode='" + soCode + '\'' +
                ", invoiceCode='" + invoiceCode + '\'' +
                ", clientId=" + clientId +
                ", clientCode='" + clientCode + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientNick='" + clientNick + '\'' +
                ", packageLength=" + packageLength +
                ", packageWidth=" + packageWidth +
                ", packageHeight=" + packageHeight +
                ", sizeUnit='" + sizeUnit + '\'' +
                ", netWeight=" + netWeight +
                ", crossWeight=" + crossWeight +
                ", weightUnit='" + weightUnit + '\'' +
                ", inspector='" + inspector + '\'' +
                ", inspectorName='" + inspectorName + '\'' +
                ", enableFlag='" + enableFlag + '\'' +
                ", status='" + status + '\'' +
                ", attr1='" + attr1 + '\'' +
                ", attr2='" + attr2 + '\'' +
                ", attr3=" + attr3 +
                ", attr4=" + attr4 +
                '}';
    }
}
