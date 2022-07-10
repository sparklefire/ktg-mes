package com.ktg.mes.pro.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ktg.mes.pro.domain.ProFeedback;
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
import com.ktg.mes.pro.service.IProFeedbackService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 生产报工记录Controller
 * 
 * @author yinjinlu
 * @date 2022-07-10
 */
@RestController
@RequestMapping("/mes/pro/feedback")
public class ProFeedbackController extends BaseController
{
    @Autowired
    private IProFeedbackService proFeedbackService;

    /**
     * 查询生产报工记录列表
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:feedback:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProFeedback proFeedback)
    {
        startPage();
        List<ProFeedback> list = proFeedbackService.selectProFeedbackList(proFeedback);
        return getDataTable(list);
    }

    /**
     * 导出生产报工记录列表
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:feedback:export')")
    @Log(title = "生产报工记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProFeedback proFeedback)
    {
        List<ProFeedback> list = proFeedbackService.selectProFeedbackList(proFeedback);
        ExcelUtil<ProFeedback> util = new ExcelUtil<ProFeedback>(ProFeedback.class);
        util.exportExcel(response, list, "生产报工记录数据");
    }

    /**
     * 获取生产报工记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:feedback:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return AjaxResult.success(proFeedbackService.selectProFeedbackByRecordId(recordId));
    }

    /**
     * 新增生产报工记录
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:feedback:add')")
    @Log(title = "生产报工记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProFeedback proFeedback)
    {
        return toAjax(proFeedbackService.insertProFeedback(proFeedback));
    }

    /**
     * 修改生产报工记录
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:feedback:edit')")
    @Log(title = "生产报工记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProFeedback proFeedback)
    {
        return toAjax(proFeedbackService.updateProFeedback(proFeedback));
    }

    /**
     * 删除生产报工记录
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:feedback:remove')")
    @Log(title = "生产报工记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(proFeedbackService.deleteProFeedbackByRecordIds(recordIds));
    }
}
