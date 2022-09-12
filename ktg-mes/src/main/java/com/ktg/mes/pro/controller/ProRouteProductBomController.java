package com.ktg.mes.pro.controller;

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
import com.ktg.mes.pro.domain.ProRouteProductBom;
import com.ktg.mes.pro.service.IProRouteProductBomService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 产品制程物料BOMController
 * 
 * @author yinjinlu
 * @date 2022-09-12
 */
@RestController
@RequestMapping("/mes/pro/routeproductbom")
public class ProRouteProductBomController extends BaseController
{
    @Autowired
    private IProRouteProductBomService proRouteProductBomService;

    /**
     * 查询产品制程物料BOM列表
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:routeproductbom:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProRouteProductBom proRouteProductBom)
    {
        startPage();
        List<ProRouteProductBom> list = proRouteProductBomService.selectProRouteProductBomList(proRouteProductBom);
        return getDataTable(list);
    }

    /**
     * 导出产品制程物料BOM列表
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:routeproductbom:export')")
    @Log(title = "产品制程物料BOM", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProRouteProductBom proRouteProductBom)
    {
        List<ProRouteProductBom> list = proRouteProductBomService.selectProRouteProductBomList(proRouteProductBom);
        ExcelUtil<ProRouteProductBom> util = new ExcelUtil<ProRouteProductBom>(ProRouteProductBom.class);
        util.exportExcel(response, list, "产品制程物料BOM数据");
    }

    /**
     * 获取产品制程物料BOM详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:routeproductbom:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return AjaxResult.success(proRouteProductBomService.selectProRouteProductBomByRecordId(recordId));
    }

    /**
     * 新增产品制程物料BOM
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:routeproductbom:add')")
    @Log(title = "产品制程物料BOM", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProRouteProductBom proRouteProductBom)
    {
        if(UserConstants.NOT_UNIQUE.equals(proRouteProductBomService.checkUnique(proRouteProductBom))){
            return AjaxResult.error("当前BOM物料在此工序已经配置过！");
        }
        return toAjax(proRouteProductBomService.insertProRouteProductBom(proRouteProductBom));
    }

    /**
     * 修改产品制程物料BOM
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:routeproductbom:edit')")
    @Log(title = "产品制程物料BOM", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProRouteProductBom proRouteProductBom)
    {
        if(UserConstants.NOT_UNIQUE.equals(proRouteProductBomService.checkUnique(proRouteProductBom))){
            return AjaxResult.error("当前BOM物料在此工序已经配置过！");
        }
        return toAjax(proRouteProductBomService.updateProRouteProductBom(proRouteProductBom));
    }

    /**
     * 删除产品制程物料BOM
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:routeproductbom:remove')")
    @Log(title = "产品制程物料BOM", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(proRouteProductBomService.deleteProRouteProductBomByRecordIds(recordIds));
    }
}
