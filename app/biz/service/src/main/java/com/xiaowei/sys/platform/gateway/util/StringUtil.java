package com.xiaowei.sys.platform.gateway.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.UUID;

/**
 * title:
 * description:
 * project:
 * company:
 * copyright:
 *
 * @author xuys
 * @version 1.0.0
 * @data 2017年8月4日 上午10:09:59
 */
public class StringUtil {
    private final static Log LOGGER = LogFactory.getLog(StringUtil.class);


    /**
     * 生成UUID主键
     *
     * @return
     */
    public static String genUUID() {

        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    public static String setNoNull(Object o) {
        if (o == null)
            return "";
        return o.toString();
    }



}
