package com.ktg.mes.pro.controller;

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
import com.ktg.mes.pro.domain.ProProcessContent;
import com.ktg.mes.pro.service.IProProcessContentService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 生产工序内容Controller
 * 
 * @author yinjinlu
 * @date 2022-05-12
 */
@RestController
@RequestMapping("/mes/pro/processcontent")
public class ProProcessContentController extends BaseController
{
    @Autowired
    private IProProcessContentService proProcessContentService;

    /**
     * 查询生产工序内容列表
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:processcontent:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProProcessContent proProcessContent)
    {
        startPage();
        List<ProProcessContent> list = proProcessContentService.selectProProcessContentList(proProcessContent);
        return getDataTable(list);
    }

    /**
     * 导出生产工序内容列表
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:processcontent:export')")
    @Log(title = "生产工序内容", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProProcessContent proProcessContent)
    {
        List<ProProcessContent> list = proProcessContentService.selectProProcessContentList(proProcessContent);
        ExcelUtil<ProProcessContent> util = new ExcelUtil<ProProcessContent>(ProProcessContent.class);
        util.exportExcel(response, list, "生产工序内容数据");
    }

    /**
     * 获取生产工序内容详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:processcontent:query')")
    @GetMapping(value = "/{contentId}")
    public AjaxResult getInfo(@PathVariable("contentId") Long contentId)
    {
        return AjaxResult.success(proProcessContentService.selectProProcessContentByContentId(contentId));
    }

    /**
     * 新增生产工序内容
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:processcontent:add')")
    @Log(title = "生产工序内容", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProProcessContent proProcessContent)
    {
        return toAjax(proProcessContentService.insertProProcessContent(proProcessContent));
    }

    /**
     * 修改生产工序内容
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:processcontent:edit')")
    @Log(title = "生产工序内容", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProProcessContent proProcessContent)
    {
        return toAjax(proProcessContentService.updateProProcessContent(proProcessContent));
    }

    /**
     * 删除生产工序内容
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:processcontent:remove')")
    @Log(title = "生产工序内容", businessType = BusinessType.DELETE)
	@DeleteMapping("/{contentIds}")
    public AjaxResult remove(@PathVariable Long[] contentIds)
    {
        return toAjax(proProcessContentService.deleteProProcessContentByContentIds(contentIds));
    }
}
