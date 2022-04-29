package com.kuiper.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuiper.annotation.Log;
import com.kuiper.constants.Constants;
import com.kuiper.domain.SysMenuRole;
import com.kuiper.domain.SysOrgRole;
import com.kuiper.domain.SysRole;
import com.kuiper.domain.SysUserRole;
import com.kuiper.dto.SysRoleDto;
import com.kuiper.service.SysOrgRoleService;
import com.kuiper.service.SysRoleService;
import com.kuiper.service.SysUserRoleService;
import com.kuiper.utils.WrapMapper;
import com.kuiper.utils.Wrapper;
import com.kuiper.vo.SysMenuRoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName： SysRoleController.java
 * @ClassPath： com.kuiper.controller.SysRoleController.java
 * @Description： 角色管理
 * @Author： tanyp
 * @Date： 2022/4/25 16:36
 **/
@Slf4j
@RestController
@RequestMapping("/sysRole")
@Api(value = "sysRole", tags = "角色管理")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysOrgRoleService sysOrgRoleService;

    @ApiOperation(value = "角色分页", notes = "角色分页")
    @Log(modul = "角色管理-角色分页", type = Constants.SELECT, desc = "角色分页")
    @GetMapping("/page")
    public Wrapper<IPage<SysRole>> page(Page page, SysRoleDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.page(page, dto));
    }

    @ApiOperation(value = "列表", notes = "列表")
    @Log(modul = "角色-列表", type = Constants.SELECT, desc = "列表")
    @GetMapping("/list")
    public Wrapper<List<SysRole>> list(SysRole sysRole) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.list(sysRole));
    }

    @ApiOperation(value = "添加", notes = "添加")
    @Log(modul = "角色管理-添加", type = Constants.INSERT, desc = "添加")
    @PostMapping("save")
    public Wrapper<Object> save(@RequestBody SysRole sysRole) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.save(sysRole));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @Log(modul = "角色管理-删除", type = Constants.DELETE, desc = "删除")
    @GetMapping("delete")
    public Wrapper<Object> delete(SysRoleDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.delete(dto));
    }

    @ApiOperation(value = "修改", notes = "修改")
    @Log(modul = "角色管理-修改", type = Constants.UPDATE, desc = "修改")
    @PostMapping("update")
    public Wrapper<Object> update(@RequestBody SysRole sysRole) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.update(sysRole));
    }

    @ApiOperation(value = "获取菜单角色", notes = "获取菜单角色")
    @Log(modul = "角色-获取菜单角色", type = Constants.SELECT, desc = "获取菜单角色")
    @GetMapping("/menuRoleList")
    public Wrapper<List<SysMenuRoleVo>> menuRoleList(SysMenuRole role) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.menuRoleList(role));
    }

    @ApiOperation(value = "添加菜单角色", notes = "添加菜单角色")
    @Log(modul = "角色-添加菜单角色", type = Constants.SELECT, desc = "添加菜单角色")
    @PostMapping("/menuRoleAdd")
    public Wrapper<Boolean> menuRoleAdd(@RequestBody SysRoleDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.menuRoleAdd(dto));
    }

    @ApiOperation(value = "获取用户角色信息", notes = "获取用户角色信息")
    @Log(modul = "角色-获取用户角色信息", type = Constants.SELECT, desc = "获取用户角色信息")
    @GetMapping("/userRoleInfo")
    public Wrapper<SysUserRole> userRoleInfo(SysUserRole role) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,
                sysUserRoleService.getOne(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, role.getUserId()))
        );
    }

    @ApiOperation(value = "添加用户角色", notes = "添加用户角色")
    @Log(modul = "角色-添加用户角色", type = Constants.INSERT, desc = "添加用户角色")
    @PostMapping("/userRoleAdd")
    public Wrapper<Boolean> userRoleAdd(@RequestBody SysRoleDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.userRoleAdd(dto));
    }

    @ApiOperation(value = "获取角色信息", notes = "获取角色信息")
    @Log(modul = "角色-获取角色信息", type = Constants.SELECT, desc = "获取角色信息")
    @GetMapping("/orgRoleInfo")
    public Wrapper<SysOrgRole> orgRoleInfo(SysOrgRole role) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,
                sysOrgRoleService.getOne(Wrappers.<SysOrgRole>lambdaQuery().eq(SysOrgRole::getOrgId, role.getOrgId()))
        );
    }

    @ApiOperation(value = "添加组织权限", notes = "添加组织权限")
    @Log(modul = "角色-添加组织权限", type = Constants.INSERT, desc = "添加组织权限")
    @PostMapping("/orgRoleAdd")
    public Wrapper<Boolean> orgRoleAdd(@RequestBody SysRoleDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.orgRoleAdd(dto));
    }

}
