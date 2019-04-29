package com.xiaowei.sys.platform.gateway.res.sysrole.roleacl;

import com.google.common.collect.Lists;
import com.xiaowei.sys.platform.core.common.dal.dataobject.SysAclModuleDO;
import com.xiaowei.sys.platform.core.facade.service.vo.sysrole.roleacl.AclDtoVO;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MOMO on 2018/9/18.
 */
public class AclModuleLevelDtoRes implements Serializable{
    private List<AclModuleLevelDtoRes> aclModuleList = Lists.newArrayList();
    private List<AclDtoRes> aclList = Lists.newArrayList();

    private Long id;

    private Long sysAclModuleParentId;


    private String sysAclModuleName;

    private String sysAclModuleUuid;

    private String sysAclModuleLevel;


    private Integer sysAclModuleSeq;

    public List<AclModuleLevelDtoRes> getAclModuleList() {
        return aclModuleList;
    }

    public void setAclModuleList(List<AclModuleLevelDtoRes> aclModuleList) {
        this.aclModuleList = aclModuleList;
    }

    public List<AclDtoRes> getAclList() {
        return aclList;
    }

    public void setAclList(List<AclDtoRes> aclList) {
        this.aclList = aclList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSysAclModuleParentId() {
        return sysAclModuleParentId;
    }

    public void setSysAclModuleParentId(Long sysAclModuleParentId) {
        this.sysAclModuleParentId = sysAclModuleParentId;
    }

    public String getSysAclModuleName() {
        return sysAclModuleName;
    }

    public void setSysAclModuleName(String sysAclModuleName) {
        this.sysAclModuleName = sysAclModuleName;
    }

    public String getSysAclModuleUuid() {
        return sysAclModuleUuid;
    }

    public void setSysAclModuleUuid(String sysAclModuleUuid) {
        this.sysAclModuleUuid = sysAclModuleUuid;
    }

    public String getSysAclModuleLevel() {
        return sysAclModuleLevel;
    }

    public void setSysAclModuleLevel(String sysAclModuleLevel) {
        this.sysAclModuleLevel = sysAclModuleLevel;
    }

    public Integer getSysAclModuleSeq() {
        return sysAclModuleSeq;
    }

    public void setSysAclModuleSeq(Integer sysAclModuleSeq) {
        this.sysAclModuleSeq = sysAclModuleSeq;
    }
}
