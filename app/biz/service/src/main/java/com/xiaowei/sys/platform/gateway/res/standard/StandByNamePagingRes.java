package com.xiaowei.sys.platform.gateway.res.standard;

import com.sun.xml.internal.rngom.parse.host.Base;
import com.xiaowei.sys.platform.gateway.res.BasePage;

import java.io.Serializable;

/**
 * Created by MOMO on 2018/9/29.
 */
public class StandByNamePagingRes extends BasePage<BaseStandPPageRes> implements Serializable{
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private String delFlag;
    /**
     * standardName 水质标准名称.
     */
    private String standardName;

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }
}
