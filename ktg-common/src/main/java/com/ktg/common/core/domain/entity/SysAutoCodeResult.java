package com.ktg.common.core.domain.entity;

import com.ktg.common.core.domain.BaseEntity;

public class SysAutoCodeResult extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long codeId;

    private Long ruleId;

    private String genDate;

    private int genIndex;

    private String lastResult;

    private int lastSerialNo;

    private String lastInputChar;

    public Long getCodeId() {
        return codeId;
    }

    public void setCodeId(Long codeId) {
        this.codeId = codeId;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getGenDate() {
        return genDate;
    }

    public void setGenDate(String genDate) {
        this.genDate = genDate;
    }

    public int getGenIndex() {
        return genIndex;
    }

    public void setGenIndex(int genIndex) {
        this.genIndex = genIndex;
    }

    public String getLastResult() {
        return lastResult;
    }

    public void setLastResult(String lastResult) {
        this.lastResult = lastResult;
    }

    public int getLastSerialNo() {
        return lastSerialNo;
    }

    public void setLastSerialNo(int lastSerialNo) {
        this.lastSerialNo = lastSerialNo;
    }

    public String getLastInputChar() {
        return lastInputChar;
    }

    public void setLastInputChar(String lastInputChar) {
        this.lastInputChar = lastInputChar;
    }

    @Override
    public String toString() {
        return "SysAutoCodeResult{" +
                "codeId=" + codeId +
                ", ruleId=" + ruleId +
                ", genDate='" + genDate + '\'' +
                ", genIndex=" + genIndex +
                ", lastResult='" + lastResult + '\'' +
                ", lastSerialNo=" + lastSerialNo +
                ", lastInputChar='" + lastInputChar + '\'' +
                '}';
    }
}
