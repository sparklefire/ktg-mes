package com.ktg.mes.pro.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 流转单对象 pro_trans_order
 * 
 * @author yinjinlu
 * @date 2022-07-24
 */
public class ProTransOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流转单ID */
    private Long transOrderId;

    /** 流转单编号 */
    @Excel(name = "流转单编号")
    private String transOrderCode;

    /** 生产任务ID */
    @Excel(name = "生产任务ID")
    private Long taskId;

    /** 生产任务编号 */
    @Excel(name = "生产任务编号")
    private String taskCode;

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

    /** 工序编号 */
    @Excel(name = "工序编号")
    private String processCode;

    /** 工序名称 */
    @Excel(name = "工序名称")
    private String processName;

    /** 生产工单ID */
    @Excel(name = "生产工单ID")
    private Long workorderId;

    /** 生产工单编号 */
    @Excel(name = "生产工单编号")
    private String workorderCode;

    /** 生产工单名称 */
    @Excel(name = "生产工单名称")
    private String workorderName;

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

    private String barCodeUrl;

    /** 流转数量 */
    @Excel(name = "流转数量")
    private BigDecimal quantityTransfered;

    /** 生产日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date produceDate;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

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
    public void setTaskCode(String taskCode) 
    {
        this.taskCode = taskCode;
    }

    public String getTaskCode() 
    {
        return taskCode;
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
    public void setQuantityTransfered(BigDecimal quantityTransfered) 
    {
        this.quantityTransfered = quantityTransfered;
    }

    public BigDecimal getQuantityTransfered() 
    {
        return quantityTransfered;
    }
    public void setProduceDate(Date produceDate) 
    {
        this.produceDate = produceDate;
    }

    public Date getProduceDate() 
    {
        return produceDate;
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

    public String getBarCodeUrl() {
        return barCodeUrl;
    }

    public void setBarCodeUrl(String barCodeUrl) {
        this.barCodeUrl = barCodeUrl;
    }

    @Override
    public String toString() {
        return "ProTransOrder{" +
                "transOrderId=" + transOrderId +
                ", transOrderCode='" + transOrderCode + '\'' +
                ", taskId=" + taskId +
                ", taskCode='" + taskCode + '\'' +
                ", workstationId=" + workstationId +
                ", workstationCode='" + workstationCode + '\'' +
                ", workstationName='" + workstationName + '\'' +
                ", processId=" + processId +
                ", processCode='" + processCode + '\'' +
                ", processName='" + processName + '\'' +
                ", workorderId=" + workorderId +
                ", workorderCode='" + workorderCode + '\'' +
                ", workorderName='" + workorderName + '\'' +
                ", batchCode='" + batchCode + '\'' +
                ", itemId=" + itemId +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", specification='" + specification + '\'' +
                ", unitOfMeasure='" + unitOfMeasure + '\'' +
                ", barCodeUrl='" + barCodeUrl + '\'' +
                ", quantityTransfered=" + quantityTransfered +
                ", produceDate=" + produceDate +
                ", attr1='" + attr1 + '\'' +
                ", attr2='" + attr2 + '\'' +
                ", attr3=" + attr3 +
                ", attr4=" + attr4 +
                '}';
    }
}
