package com.ktg.system.strategy;

import com.ktg.common.core.domain.entity.SysAutoCodePart;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@Order(0)
public class PartTypeInputCharHandler implements PartTypeTemplate {


    @Override
    public String partHandle(SysAutoCodePart sysAutoCodePart) {
        String inputCharacter = sysAutoCodePart.getInputCharacter();
        Assert.notNull(inputCharacter,"编码规则传入字符不能为空！");
        Assert.isTrue(inputCharacter.length() == sysAutoCodePart.getPartLength(),"传入字符的长度错误！");
        return inputCharacter;
    }
}
