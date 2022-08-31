package com.ktg.mes.qc.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 出货检验单对象 qc_oqc
 * 
 * @author yinjinlu
 * @date 2022-08-31
 */
public class QcOqc extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 出货检验单ID */
    private Long oqcId;

    /** 出货检验单编号 */
    @Excel(name = "出货检验单编号")
    private String oqcCode;

    /** 出货检验单名称 */
    @Excel(name = "出货检验单名称")
    private String oqcName;

    /** 检验模板ID */
    @Excel(name = "检验模板ID")
    private Long templateId;

    /** 客户ID */
    @Excel(name = "客户ID")
    private Long clientId;

    /** 客户编码 */
    @Excel(name = "客户编码")
    private String clientCode;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String clientName;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchCode;

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

    /** 最低检测数 */
    @Excel(name = "最低检测数")
    private BigDecimal quantityMinCheck;

    /** 最大不合格数 */
    @Excel(name = "最大不合格数")
    private BigDecimal quantityMaxUnqualified;

    /** 发货数量 */
    @Excel(name = "发货数量")
    private BigDecimal quantityOut;

    /** 本次检测数量 */
    @Excel(name = "本次检测数量")
    private BigDecimal quantityCheck;

    /** 不合格数 */
    @Excel(name = "不合格数")
    private BigDecimal quantityUnqualified;

    /** 合格数量 */
    @Excel(name = "合格数量")
    private BigDecimal quantityQuanlified;

    /** 致命缺陷率 */
    @Excel(name = "致命缺陷率")
    private BigDecimal crRate;

    /** 严重缺陷率 */
    @Excel(name = "严重缺陷率")
    private BigDecimal majRate;

    /** 轻微缺陷率 */
    @Excel(name = "轻微缺陷率")
    private BigDecimal minRate;

    /** 致命缺陷数量 */
    @Excel(name = "致命缺陷数量")
    private BigDecimal crQuantity;

    /** 严重缺陷数量 */
    @Excel(name = "严重缺陷数量")
    private BigDecimal majQuantity;

    /** 轻微缺陷数量 */
    @Excel(name = "轻微缺陷数量")
    private BigDecimal minQuantity;

    /** 检测结果 */
    @Excel(name = "检测结果")
    private String checkResult;

    /** 出货日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date outDate;

    /** 检测日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检测日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inspectDate;

    /** 检测人员 */
    @Excel(name = "检测人员")
    private String inspector;

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

    public void setOqcId(Long oqcId) 
    {
        this.oqcId = oqcId;
    }

    public Long getOqcId() 
    {
        return oqcId;
    }
    public void setOqcCode(String oqcCode) 
    {
        this.oqcCode = oqcCode;
    }

    public String getOqcCode() 
    {
        return oqcCode;
    }
    public void setOqcName(String oqcName) 
    {
        this.oqcName = oqcName;
    }

    public String getOqcName() 
    {
        return oqcName;
    }
    public void setTemplateId(Long templateId) 
    {
        this.templateId = templateId;
    }

    public Long getTemplateId() 
    {
        return templateId;
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
    public void setBatchCode(String batchCode) 
    {
        this.batchCode = batchCode;
    }

    public String getBatchCode() 
    {
        return batchCode;
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
    public void setQuantityMinCheck(BigDecimal quantityMinCheck) 
    {
        this.quantityMinCheck = quantityMinCheck;
    }

    public BigDecimal getQuantityMinCheck() 
    {
        return quantityMinCheck;
    }
    public void setQuantityMaxUnqualified(BigDecimal quantityMaxUnqualified) 
    {
        this.quantityMaxUnqualified = quantityMaxUnqualified;
    }

    public BigDecimal getQuantityMaxUnqualified() 
    {
        return quantityMaxUnqualified;
    }
    public void setQuantityOut(BigDecimal quantityOut) 
    {
        this.quantityOut = quantityOut;
    }

    public BigDecimal getQuantityOut() 
    {
        return quantityOut;
    }
    public void setQuantityCheck(BigDecimal quantityCheck) 
    {
        this.quantityCheck = quantityCheck;
    }

    public BigDecimal getQuantityCheck() 
    {
        return quantityCheck;
    }
    public void setQuantityUnqualified(BigDecimal quantityUnqualified) 
    {
        this.quantityUnqualified = quantityUnqualified;
    }

    public BigDecimal getQuantityUnqualified() 
    {
        return quantityUnqualified;
    }
    public void setQuantityQuanlified(BigDecimal quantityQuanlified) 
    {
        this.quantityQuanlified = quantityQuanlified;
    }

    public BigDecimal getQuantityQuanlified() 
    {
        return quantityQuanlified;
    }
    public void setCrRate(BigDecimal crRate) 
    {
        this.crRate = crRate;
    }

    public BigDecimal getCrRate() 
    {
        return crRate;
    }
    public void setMajRate(BigDecimal majRate) 
    {
        this.majRate = majRate;
    }

    public BigDecimal getMajRate() 
    {
        return majRate;
    }
    public void setMinRate(BigDecimal minRate) 
    {
        this.minRate = minRate;
    }

    public BigDecimal getMinRate() 
    {
        return minRate;
    }
    public void setCrQuantity(BigDecimal crQuantity) 
    {
        this.crQuantity = crQuantity;
    }

    public BigDecimal getCrQuantity() 
    {
        return crQuantity;
    }
    public void setMajQuantity(BigDecimal majQuantity) 
    {
        this.majQuantity = majQuantity;
    }

    public BigDecimal getMajQuantity() 
    {
        return majQuantity;
    }
    public void setMinQuantity(BigDecimal minQuantity) 
    {
        this.minQuantity = minQuantity;
    }

    public BigDecimal getMinQuantity() 
    {
        return minQuantity;
    }
    public void setCheckResult(String checkResult) 
    {
        this.checkResult = checkResult;
    }

    public String getCheckResult() 
    {
        return checkResult;
    }
    public void setOutDate(Date outDate) 
    {
        this.outDate = outDate;
    }

    public Date getOutDate() 
    {
        return outDate;
    }
    public void setInspectDate(Date inspectDate) 
    {
        this.inspectDate = inspectDate;
    }

    public Date getInspectDate() 
    {
        return inspectDate;
    }
    public void setInspector(String inspector) 
    {
        this.inspector = inspector;
    }

    public String getInspector() 
    {
        return inspector;
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
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("oqcId", getOqcId())
            .append("oqcCode", getOqcCode())
            .append("oqcName", getOqcName())
            .append("templateId", getTemplateId())
            .append("clientId", getClientId())
            .append("clientCode", getClientCode())
            .append("clientName", getClientName())
            .append("batchCode", getBatchCode())
            .append("itemId", getItemId())
            .append("itemCode", getItemCode())
            .append("itemName", getItemName())
            .append("specification", getSpecification())
            .append("unitOfMeasure", getUnitOfMeasure())
            .append("quantityMinCheck", getQuantityMinCheck())
            .append("quantityMaxUnqualified", getQuantityMaxUnqualified())
            .append("quantityOut", getQuantityOut())
            .append("quantityCheck", getQuantityCheck())
            .append("quantityUnqualified", getQuantityUnqualified())
            .append("quantityQuanlified", getQuantityQuanlified())
            .append("crRate", getCrRate())
            .append("majRate", getMajRate())
            .append("minRate", getMinRate())
            .append("crQuantity", getCrQuantity())
            .append("majQuantity", getMajQuantity())
            .append("minQuantity", getMinQuantity())
            .append("checkResult", getCheckResult())
            .append("outDate", getOutDate())
            .append("inspectDate", getInspectDate())
            .append("inspector", getInspector())
            .append("status", getStatus())
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
