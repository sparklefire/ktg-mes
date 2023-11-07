package com.ktg.mes.wm.controller;

import com.ktg.common.annotation.Log;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.core.page.TableDataInfo;
import com.ktg.common.enums.BusinessType;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.wm.domain.WmStockTaking;
import com.ktg.mes.wm.domain.WmStockTakingLine;
import com.ktg.mes.wm.domain.WmWarehouse;
import com.ktg.mes.wm.service.IWmStockTakingLineService;
import com.ktg.mes.wm.service.IWmStockTakingResultService;
import com.ktg.mes.wm.service.IWmStockTakingService;
import com.ktg.mes.wm.service.IWmWarehouseService;
import com.ktg.system.strategy.AutoCodeUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mes/wm/stocktaking")
public class WmStockTakingController extends BaseController {

    @Autowired
    private IWmStockTakingService wmStockTakingService;

    @Autowired
    private IWmStockTakingLineService wmStockTakingLineService;

    @Autowired
    private IWmStockTakingResultService wmStockTakingResultService;

    @Autowired
    private AutoCodeUtil autoCodeUtil;

    @Autowired
    private IWmWarehouseService wmWarehouseService;

    /**
     * 查询库存盘点记录列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:stocktaking:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmStockTaking wmStockTaking)
    {
        startPage();
        List<WmStockTaking> list = wmStockTakingService.selectWmStockTakingList(wmStockTaking);
        return getDataTable(list);
    }

    /**
     * 获取库存盘点记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:stocktaking:query')")
    @GetMapping(value = "/{takingId}")
    public AjaxResult getInfo(@PathVariable("takingId") Long takingId)
    {
        return AjaxResult.success(wmStockTakingService.selectWmStockTakingByTakingId(takingId));
    }

    /**
     * 新增库存盘点记录
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:stocktaking:add')")
    @Log(title = "库存盘点记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmStockTaking wmStockTaking)
    {
        if(StringUtils.isNotNull(wmStockTaking.getTakingCode())){
            if(UserConstants.NOT_UNIQUE.equals(wmStockTakingService.checkUnique(wmStockTaking))){
                return AjaxResult.error("单据编号已存在!");
            }
        }else {
            wmStockTaking.setTakingCode(autoCodeUtil.genSerialCode(UserConstants.STOCKTAKING_CODE,""));
        }

        if(StringUtils.isNotNull(wmStockTaking.getWarehouseId())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseId(wmStockTaking.getWarehouseId());
            wmStockTaking.setWarehouseCode(warehouse.getWarehouseCode());
            wmStockTaking.setWarehouseName(warehouse.getWarehouseName());
        }

        wmStockTakingService.insertWmStockTaking(wmStockTaking);
        wmStockTaking.setCreateBy(getUsername());
        return AjaxResult.success(wmStockTaking);
    }

    /**
     * 修改库存盘点记录
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:stocktaking:edit')")
    @Log(title = "库存盘点记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmStockTaking wmStockTaking)
    {
        if(StringUtils.isNotNull(wmStockTaking.getWarehouseId())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseId(wmStockTaking.getWarehouseId());
            wmStockTaking.setWarehouseCode(warehouse.getWarehouseCode());
            wmStockTaking.setWarehouseName(warehouse.getWarehouseName());
        }
        return toAjax(wmStockTakingService.updateWmStockTaking(wmStockTaking));
    }

    /**
     * 删除库存盘点记录
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:stocktaking:remove')")
    @Log(title = "库存盘点记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{takingIds}")
    public AjaxResult remove(@PathVariable Long[] takingIds)
    {
        for(Long takingId:takingIds){
            WmStockTaking taking = wmStockTakingService.selectWmStockTakingByTakingId(takingId);
            if(!UserConstants.ORDER_STATUS_PREPARE.equals(taking.getStatus())){
                return AjaxResult.error("只能删除草稿状态的单据！");
            }
            wmStockTakingLineService.deleteByTakingId(takingId);
            wmStockTakingResultService.deleteWmStockTakingResultByTakingId(takingId);
        }

        return toAjax(wmStockTakingService.deleteWmStockTakingByTakingIds(takingIds));
    }

    /**
     * 完成盘点，系统对比计算盘点结果
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:stocktaking:edit')")
    @Log(title = "盘点单", businessType = BusinessType.UPDATE)
    @Transactional
    @PutMapping("/{takingId}")
    public AjaxResult execute(@PathVariable Long takingId){
        WmStockTaking taking = wmStockTakingService.selectWmStockTakingByTakingId(takingId);

        WmStockTakingLine param = new WmStockTakingLine();
        param.setTakingId(takingId);
        List<WmStockTakingLine> lines = wmStockTakingLineService.selectWmStockTakingLineList(param);
        if(CollectionUtils.isEmpty(lines)){
            return AjaxResult.error("未检测到盘点的物资！");
        }

        //先删除历史记录
        wmStockTakingResultService.deleteWmStockTakingResultByTakingId(takingId);

        if(UserConstants.WM_STOCK_TAKING_TYPE_OPEN.equals(taking.getTakingType())){
            //如果是明盘，则直接对比明细中的库存数量和盘点数量
            wmStockTakingResultService.calculateOpenWmStockTakingResult(takingId);
        }else {
            //如果是盲盘，则对比盘点明细中的盘点数量，和当前库存现有量的数量
            wmStockTakingResultService.calculateWmStockTakingResult(takingId);
        }

        taking.setStatus(UserConstants.ORDER_STATUS_APPROVED);
        wmStockTakingService.updateWmStockTaking(taking);

        return AjaxResult.success();
    }

}
