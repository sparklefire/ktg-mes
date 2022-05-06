package com.ktg.web.controller.system;

import com.ktg.common.annotation.Log;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.core.domain.entity.SysAutoCodeRule;
import com.ktg.common.core.page.TableDataInfo;
import com.ktg.common.enums.BusinessType;
import com.ktg.system.service.IAutoCodeRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/autocode/rule")
public class SysAutoCodeRuleController extends BaseController {

    @Autowired
    private IAutoCodeRuleService iAutoCodeRuleService;

    @PreAuthorize("@ss.hasPermi('system:autocode:rule:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysAutoCodeRule sysAutoCodeRule){
        startPage();
        List<SysAutoCodeRule> rules = iAutoCodeRuleService.selectAutoCodeList(sysAutoCodeRule);
        return getDataTable(rules);
    }

    @PreAuthorize("@ss.hasPermi('system:autocode:rule:query')")
    @GetMapping("/{ruleId}")
    public AjaxResult getInfo(@PathVariable Long ruleId){
        return AjaxResult.success(iAutoCodeRuleService.findById(ruleId));
    }

    @PreAuthorize("@ss.hasPermi('system:autocode:rule:add')")
    @Log(title = "新增自动编码规则",businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysAutoCodeRule sysAutoCodeRule){
        if(UserConstants.NOT_UNIQUE.equals(iAutoCodeRuleService.checkRuleCodeUnique(sysAutoCodeRule))){
            return AjaxResult.error("自动编码规则的编号重复");
        }
        if(UserConstants.NOT_UNIQUE.equals(iAutoCodeRuleService.checkRuleNameUnique(sysAutoCodeRule))){
            return AjaxResult.error("自动编码规则的名称重复");
        }
        if("N".equals(sysAutoCodeRule.getIsPadded())){
            sysAutoCodeRule.setPaddedChar(null);
            sysAutoCodeRule.setPaddedMethod(null);
        }
        sysAutoCodeRule.setCreateBy(getUsername());
        return toAjax(iAutoCodeRuleService.insertInfo(sysAutoCodeRule));
    }

    @PreAuthorize("@ss.hasPermi('system:autocode:rule:edit')")
    @Log(title = "更新自动编码规则",businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult updateInfo(@Validated @RequestBody SysAutoCodeRule sysAutoCodeRule){
        if(UserConstants.NOT_UNIQUE.equals(iAutoCodeRuleService.checkRuleCodeUnique(sysAutoCodeRule))){
            return AjaxResult.error("自动编码规则的编号重复");
        }
        if(UserConstants.NOT_UNIQUE.equals(iAutoCodeRuleService.checkRuleNameUnique(sysAutoCodeRule))){
            return AjaxResult.error("自动编码规则的名称重复");
        }
        sysAutoCodeRule.setUpdateBy(getUsername());
        return toAjax(iAutoCodeRuleService.updateInfo(sysAutoCodeRule));
    }

    @PreAuthorize("@ss.hasPermi('system:autocode:rule:remove')")
    @Log(title = "删除自动编码规则",businessType = BusinessType.DELETE)
    @DeleteMapping("/{ruleIds}")
    public AjaxResult remove(@PathVariable Long[] ruleIds){

        return toAjax(iAutoCodeRuleService.deleteByIds(ruleIds));
    }

}
