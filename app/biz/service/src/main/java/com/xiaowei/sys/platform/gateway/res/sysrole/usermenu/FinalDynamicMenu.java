package com.xiaowei.sys.platform.gateway.res.sysrole.usermenu;

import java.util.List;

/**
 * Created by MOMO on 2018/9/28.
 */
public class FinalDynamicMenu {
    private List<String> acls;
    private List<RoleMenuRes> roles;

    public List<String> getAcls() {
        return acls;
    }

    public void setAcls(List<String> acls) {
        this.acls = acls;
    }

    public List<RoleMenuRes> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleMenuRes> roles) {
        this.roles = roles;
    }
}
