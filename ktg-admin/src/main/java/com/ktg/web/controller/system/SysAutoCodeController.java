package com.ktg.web.controller.system;

import com.ktg.system.strategy.AutoCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("用户信息管理")
@RestController
@RequestMapping("/system/autocode")
public class SysAutoCodeController {

    @Autowired
    private AutoCodeUtil autoCodeUtil;

    @ApiOperation("获取一个自动生成的编码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ruleCode", value = "规则编码", required = true, dataType = "String", paramType = "path", dataTypeClass = String.class),
            @ApiImplicitParam(name = "inputCharacter", value = "输入字符", required = false,dataType = "String", paramType = "path", dataTypeClass = String.class)
    })
    @GetMapping(value = {"/get/{ruleCode}/{inputCharacter}","/get/{ruleCode}"})
    public String getAutoCode(@PathVariable String ruleCode,@PathVariable(required = false) String inputCharacter){
        return autoCodeUtil.genSerialCode(ruleCode,inputCharacter);
    }

}
