package com.xiaowei.sys.platform.gateway.integration.waterstandard;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaowei.sys.platform.core.facade.service.facade.dictionary.DictionaryFacade;
import com.xiaowei.sys.platform.core.facade.service.facade.waterstandard.WaterStandardFacade;
import com.xiaowei.sys.platform.core.facade.service.req.waterstandard.SaveStandardReq;
import com.xiaowei.sys.platform.core.facade.service.vo.dictionary.DictionaryVO;
import com.xiaowei.sys.platform.core.facade.service.vo.dictionary.WaterStandardVo;
import com.xiaowei.sys.platform.core.facade.service.vo.waterstandard.SysStandardTypeVO;
import com.yeecli.component.common.model.Result;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class WaterstandardIntegration {
    @Reference(version = "1.0.0", check = false)
    private WaterStandardFacade waterStandardFacade;
    @Reference(version = "1.0.0", check = false)
    private DictionaryFacade dictionaryFacade;

    /**
     * 添加水质标准
     */
    public Result<Object> save(SaveStandardReq saveStandardReq) {
        return waterStandardFacade.save(saveStandardReq);
    }

    /**
     * 根据codeValue查询数据字典
     */
    public Result<List<DictionaryVO>> getDictionaryInfoByCodeValue(String codeValue) {
        return dictionaryFacade.getDictionaryInfoByCodeValue(codeValue);
    }

    /**
     * 根据parentId查询数据字典
     */
    public Result<List<WaterStandardVo>> getDictionaryInfoByParentId(String parentId) {
        return dictionaryFacade.getDictionaryInfoByParentId(parentId);
    }

    /**
     * 查询所有的水质类型
     */
    public Result<List<SysStandardTypeVO>> findAllStandardType(String codeValue) {
        return waterStandardFacade.findAllStandardType(codeValue);
    }

    /**
     * 根据parentValue查询该水质下的项目
     */
    public Result<List<SysStandardTypeVO>> findByParentValue(String parentValue) {
        return waterStandardFacade.findByParentValue(parentValue);
    }
}
