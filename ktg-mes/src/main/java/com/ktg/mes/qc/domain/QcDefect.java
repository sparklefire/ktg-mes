package com.ktg.mes.qc.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 常见缺陷对象 qc_defect
 * 
 * @author yinjinlu
 * @date 2022-05-19
 */
public class QcDefect extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 缺陷ID */
    private Long defectId;

    /** 缺陷编码 */
    @Excel(name = "缺陷编码")
    private String defectCode;

    /** 缺陷描述 */
    @Excel(name = "缺陷描述")
    private String defectName;

    /** 检测项类型 */
    @Excel(name = "检测项类型")
    private String indexType;

    /** 缺陷等级 */
    @Excel(name = "缺陷等级")
    private String defectLevel;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

    public void setDefectId(Long defectId) 
    {
        this.defectId = defectId;
    }

    public Long getDefectId() 
    {
        return defectId;
    }
    public void setDefectCode(String defectCode) 
    {
        this.defectCode = defectCode;
    }

    public String getDefectCode() 
    {
        return defectCode;
    }
    public void setDefectName(String defectName) 
    {
        this.defectName = defectName;
    }

    public String getDefectName() 
    {
        return defectName;
    }
    public void setIndexType(String indexType) 
    {
        this.indexType = indexType;
    }

    public String getIndexType() 
    {
        return indexType;
    }
    public void setDefectLevel(String defectLevel) 
    {
        this.defectLevel = defectLevel;
    }

    public String getDefectLevel() 
    {
        return defectLevel;
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
            .append("defectId", getDefectId())
            .append("defectCode", getDefectCode())
            .append("defectName", getDefectName())
            .append("indexType", getIndexType())
            .append("defectLevel", getDefectLevel())
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
