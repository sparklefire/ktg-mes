package com.ktg.mes.tm.controller;

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
import com.ktg.mes.tm.domain.TmTool;
import com.ktg.mes.tm.service.ITmToolService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 工装夹具清单Controller
 * 
 * @author yinjinlu
 * @date 2022-05-11
 */
@RestController
@RequestMapping("/mes/tm/tool")
public class TmToolController extends BaseController
{
    @Autowired
    private ITmToolService tmToolService;

    @Autowired
    private ITmToolTypeService tmToolTypeService;

    /**
     * 查询工装夹具清单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:tm:tool:list')")
    @GetMapping("/list")
    public TableDataInfo list(TmTool tmTool)
    {
        startPage();
        List<TmTool> list = tmToolService.selectTmToolList(tmTool);
        return getDataTable(list);
    }

    /**
     * 导出工装夹具清单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:tm:tool:export')")
    @Log(title = "工装夹具清单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TmTool tmTool)
    {
        List<TmTool> list = tmToolService.selectTmToolList(tmTool);
        ExcelUtil<TmTool> util = new ExcelUtil<TmTool>(TmTool.class);
        util.exportExcel(response, list, "工装夹具清单数据");
    }

    /**
     * 获取工装夹具清单详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:tm:tool:query')")
    @GetMapping(value = "/{toolId}")
    public AjaxResult getInfo(@PathVariable("toolId") Long toolId)
    {
        return AjaxResult.success(tmToolService.selectTmToolByToolId(toolId));
    }

    /**
     * 新增工装夹具清单
     */
    @PreAuthorize("@ss.hasPermi('mes:tm:tool:add')")
    @Log(title = "工装夹具清单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TmTool tmTool)
    {
        if(UserConstants.NOT_UNIQUE.equals(tmToolService.checkToolCodeUnique(tmTool))){
            return AjaxResult.error("此工装夹具编码已存在！");
        }

        TmToolType type = tmToolTypeService.selectTmToolTypeByToolTypeId(tmTool.getToolTypeId());
        tmTool.setToolTypeCode(type.getToolTypeCode());
        tmTool.setToolTypeName(type.getToolTypeName());
        tmTool.setCodeFlag(type.getCodeFlag());
        return toAjax(tmToolService.insertTmTool(tmTool));
    }

    /**
     * 修改工装夹具清单
     */
    @PreAuthorize("@ss.hasPermi('mes:tm:tool:edit')")
    @Log(title = "工装夹具清单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TmTool tmTool)
    {
        if(UserConstants.NOT_UNIQUE.equals(tmToolService.checkToolCodeUnique(tmTool))){
            return AjaxResult.error("此工装夹具编码已存在！");
        }
        TmToolType type = tmToolTypeService.selectTmToolTypeByToolTypeId(tmTool.getToolTypeId());
        tmTool.setToolTypeCode(type.getToolTypeCode());
        tmTool.setToolTypeName(type.getToolTypeName());
        tmTool.setCodeFlag(type.getCodeFlag());
        if(UserConstants.MAINTEN_TYPE_REGULAR.equals(tmTool.getMaintenType())){
            tmTool.setNextMaintenPeriod(null);
        }else{
            tmTool.setNextMaintenDate(null);
        }
        return toAjax(tmToolService.updateTmTool(tmTool));
    }

    /**
     * 删除工装夹具清单
     */
    @PreAuthorize("@ss.hasPermi('mes:tm:tool:remove')")
    @Log(title = "工装夹具清单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{toolIds}")
    public AjaxResult remove(@PathVariable Long[] toolIds)
    {
        return toAjax(tmToolService.deleteTmToolByToolIds(toolIds));
    }
}
