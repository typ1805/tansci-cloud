package com.tansci.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.admin.api.domain.SysMenuRole;
import com.tansci.admin.api.domain.SysRole;
import com.tansci.admin.api.domain.SysUser;
import com.tansci.admin.api.domain.SysUserRole;
import com.tansci.admin.api.dto.SysRoleDto;
import com.tansci.admin.mapper.SysRoleMapper;
import com.tansci.admin.service.SysMenuRoleService;
import com.tansci.admin.service.SysRoleService;
import com.tansci.admin.service.SysUserRoleService;
import com.tansci.admin.service.SysUserService;
import com.tansci.common.core.enums.Enums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName： SysRoleServiceImpl.java
 * @ClassPath： com.tansci.admin.service.impl.SysRoleServiceImpl.java
 * @Description： 权限
 * @Author： tanyp
 * @Date： 2022/4/25 16:32
 **/
@Slf4j
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysMenuRoleService sysMenuRoleService;

    @Override
    public IPage<SysRole> page(Page page, SysRoleDto dto) {
        IPage<SysRole> roleIPage = this.baseMapper.selectPage(page,
                Wrappers.<SysRole>lambdaQuery()
                        .eq(Objects.nonNull(dto.getStatus()), SysRole::getStatus, dto.getStatus())
                        .like(Objects.nonNull(dto.getName()), SysRole::getName, dto.getName())
                        .orderByDesc(SysRole::getUpdateTime)
        );

        if (Objects.nonNull(roleIPage.getRecords()) && roleIPage.getRecords().size() > 0) {
            List<SysUser> users = sysUserService.listByIds(roleIPage.getRecords().stream().map(SysRole::getCreator).distinct().collect(Collectors.toList()));
            roleIPage.getRecords().forEach(item -> {
                Optional<SysUser> uOptional = users.stream().filter(u -> Objects.equals(u.getId(), item.getCreator())).findFirst();
                if (uOptional.isPresent()) {
                    item.setCreatorName(uOptional.get().getNickname());
                }

                if (Objects.nonNull(item.getStatus())) {
                    item.setStatusName(Enums.getVlaueByGroup(item.getStatus(), "status"));
                }
            });
        }
        return roleIPage;
    }

    @Override
    public List<SysRole> list(SysRole role) {
        return this.baseMapper.selectList(
                Wrappers.<SysRole>lambdaQuery()
                        .eq(Objects.nonNull(role.getStatus()), SysRole::getStatus, role.getStatus())
                        .eq(Objects.nonNull(role.getCreator()), SysRole::getCreator, role.getCreator())
                        .like(Objects.nonNull(role.getName()), SysRole::getName, role.getName())
                        .orderByDesc(SysRole::getUpdateTime)
        );
    }

    @Transactional
    @Override
    public Object delete(SysRoleDto dto) {
        int row = this.baseMapper.deleteById(dto.getId());
        if (row > 0) {
            sysMenuRoleService.remove(Wrappers.<SysMenuRole>lambdaQuery().eq(SysMenuRole::getRoleId, dto.getId()));
            sysUserRoleService.remove(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getRoleId, dto.getId()));
        }
        return row;
    }

}
