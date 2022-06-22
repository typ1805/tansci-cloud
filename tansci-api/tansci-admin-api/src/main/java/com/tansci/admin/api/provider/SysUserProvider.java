package com.tansci.admin.api.provider;

import com.tansci.admin.api.domain.SysUser;
import com.tansci.common.core.utils.Wrapper;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName： SysUserProvider.java
 * @ClassPath： com.tansci.admin.api.provider.SysUserProvider.java
 * @Description： 用户接口
 * @Author： tanyp
 * @Date： 2022/04/22 11:20
 **/
public interface SysUserProvider {

    /**
     * @MonthName： findByUsername
     * @Description： 根据用户名称获取用户信息
     * @Author： tanyp
     * @Date： 2022/4/15 16:15
     * @Param：
     * @return：
     **/
    Wrapper<SysUser> findByUsername(@PathVariable("username") String username);

}
