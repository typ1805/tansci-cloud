package com.tansci.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.admin.api.domain.SysUserRole;
import com.tansci.admin.mapper.SysUserRoleMapper;
import com.tansci.admin.service.SysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName： SysUserRoleServiceImpl.java
 * @ClassPath： com.tansci.admin.service.impl.SysUserRoleServiceImpl.java
 * @Description： 用户角色
 * @Author： tanyp
 * @Date： 2022/4/25 16:30
 **/
@Slf4j
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Override
    public Object userRoleSave(SysUserRole sysUserRole) {
        this.baseMapper.delete(Wrappers.<SysUserRole>lambdaUpdate().eq(SysUserRole::getUserId, sysUserRole.getUserId()));
        return this.baseMapper.insert(sysUserRole);
    }

}
