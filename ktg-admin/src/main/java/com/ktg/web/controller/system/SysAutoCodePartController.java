package com.ktg.web.controller.system;

import com.ktg.common.annotation.Log;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.core.domain.entity.SysAutoCodePart;
import com.ktg.common.core.page.TableDataInfo;
import com.ktg.common.enums.BusinessType;
import com.ktg.system.service.IAutoCodePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/autocode/part")
public class SysAutoCodePartController extends BaseController {

    @Autowired
    private IAutoCodePartService iAutoCodePartService;

    @PreAuthorize("@ss.hasPermi('system:autocode:part:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysAutoCodePart sysAutoCodePart){
        startPage();
        List<SysAutoCodePart> parts = iAutoCodePartService.listPart(sysAutoCodePart);
        return getDataTable(parts);
    }

    @PreAuthorize("@ss.hasPermi('system:autocode:part:query')")
    @GetMapping("/{partId}")
    public AjaxResult getInfo(@PathVariable Long partId){
        return AjaxResult.success(iAutoCodePartService.findById(partId));
    }


    @PreAuthorize("@ss.hasPermi('system:autocode:part:insert')")
    @Log(title = "新增编码生产规则组成部分",businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysAutoCodePart part){
        if(UserConstants.NOT_UNIQUE.equals(iAutoCodePartService.checkPartUnique(part))){
            return AjaxResult.error("规则组成不唯一，清检查组成编码、组成名称、组成序号");
        }
        part.setCreateBy(getUsername());
        return toAjax(iAutoCodePartService.insertPart(part));
    }

    @PreAuthorize("@ss.hasPermi('system:autocode:part:update')")
    @Log(title = "更新物料编码",businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult update(@Validated @RequestBody SysAutoCodePart sysAutoCodePart){
        if(UserConstants.NOT_UNIQUE.equals(iAutoCodePartService.checkPartUnique(sysAutoCodePart))){
            return AjaxResult.error("规则组成不唯一，清检查组成编码、组成名称、组成序号");
        }
        sysAutoCodePart.setUpdateBy(getUsername());
        return toAjax(iAutoCodePartService.updatePart(sysAutoCodePart));
    }


    @PreAuthorize("@ss.hasPermi('system:autocode:part:remove')")
    @Log(title = "删除物料编码",businessType = BusinessType.DELETE)
    @DeleteMapping("/{partIds}")
    public AjaxResult delete(@PathVariable Long[] partIds){
        return toAjax(iAutoCodePartService.deleteByIds(partIds));
    }

}
