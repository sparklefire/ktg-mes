package com.ktg.mes.qc.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ktg.mes.qc.domain.QcIndex;
import com.ktg.mes.qc.service.IQcIndexService;
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
import com.ktg.mes.qc.domain.QcTemplateIndex;
import com.ktg.mes.qc.service.IQcTemplateIndexService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 检测模板-检测项Controller
 * 
 * @author yinjinlu
 * @date 2022-05-18
 */
@RestController
@RequestMapping("/mes/qc/templateindex")
public class QcTemplateIndexController extends BaseController
{
    @Autowired
    private IQcTemplateIndexService qcTemplateIndexService;

    @Autowired
    private IQcIndexService qcIndexService;

    /**
     * 查询检测模板-检测项列表
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:templateindex:list')")
    @GetMapping("/list")
    public TableDataInfo list(QcTemplateIndex qcTemplateIndex)
    {
        startPage();
        List<QcTemplateIndex> list = qcTemplateIndexService.selectQcTemplateIndexList(qcTemplateIndex);
        return getDataTable(list);
    }

    /**
     * 导出检测模板-检测项列表
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:templateindex:export')")
    @Log(title = "检测模板-检测项", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QcTemplateIndex qcTemplateIndex)
    {
        List<QcTemplateIndex> list = qcTemplateIndexService.selectQcTemplateIndexList(qcTemplateIndex);
        ExcelUtil<QcTemplateIndex> util = new ExcelUtil<QcTemplateIndex>(QcTemplateIndex.class);
        util.exportExcel(response, list, "检测模板-检测项数据");
    }

    /**
     * 获取检测模板-检测项详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:templateindex:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return AjaxResult.success(qcTemplateIndexService.selectQcTemplateIndexByRecordId(recordId));
    }

    /**
     * 新增检测模板-检测项
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:templateindex:add')")
    @Log(title = "检测模板-检测项", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QcTemplateIndex qcTemplateIndex)
    {
        QcIndex index =qcIndexService.selectQcIndexByIndexId(qcTemplateIndex.getIndexId());
        qcTemplateIndex.setIndexCode(index.getIndexCode());
        qcTemplateIndex.setIndexName(index.getIndexName());
        qcTemplateIndex.setIndexType(index.getIndexType());
        qcTemplateIndex.setQcTool(index.getQcTool());
        return toAjax(qcTemplateIndexService.insertQcTemplateIndex(qcTemplateIndex));
    }

    /**
     * 修改检测模板-检测项
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:templateindex:edit')")
    @Log(title = "检测模板-检测项", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QcTemplateIndex qcTemplateIndex)
    {
        QcIndex index =qcIndexService.selectQcIndexByIndexId(qcTemplateIndex.getIndexId());
        qcTemplateIndex.setIndexCode(index.getIndexCode());
        qcTemplateIndex.setIndexName(index.getIndexName());
        qcTemplateIndex.setIndexType(index.getIndexType());
        qcTemplateIndex.setQcTool(index.getQcTool());
        return toAjax(qcTemplateIndexService.updateQcTemplateIndex(qcTemplateIndex));
    }

    /**
     * 删除检测模板-检测项
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:templateindex:remove')")
    @Log(title = "检测模板-检测项", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(qcTemplateIndexService.deleteQcTemplateIndexByRecordIds(recordIds));
    }
}
