package com.tansci.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.admin.api.domain.SysMenuRole;

/**
 * @ClassName： SysMenuRoleService.java
 * @ClassPath： com.tansci.admin.service.SysMenuRoleService.java
 * @Description： 菜单角色
 * @Author： tanyp
 * @Date： 2022/4/25 16:25
 **/
public interface SysMenuRoleService extends IService<SysMenuRole> {

    Object menuRoleSave(SysMenuRole sysMenuRole);

}
