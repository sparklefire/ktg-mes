package com.ktg.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.core.domain.entity.SysAutoCodeRule;
import com.ktg.common.utils.StringUtils;
import com.ktg.system.mapper.SysAutoCodeRuleMapper;
import com.ktg.system.service.IAutoCodeRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysAutoCodeRuleServiceImpl implements IAutoCodeRuleService {

    @Autowired
    private SysAutoCodeRuleMapper sysAutoCodeRuleMapper;

    @Override
    public SysAutoCodeRule getOne(String ruleCode) {
        SysAutoCodeRule param = new SysAutoCodeRule();
        param.setRuleCode(ruleCode);
        List<SysAutoCodeRule> rules = sysAutoCodeRuleMapper.selectSysAutoCodeResultList(param);
        if(CollectionUtil.isNotEmpty(rules)){
            return rules.get(0);
        }
        return null;
    }

    @Override
    public List<SysAutoCodeRule> selectAutoCodeList(SysAutoCodeRule sysAutoCodeRule) {
        return sysAutoCodeRuleMapper.selectSysAutoCodeResultList(sysAutoCodeRule);
    }

    @Override
    public SysAutoCodeRule findById(Long ruleId) {
        return sysAutoCodeRuleMapper.findById(ruleId);
    }

    @Override
    public String checkRuleCodeUnique(SysAutoCodeRule sysAutoCodeRule) {
        SysAutoCodeRule rule = sysAutoCodeRuleMapper.checkRuleCodeUnique(sysAutoCodeRule.getRuleCode());
        if (StringUtils.isNotNull(rule) && rule.getRuleId().longValue() != sysAutoCodeRule.getRuleId().longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }

        return UserConstants.UNIQUE;
    }

    @Override
    public String checkRuleNameUnique(SysAutoCodeRule sysAutoCodeRule) {
        SysAutoCodeRule rule = sysAutoCodeRuleMapper.checkRuleNameUnique(sysAutoCodeRule.getRuleName());
        if (StringUtils.isNotNull(rule) && rule.getRuleId().longValue() != sysAutoCodeRule.getRuleId().longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }

        return UserConstants.UNIQUE;
    }

    @Override
    public int insertInfo(SysAutoCodeRule rule) {
        return sysAutoCodeRuleMapper.add(rule);
    }

    @Override
    public int updateInfo(SysAutoCodeRule rule) {
        return sysAutoCodeRuleMapper.updateSysAutoCodeRule(rule);
    }

    @Override
    public int deleteById(Long ruleId) {

        return sysAutoCodeRuleMapper.deleteById(ruleId);
    }

    @Override
    public int deleteByIds(Long[] ruleIds) {
        for (Long ruleId: ruleIds
             ) {
            sysAutoCodeRuleMapper.deleteById(ruleId);
        }
        return 1;
    }
}
