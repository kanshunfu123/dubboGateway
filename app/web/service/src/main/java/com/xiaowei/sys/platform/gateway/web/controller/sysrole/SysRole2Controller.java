package com.xiaowei.sys.platform.gateway.web.controller.sysrole;

import com.xiaowei.sys.platform.gateway.exception.ErrorEnum;
import com.xiaowei.sys.platform.gateway.req.sysrole.SysRoleAddReq;
import com.xiaowei.sys.platform.gateway.req.sysrole.SysRoleDelReq;
import com.xiaowei.sys.platform.gateway.req.sysrole.SysRoleEditReq;
import com.xiaowei.sys.platform.gateway.service.sysrole.SysRoleService;
import com.xiaowei.sys.platform.gateway.web.model.res.JsonResVo;
import com.yeecli.component.common.model.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MOMO on 2018/9/17.
 */
@RestController
@RequestMapping("/api/sys/role")
public class SysRole2Controller {
    private Logger logger = Logger.getLogger(SysRole2Controller.class);

    @Autowired
    private SysRoleService sysRoleService;

    @PostMapping(path = "/delByUuid/v1")
    public JsonResVo del(@Validated(SysRoleDelReq.Delete.class) @RequestBody SysRoleDelReq sysRoleDelReq) {
        Result result = null;
        try {
            result = sysRoleService.delByUuid(sysRoleDelReq);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_DELETE_DEPT_INFO.getErrorCode(), ErrorEnum.ERROR_DELETE_DEPT_INFO.getErrorMessage());
        }
    }

    @PostMapping(path = "/addSysRole/v1")
    public JsonResVo addSysRole(@Validated(SysRoleAddReq.Add.class) @RequestBody SysRoleAddReq sysRoleAddReq) {
        Result result = null;
        try {
            result = sysRoleService.addSysRole(sysRoleAddReq);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_ADD_ROLE_INFO.getErrorCode(), ErrorEnum.ERROR_ADD_ROLE_INFO.getErrorMessage());
        }
    }

    @PostMapping(path = "/editSysRole/v1")
    public JsonResVo editSysRole(@Validated(SysRoleEditReq.Modify.class) @RequestBody SysRoleEditReq sysRoleEditReq) {
        Result result = null;
        try {
            result = sysRoleService.editSysRole(sysRoleEditReq);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_EDIT_ROLE_INFO.getErrorCode(), ErrorEnum.ERROR_EDIT_ROLE_INFO.getErrorMessage());
        }
    }
}
