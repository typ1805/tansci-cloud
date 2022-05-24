package com.tansci.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.SysRole;
import com.tansci.dto.SysRoleDto;

import java.util.List;

/**
 * @ClassName： SysRoleService.java
 * @ClassPath： com.tansci.service.SysRoleService.java
 * @Description： 权限
 * @Author： tanyp
 * @Date： 2022/4/25 16:28
 **/
public interface SysRoleService extends IService<SysRole> {

    IPage<SysRole> page(Page page, SysRoleDto dto);

    List<SysRole> list(SysRole role);

}
