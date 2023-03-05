package com.ktg.web.controller.system;


import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.core.domain.entity.SysUser;
import com.ktg.common.core.domain.model.LoginUser;
import com.ktg.common.utils.SecurityUtils;
import com.ktg.common.utils.StringUtils;
import com.ktg.framework.web.service.MobileLoginService;
import com.ktg.framework.web.service.TokenService;
import com.ktg.system.domain.LoginParams;
import com.ktg.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录控制器
 *
 * @author SK
 * @since 2018/6/13
 */
@Api("用户信息")
@RestController
@RequestMapping("/mobile/user")
public class UserController {

    @Autowired
    private ISysUserService sysUserService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private MobileLoginService loginService;

    /**
     * 注册用户
     *
     * @return -1 用户名或密码错误  -2 账号冻结  -3 账号锁定 1 成功  -4 验证码错误
     */
    @PostMapping("/registerUser")
    @ResponseBody
    public AjaxResult registerUser(HttpServletRequest request) {
        String phoneNo = request.getParameter("phoneNo");
        String validCode = request.getParameter("validCode");
        // 登录结果
        LoginParams loginParams = new LoginParams();
        loginParams.setPhoneNo(phoneNo);
        loginParams.setValidCode(validCode);
        return loginService.registerUser(loginParams);
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @ApiOperation("获取用户信息")
    @ApiResponses({
            @ApiResponse(code = 200,message = "查询成功",response = AjaxResult.class),
    })
    @GetMapping("getUserInfo")
    public AjaxResult getUserInfo(HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        SysUser user = sysUserService.selectUserById(loginUser.getUser().getUserId());
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        return ajax;
    }

    @PostMapping({"saveUserInfo"})
    @ResponseBody
    public AjaxResult saveUserInfo(SysUser user, HttpServletRequest request) {
        AjaxResult ajax = AjaxResult.success("个人信息修改成功！");
        SysUser currentUser = SecurityUtils.getLoginUser().getUser();
        currentUser = sysUserService.selectUserById(currentUser.getUserId());
        if (StringUtils.isNotBlank(user.getNickName())) {
            currentUser.setNickName(user.getNickName());
        }
        if (StringUtils.isNotBlank(user.getEmail())) {
            currentUser.setEmail(user.getEmail());
        } else {
            currentUser.setEmail("");
        }
        if (StringUtils.isNotBlank(user.getPhonenumber())) {
            currentUser.setPhonenumber(user.getPhonenumber());
        } else {
            currentUser.setPhonenumber("");
        }
        if (StringUtils.isNotBlank(user.getSex())) {
            currentUser.setSex(user.getSex());
        }
        sysUserService.updateUser(currentUser);
        return ajax;
    }
}
