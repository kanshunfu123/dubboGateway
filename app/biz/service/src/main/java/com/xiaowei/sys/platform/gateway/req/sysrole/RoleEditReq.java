package com.xiaowei.sys.platform.gateway.req.sysrole;

import com.xiaowei.sys.platform.gateway.req.BaseReq;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by MOMO on 2018/9/21.
 */
public class RoleEditReq extends BaseReq implements Serializable {
    @NotBlank(message = "角色uuid不能为空",groups = {Modify.class})
    private String sysRoleUuid;

    public String getSysRoleUuid() {
        return sysRoleUuid;
    }

    public void setSysRoleUuid(String sysRoleUuid) {
        this.sysRoleUuid = sysRoleUuid;
    }
}
