package com.kuiper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuiper.domain.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName： SysUserRoleMapper.java
 * @ClassPath： com.kuiper.mapper.SysUserRoleMapper.java
 * @Description： 用户角色
 * @Author： tanyp
 * @Date： 2022/4/25 16:23
 **/
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
}
