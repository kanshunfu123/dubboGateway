package com.xiaowei.sys.platform.gateway.service.dictionary;
import com.xiaowei.sys.platform.gateway.req.dictionary.DictAttrListGWReq;
import com.xiaowei.sys.platform.gateway.req.dictionary.DictionaryDelReq;
import com.xiaowei.sys.platform.gateway.req.dictionary.DictionaryInsertReq;
import com.xiaowei.sys.platform.gateway.req.dictionary.DictionaryUpdateReq;
import com.xiaowei.sys.platform.gateway.res.dictionary.DictAttrListPagingRes;
import com.xiaowei.sys.platform.gateway.res.dictionary.DictFatherVlueGatewayRes;
import com.yeecli.component.common.model.Result;

import java.util.List;
import java.util.Map;

/**
 * Created by kanshunfu on 2018/9/12
 */
public interface DictionaryService {
    /**
     * 字典树列表
     *
     * @return
     */
    public Result dictionaryTreeResult();

    /**
     * 新增字典树
     *
     * @param dictionaryInsertReq
     * @return
     */
    public Result saveDictionary(DictionaryInsertReq dictionaryInsertReq);

    /**
     * 编辑字典树
     * @param dictionaryUpdateReq
     * @return
     */
    public Result updateDiction(DictionaryUpdateReq dictionaryUpdateReq);

    /**
     * 逻辑删除字典树
     * @param dictionaryDelReq
     * @return
     */
    public Result delDictionary(DictionaryDelReq dictionaryDelReq);

    /**
     * 根据uuid查看字典组织架构
     * @param uuid
     * @return
     */
    public Result getDictionaryInfoByUuid(String uuid);
    /**
     * 根据code_value集合 分批查询孙子 数据字典
     * @param codeValue
     * @return
     */
    public Result<Map<String,List<DictFatherVlueGatewayRes>>> getChildDictByCodevalue(List<String> codeValue);

    /**
     * 根据 codeValue 分页查询字典 以及字典所对应的属性
     * @param dictAttrListGWReq
     * @return
     */
    public Result<DictAttrListPagingRes> dictAttrListPagingVO(DictAttrListGWReq dictAttrListGWReq);
}
