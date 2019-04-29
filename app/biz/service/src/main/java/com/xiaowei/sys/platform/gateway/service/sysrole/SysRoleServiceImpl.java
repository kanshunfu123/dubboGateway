package com.xiaowei.sys.platform.gateway.service.sysrole;

import com.google.common.collect.Lists;
import com.xiaowei.sys.platform.core.facade.service.facade.sysuser.SysUserFacade;
import com.xiaowei.sys.platform.core.facade.service.req.sysrole.RoleMmmpGG;
import com.xiaowei.sys.platform.core.facade.service.req.sysrole.SysRolePageReq;
import com.xiaowei.sys.platform.core.facade.service.vo.sysrole.RoleListPageVO;
import com.xiaowei.sys.platform.core.facade.service.vo.sysrole.RoleMenuVO;
import com.xiaowei.sys.platform.core.facade.service.vo.sysrole.SysAllRoleVO;
import com.xiaowei.sys.platform.core.facade.service.vo.sysrole.SysRoleAndAclVO;
import com.xiaowei.sys.platform.core.facade.service.vo.sysrole.roleacl.AclModuleLevelDtoVO;
import com.xiaowei.sys.platform.core.facade.service.vo.sysrole.roleacl.RoleAndAclVO;
import com.xiaowei.sys.platform.core.facade.service.vo.sysrole.usermenu.AclModuleLevelMenuDtoVO;
import com.xiaowei.sys.platform.core.facade.service.vo.sysrole.usermenu.UserIsSuperMainInfoVO;
import com.xiaowei.sys.platform.gateway.integration.sysrole.SysRoleIntergration;
import com.xiaowei.sys.platform.gateway.integration.sysuser.SysUserIntegration;
import com.xiaowei.sys.platform.gateway.req.sysrole.RoleMmmpGGRes;
import com.xiaowei.sys.platform.gateway.req.sysrole.SysRolePageGatewayReq;
import com.xiaowei.sys.platform.gateway.res.sysrole.RoleListPageGatewayRes;
import com.xiaowei.sys.platform.gateway.res.sysrole.SysAllRoleRes;
import com.xiaowei.sys.platform.gateway.res.sysrole.SysRoleAndAclGatewayRes;
import com.xiaowei.sys.platform.gateway.res.sysrole.roleacl.AclModuleLevelDtoRes;
import com.xiaowei.sys.platform.gateway.res.sysrole.roleacl.RoleAndAclRes;
import com.xiaowei.sys.platform.gateway.res.sysrole.usermenu.*;
import com.yeecli.component.common.model.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import com.xiaowei.sys.platform.core.facade.service.req.sysrole.AddSysRoleReq;
import com.xiaowei.sys.platform.core.facade.service.req.sysrole.EditSysRoleReq;
import com.xiaowei.sys.platform.core.facade.service.req.sysrole.SysRoleReq;
import com.xiaowei.sys.platform.gateway.integration.sysrole.SysRoleIntergration;
import com.xiaowei.sys.platform.gateway.req.sysrole.SysRoleAddReq;
import com.xiaowei.sys.platform.gateway.req.sysrole.SysRoleDelReq;
import com.xiaowei.sys.platform.gateway.req.sysrole.SysRoleEditReq;
import com.yeecli.component.common.model.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by MOMO on 2018/9/17.
 */
@Component
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleIntergration sysRoleIntergration;
    @Autowired
    private SysUserIntegration sysUserIntegration;

    @Override
    public Result<RoleListPageGatewayRes> roleListPage(SysRolePageGatewayReq sysRolePageGatewayReq) {
        SysRolePageReq sysRolePageReq = new SysRolePageReq();
        if (sysRolePageGatewayReq != null) {
            BeanUtils.copyProperties(sysRolePageGatewayReq, sysRolePageReq);
        }
        RoleListPageGatewayRes roleListPageGatewayRes = new RoleListPageGatewayRes();
        List<SysRoleAndAclGatewayRes> sysRoleAndAclGatewayResaaa = new ArrayList<>();
        Result<RoleListPageVO> roleListPageVOResult = sysRoleIntergration.roleListPage(sysRolePageReq);
        if (roleListPageVOResult.isSuccess()) {
            List<SysRoleAndAclVO> sysRoleAndAclVOS = roleListPageVOResult.getData().getDatas();
            sysRoleAndAclVOS.forEach(sysRoleAndAclVO -> {
                if (!CollectionUtils.isEmpty(sysRoleAndAclVOS)) {
                    SysRoleAndAclGatewayRes sysRoleAndAclGatewayRes = new SysRoleAndAclGatewayRes();
                    BeanUtils.copyProperties(sysRoleAndAclVO, sysRoleAndAclGatewayRes);
                    sysRoleAndAclGatewayResaaa.add(sysRoleAndAclGatewayRes);
                }
            });
            BeanUtils.copyProperties(roleListPageVOResult.getData(),roleListPageGatewayRes);
        }
        roleListPageGatewayRes.setDatas(sysRoleAndAclGatewayResaaa);
        return Result.wrapSuccessfulResult(roleListPageGatewayRes);
    }

    @Override
    public Result<RoleAndAclRes> roleTree(String sysRoleId, RoleMmmpGGRes roleMmmpGGRes) {
        RoleMmmpGG roleMmmpGG = new RoleMmmpGG();
        if (null != roleMmmpGGRes) {
            BeanUtils.copyProperties(roleMmmpGGRes, roleMmmpGG);
        }
        Result result = sysRoleIntergration.roleTree(sysRoleId, roleMmmpGG);
        RoleAndAclRes roleAndAclRes = new RoleAndAclRes();
        if (result.isSuccess()) {
            RoleAndAclVO roleAndAclRes1 = (RoleAndAclVO) result.getData();
            if (null != roleAndAclRes1) {
                BeanUtils.copyProperties(roleAndAclRes1, roleAndAclRes);
            }
            return Result.wrapSuccessfulResult(roleAndAclRes);
            /*if (roleAndAclRes1!=null){
                List<AclModuleLevelDtoVO> aclModuleLevelDtoVOS =roleAndAclRes1.getAcls();
                if (!CollectionUtils.isEmpty(aclModuleLevelDtoVOS)) {
                    aclModuleLevelDtoVOS.forEach(aclModuleLevelDtoVO -> {
                        AclModuleLevelDtoRes aclModuleLevelDtoRes1 = new AclModuleLevelDtoRes();
                        BeanUtils.copyProperties(aclModuleLevelDtoVO, aclModuleLevelDtoRes1);
                        aclModuleLevelDtoRes.add(aclModuleLevelDtoRes1);
                    });
                    roleAndAclRes.setAcls(aclModuleLevelDtoRes);
                }
            }*/
        }
        return Result.wrapErrorResult(result.getCode(), result.getMessage());
    }

    @Override
    public Result<List<AclModuleLevelDtoRes>> userAclTree(Long userId, RoleMmmpGGRes roleMmmpGGRes) {
        RoleMmmpGG roleMmmpGG = new RoleMmmpGG();
        if (null != roleMmmpGGRes) {
            BeanUtils.copyProperties(roleMmmpGGRes, roleMmmpGG);
        }
        Result result = sysRoleIntergration.userAclTree(userId, roleMmmpGG);
        List<AclModuleLevelDtoRes> aclModuleLevelDtoRes = new ArrayList<>();
        if (result.isSuccess()) {
            List<AclModuleLevelDtoVO> aclModuleLevelDtoVOS = (List<AclModuleLevelDtoVO>) result.getData();
            if (!CollectionUtils.isEmpty(aclModuleLevelDtoVOS)) {
                aclModuleLevelDtoVOS.forEach(aclModuleLevelDtoVO -> {
                    AclModuleLevelDtoRes aclModuleLevelDtoRes1 = new AclModuleLevelDtoRes();
                    BeanUtils.copyProperties(aclModuleLevelDtoVO, aclModuleLevelDtoRes1);
                    aclModuleLevelDtoRes.add(aclModuleLevelDtoRes1);
                });
            }
        }
        return Result.wrapSuccessfulResult(aclModuleLevelDtoRes);
    }

    @Override
    public Result<List<AclModuleLevelDtoRes>> roleEDITTree(String s, RoleMmmpGGRes roleMmmpGGRes) {
        RoleMmmpGG roleMmmpGG = new RoleMmmpGG();
        if (null != roleMmmpGGRes) {
            BeanUtils.copyProperties(roleMmmpGGRes, roleMmmpGG);
        }
        Result<List<AclModuleLevelDtoVO>> result = sysRoleIntergration.roleEDITTree(s, roleMmmpGG);
        List<AclModuleLevelDtoRes> aclModuleLevelDtoRes = new ArrayList<>();
        if (result.isSuccess()) {
            List<AclModuleLevelDtoVO> aclModuleLevelDtoVOS = result.getData();
            if (!CollectionUtils.isEmpty(aclModuleLevelDtoVOS)) {
                aclModuleLevelDtoVOS.forEach(aclModuleLevelDtoVO -> {
                    AclModuleLevelDtoRes aclModuleLevelDtoRes1 = new AclModuleLevelDtoRes();
                    BeanUtils.copyProperties(aclModuleLevelDtoVO, aclModuleLevelDtoRes1);
                    aclModuleLevelDtoRes.add(aclModuleLevelDtoRes1);
                });
            }
        }
        return Result.wrapSuccessfulResult(aclModuleLevelDtoRes);
    }

/*    @Override
    public Result<List<AclModuleLevelMenuDtoRes>> dynamicMenu(UserIsSuperMainInfoRes userIsSuperMainInfoRes) {
        UserIsSuperMainInfoVO userIsSuperMainInfoVO = new UserIsSuperMainInfoVO();
        BeanUtils.copyProperties(userIsSuperMainInfoRes, userIsSuperMainInfoVO);
        Result<List<AclModuleLevelMenuDtoVO>> listResult = sysRoleIntergration.dynamicMenu(userIsSuperMainInfoVO);
        List<AclModuleLevelMenuDtoRes> aclModuleLevelMenuDtoRes = Lists.newArrayList();
        if (listResult.isSuccess()) {
            List<AclModuleLevelMenuDtoVO> aclModuleLevelMenuDtoVOS = listResult.getData();
            if (CollectionUtils.isEmpty(aclModuleLevelMenuDtoVOS)) {
                return Result.wrapErrorResult(listResult.getCode(), listResult.getMessage());
            }
            aclModuleLevelMenuDtoVOS.forEach(aclModuleLevelMenuDtoVO -> {
                AclModuleLevelMenuDtoRes res = new AclModuleLevelMenuDtoRes();
                BeanUtils.copyProperties(aclModuleLevelMenuDtoVO, res);
                aclModuleLevelMenuDtoRes.add(res);
            });
            return Result.wrapSuccessfulResult(aclModuleLevelMenuDtoRes);
        }
        return Result.wrapErrorResult(listResult.getCode(), listResult.getMessage());

    }*/

    @Override
    public Result<FinalDynamicMenu> finalDynamicMenu(UserIsSuperMainInfoRes userIsSuperMainInfoRes) {
        //最终结果集
        FinalDynamicMenu finalDynamicMenu=new FinalDynamicMenu();
        UserIsSuperMainInfoVO userIsSuperMainInfoVO = new UserIsSuperMainInfoVO();
        BeanUtils.copyProperties(userIsSuperMainInfoRes, userIsSuperMainInfoVO);
        //菜单列表
        Result<List<String>> acls = sysUserIntegration.finalDynamicMenu(userIsSuperMainInfoVO);
        //角色列表
        Result<List<RoleMenuVO>> roles = sysRoleIntergration.getRoleListByRoleUUID(userIsSuperMainInfoVO);
        if (!acls.isSuccess()) {
            return Result.wrapErrorResult(acls.getCode(), acls.getMessage());
        }
        if (!roles.isSuccess()) {
            return Result.wrapErrorResult(roles.getCode(), roles.getMessage());
        }
        List<String> aclsData = acls.getData();
        List<String> aclsaa=Lists.newArrayList();
        if (!CollectionUtils.isEmpty(aclsData)){
            aclsData.forEach(aclModuleLevelMenuDtoVO -> {
                aclsaa.add(aclModuleLevelMenuDtoVO);
            });
        }
        List<RoleMenuVO> rolesData=roles.getData();
        List<RoleMenuRes> roleMenuRes = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(rolesData)){
            rolesData.forEach(roleMenuVO -> {
                RoleMenuRes roleMenuRes1=new RoleMenuRes();
                BeanUtils.copyProperties(roleMenuVO,roleMenuRes1);
                roleMenuRes.add(roleMenuRes1);
            });
        }
        finalDynamicMenu.setAcls(aclsaa);
        finalDynamicMenu.setRoles(roleMenuRes);
        return Result.wrapSuccessfulResult(finalDynamicMenu);
    }

    @Override
    public Result<AclAndRolesMenuDtoRes> dynamicMenuAndRole(UserIsSuperMainInfoRes userIsSuperMainInfoRes) {
        //最终结果集
        AclAndRolesMenuDtoRes aclAndRolesMenuDtoRes = new AclAndRolesMenuDtoRes();

        UserIsSuperMainInfoVO userIsSuperMainInfoVO = new UserIsSuperMainInfoVO();
        BeanUtils.copyProperties(userIsSuperMainInfoRes, userIsSuperMainInfoVO);
        //菜单列表
        Result<List<AclModuleLevelMenuDtoVO>> acls = sysRoleIntergration.dynamicMenu(userIsSuperMainInfoVO);
        //角色列表
        Result<List<RoleMenuVO>> roles = sysRoleIntergration.getRoleListByRoleUUID(userIsSuperMainInfoVO);
        if (!acls.isSuccess()) {
            return Result.wrapErrorResult(acls.getCode(), acls.getMessage());
        }
        if (!roles.isSuccess()) {
            return Result.wrapErrorResult(roles.getCode(), roles.getMessage());
        }
        List<AclModuleLevelMenuDtoRes> aclModuleLevelMenuDtoRes = Lists.newArrayList();
        List<RoleMenuRes> roleMenuRes = Lists.newArrayList();
        List<AclModuleLevelMenuDtoVO> aclsData = acls.getData();
        if (!CollectionUtils.isEmpty(aclsData)){
            aclsData.forEach(aclModuleLevelMenuDtoVO -> {
                AclModuleLevelMenuDtoRes res = new AclModuleLevelMenuDtoRes();
                BeanUtils.copyProperties(aclModuleLevelMenuDtoVO, res);
                aclModuleLevelMenuDtoRes.add(res);
            });
        }
        List<RoleMenuVO> rolesData=roles.getData();
        if (!CollectionUtils.isEmpty(rolesData)){
            rolesData.forEach(roleMenuVO -> {
                RoleMenuRes roleMenuRes1=new RoleMenuRes();
                BeanUtils.copyProperties(roleMenuVO,roleMenuRes1);
                roleMenuRes.add(roleMenuRes1);
            });
        }
        aclAndRolesMenuDtoRes.setAcls(aclModuleLevelMenuDtoRes);
        aclAndRolesMenuDtoRes.setRoles(roleMenuRes);
        return Result.wrapSuccessfulResult(aclAndRolesMenuDtoRes);
    }

    @Override
    public Result<Object> delByUuid(SysRoleDelReq sysRoleDelReq) {
        SysRoleReq sysRoleReq = new SysRoleReq();
        BeanUtils.copyProperties(sysRoleDelReq, sysRoleReq);
        return sysRoleIntergration.delByUuid(sysRoleReq);
    }

    @Override
    public Result<Object> addSysRole(SysRoleAddReq sysRoleAddReq) {
        AddSysRoleReq addSysRoleReq = new AddSysRoleReq();
        BeanUtils.copyProperties(sysRoleAddReq, addSysRoleReq);
        return sysRoleIntergration.addSysRole(addSysRoleReq);
    }

    @Override
    public Result<Object> editSysRole(SysRoleEditReq sysRoleEditReq) {
        EditSysRoleReq editSysRoleReq = new EditSysRoleReq();
        BeanUtils.copyProperties(sysRoleEditReq, editSysRoleReq);
        return sysRoleIntergration.editSysRoles(editSysRoleReq);
    }

    @Override
    public Result<List<SysAllRoleRes>> getAllRoleListByNoParam() {
        Result<List<SysAllRoleVO>> listResult = sysRoleIntergration.getAllRoleListByNoParam();
        List<SysAllRoleRes> list = Lists.newArrayList();
        if (listResult.isSuccess()) {
            List<SysAllRoleVO> list1 = listResult.getData();
            if (!CollectionUtils.isEmpty(list1)) {
                list1.forEach(sysAllRoleVO -> {
                    SysAllRoleRes sysAllRoleRes = new SysAllRoleRes();
                    BeanUtils.copyProperties(sysAllRoleVO, sysAllRoleRes);
                    list.add(sysAllRoleRes);
                });
            }
            return Result.wrapSuccessfulResult(list);
        }
        return Result.wrapErrorResult(listResult.getCode(), listResult.getMessage());
    }
}
