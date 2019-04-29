package com.xiaowei.sys.platform.gateway.res.sysdept;


import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MOMO on 2018/9/11.
 */
public class SysDeptTreeRes implements Serializable {
    private List<SysDeptTreeRes> children;

    /**
     * label .
     */
    private String label;//部门名称
    /**
     * sysDeptUuid .
     */
    private String sysDeptUuid;
    /**
     * sysDeptCodeName 部门参数值.
     */
    private Long sysDeptCodeName;
    /**
     * sysDeptLevel .
     */
   // private String sysDeptLevel;


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSysDeptUuid() {
        return sysDeptUuid;
    }

    public void setSysDeptUuid(String sysDeptUuid) {
        this.sysDeptUuid = sysDeptUuid;
    }

    public Long getSysDeptCodeName() {
        return sysDeptCodeName;
    }

    public void setSysDeptCodeName(Long sysDeptCodeName) {
        this.sysDeptCodeName = sysDeptCodeName;
    }

    public List<SysDeptTreeRes> getChildren() {
        return children;
    }

    public void setChildren(List<SysDeptTreeRes> children) {
        this.children = children;
    }

}
