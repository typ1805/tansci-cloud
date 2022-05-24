package com.tansci.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.SysRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName： SysRoleMapper.java
 * @ClassPath： com.tansci.mapper.SysRoleMapper.java
 * @Description： 权限
 * @Author： tanyp
 * @Date： 2022/4/25 16:22
 **/
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

}
