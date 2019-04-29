package com.xiaowei.sys.platform.gateway.req.details;


import com.xiaowei.sys.platform.gateway.req.BaseReq;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 入参详情
 *
 * @author zhangwt
 */
public class DetailsReq extends BaseReq implements Serializable {
    /**
     * id ID.
     */
    private Long id;
    /**
     * detailsId standard外键关联.
     */
    @NotBlank(message = "detailsId不能为空",groups = BaseReq.Add.class)
    private Long detailsId;
    /**
     * maxParm 最大范围数.
     */
    @NotBlank(message = "maxParm不能为空",groups = BaseReq.Add.class)
    private String maxParm;
    /**
     * minParm 最小范围数.
     */
    @NotBlank(message = "minParm不能为空",groups = BaseReq.Add.class)
    private String minParm;
    /**
     * detailName 项目.
     */
    @NotBlank(message = "detailName不能为空",groups = BaseReq.Add.class)
    private String detailName;
    /**
     * detailUuid 标准明细uuid.
     */
    private String detailUuid;

    /**
     * Set id ID.
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * Get id ID.
     *
     * @return the string
     */
    public Long getId(){
        return id;
    }

    /**
     * Set detailsId standard外键关联.
     */
    public void setDetailsId(Long detailsId){
        this.detailsId = detailsId;
    }

    /**
     * Get detailsId standard外键关联.
     *
     * @return the string
     */
    public Long getDetailsId(){
        return detailsId;
    }

    /**
     * Set maxParm 最大范围数.
     */
    public void setMaxParm(String maxParm){
        this.maxParm = maxParm;
    }

    /**
     * Get maxParm 最大范围数.
     *
     * @return the string
     */
    public String getMaxParm(){
        return maxParm;
    }

    /**
     * Set minParm 最小范围数.
     */
    public void setMinParm(String minParm){
        this.minParm = minParm;
    }

    /**
     * Get minParm 最小范围数.
     *
     * @return the string
     */
    public String getMinParm(){
        return minParm;
    }

    /**
     * Set detailName 项目.
     */
    public void setDetailName(String detailName){
        this.detailName = detailName;
    }

    /**
     * Get detailName 项目.
     *
     * @return the string
     */
    public String getDetailName(){
        return detailName;
    }

    /**
     * Set detailUuid 标准明细uuid.
     */
    public void setDetailUuid(String detailUuid){
        this.detailUuid = detailUuid;
    }

    /**
     * Get detailUuid 标准明细uuid.
     *
     * @return the string
     */
    public String getDetailUuid(){
        return detailUuid;
    }
}