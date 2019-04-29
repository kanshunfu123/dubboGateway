package com.xiaowei.sys.platform.gateway.req;

import java.io.Serializable;

/**
 * Created by MOMO on 2018/9/19.
 */
public class BaseUserReq implements Serializable{
    /**
     * id 主键.
     */
    private Long id;
    /**
     * sysUserName 姓名.
     */
    private String sysUserName;
    /**
     * sysUserUuid 唯一，32位字符串，查询用.
     */
    private String sysUserUuid;
}
