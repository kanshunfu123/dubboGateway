package com.xiaowei.sys.platform.gateway.service.waterstandard;

import com.xiaowei.sys.platform.core.facade.service.vo.dictionary.DictionaryVO;
import com.xiaowei.sys.platform.core.facade.service.vo.dictionary.WaterStandardVo;
import com.xiaowei.sys.platform.core.facade.service.vo.waterstandard.SysStandardTypeVO;
import com.xiaowei.sys.platform.gateway.req.waterstandard.StandardSaveReq;
import com.xiaowei.sys.platform.gateway.res.waterstandard.SysStandardRes;
import com.xiaowei.sys.platform.gateway.res.waterstandard.SysStandardTypeRes;
import com.yeecli.component.common.model.Result;

import java.util.List;

public interface WaterStandardService {
    /**
     * 添加水质标准
     */
    public Result<Object> save(StandardSaveReq waterStandardSaveReq);

    /**
     * 根据代码名称查询字典
     *
     * @param codeValue
     * @return
     */
    public Result<List<DictionaryVO>> getDictionaryInfoByCodeValue(String codeValue);

    /**
     * 根据parentId查询字典
     *
     * @param parentId
     * @return
     */
    public Result<List<WaterStandardVo>> getDictionaryInfoByParentId(String parentId);

    /**
     * 查询所有的水质类型
     */
    public Result<List<SysStandardTypeRes>> findAllStandardType(String codeValue);

    /**
     * 根据parentValue查询该水质下的项目
     */
    public Result<List<SysStandardRes>> findByParentValue(String parentValue);

}
