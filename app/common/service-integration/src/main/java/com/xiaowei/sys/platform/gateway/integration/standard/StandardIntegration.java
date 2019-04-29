package com.xiaowei.sys.platform.gateway.integration.standard;

import com.alibaba.dubbo.config.annotation.Reference;

import com.xiaowei.sys.platform.core.facade.service.facade.standard.StandardFacade;
import com.xiaowei.sys.platform.core.facade.service.req.standard.StandByNamePagingReq;
import com.xiaowei.sys.platform.core.facade.service.req.standard.StandardInfo;
import com.xiaowei.sys.platform.core.facade.service.req.standard.StandardPageReq;
import com.xiaowei.sys.platform.core.facade.service.req.standard.StandardReqAdd;
import com.xiaowei.sys.platform.core.facade.service.vo.standard.StandardEditVO;
import com.xiaowei.sys.platform.core.facade.service.vo.standard.StandardPaginVO;
import com.xiaowei.sys.platform.core.facade.service.vo.standard.standByNamePagingVO;
import com.yeecli.component.common.model.Result;
import org.springframework.stereotype.Component;

@Component
public class StandardIntegration {
    @Reference(version = "1.0.0", check = false)
    private StandardFacade standardFacade;

    public Result<StandardEditVO> getstandardByUuid(StandardInfo standardInfo) {
        return standardFacade.getstandardByUuid(standardInfo);
    }
    public Result<Object> delete(StandardInfo standardInfo) {
        return standardFacade.deleteStandard(standardInfo);
    }

    public Result<Object> update(StandardReqAdd reqAdd) {
        return standardFacade.updateStandard(reqAdd);
    }

    public Result<StandardPaginVO> standardPageList(StandardPageReq standardPageReq) {
        return standardFacade.standardPageList(standardPageReq);
    }
    /**
     * 水质标准分页查询，带明细
     * @param standByNamePagingReq
     * @return
     */
    public Result<standByNamePagingVO> standByNamePaging(StandByNamePagingReq standByNamePagingReq){
        return standardFacade.standByNamePaging(standByNamePagingReq);
    }
}
