package com.ktg.mes.wm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 销售出库单对象 wm_product_salse
 * 
 * @author yinjinlu
 * @date 2022-10-04
 */
public class WmProductSalse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 出库单ID */
    private Long salseId;

    /** 出库单编号 */
    @Excel(name = "出库单编号")
    private String salseCode;

    /** 出库单名称 */
    @Excel(name = "出库单名称")
    private String salseName;

    /** 出货检验单ID */
    @Excel(name = "出货检验单ID")
    private Long oqcId;

    /** 出货检验单编号 */
    @Excel(name = "出货检验单编号")
    private String oqcCode;

    /** 销售订单编号 */
    @Excel(name = "销售订单编号")
    private String soCode;

    /** 客户ID */
    @Excel(name = "客户ID")
    private Long clientId;

    /** 客户编码 */
    @Excel(name = "客户编码")
    private String clientCode;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String clientName;

    /** 客户简称 */
    @Excel(name = "客户简称")
    private String clientNick;

    /** 仓库ID */
    @Excel(name = "仓库ID")
    private Long warehouseId;

    /** 仓库编码 */
    @Excel(name = "仓库编码")
    private String warehouseCode;

    /** 仓库名称 */
    @Excel(name = "仓库名称")
    private String warehouseName;

    /** 库区ID */
    @Excel(name = "库区ID")
    private Long locationId;

    /** 库区编码 */
    @Excel(name = "库区编码")
    private String locationCode;

    /** 库区名称 */
    @Excel(name = "库区名称")
    private String locationName;

    /** 库位ID */
    @Excel(name = "库位ID")
    private Long areaId;

    /** 库位编码 */
    @Excel(name = "库位编码")
    private String areaCode;

    /** 库位名称 */
    @Excel(name = "库位名称")
    private String areaName;

    /** 出库日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出库日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date salseDate;

    /** 单据状态 */
    @Excel(name = "单据状态")
    private String status;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

    public void setSalseId(Long salseId) 
    {
        this.salseId = salseId;
    }

    public Long getSalseId() 
    {
        return salseId;
    }
    public void setSalseCode(String salseCode) 
    {
        this.salseCode = salseCode;
    }

    public String getSalseCode() 
    {
        return salseCode;
    }
    public void setSalseName(String salseName) 
    {
        this.salseName = salseName;
    }

    public String getSalseName() 
    {
        return salseName;
    }
    public void setOqcId(Long oqcId) 
    {
        this.oqcId = oqcId;
    }

    public Long getOqcId() 
    {
        return oqcId;
    }
    public void setOqcCode(String oqcCode) 
    {
        this.oqcCode = oqcCode;
    }

    public String getOqcCode() 
    {
        return oqcCode;
    }
    public void setSoCode(String soCode) 
    {
        this.soCode = soCode;
    }

    public String getSoCode() 
    {
        return soCode;
    }
    public void setClientId(Long clientId) 
    {
        this.clientId = clientId;
    }

    public Long getClientId() 
    {
        return clientId;
    }
    public void setClientCode(String clientCode) 
    {
        this.clientCode = clientCode;
    }

    public String getClientCode() 
    {
        return clientCode;
    }
    public void setClientName(String clientName) 
    {
        this.clientName = clientName;
    }

    public String getClientName() 
    {
        return clientName;
    }
    public void setClientNick(String clientNick) 
    {
        this.clientNick = clientNick;
    }

    public String getClientNick() 
    {
        return clientNick;
    }
    public void setWarehouseId(Long warehouseId) 
    {
        this.warehouseId = warehouseId;
    }

    public Long getWarehouseId() 
    {
        return warehouseId;
    }
    public void setWarehouseCode(String warehouseCode) 
    {
        this.warehouseCode = warehouseCode;
    }

    public String getWarehouseCode() 
    {
        return warehouseCode;
    }
    public void setWarehouseName(String warehouseName) 
    {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseName() 
    {
        return warehouseName;
    }
    public void setLocationId(Long locationId) 
    {
        this.locationId = locationId;
    }

    public Long getLocationId() 
    {
        return locationId;
    }
    public void setLocationCode(String locationCode) 
    {
        this.locationCode = locationCode;
    }

    public String getLocationCode() 
    {
        return locationCode;
    }
    public void setLocationName(String locationName) 
    {
        this.locationName = locationName;
    }

    public String getLocationName() 
    {
        return locationName;
    }
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
    public void setSalseDate(Date salseDate) 
    {
        this.salseDate = salseDate;
    }

    public Date getSalseDate() 
    {
        return salseDate;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
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
            .append("salseId", getSalseId())
            .append("salseCode", getSalseCode())
            .append("salseName", getSalseName())
            .append("oqcId", getOqcId())
            .append("oqcCode", getOqcCode())
            .append("soCode", getSoCode())
            .append("clientId", getClientId())
            .append("clientCode", getClientCode())
            .append("clientName", getClientName())
            .append("clientNick", getClientNick())
            .append("warehouseId", getWarehouseId())
            .append("warehouseCode", getWarehouseCode())
            .append("warehouseName", getWarehouseName())
            .append("locationId", getLocationId())
            .append("locationCode", getLocationCode())
            .append("locationName", getLocationName())
            .append("areaId", getAreaId())
            .append("areaCode", getAreaCode())
            .append("areaName", getAreaName())
            .append("salseDate", getSalseDate())
            .append("status", getStatus())
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
