package com.ktg.mes.pro.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ktg.common.constant.UserConstants;
import com.ktg.mes.wm.utils.WmBarCodeUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ktg.mes.pro.domain.ProTransOrder;
import com.ktg.mes.pro.service.IProTransOrderService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 流转单Controller
 * 
 * @author yinjinlu
 * @date 2022-07-24
 */
@RestController
@RequestMapping("/mes/pro/transorder")
public class ProTransOrderController extends BaseController
{
    @Autowired
    private IProTransOrderService proTransOrderService;

    @Autowired
    private WmBarCodeUtil wmBarCodeUtil;

    /**
     * 查询流转单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:transorder:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProTransOrder proTransOrder)
    {
        startPage();
        List<ProTransOrder> list = proTransOrderService.selectProTransOrderList(proTransOrder);
        return getDataTable(list);
    }

    /**
     * 导出流转单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:transorder:export')")
    @Log(title = "流转单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProTransOrder proTransOrder)
    {
        List<ProTransOrder> list = proTransOrderService.selectProTransOrderList(proTransOrder);
        ExcelUtil<ProTransOrder> util = new ExcelUtil<ProTransOrder>(ProTransOrder.class);
        util.exportExcel(response, list, "流转单数据");
    }

    /**
     * 获取流转单详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:transorder:query')")
    @GetMapping(value = "/{transOrderId}")
    public AjaxResult getInfo(@PathVariable("transOrderId") Long transOrderId)
    {
        return AjaxResult.success(proTransOrderService.selectProTransOrderByTransOrderId(transOrderId));
    }

    /**
     * 新增流转单
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:transorder:add')")
    @Log(title = "流转单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProTransOrder proTransOrder)
    {
        proTransOrderService.insertProTransOrder(proTransOrder);
        wmBarCodeUtil.generateBarCode(UserConstants.BARCODE_TYPE_TRANSORDER,proTransOrder.getTransOrderId(),proTransOrder.getTransOrderCode(),null);
        proTransOrder.setCreateBy(getUsername());
        return AjaxResult.success(proTransOrder.getTransOrderId());
    }

    /**
     * 修改流转单
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:transorder:edit')")
    @Log(title = "流转单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProTransOrder proTransOrder)
    {
        return toAjax(proTransOrderService.updateProTransOrder(proTransOrder));
    }

    /**
     * 删除流转单
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:transorder:remove')")
    @Log(title = "流转单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{transOrderIds}")
    public AjaxResult remove(@PathVariable Long[] transOrderIds)
    {
        return toAjax(proTransOrderService.deleteProTransOrderByTransOrderIds(transOrderIds));
    }
}
