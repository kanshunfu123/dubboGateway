package com.xiaowei.sys.platform.gateway.web.controller.sysarea;

import com.xiaowei.sys.platform.gateway.exception.ErrorEnum;
import com.xiaowei.sys.platform.gateway.req.RedisUser;
import com.xiaowei.sys.platform.gateway.req.dictionary.DictionaryInsertReq;
import com.xiaowei.sys.platform.gateway.req.sysarea.SysAreDelReq;
import com.xiaowei.sys.platform.gateway.req.sysarea.SysAreaInsertReq;
import com.xiaowei.sys.platform.gateway.req.sysarea.SysAreaParentIdGatewayReq;
import com.xiaowei.sys.platform.gateway.service.sysarea.SysAreaService;
import com.xiaowei.sys.platform.gateway.web.controller.BaseController;
import com.xiaowei.sys.platform.gateway.web.model.res.JsonResVo;
import com.yeecli.component.common.model.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by MOMO on 2018/9/17.
 */
@Controller
@RequestMapping("/api/sys/area")
public class SysAreaController extends BaseController {
    private Logger logger = Logger.getLogger(SysAreaController.class);
    @Autowired
    private SysAreaService sysAreaService;

    @PostMapping("/areaTree/v1")
    @ResponseBody
    public JsonResVo areaTree() {
        Result result = null;
        try {

            result = sysAreaService.areaTreeList();
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_AREA_TREE_FAIL.getErrorCode(), ErrorEnum.ERROR_AREA_TREE_FAIL.getErrorMessage());
        }
    }

    @PostMapping(path = "/add/v1")
    @ResponseBody
    public JsonResVo add(@Validated @RequestBody SysAreaInsertReq sysAreaInsertReq, HttpServletRequest request, HttpServletResponse response) {
        Result result=null;
        try {
            RedisUser baseReq = this.getUserInfoFromRedis(request, response);
            BeanUtils.copyProperties(baseReq, sysAreaInsertReq);
             result= sysAreaService.insertSysArea(sysAreaInsertReq);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_ADD_AREA_TREE_FAIL.getErrorCode(), ErrorEnum.ERROR_ADD_AREA_TREE_FAIL.getErrorMessage());
        }

    }

    @PostMapping(path = "/edit/v1")
    @ResponseBody
    public JsonResVo edit(@Validated @RequestBody SysAreaInsertReq sysAreaInsertReq,HttpServletRequest request, HttpServletResponse response) {
        try {
            RedisUser baseReq = this.getUserInfoFromRedis(request, response);
            BeanUtils.copyProperties(baseReq, sysAreaInsertReq);
            Result result = sysAreaService.editSysArea(sysAreaInsertReq);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            } else {
                return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_UPDATE_AREA_FAIL.getErrorCode(), ErrorEnum.ERROR_UPDATE_AREA_FAIL.getErrorMessage());
        }

    }

    @PostMapping(path = "/del/v1")
    @ResponseBody
    public JsonResVo del(@Validated @RequestBody SysAreDelReq sysAreDelReq,HttpServletRequest request, HttpServletResponse response) {
        try {
            RedisUser baseReq = this.getUserInfoFromRedis(request, response);
            BeanUtils.copyProperties(baseReq, sysAreDelReq);
            Result result = sysAreaService.delSysArea(sysAreDelReq);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            } else {
                return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_DEL_AREA_FAIL.getErrorCode(), ErrorEnum.ERROR_DEL_AREA_FAIL.getErrorMessage());
        }

    }
    @PostMapping(path = "/getareaListByParentId/v1")
    @ResponseBody
    public JsonResVo getareaListByParentId(@Validated(SysAreaParentIdGatewayReq.Query.class) @RequestBody SysAreaParentIdGatewayReq sysAreDelReq) {
        try {
            Result result = sysAreaService.getareaListByParentId(sysAreDelReq);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            } else {
                return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_CHILD_AREA_LIST_FAIL.getErrorCode(), ErrorEnum.ERROR_CHILD_AREA_LIST_FAIL.getErrorMessage());
        }

    }
}
