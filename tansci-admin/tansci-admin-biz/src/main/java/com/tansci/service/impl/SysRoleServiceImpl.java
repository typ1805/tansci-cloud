package com.kuiper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kuiper.domain.*;
import com.kuiper.dto.SysRoleDto;
import com.kuiper.mapper.SysRoleMapper;
import com.kuiper.service.*;
import com.kuiper.utils.UserInfoContext;
import com.kuiper.vo.SysMenuRoleVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName： SysRoleServiceImpl.java
 * @ClassPath： com.kuiper.service.impl.SysRoleServiceImpl.java
 * @Description： 权限
 * @Author： tanyp
 * @Date： 2022/4/25 16:32
 **/
@Slf4j
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysMenuRoleService sysMenuRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysOrgRoleService sysOrgRoleService;
    @Autowired
    private SysUserOrgService sysUserOrgService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysOrgService sysOrgService;

    @Override
    public IPage<SysRole> page(Page page, SysRoleDto dto) {
        LambdaQueryWrapper queryWrapper = null;
        if (Objects.equals(0, UserInfoContext.getUser().getType())) {
            queryWrapper = Wrappers.<SysRole>lambdaQuery()
                    .like(Objects.nonNull(dto.getName()), SysRole::getName, dto.getName())
                    .orderByDesc(SysRole::getCreateTime, SysRole::getUpdateTime);
        } else {
            // 获取组织id集合
            List<SysOrg> orgList = sysOrgService.getOrgChildrens(UserInfoContext.getUser().getOrgId());
            List<String> orgIds = new ArrayList<>();
            orgIds.add(UserInfoContext.getUser().getOrgId());
            if (Objects.nonNull(orgList) && orgList.size() > 0) {
                orgIds.addAll(orgList.stream().map(SysOrg::getId).collect(Collectors.toList()));
            }

            List<SysUserOrg> orgs = sysUserOrgService.list(Wrappers.<SysUserOrg>lambdaQuery().in(SysUserOrg::getOrgId, orgIds));
            List<String> userIds = new ArrayList<>();
            if (Objects.nonNull(orgs) && orgs.size() > 0) {
                userIds = orgs.stream().map(SysUserOrg::getUserId).distinct().collect(Collectors.toList());
            } else {
                userIds.add(UserInfoContext.getUser().getId());
            }
            queryWrapper = Wrappers.<SysRole>lambdaQuery()
                    .in(SysRole::getCreator, userIds)
                    .like(Objects.nonNull(dto.getName()), SysRole::getName, dto.getName())
                    .orderByDesc(SysRole::getCreateTime, SysRole::getUpdateTime);
        }
        IPage<SysRole> roleIPage = this.baseMapper.selectPage(page, queryWrapper);
        return roleIPage;
    }

    @Override
    public List<SysRole> list(SysRole role) {
        LambdaQueryWrapper queryWrapper = null;
        if (Objects.equals(0, UserInfoContext.getUser().getType())) {
            queryWrapper = Wrappers.<SysRole>lambdaQuery()
                    .like(Objects.nonNull(role.getName()), SysRole::getName, role.getName())
                    .orderByDesc(SysRole::getCreateTime, SysRole::getUpdateTime);
        } else {
            // 获取组织id集合
            List<SysOrg> orgList = sysOrgService.getOrgChildrens(UserInfoContext.getUser().getOrgId());
            List<String> orgIds = new ArrayList<>();
            orgIds.add(UserInfoContext.getUser().getOrgId());
            if (Objects.nonNull(orgList) && orgList.size() > 0) {
                orgIds.addAll(orgList.stream().map(SysOrg::getId).collect(Collectors.toList()));
            }

            List<SysUserOrg> orgs = sysUserOrgService.list(Wrappers.<SysUserOrg>lambdaQuery().in(SysUserOrg::getOrgId, orgIds));
            List<String> userIds = new ArrayList<>();
            if (Objects.nonNull(orgs) && orgs.size() > 0) {
                userIds = orgs.stream().map(SysUserOrg::getUserId).distinct().collect(Collectors.toList());
            } else {
                userIds.add(UserInfoContext.getUser().getId());
            }
            queryWrapper = Wrappers.<SysRole>lambdaQuery()
                    .in(SysRole::getCreator, userIds)
                    .like(Objects.nonNull(role.getName()), SysRole::getName, role.getName())
                    .orderByDesc(SysRole::getCreateTime, SysRole::getUpdateTime);
        }
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public boolean save(SysRole sysRole) {
        sysRole.setCreateTime(LocalDateTime.now());
        sysRole.setUpdateTime(LocalDateTime.now());
        sysRole.setCreator(UserInfoContext.getUser().getId());
        this.baseMapper.insert(sysRole);
        return false;
    }

    @Override
    public Object delete(SysRoleDto dto) {
        return this.baseMapper.deleteById(dto.getId());
    }

    @Override
    public Object update(SysRole sysRole) {
        sysRole.setUpdateTime(LocalDateTime.now());
        return this.baseMapper.updateById(sysRole);
    }

    @Override
    public List<SysMenuRoleVo> menuRoleList(SysMenuRole role) {
        List<SysMenuRoleVo> roleVos = new ArrayList<>();
        if (!Objects.equals(0, UserInfoContext.getUser().getType())) {
            role.setThisRoleId(UserInfoContext.getUser().getRoleId());
            roleVos = this.baseMapper.menuRoleList(role);

            if (Objects.nonNull(roleVos)) {
                // 获取所有菜单的父id
                List<String> parentIds = roleVos.stream().map(SysMenuRoleVo::getParentId).distinct().collect(Collectors.toList());
                if (Objects.nonNull(parentIds) && parentIds.size() > 0) {
                    List<SysMenu> parentList = sysMenuService.list(Wrappers.<SysMenu>lambdaQuery().in(SysMenu::getId, parentIds));
                    List<SysMenuRoleVo> menuRoleVos = new ArrayList<>();
                    parentList.forEach(item -> {
                        menuRoleVos.add(
                                SysMenuRoleVo.builder()
                                        .id(item.getId())
                                        .parentId(item.getParentId())
                                        .name(item.getName())
                                        .code(item.getCode())
                                        .sort(item.getSort())
                                        .build()
                        );
                    });
                    roleVos.addAll(menuRoleVos);
                }
            }
        } else {
            roleVos = this.baseMapper.menuRoleList(role);
        }

        roleVos = roleVos.stream().distinct().collect(Collectors.toList());
        Map<String, List<SysMenuRoleVo>> map = roleVos.stream().collect(Collectors.groupingBy(SysMenuRoleVo::getParentId, Collectors.toList()));
        roleVos.stream().forEach(item -> item.setChildren(map.get(item.getId())));
        return map.get("0");
    }

    @Transient
    @Override
    public Boolean menuRoleAdd(SysRoleDto dto) {
        sysMenuRoleService.remove(Wrappers.<SysMenuRole>lambdaQuery().eq(SysMenuRole::getRoleId, dto.getRoleId()));
        List<SysMenuRole> roles = new ArrayList<>();
        dto.getMenuIds().forEach(id -> {
            roles.add(SysMenuRole.builder().roleId(dto.getRoleId()).menuId(id).build());
        });
        return sysMenuRoleService.saveBatch(roles);
    }

    @Override
    public Boolean userRoleAdd(SysRoleDto dto) {
        sysUserRoleService.remove(Wrappers.<SysUserRole>lambdaUpdate().eq(SysUserRole::getUserId, dto.getUserId()));
        return sysUserRoleService.save(SysUserRole.builder().roleId(dto.getRoleId()).userId(dto.getUserId()).build());
    }

    @Override
    public Boolean orgRoleAdd(SysRoleDto dto) {
        sysOrgRoleService.remove(Wrappers.<SysOrgRole>lambdaUpdate().eq(SysOrgRole::getOrgId, dto.getUserId()));
        return sysOrgRoleService.save(SysOrgRole.builder().roleId(dto.getRoleId()).orgId(dto.getOrgId()).build());
    }

}
