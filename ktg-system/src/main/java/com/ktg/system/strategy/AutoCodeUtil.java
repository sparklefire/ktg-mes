package com.ktg.system.strategy;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import com.ktg.common.annotation.Log;
import com.ktg.common.core.domain.entity.SysAutoCodePart;
import com.ktg.common.core.domain.entity.SysAutoCodeResult;
import com.ktg.common.core.domain.entity.SysAutoCodeRule;
import com.ktg.common.enums.BusinessType;
import com.ktg.common.enums.PartTypeEnum;
import com.ktg.common.utils.StringUtils;
import com.ktg.system.service.IAutoCodePartService;
import com.ktg.system.service.IAutoCodeResultService;
import com.ktg.system.service.IAutoCodeRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutoCodeUtil {
    public static ThreadLocal<Boolean> threadLocal = new ThreadLocal<>();

    @Autowired
    private IAutoCodeRuleService iAutoCodeRuleService;

    @Autowired
    private IAutoCodePartService iAutoCodePartService;

    @Autowired
    private IAutoCodeResultService iAutoCodeResultService;

    @Autowired
    private PartTypeHandler partTypeHandler;

    private String lastSerialNo;

    @Log(title = "生成业务编号",businessType = BusinessType.INSERT)
    synchronized
    public String genSerialCode(String ruleCode,String inputCharacter){

        //查找编码规则
        SysAutoCodeRule rule = iAutoCodeRuleService.getOne(ruleCode);
        Assert.notNull(rule,"未获取到指定类型:[{}]的业务编码生成规则",ruleCode);

        //查找规则组成
        SysAutoCodePart partParam = new SysAutoCodePart();
        partParam.setRuleId(rule.getRuleId());
        List<SysAutoCodePart> parts = iAutoCodePartService.listPart(partParam);
        List<SysAutoCodePart> collect = parts.stream().filter(part->PartTypeEnum.PART_TYPE_SERIALNO.getCode().equals(part.getPartType())).collect(Collectors.toList());

        Assert.isTrue(collect.size()<2,"编码规则[{}]流水号方式的组成只能存在一个",ruleCode);

        StringBuilder buff = new StringBuilder();
        parts.forEach(codePart ->{
            codePart.setInputCharacter(inputCharacter);
            //根据当前组成部分，获取当前组成部分的结果
            String partStr = partTypeHandler.choiceExecute(codePart);

            //如果是流水号部分，则进行记录
            if(StringUtils.equals(codePart.getPartType(),PartTypeEnum.PART_TYPE_SERIALNO.getCode())){
                lastSerialNo = partStr;
            }
            //将获取到的部分组装进整体编码中
            buff.append(partStr);
        });

        Assert.notBlank(buff.toString(),"规则：[{}]生成的编码为空！",ruleCode);

        String autoCode = paddingStr(rule,buff);

        //将生成结果保存到数据库
        saveAutoCodeResult(rule,autoCode,inputCharacter);
        return autoCode;
    }

    /**
     * 根据编码规则的配置进行补齐操作
     * @param rule
     * @param sb
     * @return
     */
    private String paddingStr(SysAutoCodeRule rule,StringBuilder sb){
        String isPadding = rule.getIsPadded();
        if("Y".equals(isPadding)){
            int maxLength = rule.getMaxLength();
            String paddingChar = rule.getPaddedChar();
            StringBuilder resultStr = new StringBuilder();
            long length = maxLength - sb.length();
            Assert.isTrue(maxLength>sb.length(),"生成的编码[{}]已经超出规则中配置的最大长度：[{}]",sb.toString(),maxLength);

            if("L".equals(rule.getPaddedMethod())){
                //左补齐
                //使用指定字符补齐左侧后,再将生成的编码添加到右侧
                for(;length>0;length --){
                    resultStr.append(paddingChar);
                }
                resultStr.append(sb);
            }else{
                //右补齐
                //将生成的编码添加到左侧后,再使用指定字符补齐右侧
                resultStr.append(sb);
                for(;length>0;length --){
                    resultStr.append(paddingChar);
                }
            }
            return resultStr.toString();
        }
        return sb.toString(); //如果不需要补齐，则直接返回
    }

    private void saveAutoCodeResult(SysAutoCodeRule rule,String autoCode,String inputChar){
        Boolean flag = threadLocal.get(); //针对当前线程的判断 flag = true则数据库中没有当前规则的生成记录
        if(flag !=null && flag){
            SysAutoCodeResult rs = new SysAutoCodeResult();
            rs.setRuleId(rule.getRuleId());
            rs.setGenDate(DateUtil.format(LocalDateTime.now(),"yyyyMMddHHmmss"));
            rs.setLastResult(autoCode);
            rs.setGenIndex(1);
            rs.setLastSerialNo(Integer.parseInt(lastSerialNo));
            rs.setLastInputChar(inputChar);
            iAutoCodeResultService.saveAutoCodeResult(rs);
        }else{
            //直接更新对应的记录（我们默认非流水号模式下一个RULE_CODE只有一种方式）
            SysAutoCodeResult bo = new SysAutoCodeResult();
            bo.setRuleId(rule.getRuleId());
            List<SysAutoCodeResult> results = iAutoCodeResultService.list(bo);
            Assert.notEmpty(results,"未查询到规则{[]}对应的结果记录",rule.getRuleCode());
            SysAutoCodeResult rs = results.get(0);
            rs.setLastResult(autoCode);
            rs.setGenDate(DateUtil.format(LocalDateTime.now(),"yyyyMMddHHmmss"));
            rs.setGenIndex(rs.getGenIndex()+1);
            rs.setLastSerialNo(Integer.parseInt(lastSerialNo));
            rs.setLastInputChar(inputChar);
            iAutoCodeResultService.updateAutoCodeResult(rs);
        }

    }
}
