package com.tansci.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.admin.api.domain.SysMenu;
import com.tansci.admin.api.dto.SysMenuDto;

import java.util.List;

/**
 * @path：com.tansci.admin.service.SysMenuService.java
 * @className：SysMenuService.java
 * @description：菜单
 * @author：tanyp
 * @dateTime：2022/2/15 13:49
 * @editNote：
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> list(SysMenuDto dto);

    Integer delete(SysMenuDto dto);

}

