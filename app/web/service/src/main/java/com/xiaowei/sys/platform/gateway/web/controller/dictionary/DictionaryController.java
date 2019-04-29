package com.xiaowei.sys.platform.gateway.web.controller.dictionary;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.xiaowei.sys.platform.gateway.exception.ErrorEnum;
import com.xiaowei.sys.platform.gateway.req.RedisUser;
import com.xiaowei.sys.platform.gateway.req.dictionary.*;
import com.xiaowei.sys.platform.gateway.service.dictionary.DictionaryService;
import com.xiaowei.sys.platform.gateway.web.controller.BaseController;
import com.xiaowei.sys.platform.gateway.web.model.res.JsonResVo;
import com.yeecli.component.common.model.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by kanshunfu on 2018/9/11.
 */
@RestController
@RequestMapping("/api/sys/dictionary/")
public class DictionaryController extends BaseController {
    private final static Logger logger = Logger.getLogger(DictionaryController.class);

    @Autowired
    private DictionaryService dictionaryService;

    @PostMapping("/treeList/v1")
    public JsonResVo treeList() {
        try {
            Result result = dictionaryService.dictionaryTreeResult();
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            } else {
                return JsonResVo.buildErrorResult(2, result.getMessage());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_DICTIONARY_TREE_FAIL.getErrorCode(), ErrorEnum.ERROR_DICTIONARY_TREE_FAIL.getErrorMessage());
        }
    }

    @PostMapping(path = "/add/v1")

    public JsonResVo add(@Validated(DictionaryInsertReq.Add.class) @RequestBody DictionaryInsertReq dictionaryInsertReq, HttpServletRequest request, HttpServletResponse response) {
        try {
            RedisUser baseReq = this.getUserInfoFromRedis(request, response);
            BeanUtils.copyProperties(baseReq, dictionaryInsertReq);
            Result result = dictionaryService.saveDictionary(dictionaryInsertReq);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            } else {
                return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_DICTIONARY_NAME_EXIST_FAIL.getErrorCode(), ErrorEnum.ERROR_DICTIONARY_NAME_EXIST_FAIL.getErrorMessage());
        }

    }

    @PostMapping(path = "/edit/v1")
    @ResponseBody
    public JsonResVo edit(@Validated(DictionaryUpdateReq.Modify.class) @RequestBody DictionaryUpdateReq dictionaryUpdateReq,HttpServletRequest request, HttpServletResponse response) {
        try {
            RedisUser baseReq = this.getUserInfoFromRedis(request, response);
            BeanUtils.copyProperties(baseReq, dictionaryUpdateReq);
            Result result = dictionaryService.updateDiction(dictionaryUpdateReq);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            } else {
                return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_UPDATE_DICTIONARY_NO_EXIST_FAIL.getErrorCode(), ErrorEnum.ERROR_UPDATE_DICTIONARY_NO_EXIST_FAIL.getErrorMessage());
        }

    }

    @PostMapping(path = "del/v1")
    public JsonResVo del(@Validated(DictionaryDelReq.Delete.class) @RequestBody DictionaryDelReq dictionaryDelReq,HttpServletRequest request, HttpServletResponse response) {
        try {
            RedisUser baseReq = this.getUserInfoFromRedis(request, response);
            BeanUtils.copyProperties(baseReq, dictionaryDelReq);
            Result result = dictionaryService.delDictionary(dictionaryDelReq);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            } else {
                return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_DEL_DICTIONARY_EXIST_USER_FAIL.getErrorCode(), ErrorEnum.ERROR_DEL_DICTIONARY_EXIST_USER_FAIL.getErrorMessage());
        }
    }

    @PostMapping(path = "/infoByUuId/v1")
    public JsonResVo infoByUuid(@Validated(DictionaryDelReq.Delete.class) @RequestBody DictionaryDelReq dictionaryDelReq) {
        Result result = null;
        try {
            result = dictionaryService.getDictionaryInfoByUuid(dictionaryDelReq.getUuid());
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_DICTIONARY_TREE_FAIL.getErrorCode(), ErrorEnum.ERROR_DICTIONARY_TREE_FAIL.getErrorMessage());
        }
    }
    @PostMapping(path = "/getChildDictByCodevalue/v1")
    public JsonResVo getChildDictByCodevalue( @RequestBody SelectListReq selectListReq) {
        Result result = null;
        try {
            List<String> selectKeys = selectListReq.getSelectKeys();
            result = dictionaryService.getChildDictByCodevalue(selectKeys);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_CHILD_DICT_ERROR.getErrorCode(), ErrorEnum.ERROR_CHILD_DICT_ERROR.getErrorMessage());
        }
    }
    @PostMapping(path = "/dictAttrListPagingVO/v1")
    public JsonResVo dictAttrListPagingVO(@Validated(DictAttrListGWReq.Query.class) @RequestBody DictAttrListGWReq dictAttrListGWReq) {
        Result result = null;
        try {
            result = dictionaryService.dictAttrListPagingVO(dictAttrListGWReq);
            if (result.isSuccess()) {
                return JsonResVo.buildSuccess(result.getData());
            }
            return JsonResVo.buildErrorResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResVo.buildErrorResult(ErrorEnum.ERROR_CHILD_DICT_ERROR.getErrorCode(), ErrorEnum.ERROR_CHILD_DICT_ERROR.getErrorMessage());
        }
    }


    public static void main(String[] args) {
        SelectListReq selectListReq=new SelectListReq();
        List<String> selectKeys= Lists.newArrayList();
        selectKeys.add("a");
        selectListReq.setSelectKeys(selectKeys);
        System.out.println(JSONObject.toJSONString(selectListReq, SerializerFeature.WriteNullListAsEmpty));
    }
}
