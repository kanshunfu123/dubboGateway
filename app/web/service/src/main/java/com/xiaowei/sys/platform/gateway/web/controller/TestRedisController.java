package com.xiaowei.sys.platform.gateway.web.controller;

import com.xiaowei.sys.platform.gateway.req.sysuser.UserLoginReq;
import com.xiaowei.sys.platform.gateway.util.SSORedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MOMO on 2018/9/12.
 */
@RestController
public class TestRedisController {
    @Autowired
    private SSORedisUtil ssoRedisUtil;

    @Autowired
    HttpServletResponse response;

    @RequestMapping("/testheader")
    @ResponseBody
    public Object test(){
        response.setHeader("aaa","sff地方");
        return "sss";
    }
    @RequestMapping("/testRedis")
    @ResponseBody
    public Object restRedis(){
        List<UserLoginReq> list=new ArrayList<>();
        UserLoginReq userLoginReq=new UserLoginReq();
        userLoginReq.setVerificationCode("菜单");
        userLoginReq.setSysUserName("李杰");
        list.add(userLoginReq);
        UserLoginReq userLoginReq2=new UserLoginReq();
        userLoginReq2.setVerificationCode("菜单1");
        userLoginReq2.setSysUserName("李杰1");
        list.add(userLoginReq2);
        List<UserLoginReq> list2=new ArrayList<>();
        ssoRedisUtil.setList("list",list);
        ssoRedisUtil.setList("list2",list2);
        ssoRedisUtil.expire("list",60);
        List<UserLoginReq> list1=  ssoRedisUtil.getList("list",UserLoginReq.class);
        for (UserLoginReq loginReq : list1) {
            System.out.println(loginReq.getVerificationCode());
        }
        ssoRedisUtil.setString("str","测试string类型是否乱码");
        System.out.println(ssoRedisUtil.get("str"));
        return ssoRedisUtil.get("test");
    }
}
