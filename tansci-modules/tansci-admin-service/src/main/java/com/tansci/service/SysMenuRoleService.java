package com.tansci.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.SysMenuRole;

/**
 * @ClassName： SysMenuRoleService.java
 * @ClassPath： com.tansci.service.SysMenuRoleService.java
 * @Description： 菜单角色
 * @Author： tanyp
 * @Date： 2022/4/25 16:25
 **/
public interface SysMenuRoleService extends IService<SysMenuRole> {

    Object menuRoleSave(SysMenuRole sysMenuRole);

}
