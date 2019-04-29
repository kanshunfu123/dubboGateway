package com.xiaowei.sys.platform.gateway.req.sysuser;

import com.xiaowei.sys.platform.gateway.req.BaseReq;
import com.xiaowei.sys.platform.gateway.req.BaseUserReq;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by MOMO on 2018/9/14.
 */
public class SysUserInserGatewayReq extends BaseReq implements Serializable {


    private String sysAreaUuid;
    @NotBlank(message = "部门uuid不能为空",groups = {Add.class})
    private String sysDeptUuid;
    private String delFlag;
    @NotBlank(message = "用户密码不能为空不能为空",groups = {Add.class})
    private String sysUserPwd;
    private List<String> roles;
    private List<String> areaUuidList;//区域列表

    public List<String> getAreaUuidList() {
        return areaUuidList;
    }

    public void setAreaUuidList(List<String> areaUuidList) {
        this.areaUuidList = areaUuidList;
    }

    public String getSysUserUuid() {
        return sysUserUuid;
    }

    public void setSysUserUuid(String sysUserUuid) {
        this.sysUserUuid = sysUserUuid;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getSysAreaUuid() {
        return sysAreaUuid;
    }

    public void setSysAreaUuid(String sysAreaUuid) {
        this.sysAreaUuid = sysAreaUuid;
    }

    public String getSysDeptUuid() {
        return sysDeptUuid;
    }

    public void setSysDeptUuid(String sysDeptUuid) {
        this.sysDeptUuid = sysDeptUuid;
    }

    public String getSysUserPwd() {
        return sysUserPwd;
    }

    public void setSysUserPwd(String sysUserPwd) {
        this.sysUserPwd = sysUserPwd;
    }

    public String getSysUserName() {
        return sysUserName;
    }

    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName;
    }

    public String getSysUserEmail() {
        return sysUserEmail;
    }

    public void setSysUserEmail(String sysUserEmail) {
        this.sysUserEmail = sysUserEmail;
    }

    public String getSysUserPhone() {
        return sysUserPhone;
    }

    public void setSysUserPhone(String sysUserPhone) {
        this.sysUserPhone = sysUserPhone;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    /**
     * sysUserName 姓名.
     */
    @NotBlank(message = "用户姓名不能为空",groups = {Add.class})
    private String sysUserName;
    /**
     * sysUserUuid 唯一，32位字符串，查询用.
     */
    private String sysUserUuid;
    /**
     * sysUserEmail 邮箱.
     */
    @NotBlank(message = "用户邮箱不能为空",groups = {Add.class})
    @Email(message = "邮箱格式不正确")
    private String sysUserEmail;
    /**
     * sysUserPhone 手机号.
     */
    @NotBlank(message = "用户手机号不能为空",groups = {Add.class})
    private String sysUserPhone;


}
