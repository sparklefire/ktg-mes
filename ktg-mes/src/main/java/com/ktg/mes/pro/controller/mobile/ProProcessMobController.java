package com.ktg.mes.pro.controller.mobile;

import com.ktg.common.constant.UserConstants;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.pro.domain.ProProcess;
import com.ktg.mes.pro.service.IProProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * 获取工序详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:process:query')")
    @GetMapping(value = "/{processId}")
    public AjaxResult getInfo(@PathVariable("processId") Long processId)
    {
        ProProcess p = proProcessService.selectProProcessByProcessId(processId);
        if(StringUtils.isNotNull(p)){
            return AjaxResult.success(p);
        }else{
            return AjaxResult.error("未查询到当前工序信息");
        }

    }
}
