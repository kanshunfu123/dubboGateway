package com.xiaowei.sys.platform.gateway.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xiaowei.sys.platform.gateway.req.BaseReq;
import com.xiaowei.sys.platform.gateway.req.RedisUser;
import com.xiaowei.sys.platform.gateway.util.SSORedisUtil;
import com.xiaowei.sys.platform.gateway.util.redisKey.RedisKeyEnum;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by MOMO on 2018/9/19.
 */
public class BaseController {

    @Autowired
    private SSORedisUtil baseSsoRedisUtil;

    public RedisUser getUserInfoFromRedis(HttpServletRequest request, HttpServletResponse response) {
        String userKey = request.getHeader(RedisKeyEnum.REDIS_KEY_HEADER_INFO.getKey());
        Object o = baseSsoRedisUtil.get(RedisKeyEnum.REDIS_KEY_USER_INFO.getKey() + userKey);
        if (o == null) {
            return new RedisUser();
        } else {
            response.setHeader(RedisKeyEnum.REDIS_KEY_HEADER_INFO.getKey(),userKey);
            RedisUser baseReq = JSON.parseObject((String) o, new TypeReference<RedisUser>() {
            });
            return baseReq;
        }
    }

}
