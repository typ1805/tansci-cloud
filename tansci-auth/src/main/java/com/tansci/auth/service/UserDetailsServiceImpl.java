package com.tansci.auth.service;

import com.tansci.admin.api.domain.SysUser;
import com.tansci.admin.api.provider.SysUserProvider;
import com.tansci.auth.domain.AuthUser;
import com.tansci.common.core.constants.Constants;
import com.tansci.common.core.constants.JWTConstants;
import com.tansci.common.core.utils.Wrapper;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @ClassName： UserDetailsServiceImpl.java
 * @ClassPath： com.tansci.auth.service.UserDetailsServiceImpl.java
 * @Description： 自定义用户认证和授权
 * @Author： tanyp
 * @Date： 2022/2/11 10:21
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @DubboReference
    private SysUserProvider sysUserProvider;

    /**
     * @methodName：loadUserByUsername
     * @description：用户信息校验及权限处理 1、后台管理登录处理（包含用户信息、用权限）
     * 2、商户端登录（用户信息校验）
     * @author：tanyp
     * @dateTime：2020/12/2 10:06
     * @Params： [username]
     * @Return： org.springframework.security.core.userdetails.UserDetails
     * @editNote：
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        // 商户端登录
        Wrapper<SysUser> userInfo = sysUserProvider.findByUsername(username);
        if (Objects.nonNull(userInfo) && userInfo.getCode() == Constants.SUCCESS && Objects.nonNull(userInfo.getResult())) {
            SysUser userVo = userInfo.getResult();
            // 角色赋值为商户角色
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(JWTConstants.ROLE_MERCHANTS);
            authorities.add(authority);
            AuthUser user = new AuthUser(userVo.getUsername(), userVo.getPassword(), authorities);
            user.setId(userVo.getId());
            user.setType(userVo.getType());
            user.setRoleId(userVo.getRoleId());
            return user;
        } else {
            throw new UsernameNotFoundException("用户【" + username + "】不存在!");
        }
    }

}
