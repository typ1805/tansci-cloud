package com.tansci.provider.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tansci.domain.SysUser;
import com.tansci.domain.SysUserRole;
import com.tansci.exception.BusinessException;
import com.tansci.provider.SysUserProvider;
import com.tansci.service.SysUserRoleService;
import com.tansci.service.SysUserService;
import com.tansci.utils.WrapMapper;
import com.tansci.utils.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * @ClassName： SysUserProviderImpl.java
 * @ClassPath： com.tansci.provider.impl.SysUserProviderImpl.java
 * @Description： 用户接口
 * @Author： tanyp
 * @Date： 2022/2/11 11:53
 **/
@Slf4j
@DubboService
public class SysUserProviderImpl implements SysUserProvider {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public Wrapper<SysUser> findByUsername(String username) {
        try {
            // 1.根据 username 获取用户信息
            SysUser user = sysUserService.findByUsername(username);
            if (Objects.nonNull(user)) {
                // 2.根据 userId 获取角色
                SysUserRole role = sysUserRoleService.getOne(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, user.getId()));
                user.setRoleId(Objects.nonNull(role) ? role.getRoleId() : null);
            }
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, user);
        } catch (BusinessException e) {
            log.error("服务间调用异常：{}", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, e.getMessage());
        } catch (Exception e) {
            log.error("服务间调用异常：{}", e);
            return WrapMapper.error();
        }
    }

}
