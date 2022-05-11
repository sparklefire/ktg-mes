package com.ktg.mes.tm.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ktg.common.constant.UserConstants;
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
import com.ktg.mes.tm.domain.TmToolType;
import com.ktg.mes.tm.service.ITmToolTypeService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 工装夹具类型Controller
 * 
 * @author yinjinlu
 * @date 2022-05-10
 */
@RestController
@RequestMapping("/mes/tm/tooltype")
public class TmToolTypeController extends BaseController
{
    @Autowired
    private ITmToolTypeService tmToolTypeService;

    /**
     * 查询工装夹具类型列表
     */
    @PreAuthorize("@ss.hasPermi('mes:tm:tooltype:list')")
    @GetMapping("/list")
    public TableDataInfo list(TmToolType tmToolType)
    {
        startPage();
        List<TmToolType> list = tmToolTypeService.selectTmToolTypeList(tmToolType);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('mes:tm:tooltype:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll(){
        TmToolType tmToolType = new TmToolType();
        List<TmToolType> list = tmToolTypeService.selectTmToolTypeList(tmToolType);
        return AjaxResult.success(list);
    }


    /**
     * 导出工装夹具类型列表
     */
    @PreAuthorize("@ss.hasPermi('mes:tm:tooltype:export')")
    @Log(title = "工装夹具类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TmToolType tmToolType)
    {
        List<TmToolType> list = tmToolTypeService.selectTmToolTypeList(tmToolType);
        ExcelUtil<TmToolType> util = new ExcelUtil<TmToolType>(TmToolType.class);
        util.exportExcel(response, list, "工装夹具类型数据");
    }

    /**
     * 获取工装夹具类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:tm:tooltype:query')")
    @GetMapping(value = "/{toolTypeId}")
    public AjaxResult getInfo(@PathVariable("toolTypeId") Long toolTypeId)
    {
        return AjaxResult.success(tmToolTypeService.selectTmToolTypeByToolTypeId(toolTypeId));
    }

    /**
     * 新增工装夹具类型
     */
    @PreAuthorize("@ss.hasPermi('mes:tm:tooltype:add')")
    @Log(title = "工装夹具类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TmToolType tmToolType)
    {
        if(UserConstants.NOT_UNIQUE.equals(tmToolTypeService.checkToolTypeCodeUnique(tmToolType))){
            return AjaxResult.error("类型编码已存在！");
        }
        if(UserConstants.NOT_UNIQUE.equals(tmToolTypeService.checkToolTypeNameUnique(tmToolType))){
            return AjaxResult.error("类型名称已存在！");
        }
        return toAjax(tmToolTypeService.insertTmToolType(tmToolType));
    }

    /**
     * 修改工装夹具类型
     */
    @PreAuthorize("@ss.hasPermi('mes:tm:tooltype:edit')")
    @Log(title = "工装夹具类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TmToolType tmToolType)
    {
        if(UserConstants.NOT_UNIQUE.equals(tmToolTypeService.checkToolTypeCodeUnique(tmToolType))){
            return AjaxResult.error("类型编码已存在！");
        }
        if(UserConstants.NOT_UNIQUE.equals(tmToolTypeService.checkToolTypeNameUnique(tmToolType))){
            return AjaxResult.error("类型名称已存在！");
        }
        return toAjax(tmToolTypeService.updateTmToolType(tmToolType));
    }

    /**
     * 删除工装夹具类型
     */
    @PreAuthorize("@ss.hasPermi('mes:tm:tooltype:remove')")
    @Log(title = "工装夹具类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{toolTypeIds}")
    public AjaxResult remove(@PathVariable Long[] toolTypeIds)
    {
        return toAjax(tmToolTypeService.deleteTmToolTypeByToolTypeIds(toolTypeIds));
    }
}
