package com.xiaowei.sys.platform.gateway.res.sysrole;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MOMO on 2018/9/17.
 */
public class SysModulAclGatewayRes implements Serializable{
    private String sysAclModule;//模块名称
    private List<String> acl;//权限列表

    public String getSysAclModule() {
        return sysAclModule;
    }

    public void setSysAclModule(String sysAclModule) {
        this.sysAclModule = sysAclModule;
    }

    public List<String> getAcl() {
        return acl;
    }

    public void setAcl(List<String> acl) {
        this.acl = acl;
    }
}
