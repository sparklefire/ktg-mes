package com.ktg.mes.wm.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.wm.domain.*;
import com.ktg.mes.wm.domain.tx.ProductRecptTxBean;
import com.ktg.mes.wm.service.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
 * 产品入库录Controller
 * 
 * @author yinjinlu
 * @date 2022-09-22
 */
@RestController
@RequestMapping("/mes/wm/productrecpt")
public class WmProductRecptController extends BaseController
{
    @Autowired
    private IWmProductRecptService wmProductRecptService;

    @Autowired
    private IWmProductRecptLineService wmProductRecptLineService;

    @Autowired
    private IWmWarehouseService wmWarehouseService;

    @Autowired
    private IWmStorageLocationService wmStorageLocationService;

    @Autowired
    private IWmStorageAreaService wmStorageAreaService;

    @Autowired
    private IStorageCoreService storageCoreService;

    /**
     * 查询产品入库录列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:productrecpt:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmProductRecpt wmProductRecpt)
    {
        startPage();
        List<WmProductRecpt> list = wmProductRecptService.selectWmProductRecptList(wmProductRecpt);
        return getDataTable(list);
    }

    /**
     * 导出产品入库录列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:productrecpt:export')")
    @Log(title = "产品入库记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmProductRecpt wmProductRecpt)
    {
        List<WmProductRecpt> list = wmProductRecptService.selectWmProductRecptList(wmProductRecpt);
        ExcelUtil<WmProductRecpt> util = new ExcelUtil<WmProductRecpt>(WmProductRecpt.class);
        util.exportExcel(response, list, "产品入库录数据");
    }

    /**
     * 获取产品入库录详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:productrecpt:query')")
    @GetMapping(value = "/{recptId}")
    public AjaxResult getInfo(@PathVariable("recptId") Long recptId)
    {
        return AjaxResult.success(wmProductRecptService.selectWmProductRecptByRecptId(recptId));
    }

    /**
     * 新增产品入库录
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:productrecpt:add')")
    @Log(title = "产品入库记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmProductRecpt wmProductRecpt)
    {
        if(UserConstants.NOT_UNIQUE.equals(wmProductRecptService.checkUnique(wmProductRecpt))){
            return AjaxResult.error("入库单编号已存在！");
        }

        if(StringUtils.isNotNull(wmProductRecpt.getWarehouseId())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseId(wmProductRecpt.getWarehouseId());
            wmProductRecpt.setWarehouseCode(warehouse.getWarehouseCode());
            wmProductRecpt.setWarehouseName(warehouse.getWarehouseName());
        }
        if(StringUtils.isNotNull(wmProductRecpt.getLocationId())){
            WmStorageLocation location = wmStorageLocationService.selectWmStorageLocationByLocationId(wmProductRecpt.getLocationId());
            wmProductRecpt.setLocationCode(location.getLocationCode());
            wmProductRecpt.setLocationName(location.getLocationName());
        }
        if(StringUtils.isNotNull(wmProductRecpt.getAreaId())){
            WmStorageArea area = wmStorageAreaService.selectWmStorageAreaByAreaId(wmProductRecpt.getAreaId());
            wmProductRecpt.setAreaCode(area.getAreaCode());
            wmProductRecpt.setAreaName(area.getAreaName());
        }
        wmProductRecpt.setCreateBy(getUsername());
        return toAjax(wmProductRecptService.insertWmProductRecpt(wmProductRecpt));
    }

    /**
     * 修改产品入库录
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:productrecpt:edit')")
    @Log(title = "产品入库记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmProductRecpt wmProductRecpt)
    {
        if(UserConstants.NOT_UNIQUE.equals(wmProductRecptService.checkUnique(wmProductRecpt))){
            return AjaxResult.error("入库单编号已存在！");
        }


        if(StringUtils.isNotNull(wmProductRecpt.getWarehouseId())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseId(wmProductRecpt.getWarehouseId());
            wmProductRecpt.setWarehouseCode(warehouse.getWarehouseCode());
            wmProductRecpt.setWarehouseName(warehouse.getWarehouseName());
        }
        if(StringUtils.isNotNull(wmProductRecpt.getLocationId())){
            WmStorageLocation location = wmStorageLocationService.selectWmStorageLocationByLocationId(wmProductRecpt.getLocationId());
            wmProductRecpt.setLocationCode(location.getLocationCode());
            wmProductRecpt.setLocationName(location.getLocationName());
        }
        if(StringUtils.isNotNull(wmProductRecpt.getAreaId())){
            WmStorageArea area = wmStorageAreaService.selectWmStorageAreaByAreaId(wmProductRecpt.getAreaId());
            wmProductRecpt.setAreaCode(area.getAreaCode());
            wmProductRecpt.setAreaName(area.getAreaName());
        }

        return toAjax(wmProductRecptService.updateWmProductRecpt(wmProductRecpt));
    }

    /**
     * 删除产品入库录
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:productrecpt:remove')")
    @Log(title = "产品入库记录", businessType = BusinessType.DELETE)
    @Transactional
	@DeleteMapping("/{recptIds}")
    public AjaxResult remove(@PathVariable Long[] recptIds)
    {
        for (Long recptId: recptIds
             ) {
            wmProductRecptLineService.deleteByRecptId(recptId);
        }
        return toAjax(wmProductRecptService.deleteWmProductRecptByRecptIds(recptIds));
    }

    /**
     * 执行入库
     * @return
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:productrecpt:edit')")
    @Log(title = "产品入库记录", businessType = BusinessType.UPDATE)
    @Transactional
    @PutMapping("/{recptId}")
    public AjaxResult execute(@PathVariable Long recptId){
        WmProductRecpt recpt = wmProductRecptService.selectWmProductRecptByRecptId(recptId);

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
