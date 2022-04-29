package com.kuiper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kuiper.domain.SysMenuRole;
import com.kuiper.mapper.SysMenuRoleMapper;
import com.kuiper.service.SysMenuRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName： SysMenuRoleServiceImpl.java
 * @ClassPath： com.kuiper.service.impl.SysMenuRoleServiceImpl.java
 * @Description： 菜单角色
 * @Author： tanyp
 * @Date： 2022/4/25 16:35
 **/
@Slf4j
@Service
public class SysMenuRoleServiceImpl extends ServiceImpl<SysMenuRoleMapper, SysMenuRole> implements SysMenuRoleService {
}
