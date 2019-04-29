package com.xiaowei.sys.platform.gateway.service.sysdept;

import com.xiaowei.sys.platform.core.facade.service.req.sysdept.SysDeptReq;
import com.xiaowei.sys.platform.core.facade.service.vo.sysdept.SysDeptVO;
import com.xiaowei.sys.platform.gateway.req.sysdept.SysdeptDelReq;
import com.xiaowei.sys.platform.gateway.req.sysdept.SysdeptInsertReq;
import com.xiaowei.sys.platform.gateway.req.sysdept.SysdeptUpdateReq;
import com.xiaowei.sys.platform.gateway.res.sysdept.SysDeptTreeRes;
import com.yeecli.component.common.model.Result;

import java.util.List;

/**
 * Created by MOMO on 2018/9/11.
 */
public interface SysdeptService {

    /**
     * 部门树形列表
     *
     * @return
     */
    public Result<List<SysDeptTreeRes>> deptTreeResult();

    /**
     * 新增部门
     *
     * @param sysdeptInsertReq
     * @return
     */
    public Result<Object> insertDept(SysdeptInsertReq sysdeptInsertReq);
    /**
     * 编辑部门
     *
     * @return
     */
    public Result<Object> updateDept(SysdeptUpdateReq sysdeptUpdateReq);

    /**
     * 逻辑删除部门
     * @param sysdeptDelReq
     * @return
     */
    public Result<Object> delDept(SysdeptDelReq sysdeptDelReq);
    /**
     * 根据uuid查看组织架构信息
     *
     * @param deptUuid
     * @return
     */
    public Result getDeptInfoByUuid(String deptUuid);
}
