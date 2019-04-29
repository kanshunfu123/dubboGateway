package com.xiaowei.sys.platform.gateway.integration.sysdept;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaowei.sys.platform.core.facade.service.facade.sysdept.SysDeptFacade;
import com.xiaowei.sys.platform.core.facade.service.req.sysarea.SysAreaParentIdReq;
import com.xiaowei.sys.platform.core.facade.service.req.sysdept.SysDeptReq;
import com.xiaowei.sys.platform.core.facade.service.vo.sysarea.SysAreaParentIdVo;
import com.xiaowei.sys.platform.core.facade.service.vo.sysdept.SysDeptTreeVO;
import com.xiaowei.sys.platform.core.facade.service.vo.sysdept.SysDeptVO;
import com.yeecli.component.common.model.Result;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by MOMO on 2018/9/11.
 */
@Component
public class SysDeptIntegration {

    @Reference(version = "1.0.0", check = false)
    private SysDeptFacade sysDeptFacade;

    /**
     * 部门树形列表
     *
     * @return
     */
    public Result<List<SysDeptTreeVO>> deptTreeList() {
        return sysDeptFacade.deptTreeList();
    }

    /**
     * 新增部门
     *
     * @param sysDeptReq
     * @return
     */
    public Result<Object> insertDept(SysDeptReq sysDeptReq) {
        return sysDeptFacade.insertDept(sysDeptReq);
    }

    /**
     * 编辑部门
     *
     * @return
     */
    public Result<Object> updateDept(SysDeptReq sysDeptReq) {
        return sysDeptFacade.updateDept(sysDeptReq);
    }

    /**
     * 逻辑删除部门
     *
     * @param sysDeptReq
     * @return
     */
    public Result<Object> delDept(SysDeptReq sysDeptReq) {
        return sysDeptFacade.delDept(sysDeptReq);
    }

    /**
     * 根据uuid查看组织架构信息
     *
     * @param deptUuid
     * @return
     */
    public Result<SysDeptVO> getDeptInfoByUuid(String deptUuid){
        return sysDeptFacade.getDeptInfoByUuid(deptUuid);
    }
}
