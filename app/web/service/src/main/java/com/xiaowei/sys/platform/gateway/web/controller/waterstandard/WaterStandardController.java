package com.xiaowei.sys.platform.gateway.web.controller.waterstandard;

import com.xiaowei.sys.platform.gateway.exception.ErrorEnum;
import com.xiaowei.sys.platform.gateway.req.BaseReq;
import com.xiaowei.sys.platform.gateway.req.waterstandard.*;
import com.xiaowei.sys.platform.gateway.service.waterstandard.WaterStandardService;
import com.xiaowei.sys.platform.gateway.web.model.res.JsonResVo;
import com.yeecli.component.common.model.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WaterStandardController {
    private Logger logger = Logger.getLogger(WaterStandardController.class);
    @Autowired
    private WaterStandardService waterStandardService;

    @PostMapping("/standard/save/v1")
    public JsonResVo save(@Validated(StandardSaveReq.Add.class) @RequestBody StandardSaveReq standardSaveReq) {
        Result result = null;
        try {
            result = waterStandardService.save(standardSaveReq);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_ADD_WATER_STANDARD_INFO.getErrorCode(), ErrorEnum.ERROR_ADD_WATER_STANDARD_INFO.getErrorMessage());
        }
    }

    @PostMapping("/standard/findType/v1")
    public JsonResVo getDictionaryInfoByCodeValue(@Validated(StandardFindReq.Query.class) @RequestBody StandardFindReq standardFindReq) {
        Result result = null;
        try {
            result = waterStandardService.getDictionaryInfoByCodeValue(standardFindReq.getCodeValue());
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_SELECT_PARAMETER_FAIL.getErrorCode(), ErrorEnum.ERROR_SELECT_PARAMETER_FAIL.getErrorMessage());
        }
    }

    @PostMapping("/standard/findByType/v1")
    public JsonResVo getDictionaryInfoByParentId(@Validated(StandardIdReq.Query.class) @RequestBody StandardIdReq standardIdReq) {
        Result result = null;
        try {
            result = waterStandardService.getDictionaryInfoByParentId(standardIdReq.getParentId());
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_SELECT_PARAMETER_FAIL.getErrorCode(), ErrorEnum.ERROR_SELECT_PARAMETER_FAIL.getErrorMessage());
        }
    }
    @PostMapping("/standard/findStandardTypes/v1")
    public JsonResVo findAllStandardType(@Validated(StandardTypesReq.Query.class) @RequestBody StandardTypesReq standardTypesReq) {
        Result result = null;
        try {
            result = waterStandardService.findAllStandardType(standardTypesReq.getCodeValue());
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_SELECT_PARAMETER_FAIL.getErrorCode(), ErrorEnum.ERROR_SELECT_PARAMETER_FAIL.getErrorMessage());
        }
    }
    @PostMapping("/standard/findStandardByType/v1")
    public JsonResVo findByParentValue(@Validated(StandardByTypeReq.Query.class) @RequestBody StandardByTypeReq standardByTypeReq) {
        Result result = null;
        try {
            result = waterStandardService.findByParentValue(standardByTypeReq.getParentValue());
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_SELECT_PARAMETER_FAIL.getErrorCode(), ErrorEnum.ERROR_SELECT_PARAMETER_FAIL.getErrorMessage());
        }
    }
}
