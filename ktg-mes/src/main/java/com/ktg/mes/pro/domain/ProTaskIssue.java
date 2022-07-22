package com.ktg.mes.pro.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 生产任务投料对象 pro_task_issue
 * 
 * @author yinjinlu
 * @date 2022-07-22
 */
public class ProTaskIssue extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long recordId;

    /** 生产任务ID */
    @Excel(name = "生产任务ID")
    private Long taskId;

    /** 生产工单ID */
    @Excel(name = "生产工单ID")
    private Long workorderId;

    /** 工作站ID */
    @Excel(name = "工作站ID")
    private Long workstationId;

    /** 单据ID */
    @Excel(name = "单据ID")
    private Long sourceDocId;

    /** 单据编号 */
    @Excel(name = "单据编号")
    private String sourceDocCode;

    /** 单据类型 */
    @Excel(name = "单据类型")
    private String sourceDocType;

    /** 投料批次 */
    @Excel(name = "投料批次")
    private String batchCode;

    /** 行ID */
    @Excel(name = "行ID")
    private Long sourceLineId;

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

    /** 总的投料数量 */
    @Excel(name = "总的投料数量")
    private BigDecimal quantityIssued;

    /** 当前可用数量 */
    @Excel(name = "当前可用数量")
    private BigDecimal quantityAvailable;

    /** 当前使用数量 */
    @Excel(name = "当前使用数量")
    private BigDecimal quantityUsed;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

    public void setRecordId(Long recordId) 
    {
        this.recordId = recordId;
    }

    public Long getRecordId() 
    {
        return recordId;
    }
    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setWorkorderId(Long workorderId) 
    {
        this.workorderId = workorderId;
    }

    public Long getWorkorderId() 
    {
        return workorderId;
    }
    public void setWorkstationId(Long workstationId) 
    {
        this.workstationId = workstationId;
    }

    public Long getWorkstationId() 
    {
        return workstationId;
    }
    public void setSourceDocId(Long sourceDocId) 
    {
        this.sourceDocId = sourceDocId;
    }

    public Long getSourceDocId() 
    {
        return sourceDocId;
    }
    public void setSourceDocCode(String sourceDocCode) 
    {
        this.sourceDocCode = sourceDocCode;
    }

    public String getSourceDocCode() 
    {
        return sourceDocCode;
    }
    public void setSourceDocType(String sourceDocType) 
    {
        this.sourceDocType = sourceDocType;
    }

    public String getSourceDocType() 
    {
        return sourceDocType;
    }
    public void setBatchCode(String batchCode) 
    {
        this.batchCode = batchCode;
    }

    public String getBatchCode() 
    {
        return batchCode;
    }
    public void setSourceLineId(Long sourceLineId) 
    {
        this.sourceLineId = sourceLineId;
    }

    public Long getSourceLineId() 
    {
        return sourceLineId;
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
    public void setQuantityIssued(BigDecimal quantityIssued) 
    {
        this.quantityIssued = quantityIssued;
    }

    public BigDecimal getQuantityIssued() 
    {
        return quantityIssued;
    }
    public void setQuantityAvailable(BigDecimal quantityAvailable) 
    {
        this.quantityAvailable = quantityAvailable;
    }

    public BigDecimal getQuantityAvailable() 
    {
        return quantityAvailable;
    }
    public void setQuantityUsed(BigDecimal quantityUsed) 
    {
        this.quantityUsed = quantityUsed;
    }

    public BigDecimal getQuantityUsed() 
    {
        return quantityUsed;
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
            .append("recordId", getRecordId())
            .append("taskId", getTaskId())
            .append("workorderId", getWorkorderId())
            .append("workstationId", getWorkstationId())
            .append("sourceDocId", getSourceDocId())
            .append("sourceDocCode", getSourceDocCode())
            .append("sourceDocType", getSourceDocType())
            .append("batchCode", getBatchCode())
            .append("sourceLineId", getSourceLineId())
            .append("itemId", getItemId())
            .append("itemCode", getItemCode())
            .append("itemName", getItemName())
            .append("specification", getSpecification())
            .append("unitOfMeasure", getUnitOfMeasure())
            .append("quantityIssued", getQuantityIssued())
            .append("quantityAvailable", getQuantityAvailable())
            .append("quantityUsed", getQuantityUsed())
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
