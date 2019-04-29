package com.xiaowei.sys.platform.gateway.req.dictionary;

import com.sun.xml.internal.rngom.parse.host.Base;
import com.xiaowei.sys.platform.gateway.exception.BizException;
import com.xiaowei.sys.platform.gateway.exception.ErrorEnum;
import com.xiaowei.sys.platform.gateway.req.BaseReq;
import com.xiaowei.sys.platform.gateway.util.JcStringUtils;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MOMO on 2018/10/12.
 */
public class SelectListReq extends BaseReq implements Serializable {
    private List<String> selectKeys;

    public List<String> getSelectKeys() {
        return selectKeys;
    }

    public void setSelectKeys(List<String> selectKeys) {
        this.selectKeys = selectKeys;
    }

    /**
     * 检查参数是否合法
     *
     * @return
     */
    @Override
    public void checkData() {
        if (selectKeys == null || selectKeys.size() == 0) {
            throw new BizException(ErrorEnum.ERROR_PARAM);
        }

        for (String key : selectKeys) {
            if (JcStringUtils.isNullOrBlank(key)) {
                throw new BizException(ErrorEnum.ERROR_PARAM, "selectKey 不能为空");
            }
        }
    }
}
