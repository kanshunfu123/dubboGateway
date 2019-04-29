package com.xiaowei.sys.platform.gateway.req.sysuser;

import com.xiaowei.sys.platform.gateway.req.BaseReq;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by MOMO on 2018/10/11.
 */
public class DeptUserGatewayReq extends BaseReq implements Serializable {
    @NotBlank(message = "userUuid ---> 用户uuid不能为空",groups = {Query.class})
    private String currentUserUuid;

    public String getCurrentUserUuid() {
        return currentUserUuid;
    }

    public void setCurrentUserUuid(String currentUserUuid) {
        this.currentUserUuid = currentUserUuid;
    }
}
