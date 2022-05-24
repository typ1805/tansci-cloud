package com.tansci.controller;

import com.tansci.annotation.Log;
import com.tansci.constants.Constants;
import com.tansci.domain.SysMenu;
import com.tansci.dto.SysMenuDto;
import com.tansci.service.SysMenuService;
import com.tansci.utils.WrapMapper;
import com.tansci.utils.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


/**
 * @path：com.tansci.controller.SysMenuController.java
 * @className：SysMenuController.java
 * @description：菜单接口
 * @author：tanyp
 * @dateTime：2022/2/15 9:34
 * @editNote：
 */
@RestController
@RequestMapping("/sysMenu")
@Api(value = "sysMenu", tags = "菜单接口")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @ApiOperation(value = "获取菜单树", notes = "获取菜单树")
    @Log(modul = "菜单-获取菜单树", type = Constants.SELECT, desc = "获取菜单树")
    @GetMapping("list")
    public Wrapper list(SysMenuDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysMenuService.list(dto));
    }

    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @Log(modul = "菜单-根据id查询", type = Constants.SELECT, desc = "根据id查询")
    @GetMapping("getById/{id}")
    public Wrapper<SysMenu> getById(@PathVariable("id") String id) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysMenuService.getById(id));
    }

    @ApiOperation(value = "添加", notes = "添加")
    @Log(modul = "菜单-添加", type = Constants.INSERT, desc = "添加")
    @PostMapping("save")
    public Wrapper save(@RequestBody SysMenu sysMenu) {
        sysMenu.setUpdateTime(LocalDateTime.now());
        sysMenu.setCreateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysMenuService.save(sysMenu));
    }

    @ApiOperation(value = "修改", notes = "修改")
    @Log(modul = "菜单-修改", type = Constants.UPDATE, desc = "修改")
    @PostMapping("update")
    public Wrapper update(@RequestBody SysMenu sysMenu) {
        sysMenu.setUpdateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysMenuService.updateById(sysMenu));
    }

    @ApiOperation(value = "根据id删除", notes = "根据id删除")
    @Log(modul = "菜单-根据id删除", type = Constants.DELETE, desc = "根据id删除")
    @GetMapping("delete")
    public Wrapper delete(SysMenuDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysMenuService.delete(dto));
    }

}
