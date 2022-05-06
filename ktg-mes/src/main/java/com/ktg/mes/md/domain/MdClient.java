package com.ktg.mes.md.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 客户对象 md_client
 * 
 * @author yinjinlu
 * @date 2022-05-06
 */
public class MdClient extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 客户ID */
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

    /** 客户英文名称 */
    @Excel(name = "客户英文名称")
    private String clientEn;

    /** 客户简介 */
    @Excel(name = "客户简介")
    private String clientDes;

    /** 客户LOGO地址 */
    @Excel(name = "客户LOGO地址")
    private String clientLogo;

    /** 客户类型 */
    @Excel(name = "客户类型")
    private String clientType;

    /** 客户地址 */
    @Excel(name = "客户地址")
    private String address;

    /** 客户官网地址 */
    @Excel(name = "客户官网地址")
    private String website;

    /** 客户邮箱地址 */
    @Excel(name = "客户邮箱地址")
    private String email;

    /** 客户电话 */
    @Excel(name = "客户电话")
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
    @Excel(name = "预留字段1")
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

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
    public void setClientEn(String clientEn) 
    {
        this.clientEn = clientEn;
    }

    public String getClientEn() 
    {
        return clientEn;
    }
    public void setClientDes(String clientDes) 
    {
        this.clientDes = clientDes;
    }

    public String getClientDes() 
    {
        return clientDes;
    }
    public void setClientLogo(String clientLogo) 
    {
        this.clientLogo = clientLogo;
    }

    public String getClientLogo() 
    {
        return clientLogo;
    }
    public void setClientType(String clientType) 
    {
        this.clientType = clientType;
    }

    public String getClientType() 
    {
        return clientType;
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
            .append("clientId", getClientId())
            .append("clientCode", getClientCode())
            .append("clientName", getClientName())
            .append("clientNick", getClientNick())
            .append("clientEn", getClientEn())
            .append("clientDes", getClientDes())
            .append("clientLogo", getClientLogo())
            .append("clientType", getClientType())
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
