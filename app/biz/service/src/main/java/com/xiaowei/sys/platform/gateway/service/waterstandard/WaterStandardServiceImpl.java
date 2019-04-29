package com.xiaowei.sys.platform.gateway.service.waterstandard;

import com.google.common.collect.Lists;
import com.xiaowei.sys.platform.core.facade.service.req.waterstandard.SaveStandardReq;
import com.xiaowei.sys.platform.core.facade.service.vo.dictionary.DictionaryVO;
import com.xiaowei.sys.platform.core.facade.service.vo.dictionary.WaterStandardVo;
import com.xiaowei.sys.platform.core.facade.service.vo.waterstandard.SysStandardTypeVO;
import com.xiaowei.sys.platform.gateway.integration.waterstandard.WaterstandardIntegration;
import com.xiaowei.sys.platform.gateway.req.waterstandard.StandardSaveReq;
import com.xiaowei.sys.platform.gateway.res.waterstandard.SysStandardRes;
import com.xiaowei.sys.platform.gateway.res.waterstandard.SysStandardTypeRes;
import com.yeecli.component.common.model.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class WaterStandardServiceImpl implements WaterStandardService {

    @Autowired
    WaterstandardIntegration waterstandardIntegration;

    @Override
    public Result<Object> save(StandardSaveReq standardSaveReq) {
        SaveStandardReq saveStandardReq = new SaveStandardReq();
        BeanUtils.copyProperties(standardSaveReq, saveStandardReq);
        return waterstandardIntegration.save(saveStandardReq);
    }

    @Override
    public Result<List<DictionaryVO>> getDictionaryInfoByCodeValue(String codeValue) {
        Result<List<DictionaryVO>> result = waterstandardIntegration.getDictionaryInfoByCodeValue(codeValue);
        return result;
    }

    @Override
    public Result<List<WaterStandardVo>> getDictionaryInfoByParentId(String parentId) {
        return waterstandardIntegration.getDictionaryInfoByParentId(parentId);
    }

    @Override
    public Result<List<SysStandardTypeRes>> findAllStandardType(String codeValue) {
        Result<List<SysStandardTypeVO>> listResult=waterstandardIntegration.findAllStandardType(codeValue);
        List<SysStandardTypeRes>list= Lists.newArrayList();
        if(listResult.isSuccess())
        {
            List<SysStandardTypeVO>list1=listResult.getData();
            if(!CollectionUtils.isEmpty(list1))
            {
                list1.forEach(sysStandardTypeVO -> {
                    SysStandardTypeRes sysStandardTypeRes=new SysStandardTypeRes();
                    BeanUtils.copyProperties(sysStandardTypeVO,sysStandardTypeRes);
                    list.add(sysStandardTypeRes);
                });
            }
        }
        return Result.wrapSuccessfulResult(list);
    }

    @Override
    public Result<List<SysStandardRes>> findByParentValue(String parentValue) {
        Result<List<SysStandardTypeVO>> listResult=waterstandardIntegration.findByParentValue(parentValue);
        List<SysStandardRes>list= Lists.newArrayList();
        if(listResult.isSuccess())
        {
            List<SysStandardTypeVO>list1=listResult.getData();
            if(!CollectionUtils.isEmpty(list1))
            {
                list1.forEach(sysStandardTypeVO -> {
                    SysStandardRes sysStandardRes=new SysStandardRes();
                    sysStandardRes.setDetailName(sysStandardTypeVO.getCodeName());
                    list.add(sysStandardRes);
                });
            }
        }
        return Result.wrapSuccessfulResult(list);
    }
}
