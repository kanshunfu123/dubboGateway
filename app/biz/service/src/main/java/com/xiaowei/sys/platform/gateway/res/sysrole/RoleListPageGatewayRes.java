package com.xiaowei.sys.platform.gateway.res.sysrole;

import com.xiaowei.sys.platform.gateway.res.BasePage;

import java.io.Serializable;

/**
 * Created by MOMO on 2018/9/17.
 */
public class RoleListPageGatewayRes extends BasePage<SysRoleAndAclGatewayRes> implements Serializable {
    private String delFlag="0";//

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
