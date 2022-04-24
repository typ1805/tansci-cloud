package com.tansci.provider.impl;

import com.tansci.domain.SysUser;
import com.tansci.exception.BusinessException;
import com.tansci.provider.SysUserProvider;
import com.tansci.service.SysUserService;
import com.tansci.utils.WrapMapper;
import com.tansci.utils.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Override
    public Wrapper<SysUser> findByUsername(String username) {
        try {
            SysUser userVo = sysUserService.findByUsername(username);
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, userVo);
        } catch (BusinessException e) {
            log.error("服务间调用异常：{}", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, e.getMessage());
        } catch (Exception e) {
            log.error("服务间调用异常：{}", e);
            return WrapMapper.error();
        }
    }

}
