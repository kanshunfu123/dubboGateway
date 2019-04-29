package com.xiaowei.sys.platform.gateway.service.parameter;

import com.xiaowei.sys.platform.core.facade.service.vo.parameter.ParameterVo;
import com.xiaowei.sys.platform.gateway.req.parameter.ParameterInsertReq;
import com.xiaowei.sys.platform.gateway.req.parameter.ParameterReq;
import com.yeecli.component.common.model.Result;

import java.util.List;

/**
 * Created by kanshunfu on 2018/9/12
 */
public interface ParameterService {
    /**
     * 根据fieldValue查看参数列表
     * @param fieldValue
     * @return
     */
    public Result<List<ParameterVo>> getInfoByFieldValue(String fieldValue);

    /**
     * 新增参数
     * @param parameterInsertReq
     * @return
     */
    public Result saveParameter(ParameterInsertReq parameterInsertReq);
    /**
     * 编辑参数
     * @param parameterInsertReq
     * @return
     */
    public Result updateParameter(ParameterInsertReq parameterInsertReq);

    /**
     * 逻辑删除参数
     * @param parameterReq
     * @return
     */
    public Result delParameter(ParameterReq parameterReq);


}
