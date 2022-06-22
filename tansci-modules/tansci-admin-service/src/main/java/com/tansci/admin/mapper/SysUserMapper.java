package com.tansci.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.admin.api.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @path：com.tansci.admin.mapper.SysUserMapper.java
 * @className：SysUserMapper.java
 * @description：用户信息
 * @author：tanyp
 * @dateTime：2022/2/11 15:31
 * @editNote：
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}
