package com.xiaowei.sys.platform.gateway.req.waterstandard;

import com.xiaowei.sys.platform.gateway.req.BaseReq;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author ZiBao
 * @date 2018/9/26
 */
public class StandardIdReq extends BaseReq implements Serializable {
    @NotBlank(message = "parentId的值不能为空", groups = Query.class)
    private String parentId;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
