package com.xiaowei.sys.platform.gateway.integration.demo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaowei.sys.platform.core.facade.service.facade.DemoFacade;
import com.yeecli.component.common.model.Result;
import org.springframework.stereotype.Component;

/**
 * Created by WingsChan on 2018/9/7.
 */
@Component
public class DemoIntegration {

    @Reference(version = "1.0.0", check = false)
    private DemoFacade demoFacade;

    public Result<String> demo() {
        return demoFacade.demo();
    }
}
