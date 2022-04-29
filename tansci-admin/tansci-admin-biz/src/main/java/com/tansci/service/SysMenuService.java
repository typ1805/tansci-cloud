package com.tansci.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.SysMenu;
import com.tansci.dto.SysMenuDto;

import java.util.List;

/**
 * @path：com.tansci.service.SysMenuService.java
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

