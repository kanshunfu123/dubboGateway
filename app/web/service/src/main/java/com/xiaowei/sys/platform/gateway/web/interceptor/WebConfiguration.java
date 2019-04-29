package com.xiaowei.sys.platform.gateway.web.interceptor;

import com.xiaowei.sys.platform.gateway.req.RedisUser;
import com.xiaowei.sys.platform.gateway.service.sysuser.SysUserService;
import com.xiaowei.sys.platform.gateway.util.SSORedisUtil;
import com.xiaowei.sys.platform.gateway.web.sso.inteceptor.ApiLoginRequiredInteceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 跨域处理
 * @author jim
 * @date 16/10/12
 */

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private SSORedisUtil<RedisUser> redisUtil;
    @Autowired
    private SysUserService sysUserService;
    /**
     * 跨域处理
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedHeaders("*").allowedMethods("*").allowedOrigins("*");
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiLoginRequiredInteceptor(redisUtil,sysUserService))
                .addPathPatterns("/**")
                .excludePathPatterns("/api/sys/user/login/v1")
                .excludePathPatterns("/api/sys/user/images/captcha/v1")
        ;
        super.addInterceptors(registry);
    }
}