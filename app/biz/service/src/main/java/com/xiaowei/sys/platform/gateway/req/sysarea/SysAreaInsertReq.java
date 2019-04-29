package com.xiaowei.sys.platform.gateway.req.sysarea;

import com.xiaowei.sys.platform.gateway.req.BaseReq;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class SysAreaInsertReq extends BaseReq implements Serializable {
    /**
     * 区域名称
     */
    @NotBlank(message = "区域名称不能为空")
    @Length(min = 1, max = 32, message = "区域名称需要在1~32字之间")
    private String sysAreaName;

    public String getSysAreaRemark() {
        return sysAreaRemark;
    }

    public void setSysAreaRemark(String sysAreaRemark) {
        this.sysAreaRemark = sysAreaRemark;
    }

    /**
     * 备注
     */
    private String sysAreaRemark;

    /**
     * 上级id
     */
   @NotNull
    private  Long sysAreaParentId;
    /**
     * uuid
     */
    public String getFatherUuid() {
        return fatherUuid;
    }

    public void setFatherUuid(String fatherUuid) {
        this.fatherUuid = fatherUuid;
    }


    /**
     * 父级id
     */
    private String fatherUuid;

    private String sysAreaUuid;

    public String getSysAreaName() {
        return sysAreaName;
    }

    public void setSysAreaName(String sysAreaName) {
        this.sysAreaName = sysAreaName;
    }

    public Long getSysAreaParentId() {
        return sysAreaParentId;
    }

    public void setSysAreaParentId(Long sysAreaParentId) {
        this.sysAreaParentId = sysAreaParentId;
    }

    public String getSysAreaUuid() {
        return sysAreaUuid;
    }

    public void setSysAreaUuid(String sysAreaUuid) {
        this.sysAreaUuid = sysAreaUuid;
    }

    public Integer getSysAreaSeq() {
        return sysAreaSeq;
    }

    public void setSysAreaSeq(Integer sysAreaSeq) {
        this.sysAreaSeq = sysAreaSeq;
    }
    /**
     * sysAreaSeq 排序.
     */
    private Integer sysAreaSeq;


    @Min(value = 0, message = "区域在当前层级下的顺序不能为空，只能是大于等于0的数字")


    public Long getSysAreaCodeNum() {
        return sysAreaCodeNum;
    }

    public void setSysAreaCodeNum(Long sysAreaCodeNum) {
        this.sysAreaCodeNum = sysAreaCodeNum;
    }

    private Long sysAreaCodeNum;





}
