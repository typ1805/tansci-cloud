package com.kuiper.controller;

import com.kuiper.annotation.Log;
import com.kuiper.constants.Constants;
import com.kuiper.domain.SysOrg;
import com.kuiper.dto.SysOrgDto;
import com.kuiper.service.SysOrgService;
import com.kuiper.utils.WrapMapper;
import com.kuiper.utils.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName： SysOrgController.java
 * @ClassPath： com.kuiper.controller.SysOrgController.java
 * @Description： 组织管理
 * @Author： tanyp
 * @Date： 2022/4/25 16:36
 **/
@Slf4j
@RestController
@RequestMapping("/sysOrg")
@Api(value = "sysOrg", tags = "组织管理")
public class SysOrgController {

    @Autowired
    private SysOrgService sysOrgService;

    @ApiOperation(value = "组织列表", notes = "组织列表")
    @Log(modul = "组织管理-组织列表", type = Constants.SELECT, desc = "组织列表")
    @GetMapping("/page")
    public Wrapper<List<SysOrg>> list(SysOrgDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysOrgService.list(dto));
    }

    @ApiOperation(value = "添加", notes = "添加")
    @Log(modul = "组织管理-添加", type = Constants.INSERT, desc = "添加")
    @PostMapping("save")
    public Wrapper<Object> save(@RequestBody SysOrg sysOrg) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysOrgService.save(sysOrg));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @Log(modul = "组织管理-删除", type = Constants.DELETE, desc = "删除")
    @GetMapping("delete")
    public Wrapper<Object> delete(SysOrgDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysOrgService.delete(dto));
    }

    @ApiOperation(value = "修改", notes = "修改")
    @Log(modul = "组织管理-修改", type = Constants.UPDATE, desc = "修改")
    @PostMapping("update")
    public Wrapper<Object> update(@RequestBody SysOrg sysOrg) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysOrgService.update(sysOrg));
    }

}
