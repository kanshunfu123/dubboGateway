package com.xiaowei.sys.platform.gateway.req.dictionary;

import com.xiaowei.sys.platform.gateway.req.BaseReq;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by MOMO on 2018/10/16.
 */
public class DictAttrListGWReq extends BaseReq implements Serializable{
    private Integer limit=20;//每页显示记录数
    private Integer currPageNo=1;//当前页 页码
    /**
     * id 字典id.
     */
    private Long id;
    /**
     * uuid uuid.
     */
    @NotBlank(message = "uuid--字典uuid不能为空",groups = {Query.class})
    private String uuid;
    /**
     * codeName 代码名称.
     */
    private String codeName;

    /**
     * parentId 上级id.
     */
    private String parentId;

    /**
     * codeValue 代码值 关联sys_parameter_configuration表field_value.
     */
    private String codeValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
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
}
