package com.xiaowei.sys.platform.gateway.req.sysuser;

import com.xiaowei.sys.platform.gateway.req.BaseReq;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * Created by MOMO on 2018/9/13.
 */
public class UserDeptAreaRolesPageGatewayReq extends BaseReq implements Serializable {
    private String sysUserName;//用户姓名
    private String sysDeptName;//部门名称
    @Min(value = 1,message = "每页显示记录数不能为空，且只能为整数",groups = {Query.class})
    private Integer limit = 20;//每页显示记录数
    @Min(value = 1,message = "页码不能为空，且只能为整数",groups = {Query.class})
    private Integer currPageNo = 1;//当前页 页码


    public String getSysUserName() {
        return sysUserName;
    }

    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName;
    }

    public String getSysDeptName() {
        return sysDeptName;
    }

    public void setSysDeptName(String sysDeptName) {
        this.sysDeptName = sysDeptName;
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
