package com.ktg.mes.qc.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 过程检验单对象 qc_ipqc
 * 
 * @author yinjinlu
 * @date 2022-08-29
 */
public class QcIpqc extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 检验单ID */
    private Long ipqcId;

    /** 检验单编号 */
    @Excel(name = "检验单编号")
    private String ipqcCode;

    /** 检验单名称 */
    @Excel(name = "检验单名称")
    private String ipqcName;

    /** 检验类型 */
    @Excel(name = "检验类型")
    private String ipqcType;

    /** 检验模板ID */
    @Excel(name = "检验模板ID")
    private Long templateId;

    /** 工单ID */
    @Excel(name = "工单ID")
    private Long workorderId;

    /** 工单编码 */
    @Excel(name = "工单编码")
    private String workorderCode;

    /** 工单名称 */
    @Excel(name = "工单名称")
    private String workorderName;

    /** 任务ID */
    @Excel(name = "任务ID")
    private Long taskId;

    /** 任务编号 */
    @Excel(name = "任务编号")
    private String taskCode;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String taskName;

    /** 工作站ID */
    @Excel(name = "工作站ID")
    private Long workstationId;

    /** 工作站编号 */
    @Excel(name = "工作站编号")
    private String workstationCode;

    /** 工作站名称 */
    @Excel(name = "工作站名称")
    private String workstationName;

    /** 工序ID */
    @Excel(name = "工序ID")
    private Long processId;

    /** 工序编码 */
    @Excel(name = "工序编码")
    private String processCode;

    /** 工序名称 */
    @Excel(name = "工序名称")
    private String processName;

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

    /** 检测数量 */
    @Excel(name = "检测数量")
    private BigDecimal quantityCheck;

    /** 不合格数 */
    @Excel(name = "不合格数")
    private BigDecimal quantityUnqualified;

    /** 合格品数量 */
    @Excel(name = "合格品数量")
    private BigDecimal quantityQualified;

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

    public void setIpqcId(Long ipqcId) 
    {
        this.ipqcId = ipqcId;
    }

    public Long getIpqcId() 
    {
        return ipqcId;
    }
    public void setIpqcCode(String ipqcCode) 
    {
        this.ipqcCode = ipqcCode;
    }

    public String getIpqcCode() 
    {
        return ipqcCode;
    }
    public void setIpqcName(String ipqcName) 
    {
        this.ipqcName = ipqcName;
    }

    public String getIpqcName() 
    {
        return ipqcName;
    }
    public void setIpqcType(String ipqcType) 
    {
        this.ipqcType = ipqcType;
    }

    public String getIpqcType() 
    {
        return ipqcType;
    }
    public void setTemplateId(Long templateId) 
    {
        this.templateId = templateId;
    }

    public Long getTemplateId() 
    {
        return templateId;
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
    public void setWorkorderName(String workorderName) 
    {
        this.workorderName = workorderName;
    }

    public String getWorkorderName() 
    {
        return workorderName;
    }
    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setTaskCode(String taskCode) 
    {
        this.taskCode = taskCode;
    }

    public String getTaskCode() 
    {
        return taskCode;
    }
    public void setTaskName(String taskName) 
    {
        this.taskName = taskName;
    }

    public String getTaskName() 
    {
        return taskName;
    }
    public void setWorkstationId(Long workstationId) 
    {
        this.workstationId = workstationId;
    }

    public Long getWorkstationId() 
    {
        return workstationId;
    }
    public void setWorkstationCode(String workstationCode) 
    {
        this.workstationCode = workstationCode;
    }

    public String getWorkstationCode() 
    {
        return workstationCode;
    }
    public void setWorkstationName(String workstationName) 
    {
        this.workstationName = workstationName;
    }

    public String getWorkstationName() 
    {
        return workstationName;
    }
    public void setProcessId(Long processId) 
    {
        this.processId = processId;
    }

    public Long getProcessId() 
    {
        return processId;
    }
    public void setProcessCode(String processCode) 
    {
        this.processCode = processCode;
    }

    public String getProcessCode() 
    {
        return processCode;
    }
    public void setProcessName(String processName) 
    {
        this.processName = processName;
    }

    public String getProcessName() 
    {
        return processName;
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
    public void setQuantityQualified(BigDecimal quantityQualified) 
    {
        this.quantityQualified = quantityQualified;
    }

    public BigDecimal getQuantityQualified() 
    {
        return quantityQualified;
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
            .append("ipqcId", getIpqcId())
            .append("ipqcCode", getIpqcCode())
            .append("ipqcName", getIpqcName())
            .append("ipqcType", getIpqcType())
            .append("templateId", getTemplateId())
            .append("workorderId", getWorkorderId())
            .append("workorderCode", getWorkorderCode())
            .append("workorderName", getWorkorderName())
            .append("taskId", getTaskId())
            .append("taskCode", getTaskCode())
            .append("taskName", getTaskName())
            .append("workstationId", getWorkstationId())
            .append("workstationCode", getWorkstationCode())
            .append("workstationName", getWorkstationName())
            .append("processId", getProcessId())
            .append("processCode", getProcessCode())
            .append("processName", getProcessName())
            .append("itemId", getItemId())
            .append("itemCode", getItemCode())
            .append("itemName", getItemName())
            .append("specification", getSpecification())
            .append("unitOfMeasure", getUnitOfMeasure())
            .append("quantityCheck", getQuantityCheck())
            .append("quantityUnqualified", getQuantityUnqualified())
            .append("quantityQualified", getQuantityQualified())
            .append("crRate", getCrRate())
            .append("majRate", getMajRate())
            .append("minRate", getMinRate())
            .append("crQuantity", getCrQuantity())
            .append("majQuantity", getMajQuantity())
            .append("minQuantity", getMinQuantity())
            .append("checkResult", getCheckResult())
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
