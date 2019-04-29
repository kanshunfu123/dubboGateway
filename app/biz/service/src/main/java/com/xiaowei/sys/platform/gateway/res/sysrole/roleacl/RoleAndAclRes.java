package com.xiaowei.sys.platform.gateway.res.sysrole.roleacl;

import java.util.List;

/**
 * Created by MOMO on 2018/9/18.
 */
public class RoleAndAclRes {
    private String sysRoleName;
    private String sysRoleUuid;
    private List<AclModuleLevelDtoRes> acls;

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

    public List<AclModuleLevelDtoRes> getAcls() {
        return acls;
    }

    public void setAcls(List<AclModuleLevelDtoRes> acls) {
        this.acls = acls;
    }
}
