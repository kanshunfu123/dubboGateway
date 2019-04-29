package com.xiaowei.sys.platform.gateway.req.parameter;

import com.xiaowei.sys.platform.gateway.req.BaseReq;
import com.xiaowei.sys.platform.gateway.util.ParamsUtil;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Crested by kanshunfu on 2018/9/13
 */
public class ParameterReq extends BaseReq implements Serializable {
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @NotBlank(message = "参数uuid不能为空",groups = {Delete.class})
    private String uuid;


    public void checkData() {
        Map<String, Object> checkParam = new HashMap<>();
        checkParam.put("uuid", uuid);
        ParamsUtil.hasEmptyParamMap(checkParam);
    }
}
