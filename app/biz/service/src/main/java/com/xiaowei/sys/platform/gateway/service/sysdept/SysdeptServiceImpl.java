package com.xiaowei.sys.platform.gateway.service.sysdept;

import com.google.common.collect.Lists;
import com.xiaowei.sys.platform.core.facade.service.req.sysdept.SysDeptReq;
import com.xiaowei.sys.platform.core.facade.service.vo.sysdept.SysDeptTreeVO;
import com.xiaowei.sys.platform.core.facade.service.vo.sysdept.SysDeptVO;
import com.xiaowei.sys.platform.gateway.integration.sysdept.SysDeptIntegration;
import com.xiaowei.sys.platform.gateway.req.sysdept.SysdeptDelReq;
import com.xiaowei.sys.platform.gateway.req.sysdept.SysdeptInsertReq;
import com.xiaowei.sys.platform.gateway.req.sysdept.SysdeptUpdateReq;
import com.xiaowei.sys.platform.gateway.res.sysdept.SysDeptTreeRes;
import com.yeecli.component.common.model.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by MOMO on 2018/9/11.
 */
@Component
public class SysdeptServiceImpl implements SysdeptService {

    @Autowired
    private SysDeptIntegration sysDeptIntegration;

    @Override
    public Result<List<SysDeptTreeRes>> deptTreeResult() {
        Result<List<SysDeptTreeVO>> result = sysDeptIntegration.deptTreeList();
        List<SysDeptTreeRes> res= Lists.newArrayList();
        if (result.isSuccess()) {
            List<SysDeptTreeVO> sysDeptTreeVOS = result.getData();
            if (CollectionUtils.isEmpty(sysDeptTreeVOS)){
                return Result.wrapSuccessfulResult(res);
            }
            sysDeptTreeVOS.forEach(sysDeptTreeVO -> {
                SysDeptTreeRes res1=new SysDeptTreeRes();
                BeanUtils.copyProperties(sysDeptTreeVO,res1);
                res.add(res1);
            });
            return Result.wrapSuccessfulResult(res);
        }
        return Result.wrapErrorResult(result.getCode(),result.getMessage());
    }

    @Override
    public Result<Object> insertDept(SysdeptInsertReq sysdeptInsertReq) {
        SysDeptReq sysDeptReq = new SysDeptReq();
        BeanUtils.copyProperties(sysdeptInsertReq, sysDeptReq);
        return sysDeptIntegration.insertDept(sysDeptReq);
    }

    @Override
    public Result<Object> updateDept(SysdeptUpdateReq sysdeptUpdateReq) {
        SysDeptReq sysDeptReq = new SysDeptReq();
        BeanUtils.copyProperties(sysdeptUpdateReq, sysDeptReq);
        return sysDeptIntegration.updateDept(sysDeptReq);
    }

    @Override
    public Result<Object> delDept(SysdeptDelReq sysdeptDelReq) {
        SysDeptReq sysDeptReq = new SysDeptReq();
        BeanUtils.copyProperties(sysdeptDelReq, sysDeptReq);
        return sysDeptIntegration.delDept(sysDeptReq);
    }

    @Override
    public Result getDeptInfoByUuid(String deptUuid) {
        Result result = sysDeptIntegration.getDeptInfoByUuid(deptUuid);
        if (result.isSuccess()) {
            Object a = result.getData();
            return Result.wrapSuccessfulResult(result.getData());
        }
        return result;
    }
    /* @Override
    public Result deptTreeResult() {
        try {
            Result<List<SysDeptVo>> result = sysDeptIntegration.deptTreeList();
            //递归树最终结果集
            //将deptList拷贝到dtoList
            List<SysDeptTreeRes> sysDeptTreeRes = new ArrayList<>(0);
            if (result.isSuccess()) {
                //得到所有部门
                List<SysDeptVo> deptVos = result.getData();
                if (deptVos != null && deptVos.size() != 0) {
                    deptVos.forEach(deptEntity -> {
                        //去除被删除的部门
                        if (deptEntity.getDelFlag().equals("0")) {
                            SysDeptTreeRes treeRes = new SysDeptTreeRes();
                            BeanUtils.copyProperties(deptEntity, treeRes);
                            sysDeptTreeRes.add(treeRes);
                        }
                    });
                }
                deptMenuToTree(sysDeptTreeRes);
                return Result.wrapSuccessfulResult(sysDeptTreeRes);
            } else {
                return Result.wrapErrorResult(result.getCode(), result.getMessage());
            }
        } catch (Exception e) {
            //logger.error(e.getMessage(), e);
            //暂时异常处理 返回正常出去 后续会改成 记录错误单据信息以及错误信息
            return Result.wrapErrorResult(ErrorEnum.SYSTEM_ERROR.getErrorCode(),
                    ErrorEnum.SYSTEM_ERROR.getErrorMessage() + ":" + e.getMessage());
        }
    }

    public List<SysDeptTreeRes> deptMenuToTree(List<SysDeptTreeRes> resList) {
        //集合为空直接返回
        if (CollectionUtils.isEmpty(resList)) {
            return Lists.newArrayList();
        }
        //可以放相同的key，比普通的map高级
        //把当前的tree以level为key 相同level的部门作为value，放到map里
        // level->[dept1,dept2,....]
        Multimap<String, SysDeptTreeRes> levelDeptMap = ArrayListMultimap.create();
        //用来保存第一层级的树,同时也是最终的部门树
        List<SysDeptTreeRes> rootList = Lists.newArrayList();
        for (SysDeptTreeRes treeRes : resList) {
            levelDeptMap.put(treeRes.getSysDeptLevel(), treeRes);
            if (LevelUtil.ROOT.equals(treeRes.getSysDeptLevel())) {
                rootList.add(treeRes);
            }
        }
        //按照 seq 从小到大 对部门树进行排序
        Collections.sort(rootList, new Comparator<SysDeptTreeRes>() {
            @Override
            public int compare(SysDeptTreeRes o1, SysDeptTreeRes o2) {
                return o1.getSysDeptSeq() - o2.getSysDeptSeq();
            }
        });
        //递归生成树
        transformDeptTree(rootList, LevelUtil.ROOT, levelDeptMap);
        return rootList;
    }

    private void transformDeptTree(List<SysDeptTreeRes> deptLevelList, String level, Multimap<String, SysDeptTreeRes> levelDeptMap) {
        int size = deptLevelList.size();
        for (int i = 0; i < size; i++) {
            //遍历该层的每个元素
            SysDeptTreeRes depetMenuDto = deptLevelList.get(i);
            //处理当前层级的数据
            String nexeLevel = LevelUtil.calculateLevel(level, depetMenuDto.getId());
            //处理下一层
            List<SysDeptTreeRes> tempDeptList = (List<SysDeptTreeRes>) levelDeptMap.get(nexeLevel);
            if (!CollectionUtils.isEmpty(tempDeptList)) {
                //排序
                Collections.sort(tempDeptList, deptSeqComparator);
                //设置下一层部门
                depetMenuDto.setChildren(tempDeptList);
                //进入到下一层处理
                transformDeptTree(tempDeptList, nexeLevel, levelDeptMap);
            }
        }
    }

    public Comparator<SysDeptTreeRes> deptSeqComparator = new Comparator<SysDeptTreeRes>() {
        @Override
        public int compare(SysDeptTreeRes o1, SysDeptTreeRes o2) {
            return o1.getSysDeptSeq() - o2.getSysDeptSeq();
        }
    };*/
}
