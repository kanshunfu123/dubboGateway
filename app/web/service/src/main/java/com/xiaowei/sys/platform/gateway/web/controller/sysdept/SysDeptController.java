package com.xiaowei.sys.platform.gateway.web.controller.sysdept;

import com.xiaowei.sys.platform.gateway.exception.ErrorEnum;
import com.xiaowei.sys.platform.gateway.req.RedisUser;
import com.xiaowei.sys.platform.gateway.req.sysdept.SysdeptDelReq;
import com.xiaowei.sys.platform.gateway.req.sysdept.SysdeptInsertReq;
import com.xiaowei.sys.platform.gateway.req.sysdept.SysdeptUpdateReq;
import com.xiaowei.sys.platform.gateway.req.sysuser.SysUserRedisGatewayReq;
import com.xiaowei.sys.platform.gateway.service.sysdept.SysdeptService;
import com.xiaowei.sys.platform.gateway.util.redisKey.RedisKeyEnum;
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
 * Created by MOMO on 2018/9/11.
 */
@RestController
@RequestMapping("/api/sys/dept")
public class SysDeptController extends BaseController {

    private Logger logger = Logger.getLogger(SysDeptController.class);
    @Autowired
    private SysdeptService sysdeptService;

    @PostMapping(path = "/deptTree/v1")
    public JsonResVo deptTree() {
        Result result = null;
        try {
            result = sysdeptService.deptTreeResult();
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            } else {
                return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_DEPT__TREE_INFO.getErrorCode(), ErrorEnum.ERROR_DEPT__TREE_INFO.getErrorMessage());
        }
    }

    @PostMapping(path = "/add/v1")
    public JsonResVo add(@Validated(SysdeptInsertReq.Add.class) @RequestBody SysdeptInsertReq sysdeptInsertReq, HttpServletRequest request, HttpServletResponse response) {
        Result result = null;
        try {
            RedisUser baseReq = this.getUserInfoFromRedis(request, response);
            BeanUtils.copyProperties(baseReq, sysdeptInsertReq);
            result = sysdeptService.insertDept(sysdeptInsertReq);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_DD_DEPT_INFO.getErrorCode(), ErrorEnum.ERROR_DD_DEPT_INFO.getErrorMessage());
        }
    }

    @PostMapping(path = "/edit/v1")
    public JsonResVo edit(@Validated(SysdeptUpdateReq.Modify.class) @RequestBody SysdeptUpdateReq sysdeptUpdateReq, HttpServletRequest request, HttpServletResponse response) {
        Result result = null;
        try {
            RedisUser baseReq = this.getUserInfoFromRedis(request, response);
            BeanUtils.copyProperties(baseReq, sysdeptUpdateReq);
            result = sysdeptService.updateDept(sysdeptUpdateReq);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_EDIT_DEPT_INFO.getErrorCode(), ErrorEnum.ERROR_EDIT_DEPT_INFO.getErrorMessage());
        }
    }

    @PostMapping(path = "/del/v1")
    public JsonResVo del(@Validated(SysdeptDelReq.Delete.class) @RequestBody SysdeptDelReq sysdeptDelReq, HttpServletRequest request, HttpServletResponse response) {
        Result result = null;
        try {
            RedisUser baseReq = this.getUserInfoFromRedis(request, response);
            BeanUtils.copyProperties(baseReq, sysdeptDelReq);
            result = sysdeptService.delDept(sysdeptDelReq);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_DELETE_DEPT_INFO.getErrorCode(), ErrorEnum.ERROR_DELETE_DEPT_INFO.getErrorMessage());
        }
    }

    @PostMapping(path = "/infoByUuid/v1")
    public JsonResVo infoByUuid(@Validated(SysdeptDelReq.Delete.class) @RequestBody SysdeptDelReq sysdeptDelReq) {
        Result result = null;
        try {
            result = sysdeptService.getDeptInfoByUuid(sysdeptDelReq.getSysDeptUuid());
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_DELETE_DEPT_INFO.getErrorCode(), ErrorEnum.ERROR_DELETE_DEPT_INFO.getErrorMessage());
        }
    }
}
