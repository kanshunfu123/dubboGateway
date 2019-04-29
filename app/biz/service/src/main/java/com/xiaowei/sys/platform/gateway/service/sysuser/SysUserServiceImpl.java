package com.xiaowei.sys.platform.gateway.service.sysuser;

import com.google.common.collect.Lists;
import com.xiaowei.sys.platform.core.facade.service.req.sysrole.RoleMmmpGG;
import com.xiaowei.sys.platform.core.facade.service.req.sysuser.DeptUserReq;
import com.xiaowei.sys.platform.core.facade.service.req.sysuser.SysUserReq;
import com.xiaowei.sys.platform.core.facade.service.req.sysuser.SysUserUpdateReq;
import com.xiaowei.sys.platform.core.facade.service.req.sysuser.UserDeptAreaRolesPageReq;
import com.xiaowei.sys.platform.core.facade.service.vo.sysuser.*;
import com.xiaowei.sys.platform.gateway.exception.ErrorEnum;
import com.xiaowei.sys.platform.gateway.integration.sysuser.SysUserIntegration;
import com.xiaowei.sys.platform.gateway.req.sysrole.RoleMmmpGGRes;
import com.xiaowei.sys.platform.gateway.req.sysuser.*;
import com.xiaowei.sys.platform.gateway.res.sysuser.DeptUserRes;
import com.xiaowei.sys.platform.gateway.res.sysuser.SysUserRoleMenuRes;
import com.xiaowei.sys.platform.gateway.util.SSORedisUtil;
import com.yeecli.component.common.model.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by MOMO on 2018/9/11.
 */
@Component
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SSORedisUtil ssoRedisUtil;
    @Autowired
    private SysUserIntegration sysUserIntegration;


    @Override
    public Result loginByUsername(UserLoginReq userLoginReq) {
        String key = "verUUidCode:" + userLoginReq.getVerUUidCode();
        boolean existsKey = ssoRedisUtil.existsKey(key);
        if (!existsKey) {//验证验证码
            return Result.wrapErrorResult(ErrorEnum.ERROR_IMG_CODE_LOSE.getErrorCode(), ErrorEnum.ERROR_IMG_CODE_LOSE.getErrorMessage());
        }
        String verUUidCode = (String) ssoRedisUtil.get(key);
        String aa = userLoginReq.getVerificationCode().toLowerCase().trim();
        if (!aa.equals(verUUidCode.toLowerCase().trim())) {
            return Result.wrapErrorResult(ErrorEnum.ERROR_IMG_CODE.getErrorCode(), ErrorEnum.ERROR_IMG_CODE.getErrorMessage());
        }

        SysUserReq sysUserReq = new SysUserReq();
        BeanUtils.copyProperties(userLoginReq, sysUserReq);
        Result<SysUserVO> result = sysUserIntegration.loginByUsername(sysUserReq);
        return result;
        /*if (result.isSuccess()) {
            SysUserVO sysUserRes = result.getData();
            //页面需要的
            SysUserRedisGatewayReq sysUserRedisGatewayReq = new SysUserRedisGatewayReq();
            sysUserRedisGatewayReq.setSysUserUuid(sysUserRes.getSysUserUuid());
            sysUserRedisGatewayReq.setSysUserName(sysUserRes.getSysUserName());
            //存入redis
            RedisUser aaa=new RedisUser();
            aaa.setUserId(sysUserRes.getId());
            aaa.setUserName(sysUserRes.getSysUserName());
            aaa.setUserUuid(sysUserRes.getSysUserUuid());
           aaa.setUserPhone(sysUserRes.getSysUserPhone());
            String s=JSONObject.toJSONString(aaa);
            ssoRedisUtil.setString(RedisKeyEnum.REDIS_KEY_USER_INFO.getKey() + sysUserRes.getSysUserUuid(), s);
            ssoRedisUtil.expire(RedisKeyEnum.REDIS_KEY_USER_INFO.getKey() + sysUserRes.getSysUserUuid(), RedisKeyEnum.REDIS_KEY_USER_INFO.getExpireTime());
            return Result.wrapSuccessfulResult(sysUserRedisGatewayReq);
        } else {
            return result;
        }*/

    }

    @Override
    public Result userDeptAreaRolesList(UserDeptAreaRolesPageGatewayReq userDeptAreaRolesPageGatewayReq) {
        UserDeptAreaRolesPageReq userDeptAreaRolesPageReq = new UserDeptAreaRolesPageReq();
        if (userDeptAreaRolesPageGatewayReq != null) {
            BeanUtils.copyProperties(userDeptAreaRolesPageGatewayReq, userDeptAreaRolesPageReq);
        }
        Result<UserDeptAreaRolesPageVO> userDeptAreaRolesPageVOResult = sysUserIntegration.userDeptAreaRolesList(userDeptAreaRolesPageReq);
        return Result.wrapSuccessfulResult(userDeptAreaRolesPageVOResult.getData());
    }

    @Override
    public Result getUserInfiByUuid(UserIfoByUUIDGatewayReq userIfoByUUIDGatewayReq) {
        UserDeptAreaRolesPageReq userDeptAreaRolesPageReq = new UserDeptAreaRolesPageReq();
        if (userIfoByUUIDGatewayReq != null) {
            BeanUtils.copyProperties(userIfoByUUIDGatewayReq, userDeptAreaRolesPageReq);
        }
        Result<UserDeptAreaRolesPageVO> userDeptAreaRolesPageVOResult = sysUserIntegration.userDeptAreaRolesList(userDeptAreaRolesPageReq);
        return Result.wrapSuccessfulResult(userDeptAreaRolesPageVOResult.getData());

    }

    @Override
    public Result<Object> insertUser(SysUserInserGatewayReq sysUserInserGatewayReq) {
        SysUserUpdateReq sysUserUpdateReq = new SysUserUpdateReq();
        BeanUtils.copyProperties(sysUserInserGatewayReq, sysUserUpdateReq);
        return sysUserIntegration.insertUser(sysUserUpdateReq);
    }

    @Override
    public Result<Object> delUser(SysUserDelGatewayReq sysUserDelGatewayReq) {
        SysUserUpdateReq sysUserUpdateReq = new SysUserUpdateReq();
        BeanUtils.copyProperties(sysUserDelGatewayReq, sysUserUpdateReq);
        return sysUserIntegration.delUser(sysUserUpdateReq);
    }

    @Override
    public Result<Object> updateUser(SysUserUpdateGatewayReq sysUserUpdateGatewayReq) {
        SysUserUpdateReq sysUserUpdateReq = new SysUserUpdateReq();
        BeanUtils.copyProperties(sysUserUpdateGatewayReq, sysUserUpdateReq);
        return sysUserIntegration.updateUser(sysUserUpdateReq);
    }

    @Override
    public Result<Boolean> hasUrlAcl(String url, Long userId, String userPhone) {
        return sysUserIntegration.hasUrlAcl(url, userId, userPhone);
    }

    @Override
    public Result<List<DeptUserRes>> getChildDeptUserssByUserUuid(DeptUserGatewayReq deptUserGatewayReq) {
        DeptUserReq deptUserReq = new DeptUserReq();
        BeanUtils.copyProperties(deptUserGatewayReq, deptUserReq);
        Result<List<DeptUser>> listResult = sysUserIntegration.getChildDeptUserssByUserUuid(deptUserReq);
        List<DeptUserRes> deptUserRes = Lists.newArrayList();
        if (listResult.isSuccess()) {
            List<DeptUser> deptUsers = listResult.getData();
            if (CollectionUtils.isEmpty(deptUsers)) {
                return Result.wrapSuccessfulResult(deptUserRes);
            }
            deptUsers.forEach(deptUser -> {
                DeptUserRes userRes = new DeptUserRes();
                BeanUtils.copyProperties(deptUser, userRes);
                deptUserRes.add(userRes);
            });
            return Result.wrapSuccessfulResult(deptUserRes);
        }
        return Result.wrapErrorResult(listResult.getCode(), listResult.getMessage());
    }
    @Override
    public Result<SysUserRoleMenuRes> userRoleMenu(Long userId, RoleMmmpGGRes roleMmmpGGRes) {
        RoleMmmpGG roleMmmpGG=new RoleMmmpGG();
        if (null!=roleMmmpGGRes){
            BeanUtils.copyProperties(roleMmmpGGRes,roleMmmpGG);
        }
        Result<SysUserRoleMenuVO> sysUserRoleMenuVOResult = sysUserIntegration.userRoleMenu(userId,roleMmmpGG);
        SysUserRoleMenuRes sysUserRoleMenuRes = new SysUserRoleMenuRes();
        if (sysUserRoleMenuVOResult.isSuccess()) {
            SysUserRoleMenuVO sysUserRoleMenuVO = sysUserRoleMenuVOResult.getData();
            if (null != sysUserRoleMenuVO) {
                BeanUtils.copyProperties(sysUserRoleMenuVO, sysUserRoleMenuRes);
            }
        }
        return Result.wrapSuccessfulResult(sysUserRoleMenuRes);
    }
}
