package com.xiaowei.sys.platform.gateway.web.controller.sysuser;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.xiaowei.sys.platform.core.facade.service.vo.sysuser.SysUserVO;
import com.xiaowei.sys.platform.gateway.exception.ErrorEnum;
import com.xiaowei.sys.platform.gateway.req.RedisUser;
import com.xiaowei.sys.platform.gateway.req.sysrole.RoleMmmpGGRes;
import com.xiaowei.sys.platform.gateway.req.sysuser.*;
import com.xiaowei.sys.platform.gateway.res.sysrole.usermenu.UserIsSuperMainInfoRes;
import com.xiaowei.sys.platform.gateway.service.sysrole.SysRoleService;
import com.xiaowei.sys.platform.gateway.service.sysuser.SysUserService;
import com.xiaowei.sys.platform.gateway.util.SSORedisUtil;
import com.xiaowei.sys.platform.gateway.util.StringUtil;
import com.xiaowei.sys.platform.gateway.util.redisKey.RedisKeyEnum;
import com.xiaowei.sys.platform.gateway.web.controller.BaseController;
import com.xiaowei.sys.platform.gateway.web.model.res.JsonResVo;
import com.xiaowei.sys.platform.gateway.web.utils.captcha.SpecCaptcha;
import com.yeecli.component.common.model.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by MOMO on 2018/9/11.
 */
@RestController
@RequestMapping("/api/sys/user")
public class SysUserController extends BaseController {

    private Logger logger = Logger.getLogger(SysUserController.class);
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SSORedisUtil ssoRedisUtil;
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 生成图片验证码
     * https://github.com/whvcse/EasyCaptcha
     *
     * @throws Exception
     */
    /*@RequestMapping(path = "/images/captcha/v1")
    public void captcha(HttpServletRequest request1,  HttpServletRequest request, HttpServletResponse response) throws Exception {

        String key = StringUtil.genUUID();
        response.setHeader(RedisKeyEnum.REDIS_KEY_IMG_UUID_CODE_HEADER.getKey(), key);
        CookieUtils.setCookie(request, response, RedisKeyEnum.REDIS_KEY_IMG_UUID_CODE_HEADER.getKey(), key);
        String verificationCode = CaptchaUtil.out(request1, response);



        ssoRedisUtil.setString(RedisKeyEnum.REDIS_KEY_IMG_UUID_CODE.getKey() + key, verificationCode);
        ssoRedisUtil.expire(RedisKeyEnum.REDIS_KEY_IMG_UUID_CODE.getKey() + key, RedisKeyEnum.REDIS_KEY_IMG_UUID_CODE.getExpireTime());
    }*/
    @RequestMapping(path = "/images/captcha/v1")
    public JsonResVo captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
           /*ValidateCodeUtil.Validate v = ValidateCodeUtil.getRandomCode();

           String verUUidCode = v.getBase64Str();
           String verificationCode = v.getValue();
           String key = StringUtil.genUUID();*/
            SpecCaptcha specCaptcha = new SpecCaptcha();
            String verUUidCode = specCaptcha.outaaaa(null);
            String key = StringUtil.genUUID();
            String verificationCode = specCaptcha.text();
            ssoRedisUtil.setString(RedisKeyEnum.REDIS_KEY_IMG_UUID_CODE.getKey() + key, verificationCode);
            ssoRedisUtil.expire(RedisKeyEnum.REDIS_KEY_IMG_UUID_CODE.getKey() + key, RedisKeyEnum.REDIS_KEY_IMG_UUID_CODE.getExpireTime());

            Map<String, Object> map = Maps.newHashMap();
            //base64
            map.put(RedisKeyEnum.REDIS_KEY_IMG_BASE.getKey(), verUUidCode);
            //一个verUUidCode对应当前请求用户
            map.put(RedisKeyEnum.REDIS_KEY_IMG_UUID_CODE_HEADER.getKey(), key);
            map.put(RedisKeyEnum.REDIS_KEY_IMG_TYPE.getKey(), "data:image/png;base64,");
            return JsonResVo.buildSuccess(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_IMG_CODE_FAIL.getErrorCode(), ErrorEnum.ERROR_IMG_CODE_FAIL.getErrorMessage());
        }
    }

    @PostMapping("/safeOut/v1")
    public JsonResVo safeOut(HttpServletRequest request) {
        try {
            String userKey = request.getHeader(RedisKeyEnum.REDIS_KEY_HEADER_INFO.getKey());
            String key = RedisKeyEnum.REDIS_KEY_USER_INFO.getKey() + userKey;
            if (ssoRedisUtil.del(key)) {
                return JsonResVo.buildSuccess();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_USER_SAFE_OUT_EXCEPTION_FAIL.getErrorCode(), ErrorEnum.ERROR_USER_SAFE_OUT_EXCEPTION_FAIL.getErrorMessage());
        }
        return JsonResVo.buildErrorResult(ErrorEnum.ERROR_USER_SAFE_OUT_FAIL.getErrorCode(), ErrorEnum.ERROR_USER_SAFE_OUT_FAIL.getErrorMessage());
    }

    /**
     * 用户登录
     *
     * @param userLoginReq
     * @return
     */
    @PostMapping(path = "/login/v1")
    public JsonResVo userLogin(@Validated(UserLoginReq.Submit.class) @RequestBody UserLoginReq userLoginReq, HttpServletRequest request, HttpServletResponse response) {
        try {

            //获取cookie    verUUidCode=a12e9105844148c789f66bb26b0594d3
           /* String verUUidCode = CookieUtils.getCookieValue(request, "Cookie");
            String code = "";
            if (StringUtils.isNotBlank(verUUidCode)) {
                code = verUUidCode.split("=")[1];
            }else{
                throw new Exception();
            }*/

            String verUUidCode = request.getHeader(RedisKeyEnum.REDIS_KEY_IMG_UUID_CODE_HEADER.getKey());
            userLoginReq.setVerUUidCode(verUUidCode);
            Result result = sysUserService.loginByUsername(userLoginReq);
            if (result.isSuccess()) {

                SysUserVO sysUserRes = (SysUserVO) result.getData();
                //页面需要的
                SysUserRedisGatewayReq sysUserRedisGatewayReq = new SysUserRedisGatewayReq();
                sysUserRedisGatewayReq.setSysUserUuid(sysUserRes.getSysUserUuid());
                sysUserRedisGatewayReq.setSysUserName(sysUserRes.getSysUserName());

                //存入redis
                RedisUser aaa = new RedisUser();
                aaa.setUserId(sysUserRes.getId());
                aaa.setUserName(sysUserRes.getSysUserName());
                aaa.setUserUuid(sysUserRes.getSysUserUuid());
                aaa.setUserPhone(sysUserRes.getSysUserPhone());
                aaa.setDeptId(sysUserRes.getDeptid());
                String s = JSONObject.toJSONString(aaa);
                String redisUserKey = StringUtil.genUUID();
                //token
                sysUserRedisGatewayReq.setX_token(redisUserKey);
                ssoRedisUtil.setString(RedisKeyEnum.REDIS_KEY_USER_INFO.getKey() + redisUserKey, s);
                ssoRedisUtil.expire(RedisKeyEnum.REDIS_KEY_USER_INFO.getKey() + redisUserKey, RedisKeyEnum.REDIS_KEY_USER_INFO.getExpireTime());

                response.setHeader(RedisKeyEnum.REDIS_KEY_HEADER_INFO.getKey(), redisUserKey);
                return JsonResVo.buildSuccess(sysUserRedisGatewayReq);
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_USER_LOGIN.getErrorCode(), ErrorEnum.ERROR_USER_LOGIN.getErrorMessage());
        }
    }

    /**
     * 分页查询出  用户、用属于哪个部门、哪个区域、哪个角色
     *
     * @param userDeptAreaRolesPageGatewayReq
     * @return
     */
    @PostMapping(path = "/list/v1")
    public JsonResVo list(@Validated(UserDeptAreaRolesPageGatewayReq.Query.class) @RequestBody UserDeptAreaRolesPageGatewayReq userDeptAreaRolesPageGatewayReq, HttpServletRequest request, HttpServletResponse response) {
        Result result = null;
        try {
            RedisUser baseReq = this.getUserInfoFromRedis(request, response);
            BeanUtils.copyProperties(baseReq, userDeptAreaRolesPageGatewayReq);
            result = sysUserService.userDeptAreaRolesList(userDeptAreaRolesPageGatewayReq);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_USER_INFO_LIST.getErrorCode(), ErrorEnum.ERROR_USER_INFO_LIST.getErrorMessage());
        }
    }

    @PostMapping("/infoByUuid/v1")
    public JsonResVo infoByUuid(@Validated(UserIfoByUUIDGatewayReq.Query.class) @RequestBody UserIfoByUUIDGatewayReq UserIfoByUUIDGatewayReq) {
        Result result = null;
        try {
            result = sysUserService.getUserInfiByUuid(UserIfoByUUIDGatewayReq);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_USER_INFO_LIST.getErrorCode(), ErrorEnum.ERROR_USER_INFO_LIST.getErrorMessage());
        }
    }

    @PostMapping(path = "/add/v1")
    public JsonResVo add(@Validated(SysUserInserGatewayReq.Add.class) @RequestBody SysUserInserGatewayReq sysUserInserGatewayReq, HttpServletRequest request, HttpServletResponse response) {
        Result result = null;
        try {
            RedisUser baseReq = this.getUserInfoFromRedis(request, response);
            BeanUtils.copyProperties(baseReq, sysUserInserGatewayReq);
            result = sysUserService.insertUser(sysUserInserGatewayReq);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_ADD_USER_INFO.getErrorCode(), ErrorEnum.ERROR_ADD_USER_INFO.getErrorMessage());
        }
    }

    @PostMapping(path = "/del/v1")
    public JsonResVo del(@Validated(SysUserDelGatewayReq.Delete.class) @RequestBody SysUserDelGatewayReq sysUserDelGatewayReq, HttpServletRequest request, HttpServletResponse response) {
        Result result = null;
        try {
            RedisUser baseReq = this.getUserInfoFromRedis(request, response);
            BeanUtils.copyProperties(baseReq, sysUserDelGatewayReq);
            result = sysUserService.delUser(sysUserDelGatewayReq);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_DEL_USER_INFO.getErrorCode(), ErrorEnum.ERROR_DEL_USER_INFO.getErrorMessage());
        }
    }

    @PostMapping(path = "/edit/v1")
    public JsonResVo edit(@Validated(SysUserUpdateGatewayReq.Modify.class) @RequestBody SysUserUpdateGatewayReq sysUserUpdateGatewayReq, HttpServletRequest request, HttpServletResponse response) {
        Result result = null;
        try {
            RedisUser aa = this.getUserInfoFromRedis(request, response);
            BeanUtils.copyProperties(aa, sysUserUpdateGatewayReq);
            result = sysUserService.updateUser(sysUserUpdateGatewayReq);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_EDIT_USER_INFO.getErrorCode(), ErrorEnum.ERROR_EDIT_USER_INFO.getErrorMessage());
        }
    }

    /*
        @PostMapping("/menus/v1")
        public JsonResVo menus(Long userId){
            Result result = null;
            try {
                result = sysRoleService.userAclTree(userId);
                if (result.isSuccess()){
                    return JsonResVo.buildSuccess(result.getData());
                }
                return JsonResVo.buildErrorResult(result.getCode(),result.getMessage());
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
                return JsonResVo.buildErrorResult(ErrorEnum.ERROR_ROLE_ACL_MENU_TREE_FAIL.getErrorCode(),ErrorEnum.ERROR_ROLE_ACL_MENU_TREE_FAIL.getErrorMessage());
            }
        }
    */
    /*@PostMapping("/sysDynamicMenu/v1")
    public JsonResVo menus(Long userId,@Validated(RoleMmmpGGRes.Query.class)@RequestBody RoleMmmpGGRes RoleMmmpGGRes, HttpServletRequest request, HttpServletResponse response) {
        Result result = null;
        try {
            RedisUser aa = this.getUserInfoFromRedis(request, response);
            BeanUtils.copyProperties(aa, RoleMmmpGGRes);
            result = sysUserService.userRoleMenu(RoleMmmpGGRes.getUserId(),RoleMmmpGGRes);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_ROLE_ACL_MENU_TREE_FAIL.getErrorCode(), ErrorEnum.ERROR_ROLE_ACL_MENU_TREE_FAIL.getErrorMessage());
        }
    }*/
    @PostMapping("/dynamicMenuAndRole/v1")
    public JsonResVo sysDynamicMenu(Long userId,@Validated(UserIsSuperMainInfoRes.Query.class) @RequestBody UserIsSuperMainInfoRes userIsSuperMainInfoRes, HttpServletRequest request, HttpServletResponse response) {
        Result result = null;
        try {
            RedisUser aa = this.getUserInfoFromRedis(request, response);
            BeanUtils.copyProperties(aa, userIsSuperMainInfoRes);
            result = sysRoleService.dynamicMenuAndRole(userIsSuperMainInfoRes);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_ROLE_ACL_MENU_TREE_FAIL.getErrorCode(), ErrorEnum.ERROR_ROLE_ACL_MENU_TREE_FAIL.getErrorMessage());
        }
    }
    @PostMapping("/menus/v1")
    public JsonResVo menus(Long userId, @RequestBody UserIsSuperMainInfoRes userIsSuperMainInfoRes, HttpServletRequest request, HttpServletResponse response) {
        Result result = null;
        try {
            RedisUser aa = this.getUserInfoFromRedis(request, response);
            BeanUtils.copyProperties(aa, userIsSuperMainInfoRes);
            result = sysRoleService.finalDynamicMenu(userIsSuperMainInfoRes);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_ROLE_ACL_MENU_TREE_FAIL.getErrorCode(), ErrorEnum.ERROR_ROLE_ACL_MENU_TREE_FAIL.getErrorMessage());
        }
    }

    @PostMapping("/getuidsBydeId/v1")
    public JsonResVo getChildDeptUserssByUserUuid(@Validated(DeptUserGatewayReq.Query.class) @RequestBody DeptUserGatewayReq deptUserReq, HttpServletRequest request, HttpServletResponse response) {
        Result result = null;
        try {
            RedisUser aa = this.getUserInfoFromRedis(request, response);
            BeanUtils.copyProperties(aa, deptUserReq);
            result = sysUserService.getChildDeptUserssByUserUuid(deptUserReq);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_ROLE_ACL_MENU_TREE_FAIL.getErrorCode(), ErrorEnum.ERROR_ROLE_ACL_MENU_TREE_FAIL.getErrorMessage());
        }
    }

}
