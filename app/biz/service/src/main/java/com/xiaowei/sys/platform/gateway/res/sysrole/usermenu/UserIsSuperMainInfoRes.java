package com.xiaowei.sys.platform.gateway.res.sysrole.usermenu;

import com.xiaowei.sys.platform.gateway.req.BaseReq;
import com.xiaowei.sys.platform.gateway.req.RedisUser;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by 李杰 on 2018/9/20.
 */
public class UserIsSuperMainInfoRes extends BaseReq implements Serializable {

    //菜单类型 1 系统管理 2资产管理
    @NotNull(message = "菜单类型不能为空",groups = {BaseReq.Query.class,BaseReq.Submit.class})
    private Integer sysAclModuleType=1;

    public Integer getSysAclModuleType() {
        return sysAclModuleType;
    }

    public void setSysAclModuleType(Integer sysAclModuleType) {
        this.sysAclModuleType = sysAclModuleType;
    }
}
