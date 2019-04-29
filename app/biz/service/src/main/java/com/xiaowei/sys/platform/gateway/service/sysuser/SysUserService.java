package com.xiaowei.sys.platform.gateway.service.sysuser;

import com.xiaowei.sys.platform.gateway.req.sysrole.RoleMmmpGGRes;
import com.xiaowei.sys.platform.gateway.req.sysuser.*;
import com.xiaowei.sys.platform.gateway.res.sysuser.DeptUserRes;
import com.xiaowei.sys.platform.gateway.res.sysuser.SysUserRoleMenuRes;
import com.yeecli.component.common.model.Result;

import java.util.List;


/**
 * Created by MOMO on 2018/9/11.
 */
public interface SysUserService {

    /**
     * 用户登录
     * @param userLoginReq
     * @return
     */
    public Result loginByUsername(UserLoginReq userLoginReq);

    /**
     * 分页查询出  用户、用属于哪个部门、哪个区域、哪个角色
     *
     * @param userDeptAreaRolesPageGatewayReq
     * @return
     */
    public Result userDeptAreaRolesList(UserDeptAreaRolesPageGatewayReq userDeptAreaRolesPageGatewayReq);
    /**
     * 根据用户uuid查看 用户信息
     *
     * @param userIfoByUUIDGatewayReq
     * @return
     */
    public Result getUserInfiByUuid(UserIfoByUUIDGatewayReq userIfoByUUIDGatewayReq);

    /**
     * 新增用户
     *
     * @param sysUserInserGatewayReq
     * @return
     */
    public Result<Object> insertUser(SysUserInserGatewayReq sysUserInserGatewayReq);

    /**
     * 删除用户
     *
     * @param sysUserDelGatewayReq
     * @return
     */
    public Result<Object> delUser(SysUserDelGatewayReq sysUserDelGatewayReq);
    /**
     * 编辑用户
     *
     * @return
     */
    public Result<Object> updateUser(SysUserUpdateGatewayReq sysUserUpdateGatewayReq);

    /**
     * 判断用户是否有权限访问系统资源
     * @param url
     * @param userId
     * @param userPhone
     * @return
     */
    public Result<Boolean> hasUrlAcl(String url, Long userId, String userPhone);
    /**
     * 动态权限菜单列表 最终======》》兼容多个系统
     * @param userId
     * @return
     */
    public Result<SysUserRoleMenuRes> userRoleMenu(Long userId, RoleMmmpGGRes roleMmmpGGRes);
    /**
     * 根据用户的uuid 得到子部门的员工，最后一级部门只可以查看自己的数据
     * @param deptUserReq
     * @return
     */
    public Result<List<DeptUserRes>> getChildDeptUserssByUserUuid(DeptUserGatewayReq deptUserReq);
}
