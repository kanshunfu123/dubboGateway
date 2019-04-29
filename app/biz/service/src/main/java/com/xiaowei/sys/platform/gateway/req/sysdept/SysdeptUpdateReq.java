package com.xiaowei.sys.platform.gateway.req.sysdept;

import com.xiaowei.sys.platform.gateway.req.BaseReq;
import org.hibernate.validator.constraints.NotBlank;

import javax.print.attribute.standard.Media;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by MOMO on 2018/9/12.
 */
public class SysdeptUpdateReq extends BaseReq implements Serializable {
    /**
     * sysDeptName 部门名称.
     */
    @NotBlank(message = "部门名称不能为空",groups = {Add.class,Modify.class})
    private String sysDeptName;
    /**
     * sysDeptUuid 唯一，32位字符串，查询用.
     */
    @NotBlank(message = "部门uuid不能为空",groups = {Add.class,Modify.class})
    private String sysDeptUuid;
    /**
     * sysDeptParentId 上级部门id.
     */
    @NotNull(message = "上级部门参数值必填",groups = {Add.class,Modify.class} )
    private Long sysDeptParentId;
    /**
     * sysDeptRemark 备注.
     */
    private String sysDeptRemark;
    /**
     * sysDeptSeq 部门在当前层级下的顺序，由小到大.
     */
    @Min(value = 0,message = "部门在当前层级下的顺序不能为空，只能是大于等于0的数字",groups = {Add.class,Modify.class})
    private Integer sysDeptSeq;
    /**
     * sysDeptCodeName 部门参数值.
     */
    private Long sysDeptCodeName;
    private String fatherUuid;//父级id

    public String getSysDeptName() {
        return sysDeptName;
    }

    public void setSysDeptName(String sysDeptName) {
        this.sysDeptName = sysDeptName;
    }

    public String getSysDeptUuid() {
        return sysDeptUuid;
    }

    public void setSysDeptUuid(String sysDeptUuid) {
        this.sysDeptUuid = sysDeptUuid;
    }

    public String getSysDeptRemark() {
        return sysDeptRemark;
    }

    public void setSysDeptRemark(String sysDeptRemark) {
        this.sysDeptRemark = sysDeptRemark;
    }

    public Integer getSysDeptSeq() {
        return sysDeptSeq;
    }

    public void setSysDeptSeq(Integer sysDeptSeq) {
        this.sysDeptSeq = sysDeptSeq;
    }

    public String getFatherUuid() {
        return fatherUuid;
    }

    public Long getSysDeptCodeName() {
        return sysDeptCodeName;
    }

    public void setSysDeptCodeName(Long sysDeptCodeName) {
        this.sysDeptCodeName = sysDeptCodeName;
    }

    public Long getSysDeptParentId() {
        return sysDeptParentId;
    }

    public void setSysDeptParentId(Long sysDeptParentId) {
        this.sysDeptParentId = sysDeptParentId;
    }

    public void setFatherUuid(String fatherUuid) {
        this.fatherUuid = fatherUuid;
    }
}
