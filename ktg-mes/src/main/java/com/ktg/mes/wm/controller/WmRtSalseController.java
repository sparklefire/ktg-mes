package com.ktg.mes.wm.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.wm.domain.*;
import com.ktg.mes.wm.domain.tx.RtSalseTxBean;
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
 * 产品销售退货单Controller
 * 
 * @author yinjinlu
 * @date 2022-10-06
 */
@RestController
@RequestMapping("/mes/wm/rtsalse")
public class WmRtSalseController extends BaseController
{
    @Autowired
    private IWmRtSalseService wmRtSalseService;

    @Autowired
    private IWmRtSalseLineService wmRtSalseLineService;

    @Autowired
    private IWmWarehouseService wmWarehouseService;

    @Autowired
    private IWmStorageLocationService wmStorageLocationService;

    @Autowired
    private IWmStorageAreaService wmStorageAreaService;

    @Autowired
    private IStorageCoreService storageCoreService;

    /**
     * 查询产品销售退货单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtsalse:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmRtSalse wmRtSalse)
    {
        startPage();
        List<WmRtSalse> list = wmRtSalseService.selectWmRtSalseList(wmRtSalse);
        return getDataTable(list);
    }

    /**
     * 导出产品销售退货单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtsalse:export')")
    @Log(title = "产品销售退货单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmRtSalse wmRtSalse)
    {
        List<WmRtSalse> list = wmRtSalseService.selectWmRtSalseList(wmRtSalse);
        ExcelUtil<WmRtSalse> util = new ExcelUtil<WmRtSalse>(WmRtSalse.class);
        util.exportExcel(response, list, "产品销售退货单数据");
    }

    /**
     * 获取产品销售退货单详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtsalse:query')")
    @GetMapping(value = "/{rtId}")
    public AjaxResult getInfo(@PathVariable("rtId") Long rtId)
    {
        return AjaxResult.success(wmRtSalseService.selectWmRtSalseByRtId(rtId));
    }

    /**
     * 新增产品销售退货单
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtsalse:add')")
    @Log(title = "产品销售退货单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmRtSalse wmRtSalse)
    {
        if(UserConstants.NOT_UNIQUE.equals(wmRtSalseService.checkUnique(wmRtSalse))){
            return AjaxResult.error("退货单号已存在!");
        }

        if(StringUtils.isNotNull(wmRtSalse.getWarehouseId())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseId(wmRtSalse.getWarehouseId());
            wmRtSalse.setWarehouseCode(warehouse.getWarehouseCode());
            wmRtSalse.setWarehouseName(warehouse.getWarehouseName());
        }
        if(StringUtils.isNotNull(wmRtSalse.getLocationId())){
            WmStorageLocation location = wmStorageLocationService.selectWmStorageLocationByLocationId(wmRtSalse.getLocationId());
            wmRtSalse.setLocationCode(location.getLocationCode());
            wmRtSalse.setLocationName(location.getLocationName());
        }
        if(StringUtils.isNotNull(wmRtSalse.getAreaId())){
            WmStorageArea area = wmStorageAreaService.selectWmStorageAreaByAreaId(wmRtSalse.getAreaId());
            wmRtSalse.setAreaCode(area.getAreaCode());
            wmRtSalse.setAreaName(area.getAreaName());
        }
        wmRtSalse.setCreateBy(getUsername());
        return toAjax(wmRtSalseService.insertWmRtSalse(wmRtSalse));
    }

    /**
     * 修改产品销售退货单
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtsalse:edit')")
    @Log(title = "产品销售退货单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmRtSalse wmRtSalse)
    {
        if(UserConstants.NOT_UNIQUE.equals(wmRtSalseService.checkUnique(wmRtSalse))){
            return AjaxResult.error("退货单号已存在!");
        }

        if(StringUtils.isNotNull(wmRtSalse.getWarehouseId())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseId(wmRtSalse.getWarehouseId());
            wmRtSalse.setWarehouseCode(warehouse.getWarehouseCode());
            wmRtSalse.setWarehouseName(warehouse.getWarehouseName());
        }
        if(StringUtils.isNotNull(wmRtSalse.getLocationId())){
            WmStorageLocation location = wmStorageLocationService.selectWmStorageLocationByLocationId(wmRtSalse.getLocationId());
            wmRtSalse.setLocationCode(location.getLocationCode());
            wmRtSalse.setLocationName(location.getLocationName());
        }
        if(StringUtils.isNotNull(wmRtSalse.getAreaId())){
            WmStorageArea area = wmStorageAreaService.selectWmStorageAreaByAreaId(wmRtSalse.getAreaId());
            wmRtSalse.setAreaCode(area.getAreaCode());
            wmRtSalse.setAreaName(area.getAreaName());
        }
        return toAjax(wmRtSalseService.updateWmRtSalse(wmRtSalse));
    }

    /**
     * 删除产品销售退货单
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtsalse:remove')")
    @Log(title = "产品销售退货单", businessType = BusinessType.DELETE)
    @Transactional
	@DeleteMapping("/{rtIds}")
    public AjaxResult remove(@PathVariable Long[] rtIds)
    {
        for (Long rtId: rtIds
             ) {
            wmRtSalseLineService.deleteByRtId(rtId);
        }

        return toAjax(wmRtSalseService.deleteWmRtSalseByRtIds(rtIds));
    }

    /**
     * 执行退货
     * @param rtId
     * @return
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtsalse:edit')")
    @Log(title = "产品销售退货单", businessType = BusinessType.UPDATE)
    @Transactional
    @PutMapping("/{rtId}")
    public AjaxResult execute(@PathVariable Long rtId){
        WmRtSalse rtSalse = wmRtSalseService.selectWmRtSalseByRtId(rtId);
        WmRtSalseLine param = new WmRtSalseLine();
        param.setRtId(rtId);
        List<WmRtSalseLine> lines = wmRtSalseLineService.selectWmRtSalseLineList(param);
        if(CollectionUtils.isEmpty(lines)){
            return AjaxResult.error("请添加退货单行信息！");
        }

        List<RtSalseTxBean> beans = wmRtSalseService.getTxBeans(rtId);

        storageCoreService.processRtSalse(beans);

        rtSalse.setStatus(UserConstants.ORDER_STATUS_FINISHED);
        wmRtSalseService.updateWmRtSalse(rtSalse);
        return AjaxResult.success();
    }

}
