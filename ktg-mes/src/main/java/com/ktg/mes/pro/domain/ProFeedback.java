package com.ktg.mes.pro.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 生产报工记录对象 pro_feedback
 * 
 * @author yinjinlu
 * @date 2022-07-10
 */
public class ProFeedback extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long recordId;

    /** 工作站ID */
    @Excel(name = "工作站ID")
    private Long workstationId;

    /** 工作站编号 */
    @Excel(name = "工作站编号")
    private String workstationCode;

    /** 工作站名称 */
    @Excel(name = "工作站名称")
    private String workstationName;

    /** 生产工单ID */
    @Excel(name = "生产工单ID")
    private Long workorderId;

    /** 生产工单编号 */
    @Excel(name = "生产工单编号")
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

    /** 排产数量 */
    @Excel(name = "排产数量")
    private BigDecimal quantity;

    /** 本次报工数量 */
    @Excel(name = "本次报工数量")
    private BigDecimal quantityFeedback;

    /** 合格品数量 */
    @Excel(name = "合格品数量")
    private BigDecimal quantityQualified;

    /** 不良品数量 */
    @Excel(name = "不良品数量")
    private BigDecimal quantityUnquanlified;

    /** 报工用户名 */
    @Excel(name = "报工用户名")
    private String userName;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickName;

    /** 报工途径 */
    @Excel(name = "报工途径")
    private String feedbackChannel;

    /** 报工时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报工时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date feedbackTime;

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
    public void setQuantity(BigDecimal quantity) 
    {
        this.quantity = quantity;
    }

    public BigDecimal getQuantity() 
    {
        return quantity;
    }
    public void setQuantityFeedback(BigDecimal quantityFeedback) 
    {
        this.quantityFeedback = quantityFeedback;
    }

    public BigDecimal getQuantityFeedback() 
    {
        return quantityFeedback;
    }
    public void setQuantityQualified(BigDecimal quantityQualified) 
    {
        this.quantityQualified = quantityQualified;
    }

    public BigDecimal getQuantityQualified() 
    {
        return quantityQualified;
    }
    public void setQuantityUnquanlified(BigDecimal quantityUnquanlified) 
    {
        this.quantityUnquanlified = quantityUnquanlified;
    }

    public BigDecimal getQuantityUnquanlified() 
    {
        return quantityUnquanlified;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }
    public void setFeedbackChannel(String feedbackChannel) 
    {
        this.feedbackChannel = feedbackChannel;
    }

    public String getFeedbackChannel() 
    {
        return feedbackChannel;
    }
    public void setFeedbackTime(Date feedbackTime) 
    {
        this.feedbackTime = feedbackTime;
    }

    public Date getFeedbackTime() 
    {
        return feedbackTime;
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
            .append("workstationId", getWorkstationId())
            .append("workstationCode", getWorkstationCode())
            .append("workstationName", getWorkstationName())
            .append("workorderId", getWorkorderId())
            .append("workorderCode", getWorkorderCode())
            .append("workorderName", getWorkorderName())
            .append("taskId", getTaskId())
            .append("taskCode", getTaskCode())
            .append("quantity", getQuantity())
            .append("quantityFeedback", getQuantityFeedback())
            .append("quantityQualified", getQuantityQualified())
            .append("quantityUnquanlified", getQuantityUnquanlified())
            .append("userName", getUserName())
            .append("nickName", getNickName())
            .append("feedbackChannel", getFeedbackChannel())
            .append("feedbackTime", getFeedbackTime())
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
