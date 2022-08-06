package com.ktg.mes.dv.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 设备维修单对象 dv_repair
 * 
 * @author yinjinlu
 * @date 2022-08-06
 */
public class DvRepair extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 维修单ID */
    private Long repairId;

    /** 维修单编号 */
    @Excel(name = "维修单编号")
    private String repairCode;

    /** 维修单名称 */
    @Excel(name = "维修单名称")
    private String repairName;

    /** 设备ID */
    @Excel(name = "设备ID")
    private Long machineryId;

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String machineryCode;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String machineryName;

    /** 品牌 */
    @Excel(name = "品牌")
    private String machineryBrand;

    /** 规格型号 */
    @Excel(name = "规格型号")
    private String machinerySpec;

    /** 设备类型ID */
    @Excel(name = "设备类型ID")
    private Long machineryTypeId;

    /** 报修日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报修日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date requireDate;

    /** 维修完成日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "维修完成日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date finishDate;

    /** 验收日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "验收日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date confirmDate;

    /** 维修结果 */
    @Excel(name = "维修结果")
    private String repairResult;

    /** 维修人员 */
    @Excel(name = "维修人员")
    private String acceptedBy;

    private String acceptName;

    /** 验收人员 */
    @Excel(name = "验收人员")
    private String confirmBy;

    private String confirmName;

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

    public void setRepairId(Long repairId) 
    {
        this.repairId = repairId;
    }

    public Long getRepairId() 
    {
        return repairId;
    }
    public void setRepairCode(String repairCode) 
    {
        this.repairCode = repairCode;
    }

    public String getRepairCode() 
    {
        return repairCode;
    }
    public void setRepairName(String repairName) 
    {
        this.repairName = repairName;
    }

    public String getRepairName() 
    {
        return repairName;
    }
    public void setMachineryId(Long machineryId) 
    {
        this.machineryId = machineryId;
    }

    public Long getMachineryId() 
    {
        return machineryId;
    }
    public void setMachineryCode(String machineryCode) 
    {
        this.machineryCode = machineryCode;
    }

    public String getMachineryCode() 
    {
        return machineryCode;
    }
    public void setMachineryName(String machineryName) 
    {
        this.machineryName = machineryName;
    }

    public String getMachineryName() 
    {
        return machineryName;
    }
    public void setMachineryBrand(String machineryBrand) 
    {
        this.machineryBrand = machineryBrand;
    }

    public String getMachineryBrand() 
    {
        return machineryBrand;
    }
    public void setMachinerySpec(String machinerySpec) 
    {
        this.machinerySpec = machinerySpec;
    }

    public String getMachinerySpec() 
    {
        return machinerySpec;
    }
    public void setMachineryTypeId(Long machineryTypeId) 
    {
        this.machineryTypeId = machineryTypeId;
    }

    public Long getMachineryTypeId() 
    {
        return machineryTypeId;
    }
    public void setRequireDate(Date requireDate) 
    {
        this.requireDate = requireDate;
    }

    public Date getRequireDate() 
    {
        return requireDate;
    }
    public void setFinishDate(Date finishDate) 
    {
        this.finishDate = finishDate;
    }

    public Date getFinishDate() 
    {
        return finishDate;
    }
    public void setConfirmDate(Date confirmDate) 
    {
        this.confirmDate = confirmDate;
    }

    public Date getConfirmDate() 
    {
        return confirmDate;
    }
    public void setRepairResult(String repairResult) 
    {
        this.repairResult = repairResult;
    }

    public String getRepairResult() 
    {
        return repairResult;
    }
    public void setAcceptedBy(String acceptedBy) 
    {
        this.acceptedBy = acceptedBy;
    }

    public String getAcceptedBy() 
    {
        return acceptedBy;
    }
    public void setConfirmBy(String confirmBy) 
    {
        this.confirmBy = confirmBy;
    }

    public String getConfirmBy() 
    {
        return confirmBy;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getAcceptName() {
        return acceptName;
    }

    public void setAcceptName(String acceptName) {
        this.acceptName = acceptName;
    }

    public String getConfirmName() {
        return confirmName;
    }

    public void setConfirmName(String confirmName) {
        this.confirmName = confirmName;
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
            .append("repairId", getRepairId())
            .append("repairCode", getRepairCode())
            .append("repairName", getRepairName())
            .append("machineryId", getMachineryId())
            .append("machineryCode", getMachineryCode())
            .append("machineryName", getMachineryName())
            .append("machineryBrand", getMachineryBrand())
            .append("machinerySpec", getMachinerySpec())
            .append("machineryTypeId", getMachineryTypeId())
            .append("requireDate", getRequireDate())
            .append("finishDate", getFinishDate())
            .append("confirmDate", getConfirmDate())
            .append("repairResult", getRepairResult())
            .append("acceptedBy", getAcceptedBy())
            .append("confirmBy", getConfirmBy())
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
