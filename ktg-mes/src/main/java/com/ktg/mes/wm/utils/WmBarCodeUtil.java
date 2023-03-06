package com.ktg.mes.wm.utils;

import com.ktg.common.constant.UserConstants;
import com.ktg.mes.wm.domain.WmBarcode;
import com.ktg.mes.wm.domain.WmBarcodeConfig;
import com.ktg.mes.wm.service.IWmBarcodeConfigService;
import com.ktg.mes.wm.service.IWmBarcodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class WmBarCodeUtil {

    private static final Logger log = LoggerFactory.getLogger(WmBarCodeUtil.class);

    @Autowired
    private IWmBarcodeConfigService wmBarcodeConfigService;

    @Autowired
    private IWmBarcodeService wmBarcodeService;

    public void generateBarCode(String type,Long businessId,String businessCode,String businessName){
        //先判断当前类型的业务是否配置了需要自动生成条码
        if(!wmBarcodeConfigService.isAutoGen(type)){
            //无需自动生成条码
            return ;
        }
        WmBarcodeConfig param = new WmBarcodeConfig();
        param.setBarcodeType(type);
        List<WmBarcodeConfig> confgs = wmBarcodeConfigService.selectWmBarcodeConfigList(param);
        if(CollectionUtils.isEmpty(confgs)){
            log.warn("当前类型的业务未配置对应的条码生成规则！{}",type);
            return ;
        }

        WmBarcodeConfig config = confgs.get(0);
        WmBarcode barcode = new WmBarcode();
        barcode.setBarcodeFormart(config.getBarcodeFormart());
        barcode.setBarcodeType(type);
        barcode.setBarcodeContent(config.getContentFormart().replace("{BUSINESSCODE}",businessCode));
        barcode.setBussinessCode(businessCode);
        barcode.setEnableFlag(UserConstants.YES);
        barcode.setBussinessId(businessId);

        if(UserConstants.NOT_UNIQUE.equals(wmBarcodeService.checkBarcodeUnique(barcode))){
            log.warn("当前业务对象的赋码已存在！{} {}",type,businessCode);
            return ;
        }

        String path = wmBarcodeService.generateBarcode(barcode);
        barcode.setBarcodeUrl(path);
        wmBarcodeService.insertWmBarcode(barcode);
    }


}
