package com.ktg.mes.qc.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.qc.domain.ValidList;
import com.ktg.mes.qc.service.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
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

    @Autowired
    private IQcIqcLineService qcIqcLineService;

    @Autowired
    private IQcIqcService qcIqcService;

    @Autowired
    private IQcIpqcLineService qcIpqcLineService;

    @Autowired
    private IQcIpqcService qcIpqcService;

    @Autowired
    private IQcOqcService qcOqcService;

    @Autowired
    private IQcOqcLineService qcOqcLineService;


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
     * 修改来料检验单缺陷记录
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:defectrecord:edit')")
    @Log(title = "检验单缺陷记录", businessType = BusinessType.UPDATE)
    @Transactional
    @PutMapping
    public AjaxResult updateList(@Validated @RequestBody ValidList<QcDefectRecord> defects){
        Long qcId = -1L;
        String qcType = "";
        Long lineId = -1L;
        if(CollUtil.isNotEmpty(defects)){
            for (QcDefectRecord defect: defects
            ) {
                if(StringUtils.isNotNull(defect.getRecordId())){
                    qcDefectRecordService.updateQcDefectRecord(defect);
                }else {
                    qcDefectRecordService.insertQcDefectRecord(defect);
                }
                qcId = defect.getQcId();
                qcType = defect.getQcType();
                lineId = defect.getLineId();
            }

            if(UserConstants.QC_TYPE_IQC.equals(qcType)){
                //更新来料检验单行上的cr,maj,min数量
                qcIqcLineService.updateCrMajMinQuantity(qcId,lineId);
                //更新来料检验单头上的cr,maj,min数量和比例
                qcIqcService.updateCrMajMinQuaAndRate(qcId);

            }else if(UserConstants.QC_TYPE_IPQC.equals(qcType)){
                //更新过程检验单行上的cr,maj,min数量
                qcIpqcLineService.updateCrMajMinQuantity(qcId,lineId);
                //更新过程检验单头上的cr,maj,min数量和比例
                qcIpqcService.updateCrMajMinQuaAndRate(qcId);
            }else {
                //更新出货检验单行上的cr,maj,min数量
                qcOqcLineService.updateCrMajMinQuantity(qcId,lineId);
                //更新出货检验单头上的cr,maj,min数量和比例
                qcOqcService.updateCrMajMinQuaAndRate(qcId);
            }

        }
        return AjaxResult.success();
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
