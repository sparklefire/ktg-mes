package com.ktg.mes.wm.controller.mobile;

import com.ktg.common.annotation.Log;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.core.page.TableDataInfo;
import com.ktg.common.enums.BusinessType;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.md.domain.MdVendor;
import com.ktg.mes.md.service.IMdVendorService;
import com.ktg.mes.wm.domain.WmRtVendor;
import com.ktg.mes.wm.domain.tx.RtVendorTxBean;
import com.ktg.mes.wm.service.IStorageCoreService;
import com.ktg.mes.wm.service.IWmRtVendorService;
import com.ktg.system.strategy.AutoCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("退回供应商")
@RestController
@RequestMapping("/mobile/wm/rtvendor")
public class WmRtVendorMobController extends BaseController {

    @Autowired
    private IWmRtVendorService wmRtVendorService;

    @Autowired
    private IStorageCoreService storageCoreService;

    @Autowired
    private IMdVendorService mdVendorService;

    @Autowired
    private AutoCodeUtil autoCodeUtil;

    /**
     * 查询供应商退货列表
     */
    @ApiOperation("查询采购退货单据清单")
    @PreAuthorize("@ss.hasPermi('mes:wm:rtvendor:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmRtVendor wmRtVendor)
    {
        startPage();
        List<WmRtVendor> list = wmRtVendorService.selectWmRtVendorList(wmRtVendor);
        return getDataTable(list);
    }

    /**
     * 获取供应商退货详细信息
     */
    @ApiOperation("查询采购退货单据明细")
    @PreAuthorize("@ss.hasPermi('mes:wm:rtvendor:query')")
    @GetMapping(value = "/{rtId}")
    public AjaxResult getInfo(@PathVariable("rtId") Long rtId)
    {
        return AjaxResult.success(wmRtVendorService.selectWmRtVendorByRtId(rtId));
    }

    /**
     * 新增供应商退货
     */
    @ApiOperation("新增采购退货单")
    @PreAuthorize("@ss.hasPermi('mes:wm:rtvendor:add')")
    @Log(title = "供应商退货", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmRtVendor wmRtVendor)
    {
        if(StringUtils.isNotNull(wmRtVendor.getRtCode())){
            if(UserConstants.NOT_UNIQUE.equals(wmRtVendorService.checkCodeUnique(wmRtVendor))){
                return AjaxResult.error("退货单编号已经存在！");
            }
        }else {
            wmRtVendor.setRtCode(autoCodeUtil.genSerialCode(UserConstants.WM_RTVENDOR_CODE,""));
        }

        if(StringUtils.isNotNull(wmRtVendor.getVendorId())){
            MdVendor vendor = mdVendorService.selectMdVendorByVendorId(wmRtVendor.getVendorId());
            wmRtVendor.setVendorCode(vendor.getVendorCode());
            wmRtVendor.setVendorName(vendor.getVendorName());
            wmRtVendor.setVendorNick(vendor.getVendorNick());
        }

        wmRtVendor.setCreateBy(getUsername());
        return toAjax(wmRtVendorService.insertWmRtVendor(wmRtVendor));
    }

    /**
     * 修改供应商退货
     */
    @ApiOperation("编辑采购退货单")
    @PreAuthorize("@ss.hasPermi('mes:wm:rtvendor:edit')")
    @Log(title = "供应商退货", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmRtVendor wmRtVendor)
    {
        if(UserConstants.NOT_UNIQUE.equals(wmRtVendorService.checkCodeUnique(wmRtVendor))){
            return AjaxResult.error("退货单编号已经存在！");
        }

        if(StringUtils.isNotNull(wmRtVendor.getVendorId())){
            MdVendor vendor = mdVendorService.selectMdVendorByVendorId(wmRtVendor.getVendorId());
            wmRtVendor.setVendorCode(vendor.getVendorCode());
            wmRtVendor.setVendorName(vendor.getVendorName());
            wmRtVendor.setVendorNick(vendor.getVendorNick());
        }

        return toAjax(wmRtVendorService.updateWmRtVendor(wmRtVendor));
    }


    /**
     * 执行退货
     */
    @ApiOperation("执行退货")
    @PreAuthorize("@ss.hasPermi('mes:wm:rtvendor:edit')")
    @Log(title = "供应商退货单", businessType = BusinessType.UPDATE)
    @Transactional
    @PutMapping("/{rtId}")
    public AjaxResult execute(@PathVariable Long rtId){
        //判断单据状态
        WmRtVendor wmRtVendor = wmRtVendorService.selectWmRtVendorByRtId(rtId);

        //构造事务Bean
        List<RtVendorTxBean> beans = wmRtVendorService.getTxBeans(rtId);

        //调用库存核心
        storageCoreService.processRtVendor(beans);

        //更新单据状态
        WmRtVendor rtVendor = wmRtVendorService.selectWmRtVendorByRtId(rtId);
        rtVendor.setStatus(UserConstants.ORDER_STATUS_FINISHED);
        wmRtVendorService.updateWmRtVendor(rtVendor);

        return AjaxResult.success();
    }
}
