package com.tansci.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.base.UserInfo;
import com.tansci.domain.SysMenu;
import com.tansci.dto.SysMenuDto;
import com.tansci.mapper.SysMenuMapper;
import com.tansci.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @path：com.tansci.service.impl.SysMenuServiceImpl.java
 * @className：SysMenuServiceImpl.java
 * @description：菜单
 * @author：tanyp
 * @dateTime：2022/2/15 13:51
 * @editNote：
 */
@Slf4j
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Override
    public List<SysMenu> list(SysMenuDto dto) {
        List<SysMenu> list = this.baseMapper.list(dto);
        list = list.stream().distinct().collect(Collectors.toList());

        // 组装树型数据
        Map<String, List<SysMenu>> map = list.stream().collect(Collectors.groupingBy(SysMenu::getParentId, Collectors.toList()));
        list.stream().forEach(item -> item.setChildren(map.get(item.getId())));

        List<SysMenu> sysMenus = map.get("0");

        return sysMenus;
    }

    @Override
    public List<SysMenu> treeList(SysMenuDto dto) {
        // 用户权限
        if (Objects.isNull(dto.getUserId()) || Objects.equals("", dto.getUserId())) {
//            UserInfo user = UserInfoContext.getUser();
//            if (Objects.nonNull(user) && !Objects.equals(1, user.getType())) {
//                dto.setUserId(user.getId());
//            }
        }

        List<SysMenu> list = this.baseMapper.treeList(dto);
        list = list.stream().distinct().collect(Collectors.toList());

        // 组装树型数据
        Map<String, List<SysMenu>> map = list.stream().collect(Collectors.groupingBy(SysMenu::getParentId, Collectors.toList()));
        list.stream().forEach(item -> item.setChildren(map.get(item.getId())));

        List<SysMenu> sysMenus = map.get("0");
        return sysMenus;
    }

    @Override
    public Integer delete(SysMenuDto dto) {
        List<String> ids = new ArrayList<>();
        ids.add(dto.getId());

        List<SysMenu> menus = this.baseMapper.getMenuChildrens(dto.getId());
        if (Objects.nonNull(menus) && menus.size() > 0) {
            ids.addAll(menus.stream().map(SysMenu::getId).collect(Collectors.toList()));
        }
        return this.baseMapper.deleteBatchIds(ids);
    }

}