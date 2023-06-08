package com.ktg.mes.wm.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.wm.domain.*;
import com.ktg.mes.wm.domain.tx.TransferTxBean;
import com.ktg.mes.wm.service.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ktg.common.annotation.Log;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.enums.BusinessType;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 转移单Controller
 * 
 * @author yinjinlu
 * @date 2022-11-28
 */
@RestController
@RequestMapping("/mes/wm/transfer")
public class WmTransferController extends BaseController
{
    @Autowired
    private IWmTransferService wmTransferService;

    @Autowired
    private IWmTransferLineService wmTransferLineService;

    @Autowired
    private IWmWarehouseService wmWarehouseService;

    @Autowired
    private IStorageCoreService storageCoreService;

    /**
     * 查询转移单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:transfer:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmTransfer wmTransfer)
    {
        startPage();
        List<WmTransfer> list = wmTransferService.selectWmTransferList(wmTransfer);
        return getDataTable(list);
    }

    /**
     * 导出转移单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:transfer:export')")
    @Log(title = "转移单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmTransfer wmTransfer)
    {
        List<WmTransfer> list = wmTransferService.selectWmTransferList(wmTransfer);
        ExcelUtil<WmTransfer> util = new ExcelUtil<WmTransfer>(WmTransfer.class);
        util.exportExcel(response, list, "转移单数据");
    }

    /**
     * 获取转移单详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:transfer:query')")
    @GetMapping(value = "/{transferId}")
    public AjaxResult getInfo(@PathVariable("transferId") Long transferId)
    {
        return AjaxResult.success(wmTransferService.selectWmTransferByTransferId(transferId));
    }

    /**
     * 新增转移单
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:transfer:add')")
    @Log(title = "转移单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmTransfer wmTransfer)
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

        wmTransfer.setCreateBy(getUsername());
        return toAjax(wmTransferService.insertWmTransfer(wmTransfer));
    }

    /**
     * 修改转移单
     */
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
    @PreAuthorize("@ss.hasPermi('mes:wm:transfer:remove')")
    @Log(title = "转移单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{transferIds}")
    @Transactional
    public AjaxResult remove(@PathVariable Long[] transferIds)
    {
        for (Long transferId:transferIds
             ) {
            wmTransferLineService.deleteByTransferId(transferId);
        }
        return toAjax(wmTransferService.deleteWmTransferByTransferIds(transferIds));
    }

    /**
     * 执行退货
     */
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
