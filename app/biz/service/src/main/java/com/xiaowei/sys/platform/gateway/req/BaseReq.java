package com.xiaowei.sys.platform.gateway.req;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.xiaowei.sys.platform.gateway.util.ParamsUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jim
 * @date 16/10/12
 */
public class BaseReq implements Serializable {


    private String accessToken;

    private String clientVersion;

    /**
     * A, I
     */
    @JsonProperty(value = "OSType")
    private String OSType;

    protected String platformBusinessNo;

    protected String sign;

    protected String signType;

    private Long userId;
    private String userName;//用户姓名
    private String userPhone;//用户手机号
    private String userUuid;//用户uuid

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    public String getOSType() {
        return OSType;
    }

    public void setOSType(String OSType) {
        this.OSType = OSType;
    }

    public String getPlatformBusinessNo() {
        return platformBusinessNo;
    }

    public void setPlatformBusinessNo(String platformBusinessNo) {
        this.platformBusinessNo = platformBusinessNo;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    /**
     * 检查参数是否合法
     *
     * @return
     */
    public void checkData() {
        Map<String, Object> checkParam = new HashMap<>();
        ParamsUtil.hasEmptyParamMap(checkParam);
    }

    public void trimData() {

    }

    public interface Add {

    }

    public interface Modify {

    }

    public interface Delete {

    }

    public interface Query {

    }

    public interface Status {

    }

    public interface Detail {

    }

    public interface Submit {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }
}
