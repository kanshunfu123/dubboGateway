package com.xiaowei.sys.platform.gateway.req.standard;

import com.xiaowei.sys.platform.gateway.req.BaseReq;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
public class StandardPageReqs extends BaseReq implements Serializable {

    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private String delFlag;
    /**
     * standardName 标准名称.
     */
    private String standardName;
    private Integer limit = 20;//每页显示记录数
    private Integer currPageNo = 1;//当前页 页码

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
