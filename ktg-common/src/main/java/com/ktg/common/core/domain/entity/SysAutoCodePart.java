package com.ktg.common.core.domain.entity;

import com.ktg.common.core.domain.BaseEntity;

public class SysAutoCodePart extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long partId;

    private Long ruleId;

    private Integer partIndex;

    private String partType;

    private String partCode;

    private String partName;

    private Integer partLength;

    private String dateFormat;

    private String inputCharacter;

    private String fixCharacter;

    private Integer seriaStartNo;

    private Integer seriaStep;

    private Integer seriaNowNo;

    private String cycleFlag;

    private String cycleMethod;


    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getPartIndex() {
        return partIndex;
    }

    public void setPartIndex(Integer partIndex) {
        this.partIndex = partIndex;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public String getPartCode() {
        return partCode;
    }

    public void setPartCode(String partCode) {
        this.partCode = partCode;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Integer getPartLength() {
        return partLength;
    }

    public void setPartLength(Integer partLength) {
        this.partLength = partLength;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getInputCharacter() {
        return inputCharacter;
    }

    public void setInputCharacter(String inputCharacter) {
        this.inputCharacter = inputCharacter;
    }

    public String getFixCharacter() {
        return fixCharacter;
    }

    public void setFixCharacter(String fixCharacter) {
        this.fixCharacter = fixCharacter;
    }

    public Integer getSeriaStartNo() {
        return seriaStartNo;
    }

    public void setSeriaStartNo(Integer seriaStartNo) {
        this.seriaStartNo = seriaStartNo;
    }

    public Integer getSeriaStep() {
        return seriaStep;
    }

    public void setSeriaStep(Integer seriaStep) {
        this.seriaStep = seriaStep;
    }

    public Integer getSeriaNowNo() {
        return seriaNowNo;
    }

    public void setSeriaNowNo(Integer seriaNowNo) {
        this.seriaNowNo = seriaNowNo;
    }

    public String getCycleFlag() {
        return cycleFlag;
    }

    public void setCycleFlag(String cycleFlag) {
        this.cycleFlag = cycleFlag;
    }

    public String getCycleMethod() {
        return cycleMethod;
    }

    public void setCycleMethod(String cycleMethod) {
        this.cycleMethod = cycleMethod;
    }

    @Override
    public String toString() {
        return "SysAutoCodePart{" +
                "partId=" + partId +
                ", ruleId=" + ruleId +
                ", partIndex=" + partIndex +
                ", partType='" + partType + '\'' +
                ", partCode='" + partCode + '\'' +
                ", partName='" + partName + '\'' +
                ", partLength=" + partLength +
                ", dateFormat='" + dateFormat + '\'' +
                ", inputCharacter='" + inputCharacter + '\'' +
                ", fixCharacter='" + fixCharacter + '\'' +
                ", seriaStartNo=" + seriaStartNo +
                ", seriaStep=" + seriaStep +
                ", seriaNowNo=" + seriaNowNo +
                ", cycleFlag='" + cycleFlag + '\'' +
                ", cycleMethod='" + cycleMethod + '\'' +
                '}';
    }
}
