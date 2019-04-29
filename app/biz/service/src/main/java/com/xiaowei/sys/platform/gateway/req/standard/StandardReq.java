package com.xiaowei.sys.platform.gateway.req.standard;


import com.xiaowei.sys.platform.gateway.req.BaseReq;
import com.xiaowei.sys.platform.gateway.util.ParamsUtil;
import org.hibernate.validator.constraints.NotBlank;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 入参详情
 *
 * @author zhangwt
 */
public class StandardReq extends BaseReq implements Serializable {
    @NotBlank(message = "requestId不能为空",groups = Delete.class)
    private String requestId;
    private Integer limit = 20;//每页显示记录数
    private Integer currPageNo = 1;//当前页 页码

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getCurrPageNo() {
        return currPageNo;
    }

    public void setCurrPageNo(Integer currPageNo) {
        this.currPageNo = currPageNo;
    }

    @Override
    public String toString() {
        return "StandardReq{" +
                "requestId='" + requestId + '\'' +
                ", limit=" + limit +
                ", currPageNo=" + currPageNo +
                '}';
    }
}