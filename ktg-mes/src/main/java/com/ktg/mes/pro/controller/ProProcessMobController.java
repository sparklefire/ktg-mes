package com.ktg.mes.pro.controller;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.mes.pro.domain.ProProcess;
import com.ktg.mes.pro.service.IProProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mobile/pro/process")
public class ProProcessMobController extends BaseController {

    @Autowired
    private IProProcessService proProcessService;

    @GetMapping("/getProcessList")
    public AjaxResult getProcessList(){
        ProProcess proProcess = new ProProcess();
        proProcess.setEnableFlag(UserConstants.YES);
        List<ProProcess> list = proProcessService.selectProProcessList(proProcess);
        return AjaxResult.success(list);
    }
}
