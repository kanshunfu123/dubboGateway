package com.xiaowei.sys.platform.gateway.integration.sysarea;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaowei.sys.platform.core.facade.service.facade.sysarea.SysAreaFacade;
import com.xiaowei.sys.platform.core.facade.service.req.sysarea.SysAreaParentIdReq;
import com.xiaowei.sys.platform.core.facade.service.vo.sysarea.SysAreaAddVo;
import com.xiaowei.sys.platform.core.facade.service.vo.sysarea.SysAreaListVO;
import com.xiaowei.sys.platform.core.facade.service.vo.sysarea.SysAreaParentIdVo;
import com.yeecli.component.common.model.Result;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by MOMO on 2018/9/17.
 */
@Component
public class SysAreaIntergration {
    @Reference(version = "1.0.0", check = false)
    private SysAreaFacade sysAreaFacade;

    public Result<List<SysAreaListVO>> areaTreeList() {
        return sysAreaFacade.areaTreeList();
    }

    public Result<Object> insertSysArea(SysAreaAddVo sysAreaAddVo) {
        return sysAreaFacade.insertSysArea(sysAreaAddVo);
    }
    public Result<Object>editSysArea(SysAreaAddVo sysAreaAddVo){
        return sysAreaFacade.editSysArea(sysAreaAddVo);
    }
    public Result<Object>delSysArea(SysAreaAddVo sysAreaAddVo){
        return sysAreaFacade.delSysArea(sysAreaAddVo);
    }
    /**
     * 根据父级id查看子区域
     * @param sysAreaParentIdReq
     * @return
     */
    public Result<List<SysAreaParentIdVo>> getareaListByParentId(SysAreaParentIdReq sysAreaParentIdReq){
        return sysAreaFacade.getareaListByParentId(sysAreaParentIdReq);
    }

}
