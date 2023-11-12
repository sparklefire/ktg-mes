package com.ktg.mes.wm.controller.mobile;

import com.ktg.common.annotation.Log;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.core.page.TableDataInfo;
import com.ktg.common.enums.BusinessType;
import com.ktg.common.utils.StringUtils;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.mes.wm.domain.WmTransfer;
import com.ktg.mes.wm.domain.WmTransferLine;
import com.ktg.mes.wm.domain.WmWarehouse;
import com.ktg.mes.wm.domain.tx.TransferTxBean;
import com.ktg.mes.wm.service.IStorageCoreService;
import com.ktg.mes.wm.service.IWmTransferLineService;
import com.ktg.mes.wm.service.IWmTransferService;
import com.ktg.mes.wm.service.IWmWarehouseService;
import com.ktg.system.strategy.AutoCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api("转移调拨")
@RestController
@RequestMapping("/mobile/wm/transfer")
public class WmTransferMobController extends BaseController {
    @Autowired
    private IWmTransferService wmTransferService;

    @Autowired
    private IWmTransferLineService wmTransferLineService;

    @Autowired
    private IWmWarehouseService wmWarehouseService;

    @Autowired
    private IStorageCoreService storageCoreService;

    @Autowired
    private AutoCodeUtil autoCodeUtil;

    /**
     * 查询转移单列表
     */
    @ApiOperation("查询转移调拨单清单列表")
    @PreAuthorize("@ss.hasPermi('mes:wm:transfer:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmTransfer wmTransfer)
    {
        startPage();
        List<WmTransfer> list = wmTransferService.selectWmTransferList(wmTransfer);
        return getDataTable(list);
    }

    /**
     * 获取转移单详细信息
     */
    @ApiOperation("获取转移调拨单详情接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:transfer:query')")
    @GetMapping(value = "/{transferId}")
    public AjaxResult getInfo(@PathVariable("transferId") Long transferId)
    {
        return AjaxResult.success(wmTransferService.selectWmTransferByTransferId(transferId));
    }

    /**
     * 新增转移单
     */
    @ApiOperation("新增转移调拨单基本信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:transfer:add')")
    @Log(title = "转移单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmTransfer wmTransfer)
    {
        if(StringUtils.isNotNull(wmTransfer.getTransferCode())){
            if(UserConstants.NOT_UNIQUE.equals(wmTransferService.checkUnique(wmTransfer))){
                return AjaxResult.error("单据编号已存在");
            }
        }else {
            wmTransfer.setTransferCode(autoCodeUtil.genSerialCode(UserConstants.TRANSFER_CODE,""));
        }

        if(StringUtils.isNotNull(wmTransfer.getFromWarehouseId())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseId(wmTransfer.getFromWarehouseId());
            wmTransfer.setFromWarehouseCode(warehouse.getWarehouseCode());
            wmTransfer.setFromWarehouseName(warehouse.getWarehouseName());
        }
        if(StringUtils.isNotNull(wmTransfer.getToWarehouseId())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseId(wmTransfer.getToWarehouseId());
            wmTransfer.setToWarehouseCode(warehouse.getWarehouseCode());
            wmTransfer.setToWarehouseName(warehouse.getWarehouseName());
        }

        wmTransfer.setCreateBy(getUsername());
        wmTransferService.insertWmTransfer(wmTransfer);
        return AjaxResult.success(wmTransfer);
    }

    /**
     * 修改转移单
     */
    @ApiOperation("编辑转移调拨单基本信息接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:transfer:edit')")
    @Log(title = "转移单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmTransfer wmTransfer)
    {
        if(UserConstants.NOT_UNIQUE.equals(wmTransferService.checkUnique(wmTransfer))){
            return AjaxResult.error("转移单编号已存在");
        }
        if(StringUtils.isNotNull(wmTransfer.getFromWarehouseId())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseId(wmTransfer.getFromWarehouseId());
            wmTransfer.setFromWarehouseCode(warehouse.getWarehouseCode());
            wmTransfer.setFromWarehouseName(warehouse.getWarehouseName());
        }
        if(StringUtils.isNotNull(wmTransfer.getToWarehouseId())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseId(wmTransfer.getToWarehouseId());
            wmTransfer.setToWarehouseCode(warehouse.getWarehouseCode());
            wmTransfer.setToWarehouseName(warehouse.getWarehouseName());
        }
        return toAjax(wmTransferService.updateWmTransfer(wmTransfer));
    }

    /**
     * 删除转移单
     */
    @ApiOperation("删除转移调拨单接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:transfer:remove')")
    @Log(title = "转移单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{transferIds}")
    @Transactional
    public AjaxResult remove(@PathVariable Long[] transferIds)
    {
        for (Long transferId:transferIds
        ) {
            WmTransfer transfer = wmTransferService.selectWmTransferByTransferId(transferId);
            if(!UserConstants.ORDER_STATUS_PREPARE.equals(transfer.getStatus())){
                return AjaxResult.error("只能删除草稿状态单据！");
            }

            wmTransferLineService.deleteByTransferId(transferId);
        }
        return toAjax(wmTransferService.deleteWmTransferByTransferIds(transferIds));
    }

    /**
     * 执行转移
     */
    @ApiOperation("执行转移调拨接口")
    @PreAuthorize("@ss.hasPermi('mes:wm:transfer:edit')")
    @Log(title = "转移单", businessType = BusinessType.UPDATE)
    @Transactional
    @PutMapping("/{transferId}")
    public AjaxResult execute(@PathVariable Long transferId){
        WmTransfer transfer = wmTransferService.selectWmTransferByTransferId(transferId);

        WmTransferLine param = new WmTransferLine();
        param.setTransferId(transferId);
        List<WmTransferLine> lines = wmTransferLineService.selectWmTransferLineList(param);
        if(CollectionUtils.isEmpty(lines)){
            return AjaxResult.error("请添加需要转移的物资！");
        }

        List<TransferTxBean> beans = wmTransferService.getTxBeans(transferId);

        if(CollectionUtils.isEmpty(beans)){
            return AjaxResult.error("请添加转移单行信息！");
        }

        storageCoreService.processTransfer(beans);


        transfer.setStatus(UserConstants.ORDER_STATUS_FINISHED);
        wmTransferService.updateWmTransfer(transfer);
        return AjaxResult.success();
    }
}
