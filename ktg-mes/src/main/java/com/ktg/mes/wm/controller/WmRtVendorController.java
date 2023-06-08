package com.ktg.mes.wm.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ktg.common.constant.UserConstants;
import com.ktg.mes.wm.domain.WmRtVendorLine;
import com.ktg.mes.wm.domain.tx.RtVendorTxBean;
import com.ktg.mes.wm.service.IStorageCoreService;
import com.ktg.mes.wm.service.IWmRtVendorLineService;
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
import com.ktg.mes.wm.domain.WmRtVendor;
import com.ktg.mes.wm.service.IWmRtVendorService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 供应商退货Controller
 * 
 * @author yinjinlu
 * @date 2022-06-13
 */
@RestController
@RequestMapping("/mes/wm/rtvendor")
public class WmRtVendorController extends BaseController
{
    @Autowired
    private IWmRtVendorService wmRtVendorService;

    @Autowired
    private IWmRtVendorLineService wmRtVendorLineService;

    @Autowired
    private IStorageCoreService storageCoreService;
    /**
     * 查询供应商退货列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtvendor:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmRtVendor wmRtVendor)
    {
        startPage();
        List<WmRtVendor> list = wmRtVendorService.selectWmRtVendorList(wmRtVendor);
        return getDataTable(list);
    }

    /**
     * 导出供应商退货列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtvendor:export')")
    @Log(title = "供应商退货", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmRtVendor wmRtVendor)
    {
        List<WmRtVendor> list = wmRtVendorService.selectWmRtVendorList(wmRtVendor);
        ExcelUtil<WmRtVendor> util = new ExcelUtil<WmRtVendor>(WmRtVendor.class);
        util.exportExcel(response, list, "供应商退货数据");
    }

    /**
     * 获取供应商退货详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtvendor:query')")
    @GetMapping(value = "/{rtId}")
    public AjaxResult getInfo(@PathVariable("rtId") Long rtId)
    {
        return AjaxResult.success(wmRtVendorService.selectWmRtVendorByRtId(rtId));
    }

    /**
     * 新增供应商退货
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtvendor:add')")
    @Log(title = "供应商退货", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmRtVendor wmRtVendor)
    {
        if(UserConstants.NOT_UNIQUE.equals(wmRtVendorService.checkCodeUnique(wmRtVendor))){
            return AjaxResult.error("退货单编号已经存在！");
        }
        wmRtVendor.setCreateBy(getUsername());
        return toAjax(wmRtVendorService.insertWmRtVendor(wmRtVendor));
    }

    /**
     * 修改供应商退货
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtvendor:edit')")
    @Log(title = "供应商退货", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmRtVendor wmRtVendor)
    {
        if(UserConstants.NOT_UNIQUE.equals(wmRtVendorService.checkCodeUnique(wmRtVendor))){
            return AjaxResult.error("退货单编号已经存在！");
        }
        return toAjax(wmRtVendorService.updateWmRtVendor(wmRtVendor));
    }

    /**
     * 删除供应商退货
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtvendor:remove')")
    @Log(title = "供应商退货", businessType = BusinessType.DELETE)
	@Transactional
    @DeleteMapping("/{rtIds}")
    public AjaxResult remove(@PathVariable Long[] rtIds)
    {
        for (Long rtId:rtIds
             ) {
            WmRtVendor rtVendor = wmRtVendorService.selectWmRtVendorByRtId(rtId);
            if(!UserConstants.ORDER_STATUS_PREPARE.equals(rtVendor.getStatus())){
                return AjaxResult.error("只能删除草稿状态的单据!");
            }

            wmRtVendorLineService.deleteByRtId(rtId);
        }
        return toAjax(wmRtVendorService.deleteWmRtVendorByRtIds(rtIds));
    }

    /**
     * 执行退货
     */
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
