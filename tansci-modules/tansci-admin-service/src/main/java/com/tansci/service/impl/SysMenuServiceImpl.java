package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.SysMenu;
import com.tansci.dto.SysMenuDto;
import com.tansci.mapper.SysMenuMapper;
import com.tansci.service.SysMenuService;
import com.tansci.utils.UserInfoContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
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
        // 用户权限
        dto.setUserType(UserInfoContext.getUser().getType());
        if (Objects.isNull(dto.getRoleId()) && !Objects.equals(1, UserInfoContext.getUser().getType())) {
            dto.setRoleId(UserInfoContext.getUser().getRoleId());
        }
        List<SysMenu> list = this.baseMapper.list(dto);

        if (Objects.nonNull(dto.getRoleId())) {
            // 获取所有菜单的父id
            List<String> parentIds = list.stream().map(SysMenu::getParentId).distinct().collect(Collectors.toList());
            if (Objects.nonNull(parentIds) && parentIds.size() > 0) {
                List<SysMenu> parentList = this.list(
                        Wrappers.<SysMenu>lambdaQuery().in(SysMenu::getId, parentIds)
                                .eq(Objects.nonNull(dto.getType()), SysMenu::getType, dto.getType())
                                .eq(Objects.nonNull(dto.getStatus()), SysMenu::getStatus, dto.getStatus())
                );
                list.addAll(parentList);
            }
        }

        list = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(SysMenu::getId))), ArrayList::new));
        Map<String, List<SysMenu>> map = list.stream().collect(Collectors.groupingBy(SysMenu::getParentId, Collectors.toList()));
        list.stream().forEach(item -> item.setChildren(map.get(item.getId())));

        List<SysMenu> menuList = map.get("0").stream().sorted(Comparator.comparing(SysMenu::getSort)).collect(Collectors.toList());
        return menuList;
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