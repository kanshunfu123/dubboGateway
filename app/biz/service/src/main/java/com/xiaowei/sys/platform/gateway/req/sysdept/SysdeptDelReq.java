package com.xiaowei.sys.platform.gateway.req.sysdept;

import com.xiaowei.sys.platform.gateway.req.BaseReq;
import com.xiaowei.sys.platform.gateway.util.ParamsUtil;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MOMO on 2018/9/12.
 */
public class SysdeptDelReq extends BaseReq implements Serializable {
    @NotBlank(message = "部门uuid不能为空",groups = {Delete.class})
    private String sysDeptUuid;

    public String getSysDeptUuid() {
        return sysDeptUuid;
    }

    public void setSysDeptUuid(String sysDeptUuid) {
        this.sysDeptUuid = sysDeptUuid;
    }
    @Override
    public void checkData() {
        Map<String, Object> checkParam = new HashMap<>();
        checkParam.put("sysDeptUuid",sysDeptUuid);
        ParamsUtil.hasEmptyParamMap(checkParam);
    }
}
