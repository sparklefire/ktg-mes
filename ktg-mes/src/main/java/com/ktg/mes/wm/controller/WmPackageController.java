package com.ktg.mes.wm.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ktg.common.constant.UserConstants;
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
import com.ktg.mes.wm.domain.WmPackage;
import com.ktg.mes.wm.service.IWmPackageService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 装箱单Controller
 * 
 * @author yinjinlu
 * @date 2022-10-10
 */
@RestController
@RequestMapping("/mes/wm/package")
public class WmPackageController extends BaseController
{
    @Autowired
    private IWmPackageService wmPackageService;

    /**
     * 查询装箱单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:package:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmPackage wmPackage)
    {
        startPage();
        List<WmPackage> list = wmPackageService.selectWmPackageList(wmPackage);
        return getDataTable(list);
    }

    /**
     * 导出装箱单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:package:export')")
    @Log(title = "装箱单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmPackage wmPackage)
    {
        List<WmPackage> list = wmPackageService.selectWmPackageList(wmPackage);
        ExcelUtil<WmPackage> util = new ExcelUtil<WmPackage>(WmPackage.class);
        util.exportExcel(response, list, "装箱单数据");
    }

    /**
     * 获取装箱单详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:package:query')")
    @GetMapping(value = "/{packageId}")
    public AjaxResult getInfo(@PathVariable("packageId") Long packageId)
    {
        return AjaxResult.success(wmPackageService.selectWmPackageByPackageId(packageId));
    }

    /**
     * 新增装箱单
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:package:add')")
    @Log(title = "装箱单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmPackage wmPackage)
    {
        if(UserConstants.NOT_UNIQUE.equals(wmPackageService.checkPackgeCodeUnique(wmPackage))){
            return AjaxResult.error("装箱单编号已存在!");
        }
        return toAjax(wmPackageService.insertWmPackage(wmPackage));
    }

    /**
     * 修改装箱单
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:package:edit')")
    @Log(title = "装箱单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmPackage wmPackage)
    {
        if(UserConstants.NOT_UNIQUE.equals(wmPackageService.checkPackgeCodeUnique(wmPackage))){
            return AjaxResult.error("装箱单编号已存在!");
        }
        return toAjax(wmPackageService.updateWmPackage(wmPackage));
    }

    /**
     * 删除装箱单
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:package:remove')")
    @Log(title = "装箱单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{packageIds}")
    public AjaxResult remove(@PathVariable Long[] packageIds)
    {
        return toAjax(wmPackageService.deleteWmPackageByPackageIds(packageIds));
    }
}
