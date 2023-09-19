package com.ktg.mes.pro.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.TreeEntity;

/**
 * 生产工单对象 pro_workorder
 * 
 * @author yinjinlu
 * @date 2022-05-15
 */
public class ProWorkorder extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 工单ID */
    private Long workorderId;

    /** 工单编码 */
    @Excel(name = "工单编码")
    private String workorderCode;

    /** 工单名称 */
    @Excel(name = "工单名称")
    private String workorderName;

    /** 来源类型 */
    @Excel(name = "来源类型")
    private String orderSource;

    /** 来源单据 */
    @Excel(name = "来源单据")
    private String sourceCode;

    /** 产品ID */
    @Excel(name = "产品ID")
    private Long productId;

    /** 产品编号 */
    @Excel(name = "产品编号")
    private String productCode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 规格型号 */
    @Excel(name = "规格型号")
    private String productSpc;

    /** 单位 */
    @Excel(name = "单位")
    private String unitOfMeasure;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchCode;

    /** 生产数量 */
    @Excel(name = "生产数量")
    private BigDecimal quantity;

    /** 已生产数量 */
    @Excel(name = "已生产数量")
    private BigDecimal quantityProduced;

    /** 调整数量 */
    @Excel(name = "调整数量")
    private BigDecimal quantityChanged;

    /** 已排产数量 */
    @Excel(name = "已排产数量")
    private BigDecimal quantityScheduled;

    /** 客户ID */
    @Excel(name = "客户ID")
    private Long clientId;

    /** 客户编码 */
    @Excel(name = "客户编码")
    private String clientCode;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String clientName;

    /** 需求日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "需求日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date requestDate;

    /** 完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Excel(name = "完成时间", width = 30, dateFormat = "yyyy-MM-dd hh:mm:ss")
    private Date finishDate;

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
    public void setWorkorderName(String workorderName) 
    {
        this.workorderName = workorderName;
    }

    public String getWorkorderName() 
    {
        return workorderName;
    }
    public void setOrderSource(String orderSource) 
    {
        this.orderSource = orderSource;
    }

    public String getOrderSource() 
    {
        return orderSource;
    }
    public void setSourceCode(String sourceCode) 
    {
        this.sourceCode = sourceCode;
    }

    public String getSourceCode() 
    {
        return sourceCode;
    }
    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }
    public void setProductCode(String productCode) 
    {
        this.productCode = productCode;
    }

    public String getProductCode() 
    {
        return productCode;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setProductSpc(String productSpc) 
    {
        this.productSpc = productSpc;
    }

    public String getProductSpc() 
    {
        return productSpc;
    }
    public void setUnitOfMeasure(String unitOfMeasure) 
    {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getUnitOfMeasure() 
    {
        return unitOfMeasure;
    }
    public void setQuantity(BigDecimal quantity) 
    {
        this.quantity = quantity;
    }

    public BigDecimal getQuantity() 
    {
        return quantity;
    }
    public void setQuantityProduced(BigDecimal quantityProduced) 
    {
        this.quantityProduced = quantityProduced;
    }

    public BigDecimal getQuantityProduced() 
    {
        return quantityProduced;
    }
    public void setQuantityChanged(BigDecimal quantityChanged) 
    {
        this.quantityChanged = quantityChanged;
    }

    public BigDecimal getQuantityChanged() 
    {
        return quantityChanged;
    }
    public void setQuantityScheduled(BigDecimal quantityScheduled) 
    {
        this.quantityScheduled = quantityScheduled;
    }

    public BigDecimal getQuantityScheduled() 
    {
        return quantityScheduled;
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
    public void setRequestDate(Date requestDate) 
    {
        this.requestDate = requestDate;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public Date getRequestDate()
    {
        return requestDate;
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

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    @Override
    public String toString() {
        return "ProWorkorder{" +
                "workorderId=" + workorderId +
                ", workorderCode='" + workorderCode + '\'' +
                ", workorderName='" + workorderName + '\'' +
                ", orderSource='" + orderSource + '\'' +
                ", sourceCode='" + sourceCode + '\'' +
                ", productId=" + productId +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", productSpc='" + productSpc + '\'' +
                ", unitOfMeasure='" + unitOfMeasure + '\'' +
                ", batchCode='" + batchCode + '\'' +
                ", quantity=" + quantity +
                ", quantityProduced=" + quantityProduced +
                ", quantityChanged=" + quantityChanged +
                ", quantityScheduled=" + quantityScheduled +
                ", clientId=" + clientId +
                ", clientCode='" + clientCode + '\'' +
                ", clientName='" + clientName + '\'' +
                ", requestDate=" + requestDate +
                ", finishDate=" + finishDate +
                ", status='" + status + '\'' +
                ", attr1='" + attr1 + '\'' +
                ", attr2='" + attr2 + '\'' +
                ", attr3=" + attr3 +
                ", attr4=" + attr4 +
                '}';
    }
}
