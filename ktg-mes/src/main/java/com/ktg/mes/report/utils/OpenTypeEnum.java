package com.ktg.mes.report.utils;

import java.util.Arrays;

/**
 * @author: create by libin
 * @version: v1.0
 * @description: pl.piomin.jasperreport.enums
 * @date:2020/4/15
 */
public enum OpenTypeEnum {
    /**
     * 预览
     */
    inline,
    /**
     * 下载
     */
    attachment;

    public static OpenTypeEnum getValue(String value){
        return Arrays.stream(values()).filter(item -> item.name().equals(value)).findFirst().orElse(null);
    }
}
