package com.xiaowei.sys.platform.gateway.req.dictionary;

import com.xiaowei.sys.platform.gateway.req.BaseReq;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Crested by kanshunfu on 2018/9/13
 */
public class DictionaryInsertReq extends BaseReq implements Serializable {
    /**
     * 代码名称
     */
    @NotBlank(message = "代码名称不能为空",groups = {Add.class,Modify.class})
    @Length(min = 1, max = 32, message = "区域名称需要在1~32字之间")
    private String codeName;
    /**
     * 代码值
     */
    @NotBlank(message = "代码值不能为空",groups = {Add.class,Modify.class})
    private String codeValue;
    /**
     * 上级id
     */
    @NotNull
    private  String parentId;
    /**
     * uuid
     */

    private String uuid;

    /**
     * 排列序号
     *
     * @param uuid
     */
    @Min(value = 0, message = "字典在当前层级下的顺序不能为空，只能是大于等于0的数字",groups = {Add.class,Modify.class})
    private Integer serialNumber;

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }
}
