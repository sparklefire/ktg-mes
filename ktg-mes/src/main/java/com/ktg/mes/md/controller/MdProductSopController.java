package com.ktg.mes.md.controller;

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
import com.ktg.mes.md.domain.MdProductSop;
import com.ktg.mes.md.service.IMdProductSopService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 产品SOPController
 * 
 * @author yinjinlu
 * @date 2022-07-26
 */
@RestController
@RequestMapping("/mes/md/sop")
public class MdProductSopController extends BaseController
{
    @Autowired
    private IMdProductSopService mdProductSopService;

    /**
     * 查询产品SOP列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:sop:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdProductSop mdProdutSop)
    {
        startPage();
        List<MdProductSop> list = mdProductSopService.selectMdProductSopList(mdProdutSop);
        return getDataTable(list);
    }

    /**
     * 导出产品SOP列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:sop:export')")
    @Log(title = "产品SOP", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdProductSop mdProdutSop)
    {
        List<MdProductSop> list = mdProductSopService.selectMdProductSopList(mdProdutSop);
        ExcelUtil<MdProductSop> util = new ExcelUtil<MdProductSop>(MdProductSop.class);
        util.exportExcel(response, list, "产品SOP数据");
    }

    /**
     * 获取产品SOP详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:sop:query')")
    @GetMapping(value = "/{sopId}")
    public AjaxResult getInfo(@PathVariable("sopId") Long sopId)
    {
        return AjaxResult.success(mdProductSopService.selectMdProductSopBySopId(sopId));
    }

    /**
     * 新增产品SOP
     */
    @PreAuthorize("@ss.hasPermi('mes:md:sop:add')")
    @Log(title = "产品SOP", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdProductSop mdProdutSop)
    {
        return toAjax(mdProductSopService.insertMdProductSop(mdProdutSop));
    }

    /**
     * 修改产品SOP
     */
    @PreAuthorize("@ss.hasPermi('mes:md:sop:edit')")
    @Log(title = "产品SOP", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdProductSop mdProdutSop)
    {
        return toAjax(mdProductSopService.updateMdProductSop(mdProdutSop));
    }

    /**
     * 删除产品SOP
     */
    @PreAuthorize("@ss.hasPermi('mes:md:sop:remove')")
    @Log(title = "产品SOP", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sopIds}")
    public AjaxResult remove(@PathVariable Long[] sopIds)
    {
        return toAjax(mdProductSopService.deleteMdProductSopBySopIds(sopIds));
    }
}
