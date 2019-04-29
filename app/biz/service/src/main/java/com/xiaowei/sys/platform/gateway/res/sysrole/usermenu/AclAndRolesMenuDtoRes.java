package com.xiaowei.sys.platform.gateway.res.sysrole.usermenu;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MOMO on 2018/9/25.
 */
public class AclAndRolesMenuDtoRes implements Serializable{
    private List<AclModuleLevelMenuDtoRes> acls;
    private List<RoleMenuRes> roles;

    public List<AclModuleLevelMenuDtoRes> getAcls() {
        return acls;
    }

    public void setAcls(List<AclModuleLevelMenuDtoRes> acls) {
        this.acls = acls;
    }

    public List<RoleMenuRes> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleMenuRes> roles) {
        this.roles = roles;
    }
}
