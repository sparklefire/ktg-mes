package com.ktg.mes.aspect;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BarcodeGen {
    /**
     * 业务类型
     * @return
     */
    String barcodeType();
}
