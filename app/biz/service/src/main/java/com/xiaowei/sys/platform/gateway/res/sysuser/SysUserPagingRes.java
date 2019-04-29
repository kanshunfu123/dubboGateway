package com.xiaowei.sys.platform.gateway.res.sysuser;

import com.xiaowei.sys.platform.core.facade.service.vo.sysuser.SysRolesVO;

import java.util.List;

/**
 * Created by MOMO on 2018/9/13.
 */
public class SysUserPagingRes {
    private String sysUserUuid;
    private String sysUserName;
    private String sysDeptName;
    private String sysUserPhone;
    private String sysUserEmail;
    private String areaInfo;//拼接好省市区  上海、南京市、工业园区
    private List<SysRolesVO> roles;

    public String getSysUserUuid() {
        return sysUserUuid;
    }

    public void setSysUserUuid(String sysUserUuid) {
        this.sysUserUuid = sysUserUuid;
    }

    public String getSysUserName() {
        return sysUserName;
    }

    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName;
    }

    public String getSysDeptName() {
        return sysDeptName;
    }

    public void setSysDeptName(String sysDeptName) {
        this.sysDeptName = sysDeptName;
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

    public String getAreaInfo() {
        return areaInfo;
    }

    public void setAreaInfo(String areaInfo) {
        this.areaInfo = areaInfo;
    }

    public List<SysRolesVO> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRolesVO> roles) {
        this.roles = roles;
    }
}
