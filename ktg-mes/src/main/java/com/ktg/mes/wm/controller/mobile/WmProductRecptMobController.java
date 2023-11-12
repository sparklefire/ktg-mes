package com.ktg.mes.wm.controller.mobile;

import cn.hutool.core.collection.CollUtil;
import com.ktg.common.annotation.Log;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.core.page.TableDataInfo;
import com.ktg.common.enums.BusinessType;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.pro.domain.ProWorkorder;
import com.ktg.mes.pro.service.IProWorkorderService;
import com.ktg.mes.wm.domain.*;
import com.ktg.mes.wm.domain.tx.ProductRecptTxBean;
import com.ktg.mes.wm.service.*;
import com.ktg.system.strategy.AutoCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api("产品入库")
@RestController
@RequestMapping("/mobile/wm/productrecpt")
public class WmProductRecptMobController extends BaseController {

    @Autowired
    private IWmProductRecptService wmProductRecptService;

    @Autowired
    private IWmProductRecptLineService wmProductRecptLineService;

    @Autowired
    private IStorageCoreService storageCoreService;

    @Autowired
    private IProWorkorderService proWorkorderService;

    @Autowired
    private AutoCodeUtil autoCodeUtil;

    /**
     * 查询产品入库录列表
     */
    @ApiOperation("查询产品入库单清单接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:productrecpt:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmProductRecpt wmProductRecpt)
    {
        startPage();
        List<WmProductRecpt> list = wmProductRecptService.selectWmProductRecptList(wmProductRecpt);
        return getDataTable(list);
    }


    /**
     * 获取产品入库录详细信息
     */
    @ApiOperation("获取产品入库单明细信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:productrecpt:query')")
    @GetMapping(value = "/{recptId}")
    public AjaxResult getInfo(@PathVariable("recptId") Long recptId)
    {
        return AjaxResult.success(wmProductRecptService.selectWmProductRecptByRecptId(recptId));
    }

    /**
     * 新增产品入库记录
     */
    @ApiOperation("新增产品入库单基本信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:productrecpt:add')")
    @Log(title = "产品入库记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmProductRecpt wmProductRecpt)
    {
        if(StringUtils.isNotNull(wmProductRecpt.getRecptCode())){
            if(UserConstants.NOT_UNIQUE.equals(wmProductRecptService.checkUnique(wmProductRecpt))){
                return AjaxResult.error("入库单编号已存在！");
            }
        }else {
            wmProductRecpt.setRecptCode(autoCodeUtil.genSerialCode(UserConstants.PRODUCTRECPT_CODE,""));
        }


        if(StringUtils.isNotNull(wmProductRecpt.getWorkorderId())){
            ProWorkorder workorder = proWorkorderService.selectProWorkorderByWorkorderId(wmProductRecpt.getWorkorderId());
            wmProductRecpt.setWorkorderCode(workorder.getWorkorderCode());
            wmProductRecpt.setWorkorderName(workorder.getWorkorderName());
        }

        wmProductRecpt.setCreateBy(getUsername());
        wmProductRecptService.insertWmProductRecpt(wmProductRecpt);
        return AjaxResult.success(wmProductRecpt);
    }

    /**
     * 修改产品入库录
     */
    @ApiOperation("编辑产品入库单基本信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:productrecpt:edit')")
    @Log(title = "产品入库记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmProductRecpt wmProductRecpt)
    {
        if(UserConstants.NOT_UNIQUE.equals(wmProductRecptService.checkUnique(wmProductRecpt))){
            return AjaxResult.error("入库单编号已存在！");
        }

        if(StringUtils.isNotNull(wmProductRecpt.getWorkorderId())){
            ProWorkorder workorder = proWorkorderService.selectProWorkorderByWorkorderId(wmProductRecpt.getWorkorderId());
            wmProductRecpt.setWorkorderCode(workorder.getWorkorderCode());
            wmProductRecpt.setWorkorderName(workorder.getWorkorderName());
        }

        return toAjax(wmProductRecptService.updateWmProductRecpt(wmProductRecpt));
    }

    /**
     * 删除产品入库录
     */
    @ApiOperation("删除产品入库单接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:productrecpt:remove')")
    @Log(title = "产品入库记录", businessType = BusinessType.DELETE)
    @Transactional
    @DeleteMapping("/{recptIds}")
    public AjaxResult remove(@PathVariable Long[] recptIds)
    {
        for (Long recptId: recptIds
        ) {
            WmProductRecpt recpt = wmProductRecptService.selectWmProductRecptByRecptId(recptId);
            if(!UserConstants.ORDER_STATUS_PREPARE.equals(recpt.getStatus())){
                return AjaxResult.error("只能删除草稿状态的单据!");
            }
            wmProductRecptLineService.deleteByRecptId(recptId);
        }
        return toAjax(wmProductRecptService.deleteWmProductRecptByRecptIds(recptIds));
    }

    /**
     * 执行入库
     * @return
     */
    @ApiOperation("产品入库单执行入库接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:productrecpt:edit')")
    @Log(title = "产品入库记录", businessType = BusinessType.UPDATE)
    @Transactional
    @PutMapping("/{recptId}")
    public AjaxResult execute(@PathVariable Long recptId){
        WmProductRecpt recpt = wmProductRecptService.selectWmProductRecptByRecptId(recptId);

        //单据有效性
        if(!StringUtils.isNotNull(recpt)){
            return AjaxResult.error("无效单据");
        }

        //先检查单据状态
        if(UserConstants.ORDER_STATUS_FINISHED.equals(recpt.getStatus())){
            return AjaxResult.error("当前单据已提交!");
        }


        WmProductRecptLine param = new WmProductRecptLine();
        param.setRecptId(recptId);
        List<WmProductRecptLine> lines = wmProductRecptLineService.selectWmProductRecptLineList(param);
        if(CollUtil.isEmpty(lines)){
            return AjaxResult.error("请添加要入库的产品");
        }

        List<ProductRecptTxBean> beans = wmProductRecptService.getTxBean(recptId);
        storageCoreService.processProductRecpt(beans);

        recpt.setStatus(UserConstants.ORDER_STATUS_FINISHED);
        wmProductRecptService.updateWmProductRecpt(recpt);

        return AjaxResult.success();
    }

}
