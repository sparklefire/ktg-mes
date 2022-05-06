package com.ktg.mes.md.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 供应商对象 md_vendor
 * 
 * @author yinjinlu
 * @date 2022-05-06
 */
public class MdVendor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 供应商ID */
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

    /** 供应商英文名称 */
    @Excel(name = "供应商英文名称")
    private String vendorEn;

    /** 供应商简介 */
    @Excel(name = "供应商简介")
    private String vendorDes;

    /** 供应商LOGO地址 */
    @Excel(name = "供应商LOGO地址")
    private String vendorLogo;

    /** 供应商等级 */
    @Excel(name = "供应商等级")
    private String vendorLevel;

    /** 供应商评分 */
    @Excel(name = "供应商评分")
    private Long vendorScore;

    /** 供应商地址 */
    @Excel(name = "供应商地址")
    private String address;

    /** 供应商官网地址 */
    @Excel(name = "供应商官网地址")
    private String website;

    /** 供应商邮箱地址 */
    @Excel(name = "供应商邮箱地址")
    private String email;

    /** 供应商电话 */
    @Excel(name = "供应商电话")
    private String tel;

    /** 联系人1 */
    @Excel(name = "联系人1")
    private String contact1;

    /** 联系人1-电话 */
    @Excel(name = "联系人1-电话")
    private String contact1Tel;

    /** 联系人1-邮箱 */
    @Excel(name = "联系人1-邮箱")
    private String contact1Email;

    /** 联系人2 */
    @Excel(name = "联系人2")
    private String contact2;

    /** 联系人2-电话 */
    @Excel(name = "联系人2-电话")
    private String contact2Tel;

    /** 联系人2-邮箱 */
    @Excel(name = "联系人2-邮箱")
    private String contact2Email;

    /** 统一社会信用代码 */
    @Excel(name = "统一社会信用代码")
    private String creditCode;

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
    public void setVendorEn(String vendorEn) 
    {
        this.vendorEn = vendorEn;
    }

    public String getVendorEn() 
    {
        return vendorEn;
    }
    public void setVendorDes(String vendorDes) 
    {
        this.vendorDes = vendorDes;
    }

    public String getVendorDes() 
    {
        return vendorDes;
    }
    public void setVendorLogo(String vendorLogo) 
    {
        this.vendorLogo = vendorLogo;
    }

    public String getVendorLogo() 
    {
        return vendorLogo;
    }
    public void setVendorLevel(String vendorLevel) 
    {
        this.vendorLevel = vendorLevel;
    }

    public String getVendorLevel() 
    {
        return vendorLevel;
    }
    public void setVendorScore(Long vendorScore) 
    {
        this.vendorScore = vendorScore;
    }

    public Long getVendorScore() 
    {
        return vendorScore;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setWebsite(String website) 
    {
        this.website = website;
    }

    public String getWebsite() 
    {
        return website;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setTel(String tel) 
    {
        this.tel = tel;
    }

    public String getTel() 
    {
        return tel;
    }
    public void setContact1(String contact1) 
    {
        this.contact1 = contact1;
    }

    public String getContact1() 
    {
        return contact1;
    }
    public void setContact1Tel(String contact1Tel) 
    {
        this.contact1Tel = contact1Tel;
    }

    public String getContact1Tel() 
    {
        return contact1Tel;
    }
    public void setContact1Email(String contact1Email) 
    {
        this.contact1Email = contact1Email;
    }

    public String getContact1Email() 
    {
        return contact1Email;
    }
    public void setContact2(String contact2) 
    {
        this.contact2 = contact2;
    }

    public String getContact2() 
    {
        return contact2;
    }
    public void setContact2Tel(String contact2Tel) 
    {
        this.contact2Tel = contact2Tel;
    }

    public String getContact2Tel() 
    {
        return contact2Tel;
    }
    public void setContact2Email(String contact2Email) 
    {
        this.contact2Email = contact2Email;
    }

    public String getContact2Email() 
    {
        return contact2Email;
    }
    public void setCreditCode(String creditCode) 
    {
        this.creditCode = creditCode;
    }

    public String getCreditCode() 
    {
        return creditCode;
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
            .append("vendorId", getVendorId())
            .append("vendorCode", getVendorCode())
            .append("vendorName", getVendorName())
            .append("vendorNick", getVendorNick())
            .append("vendorEn", getVendorEn())
            .append("vendorDes", getVendorDes())
            .append("vendorLogo", getVendorLogo())
            .append("vendorLevel", getVendorLevel())
            .append("vendorScore", getVendorScore())
            .append("address", getAddress())
            .append("website", getWebsite())
            .append("email", getEmail())
            .append("tel", getTel())
            .append("contact1", getContact1())
            .append("contact1Tel", getContact1Tel())
            .append("contact1Email", getContact1Email())
            .append("contact2", getContact2())
            .append("contact2Tel", getContact2Tel())
            .append("contact2Email", getContact2Email())
            .append("creditCode", getCreditCode())
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
