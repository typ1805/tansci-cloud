package com.kuiper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kuiper.domain.SysUserRole;
import com.kuiper.mapper.SysUserRoleMapper;
import com.kuiper.service.SysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName： SysUserRoleServiceImpl.java
 * @ClassPath： com.kuiper.service.impl.SysUserRoleServiceImpl.java
 * @Description： 用户角色
 * @Author： tanyp
 * @Date： 2022/4/25 16:30
 **/
@Slf4j
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
}
