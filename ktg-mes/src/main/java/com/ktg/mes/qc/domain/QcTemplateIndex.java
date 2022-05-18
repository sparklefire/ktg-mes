package com.ktg.mes.qc.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 检测模板-检测项对象 qc_template_index
 * 
 * @author yinjinlu
 * @date 2022-05-18
 */
public class QcTemplateIndex extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long recordId;

    /** 检测模板ID */
    @Excel(name = "检测模板ID")
    private Long templateId;

    /** 检测项ID */
    @Excel(name = "检测项ID")
    private Long indexId;

    /** 检测项编码 */
    @Excel(name = "检测项编码")
    private String indexCode;

    /** 检测项名称 */
    @Excel(name = "检测项名称")
    private String indexName;

    /** 检测项类型 */
    @Excel(name = "检测项类型")
    private String indexType;

    /** 检测工具 */
    @Excel(name = "检测工具")
    private String qcTool;

    /** 检测方法 */
    @Excel(name = "检测方法")
    private String checkMethod;

    /** 标准值 */
    @Excel(name = "标准值")
    private BigDecimal standerVal;

    /** 单位 */
    @Excel(name = "单位")
    private String unitOfMeasure;

    /** 误差上限 */
    @Excel(name = "误差上限")
    private BigDecimal thresholdMax;

    /** 误差下限 */
    @Excel(name = "误差下限")
    private BigDecimal thresholdMin;

    /** 说明图 */
    @Excel(name = "说明图")
    private String docUrl;

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
    public void setTemplateId(Long templateId) 
    {
        this.templateId = templateId;
    }

    public Long getTemplateId() 
    {
        return templateId;
    }
    public void setIndexId(Long indexId) 
    {
        this.indexId = indexId;
    }

    public Long getIndexId() 
    {
        return indexId;
    }
    public void setIndexCode(String indexCode) 
    {
        this.indexCode = indexCode;
    }

    public String getIndexCode() 
    {
        return indexCode;
    }
    public void setIndexName(String indexName) 
    {
        this.indexName = indexName;
    }

    public String getIndexName() 
    {
        return indexName;
    }
    public void setIndexType(String indexType) 
    {
        this.indexType = indexType;
    }

    public String getIndexType() 
    {
        return indexType;
    }
    public void setQcTool(String qcTool) 
    {
        this.qcTool = qcTool;
    }

    public String getQcTool() 
    {
        return qcTool;
    }
    public void setCheckMethod(String checkMethod) 
    {
        this.checkMethod = checkMethod;
    }

    public String getCheckMethod() 
    {
        return checkMethod;
    }
    public void setStanderVal(BigDecimal standerVal) 
    {
        this.standerVal = standerVal;
    }

    public BigDecimal getStanderVal() 
    {
        return standerVal;
    }
    public void setUnitOfMeasure(String unitOfMeasure) 
    {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getUnitOfMeasure() 
    {
        return unitOfMeasure;
    }
    public void setThresholdMax(BigDecimal thresholdMax) 
    {
        this.thresholdMax = thresholdMax;
    }

    public BigDecimal getThresholdMax() 
    {
        return thresholdMax;
    }
    public void setThresholdMin(BigDecimal thresholdMin) 
    {
        this.thresholdMin = thresholdMin;
    }

    public BigDecimal getThresholdMin() 
    {
        return thresholdMin;
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
            .append("recordId", getRecordId())
            .append("templateId", getTemplateId())
            .append("indexId", getIndexId())
            .append("indexCode", getIndexCode())
            .append("indexName", getIndexName())
            .append("indexType", getIndexType())
            .append("qcTool", getQcTool())
            .append("checkMethod", getCheckMethod())
            .append("standerVal", getStanderVal())
            .append("unitOfMeasure", getUnitOfMeasure())
            .append("thresholdMax", getThresholdMax())
            .append("thresholdMin", getThresholdMin())
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
