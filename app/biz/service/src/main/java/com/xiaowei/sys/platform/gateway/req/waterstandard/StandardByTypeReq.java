package com.xiaowei.sys.platform.gateway.req.waterstandard;

import com.xiaowei.sys.platform.gateway.req.BaseReq;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @author ZiBao
 * @date 2018/10/8
 */
public class StandardByTypeReq extends BaseReq implements Serializable {
    @NotBlank(message = "parentValue不能为空",groups = Query.class)
    private String parentValue;

    public String getParentValue() {
        return parentValue;
    }

    public void setParentValue(String parentValue) {
        this.parentValue = parentValue;
    }
}
