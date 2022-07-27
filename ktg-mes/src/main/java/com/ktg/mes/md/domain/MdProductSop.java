package com.ktg.mes.md.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 产品SOP对象 md_produt_sop
 * 
 * @author yinjinlu
 * @date 2022-07-26
 */
public class MdProductSop extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long sopId;

    /** 物料产品ID */
    @Excel(name = "物料产品ID")
    private Long itemId;

    /** 排列顺序 */
    @Excel(name = "排列顺序")
    private Integer orderNum;

    /** 对应的工序 */
    @Excel(name = "对应的工序")
    private Long processId;

    /** 工序编号 */
    @Excel(name = "工序编号")
    private String processCode;

    /** 工序名称 */
    @Excel(name = "工序名称")
    private String processName;

    /** 标题 */
    @Excel(name = "标题")
    private String sopTitle;

    /** 详细描述 */
    @Excel(name = "详细描述")
    private String sopDescription;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String sopUrl;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

    public void setSopId(Long sopId) 
    {
        this.sopId = sopId;
    }

    public Long getSopId() 
    {
        return sopId;
    }
    public void setItemId(Long itemId) 
    {
        this.itemId = itemId;
    }

    public Long getItemId() 
    {
        return itemId;
    }
    public void setOrderNum(Integer orderNum) 
    {
        this.orderNum = orderNum;
    }

    public Integer getOrderNum() 
    {
        return orderNum;
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
    public void setSopTitle(String sopTitle) 
    {
        this.sopTitle = sopTitle;
    }

    public String getSopTitle() 
    {
        return sopTitle;
    }
    public void setSopDescription(String sopDescription) 
    {
        this.sopDescription = sopDescription;
    }

    public String getSopDescription() 
    {
        return sopDescription;
    }
    public void setSopUrl(String sopUrl) 
    {
        this.sopUrl = sopUrl;
    }

    public String getSopUrl() 
    {
        return sopUrl;
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
            .append("sopId", getSopId())
            .append("itemId", getItemId())
            .append("orderNum", getOrderNum())
            .append("processId", getProcessId())
            .append("processCode", getProcessCode())
            .append("processName", getProcessName())
            .append("sopTitle", getSopTitle())
            .append("sopDescription", getSopDescription())
            .append("sopUrl", getSopUrl())
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
