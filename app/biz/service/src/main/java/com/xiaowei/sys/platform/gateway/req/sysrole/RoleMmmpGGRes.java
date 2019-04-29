package com.xiaowei.sys.platform.gateway.req.sysrole;

import com.xiaowei.sys.platform.gateway.req.BaseReq;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by MOMO on 2018/9/19.
 */
public class RoleMmmpGGRes extends BaseReq implements Serializable{
    //菜单类型 1 系统管理 2资产管理
    @NotNull(message = "菜单类型不能为空",groups = {BaseReq.Query.class})
    private Integer sysAclModuleType;

    public Integer getSysAclModuleType() {
        return sysAclModuleType;
    }

    public void setSysAclModuleType(Integer sysAclModuleType) {
        this.sysAclModuleType = sysAclModuleType;
    }
}
