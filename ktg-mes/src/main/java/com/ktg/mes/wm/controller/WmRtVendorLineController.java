package com.ktg.mes.wm.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ktg.mes.wm.domain.WmRtVendorLine;
import com.ktg.mes.wm.service.IWmRtVendorLineService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 供应商退货行Controller
 * 
 * @author yinjinlu
 * @date 2022-06-13
 */
@RestController
@RequestMapping("/mes/wm/rtvendorline")
public class WmRtVendorLineController extends BaseController
{
    @Autowired
    private IWmRtVendorLineService wmRtVendorLineService;

    /**
     * 查询供应商退货行列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtvendorline:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmRtVendorLine wmRtVendorLine)
    {
        startPage();
        List<WmRtVendorLine> list = wmRtVendorLineService.selectWmRtVendorLineList(wmRtVendorLine);
        return getDataTable(list);
    }

    /**
     * 导出供应商退货行列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtvendorline:export')")
    @Log(title = "供应商退货行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmRtVendorLine wmRtVendorLine)
    {
        List<WmRtVendorLine> list = wmRtVendorLineService.selectWmRtVendorLineList(wmRtVendorLine);
        ExcelUtil<WmRtVendorLine> util = new ExcelUtil<WmRtVendorLine>(WmRtVendorLine.class);
        util.exportExcel(response, list, "供应商退货行数据");
    }

    /**
     * 获取供应商退货行详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtvendorline:query')")
    @GetMapping(value = "/{lineId}")
    public AjaxResult getInfo(@PathVariable("lineId") Long lineId)
    {
        return AjaxResult.success(wmRtVendorLineService.selectWmRtVendorLineByLineId(lineId));
    }

    /**
     * 新增供应商退货行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtvendorline:add')")
    @Log(title = "供应商退货行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmRtVendorLine wmRtVendorLine)
    {
        wmRtVendorLine.setCreateBy(getUsername());
        return toAjax(wmRtVendorLineService.insertWmRtVendorLine(wmRtVendorLine));
    }

    /**
     * 修改供应商退货行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtvendorline:edit')")
    @Log(title = "供应商退货行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmRtVendorLine wmRtVendorLine)
    {
        return toAjax(wmRtVendorLineService.updateWmRtVendorLine(wmRtVendorLine));
    }

    /**
     * 删除供应商退货行
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtvendorline:remove')")
    @Log(title = "供应商退货行", businessType = BusinessType.DELETE)
	@DeleteMapping("/{lineIds}")
    public AjaxResult remove(@PathVariable Long[] lineIds)
    {
        return toAjax(wmRtVendorLineService.deleteWmRtVendorLineByLineIds(lineIds));
    }
}
