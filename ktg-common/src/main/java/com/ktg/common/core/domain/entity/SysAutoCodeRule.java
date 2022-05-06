package com.ktg.common.core.domain.entity;

import com.ktg.common.core.domain.BaseEntity;

public class SysAutoCodeRule extends BaseEntity {


    private static final long serialVersionUID = 1L;

    private Long ruleId;

    private String ruleCode;

    private String ruleName;

    private String ruleDesc;

    private Integer maxLength;

    private String isPadded;

    private String paddedChar;

    private String paddedMethod;

    private String enableFlag;

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleDesc() {
        return ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public String getIsPadded() {
        return isPadded;
    }

    public void setIsPadded(String isPadded) {
        this.isPadded = isPadded;
    }

    public String getPaddedChar() {
        return paddedChar;
    }

    public void setPaddedChar(String paddedChar) {
        this.paddedChar = paddedChar;
    }

    public String getPaddedMethod() {
        return paddedMethod;
    }

    public void setPaddedMethod(String paddedMethod) {
        this.paddedMethod = paddedMethod;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    @Override
    public String toString() {
        return "SysAutoCodeRule{" +
                "ruleId=" + ruleId +
                ", ruleCode='" + ruleCode + '\'' +
                ", ruleName='" + ruleName + '\'' +
                ", ruleDesc='" + ruleDesc + '\'' +
                ", maxLength=" + maxLength +
                ", isPadded='" + isPadded + '\'' +
                ", paddedChar='" + paddedChar + '\'' +
                ", paddedMethod='" + paddedMethod + '\'' +
                ", enableFlag='" + enableFlag + '\'' +
                '}';
    }
}
