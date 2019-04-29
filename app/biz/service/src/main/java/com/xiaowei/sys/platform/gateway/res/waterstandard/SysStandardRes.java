package com.xiaowei.sys.platform.gateway.res.waterstandard;

import java.io.Serializable;

/**
 * @author ZiBao
 * @date 2018/10/8
 */
public class SysStandardRes implements Serializable {
    /**
     * codeName 类型名称.
     */
    private String detailName;

    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }
}
