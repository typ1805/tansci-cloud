package com.kuiper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuiper.domain.SysOrgRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName： SysOrgRoleMapper.java
 * @ClassPath： com.kuiper.mapper.SysOrgRoleMapper.java
 * @Description： 组织角色
 * @Author： tanyp
 * @Date： 2022/4/25 16:22
 **/
@Mapper
public interface SysOrgRoleMapper extends BaseMapper<SysOrgRole> {
}
