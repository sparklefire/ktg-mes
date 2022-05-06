package com.ktg.mes.md.controller;

import com.ktg.mes.md.service.IMdUnitMeasureService;
import com.ktg.common.annotation.Log;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.core.page.TableDataInfo;
import com.ktg.common.enums.BusinessType;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.mes.md.domain.MdUnitMeasure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 单位Controller
 * 
 * @author ruoyi
 * @date 2022-04-27
 */
@RestController
@RequestMapping("/mes/md/unitmeasure")
public class MdUnitMeasureController extends BaseController
{
    @Autowired
    private IMdUnitMeasureService mdUnitMeasureService;

    /**
     * 查询单位列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:unitmeasure:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdUnitMeasure mdUnitMeasure)
    {
        startPage();
        List<MdUnitMeasure> list = mdUnitMeasureService.selectMdUnitMeasureList(mdUnitMeasure);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('mes:md:unitmeasure:list')")
    @GetMapping("/listprimary")
    public AjaxResult listPrimary(){
        MdUnitMeasure mdUnitMeasure = new MdUnitMeasure();
        mdUnitMeasure.setPrimaryFlag("Y");
        List<MdUnitMeasure> list = mdUnitMeasureService.selectMdUnitMeasureList(mdUnitMeasure);
        return AjaxResult.success(list);
    }

    @GetMapping("/selectall")
    public AjaxResult selectAll(){
        MdUnitMeasure mdUnitMeasure = new MdUnitMeasure();
        mdUnitMeasure.setEnableFlag("Y");
        List<MdUnitMeasure> list = mdUnitMeasureService.selectMdUnitMeasureList(mdUnitMeasure);
        return AjaxResult.success(list);
    }

    /**
     * 导出单位列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:unitmeasure:export')")
    @Log(title = "单位", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdUnitMeasure mdUnitMeasure)
    {
        List<MdUnitMeasure> list = mdUnitMeasureService.selectMdUnitMeasureList(mdUnitMeasure);
        ExcelUtil<MdUnitMeasure> util = new ExcelUtil<MdUnitMeasure>(MdUnitMeasure.class);
        util.exportExcel(response, list, "单位数据");
    }

    /**
     * 获取单位详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:unitmeasure:query')")
    @GetMapping(value = "/{measureId}")
    public AjaxResult getInfo(@PathVariable("measureId") Long measureId)
    {
        return AjaxResult.success(mdUnitMeasureService.selectMdUnitMeasureByMeasureId(measureId));
    }

    /**
     * 新增单位
     */
    @PreAuthorize("@ss.hasPermi('mes:md:unitmeasure:add')")
    @Log(title = "单位", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdUnitMeasure mdUnitMeasure)
    {
        return toAjax(mdUnitMeasureService.insertMdUnitMeasure(mdUnitMeasure));
    }

    /**
     * 修改单位
     */
    @PreAuthorize("@ss.hasPermi('mes:md:unitmeasure:edit')")
    @Log(title = "单位", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdUnitMeasure mdUnitMeasure)
    {
        return toAjax(mdUnitMeasureService.updateMdUnitMeasure(mdUnitMeasure));
    }

    /**
     * 删除单位
     */
    @PreAuthorize("@ss.hasPermi('mes:md:unitmeasure:remove')")
    @Log(title = "单位", businessType = BusinessType.DELETE)
	@DeleteMapping("/{measureIds}")
    public AjaxResult remove(@PathVariable Long[] measureIds)
    {
        return toAjax(mdUnitMeasureService.deleteMdUnitMeasureByMeasureIds(measureIds));
    }
}
