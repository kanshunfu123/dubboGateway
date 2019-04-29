package com.xiaowei.sys.platform.gateway.web.sso.inteceptor;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.xiaowei.sys.platform.gateway.exception.ErrorEnum;
import com.xiaowei.sys.platform.gateway.req.RedisUser;
import com.xiaowei.sys.platform.gateway.service.sysuser.SysUserService;
import com.xiaowei.sys.platform.gateway.util.SSORedisUtil;
import com.xiaowei.sys.platform.gateway.util.redisKey.RedisKeyEnum;
import com.yeecli.component.common.model.Result;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * 实际上的 LoginRequired 注释拦截器
 * Created by pc1 on 2017-07-23.
 */
@Component
public class ApiLoginRequiredInteceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(ApiLoginRequiredInteceptor.class);

    private SSORedisUtil<RedisUser> redisUtil;
    @Autowired
    private SysUserService sysUserService;

    public ApiLoginRequiredInteceptor(SSORedisUtil<RedisUser> redisUtil,SysUserService sysUserService) {
        this.redisUtil = redisUtil;
        this.sysUserService=sysUserService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod method = null;
        if (handler instanceof HandlerMethod) {
            method = (HandlerMethod) handler;
//            if (method.getMethod().isAnnotationPresent(ApiLoginRequired.class)) {
            //从redis里面验证是否已经登录
            RedisUser userTo = null;
            String ticket;
            if (!request.getMethod().equalsIgnoreCase(HttpMethod.GET.name())) {
                ticket = request.getHeader("X-Token");
                // 兼容文件下载时GET请求
            } else {
                ticket = request.getParameter("X-Token");
            }

            String userKey = request.getHeader(RedisKeyEnum.REDIS_KEY_HEADER_INFO.getKey());
            String redisKey = RedisKeyEnum.REDIS_KEY_USER_INFO.getKey() + userKey;
            if (StringUtils.isNotBlank(ticket)) {
                Object o = redisUtil.get(redisKey);
                if (o != null) {
                    userTo = JSON.parseObject((String) o, new TypeReference<RedisUser>() {
                    });
                }
            }
            if (userTo == null) {
                HashMap<String, Object> jsonObjectResponse = new HashMap<String, Object>();

                jsonObjectResponse.put("code", ErrorEnum.RES_CODE_SYS_INVALID_TICKET.getErrorCode());
                jsonObjectResponse.put("errmsg", ErrorEnum.RES_CODE_SYS_INVALID_TICKET.getErrorMessage());

                String originHeader = request.getHeader("Origin");
                response.setHeader("Access-Control-Allow-Origin", originHeader);
                response.setHeader("Access-Control-Allow-Credentials", "true");
//                response.setStatus(401);//会话已失效
                response.setCharacterEncoding("utf-8");
                response.setHeader("Content-Type", "application/json; charset=utf-8");
                response.getWriter().print(JSONObject.toJSONString(jsonObjectResponse));
                ;
                return false;
            } else {
                redisUtil.expire(redisKey, RedisKeyEnum.REDIS_KEY_USER_INFO.getExpireTime());
                /*String servletPath = request.getServletPath();
                Result<Boolean> result = sysUserService.hasUrlAcl(servletPath, userTo.getUserId(), userTo.getUserPhone());
                if (result.isSuccess()) {
                    if (result.getData()){
                        redisUtil.expire(redisKey, RedisKeyEnum.REDIS_KEY_USER_INFO.getExpireTime());
                        return true;
                    }
                }else{//异常时
                    HashMap<String, Object> jsonObjectResponse = new HashMap<String, Object>();

                    jsonObjectResponse.put("code", result.getCode());
                    jsonObjectResponse.put("errmsg",result.getMessage());

                    String originHeader = request.getHeader("Origin");
                    response.setHeader("Access-Control-Allow-Origin", originHeader);
                    response.setHeader("Access-Control-Allow-Credentials", "true");
//                response.setStatus(401);//会话已失效
                    response.setCharacterEncoding("utf-8");
                    response.setHeader("Content-Type", "application/json; charset=utf-8");
                    response.getWriter().print(JSONObject.toJSONString(jsonObjectResponse));
                    return false;
                }
                HashMap<String, Object> jsonObjectResponse = new HashMap<String, Object>();

                jsonObjectResponse.put("code", ErrorEnum.ERROR_HAS_NO_ACL_FAIL.getErrorCode());
                jsonObjectResponse.put("errmsg", ErrorEnum.ERROR_HAS_NO_ACL_FAIL.getErrorMessage());

                String originHeader = request.getHeader("Origin");
                response.setHeader("Access-Control-Allow-Origin", originHeader);
                response.setHeader("Access-Control-Allow-Credentials", "true");
//                response.setStatus(401);//会话已失效
                response.setCharacterEncoding("utf-8");
                response.setHeader("Content-Type", "application/json; charset=utf-8");
                response.getWriter().print(JSONObject.toJSONString(jsonObjectResponse));
                return false;*/
            }
//            }
        }
        return super.preHandle(request, response, handler);
    }

    private String initService(HttpServletRequest request) {
        StringBuilder serviceBuild = new StringBuilder();

        serviceBuild.append(request.getScheme())
                .append("://").append(request.getServerName());
        if (request.getServerPort() != 80) {
            serviceBuild.append(":")
                    .append(request.getServerPort());
        }
        serviceBuild.append(request.getPathInfo());
        if (request.getQueryString() != null) {
            serviceBuild.append("?").append(request.getQueryString());
        }
        return serviceBuild.toString();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    public static String getCookie(Cookie[] cookies, String name) {
        if (null != cookies && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (c.getName().equals(name)) {
                    return c.getValue();
                }
            }
        }
        return null;
    }


    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (c.getName().equals(name)) {
                    return c.getValue();
                }
            }
        }
        return null;
    }
}
