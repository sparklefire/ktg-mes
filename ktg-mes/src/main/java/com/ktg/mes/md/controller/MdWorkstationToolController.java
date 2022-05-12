package com.ktg.mes.md.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ktg.common.constant.UserConstants;
import com.ktg.mes.tm.domain.TmToolType;
import com.ktg.mes.tm.service.ITmToolTypeService;
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
import com.ktg.mes.md.domain.MdWorkstationTool;
import com.ktg.mes.md.service.IMdWorkstationToolService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 工装夹具资源Controller
 * 
 * @author yinjinlu
 * @date 2022-05-12
 */
@RestController
@RequestMapping("/mes/md/workstationtool")
public class MdWorkstationToolController extends BaseController
{
    @Autowired
    private IMdWorkstationToolService mdWorkstationToolService;

    @Autowired
    private ITmToolTypeService toolTypeService;

    /**
     * 查询工装夹具资源列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationtool:list')")
    @GetMapping("/list")
    public TableDataInfo list(MdWorkstationTool mdWorkstationTool)
    {
        startPage();
        List<MdWorkstationTool> list = mdWorkstationToolService.selectMdWorkstationToolList(mdWorkstationTool);
        return getDataTable(list);
    }

    /**
     * 导出工装夹具资源列表
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationtool:export')")
    @Log(title = "工装夹具资源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MdWorkstationTool mdWorkstationTool)
    {
        List<MdWorkstationTool> list = mdWorkstationToolService.selectMdWorkstationToolList(mdWorkstationTool);
        ExcelUtil<MdWorkstationTool> util = new ExcelUtil<MdWorkstationTool>(MdWorkstationTool.class);
        util.exportExcel(response, list, "工装夹具资源数据");
    }

    /**
     * 获取工装夹具资源详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationtool:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return AjaxResult.success(mdWorkstationToolService.selectMdWorkstationToolByRecordId(recordId));
    }

    /**
     * 新增工装夹具资源
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationtool:add')")
    @Log(title = "工装夹具资源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MdWorkstationTool mdWorkstationTool)
    {
        if(UserConstants.NOT_UNIQUE.equals(mdWorkstationToolService.checkToolTypeExists(mdWorkstationTool))){
            return AjaxResult.error("此工装夹具类型已添加！");
        }
        TmToolType type = toolTypeService.selectTmToolTypeByToolTypeId(mdWorkstationTool.getToolTypeId());
        mdWorkstationTool.setToolTypeCode(type.getToolTypeCode());
        mdWorkstationTool.setToolTypeName(type.getToolTypeName());
        return toAjax(mdWorkstationToolService.insertMdWorkstationTool(mdWorkstationTool));
    }

    /**
     * 修改工装夹具资源
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationtool:edit')")
    @Log(title = "工装夹具资源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MdWorkstationTool mdWorkstationTool)
    {
        if(UserConstants.NOT_UNIQUE.equals(mdWorkstationToolService.checkToolTypeExists(mdWorkstationTool))){
            return AjaxResult.error("此工装夹具类型已添加！");
        }
        TmToolType type = toolTypeService.selectTmToolTypeByToolTypeId(mdWorkstationTool.getToolTypeId());
        mdWorkstationTool.setToolTypeCode(type.getToolTypeCode());
        mdWorkstationTool.setToolTypeName(type.getToolTypeName());
        return toAjax(mdWorkstationToolService.updateMdWorkstationTool(mdWorkstationTool));
    }

    /**
     * 删除工装夹具资源
     */
    @PreAuthorize("@ss.hasPermi('mes:md:workstationtool:remove')")
    @Log(title = "工装夹具资源", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(mdWorkstationToolService.deleteMdWorkstationToolByRecordIds(recordIds));
    }
}
