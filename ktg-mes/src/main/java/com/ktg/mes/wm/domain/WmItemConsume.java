package com.ktg.mes.wm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 物料消耗记录对象 wm_item_consume
 * 
 * @author yinjinlu
 * @date 2022-09-19
 */
public class WmItemConsume extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long recordId;

    /** 生产工单ID */
    @Excel(name = "生产工单ID")
    private Long workorderId;

    /** 生产工单编码 */
    @Excel(name = "生产工单编码")
    private String workorderCode;

    /** 生产工单名称 */
    @Excel(name = "生产工单名称")
    private String workorderName;

    /** 生产任务ID */
    @Excel(name = "生产任务ID")
    private Long taskId;

    /** 生产任务编号 */
    @Excel(name = "生产任务编号")
    private String taskCode;

    /** 生产任务名称 */
    @Excel(name = "生产任务名称")
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

    /** 工序编号 */
    @Excel(name = "工序编号")
    private String processCode;

    /** 工序名称 */
    @Excel(name = "工序名称")
    private String processName;

    /** 消耗日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "消耗日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date consumeDate;

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

    public void setRecordId(Long recordId) 
    {
        this.recordId = recordId;
    }

    public Long getRecordId() 
    {
        return recordId;
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
    public void setConsumeDate(Date consumeDate) 
    {
        this.consumeDate = consumeDate;
    }

    public Date getConsumeDate() 
    {
        return consumeDate;
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
            .append("recordId", getRecordId())
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
            .append("consumeDate", getConsumeDate())
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
