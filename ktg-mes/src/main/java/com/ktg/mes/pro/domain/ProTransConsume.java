package com.ktg.mes.pro.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 物料消耗记录对象 pro_trans_consume
 * 
 * @author yinjinlu
 * @date 2022-07-24
 */
public class ProTransConsume extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long recordId;

    /** 流转单ID */
    @Excel(name = "流转单ID")
    private Long transOrderId;

    /** 流转单编号 */
    @Excel(name = "流转单编号")
    private String transOrderCode;

    /** 生产任务ID */
    @Excel(name = "生产任务ID")
    private Long taskId;

    /** 工作站ID */
    @Excel(name = "工作站ID")
    private Long workstationId;

    /** 工序ID */
    @Excel(name = "工序ID")
    private Long processId;

    /** 生产工单ID */
    @Excel(name = "生产工单ID")
    private Long workorderId;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchCode;

    /** 被消耗单据ID */
    @Excel(name = "被消耗单据ID")
    private Long sourceDocId;

    /** 被消耗单据编号 */
    @Excel(name = "被消耗单据编号")
    private String sourceDocCode;

    /** 被消耗单据类型 */
    @Excel(name = "被消耗单据类型")
    private String sourceDocType;

    /** 被消耗单据行ID */
    @Excel(name = "被消耗单据行ID")
    private Long sourceLineId;

    /** 被消耗物料批次号 */
    @Excel(name = "被消耗物料批次号")
    private String sourceBatchCode;

    /** 被消耗产品物料ID */
    @Excel(name = "被消耗产品物料ID")
    private Long itemId;

    /** 被消耗产品物料编码 */
    @Excel(name = "被消耗产品物料编码")
    private String itemCode;

    /** 被消耗产品物料名称 */
    @Excel(name = "被消耗产品物料名称")
    private String itemName;

    /** 规格型号 */
    @Excel(name = "规格型号")
    private String specification;

    /** 单位 */
    @Excel(name = "单位")
    private String unitOfMeasure;

    /** 消耗数量 */
    @Excel(name = "消耗数量")
    private BigDecimal quantityConsumed;

    /** 消耗时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "消耗时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date consumeDate;

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
    public void setTransOrderId(Long transOrderId) 
    {
        this.transOrderId = transOrderId;
    }

    public Long getTransOrderId() 
    {
        return transOrderId;
    }
    public void setTransOrderCode(String transOrderCode) 
    {
        this.transOrderCode = transOrderCode;
    }

    public String getTransOrderCode() 
    {
        return transOrderCode;
    }
    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setWorkstationId(Long workstationId) 
    {
        this.workstationId = workstationId;
    }

    public Long getWorkstationId() 
    {
        return workstationId;
    }
    public void setProcessId(Long processId) 
    {
        this.processId = processId;
    }

    public Long getProcessId() 
    {
        return processId;
    }
    public void setWorkorderId(Long workorderId) 
    {
        this.workorderId = workorderId;
    }

    public Long getWorkorderId() 
    {
        return workorderId;
    }
    public void setBatchCode(String batchCode) 
    {
        this.batchCode = batchCode;
    }

    public String getBatchCode() 
    {
        return batchCode;
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
    public void setSourceLineId(Long sourceLineId) 
    {
        this.sourceLineId = sourceLineId;
    }

    public Long getSourceLineId() 
    {
        return sourceLineId;
    }
    public void setSourceBatchCode(String sourceBatchCode) 
    {
        this.sourceBatchCode = sourceBatchCode;
    }

    public String getSourceBatchCode() 
    {
        return sourceBatchCode;
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
    public void setQuantityConsumed(BigDecimal quantityConsumed) 
    {
        this.quantityConsumed = quantityConsumed;
    }

    public BigDecimal getQuantityConsumed() 
    {
        return quantityConsumed;
    }
    public void setConsumeDate(Date consumeDate) 
    {
        this.consumeDate = consumeDate;
    }

    public Date getConsumeDate() 
    {
        return consumeDate;
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
            .append("transOrderId", getTransOrderId())
            .append("transOrderCode", getTransOrderCode())
            .append("taskId", getTaskId())
            .append("workstationId", getWorkstationId())
            .append("processId", getProcessId())
            .append("workorderId", getWorkorderId())
            .append("batchCode", getBatchCode())
            .append("sourceDocId", getSourceDocId())
            .append("sourceDocCode", getSourceDocCode())
            .append("sourceDocType", getSourceDocType())
            .append("sourceLineId", getSourceLineId())
            .append("sourceBatchCode", getSourceBatchCode())
            .append("itemId", getItemId())
            .append("itemCode", getItemCode())
            .append("itemName", getItemName())
            .append("specification", getSpecification())
            .append("unitOfMeasure", getUnitOfMeasure())
            .append("quantityConsumed", getQuantityConsumed())
            .append("consumeDate", getConsumeDate())
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
