package com.tansci.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.admin.api.domain.SysMenuRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName： SysMenuRoleMapper.java
 * @ClassPath： com.tansci.admin.mapper.SysMenuRoleMapper.java
 * @Description： 菜单角色
 * @Author： tanyp
 * @Date： 2022/4/25 16:20
 **/
@Mapper
public interface SysMenuRoleMapper extends BaseMapper<SysMenuRole> {
}
