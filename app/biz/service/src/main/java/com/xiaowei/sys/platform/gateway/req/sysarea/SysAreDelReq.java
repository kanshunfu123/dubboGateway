package com.xiaowei.sys.platform.gateway.req.sysarea;

import com.xiaowei.sys.platform.gateway.req.BaseReq;
import com.xiaowei.sys.platform.gateway.util.ParamsUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SysAreDelReq extends BaseReq implements Serializable {



    private String sysAreaUuid;


    public String getSysAreaUuid() {
        return sysAreaUuid;
    }

    public void setSysAreaUuid(String sysAreaUuid) {
        this.sysAreaUuid = sysAreaUuid;
    }
}
