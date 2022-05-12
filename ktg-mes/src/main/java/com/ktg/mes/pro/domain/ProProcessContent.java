package com.ktg.mes.pro.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 生产工序内容对象 pro_process_content
 * 
 * @author yinjinlu
 * @date 2022-05-12
 */
public class ProProcessContent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 内容ID */
    private Long contentId;

    /** 工序ID */
    @Excel(name = "工序ID")
    private Long processId;

    /** 顺序编号 */
    @Excel(name = "顺序编号")
    private Integer orderNum;

    /** 内容说明 */
    @Excel(name = "内容说明")
    private String contentText;

    /** 辅助设备 */
    @Excel(name = "辅助设备")
    private String device;

    /** 辅助材料 */
    @Excel(name = "辅助材料")
    private String material;

    /** 材料URL */
    @Excel(name = "材料URL")
    private String docUrl;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

    public void setContentId(Long contentId) 
    {
        this.contentId = contentId;
    }

    public Long getContentId() 
    {
        return contentId;
    }
    public void setProcessId(Long processId) 
    {
        this.processId = processId;
    }

    public Long getProcessId() 
    {
        return processId;
    }
    public void setOrderNum(Integer orderNum) 
    {
        this.orderNum = orderNum;
    }

    public Integer getOrderNum() 
    {
        return orderNum;
    }
    public void setContentText(String contentText) 
    {
        this.contentText = contentText;
    }

    public String getContentText() 
    {
        return contentText;
    }
    public void setDevice(String device) 
    {
        this.device = device;
    }

    public String getDevice() 
    {
        return device;
    }
    public void setMaterial(String material) 
    {
        this.material = material;
    }

    public String getMaterial() 
    {
        return material;
    }
    public void setDocUrl(String docUrl) 
    {
        this.docUrl = docUrl;
    }

    public String getDocUrl() 
    {
        return docUrl;
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
            .append("contentId", getContentId())
            .append("processId", getProcessId())
            .append("orderNum", getOrderNum())
            .append("contentText", getContentText())
            .append("device", getDevice())
            .append("material", getMaterial())
            .append("docUrl", getDocUrl())
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
