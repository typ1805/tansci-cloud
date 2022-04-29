package com.kuiper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kuiper.domain.SysOrgRole;
import com.kuiper.mapper.SysOrgRoleMapper;
import com.kuiper.service.SysOrgRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName： SysOrgRoleServiceImpl.java
 * @ClassPath： com.kuiper.service.impl.SysOrgRoleServiceImpl.java
 * @Description： 组织角色
 * @Author： tanyp
 * @Date： 2022/4/25 16:33
 **/
@Slf4j
@Service
public class SysOrgRoleServiceImpl extends ServiceImpl<SysOrgRoleMapper, SysOrgRole> implements SysOrgRoleService {
}
