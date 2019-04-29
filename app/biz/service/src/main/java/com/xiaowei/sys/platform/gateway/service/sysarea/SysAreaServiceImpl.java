package com.xiaowei.sys.platform.gateway.service.sysarea;

import com.google.common.collect.Lists;
import com.xiaowei.sys.platform.core.facade.service.req.sysarea.SysAreaParentIdReq;
import com.xiaowei.sys.platform.core.facade.service.vo.sysarea.SysAreaAddVo;
import com.xiaowei.sys.platform.core.facade.service.vo.sysarea.SysAreaListVO;
import com.xiaowei.sys.platform.core.facade.service.vo.sysarea.SysAreaParentIdVo;
import com.xiaowei.sys.platform.gateway.integration.sysarea.SysAreaIntergration;
import com.xiaowei.sys.platform.gateway.req.sysarea.SysAreDelReq;
import com.xiaowei.sys.platform.gateway.req.sysarea.SysAreaInsertReq;
import com.xiaowei.sys.platform.gateway.req.sysarea.SysAreaParentIdGatewayReq;
import com.xiaowei.sys.platform.gateway.res.sysarea.SysAreaListReq;
import com.xiaowei.sys.platform.gateway.res.sysarea.SysAreaParentIdRes;
import com.yeecli.component.common.model.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MOMO on 2018/9/17.
 */
@Component
public class SysAreaServiceImpl implements SysAreaService {
    @Autowired
    private SysAreaIntergration sysAreaIntergration;

    @Override
    public Result<List<SysAreaListReq>> areaTreeList() {
        Result<List<SysAreaListVO>> sysAreaListVOS = sysAreaIntergration.areaTreeList();
        List<SysAreaListReq> sysAreaListReqs = new ArrayList<>();
        if (sysAreaListVOS.isSuccess()) {
            List<SysAreaListVO> sysAreaListVOS1 = sysAreaListVOS.getData();
            if (!CollectionUtils.isEmpty(sysAreaListVOS1)) {
                sysAreaListVOS1.forEach(aa -> {
                    SysAreaListReq sysAreaListReq = new SysAreaListReq();
                    BeanUtils.copyProperties(aa, sysAreaListReq);
                    sysAreaListReqs.add(sysAreaListReq);
                });
            }
        }
        return Result.wrapSuccessfulResult(sysAreaListReqs);
    }

    @Override
    public Result<Object> insertSysArea(SysAreaInsertReq sysAreaInsertReq) {
        SysAreaAddVo sysAreaAddVo = new SysAreaAddVo();
        BeanUtils.copyProperties(sysAreaInsertReq, sysAreaAddVo);
        return sysAreaIntergration.insertSysArea(sysAreaAddVo);
    }

    @Override
    public Result<Object> editSysArea(SysAreaInsertReq sysAreaInsertReq) {
        SysAreaAddVo sysAreaAddVo = new SysAreaAddVo();
        BeanUtils.copyProperties(sysAreaInsertReq, sysAreaAddVo);
        return sysAreaIntergration.editSysArea(sysAreaAddVo);
    }

    @Override
    public Result<Object> delSysArea(SysAreDelReq sysAreDelReq) {
        SysAreaAddVo sysAreaAddVo = new SysAreaAddVo();
        BeanUtils.copyProperties(sysAreDelReq, sysAreaAddVo);
        return sysAreaIntergration.delSysArea(sysAreaAddVo);
    }

    @Override
    public Result<List<SysAreaParentIdRes>> getareaListByParentId(SysAreaParentIdGatewayReq sysAreaParentIdGatewayReq) {
        List<SysAreaParentIdRes> c= Lists.newArrayList();

        SysAreaParentIdReq sysAreaParentIdReq = new SysAreaParentIdReq();
        BeanUtils.copyProperties(sysAreaParentIdGatewayReq, sysAreaParentIdReq);
        Result<List<SysAreaParentIdVo>> result = sysAreaIntergration.getareaListByParentId(sysAreaParentIdReq);
        if (result.isSuccess()) {
            List<SysAreaParentIdVo> list = result.getData();
            if (CollectionUtils.isEmpty(list)){
                return Result.wrapSuccessfulResult(c);
            }
            list.forEach(sysAreaParentIdVo -> {
                SysAreaParentIdRes sysAreaParentIdRes=new SysAreaParentIdRes();
                BeanUtils.copyProperties(sysAreaParentIdVo,sysAreaParentIdRes);
                c.add(sysAreaParentIdRes);
            });
            return Result.wrapSuccessfulResult(c);
        }
        return Result.wrapErrorResult(result.getCode(), result.getMessage());
    }
}
