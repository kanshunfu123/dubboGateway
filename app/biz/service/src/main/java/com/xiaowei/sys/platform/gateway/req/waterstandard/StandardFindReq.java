package com.xiaowei.sys.platform.gateway.req.waterstandard;

import com.xiaowei.sys.platform.gateway.req.BaseReq;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class StandardFindReq extends BaseReq implements Serializable {
    @NotBlank(message = "codeValue的值不能为空", groups = Query.class)
    private String codeValue;

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }
}
