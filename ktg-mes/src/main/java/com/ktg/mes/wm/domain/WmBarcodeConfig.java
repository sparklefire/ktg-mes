package com.ktg.mes.wm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.BaseEntity;

/**
 * 条码配置对象 wm_barcode_config
 * 
 * @author yinjinlu
 * @date 2022-10-22
 */
public class WmBarcodeConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 配置ID */
    private Long configId;

    /** 条码格式 */
    @Excel(name = "条码格式")
    private String barcodeFormart;

    /** 条码类型 */
    @Excel(name = "条码类型")
    private String barcodeType;

    /** 内容格式 */
    @Excel(name = "内容格式")
    private String contentFormart;

    /** 内容样例 */
    @Excel(name = "内容样例")
    private String contentExample;

    /** 是否自动生成 */
    @Excel(name = "是否自动生成")
    private String autoGenFlag;

    /** 默认的打印模板 **/
    private String defaultTemplate;

    /** 是否生效 */
    @Excel(name = "是否生效")
    private String enableFlag;

    /** 预留字段1 */
    @Excel(name = "预留字段1")
    private String attr1;

    /** 预留字段2 */
    @Excel(name = "预留字段2")
    private String attr2;

    /** 预留字段3 */
    @Excel(name = "预留字段3")
    private Long attr3;

    /** 预留字段4 */
    @Excel(name = "预留字段4")
    private Long attr4;

    public void setConfigId(Long configId) 
    {
        this.configId = configId;
    }

    public Long getConfigId() 
    {
        return configId;
    }
    public void setBarcodeFormart(String barcodeFormart) 
    {
        this.barcodeFormart = barcodeFormart;
    }

    public String getBarcodeFormart() 
    {
        return barcodeFormart;
    }
    public void setBarcodeType(String barcodeType) 
    {
        this.barcodeType = barcodeType;
    }

    public String getBarcodeType() 
    {
        return barcodeType;
    }
    public void setContentFormart(String contentFormart) 
    {
        this.contentFormart = contentFormart;
    }

    public String getContentFormart() 
    {
        return contentFormart;
    }
    public void setContentExample(String contentExample) 
    {
        this.contentExample = contentExample;
    }

    public String getContentExample() 
    {
        return contentExample;
    }
    public void setAutoGenFlag(String autoGenFlag) 
    {
        this.autoGenFlag = autoGenFlag;
    }

    public String getAutoGenFlag() 
    {
        return autoGenFlag;
    }
    public String getDefaultTemplate() {
        return defaultTemplate;
    }

    public void setDefaultTemplate(String defaultTemplate) {
        this.defaultTemplate = defaultTemplate;
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
        return "WmBarcodeConfig{" +
                "configId=" + configId +
                ", barcodeFormart='" + barcodeFormart + '\'' +
                ", barcodeType='" + barcodeType + '\'' +
                ", contentFormart='" + contentFormart + '\'' +
                ", contentExample='" + contentExample + '\'' +
                ", autoGenFlag='" + autoGenFlag + '\'' +
                ", defaultTemplate='" + defaultTemplate + '\'' +
                ", enableFlag='" + enableFlag + '\'' +
                ", attr1='" + attr1 + '\'' +
                ", attr2='" + attr2 + '\'' +
                ", attr3=" + attr3 +
                ", attr4=" + attr4 +
                '}';
    }
}
