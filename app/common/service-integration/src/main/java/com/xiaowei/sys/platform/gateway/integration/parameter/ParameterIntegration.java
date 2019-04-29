package com.xiaowei.sys.platform.gateway.integration.parameter;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaowei.sys.platform.core.facade.service.facade.parameter.ParameterFacade;
import com.xiaowei.sys.platform.core.facade.service.vo.parameter.ParameterAddVO;
import com.xiaowei.sys.platform.core.facade.service.vo.parameter.ParameterVo;
import com.yeecli.component.common.model.Result;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by kanshunfu on 2018/9/11.
 */
@Component
public class ParameterIntegration {

    @Reference(version = "1.0.0", check = false)
    private ParameterFacade parameterFacade;
    /**
     * 根据fieldValue查看参数列表
     * @param fieldValue
     * @return
     */
    public Result<List<ParameterVo>> getInfoByFieldValue(String fieldValue){
        return parameterFacade.getInfoByFieldValue(fieldValue);
    }

    /**
     * 增加参数
     * @param parameterAddVO
     * @return
     */
    public Result<Object>saveParameter(ParameterAddVO parameterAddVO){
        return parameterFacade.saveParameter(parameterAddVO);
    }
    /**
     * 编辑参数
     * @param parameterAddVO
     * @return
     */
    public Result<Object>updateParameter(ParameterAddVO parameterAddVO){
        return parameterFacade.updateParameter(parameterAddVO);
    }

    /**
     * 逻辑删除参数
     * @param parameterVo
     * @return
     */
    public  Result<Object>delParameter(ParameterVo parameterVo){
        return parameterFacade.delParameter(parameterVo);
    }



}
