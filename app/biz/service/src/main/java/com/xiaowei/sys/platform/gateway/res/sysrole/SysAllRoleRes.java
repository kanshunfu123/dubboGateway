package com.xiaowei.sys.platform.gateway.res.sysrole;

import java.io.Serializable;

/**
 * Created by MOMO on 2018/9/25.
 */
public class SysAllRoleRes implements Serializable{
    private String sysRoleName;//角色名称
    private String sysRoleUuid;//角色uuid
    private String sysRoleType;//角色类型

    public String getSysRoleType() {
        return sysRoleType;
    }

    public void setSysRoleType(String sysRoleType) {
        this.sysRoleType = sysRoleType;
    }

    public String getSysRoleName() {
        return sysRoleName;
    }

    public void setSysRoleName(String sysRoleName) {
        this.sysRoleName = sysRoleName;
    }

    public String getSysRoleUuid() {
        return sysRoleUuid;
    }

    public void setSysRoleUuid(String sysRoleUuid) {
        this.sysRoleUuid = sysRoleUuid;
    }
}
