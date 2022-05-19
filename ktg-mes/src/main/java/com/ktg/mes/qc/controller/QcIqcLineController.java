package com.ktg.mes.qc.controller;

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
import com.ktg.mes.qc.domain.QcIqcLine;
import com.ktg.mes.qc.service.IQcIqcLineService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 来料检验单行Controller
 * 
 * @author yinjinlu
 * @date 2022-05-19
 */
@RestController
@RequestMapping("/mes/qc/iqcline")
public class QcIqcLineController extends BaseController
{
    @Autowired
    private IQcIqcLineService qcIqcLineService;

    /**
     * 查询来料检验单行列表
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:iqcline:list')")
    @GetMapping("/list")
    public TableDataInfo list(QcIqcLine qcIqcLine)
    {
        startPage();
        List<QcIqcLine> list = qcIqcLineService.selectQcIqcLineList(qcIqcLine);
        return getDataTable(list);
    }

    /**
     * 导出来料检验单行列表
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:iqcline:export')")
    @Log(title = "来料检验单行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QcIqcLine qcIqcLine)
    {
        List<QcIqcLine> list = qcIqcLineService.selectQcIqcLineList(qcIqcLine);
        ExcelUtil<QcIqcLine> util = new ExcelUtil<QcIqcLine>(QcIqcLine.class);
        util.exportExcel(response, list, "来料检验单行数据");
    }

    /**
     * 获取来料检验单行详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:iqcline:query')")
    @GetMapping(value = "/{lineId}")
    public AjaxResult getInfo(@PathVariable("lineId") Long lineId)
    {
        return AjaxResult.success(qcIqcLineService.selectQcIqcLineByLineId(lineId));
    }

    /**
     * 新增来料检验单行
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:iqcline:add')")
    @Log(title = "来料检验单行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QcIqcLine qcIqcLine)
    {
        return toAjax(qcIqcLineService.insertQcIqcLine(qcIqcLine));
    }

    /**
     * 修改来料检验单行
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:iqcline:edit')")
    @Log(title = "来料检验单行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QcIqcLine qcIqcLine)
    {
        return toAjax(qcIqcLineService.updateQcIqcLine(qcIqcLine));
    }

    /**
     * 删除来料检验单行
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:iqcline:remove')")
    @Log(title = "来料检验单行", businessType = BusinessType.DELETE)
	@DeleteMapping("/{lineIds}")
    public AjaxResult remove(@PathVariable Long[] lineIds)
    {
        return toAjax(qcIqcLineService.deleteQcIqcLineByLineIds(lineIds));
    }
}
