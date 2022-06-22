package com.tansci.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.admin.api.domain.SysRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName： SysRoleMapper.java
 * @ClassPath： com.tansci.admin.mapper.SysRoleMapper.java
 * @Description： 权限
 * @Author： tanyp
 * @Date： 2022/4/25 16:22
 **/
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

}
