package com.xiaowei.sys.platform.gateway.res.sysarea;

import com.xiaowei.sys.platform.core.facade.service.vo.sysarea.SysAreaListVO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MOMO on 2018/9/17.
 */
public class SysAreaListReq implements Serializable{
    private long id;
    private String sysAreaName;//区域名称
    private String level;//层级
    private Integer sysAreaSeq;//排序
    private Long sysAreaCodeNum;//参数值 唯一
    private String sysAreaUuid;
    private String sysAreaRemark;//备注
    private Long sysAreaParentId;
    private List<SysAreaListVO> children;

    public Long getSysAreaParentId() {
        return sysAreaParentId;
    }

    public void setSysAreaParentId(Long sysAreaParentId) {
        this.sysAreaParentId = sysAreaParentId;
    }

    public String getSysAreaRemark() {
        return sysAreaRemark;
    }

    public void setSysAreaRemark(String sysAreaRemark) {
        this.sysAreaRemark = sysAreaRemark;
    }

    public Long getSysAreaCodeNum() {
        return sysAreaCodeNum;
    }

    public void setSysAreaCodeNum(Long sysAreaCodeNum) {
        this.sysAreaCodeNum = sysAreaCodeNum;
    }

    public String getSysAreaUuid() {
        return sysAreaUuid;
    }

    public void setSysAreaUuid(String sysAreaUuid) {
        this.sysAreaUuid = sysAreaUuid;
    }

    public String getSysAreaName() {
        return sysAreaName;
    }

    public void setSysAreaName(String sysAreaName) {
        this.sysAreaName = sysAreaName;
    }

    public Integer getSysAreaSeq() {
        return sysAreaSeq;
    }

    public void setSysAreaSeq(Integer sysAreaSeq) {
        this.sysAreaSeq = sysAreaSeq;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<SysAreaListVO> getChildren() {
        return children;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setChildren(List<SysAreaListVO> children) {
        this.children = children;
    }
}
