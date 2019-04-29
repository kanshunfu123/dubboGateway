package com.xiaowei.sys.platform.gateway.req;

import java.io.Serializable;

/**
 * Created by MOMO on 2018/9/19.
 */
public class RedisUser implements Serializable {
    private Long userId;

    private String userName;//用户姓名
    private String userPhone;//用户手机号
    private String userUuid;//用户uuid
    private Long deptId;//部门id

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }
}
