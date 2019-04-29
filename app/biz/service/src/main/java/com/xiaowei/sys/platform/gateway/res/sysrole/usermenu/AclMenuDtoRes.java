package com.xiaowei.sys.platform.gateway.res.sysrole.usermenu;

import com.xiaowei.sys.platform.core.common.dal.dataobject.SysAclDO;
import com.xiaowei.sys.platform.core.facade.service.vo.sysrole.roleacl.AclDtoVO;
import com.xiaowei.sys.platform.gateway.req.RedisUser;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 李杰 on 2018/9/20.
 */
public class AclMenuDtoRes extends RedisUser implements Serializable {// 是否要默认选中
    private boolean checked = false;

    // 是否有权限操作
    private boolean hasAcl = false;
    private String name;
    private String sysAclRouter;
    private String sysAclAction;

    private List<AclMenuDtoRes> buttons;//按钮

    public static AclDtoVO adapt(SysAclDO acl) {
        AclDtoVO dto = new AclDtoVO();
        BeanUtils.copyProperties(acl, dto);
        return dto;
    }

    /**
     * id 权限id.
     */
    private Long id;
    /**
     * sysAclModuleId 权限所在的权限模块id.
     */
    private Long sysAclModuleId;

    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
//    private String delFlag;

    /**
     * sysAclUrl 请求的url, 可以填正则表达式.
     */
    private String sysAclUrl;
    /**
     * sysAclCode 权限码.
     */
    private String sysAclCode;
    /**
     * sysAclName 权限名称.
     */
    private String sysAclName;
    /**
     * sysAclType 类型，1：菜单，2：按钮，3：其他.
     */
    private String sysAclType;
    /**
     * sysAclUuid 唯一，32位字符串，查询用.
     */
    private String sysAclUuid;

    /**
     * sysAclSeq 权限在当前模块下的顺序，由小到大.
     */
//    private Integer sysAclSeq;
    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isHasAcl() {
        return hasAcl;
    }

    public void setHasAcl(boolean hasAcl) {
        this.hasAcl = hasAcl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSysAclModuleId() {
        return sysAclModuleId;
    }

    public void setSysAclModuleId(Long sysAclModuleId) {
        this.sysAclModuleId = sysAclModuleId;
    }


    public String getSysAclUrl() {
        return sysAclUrl;
    }

    public void setSysAclUrl(String sysAclUrl) {
        this.sysAclUrl = sysAclUrl;
    }

    public String getSysAclCode() {
        return sysAclCode;
    }

    public void setSysAclCode(String sysAclCode) {
        this.sysAclCode = sysAclCode;
    }

    public String getSysAclName() {
        return sysAclName;
    }

    public void setSysAclName(String sysAclName) {
        this.sysAclName = sysAclName;
    }

    public String getSysAclType() {
        return sysAclType;
    }

    public void setSysAclType(String sysAclType) {
        this.sysAclType = sysAclType;
    }

    public String getSysAclUuid() {
        return sysAclUuid;
    }

    public void setSysAclUuid(String sysAclUuid) {
        this.sysAclUuid = sysAclUuid;
    }


    public List<AclMenuDtoRes> getButtons() {
        return buttons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setButtons(List<AclMenuDtoRes> buttons) {
        this.buttons = buttons;
    }

    public String getSysAclRouter() {
        return sysAclRouter;
    }

    public void setSysAclRouter(String sysAclRouter) {
        this.sysAclRouter = sysAclRouter;
    }

    public String getSysAclAction() {
        return sysAclAction;
    }

    public void setSysAclAction(String sysAclAction) {
        this.sysAclAction = sysAclAction;
    }
}