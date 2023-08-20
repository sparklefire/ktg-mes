package com.ktg.mes.pro.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 生产任务对象 pro_task
 * 
 * @author yinjinlu
 * @date 2022-05-15
 */
public class ProTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    private Long taskId;

    /** 任务编号 */
    @Excel(name = "任务编号")
    private String taskCode;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String taskName;

    /** 生产工单ID */
    @Excel(name = "生产工单ID")
    private Long workorderId;

    /** 生产工单编号 */
    @Excel(name = "生产工单编号")
    private String workorderCode;

    /** 工单名称 */
    @Excel(name = "工单名称")
    private String workorderName;

    /** 工作站ID */
    @Excel(name = "工作站ID")
    private Long workstationId;

    /** 工作站编号 */
    @Excel(name = "工作站编号")
    private String workstationCode;

    /** 工作站名称 */
    @Excel(name = "工作站名称")
    private String workstationName;

    private Long routeId;

    private String routeCode;

    /** 工序ID */
    @Excel(name = "工序ID")
    private Long processId;

    /** 工序编码 */
    @Excel(name = "工序编码")
    private String processCode;

    /** 工序名称 */
    @Excel(name = "工序名称")
    private String processName;

    private String isCheck;

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

    /** 排产数量 */
    @Excel(name = "排产数量")
    private BigDecimal quantity;

    /** 已生产数量 */
    @Excel(name = "已生产数量")
    private BigDecimal quantityProduced;

    private BigDecimal quantityQuanlify;

    private BigDecimal quantityUnquanlify;

    /** 调整数量 */
    @Excel(name = "调整数量")
    private BigDecimal quantityChanged;

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

    /** 开始生产时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始生产时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 生产时长 */
    @Excel(name = "生产时长")
    private Long duration;

    /** 完成生产时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "完成生产时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 甘特图显示颜色 */
    @Excel(name = "甘特图显示颜色")
    private String colorCode;

    /** 需求日期 */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Excel(name = "需求日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date requestDate;

    private String status;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

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

    public BigDecimal getQuantityQuanlify() {
        return quantityQuanlify;
    }

    public void setQuantityQuanlify(BigDecimal quantityQuanlify) {
        this.quantityQuanlify = quantityQuanlify;
    }

    public BigDecimal getQuantityUnquanlify() {
        return quantityUnquanlify;
    }

    public void setQuantityUnquanlify(BigDecimal quantityUnquanlify) {
        this.quantityUnquanlify = quantityUnquanlify;
    }

    public BigDecimal getQuantityChanged()
    {
        return quantityChanged;
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
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setDuration(Long duration) 
    {
        this.duration = duration;
    }

    public Long getDuration() 
    {
        return duration;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setColorCode(String colorCode) 
    {
        this.colorCode = colorCode;
    }

    public String getColorCode() 
    {
        return colorCode;
    }
    public void setRequestDate(Date requestDate) 
    {
        this.requestDate = requestDate;
    }

    public Date getRequestDate() 
    {
        return requestDate;
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

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode;
    }

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }

    @Override
    public String toString() {
        return "ProTask{" +
                "taskId=" + taskId +
                ", taskCode='" + taskCode + '\'' +
                ", taskName='" + taskName + '\'' +
                ", workorderId=" + workorderId +
                ", workorderCode='" + workorderCode + '\'' +
                ", workorderName='" + workorderName + '\'' +
                ", workstationId=" + workstationId +
                ", workstationCode='" + workstationCode + '\'' +
                ", workstationName='" + workstationName + '\'' +
                ", routeId=" + routeId +
                ", routeCode='" + routeCode + '\'' +
                ", processId=" + processId +
                ", processCode='" + processCode + '\'' +
                ", processName='" + processName + '\'' +
                ", isCheck='" + isCheck + '\'' +
                ", itemId=" + itemId +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", specification='" + specification + '\'' +
                ", unitOfMeasure='" + unitOfMeasure + '\'' +
                ", quantity=" + quantity +
                ", quantityProduced=" + quantityProduced +
                ", quantityQuanlify=" + quantityQuanlify +
                ", quantityUnquanlify=" + quantityUnquanlify +
                ", quantityChanged=" + quantityChanged +
                ", clientId=" + clientId +
                ", clientCode='" + clientCode + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientNick='" + clientNick + '\'' +
                ", startTime=" + startTime +
                ", duration=" + duration +
                ", endTime=" + endTime +
                ", colorCode='" + colorCode + '\'' +
                ", requestDate=" + requestDate +
                ", status='" + status + '\'' +
                ", attr1='" + attr1 + '\'' +
                ", attr2='" + attr2 + '\'' +
                ", attr3=" + attr3 +
                ", attr4=" + attr4 +
                '}';
    }
}
