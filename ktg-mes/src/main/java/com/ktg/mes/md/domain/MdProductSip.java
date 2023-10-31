package com.ktg.mes.md.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 产品SIP对象 md_product_sip
 * 
 * @author yinjinlu
 * @date 2023-10-31
 */
public class MdProductSip extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long sipId;

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
    private String sipTitle;

    /** 详细描述 */
    @Excel(name = "详细描述")
    private String sipDescription;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String sipUrl;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

    public void setSipId(Long sipId) 
    {
        this.sipId = sipId;
    }

    public Long getSipId() 
    {
        return sipId;
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
    public void setSipTitle(String sipTitle) 
    {
        this.sipTitle = sipTitle;
    }

    public String getSipTitle() 
    {
        return sipTitle;
    }
    public void setSipDescription(String sipDescription) 
    {
        this.sipDescription = sipDescription;
    }

    public String getSipDescription() 
    {
        return sipDescription;
    }
    public void setSipUrl(String sipUrl) 
    {
        this.sipUrl = sipUrl;
    }

    public String getSipUrl() 
    {
        return sipUrl;
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
            .append("sipId", getSipId())
            .append("itemId", getItemId())
            .append("orderNum", getOrderNum())
            .append("processId", getProcessId())
            .append("processCode", getProcessCode())
            .append("processName", getProcessName())
            .append("sipTitle", getSipTitle())
            .append("sipDescription", getSipDescription())
            .append("sipUrl", getSipUrl())
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
