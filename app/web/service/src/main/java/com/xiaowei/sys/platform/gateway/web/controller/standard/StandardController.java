package com.xiaowei.sys.platform.gateway.web.controller.standard;

import com.xiaowei.sys.platform.core.facade.service.vo.standard.StandardEditVO;
import com.xiaowei.sys.platform.gateway.exception.ErrorEnum;
import com.xiaowei.sys.platform.gateway.req.standard.StandByNamePagingGatewayReq;
import com.xiaowei.sys.platform.gateway.req.standard.StandardReq;
import com.xiaowei.sys.platform.gateway.req.standard.StandardReqAdds;
import com.xiaowei.sys.platform.gateway.res.standard.StandByNamePagingRes;
import com.xiaowei.sys.platform.gateway.service.standard.StandardService;
import com.xiaowei.sys.platform.gateway.web.model.res.JsonResVo;
import com.yeecli.component.common.model.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jim
 * @date 2018/1/12
 */
@RestController
@RequestMapping(value = "/api", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
public class StandardController {

    protected final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private StandardService standardService;

    /**
     * @param standardReq
     * @return（编辑（通过UUid获取标准））
     */
    @RequestMapping(path = "/standard/edit/v1")
    public JsonResVo edit(@Validated(StandardReq.Delete.class) @RequestBody StandardReq standardReq) {
        Result<StandardEditVO> standardResult = null;
        try {
            standardResult = standardService.getstandardByUuid(standardReq);
            if (standardResult.isSuccess()) {
                return JsonResVo.buildSuccess(standardResult.getData());
            }
            return JsonResVo.buildErrorResult(standardResult.getCode(), standardResult.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_BIZ_FAIL.getErrorCode(),
                    ErrorEnum.ERROR_BIZ_FAIL.getErrorMessage());
        }
    }

    /**
     * @param standardReq
     * @return（删除）
     */
    @RequestMapping(path = "/standard/delete/v1")
    public JsonResVo delete(@Validated(StandardReq.Delete.class) @RequestBody StandardReq standardReq) {
        Result<Object> result = null;
        try {
            result = standardService.delete(standardReq);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_BIZ_FAIL.getErrorCode(),
                    ErrorEnum.ERROR_BIZ_FAIL.getErrorMessage());
        }
    }

    /**
     * @param reqAdd
     * @return（修改（通过UUid修改标准））
     */
    @RequestMapping(path = "/standard/update/v1")
    public JsonResVo update(@Validated(StandardReqAdds.Modify.class) @RequestBody StandardReqAdds reqAdd) {
        try {
            Result<String> standardResult = standardService.updatestandard(reqAdd);
            if (standardResult.isSuccess()) {
                return JsonResVo.buildSuccess(standardResult.getData());
            }
            return JsonResVo.buildErrorResult(standardResult.getCode(), standardResult.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_BIZ_FAIL.getErrorCode(),
                    ErrorEnum.ERROR_BIZ_FAIL.getErrorMessage());
        }
    }

    /*@RequestMapping(path = "/standard/pagefind/v1")
    public JsonResVo pagefind(@RequestBody StandardPageReqs standardPageReq) {
        Result<String> standardResult = standardService.findStandard(standardPageReq);
        if (standardResult.isSuccess()) {
            return JsonResVo.buildSuccess(standardResult.getData());
        }
        return JsonResVo.buildErrorResult(ErrorEnum.ERROR_BIZ_FAIL.getErrorCode(),
                ErrorEnum.ERROR_BIZ_FAIL.getErrorMessage());
    }*/
    @RequestMapping(path = "/standard/pagefind/v1")
    public JsonResVo pagefind(@RequestBody StandByNamePagingGatewayReq standByNamePagingReq) {
        Result<StandByNamePagingRes> standardResult = standardService.standByNamePaging(standByNamePagingReq);
        if (standardResult.isSuccess()) {
            return JsonResVo.buildSuccess(standardResult.getData());
        }
        return JsonResVo.buildErrorResult(ErrorEnum.ERROR_BIZ_FAIL.getErrorCode(),
                ErrorEnum.ERROR_BIZ_FAIL.getErrorMessage());
    }
}
