package com.xiaowei.sys.platform.gateway.req.parameter;

import com.xiaowei.sys.platform.gateway.req.BaseReq;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Crested by kanshunfu on 2018/9/13
 */
public class ParameterInsertReq extends BaseReq implements Serializable {

    /**
     * parameterName 参数名称.
     */
    @NotBlank(message = "参数名称不能为空",groups = {Add.class,Modify.class})
    private String parameterName;
    /**
     * fieldValue 字段值.
     */
    private String fieldValue;
    /**
     * serialNumber 排列序号.
     */
    private Integer serialNumber;
    /**
     * parameterValue 参数值.
     */
    @NotBlank(message = "代码值不能为空",groups = {Add.class,Modify.class})
    private String parameterValue;
    /**
     * uuid
     */
    private String uuid;

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }


    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }



}
