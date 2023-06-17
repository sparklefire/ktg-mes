package com.ktg.mes.wm.controller.mobile;

import cn.hutool.core.collection.CollectionUtil;
import com.ktg.common.annotation.Log;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.enums.BusinessType;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.wm.domain.*;
import com.ktg.mes.wm.domain.tx.ItemRecptTxBean;
import com.ktg.mes.wm.service.*;
import com.ktg.system.strategy.AutoCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("采购入库")
@RestController
@RequestMapping("/mobile/wm/itemrecpt")
public class WmItemRecptMobController extends BaseController {

    @Autowired
    private IWmItemRecptService wmItemRecptService;

    @Autowired
    private IWmItemRecptLineService wmItemRecptLineService;

    @Autowired
    private IWmWarehouseService wmWarehouseService;

    @Autowired
    private IWmStorageLocationService wmStorageLocationService;

    @Autowired
    private IWmStorageAreaService wmStorageAreaService;

    @Autowired
    private IStorageCoreService storageCoreService;

    @Autowired
    private AutoCodeUtil autoCodeUtil;

    /**
     * 新增物料入库单
     */
    @ApiOperation("新增采购入库单基本信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:itemrecpt:add')")
    @Log(title = "物料入库单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmItemRecpt wmItemRecpt)
    {
        if(StringUtils.isNotNull(wmItemRecpt.getRecptCode())){
            if(UserConstants.NOT_UNIQUE.equals(wmItemRecptService.checkRecptCodeUnique(wmItemRecpt))){
                return  AjaxResult.error("单据编号已存在！");
            }
        }else {
            wmItemRecpt.setRecptCode(autoCodeUtil.genSerialCode(UserConstants.ITEMRECPT_CODE,""));
        }


        if(StringUtils.isNotNull(wmItemRecpt.getWarehouseId())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseId(wmItemRecpt.getWarehouseId());
            wmItemRecpt.setWarehouseCode(warehouse.getWarehouseCode());
            wmItemRecpt.setWarehouseName(warehouse.getWarehouseName());
        }
        if(StringUtils.isNotNull(wmItemRecpt.getLocationId())){
            WmStorageLocation location = wmStorageLocationService.selectWmStorageLocationByLocationId(wmItemRecpt.getLocationId());
            wmItemRecpt.setLocationCode(location.getLocationCode());
            wmItemRecpt.setLocationName(location.getLocationName());
        }
        if(StringUtils.isNotNull(wmItemRecpt.getAreaId())){
            WmStorageArea area = wmStorageAreaService.selectWmStorageAreaByAreaId(wmItemRecpt.getAreaId());
            wmItemRecpt.setAreaCode(area.getAreaCode());
            wmItemRecpt.setAreaName(area.getAreaName());
        }
        wmItemRecpt.setCreateBy(getUsername());
        wmItemRecptService.insertWmItemRecpt(wmItemRecpt);
        return AjaxResult.success(wmItemRecpt);
    }

    /**
     * 修改物料入库单
     */
    @ApiOperation("修改采购入库单基本信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:itemrecpt:edit')")
    @Log(title = "物料入库单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmItemRecpt wmItemRecpt)
    {
        if(StringUtils.isNotNull(wmItemRecpt.getWarehouseId())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseId(wmItemRecpt.getWarehouseId());
            wmItemRecpt.setWarehouseCode(warehouse.getWarehouseCode());
            wmItemRecpt.setWarehouseName(warehouse.getWarehouseName());
        }
        if(StringUtils.isNotNull(wmItemRecpt.getLocationId())){
            WmStorageLocation location = wmStorageLocationService.selectWmStorageLocationByLocationId(wmItemRecpt.getLocationId());
            wmItemRecpt.setLocationCode(location.getLocationCode());
            wmItemRecpt.setLocationName(location.getLocationName());
        }
        if(StringUtils.isNotNull(wmItemRecpt.getAreaId())){
            WmStorageArea area = wmStorageAreaService.selectWmStorageAreaByAreaId(wmItemRecpt.getAreaId());
            wmItemRecpt.setAreaCode(area.getAreaCode());
            wmItemRecpt.setAreaName(area.getAreaName());
        }
        return toAjax(wmItemRecptService.updateWmItemRecpt(wmItemRecpt));
    }

    /**
     * 获取物料入库单详细信息
     */
    @ApiOperation("获取物料入库单详细信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:itemrecpt:query')")
    @GetMapping(value = "/{recptId}")
    public AjaxResult getInfo(@PathVariable("recptId") Long recptId)
    {
        return AjaxResult.success(wmItemRecptService.selectWmItemRecptByRecptId(recptId));
    }


    /**
     * 删除物料入库单
     */
    @ApiOperation("删除采购入库单基本信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:itemrecpt:remove')")
    @Log(title = "物料入库单", businessType = BusinessType.DELETE)
    @Transactional
    @DeleteMapping("/{recptIds}")
    public AjaxResult remove(@PathVariable Long[] recptIds)
    {
        for (Long id:
                recptIds
        ) {
            WmItemRecpt itemRecpt = wmItemRecptService.selectWmItemRecptByRecptId(id);
            if(!UserConstants.ORDER_STATUS_PREPARE.equals(itemRecpt.getStatus())){
                return AjaxResult.error("只能删除草稿状态的单据!");
            }

            wmItemRecptLineService.deleteByRecptId(id);
        }

        return toAjax(wmItemRecptService.deleteWmItemRecptByRecptIds(recptIds));
    }

    /**
     * 执行入库
     * @return
     */
    @ApiOperation("执行入库接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:itemrecpt:edit')")
    @Log(title = "物料入库单", businessType = BusinessType.UPDATE)
    @Transactional
    @PutMapping("/{recptId}")
    public AjaxResult execute(@PathVariable Long recptId){

        WmItemRecpt recpt = wmItemRecptService.selectWmItemRecptByRecptId(recptId);

        //单据有效性
        if(!StringUtils.isNotNull(recpt)){
            return AjaxResult.error("无效单据");
        }

        //先检查单据状态
        if(UserConstants.ORDER_STATUS_FINISHED.equals(recpt.getStatus())){
            return AjaxResult.error("当前单据已提交!");
        }

        //检查行数量
        WmItemRecptLine param =  new WmItemRecptLine();
        param.setRecptId(recptId);
        List<WmItemRecptLine> lines = wmItemRecptLineService.selectWmItemRecptLineList(param);
        if(CollectionUtil.isEmpty(lines)){
            return AjaxResult.error("请添加明细信息！");
        }

        //构造Transaction事务，并执行库存更新逻辑
        List<ItemRecptTxBean> beans = wmItemRecptService.getTxBeans(recptId);

        //调用库存核心
        storageCoreService.processItemRecpt(beans);

        //更新单据状态
        recpt.setStatus(UserConstants.ORDER_STATUS_FINISHED);
        wmItemRecptService.updateWmItemRecpt(recpt);

        return AjaxResult.success();
    }


}
