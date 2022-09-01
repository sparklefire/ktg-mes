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
import com.ktg.mes.qc.domain.QcOqcLine;
import com.ktg.mes.qc.service.IQcOqcLineService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 出货检验单行Controller
 * 
 * @author yinjinlu
 * @date 2022-09-01
 */
@RestController
@RequestMapping("/mes/qc/oqcline")
public class QcOqcLineController extends BaseController
{
    @Autowired
    private IQcOqcLineService qcOqcLineService;

    /**
     * 查询出货检验单行列表
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:oqcline:list')")
    @GetMapping("/list")
    public TableDataInfo list(QcOqcLine qcOqcLine)
    {
        startPage();
        List<QcOqcLine> list = qcOqcLineService.selectQcOqcLineList(qcOqcLine);
        return getDataTable(list);
    }

    /**
     * 导出出货检验单行列表
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:oqcline:export')")
    @Log(title = "出货检验单行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QcOqcLine qcOqcLine)
    {
        List<QcOqcLine> list = qcOqcLineService.selectQcOqcLineList(qcOqcLine);
        ExcelUtil<QcOqcLine> util = new ExcelUtil<QcOqcLine>(QcOqcLine.class);
        util.exportExcel(response, list, "出货检验单行数据");
    }

    /**
     * 获取出货检验单行详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:oqcline:query')")
    @GetMapping(value = "/{lineId}")
    public AjaxResult getInfo(@PathVariable("lineId") Long lineId)
    {
        return AjaxResult.success(qcOqcLineService.selectQcOqcLineByLineId(lineId));
    }

    /**
     * 新增出货检验单行
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:oqcline:add')")
    @Log(title = "出货检验单行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QcOqcLine qcOqcLine)
    {
        return toAjax(qcOqcLineService.insertQcOqcLine(qcOqcLine));
    }

    /**
     * 修改出货检验单行
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:oqcline:edit')")
    @Log(title = "出货检验单行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QcOqcLine qcOqcLine)
    {
        return toAjax(qcOqcLineService.updateQcOqcLine(qcOqcLine));
    }

    /**
     * 删除出货检验单行
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:oqcline:remove')")
    @Log(title = "出货检验单行", businessType = BusinessType.DELETE)
	@DeleteMapping("/{lineIds}")
    public AjaxResult remove(@PathVariable Long[] lineIds)
    {
        return toAjax(qcOqcLineService.deleteQcOqcLineByLineIds(lineIds));
    }
}
