package com.xiaowei.sys.platform.gateway.req.sysuser;

import com.xiaowei.sys.platform.gateway.req.BaseReq;
import com.xiaowei.sys.platform.gateway.req.BaseUserReq;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 删除用户
 * Created by MOMO on 2018/9/14.
 */
public class SysUserDelGatewayReq extends BaseReq implements Serializable{
    /**
     * sysUserUuid 唯一，32位字符串，查询用.
     */
    @NotBlank(message = "用户uuid不能为空",groups = {Delete.class})
    private String sysUserUuid;

    public String getSysUserUuid() {
        return sysUserUuid;
    }

    public void setSysUserUuid(String sysUserUuid) {
        this.sysUserUuid = sysUserUuid;
    }
}
