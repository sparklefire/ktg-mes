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
import com.ktg.mes.wm.domain.WmOutsourceRecpt;
import com.ktg.mes.wm.service.IWmOutsourceRecptService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 外协入库单Controller
 * 
 * @author yinjinlu
 * @date 2023-10-30
 */
@RestController
@RequestMapping("/mes/wm/outsourcerecpt")
public class WmOutsourceRecptController extends BaseController
{
    @Autowired
    private IWmOutsourceRecptService wmOutsourceRecptService;

    /**
     * 查询外协入库单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:outsourcerecpt:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmOutsourceRecpt wmOutsourceRecpt)
    {
        startPage();
        List<WmOutsourceRecpt> list = wmOutsourceRecptService.selectWmOutsourceRecptList(wmOutsourceRecpt);
        return getDataTable(list);
    }

    /**
     * 导出外协入库单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:outsourcerecpt:export')")
    @Log(title = "外协入库单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmOutsourceRecpt wmOutsourceRecpt)
    {
        List<WmOutsourceRecpt> list = wmOutsourceRecptService.selectWmOutsourceRecptList(wmOutsourceRecpt);
        ExcelUtil<WmOutsourceRecpt> util = new ExcelUtil<WmOutsourceRecpt>(WmOutsourceRecpt.class);
        util.exportExcel(response, list, "外协入库单数据");
    }

    /**
     * 获取外协入库单详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:outsourcerecpt:query')")
    @GetMapping(value = "/{recptId}")
    public AjaxResult getInfo(@PathVariable("recptId") Long recptId)
    {
        return AjaxResult.success(wmOutsourceRecptService.selectWmOutsourceRecptByRecptId(recptId));
    }

    /**
     * 新增外协入库单
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:outsourcerecpt:add')")
    @Log(title = "外协入库单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmOutsourceRecpt wmOutsourceRecpt)
    {
        return toAjax(wmOutsourceRecptService.insertWmOutsourceRecpt(wmOutsourceRecpt));
    }

    /**
     * 修改外协入库单
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:outsourcerecpt:edit')")
    @Log(title = "外协入库单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmOutsourceRecpt wmOutsourceRecpt)
    {
        return toAjax(wmOutsourceRecptService.updateWmOutsourceRecpt(wmOutsourceRecpt));
    }

    /**
     * 删除外协入库单
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:outsourcerecpt:remove')")
    @Log(title = "外协入库单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recptIds}")
    public AjaxResult remove(@PathVariable Long[] recptIds)
    {
        return toAjax(wmOutsourceRecptService.deleteWmOutsourceRecptByRecptIds(recptIds));
    }
}
