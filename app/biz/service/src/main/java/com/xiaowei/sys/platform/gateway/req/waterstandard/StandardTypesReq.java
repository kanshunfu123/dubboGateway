package com.xiaowei.sys.platform.gateway.req.waterstandard;

import com.xiaowei.sys.platform.gateway.req.BaseReq;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @author ZiBao
 * @date 2018/10/8
 */
public class StandardTypesReq extends BaseReq implements Serializable {
    /**
     * codeValue 代码值.
     */
    @NotBlank(message = "codeValue不能为空",groups = Query.class)
    private String codeValue;

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }
}
