package com.xiaowei.sys.platform.gateway.service.demo;

import com.xiaowei.sys.platform.gateway.integration.demo.DemoIntegration;
import com.yeecli.component.common.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by WingsChan on 2018/9/7.
 */
@Service
public class DemoService {

    @Autowired
    private DemoIntegration demoIntegration;

    public Result<String> demo() {

        return demoIntegration.demo();
    }
}
