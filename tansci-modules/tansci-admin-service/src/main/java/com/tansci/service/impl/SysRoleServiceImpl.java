package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.SysRole;
import com.tansci.dto.SysRoleDto;
import com.tansci.mapper.SysRoleMapper;
import com.tansci.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName： SysRoleServiceImpl.java
 * @ClassPath： com.tansci.service.impl.SysRoleServiceImpl.java
 * @Description： 权限
 * @Author： tanyp
 * @Date： 2022/4/25 16:32
 **/
@Slf4j
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public IPage<SysRole> page(Page page, SysRoleDto dto) {
        IPage<SysRole> roleIPage = this.baseMapper.selectPage(page,
                Wrappers.<SysRole>lambdaQuery()
                        .eq(Objects.nonNull(dto.getStatus()), SysRole::getStatus, dto.getStatus())
                        .like(Objects.nonNull(dto.getName()), SysRole::getName, dto.getName())
                        .orderByDesc(SysRole::getUpdateTime)
        );
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

}
