package com.xiaowei.sys.platform.gateway.service.sysrole;

import com.xiaowei.sys.platform.core.facade.service.vo.sysrole.RoleMenuVO;
import com.xiaowei.sys.platform.core.facade.service.vo.sysrole.SysAllRoleVO;
import com.xiaowei.sys.platform.core.facade.service.vo.sysrole.roleacl.AclModuleLevelDtoVO;
import com.xiaowei.sys.platform.core.facade.service.vo.sysrole.usermenu.UserIsSuperMainInfoVO;
import com.xiaowei.sys.platform.gateway.req.RedisUser;
import com.xiaowei.sys.platform.gateway.req.sysrole.RoleMmmpGGRes;
import com.xiaowei.sys.platform.gateway.req.sysrole.SysRolePageGatewayReq;
import com.xiaowei.sys.platform.gateway.res.sysrole.RoleListPageGatewayRes;
import com.xiaowei.sys.platform.gateway.res.sysrole.SysAllRoleRes;
import com.xiaowei.sys.platform.gateway.res.sysrole.roleacl.AclModuleLevelDtoRes;
import com.xiaowei.sys.platform.gateway.res.sysrole.roleacl.RoleAndAclRes;
import com.xiaowei.sys.platform.gateway.res.sysrole.usermenu.*;
import com.yeecli.component.common.model.Result;

import java.util.List;


import com.xiaowei.sys.platform.gateway.req.sysrole.SysRoleAddReq;
import com.xiaowei.sys.platform.gateway.req.sysrole.SysRoleDelReq;
import com.xiaowei.sys.platform.gateway.req.sysrole.SysRoleEditReq;
import com.yeecli.component.common.model.Result;

/**
 * Created by MOMO on 2018/9/17.
 */
public interface SysRoleService {
    /**
     * 分页查询 角色列表
     *
     * @param sysRolePageGatewayReq
     * @return
     */
    public Result<RoleListPageGatewayRes> roleListPage(SysRolePageGatewayReq sysRolePageGatewayReq);

    /**
     * 该角色在整个权限的所拥有的权限
     *
     * @param sysRoleId
     * @return
     */
    public Result<RoleAndAclRes> roleTree(String sysRoleId, RoleMmmpGGRes roleMmmpGGRes);

    /**
     * 根据用户id查询对应菜单
     *
     * @param userId
     * @return
     */
    public Result<List<AclModuleLevelDtoRes>> userAclTree(Long userId, RoleMmmpGGRes roleMmmpGGRes);

    /**
     * 编辑角色时的权限列表
     *
     * @return
     */
    public Result<List<AclModuleLevelDtoRes>> roleEDITTree(String s, RoleMmmpGGRes roleMmmpGGRes);

    /**
     * 动态菜单按钮=======》》》废弃
     */
   // public Result<List<AclModuleLevelMenuDtoRes>> dynamicMenu(UserIsSuperMainInfoRes userIsSuperMainInfoRes);
    /**
     * 动态菜单按钮  包含按钮  ----->>>  包含多个系统权限
     */
    public Result<AclAndRolesMenuDtoRes> dynamicMenuAndRole(UserIsSuperMainInfoRes userIsSuperMainInfoRes);

    /**
     * 动态菜单按钮 最终版
     * @param userIsSuperMainInfoRes
     * @return
     */
    public Result<FinalDynamicMenu> finalDynamicMenu(UserIsSuperMainInfoRes userIsSuperMainInfoRes);
    /**
     * 删除角色信息
     */
    public Result<Object> delByUuid(SysRoleDelReq sysRoleDelReq);

    /**
     * 添加角色信息
     */
    public Result<Object> addSysRole(SysRoleAddReq sysRoleAddReq);

    /*修改角色信息*/
    public Result<Object> editSysRole(SysRoleEditReq sysRoleEditReq);

    /**
     * 查询全部角色
     *
     * @return
     */
    public Result<List<SysAllRoleRes>> getAllRoleListByNoParam();

}
