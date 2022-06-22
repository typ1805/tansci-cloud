package com.tansci.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.admin.api.domain.SysMenuRole;
import com.tansci.admin.mapper.SysMenuRoleMapper;
import com.tansci.admin.service.SysMenuRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName： SysMenuRoleServiceImpl.java
 * @ClassPath： com.tansci.admin.service.impl.SysMenuRoleServiceImpl.java
 * @Description： 菜单角色
 * @Author： tanyp
 * @Date： 2022/4/25 16:35
 **/
@Slf4j
@Service
public class SysMenuRoleServiceImpl extends ServiceImpl<SysMenuRoleMapper, SysMenuRole> implements SysMenuRoleService {

    @Override
    public Object menuRoleSave(SysMenuRole sysMenuRole) {
        this.baseMapper.delete(Wrappers.<SysMenuRole>lambdaUpdate().eq(SysMenuRole::getRoleId, sysMenuRole.getRoleId()));
        List<SysMenuRole> list = new ArrayList<>();
        sysMenuRole.getMenuIds().forEach(item -> {
            list.add(SysMenuRole.builder().roleId(sysMenuRole.getRoleId()).menuId(item).build());
        });
        return list.size() > 0 ? this.saveBatch(list) : null;
    }

}
