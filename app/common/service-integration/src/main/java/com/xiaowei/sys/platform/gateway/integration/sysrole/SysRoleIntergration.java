package com.xiaowei.sys.platform.gateway.integration.sysrole;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaowei.sys.platform.core.facade.service.facade.sysrole.SysRoleFacade;
import com.xiaowei.sys.platform.core.facade.service.req.sysrole.RoleMmmpGG;
import com.xiaowei.sys.platform.core.facade.service.req.sysrole.SysRolePageReq;
import com.xiaowei.sys.platform.core.facade.service.vo.sysrole.RoleListPageVO;
import com.xiaowei.sys.platform.core.facade.service.vo.sysrole.RoleMenuVO;
import com.xiaowei.sys.platform.core.facade.service.vo.sysrole.SysAllRoleVO;
import com.xiaowei.sys.platform.core.facade.service.vo.sysrole.roleacl.AclModuleLevelDtoVO;
import com.xiaowei.sys.platform.core.facade.service.vo.sysrole.roleacl.RoleAndAclVO;
import com.xiaowei.sys.platform.core.facade.service.vo.sysrole.usermenu.AclModuleLevelMenuDtoVO;
import com.xiaowei.sys.platform.core.facade.service.vo.sysrole.usermenu.UserIsSuperMainInfoVO;
import com.yeecli.component.common.model.Result;
import org.springframework.stereotype.Component;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaowei.sys.platform.core.facade.service.facade.sysrole.SysRoleFacade;
import com.xiaowei.sys.platform.core.facade.service.req.sysrole.AddSysRoleReq;
import com.xiaowei.sys.platform.core.facade.service.req.sysrole.EditSysRoleReq;
import com.xiaowei.sys.platform.core.facade.service.req.sysrole.SysRoleReq;
import com.yeecli.component.common.model.Result;
import org.springframework.stereotype.Component;

/**
 * Created by MOMO on 2018/9/17.
 */
@Component
public class SysRoleIntergration {
    @Reference(version = "1.0.0", check = false)
    private SysRoleFacade sysRoleFacade;

    /**
     * 分页查询 角色列表
     *
     * @param sysRolePageReq
     * @return
     */
    public Result<RoleListPageVO> roleListPage(SysRolePageReq sysRolePageReq) {
        return sysRoleFacade.roleListPage(sysRolePageReq);
    }

    /**
     * 该角色在整个权限的所拥有的权限
     *
     * @param roleId
     * @return
     */
    public Result<RoleAndAclVO> roleTree(String roleId, RoleMmmpGG roleMmmpGG) {
        Result<RoleAndAclVO> result = sysRoleFacade.roleTree(roleId, roleMmmpGG);
        return result;
    }

    /**
     * 根据用户id查询对应菜单
     *
     * @param userId
     * @return
     */
    public Result<List<AclModuleLevelDtoVO>> userAclTree(Long userId, RoleMmmpGG roleMmmpGG) {
        return sysRoleFacade.userAclTree(userId, roleMmmpGG);
    }

    /**
     * 编辑角色时的权限列表
     *
     * @return
     */
    public Result<List<AclModuleLevelDtoVO>> roleEDITTree(String s, RoleMmmpGG roleMmmpGG) {
        return sysRoleFacade.roleEDITTree(s, roleMmmpGG);
    }

    /**
     * 动态菜单按钮
     */
    public Result<List<AclModuleLevelMenuDtoVO>> dynamicMenu(UserIsSuperMainInfoVO userIsSuperMainInfoVO) {
        return sysRoleFacade.dynamicMenu(userIsSuperMainInfoVO);
    }

    public Result<Object> delByUuid(SysRoleReq sysRoleReq) {
        return sysRoleFacade.delByUuid(sysRoleReq);
    }

    public Result<Object> addSysRole(AddSysRoleReq addSysRoleReq) {
        return sysRoleFacade.addSysRole(addSysRoleReq);
    }

    public Result<Object> editSysRoles(EditSysRoleReq editSysRoleReq) {
        return sysRoleFacade.editSysRole(editSysRoleReq);
    }

    /**
     * 查询全部角色
     *
     * @return
     */
    public Result<List<SysAllRoleVO>> getAllRoleListByNoParam() {
        return sysRoleFacade.getAllRoleListByNoParam();
    }

    /**
     * 根据当前用户uuid 查询角色列表
     */
    public Result<List<RoleMenuVO>> getRoleListByRoleUUID(UserIsSuperMainInfoVO userIsSuperMainInfoVO) {
        return sysRoleFacade.getRoleListByRoleUUID(userIsSuperMainInfoVO);
    }
}
