package com.ktg.mes.wm.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.wm.domain.*;
import com.ktg.mes.wm.domain.tx.RtIssueTxBean;
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
 * 生产退料单头Controller
 * 
 * @author yinjinlu
 * @date 2022-09-15
 */
@RestController
@RequestMapping("/mes/wm/rtissue")
public class WmRtIssueController extends BaseController
{
    @Autowired
    private IWmRtIssueService wmRtIssueService;

    @Autowired
    private IWmRtIssueLineService wmRtIssueLineService;


    @Autowired
    private IWmWarehouseService wmWarehouseService;

    @Autowired
    private IWmStorageLocationService wmStorageLocationService;

    @Autowired
    private IWmStorageAreaService wmStorageAreaService;

    @Autowired
    private IStorageCoreService storageCoreService;

    /**
     * 查询生产退料单头列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtissue:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmRtIssue wmRtIssue)
    {
        startPage();
        List<WmRtIssue> list = wmRtIssueService.selectWmRtIssueList(wmRtIssue);
        return getDataTable(list);
    }

    /**
     * 导出生产退料单头列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtissue:export')")
    @Log(title = "生产退料单头", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmRtIssue wmRtIssue)
    {
        List<WmRtIssue> list = wmRtIssueService.selectWmRtIssueList(wmRtIssue);
        ExcelUtil<WmRtIssue> util = new ExcelUtil<WmRtIssue>(WmRtIssue.class);
        util.exportExcel(response, list, "生产退料单头数据");
    }

    /**
     * 获取生产退料单头详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtissue:query')")
    @GetMapping(value = "/{rtId}")
    public AjaxResult getInfo(@PathVariable("rtId") Long rtId)
    {
        return AjaxResult.success(wmRtIssueService.selectWmRtIssueByRtId(rtId));
    }

    /**
     * 新增生产退料单头
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtissue:add')")
    @Log(title = "生产退料单头", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmRtIssue wmRtIssue)
    {
        if(UserConstants.NOT_UNIQUE.equals(wmRtIssueService.checkUnique(wmRtIssue))){
            return AjaxResult.error("退料单编号已存在");
        }

        if(StringUtils.isNotNull(wmRtIssue.getWarehouseId())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseId(wmRtIssue.getWarehouseId());
            wmRtIssue.setWarehouseCode(warehouse.getWarehouseCode());
            wmRtIssue.setWarehouseName(warehouse.getWarehouseName());
        }
        if(StringUtils.isNotNull(wmRtIssue.getLocationId())){
            WmStorageLocation location = wmStorageLocationService.selectWmStorageLocationByLocationId(wmRtIssue.getLocationId());
            wmRtIssue.setLocationCode(location.getLocationCode());
            wmRtIssue.setLocationName(location.getLocationName());
        }
        if(StringUtils.isNotNull(wmRtIssue.getAreaId())){
            WmStorageArea area = wmStorageAreaService.selectWmStorageAreaByAreaId(wmRtIssue.getAreaId());
            wmRtIssue.setAreaCode(area.getAreaCode());
            wmRtIssue.setAreaName(area.getAreaName());
        }
        wmRtIssue.setCreateBy(getUsername());
        return toAjax(wmRtIssueService.insertWmRtIssue(wmRtIssue));
    }

    /**
     * 修改生产退料单头
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtissue:edit')")
    @Log(title = "生产退料单头", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmRtIssue wmRtIssue)
    {
        if(UserConstants.NOT_UNIQUE.equals(wmRtIssueService.checkUnique(wmRtIssue))){
            return AjaxResult.error("退料单编号已存在");
        }

        if(StringUtils.isNotNull(wmRtIssue.getWarehouseId())){
            WmWarehouse warehouse = wmWarehouseService.selectWmWarehouseByWarehouseId(wmRtIssue.getWarehouseId());
            wmRtIssue.setWarehouseCode(warehouse.getWarehouseCode());
            wmRtIssue.setWarehouseName(warehouse.getWarehouseName());
        }
        if(StringUtils.isNotNull(wmRtIssue.getLocationId())){
            WmStorageLocation location = wmStorageLocationService.selectWmStorageLocationByLocationId(wmRtIssue.getLocationId());
            wmRtIssue.setLocationCode(location.getLocationCode());
            wmRtIssue.setLocationName(location.getLocationName());
        }
        if(StringUtils.isNotNull(wmRtIssue.getAreaId())){
            WmStorageArea area = wmStorageAreaService.selectWmStorageAreaByAreaId(wmRtIssue.getAreaId());
            wmRtIssue.setAreaCode(area.getAreaCode());
            wmRtIssue.setAreaName(area.getAreaName());
        }
        return toAjax(wmRtIssueService.updateWmRtIssue(wmRtIssue));
    }

    /**
     * 删除生产退料单头
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtissue:remove')")
    @Log(title = "生产退料单头", businessType = BusinessType.DELETE)
    @Transactional
	@DeleteMapping("/{rtIds}")
    public AjaxResult remove(@PathVariable Long[] rtIds)
    {
        for (Long rtId: rtIds
        ) {
            wmRtIssueLineService.deleteByRtId(rtId);
        }
        return toAjax(wmRtIssueService.deleteWmRtIssueByRtIds(rtIds));
    }

    /**
     * 执行退料
     * @param rtId
     * @return
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtissue:edit')")
    @Log(title = "生产退料单头", businessType = BusinessType.UPDATE)
    @Transactional
    @PutMapping("/{rtId}")
    public AjaxResult execute(@PathVariable Long rtId){
        WmRtIssue rtIssue = wmRtIssueService.selectWmRtIssueByRtId(rtId);
        WmRtIssueLine param = new WmRtIssueLine();
        param.setRtId(rtId);
        List<WmRtIssueLine> lines = wmRtIssueLineService.selectWmRtIssueLineList(param);
        if(CollUtil.isEmpty(lines)){
            return AjaxResult.error("请选择要退料的物资");
        }

        List<RtIssueTxBean> beans = wmRtIssueService.getTxBeans(rtId);

        //执行生产退料
        storageCoreService.processRtIssue(beans);


        rtIssue.setStatus(UserConstants.ORDER_STATUS_FINISHED);
        wmRtIssueService.updateWmRtIssue(rtIssue);
        return AjaxResult.success();
    }

}
