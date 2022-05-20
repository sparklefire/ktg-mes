package com.ktg.mes.qc.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.qc.domain.ValidList;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ktg.mes.qc.domain.QcIqcDefect;
import com.ktg.mes.qc.service.IQcIqcDefectService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 来料检验单缺陷记录Controller
 * 
 * @author yinjinlu
 * @date 2022-05-19
 */
@RestController
@RequestMapping("/mes/qc/iqcdefect")
public class QcIqcDefectController extends BaseController
{
    @Autowired
    private IQcIqcDefectService qcIqcDefectService;

    /**
     * 查询来料检验单缺陷记录列表
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:iqcdefect:list')")
    @GetMapping("/list")
    public TableDataInfo list(QcIqcDefect qcIqcDefect)
    {
        startPage();
        List<QcIqcDefect> list = qcIqcDefectService.selectQcIqcDefectList(qcIqcDefect);
        return getDataTable(list);
    }

    /**
     * 导出来料检验单缺陷记录列表
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:iqcdefect:export')")
    @Log(title = "来料检验单缺陷记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QcIqcDefect qcIqcDefect)
    {
        List<QcIqcDefect> list = qcIqcDefectService.selectQcIqcDefectList(qcIqcDefect);
        ExcelUtil<QcIqcDefect> util = new ExcelUtil<QcIqcDefect>(QcIqcDefect.class);
        util.exportExcel(response, list, "来料检验单缺陷记录数据");
    }

    /**
     * 获取来料检验单缺陷记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:iqcdefect:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return AjaxResult.success(qcIqcDefectService.selectQcIqcDefectByRecordId(recordId));
    }

    /**
     * 新增来料检验单缺陷记录
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:iqcdefect:add')")
    @Log(title = "来料检验单缺陷记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QcIqcDefect qcIqcDefect)
    {
        return toAjax(qcIqcDefectService.insertQcIqcDefect(qcIqcDefect));
    }


    /**
     * 修改来料检验单缺陷记录
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:iqcdefect:edit')")
    @Log(title = "来料检验单缺陷记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult updateList(@Validated @RequestBody ValidList<QcIqcDefect> defects){
        if(CollUtil.isNotEmpty(defects)){
            for (QcIqcDefect defect: defects
                 ) {
                if(StringUtils.isNotNull(defect.getRecordId())){
                    qcIqcDefectService.updateQcIqcDefect(defect);
                }else {
                    qcIqcDefectService.insertQcIqcDefect(defect);
                }
            }
        }

        return AjaxResult.success();
    }


    /**
     * 删除来料检验单缺陷记录
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:iqcdefect:remove')")
    @Log(title = "来料检验单缺陷记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(qcIqcDefectService.deleteQcIqcDefectByRecordIds(recordIds));
    }
}
