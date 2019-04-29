package com.xiaowei.sys.platform.gateway.res.standard;

import java.io.Serializable;

/**
 * @author: qianjin
 * @CreateDate: 2018/6/8 13:41
 */
public class StandardCommonRes implements Serializable {

    /**
     * 请求序列号
     */
    private String requestId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

}
