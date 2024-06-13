package com.ktg.print.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 打印机配置对象 print_printer_config
 * 
 * @author yinjinlu
 * @date 2023-09-01
 */
public class PrintPrinterConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 打印机ID */
    private Long printerId;

    /** 打印机类型 */
    @Excel(name = "打印机类型")
    private String printerType;

    /** 打印机名称 */
    @Excel(name = "打印机名称")
    private String printerName;

    /** 品牌 */
    @Excel(name = "品牌")
    private String brand;

    /** 型号 */
    @Excel(name = "型号")
    private String printerModel;

    /** 连接类型 */
    @Excel(name = "连接类型")
    private String connectionType;

    /** 图片URL */
    @Excel(name = "图片URL")
    private String printerUrl;

    /** 打印机IP */
    @Excel(name = "打印机IP")
    private String printerIp;

    /** 打印机端口 */
    @Excel(name = "打印机端口")
    private Long printerPort;

    /** 打印客户端SID */
    @Excel(name = "打印客户端SID")
    private String clientSid;

    /** 打印客户端IP */
    @Excel(name = "打印客户端IP")
    private String clientIp;

    /** 打印客户端端口 */
    @Excel(name = "打印客户端端口")
    private Long clientPort;

    /** 启用状态 */
    @Excel(name = "启用状态")
    private String enableFlag;

    /** 打印机状态 */
    @Excel(name = "打印机状态")
    private String status;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

    public void setPrinterId(Long printerId) 
    {
        this.printerId = printerId;
    }

    public Long getPrinterId() 
    {
        return printerId;
    }
    public void setPrinterType(String printerType) 
    {
        this.printerType = printerType;
    }

    public String getPrinterType() 
    {
        return printerType;
    }
    public void setPrinterName(String printerName) 
    {
        this.printerName = printerName;
    }

    public String getPrinterName() 
    {
        return printerName;
    }
    public void setBrand(String brand) 
    {
        this.brand = brand;
    }

    public String getBrand() 
    {
        return brand;
    }
    public void setPrinterModel(String printerModel) 
    {
        this.printerModel = printerModel;
    }

    public String getPrinterModel() 
    {
        return printerModel;
    }
    public void setConnectionType(String connectionType) 
    {
        this.connectionType = connectionType;
    }

    public String getConnectionType() 
    {
        return connectionType;
    }
    public void setPrinterUrl(String printerUrl) 
    {
        this.printerUrl = printerUrl;
    }

    public String getPrinterUrl() 
    {
        return printerUrl;
    }
    public void setPrinterIp(String printerIp) 
    {
        this.printerIp = printerIp;
    }

    public String getPrinterIp() 
    {
        return printerIp;
    }
    public void setPrinterPort(Long printerPort) 
    {
        this.printerPort = printerPort;
    }

    public Long getPrinterPort() 
    {
        return printerPort;
    }
    public void setClientSid(String clientSid) 
    {
        this.clientSid = clientSid;
    }

    public String getClientSid() 
    {
        return clientSid;
    }
    public void setClientIp(String clientIp) 
    {
        this.clientIp = clientIp;
    }

    public String getClientIp() 
    {
        return clientIp;
    }
    public void setClientPort(Long clientPort) 
    {
        this.clientPort = clientPort;
    }

    public Long getClientPort() 
    {
        return clientPort;
    }
    public void setEnableFlag(String enableFlag) 
    {
        this.enableFlag = enableFlag;
    }

    public String getEnableFlag() 
    {
        return enableFlag;
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
            .append("printerId", getPrinterId())
            .append("printerType", getPrinterType())
            .append("printerName", getPrinterName())
            .append("brand", getBrand())
            .append("printerModel", getPrinterModel())
            .append("connectionType", getConnectionType())
            .append("printerUrl", getPrinterUrl())
            .append("printerIp", getPrinterIp())
            .append("printerPort", getPrinterPort())
            .append("clientSid", getClientSid())
            .append("clientIp", getClientIp())
            .append("clientPort", getClientPort())
            .append("enableFlag", getEnableFlag())
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
