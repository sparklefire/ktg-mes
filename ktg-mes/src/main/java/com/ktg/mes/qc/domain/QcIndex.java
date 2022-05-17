package com.ktg.mes.qc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 检测项对象 qc_index
 * 
 * @author yinjinlu
 * @date 2022-05-17
 */
public class QcIndex extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 检测项ID */
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

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

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
            .append("indexId", getIndexId())
            .append("indexCode", getIndexCode())
            .append("indexName", getIndexName())
            .append("indexType", getIndexType())
            .append("qcTool", getQcTool())
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
