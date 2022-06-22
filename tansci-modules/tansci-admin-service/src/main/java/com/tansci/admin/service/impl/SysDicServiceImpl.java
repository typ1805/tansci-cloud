package com.tansci.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.admin.api.domain.SysDic;
import com.tansci.admin.api.dto.SysDicDto;
import com.tansci.admin.mapper.SysDicMapper;
import com.tansci.admin.service.SysDicService;
import com.tansci.common.core.enums.Enums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @path：com.tansci.admin.service.impl.SysDicServiceImpl.java
 * @className：SysDicServiceImpl.java
 * @description：字典
 * @author：tanyp
 * @dateTime：2022/2/15 13:51
 * @editNote：
 */
@Slf4j
@Service
public class SysDicServiceImpl extends ServiceImpl<SysDicMapper, SysDic> implements SysDicService {

    @Override
    public List<SysDic> dicList(SysDicDto dto) {
        List<SysDic> dicList = this.baseMapper.selectList(
                Wrappers.<SysDic>lambdaQuery()
                        .eq(Objects.nonNull(dto.getParentId()), SysDic::getParentId, dto.getParentId())
                        .eq(Objects.nonNull(dto.getGroupName()), SysDic::getGroupName, dto.getGroupName())
                        .eq(Objects.nonNull(dto.getType()), SysDic::getType, dto.getType())
        );

        List<SysDic> newDicList = dicList.stream().filter(item -> "0".equals(item.getParentId())).map(item -> {
            item.setChildren(this.getChildrens(item, dicList));
            item.setTypeName(item.getType() == null ? "" : Enums.getVlaueByGroup(item.getType(), "dic_type"));
            return item;
        }).collect(Collectors.toList());
        return newDicList.size() > 0 ? newDicList : dicList;
    }


    /**
     * @author: tanyp
     * @Date: 2022/5/20
     * @Description:封装树形数据
     */
    public List<SysDic> getChildrens(SysDic dic, List<SysDic> list) {
        List<SysDic> treeDic = list.stream().filter(item -> Objects.equals(item.getParentId(), dic.getId())).map(item -> {
            // 递归添加子数据
            List<SysDic> childrens = getChildrens(item, list);
            item.setChildren(childrens);
            item.setTypeName(item.getType() == null ? "" : Enums.getVlaueByGroup(item.getType(), "dic_type"));
            return item;
        }).collect(Collectors.toList());
        return treeDic;
    }

    @Override
    public boolean del(String id) {
        List<String> ids = new ArrayList<>();
        ids.add(id);
        List<SysDic> dicList = this.baseMapper.getDicChildrens(id);
        if (Objects.nonNull(dicList) && dicList.size() > 0) {
            ids.addAll(dicList.stream().map(SysDic::getId).collect(Collectors.toList()));
        }
        this.baseMapper.deleteBatchIds(ids);
        return true;
    }
}