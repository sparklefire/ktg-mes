package com.ktg.mes.qc.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ktg.common.constant.UserConstants;
import com.ktg.system.strategy.AutoCodeUtil;
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
import com.ktg.mes.qc.domain.QcDefect;
import com.ktg.mes.qc.service.IQcDefectService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 常见缺陷Controller
 * 
 * @author yinjinlu
 * @date 2022-05-19
 */
@RestController
@RequestMapping("/mes/qc/qcdefect")
public class QcDefectController extends BaseController
{
    @Autowired
    private IQcDefectService qcDefectService;

    @Autowired
    private AutoCodeUtil autoCodeUtil;

    /**
     * 查询常见缺陷列表
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:qcdefect:list')")
    @GetMapping("/list")
    public TableDataInfo list(QcDefect qcDefect)
    {
        startPage();
        List<QcDefect> list = qcDefectService.selectQcDefectList(qcDefect);
        return getDataTable(list);
    }

    /**
     * 导出常见缺陷列表
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:qcdefect:export')")
    @Log(title = "常见缺陷", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QcDefect qcDefect)
    {
        List<QcDefect> list = qcDefectService.selectQcDefectList(qcDefect);
        ExcelUtil<QcDefect> util = new ExcelUtil<QcDefect>(QcDefect.class);
        util.exportExcel(response, list, "常见缺陷数据");
    }

    /**
     * 获取常见缺陷详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:qcdefect:query')")
    @GetMapping(value = "/{defectId}")
    public AjaxResult getInfo(@PathVariable("defectId") Long defectId)
    {
        return AjaxResult.success(qcDefectService.selectQcDefectByDefectId(defectId));
    }

    /**
     * 新增常见缺陷
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:qcdefect:add')")
    @Log(title = "常见缺陷", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QcDefect qcDefect)
    {
        qcDefect.setDefectCode(autoCodeUtil.genSerialCode(UserConstants.DEFECT_CODE,null));
        return toAjax(qcDefectService.insertQcDefect(qcDefect));
    }

    /**
     * 修改常见缺陷
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:qcdefect:edit')")
    @Log(title = "常见缺陷", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QcDefect qcDefect)
    {
        return toAjax(qcDefectService.updateQcDefect(qcDefect));
    }

    /**
     * 删除常见缺陷
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:qcdefect:remove')")
    @Log(title = "常见缺陷", businessType = BusinessType.DELETE)
	@DeleteMapping("/{defectIds}")
    public AjaxResult remove(@PathVariable Long[] defectIds)
    {
        return toAjax(qcDefectService.deleteQcDefectByDefectIds(defectIds));
    }
}
