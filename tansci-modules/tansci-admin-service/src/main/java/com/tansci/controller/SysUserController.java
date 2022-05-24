package com.tansci.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.annotation.Log;
import com.tansci.constants.Constants;
import com.tansci.domain.SysUser;
import com.tansci.dto.SysUserDto;
import com.tansci.service.SysUserService;
import com.tansci.utils.WrapMapper;
import com.tansci.utils.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @ClassName： SysUserController.java
 * @ClassPath： com.tansci.controller.SysUserController.java
 * @Description： 用户信息
 * @Author： tanyp
 * @Date： 2022/2/11 14:49
 **/
@Slf4j
@RestController
@RequestMapping("/sysUser")
@Api(value = "sysUser", tags = "用户信息")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "获取用户列表", notes = "获取用户列表", response = SysUserDto.class)
    @Log(modul = "用户信息-获取用户列表", type = Constants.SELECT, desc = "获取用户列表")
    @GetMapping("page")
    public Wrapper<IPage<SysUser>> page(Page page, SysUserDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserService.page(page, dto));
    }

    @ApiOperation(value = "根据名称获取用户信息", notes = "根据名称获取用户信息")
    @Log(modul = "用户信息-根据名称获取用户信息", type = Constants.SELECT, desc = "根据名称获取用户信息")
    @GetMapping("findByUsername")
    public Wrapper<SysUser> findByUsername(SysUserDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserService.findByUsername(dto.getUsername()));
    }

    @ApiOperation(value = "根据ID获取用户信息", notes = "根据ID获取用户信息")
    @Log(modul = "用户信息-根据ID获取用户信息", type = Constants.SELECT, desc = "根据ID获取用户信息")
    @GetMapping("getById")
    public Wrapper<SysUser> getById(SysUserDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserService.getById(dto.getId()));
    }

    @ApiOperation(value = "添加", notes = "添加")
    @Log(modul = "用户信息-添加", type = Constants.INSERT, desc = "添加")
    @PostMapping("save")
    public Wrapper<Object> save(@RequestBody SysUser sysUser) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        sysUser.setCreateTime(LocalDateTime.now());
        sysUser.setUpdateTime(LocalDateTime.now());
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserService.save(sysUser));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @Log(modul = "用户信息-删除", type = Constants.DELETE, desc = "删除")
    @GetMapping("delete")
    public Wrapper<Object> delete(SysUserDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserService.removeById(dto.getId()));
    }

    @ApiOperation(value = "修改", notes = "修改")
    @Log(modul = "用户信息-修改", type = Constants.UPDATE, desc = "修改")
    @PostMapping("update")
    public Wrapper<Object> update(@RequestBody SysUser sysUser) {
        sysUser.setUpdateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserService.saveOrUpdate(sysUser));
    }

    @ApiOperation(value = "用户修改密码", notes = "用户修改密码")
    @Log(modul = "用户-修改密码", type = Constants.UPDATE, desc = "修改密码")
    @PostMapping("/modifyPass")
    public Wrapper<Integer> modifyPass(@RequestBody SysUserDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserService.modifyPass(dto));
    }

}
