package com.tansci.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.admin.api.domain.DataSource;
import com.tansci.admin.api.dto.SourceDto;
import com.tansci.admin.service.DataSourceService;
import com.tansci.common.core.constants.Constants;
import com.tansci.common.core.utils.WrapMapper;
import com.tansci.common.core.utils.Wrapper;
import com.tansci.common.web.annotation.Log;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName： DataSourceController.java
 * @ClassPath： com.tansci.admin.controller.DataSourceController.java
 * @Description： 数据源
 * @Author： tanyp
 * @Date： 2022/5/31 15:24
 **/
@Slf4j
@RestController
@Api(value = "数据源", tags = "数据源")
@RequestMapping("/dataSource")
public class DataSourceController {

    @Autowired
    private DataSourceService dataSourceService;

    @ApiOperation(value = "分页", notes = "分页")
    @Log(modul = "数据源-分页", type = Constants.SELECT, desc = "分页")
    @GetMapping("page")
    public Wrapper<IPage<DataSource>> page(Page page, SourceDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, dataSourceService.page(page, dto));
    }

    @ApiOperation(value = "列表", notes = "列表")
    @Log(modul = "数据源-列表", type = Constants.SELECT, desc = "列表")
    @GetMapping("list")
    public Wrapper<List<DataSource>> list(SourceDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, dataSourceService.list(dto));
    }

    @ApiOperation(value = "添加", notes = "添加")
    @Log(modul = "数据源-添加", type = Constants.INSERT, desc = "添加")
    @PostMapping("save")
    public Wrapper<Object> save(@RequestBody DataSource source) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, dataSourceService.save(source));
    }

    @ApiOperation(value = "修改", notes = "修改")
    @Log(modul = "数据源-修改", type = Constants.UPDATE, desc = "修改")
    @PostMapping("update")
    public Wrapper<Object> update(@RequestBody DataSource source) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, dataSourceService.update(source));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @Log(modul = "数据源-删除", type = Constants.DELETE, desc = "删除")
    @GetMapping("delete")
    public Wrapper<Object> delete(SourceDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, dataSourceService.delete(dto));
    }

    @ApiOperation(value = "检测数据库连通性", notes = "检测数据库连通性")
    @Log(modul = "数据源-数据库连通性", type = Constants.SELECT, desc = "数据库连通性")
    @PostMapping("checkConnection")
    public Wrapper<Object> checkConnection(@RequestBody SourceDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, dataSourceService.checkConnection(dto));
    }

    @ApiOperation(value = "数据库表", notes = "根据数据源的id来获取指定数据库表")
    @Log(modul = "数据源-数据库表", type = Constants.SELECT, desc = "根据数据源的id来获取指定数据库表")
    @PostMapping("getDbTables")
    public Wrapper<Object> getDbTables(@RequestBody SourceDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, dataSourceService.getDbTables(dto));
    }

    @ApiOperation(value = "数据库表结构", notes = "根据数据源的id来获取指定数据库表的表结构")
    @Log(modul = "数据源-数据库表结构", type = Constants.SELECT, desc = "根据数据源的id来获取指定数据库表的表结构")
    @PostMapping("getDbTableColumns")
    public Wrapper<Object> getDbTableColumns(@RequestBody SourceDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, dataSourceService.getDbTableColumns(dto));
    }

}
