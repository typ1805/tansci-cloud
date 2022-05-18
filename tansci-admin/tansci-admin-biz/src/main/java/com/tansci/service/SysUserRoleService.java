package com.tansci.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.SysUserRole;

/**
 * @ClassName： SysUserRoleService.java
 * @ClassPath： com.tansci.service.SysUserRoleService.java
 * @Description： 用户角色
 * @Author： tanyp
 * @Date： 2022/4/25 16:29
 **/
public interface SysUserRoleService extends IService<SysUserRole> {

    Object userRoleSave(SysUserRole sysUserRole);

}
