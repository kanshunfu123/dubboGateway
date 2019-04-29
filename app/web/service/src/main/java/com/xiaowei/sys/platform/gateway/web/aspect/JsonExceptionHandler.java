package com.xiaowei.sys.platform.gateway.web.aspect;

import com.xiaowei.sys.platform.gateway.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 统一处理非格式化的异常错误
 * @author fuzl
 */
@ControllerAdvice
public class JsonExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(JsonExceptionHandler.class);

    private static Pattern pat = Pattern.compile("[\u4e00-\u9fa5]");


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map handler(HttpServletRequest req, Exception e) throws Exception {
        logger.error("JsonExceptionHandler 错误信息：{}", e);
        Map<String, Object> m = new HashMap<>();
        try {
            String msg = e.getMessage();
            if (e instanceof BizException) {
                m.put("errmsg", msg);
                m.put("code", ((BizException) e).getError().getErrorCode());
            } else if (e instanceof HttpMessageNotReadableException) {
                String resultMsg = isContainsChinese(msg) ? msg : "请求参数错误";
                m.put("errmsg", resultMsg);
                m.put("code", 20000);
            } else if (e instanceof MethodArgumentNotValidException) {
                m.put("code", 20000);
                StringBuffer sb = new StringBuffer();
                ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors().stream().forEach(x -> sb.append(x.getDefaultMessage() + ";\n"));
                m.put("errmsg", sb.toString());
            }else if (null != msg && isContainsChinese(msg)) {
                m.put("code", 20000);
                m.put("errmsg", msg);
            } else if (e instanceof BizException) {
                m.put("errmsg", msg);
                m.put("code", ((BizException) e).getError().getErrorCode());
            } else {
                m.put("code", 10000);
                m.put("errmsg", "系统错误");
            }
        } catch (Exception var3) {
            m.put("errmsg", var3.getMessage());
            m.put("code", 9999);
        }
        return m;
    }

    private static boolean isContainsChinese(String str) {
        Matcher matcher = pat.matcher(str);
        boolean flg = false;
        if (matcher.find()) {
            flg = true;
        }
        return flg;
    }
}
