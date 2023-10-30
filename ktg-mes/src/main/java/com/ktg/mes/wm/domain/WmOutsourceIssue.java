package com.ktg.mes.wm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 外协领料单头对象 wm_outsource_issue
 * 
 * @author yinjinlu
 * @date 2023-10-30
 */
public class WmOutsourceIssue extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 领料单ID */
    private Long issueId;

    /** 领料单编号 */
    @Excel(name = "领料单编号")
    private String issueCode;

    /** 领料单名称 */
    @Excel(name = "领料单名称")
    private String issueName;

    /** 生产工单ID */
    @Excel(name = "生产工单ID")
    private Long workorderId;

    /** 生产工单编码 */
    @Excel(name = "生产工单编码")
    private String workorderCode;

    /** 供应商ID */
    @Excel(name = "供应商ID")
    private Long vendorId;

    /** 供应商编码 */
    @Excel(name = "供应商编码")
    private String vendorCode;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String vendorName;

    /** 供应商简称 */
    @Excel(name = "供应商简称")
    private String vendorNick;

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

    /** 领料日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "领料日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date issueDate;

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

    public void setIssueId(Long issueId) 
    {
        this.issueId = issueId;
    }

    public Long getIssueId() 
    {
        return issueId;
    }
    public void setIssueCode(String issueCode) 
    {
        this.issueCode = issueCode;
    }

    public String getIssueCode() 
    {
        return issueCode;
    }
    public void setIssueName(String issueName) 
    {
        this.issueName = issueName;
    }

    public String getIssueName() 
    {
        return issueName;
    }
    public void setWorkorderId(Long workorderId) 
    {
        this.workorderId = workorderId;
    }

    public Long getWorkorderId() 
    {
        return workorderId;
    }
    public void setWorkorderCode(String workorderCode) 
    {
        this.workorderCode = workorderCode;
    }

    public String getWorkorderCode() 
    {
        return workorderCode;
    }
    public void setVendorId(Long vendorId) 
    {
        this.vendorId = vendorId;
    }

    public Long getVendorId() 
    {
        return vendorId;
    }
    public void setVendorCode(String vendorCode) 
    {
        this.vendorCode = vendorCode;
    }

    public String getVendorCode() 
    {
        return vendorCode;
    }
    public void setVendorName(String vendorName) 
    {
        this.vendorName = vendorName;
    }

    public String getVendorName() 
    {
        return vendorName;
    }
    public void setVendorNick(String vendorNick) 
    {
        this.vendorNick = vendorNick;
    }

    public String getVendorNick() 
    {
        return vendorNick;
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
    public void setIssueDate(Date issueDate) 
    {
        this.issueDate = issueDate;
    }

    public Date getIssueDate() 
    {
        return issueDate;
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
            .append("issueId", getIssueId())
            .append("issueCode", getIssueCode())
            .append("issueName", getIssueName())
            .append("workorderId", getWorkorderId())
            .append("workorderCode", getWorkorderCode())
            .append("vendorId", getVendorId())
            .append("vendorCode", getVendorCode())
            .append("vendorName", getVendorName())
            .append("vendorNick", getVendorNick())
            .append("warehouseId", getWarehouseId())
            .append("warehouseCode", getWarehouseCode())
            .append("warehouseName", getWarehouseName())
            .append("locationId", getLocationId())
            .append("locationCode", getLocationCode())
            .append("locationName", getLocationName())
            .append("areaId", getAreaId())
            .append("areaCode", getAreaCode())
            .append("areaName", getAreaName())
            .append("issueDate", getIssueDate())
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
