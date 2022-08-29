package com.ktg.mes.qc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

import java.util.Arrays;

/**
 * 检测模板对象 qc_template
 * 
 * @author yinjinlu
 * @date 2022-05-17
 */
public class QcTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 检测模板ID */
    private Long templateId;

    /** 检测模板编号 */
    @Excel(name = "检测模板编号")
    private String templateCode;

    /** 检测模板名称 */
    @Excel(name = "检测模板名称")
    private String templateName;

    /** 检测种类 */
    @Excel(name = "检测种类")
    private String qcTypes;

    /**
     * 用来传递检测种类参数
     */
    private String[] qcTypesParam;

    /**
     * 物料ID
     */
    private Long itemId;

    /** 是否启用 */
    @Excel(name = "是否启用")
    private String enableFlag;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

    public void setTemplateId(Long templateId) 
    {
        this.templateId = templateId;
    }

    public Long getTemplateId() 
    {
        return templateId;
    }
    public void setTemplateCode(String templateCode) 
    {
        this.templateCode = templateCode;
    }

    public String getTemplateCode() 
    {
        return templateCode;
    }
    public void setTemplateName(String templateName) 
    {
        this.templateName = templateName;
    }

    public String getTemplateName() 
    {
        return templateName;
    }
    public void setQcTypes(String qcTypes) 
    {
        this.qcTypes = qcTypes;
    }

    public String getQcTypes() 
    {
        return qcTypes;
    }

    public String[] getQcTypesParam() {
        return qcTypesParam;
    }

    public void setQcTypesParam(String[] qcTypesParam) {
        this.qcTypesParam = qcTypesParam;
    }

    public void setEnableFlag(String enableFlag)
    {
        this.enableFlag = enableFlag;
    }

    public String getEnableFlag() 
    {
        return enableFlag;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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
        return "QcTemplate{" +
                "templateId=" + templateId +
                ", templateCode='" + templateCode + '\'' +
                ", templateName='" + templateName + '\'' +
                ", qcTypes='" + qcTypes + '\'' +
                ", qcTypesParam=" + Arrays.toString(qcTypesParam) +
                ", itemId=" + itemId +
                ", enableFlag='" + enableFlag + '\'' +
                ", attr1='" + attr1 + '\'' +
                ", attr2='" + attr2 + '\'' +
                ", attr3=" + attr3 +
                ", attr4=" + attr4 +
                '}';
    }
}
