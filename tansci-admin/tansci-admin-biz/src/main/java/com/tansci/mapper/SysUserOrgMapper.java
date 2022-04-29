package com.kuiper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuiper.domain.SysUserOrg;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName： SysUserOrgMapper.java
 * @ClassPath： com.kuiper.mapper.SysUserOrgMapper.java
 * @Description： 用户组织
 * @Author： tanyp
 * @Date： 2022/4/25 16:23
 **/
@Mapper
public interface SysUserOrgMapper extends BaseMapper<SysUserOrg> {
}
