package com.kuiper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuiper.domain.SysMenuRole;
import com.kuiper.domain.SysRole;
import com.kuiper.vo.SysMenuRoleVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName： SysRoleMapper.java
 * @ClassPath： com.kuiper.mapper.SysRoleMapper.java
 * @Description： 权限
 * @Author： tanyp
 * @Date： 2022/4/25 16:22
 **/
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysMenuRoleVo> menuRoleList(SysMenuRole role);

}
