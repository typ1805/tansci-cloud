package com.tansci.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.SysMenu;
import com.tansci.dto.SysMenuDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @path：com.tansci.mapper.SysMenuMapper.java
 * @className：SysMenuMapper.java
 * @description：菜单信息
 * @author：tanyp
 * @dateTime：2022/2/15 11:42
 * @editNote：
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> list(@Param("dto") SysMenuDto dto);

    List<SysMenu> treeList(@Param("dto") SysMenuDto dto);

    List<SysMenu> getMenuChildrens(@Param("id") String id);

}
