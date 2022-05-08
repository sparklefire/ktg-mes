package com.ktg.mes.wm.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 库位设置对象 wm_storage_area
 * 
 * @author yinjinlu
 * @date 2022-05-08
 */
public class WmStorageArea extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 库位ID */
    private Long areaId;

    /** 库位编码 */
    @Excel(name = "库位编码")
    private String areaCode;

    /** 库位名称 */
    @Excel(name = "库位名称")
    private String areaName;

    /** 库区ID */
    @Excel(name = "库区ID")
    private Long locationId;

    /** 面积 */
    @Excel(name = "面积")
    private BigDecimal area;

    /** 最大载重量 */
    @Excel(name = "最大载重量")
    private BigDecimal maxLoa;

    /** 库位位置X */
    @Excel(name = "库位位置X")
    private Long positionX;

    /** 库位位置y */
    @Excel(name = "库位位置y")
    private Long positionY;

    /** 库位位置z */
    @Excel(name = "库位位置z")
    private Long positionZ;

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

    public void setAreaId(Long areaId) 
    {
        this.areaId = areaId;
    }

    public Long getAreaId() 
    {
        return areaId;
    }
    public void setAreaCode(String areaCode) 
    {
        this.areaCode = areaCode;
    }

    public String getAreaCode() 
    {
        return areaCode;
    }
    public void setAreaName(String areaName) 
    {
        this.areaName = areaName;
    }

    public String getAreaName() 
    {
        return areaName;
    }
    public void setLocationId(Long locationId) 
    {
        this.locationId = locationId;
    }

    public Long getLocationId() 
    {
        return locationId;
    }
    public void setArea(BigDecimal area) 
    {
        this.area = area;
    }

    public BigDecimal getArea() 
    {
        return area;
    }
    public void setMaxLoa(BigDecimal maxLoa) 
    {
        this.maxLoa = maxLoa;
    }

    public BigDecimal getMaxLoa() 
    {
        return maxLoa;
    }
    public void setPositionX(Long positionX) 
    {
        this.positionX = positionX;
    }

    public Long getPositionX() 
    {
        return positionX;
    }
    public void setPositionY(Long positionY) 
    {
        this.positionY = positionY;
    }

    public Long getPositionY() 
    {
        return positionY;
    }
    public void setPositionZ(Long positionZ) 
    {
        this.positionZ = positionZ;
    }

    public Long getPositionZ() 
    {
        return positionZ;
    }
    public void setEnableFlag(String enableFlag) 
    {
        this.enableFlag = enableFlag;
    }

    public String getEnableFlag() 
    {
        return enableFlag;
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
            .append("areaId", getAreaId())
            .append("areaCode", getAreaCode())
            .append("areaName", getAreaName())
            .append("locationId", getLocationId())
            .append("area", getArea())
            .append("maxLoa", getMaxLoa())
            .append("positionX", getPositionX())
            .append("positionY", getPositionY())
            .append("positionZ", getPositionZ())
            .append("enableFlag", getEnableFlag())
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
