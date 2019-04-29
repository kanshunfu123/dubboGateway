package com.xiaowei.sys.platform.gateway.res.waterstandard;

import java.io.Serializable;

public class WaterStandardVo implements Serializable {
    /**
     * 水质名称
     */
    private String detailName;

    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }
}
