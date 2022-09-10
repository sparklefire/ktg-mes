package com.ktg.mes.md.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 工作站对象 md_workstation
 * 
 * @author yinjinlu
 * @date 2022-05-10
 */
public class MdWorkstation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工作站ID */
    private Long workstationId;

    /** 工作站编码 */
    @Excel(name = "工作站编码")
    private String workstationCode;

    /** 工作站名称 */
    @Excel(name = "工作站名称")
    private String workstationName;

    /** 工作站地点 */
    @Excel(name = "工作站地点")
    private String workstationAddress;

    /** 所在车间ID */
    @Excel(name = "所在车间ID")
    private Long workshopId;

    /** 所在车间编码 */
    @Excel(name = "所在车间编码")
    private String workshopCode;

    /** 所在车间名称 */
    @Excel(name = "所在车间名称")
    private String workshopName;

    /** 工序ID */
    @Excel(name = "工序ID")
    private Long processId;

    /** 工序编码 */
    @Excel(name = "工序编码")
    private String processCode;

    /** 工序名称 */
    @Excel(name = "工序名称")
    private String processName;

    /** 线边库ID */
    @Excel(name = "仓库ID")
    private Long warehouseId;

    /** 线边库编码 */
    @Excel(name = "仓库编码")
    private String warehouseCode;

    /** 线边库名称 */
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

    public void setWorkstationId(Long workstationId) 
    {
        this.workstationId = workstationId;
    }

    public Long getWorkstationId() 
    {
        return workstationId;
    }
    public void setWorkstationCode(String workstationCode) 
    {
        this.workstationCode = workstationCode;
    }

    public String getWorkstationCode() 
    {
        return workstationCode;
    }
    public void setWorkstationName(String workstationName) 
    {
        this.workstationName = workstationName;
    }

    public String getWorkstationName() 
    {
        return workstationName;
    }
    public void setWorkstationAddress(String workstationAddress) 
    {
        this.workstationAddress = workstationAddress;
    }

    public String getWorkstationAddress() 
    {
        return workstationAddress;
    }
    public void setWorkshopId(Long workshopId) 
    {
        this.workshopId = workshopId;
    }

    public Long getWorkshopId() 
    {
        return workshopId;
    }
    public void setWorkshopCode(String workshopCode) 
    {
        this.workshopCode = workshopCode;
    }

    public String getWorkshopCode() 
    {
        return workshopCode;
    }
    public void setWorkshopName(String workshopName) 
    {
        this.workshopName = workshopName;
    }

    public String getWorkshopName() 
    {
        return workshopName;
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

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
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
        return "MdWorkstation{" +
                "workstationId=" + workstationId +
                ", workstationCode='" + workstationCode + '\'' +
                ", workstationName='" + workstationName + '\'' +
                ", workstationAddress='" + workstationAddress + '\'' +
                ", workshopId=" + workshopId +
                ", workshopCode='" + workshopCode + '\'' +
                ", workshopName='" + workshopName + '\'' +
                ", processId=" + processId +
                ", processCode='" + processCode + '\'' +
                ", processName='" + processName + '\'' +
                ", warehouseId=" + warehouseId +
                ", warehouseCode='" + warehouseCode + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", locationId=" + locationId +
                ", locationCode='" + locationCode + '\'' +
                ", locationName='" + locationName + '\'' +
                ", areaId=" + areaId +
                ", areaCode='" + areaCode + '\'' +
                ", areaName='" + areaName + '\'' +
                ", enableFlag='" + enableFlag + '\'' +
                ", attr1='" + attr1 + '\'' +
                ", attr2='" + attr2 + '\'' +
                ", attr3=" + attr3 +
                ", attr4=" + attr4 +
                '}';
    }
}
