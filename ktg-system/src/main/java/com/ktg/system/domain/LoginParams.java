package com.ktg.system.domain;



/**
 * 登入参数实体
 */
public class LoginParams {

    /**
     * 用户名
     */
    private String phoneNo;

    /**
     * 密码
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户输入的验证码
     */
    private String validCode;

    /**
     * session中的验证码
     */
    private String codeInSession;
    /**
     * 验证码类型1注册验证码2普通登录验证码
     */
    private String validCodeType;

    /**
     * 登录来源 0 pc  1 app 2 mobile
     */
    private int source;
    /**
     * 登录方式0验证码登录，1用户名密码登录，2本机一键登录，3微信单点登录
     */
    private String loginType;

    public void setFromApp() {
        this.source = 1;
    }

    /**
     * 验证验证码 是否正确 （目前都不需要验证码）
     *
     * @return 正确返回true  否则返回false
     */
    public boolean validateCode() {

        // 目前都不需要验证码
        return true;
    }


    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }

    public String getCodeInSession() {
        return codeInSession;
    }

    public void setCodeInSession(String codeInSession) {
        this.codeInSession = codeInSession;
    }

    public String getValidCodeType() {
        return validCodeType;
    }

    public void setValidCodeType(String validCodeType) {
        this.validCodeType = validCodeType;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
