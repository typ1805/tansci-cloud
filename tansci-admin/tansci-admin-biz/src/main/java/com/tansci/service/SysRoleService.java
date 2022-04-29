package com.kuiper.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kuiper.domain.SysMenuRole;
import com.kuiper.domain.SysRole;
import com.kuiper.dto.SysRoleDto;
import com.kuiper.vo.SysMenuRoleVo;

import java.util.List;

/**
 * @ClassName： SysRoleService.java
 * @ClassPath： com.kuiper.service.SysRoleService.java
 * @Description： 权限
 * @Author： tanyp
 * @Date： 2022/4/25 16:28
 **/
public interface SysRoleService extends IService<SysRole> {

    IPage<SysRole> page(Page page, SysRoleDto dto);

    List<SysRole> list(SysRole role);

    boolean save(SysRole sysRole);

    Object delete(SysRoleDto dto);

    Object update(SysRole sysRole);

    List<SysMenuRoleVo> menuRoleList(SysMenuRole role);

    Boolean menuRoleAdd(SysRoleDto dto);

    Boolean userRoleAdd(SysRoleDto dto);

    Boolean orgRoleAdd(SysRoleDto dto);

}
