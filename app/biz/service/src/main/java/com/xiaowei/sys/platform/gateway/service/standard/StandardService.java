package com.xiaowei.sys.platform.gateway.service.standard;

import com.xiaowei.sys.platform.core.facade.service.vo.standard.StandardEditVO;
import com.xiaowei.sys.platform.gateway.req.standard.StandByNamePagingGatewayReq;
import com.xiaowei.sys.platform.gateway.req.standard.StandardPageReqs;
import com.xiaowei.sys.platform.gateway.req.standard.StandardReq;
import com.xiaowei.sys.platform.gateway.req.standard.StandardReqAdds;
import com.xiaowei.sys.platform.gateway.res.standard.StandByNamePagingRes;
import com.yeecli.component.common.model.Result;


/**
 * @author: 张宇斌
 * @CreateDate: 2018/6/11 13:47
 */
public interface StandardService {
   /**
    *
    * @param standardReq
    * @return(根据UUID来查询standard)
    */
   Result<StandardEditVO> getstandardByUuid(StandardReq standardReq);
   /**
    *
    * @param standardReq
    * @return("删除")
    */
   Result<Object> delete(StandardReq standardReq);

   /**
    *
    * @param reqAdd
    * @return(修改standard)
    */
   Result updatestandard(StandardReqAdds reqAdd);
   /**
    *
    * @param standardPageReqs
    * @return(分页查询standard)
    */
   Result findStandard(StandardPageReqs standardPageReqs);
   /**
    * 水质标准分页查询，带明细
    * @param standByNamePagingReq
    * @return
    */
   public Result<StandByNamePagingRes> standByNamePaging(StandByNamePagingGatewayReq standByNamePagingReq);

}
