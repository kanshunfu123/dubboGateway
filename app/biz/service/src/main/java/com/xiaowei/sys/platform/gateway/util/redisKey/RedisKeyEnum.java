package com.xiaowei.sys.platform.gateway.util.redisKey;

/**
 * Created by MOMO on 2018/9/14.
 */
public enum RedisKeyEnum {

    REDIS_KEY_IMG_UUID_CODE("verUUidCode:", 60*2, "图片验证码"),
    REDIS_KEY_IMG_UUID_CODE_HEADER("verUUidCode", 60, "图片验证码唯一uuid放在header里"),
    REDIS_KEY_IMG_BASE("baseImg", 60, "base64图片"),
    REDIS_KEY_IMG_TYPE("imgType", 60, "t图片类型"),
    REDIS_KEY_IMG_UUID_COOKIE_CODE_HEADER("Cookie", 60, "图片验证码唯一uuid放在header里"),
    REDIS_KEY_USER_INFO("userInfo:", 60 * 60*15, "用户登录"),
    REDIS_KEY_HEADER_INFO("X-Token", 60 * 60*15, "给前端的header"),
    ;
    private final String key;
    private final int expireTime;
    private final String keyInfo;


    RedisKeyEnum(String key, int expireTime, String keyInfo) {
        this.key = key;
        this.expireTime = expireTime;
        this.keyInfo = keyInfo;
    }


    public String getKey() {
        return key;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public String getKeyInfo() {
        return keyInfo;
    }
}
