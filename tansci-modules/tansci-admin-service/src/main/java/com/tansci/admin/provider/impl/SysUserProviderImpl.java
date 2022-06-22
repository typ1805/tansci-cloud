package com.tansci.admin.provider.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tansci.admin.api.domain.SysUser;
import com.tansci.admin.api.domain.SysUserRole;
import com.tansci.admin.api.provider.SysUserProvider;
import com.tansci.admin.service.SysUserRoleService;
import com.tansci.admin.service.SysUserService;
import com.tansci.common.core.exception.BusinessException;
import com.tansci.common.core.utils.WrapMapper;
import com.tansci.common.core.utils.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * @ClassName： SysUserProviderImpl.java
 * @ClassPath： com.tansci.admin.provider.impl.SysUserProviderImpl.java
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
