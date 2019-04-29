package com.xiaowei.sys.platform.gateway.service.parameter;

import com.xiaowei.sys.platform.core.facade.service.vo.parameter.ParameterAddVO;
import com.xiaowei.sys.platform.core.facade.service.vo.parameter.ParameterVo;
import com.xiaowei.sys.platform.gateway.integration.parameter.ParameterIntegration;
import com.xiaowei.sys.platform.gateway.req.parameter.ParameterInsertReq;
import com.xiaowei.sys.platform.gateway.req.parameter.ParameterReq;
import com.yeecli.component.common.model.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ParameterServiceImpl implements ParameterService {
    @Autowired
    private ParameterIntegration parameterIntegration;


    @Override
    public Result<List<ParameterVo>> getInfoByFieldValue(String fieldValue) {
        return parameterIntegration.getInfoByFieldValue(fieldValue);
    }

    @Override
    public Result saveParameter(ParameterInsertReq parameterInsertReq) {
        ParameterAddVO parameterAddVO = new ParameterAddVO();
//        parameterAddVO.setFieldValue(parameterInsertReq.getFieldValue());
//        parameterAddVO.setParameterName(parameterInsertReq.getParameterName());
//        parameterAddVO.setParameterValue(parameterInsertReq.getParameterValue());
//        parameterAddVO.setSerialNumber(parameterInsertReq.getSerialNumber());
        BeanUtils.copyProperties(parameterInsertReq,parameterAddVO);
        return parameterIntegration.saveParameter(parameterAddVO);
    }

    @Override
    public Result updateParameter(ParameterInsertReq parameterInsertReq) {
        ParameterAddVO parameterAddVO = new ParameterAddVO();
        BeanUtils.copyProperties(parameterInsertReq, parameterAddVO);
        return parameterIntegration.updateParameter(parameterAddVO);
    }

    @Override
    public Result delParameter(ParameterReq parameterReq) {
        ParameterVo parameterVo = new ParameterVo();
        BeanUtils.copyProperties(parameterReq, parameterVo);
        return parameterIntegration.delParameter(parameterVo);

    }

//    @Override
//    public Result parameterList(ParameterListGaReq parameterListGaReq) {
//        ParameterPageList parameterPageList = new ParameterPageList();
//        if (null != parameterListGaReq) {
//            BeanUtils.copyProperties(parameterListGaReq, parameterPageList);
//        }
//        Result<ParameterVo> parameterVoResult = parameterIntegration.parameterList(parameterPageList);
//        ParameterVo parameterVo = parameterVoResult.getData();
//        ParameterListReq parameterListReq = new ParameterListReq();
//
//        BeanUtils.copyProperties(parameterVo,parameterListReq);
//        return Result.wrapSuccessfulResult(parameterListReq);
//    }
}
