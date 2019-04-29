package com.xiaowei.sys.platform.gateway.req.sysuser;

import com.sun.xml.internal.rngom.parse.host.Base;
import com.xiaowei.sys.platform.gateway.req.BaseReq;

import java.io.Serializable;

/**
 * Created by MOMO on 2018/9/14.
 */
public class SysUserRedisGatewayReq extends BaseReq implements Serializable{
    private String sysUserName;//用户姓名
    private String sysUserUuid;//用户uuid
    private String X_token;

    public String getX_token() {
        return X_token;
    }

    public void setX_token(String x_token) {
        X_token = x_token;
    }

    public String getSysUserName() {
        return sysUserName;
    }

    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName;
    }

    public String getSysUserUuid() {
        return sysUserUuid;
    }

    public void setSysUserUuid(String sysUserUuid) {
        this.sysUserUuid = sysUserUuid;
    }
}
