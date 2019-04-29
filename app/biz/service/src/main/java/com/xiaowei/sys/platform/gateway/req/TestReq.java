package com.xiaowei.sys.platform.gateway.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by MOMO on 2018/9/11.
 */
public class TestReq extends BaseReq{
    @NotBlank(message = "不能为空", groups = {Delete.class, Detail.class, Modify.class})
    private String testName;
    private String aaaName;

    public String getAaaName() {
        return aaaName;
    }

    public void setAaaName(String aaaName) {
        this.aaaName = aaaName;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}
