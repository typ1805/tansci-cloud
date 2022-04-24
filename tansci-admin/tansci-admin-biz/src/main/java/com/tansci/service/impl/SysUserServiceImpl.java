package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.SysUser;
import com.tansci.dto.SysUserDto;
import com.tansci.exception.BusinessException;
import com.tansci.mapper.SysUserMapper;
import com.tansci.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @path：com.tansci.service.impl.SysUserServiceImpl.java
 * @className：SysUserServiceImpl.java
 * @description：用户信息
 * @author：tanyp
 * @dateTime：2022/2/11 15:31
 * @editNote：
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public IPage<SysUser> page(Page page, SysUserDto dto) {
        return this.page(page,
                Wrappers.<SysUser>lambdaQuery()
                        .eq(Objects.nonNull(dto.getStatus()), SysUser::getStatus, dto.getStatus())
                        .eq(Objects.nonNull(dto.getUsername()), SysUser::getUsername, dto.getUsername())
                        .eq(Objects.nonNull(dto.getPhone()), SysUser::getPhone, dto.getPhone())
                        .like(Objects.nonNull(dto.getNickname()), SysUser::getNickname, dto.getNickname())
                        .orderByDesc(SysUser::getUpdateTime)
        );
    }

    @Override
    public SysUser findByUsername(String username) {
        return this.baseMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, username));
    }

    @Override
    public Integer modifyPass(SysUserDto dto) {
        SysUser user = this.baseMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, dto.getUsername()));
        // 验证原始密码
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Boolean flag = passwordEncoder.matches(dto.getOldPassword(), user.getPassword());
        if (Objects.isNull(user) || !flag) {
            throw new BusinessException("原始密码错误，请重新输入！");
        }
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setUpdateTime(LocalDateTime.now());
        return this.baseMapper.updateById(user);
    }

}
