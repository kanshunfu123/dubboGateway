package com.xiaowei.sys.platform.gateway.req.sysuser;

import com.xiaowei.sys.platform.gateway.req.BaseReq;
import com.xiaowei.sys.platform.gateway.req.BaseUserReq;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by MOMO on 2018/9/13.
 */
public class UserIfoByUUIDGatewayReq extends BaseReq implements Serializable {
    @NotBlank(message = "用户uuid不能为空",groups = {Query.class})
    private String sysUserUuid;
    private Integer limit = 20;//每页显示记录数
    private Integer currPageNo = 1;//当前页 页码


    public String getSysUserUuid() {
        return sysUserUuid;
    }
    public void setSysUserUuid(String sysUserUuid) {
        this.sysUserUuid = sysUserUuid;
    }
    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getCurrPageNo() {
        return currPageNo;
    }

    public void setCurrPageNo(Integer currPageNo) {
        this.currPageNo = currPageNo;
    }
}
