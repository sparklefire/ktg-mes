package com.ktg.mes.qc.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 过程检验单行对象 qc_ipqc_line
 * 
 * @author yinjinlu
 * @date 2022-08-30
 */
public class QcIpqcLine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long lineId;

    /** 检验单ID */
    @Excel(name = "检验单ID")
    private Long ipqcId;

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

    /** 检测要求 */
    @Excel(name = "检测要求")
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

    /** 致命缺陷数量 */
    @Excel(name = "致命缺陷数量")
    private BigDecimal crQuantity;

    /** 严重缺陷数量 */
    @Excel(name = "严重缺陷数量")
    private BigDecimal majQuantity;

    /** 轻微缺陷数量 */
    @Excel(name = "轻微缺陷数量")
    private BigDecimal minQuantity;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

    public void setLineId(Long lineId) 
    {
        this.lineId = lineId;
    }

    public Long getLineId() 
    {
        return lineId;
    }
    public void setIpqcId(Long ipqcId) 
    {
        this.ipqcId = ipqcId;
    }

    public Long getIpqcId() 
    {
        return ipqcId;
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
            .append("lineId", getLineId())
            .append("ipqcId", getIpqcId())
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
            .append("crQuantity", getCrQuantity())
            .append("majQuantity", getMajQuantity())
            .append("minQuantity", getMinQuantity())
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
