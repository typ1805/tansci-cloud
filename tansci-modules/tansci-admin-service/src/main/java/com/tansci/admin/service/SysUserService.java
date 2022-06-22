package com.tansci.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.admin.api.domain.SysUser;
import com.tansci.admin.api.dto.SysUserDto;

/**
 * @ClassName： SysUserService.java
 * @ClassPath： com.tansci.admin.service.SysUserService.java
 * @Description： 用户信息
 * @Author： tanyp
 * @Date： 2022/2/11 11:54
 **/
public interface SysUserService extends IService<SysUser> {

    IPage<SysUser> page(Page page, SysUserDto dto);

    SysUser findByUsername(String username);

    Integer modifyPass(SysUserDto dto);

}
