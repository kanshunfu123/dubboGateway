package com.xiaowei.sys.platform.gateway.web.controller.parameter;

import com.xiaowei.sys.platform.gateway.exception.ErrorEnum;
import com.xiaowei.sys.platform.gateway.req.RedisUser;
import com.xiaowei.sys.platform.gateway.req.parameter.ParameterInsertReq;
import com.xiaowei.sys.platform.gateway.req.parameter.ParameterListReq;
import com.xiaowei.sys.platform.gateway.req.parameter.ParameterReq;
import com.xiaowei.sys.platform.gateway.service.parameter.ParameterService;
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
 * Created by kanshunfu on 2018/9/11.
 */
@RestController
@RequestMapping("/api/sys/parameter/")
public class ParameterController extends BaseController {
    private final static Logger logger = Logger.getLogger(ParameterController.class);

    @Autowired
    private ParameterService parameterService;


    @PostMapping(path = "infoByfieldValue/v1")

    public JsonResVo infoinfoByfieldValue(@Validated @RequestBody ParameterListReq parameterListReq) {
        Result result = null;
        try {
            result = parameterService.getInfoByFieldValue(parameterListReq.getFieldValue());
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_SELECT_PARAMETER_FAIL.getErrorCode(),ErrorEnum.ERROR_SELECT_PARAMETER_FAIL.getErrorMessage());
        }
    }

    @PostMapping(path = "add/v1")
    public JsonResVo add(@Validated(ParameterInsertReq.Add.class) @RequestBody ParameterInsertReq parameterInsertReq, HttpServletRequest request, HttpServletResponse response) {
        try {
            RedisUser baseReq = this.getUserInfoFromRedis(request, response);
            BeanUtils.copyProperties(baseReq, parameterInsertReq);
            Result result = parameterService.saveParameter(parameterInsertReq);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            } else {
                return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_ADD_PARAMETER_FAIL.getErrorCode(),ErrorEnum.ERROR_ADD_PARAMETER_FAIL.getErrorMessage());
        }
    }

    @PostMapping(path = "edit/v1")
    public JsonResVo edit(@Validated(ParameterInsertReq.Add.class) @RequestBody ParameterInsertReq parameterInsertReq,HttpServletRequest request, HttpServletResponse response) {
        try {
            RedisUser baseReq = this.getUserInfoFromRedis(request, response);
            BeanUtils.copyProperties(baseReq, parameterInsertReq);
            Result result = parameterService.updateParameter(parameterInsertReq);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            } else {
                return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_EDIT_PARAMETER_FAIL.getErrorCode(),ErrorEnum.ERROR_EDIT_PARAMETER_FAIL.getErrorMessage());
        }
    }

    @PostMapping(path = "del/v1")
    public JsonResVo del(@Validated(ParameterReq.Delete.class) @RequestBody ParameterReq parameterReq,HttpServletRequest request, HttpServletResponse response) {
        try {
            RedisUser baseReq = this.getUserInfoFromRedis(request, response);
            BeanUtils.copyProperties(baseReq, parameterReq);
            Result result = parameterService.delParameter(parameterReq);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            } else {
                return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());

            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_DEL_PARAMETER_FAIL.getErrorCode(),ErrorEnum.ERROR_DEL_PARAMETER_FAIL.getErrorMessage());
        }
    }


}
