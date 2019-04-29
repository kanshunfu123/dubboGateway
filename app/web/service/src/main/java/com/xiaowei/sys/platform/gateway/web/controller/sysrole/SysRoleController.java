package com.xiaowei.sys.platform.gateway.web.controller.sysrole;

import com.xiaowei.sys.platform.core.facade.service.req.sysrole.SysRolePageReq;
import com.xiaowei.sys.platform.gateway.exception.ErrorEnum;
import com.xiaowei.sys.platform.gateway.req.BaseReq;
import com.xiaowei.sys.platform.gateway.req.RedisUser;
import com.xiaowei.sys.platform.gateway.req.sysrole.RoleEditReq;
import com.xiaowei.sys.platform.gateway.req.sysrole.RoleMmmpGGRes;
import com.xiaowei.sys.platform.gateway.req.sysrole.SysRolePageGatewayReq;
import com.xiaowei.sys.platform.gateway.service.sysrole.SysRoleService;
import com.xiaowei.sys.platform.gateway.web.controller.BaseController;
import com.xiaowei.sys.platform.gateway.web.model.res.JsonResVo;
import com.yeecli.component.common.model.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by MOMO on 2018/9/17.
 */
@RestController
@RequestMapping("/api/sys/role")
public class SysRoleController extends BaseController {

    private Logger logger = Logger.getLogger(SysRoleController.class);
    @Autowired
    private SysRoleService sysRoleService;

    @PostMapping("/list/v1")
    public JsonResVo list(@Validated(SysRolePageGatewayReq.Query.class) @RequestBody SysRolePageGatewayReq sysRolePageGatewayReq, HttpServletRequest request, HttpServletResponse response) {
        Result result = null;
        try {
            RedisUser baseReq = this.getUserInfoFromRedis(request, response);
            BeanUtils.copyProperties(baseReq, sysRolePageGatewayReq);
            result = sysRoleService.roleListPage(sysRolePageGatewayReq);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_ROLE_LIST_FAIL.getErrorCode(), ErrorEnum.ERROR_ROLE_LIST_FAIL.getErrorMessage());
        }
    }

    @PostMapping("/roleAclList/v1")
    public JsonResVo roleAclList(@Validated(RoleEditReq.Modify.class) @RequestBody RoleEditReq sysRoleUuid, RoleMmmpGGRes roleMmmpGGRes, HttpServletRequest request, HttpServletResponse response) {
        Result result = null;
        try {
            RedisUser baseReq = this.getUserInfoFromRedis(request, response);
            BeanUtils.copyProperties(baseReq, roleMmmpGGRes);
            result = sysRoleService.roleTree(sysRoleUuid.getSysRoleUuid(), roleMmmpGGRes);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_ROLE_ACL_TREE_FAIL.getErrorCode(), ErrorEnum.ERROR_ROLE_ACL_TREE_FAIL.getErrorMessage());
        }
    }

    @PostMapping("/addRoleAndAcls/v1")
    public JsonResVo addRoleAndAcls(RoleMmmpGGRes RoleMmmpGGRes, HttpServletRequest request, HttpServletResponse response) {
        Result result = null;
        try {
            RedisUser baseReq = this.getUserInfoFromRedis(request, response);
            BeanUtils.copyProperties(baseReq, RoleMmmpGGRes);
            result = sysRoleService.roleEDITTree("", RoleMmmpGGRes);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_WATING_PERMISSION_ACL_FAIL.getErrorCode(), ErrorEnum.ERROR_WATING_PERMISSION_ACL_FAIL.getErrorMessage());
        }
    }

    @PostMapping("/allList/v1")
    public JsonResVo addRoleAndAcls() {
        Result result = null;
        try {
            result = sysRoleService.getAllRoleListByNoParam();
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_ALL_ROLE_FAIL.getErrorCode(), ErrorEnum.ERROR_ALL_ROLE_FAIL.getErrorMessage());
        }
    }
}
