package com.xiaowei.sys.platform.gateway.res.sysuser;

import com.xiaowei.sys.platform.core.facade.service.req.sysuser.SysUserRoleReq;
import com.xiaowei.sys.platform.gateway.res.sysrole.roleacl.AclModuleLevelDtoRes;

import java.util.List;

/**
 * Created by MOMO on 2018/9/18.
 */
public class SysUserRoleMenuRes {
    private List<SysUserRoleReq> roles;
    private List<AclModuleLevelDtoRes> acls;

    public List<SysUserRoleReq> getRoles() {
        return roles;
    }

    public void setRoles(List<SysUserRoleReq> roles) {
        this.roles = roles;
    }

    public List<AclModuleLevelDtoRes> getAcls() {
        return acls;
    }

    public void setAcls(List<AclModuleLevelDtoRes> acls) {
        this.acls = acls;
    }
}
