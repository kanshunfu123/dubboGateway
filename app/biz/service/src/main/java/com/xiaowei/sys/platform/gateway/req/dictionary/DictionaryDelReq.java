package com.xiaowei.sys.platform.gateway.req.dictionary;

import com.xiaowei.sys.platform.gateway.req.BaseReq;
import com.xiaowei.sys.platform.gateway.util.ParamsUtil;
import org.hibernate.validator.constraints.NotBlank;

import java.util.HashMap;
import java.util.Map;
/**
 * Crested by kanshunfu on 2018/9/13
 */
public class DictionaryDelReq extends BaseReq {
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @NotBlank(message = "字典uuid不能为空",groups = {Delete.class})
    private String uuid;


    @Override
    public void checkData() {
        Map<String, Object> checkParam = new HashMap<>();
        checkParam.put("uuid",uuid);
        ParamsUtil.hasEmptyParamMap(checkParam);
    }
}
