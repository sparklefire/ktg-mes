package com.ktg.mes.md.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.core.domain.entity.SysUser;
import com.ktg.common.core.domain.model.LoginUser;
import com.ktg.common.utils.StringUtils;
import com.ktg.framework.web.service.TokenService;
import com.ktg.mes.md.domain.MdWorkstation;
import com.ktg.mes.md.service.IMdWorkstationService;
import com.ktg.mes.pro.domain.ProFeedback;
import com.ktg.mes.pro.domain.ProUserWorkstation;
import com.ktg.mes.pro.domain.ProWorkrecord;
import com.ktg.mes.pro.service.IProUserWorkstationService;
import com.ktg.mes.pro.service.IProWorkrecordService;
import com.ktg.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api
@RestController
@RequestMapping("/mobile/md/workstation")
public class MdWorkstationMobController extends BaseController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IMdWorkstationService mdWorkstationService;

    @Autowired
    private IProWorkrecordService workrecordService;

    @Autowired
    private IProUserWorkstationService userWorkstationService;

    @ApiOperation("工作站查询接口")
    @GetMapping("/getWorkstationList")
    public AjaxResult getWorkstationList(MdWorkstation mdWorkstation){
        List<MdWorkstation> list = mdWorkstationService.selectMdWorkstationList(mdWorkstation);
        return AjaxResult.success(list);
    }

    @ApiOperation("获取当前用户绑定的工作站")
    @GetMapping("/getMyWorkstation")
    public AjaxResult getBindWorkstation(HttpServletRequest request){
        LoginUser loginUser = tokenService.getLoginUser(request);
        SysUser user = sysUserService.selectUserById(loginUser.getUser().getUserId());
        ProUserWorkstation param = new ProUserWorkstation();
        param.setUserId(user.getUserId());
        List<ProUserWorkstation> uw = userWorkstationService.selectProUserWorkstationList(param);
        if(!CollectionUtil.isEmpty(uw)){
            return AjaxResult.success(uw.get(0));
        }
        return AjaxResult.success();
    }

    @ApiOperation("上工/下工记录")
    @PutMapping()
    @Transactional
    public AjaxResult bindWorkstation(@RequestBody ProWorkrecord workrecord){
        MdWorkstation param = new MdWorkstation();
        param.setWorkstationCode(workrecord.getWorkstationCode());
        List<MdWorkstation> workstations = mdWorkstationService.selectMdWorkstationList(param);
        MdWorkstation workstation = null;
        if(!CollectionUtil.isEmpty(workstations)){
            workstation = workstations.get(0);
        }

        if(!StringUtils.isNotNull(workstation)){
            return AjaxResult.error("未能找到对应的工作站");
        }

        SysUser user = sysUserService.selectUserById(workrecord.getUserId());
        workrecord.setUserName(user.getUserName());
        workrecord.setWorkstationId(workstation.getWorkstationId());
        workrecord.setWorkstationName(workstation.getWorkstationName());
        workrecordService.insertProWorkrecord(workrecord);

        ProUserWorkstation uw = new ProUserWorkstation();
        uw.setUserId(workrecord.getUserId());
        uw.setUserName(workrecord.getUserName());
        uw.setNickName(workrecord.getNickName());
        uw.setWorkstationId(workstation.getWorkstationId());
        uw.setWorkstationCode(workstation.getWorkstationCode());
        uw.setWorkstationName(workstation.getWorkstationName());

        if(UserConstants.YES.equals(workrecord.getOperationFlag())){
            //如果是绑定
            userWorkstationService.deleteByUserName(workrecord.getUserName());

            userWorkstationService.insertProUserWorkstation(uw);
        }else{
            userWorkstationService.deleteByUserName(workrecord.getUserName());
        }

        return AjaxResult.success();
    }

}
