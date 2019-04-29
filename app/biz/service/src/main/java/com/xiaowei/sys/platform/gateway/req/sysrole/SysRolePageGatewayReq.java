package com.xiaowei.sys.platform.gateway.req.sysrole;

import com.xiaowei.sys.platform.gateway.req.BaseReq;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * Created by MOMO on 2018/9/17.
 */
public class SysRolePageGatewayReq extends BaseReq implements Serializable{
    private String delFlag;//
    @Min(value = 1,message = "每页显示记录数不能为空，且只能为整数",groups = {Query.class})
    private Integer limit = 20;//每页显示记录数
    @Min(value = 1,message = "页码不能为空，且只能为整数",groups = {Query.class})
    private Integer currPageNo = 1;//当前页 页码

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
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
