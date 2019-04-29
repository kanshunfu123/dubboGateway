package com.xiaowei.sys.platform.gateway.service.dictionary;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xiaowei.sys.platform.core.facade.service.req.dictionary.DictAttrListReq;
import com.xiaowei.sys.platform.core.facade.service.req.dictionary.DictionaryAddReq;
import com.xiaowei.sys.platform.core.facade.service.req.dictionary.DictionaryReq;
import com.xiaowei.sys.platform.core.facade.service.vo.dictionary.DictAttrListPagingVO;
import com.xiaowei.sys.platform.core.facade.service.vo.dictionary.DictAttrListVO;
import com.xiaowei.sys.platform.core.facade.service.vo.dictionary.DictFatherVlueVO;
import com.xiaowei.sys.platform.core.facade.service.vo.dictionary.DictionaryTreeVo;
import com.xiaowei.sys.platform.gateway.integration.dictionary.DictionaryIntegration;
import com.xiaowei.sys.platform.gateway.req.dictionary.DictAttrListGWReq;
import com.xiaowei.sys.platform.gateway.req.dictionary.DictionaryDelReq;
import com.xiaowei.sys.platform.gateway.req.dictionary.DictionaryInsertReq;
import com.xiaowei.sys.platform.gateway.req.dictionary.DictionaryUpdateReq;
import com.xiaowei.sys.platform.gateway.res.dictionary.DictAttrListPagingRes;
import com.xiaowei.sys.platform.gateway.res.dictionary.DictAttrListRes;
import com.xiaowei.sys.platform.gateway.res.dictionary.DictFatherVlueGatewayRes;
import com.xiaowei.sys.platform.gateway.res.dictionary.DictionaryTreeRe;
import com.yeecli.component.common.model.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private DictionaryIntegration dictionaryIntegration;
//    @Autowired
//    private DictionaryService dictionaryService;


    @Override
    public Result dictionaryTreeResult() {

        Result result = dictionaryIntegration.dictionaryTreeList();
        List<DictionaryTreeVo> dictionaryTreeVos = (List<DictionaryTreeVo>) result.getData();
        List<DictionaryTreeRe> dictionaryTreeRe = new ArrayList<DictionaryTreeRe>();
        if (!CollectionUtils.isEmpty(dictionaryTreeVos)) {
            dictionaryTreeVos.forEach(aa -> {
                DictionaryTreeRe bb = new DictionaryTreeRe();
                BeanUtils.copyProperties(aa, bb);
                dictionaryTreeRe.add(bb);
            });
            result.setData(dictionaryTreeRe);
        } else {
            result.setData(dictionaryTreeRe);
        }

        return result;

    }

    /**
     * 新增字典
     *
     * @param dictionaryInsertReq
     * @return
     */
    @Override
    public Result<Object> saveDictionary(DictionaryInsertReq dictionaryInsertReq) {
        DictionaryAddReq dictionaryAddReq = new DictionaryAddReq();
        BeanUtils.copyProperties(dictionaryInsertReq, dictionaryAddReq);
        return dictionaryIntegration.saveDictionary(dictionaryAddReq);
    }

    /**
     * 编辑字典
     *
     * @param dictionaryUpdateReq
     * @return
     */
    @Override
    public Result updateDiction(DictionaryUpdateReq dictionaryUpdateReq) {
        DictionaryReq dictionaryReq = new DictionaryReq();
        BeanUtils.copyProperties(dictionaryUpdateReq, dictionaryReq);
        return dictionaryIntegration.updateDictionary(dictionaryReq);
    }

    @Override
    public Result delDictionary(DictionaryDelReq dictionaryDelReq) {
        DictionaryReq dictionaryReq = new DictionaryReq();
        BeanUtils.copyProperties(dictionaryDelReq, dictionaryReq);
        return dictionaryIntegration.delDictionary(dictionaryReq);
    }

    @Override
    public Result getDictionaryInfoByUuid(String uuid) {
        return dictionaryIntegration.getDictionaryInfoByUuid(uuid);
    }

    @Override
    public Result<Map<String, List<DictFatherVlueGatewayRes>>> getChildDictByCodevalue(List<String> codeValue) {
        Map<String, List<DictFatherVlueGatewayRes>> map = Maps.newHashMap();
        Result<Map<String, List<DictFatherVlueVO>>> result = dictionaryIntegration.getChildDictByCodevalue(codeValue);
        if (result.isSuccess()) {
            Map<String, List<DictFatherVlueVO>> listMap = result.getData();
            if (CollectionUtils.isEmpty(codeValue)) {
                return Result.wrapSuccessfulResult(map);
            }
            codeValue.forEach(s -> {
                List<DictFatherVlueGatewayRes> dictFatherVlueGatewayRes = Lists.newArrayList();
                List<DictFatherVlueVO> dictFatherVlueVOS = listMap.get(s);
                if (CollectionUtils.isEmpty(dictFatherVlueVOS)) {
                    map.put(s, dictFatherVlueGatewayRes);
                } else {

                    dictFatherVlueVOS.forEach(dictFatherVlueVO -> {
                        DictFatherVlueGatewayRes vlueGatewayRes = new DictFatherVlueGatewayRes();
                        BeanUtils.copyProperties(dictFatherVlueVO, vlueGatewayRes);
                        dictFatherVlueGatewayRes.add(vlueGatewayRes);
                    });
                }
                map.put(s, dictFatherVlueGatewayRes);
            });
            return Result.wrapSuccessfulResult(map);
        }
        return Result.wrapErrorResult(result.getCode(), result.getMessage());
    }

    @Override
    public Result<DictAttrListPagingRes> dictAttrListPagingVO(DictAttrListGWReq dictAttrListGWReq) {
        DictAttrListReq dictAttrListReq = new DictAttrListReq();


        //最终返回对象
        DictAttrListPagingRes dictAttrListPagingRes = new DictAttrListPagingRes();
        BeanUtils.copyProperties(dictAttrListGWReq, dictAttrListPagingRes);
        BeanUtils.copyProperties(dictAttrListGWReq, dictAttrListReq);
        // 对象所需数据
        List<DictAttrListRes> dictAttrListRes = Lists.newArrayList();

        //得到 服务生产者数据
        Result<DictAttrListPagingVO> result = dictionaryIntegration.dictAttrListPagingVO(dictAttrListReq);
        if (result.isSuccess()) {
            DictAttrListPagingVO dictAttrListPagingVO=result.getData();
            List<DictAttrListVO> dictAttrListVOS = dictAttrListPagingVO.getDatas();
            if (!CollectionUtils.isEmpty(dictAttrListVOS)){
                dictAttrListVOS.forEach(dictAttrListVO -> {
                    DictAttrListRes dictAttrListRes1=new DictAttrListRes();
                    BeanUtils.copyProperties(dictAttrListVO,dictAttrListRes1);
                    dictAttrListRes.add(dictAttrListRes1);
                });
                dictAttrListPagingRes.setDatas(dictAttrListRes);
            }
            dictAttrListPagingRes.setTotal(dictAttrListPagingVO.getTotal());
            return Result.wrapSuccessfulResult(dictAttrListPagingRes);
        }
        return Result.wrapErrorResult(result.getCode(),result.getMessage());
    }
}
