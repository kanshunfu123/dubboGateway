package com.xiaowei.sys.platform.gateway.req.sysrole;

import com.xiaowei.sys.platform.gateway.req.BaseReq;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

public class SysRoleDelReq extends BaseReq implements Serializable {

    @NotBlank(message = "角色的uuid不能为空",groups = {Delete.class})
    private String sysRoleUuid;

    public String getSysRoleUuid() {
        return sysRoleUuid;
    }

    public void setSysRoleUuid(String sysRoleUuid) {
        this.sysRoleUuid = sysRoleUuid;
    }

    @Override
    public String toString() {
        return "SysRoleDelReq{" +
                "sysRoleUuid='" + sysRoleUuid + '\'' +
                '}';
    }
}
