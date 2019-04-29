package com.xiaowei.sys.platform.gateway.web.controller;

import com.xiaowei.sys.platform.gateway.req.TestReq;
import com.xiaowei.sys.platform.gateway.req.sysuser.UserLoginReq;
import com.xiaowei.sys.platform.gateway.service.demo.DemoService;
import com.xiaowei.sys.platform.gateway.exception.ErrorEnum;
import com.xiaowei.sys.platform.gateway.req.demo.DemoReq;
import com.xiaowei.sys.platform.gateway.web.model.res.JsonResVo;
import com.yeecli.component.common.model.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author jim
 * @date 2018/1/12
 */
@RestController
@RequestMapping(value = "/test", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
public class TestController {

    protected final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private DemoService demoService;
  /*  @Autowired
    private SSORedisUtil ssoRedisUtil;*/

    @PostMapping("/validate")
    public Object validate(@Validated(TestReq.Modify.class) @RequestBody TestReq testReq){

        return testReq;
    }

    @RequestMapping(path = "/demo/v1")
    public JsonResVo demo(@RequestBody DemoReq req) {

        logger.info(req.getDemoId());
        logger.info(req.getDemoName());
        for (String str : req.getStrList()) {
            logger.info(str);
        }
        Result<String> demoResult = demoService.demo();
        if (demoResult.isSuccess()) {
            return JsonResVo.buildSuccess(demoResult.getData());
        }
        return JsonResVo.buildErrorResult(ErrorEnum.ERROR_BIZ_FAIL.getErrorCode(),
                ErrorEnum.ERROR_BIZ_FAIL.getErrorMessage());
    }

}
