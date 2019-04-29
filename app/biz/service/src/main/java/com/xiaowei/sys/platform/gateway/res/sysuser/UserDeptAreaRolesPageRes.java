package com.xiaowei.sys.platform.gateway.res.sysuser;

import com.xiaowei.sys.platform.gateway.res.BasePage;

/**
 * Created by MOMO on 2018/9/13.
 */
public class UserDeptAreaRolesPageRes extends BasePage<SysUserPagingRes> {

    /**
     * sysDeptName .
     */
    private String sysDeptName;//部门名称
    /**
     * sysUserName 姓名.
     */
    private String sysUserName;//用户名称


    /**
     * Set sysDeptName .
     */
    public void setSysDeptName(String sysDeptName) {
        this.sysDeptName = sysDeptName;
    }

    /**
     * Get sysDeptName .
     *
     * @return the string
     */
    public String getSysDeptName() {
        return sysDeptName;
    }

    /**
     * Set sysUserName 姓名.
     */
    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName;
    }

    /**
     * Get sysUserName 姓名.
     *
     * @return the string
     */
    public String getSysUserName() {
        return sysUserName;
    }

}
