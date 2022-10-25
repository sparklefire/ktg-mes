package com.ktg.mes.aspect;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.mes.wm.domain.WmBarcodeConfig;
import com.ktg.mes.wm.service.IWmBarcodeConfigService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Aspect
@Component
public class BarcodeGenAspect {

    private static final Logger log = LoggerFactory.getLogger(BarcodeGenAspect.class);

    @Autowired
    private IWmBarcodeConfigService wmBarcodeConfigService;

    /**
     * 根据业务controller的类型和返回值生成对应的条码
     * @param joinPoint
     * @param gen
     * @param returnResult
     */
    @AfterReturning(pointcut = "@annotation(gen)",returning = "returnResult")
    public void doAfter(JoinPoint joinPoint, BarcodeGen gen, AjaxResult returnResult){
        //先判断当前类型的业务是否配置了需要自动生成条码
        if(!wmBarcodeConfigService.isAutoGen(gen.barcodeType())){
            //无需自动生成条码
            return ;
        }
        WmBarcodeConfig param = new WmBarcodeConfig();
        param.setBarcodeType(gen.barcodeType());
        List<WmBarcodeConfig> confgs = wmBarcodeConfigService.selectWmBarcodeConfigList(param);
        if(CollectionUtils.isEmpty(confgs)){
           log.warn("当前类型的业务未配置对应的条码生成规则！{}",gen.barcodeType());
        }

        Long businessId = (Long)returnResult.get("data"); //获取业务ID

    }

}
