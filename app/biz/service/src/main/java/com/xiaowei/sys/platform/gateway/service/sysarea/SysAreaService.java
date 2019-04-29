package com.xiaowei.sys.platform.gateway.service.sysarea;

import com.xiaowei.sys.platform.core.facade.service.req.sysarea.SysAreaParentIdReq;
import com.xiaowei.sys.platform.core.facade.service.vo.sysarea.SysAreaParentIdVo;
import com.xiaowei.sys.platform.gateway.req.sysarea.SysAreDelReq;
import com.xiaowei.sys.platform.gateway.req.sysarea.SysAreaInsertReq;
import com.xiaowei.sys.platform.gateway.req.sysarea.SysAreaParentIdGatewayReq;
import com.xiaowei.sys.platform.gateway.res.sysarea.SysAreaListReq;
import com.xiaowei.sys.platform.gateway.res.sysarea.SysAreaParentIdRes;
import com.yeecli.component.common.model.Result;

import java.util.List;

/**
 * Created by MOMO on 2018/9/17.
 */
public interface SysAreaService {
    /**
     * 区域递归树
     * @return
     */
    public Result<List<SysAreaListReq>> areaTreeList();

    /**
     * 新增区域
     * @param sysAreaInsertReq
     * @return
     */
    public Result<Object> insertSysArea(SysAreaInsertReq sysAreaInsertReq);
    /**
     * 编辑区域
     * @param sysAreaInsertReq
     * @return
     */
    public Result<Object> editSysArea(SysAreaInsertReq sysAreaInsertReq);
    /**
     * 逻辑删除区域
     * @param sysAreDelReq
     * @return
     */
    public Result<Object> delSysArea(SysAreDelReq sysAreDelReq);

    /**
     * 根据父级id查看子区域
     * @param sysAreaParentIdGatewayReq
     * @return
     */
    public Result<List<SysAreaParentIdRes>> getareaListByParentId(SysAreaParentIdGatewayReq sysAreaParentIdGatewayReq);
}
