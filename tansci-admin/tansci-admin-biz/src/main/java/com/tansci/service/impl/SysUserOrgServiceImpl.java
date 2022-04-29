package com.kuiper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kuiper.domain.SysUserOrg;
import com.kuiper.mapper.SysUserOrgMapper;
import com.kuiper.service.SysUserOrgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName： SysUserOrgServiceImpl.java
 * @ClassPath： com.kuiper.service.impl.SysUserOrgServiceImpl.java
 * @Description： 用户组织
 * @Author： tanyp
 * @Date： 2022/4/25 16:31
 **/
@Slf4j
@Service
public class SysUserOrgServiceImpl extends ServiceImpl<SysUserOrgMapper, SysUserOrg> implements SysUserOrgService {
}
