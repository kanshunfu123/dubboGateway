package com.xiaowei.sys.platform.gateway.req.sysuser;

import com.xiaowei.sys.platform.gateway.req.BaseReq;
import com.xiaowei.sys.platform.gateway.req.BaseUserReq;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by MOMO on 2018/9/11.
 */
public class UserLoginReq extends BaseReq implements Serializable {

    @NotBlank(message = "登录名不能为空",groups = {Submit.class})
    private String sysUserName;//接受前台的登录名
    private String sysUserPhone;//手机号
    private String sysUserEmail;//邮箱
    @NotBlank(message = "密码不能为空",groups = {Submit.class})
    private String sysUserPwd;//密码
    @NotBlank(message = "验证码不能为空",groups = {Submit.class})
    private String verificationCode;//验证码
    private String verUUidCode;//该验证码对应唯一一个用户

    public String getSysUserName() {
        return sysUserName;
    }

    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName;
    }

    public String getSysUserPhone() {
        return sysUserPhone;
    }

    public void setSysUserPhone(String sysUserPhone) {
        this.sysUserPhone = sysUserPhone;
    }

    public String getSysUserEmail() {
        return sysUserEmail;
    }

    public void setSysUserEmail(String sysUserEmail) {
        this.sysUserEmail = sysUserEmail;
    }

    public String getSysUserPwd() {
        return sysUserPwd;
    }

    public void setSysUserPwd(String sysUserPwd) {
        this.sysUserPwd = sysUserPwd;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getVerUUidCode() {
        return verUUidCode;
    }

    public void setVerUUidCode(String verUUidCode) {
        this.verUUidCode = verUUidCode;
    }
}
