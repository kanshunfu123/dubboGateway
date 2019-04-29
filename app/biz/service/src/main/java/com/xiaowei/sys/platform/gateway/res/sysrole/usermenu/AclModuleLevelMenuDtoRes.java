package com.xiaowei.sys.platform.gateway.res.sysrole.usermenu;

import com.google.common.collect.Lists;
import com.xiaowei.sys.platform.core.facade.service.vo.sysrole.usermenu.AclMenuDtoVO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 李杰 on 2018/9/20.
 */
public class AclModuleLevelMenuDtoRes implements Serializable{
    private List<AclModuleLevelMenuDtoRes> aclModuleList = Lists.newArrayList();
    private List<AclMenuDtoRes> aclList = Lists.newArrayList();


    private Long id;

    private Long sysAclModuleParentId;


    private String sysAclModuleName;

    private String sysAclModuleUuid;

//    private String sysAclModuleLevel;


//    private Integer sysAclModuleSeq;

    public List<AclModuleLevelMenuDtoRes> getAclModuleList() {
        return aclModuleList;
    }

    public void setAclModuleList(List<AclModuleLevelMenuDtoRes> aclModuleList) {
        this.aclModuleList = aclModuleList;
    }

    public List<AclMenuDtoRes> getAclList() {
        return aclList;
    }

    public void setAclList(List<AclMenuDtoRes> aclList) {
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

}
