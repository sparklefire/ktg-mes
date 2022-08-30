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
import com.ktg.mes.qc.domain.QcDefectRecord;
import com.ktg.mes.qc.service.IQcDefectRecordService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 检验单缺陷记录Controller
 * 
 * @author yinjinlu
 * @date 2022-08-30
 */
@RestController
@RequestMapping("/mes/qc/defectrecord")
public class QcDefectRecordController extends BaseController
{
    @Autowired
    private IQcDefectRecordService qcDefectRecordService;

    /**
     * 查询检验单缺陷记录列表
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:defectrecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(QcDefectRecord qcDefectRecord)
    {
        startPage();
        List<QcDefectRecord> list = qcDefectRecordService.selectQcDefectRecordList(qcDefectRecord);
        return getDataTable(list);
    }

    /**
     * 导出检验单缺陷记录列表
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:defectrecord:export')")
    @Log(title = "检验单缺陷记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QcDefectRecord qcDefectRecord)
    {
        List<QcDefectRecord> list = qcDefectRecordService.selectQcDefectRecordList(qcDefectRecord);
        ExcelUtil<QcDefectRecord> util = new ExcelUtil<QcDefectRecord>(QcDefectRecord.class);
        util.exportExcel(response, list, "检验单缺陷记录数据");
    }

    /**
     * 获取检验单缺陷记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:defectrecord:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return AjaxResult.success(qcDefectRecordService.selectQcDefectRecordByRecordId(recordId));
    }

    /**
     * 新增检验单缺陷记录
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:defectrecord:add')")
    @Log(title = "检验单缺陷记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QcDefectRecord qcDefectRecord)
    {
        return toAjax(qcDefectRecordService.insertQcDefectRecord(qcDefectRecord));
    }

    /**
     * 修改检验单缺陷记录
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:defectrecord:edit')")
    @Log(title = "检验单缺陷记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QcDefectRecord qcDefectRecord)
    {
        return toAjax(qcDefectRecordService.updateQcDefectRecord(qcDefectRecord));
    }

    /**
     * 删除检验单缺陷记录
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:defectrecord:remove')")
    @Log(title = "检验单缺陷记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(qcDefectRecordService.deleteQcDefectRecordByRecordIds(recordIds));
    }
}
