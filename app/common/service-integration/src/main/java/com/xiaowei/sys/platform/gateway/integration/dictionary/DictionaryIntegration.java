package com.xiaowei.sys.platform.gateway.integration.dictionary;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaowei.sys.platform.core.facade.service.facade.dictionary.DictionaryFacade;
import com.xiaowei.sys.platform.core.facade.service.req.dictionary.DictAttrListReq;
import com.xiaowei.sys.platform.core.facade.service.req.dictionary.DictionaryAddReq;
import com.xiaowei.sys.platform.core.facade.service.req.dictionary.DictionaryReq;
import com.xiaowei.sys.platform.core.facade.service.vo.dictionary.DictAttrListPagingVO;
import com.xiaowei.sys.platform.core.facade.service.vo.dictionary.DictFatherVlueVO;
import com.xiaowei.sys.platform.core.facade.service.vo.dictionary.DictionaryVO;
import com.yeecli.component.common.model.Result;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by kanshunfu on 2018/9/11.
 */
@Component
public class DictionaryIntegration {
    @Reference(version = "1.0.0", check = false)
    private DictionaryFacade dictionaryFacade;

    /**
     * 查看字典树
     *
     * @return
     */
    public Result dictionaryTreeList() {
        return dictionaryFacade.dictionaryTreeList();
    }

    /**
     * 新增字典树
     *
     * @param dictionaryAddReq
     * @return
     */
    public Result<Object> saveDictionary(DictionaryAddReq dictionaryAddReq) {
        return dictionaryFacade.saveDictionary(dictionaryAddReq);
    }

    /**
     * 编辑字典树
     *
     * @param dictionaryReq
     * @return
     */
    public Result<Object> updateDictionary(DictionaryReq dictionaryReq) {
        return dictionaryFacade.updateDictionary(dictionaryReq);

    }

    /**
     * 逻辑删除字典
     * @param dictionaryReq
     * @return
     */
    public Result<Object> delDictionary(DictionaryReq dictionaryReq){
        return dictionaryFacade.delDictionary(dictionaryReq);
    }

    /**
     * 根据uuid查看字典架构
     * @param uuid
     * @return
     */
    public Result<DictionaryVO> getDictionaryInfoByUuid(String uuid){
        return dictionaryFacade.getDictionaryInfoByUuid(uuid);
    }
    /**
     * 根据code_value集合 分批查询孙子 数据字典
     * @param codeValue
     * @return
     */
    public Result<Map<String,List<DictFatherVlueVO>>> getChildDictByCodevalue(List<String> codeValue){
        return dictionaryFacade.getChildDictByCodevalue(codeValue);
    }
    /**
     * 根据 codeValue 分页查询字典 以及字典所对应的属性
     * @param dictAttrListReq
     * @return
     */
    public Result<DictAttrListPagingVO> dictAttrListPagingVO(DictAttrListReq dictAttrListReq){
        return dictionaryFacade.dictAttrListPagingVO(dictAttrListReq);
    }
}
