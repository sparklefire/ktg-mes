package com.ktg.mes.pro.controller.mobile;

import cn.hutool.core.collection.CollectionUtil;
import com.ktg.common.annotation.Log;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.enums.BusinessType;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.pro.domain.ProTask;
import com.ktg.mes.pro.domain.ProTransOrder;
import com.ktg.mes.pro.service.IProTaskService;
import com.ktg.mes.pro.service.IProTransOrderService;
import com.ktg.mes.wm.domain.WmBarcode;
import com.ktg.mes.wm.service.IWmBarcodeService;
import com.ktg.system.strategy.AutoCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("流转单接口")
@RestController
@RequestMapping("/mobile/pro/transorder")
public class ProTransOrderMobController extends BaseController {

    @Autowired
    private IProTransOrderService proTransOrderService;

    @Autowired
    private AutoCodeUtil autoCodeUtil;

    @Autowired
    private IProTaskService proTaskService;

    @Autowired
    private IWmBarcodeService wmBarcodeService;

    @ApiOperation("获取流转单清单")
    @GetMapping("/getList")
    public AjaxResult getList(ProTransOrder proTransOrder){


        return AjaxResult.success();
    }

    /**
     * 获取流转单详细信息
     */
    @ApiOperation("流转单详情查询接口")
    @PreAuthorize("@ss.hasPermi('mes:pro:transorder:query')")
    @GetMapping("/getInfo")
    public AjaxResult getInfo(ProTransOrder proTransOrder)
    {
        ProTransOrder order = null;
        if(StringUtils.isNotNull(proTransOrder.getTransOrderId())){
            order = proTransOrderService.selectProTransOrderByTransOrderId(proTransOrder.getTransOrderId());
        }

        if(StringUtils.isNotNull(proTransOrder.getTransOrderCode())){
            ProTransOrder param = new ProTransOrder();
            param.setTransOrderCode(proTransOrder.getTransOrderCode());
            List<ProTransOrder> orders =proTransOrderService.selectProTransOrderList(param);
            if(!CollectionUtil.isEmpty(orders)){
                order = orders.get(0);
            }
        }

        return AjaxResult.success(order);
    }


    /**
     * 新增流转单
     */
    @ApiOperation("流转单新增接口")
    @PreAuthorize("@ss.hasPermi('mes:pro:transorder:add')")
    @Log(title = "流转单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProTransOrder proTransOrder)
    {
        if(!StringUtils.isNotNull(proTransOrder.getTransOrderCode())){
            String transOrderCdoe = autoCodeUtil.genSerialCode(UserConstants.TRANS_ORDER_CODE,"");
            proTransOrder.setTransOrderCode(transOrderCdoe);
        }

        if(StringUtils.isNotNull(proTransOrder.getTaskId())){
            ProTask task =proTaskService.selectProTaskByTaskId(proTransOrder.getTaskId());
            proTransOrder.setTaskCode(task.getTaskCode());
            proTransOrder.setWorkstationId(task.getWorkstationId());
            proTransOrder.setWorkstationCode(task.getWorkstationCode());
            proTransOrder.setWorkstationName(task.getWorkstationName());
            proTransOrder.setProcessId(task.getProcessId());
            proTransOrder.setProcessCode(task.getProcessCode());
            proTransOrder.setProcessName(task.getProcessName());
            proTransOrder.setWorkorderId(task.getWorkorderId());
            proTransOrder.setWorkorderCode(task.getWorkorderCode());
            proTransOrder.setWorkorderName(task.getWorkorderName());
            //TODO:批次信息的获取
            proTransOrder.setItemId(task.getItemId());
            proTransOrder.setItemCode(task.getItemCode());
            proTransOrder.setItemName(task.getItemName());
            proTransOrder.setUnitOfMeasure(task.getUnitOfMeasure());
            proTransOrder.setSpecification(task.getSpecification());
        }else{
            return AjaxResult.error("请提供生产任务数据");
        }

        if(StringUtils.isNotNull(proTransOrder.getQuantityTransfered())){
            return AjaxResult.error("请填写报工数量");
        }
        proTransOrder.setCreateBy(getUsername());
        proTransOrderService.insertProTransOrder(proTransOrder);

        //自动生成条码
        WmBarcode code = new WmBarcode();
        code.setBarcodeType(UserConstants.BARCODE_TYPE_TRANSORDER);
        code.setBarcodeContent("TRANSORDER-"+proTransOrder.getTransOrderCode());
        code.setBarcodeFormart(UserConstants.QR_CODE);
        code.setBussinessId(proTransOrder.getTransOrderId());
        code.setBussinessCode(proTransOrder.getTransOrderCode());
        code.setEnableFlag(UserConstants.YES);
        String path =wmBarcodeService.generateBarcode(code);
        code.setBarcodeUrl(path);
        wmBarcodeService.updateWmBarcode(code);
        proTransOrder.setBarCodeUrl(path);
        proTransOrderService.updateProTransOrder(proTransOrder);

        return AjaxResult.success(proTransOrder);
    }

    /**
     * 修改流转单
     */
    @ApiOperation("流转单修改接口")
    @PreAuthorize("@ss.hasPermi('mes:pro:transorder:edit')")
    @Log(title = "流转单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProTransOrder proTransOrder)
    {
        return toAjax(proTransOrderService.updateProTransOrder(proTransOrder));
    }


}
