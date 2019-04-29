package com.xiaowei.sys.platform.gateway.res.standard;

import com.xiaowei.sys.platform.core.facade.service.req.BaseReq;

import java.io.Serializable;

/**
 * 入参详情
 *
 * @author zhangwt
 */
public class StandardResAdd extends BaseReq implements Serializable {

    /**
     * id ID.
     */
    private Long id;
    /**
     * standardDetailid 标准明细ID.
     */
    private Long standardDetailid;
    /**
     * standardName 标准名称.
     */
    private String standardName;
    /**
     * standardTypeid 标准类型 ID关联scentype场景类型ID.
     */
    private String standardTypeid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStandardDetailid() {
        return standardDetailid;
    }

    public void setStandardDetailid(Long standardDetailid) {
        this.standardDetailid = standardDetailid;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    public String getStandardTypeid() {
        return standardTypeid;
    }

    public void setStandardTypeid(String standardTypeid) {
        this.standardTypeid = standardTypeid;
    }

}
