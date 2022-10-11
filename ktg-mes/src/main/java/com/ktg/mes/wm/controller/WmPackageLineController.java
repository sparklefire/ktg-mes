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
import com.ktg.mes.wm.domain.WmPackageLine;
import com.ktg.mes.wm.service.IWmPackageLineService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 装箱明细Controller
 * 
 * @author yinjinlu
 * @date 2022-10-11
 */
@RestController
@RequestMapping("/mes/wm/packageline")
public class WmPackageLineController extends BaseController
{
    @Autowired
    private IWmPackageLineService wmPackageLineService;

    /**
     * 查询装箱明细列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:packageline:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmPackageLine wmPackageLine)
    {
        startPage();
        List<WmPackageLine> list = wmPackageLineService.selectWmPackageLineList(wmPackageLine);
        return getDataTable(list);
    }

    /**
     * 导出装箱明细列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:packageline:export')")
    @Log(title = "装箱明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmPackageLine wmPackageLine)
    {
        List<WmPackageLine> list = wmPackageLineService.selectWmPackageLineList(wmPackageLine);
        ExcelUtil<WmPackageLine> util = new ExcelUtil<WmPackageLine>(WmPackageLine.class);
        util.exportExcel(response, list, "装箱明细数据");
    }

    /**
     * 获取装箱明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:packageline:query')")
    @GetMapping(value = "/{lineId}")
    public AjaxResult getInfo(@PathVariable("lineId") Long lineId)
    {
        return AjaxResult.success(wmPackageLineService.selectWmPackageLineByLineId(lineId));
    }

    /**
     * 新增装箱明细
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:packageline:add')")
    @Log(title = "装箱明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmPackageLine wmPackageLine)
    {
        return toAjax(wmPackageLineService.insertWmPackageLine(wmPackageLine));
    }

    /**
     * 修改装箱明细
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:packageline:edit')")
    @Log(title = "装箱明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmPackageLine wmPackageLine)
    {
        return toAjax(wmPackageLineService.updateWmPackageLine(wmPackageLine));
    }

    /**
     * 删除装箱明细
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:packageline:remove')")
    @Log(title = "装箱明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{lineIds}")
    public AjaxResult remove(@PathVariable Long[] lineIds)
    {
        return toAjax(wmPackageLineService.deleteWmPackageLineByLineIds(lineIds));
    }
}
