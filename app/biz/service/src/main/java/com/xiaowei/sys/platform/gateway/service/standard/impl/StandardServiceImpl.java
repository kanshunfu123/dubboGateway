package com.xiaowei.sys.platform.gateway.service.standard.impl;

import com.google.common.collect.Lists;
import com.xiaowei.sys.platform.core.facade.service.req.standard.StandByNamePagingReq;
import com.xiaowei.sys.platform.core.facade.service.req.standard.StandardInfo;
import com.xiaowei.sys.platform.core.facade.service.req.standard.StandardPageReq;
import com.xiaowei.sys.platform.core.facade.service.req.standard.StandardReqAdd;
import com.xiaowei.sys.platform.core.facade.service.vo.standard.BaseStandPPage;
import com.xiaowei.sys.platform.core.facade.service.vo.standard.StandardEditVO;
import com.xiaowei.sys.platform.core.facade.service.vo.standard.StandardPaginVO;
import com.xiaowei.sys.platform.core.facade.service.vo.standard.standByNamePagingVO;
import com.xiaowei.sys.platform.gateway.integration.standard.StandardIntegration;
import com.xiaowei.sys.platform.gateway.req.details.DetailsReq;
import com.xiaowei.sys.platform.gateway.req.standard.StandByNamePagingGatewayReq;
import com.xiaowei.sys.platform.gateway.req.standard.StandardPageReqs;
import com.xiaowei.sys.platform.gateway.req.standard.StandardReq;
import com.xiaowei.sys.platform.gateway.req.standard.StandardReqAdds;
import com.xiaowei.sys.platform.gateway.res.standard.BaseStandPPageRes;
import com.xiaowei.sys.platform.gateway.res.standard.StandByNamePagingRes;
import com.xiaowei.sys.platform.gateway.service.standard.StandardService;
import com.yeecli.component.common.model.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 张宇斌
 * @CreateDate: 2018/6/11 13:47
 */
@Component
public class StandardServiceImpl implements StandardService {
    protected final Logger logger = Logger.getLogger(StandardServiceImpl.class);
    @Autowired
    private StandardIntegration standardIntegration;

    /**
     * @param standardReq
     * @return(根据UUID来查询standard)
     */
    @Override
    public Result<StandardEditVO> getstandardByUuid(StandardReq standardReq) {
        StandardInfo standardInfo = new StandardInfo();
        BeanUtils.copyProperties(standardReq, standardInfo);
        return standardIntegration.getstandardByUuid(standardInfo);
    }

    /**
     * @param standardReq
     * @return（删除）
     */
    @Override
    public Result<Object> delete(StandardReq standardReq) {
        StandardInfo standardInfo = new StandardInfo();
        BeanUtils.copyProperties(standardReq, standardInfo);
        return standardIntegration.delete(standardInfo);
    }

    /**
     * @param reqAdd
     * @return(修改Standard)
     */
    @Override
    public Result updatestandard(StandardReqAdds reqAdd) {
            StandardReqAdd standardReqAdd = new StandardReqAdd();
            List<com.xiaowei.sys.platform.core.facade.service.req.details.DetailsReq> detailsReqList = new ArrayList<>();
            List<DetailsReq> detailsReqs = reqAdd.getDetailsReq();
            for (DetailsReq detailsReq1 : detailsReqs) {
                com.xiaowei.sys.platform.core.facade.service.req.details.DetailsReq detailsReq = new com.xiaowei.sys.platform.core.facade.service.req.details.DetailsReq();
                BeanUtils.copyProperties(detailsReq1, detailsReq);
                detailsReqList.add(detailsReq);
            }
            BeanUtils.copyProperties(reqAdd, standardReqAdd);
            standardReqAdd.setDetailsReq(detailsReqList);
            Result<Object> result = standardIntegration.update(standardReqAdd);
            return result;
    }

    /**
     * @param standardPageReq
     * @return(分页查询Standard)
     */
    @Override
    public Result findStandard(StandardPageReqs standardPageReq) {
            StandardPageReq standardPageReq1 = new StandardPageReq();
            standardPageReq1.setDelFlag("0");
            standardPageReq1.setStandardName(standardPageReq.getStandardName());
            BeanUtils.copyProperties(standardPageReq, standardPageReq1);
            Result<StandardPaginVO> result = standardIntegration.standardPageList(standardPageReq1);
            return result;
    }

    @Override
    public Result<StandByNamePagingRes> standByNamePaging(StandByNamePagingGatewayReq standByNamePagingReq) {
        StandByNamePagingRes standByNamePagingRes = new StandByNamePagingRes();
        StandByNamePagingReq standByNamePa = new StandByNamePagingReq();
        BeanUtils.copyProperties(standByNamePagingReq, standByNamePa);
        Result<standByNamePagingVO> standByNamePagingVOResult = standardIntegration.standByNamePaging(standByNamePa);
        if (standByNamePagingVOResult.isSuccess()) {
            standByNamePagingVO standByNamePagingVO = standByNamePagingVOResult.getData();
            standByNamePagingRes.setTotal(standByNamePagingVO.getTotal());
            standByNamePagingRes.setLimit(standByNamePagingVO.getLimit());
            standByNamePagingRes.setCurrPageNo(standByNamePagingVO.getCurrPageNo());
            if (standByNamePagingVO != null) {
                standByNamePagingRes.setStandardName(standByNamePagingVO.getStandardName());
                standByNamePagingRes.setDelFlag(standByNamePagingVO.getDelFlag());
                List<BaseStandPPage> baseStandPPages = standByNamePagingVO.getDatas();
                List<BaseStandPPageRes> baseStandPPageRes= Lists.newArrayList();
                if (!CollectionUtils.isEmpty(baseStandPPages)){
                    baseStandPPages.forEach(baseStandPPage -> {
                        BaseStandPPageRes baseStandPPageRes1=new BaseStandPPageRes();
                        BeanUtils.copyProperties(baseStandPPage,baseStandPPageRes1);
                        baseStandPPageRes.add(baseStandPPageRes1);
                    });
                }
                standByNamePagingRes.setDatas(baseStandPPageRes);
                return Result.wrapSuccessfulResult(standByNamePagingRes);
            }else{
                return Result.wrapSuccessfulResult(standByNamePagingRes);
            }
        }
        return Result.wrapSuccessfulResult(standByNamePagingRes);
    }
}
