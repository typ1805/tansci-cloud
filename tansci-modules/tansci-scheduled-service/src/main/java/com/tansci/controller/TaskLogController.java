package com.tansci.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.annotation.Log;
import com.tansci.constants.Constants;
import com.tansci.domain.TaskLog;
import com.tansci.dto.TaskConfigDto;
import com.tansci.service.TaskLogService;
import com.tansci.utils.WrapMapper;
import com.tansci.utils.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName： TaskLogController.java
 * @ClassPath： com.tansci.controller.TaskLogController.java
 * @Description： 调度执行日志
 * @Author： tanyp
 * @Date： 2022/6/9 16:43
 **/
@Slf4j
@RestController
@RequestMapping("/taskLog")
@Api(value = "taskLog", tags = "调度执行日志")
public class TaskLogController {

    @Autowired
    private TaskLogService taskLogService;

    @ApiOperation(value = "分页", notes = "分页")
    @Log(modul = "调度执行日志-分页", type = Constants.SELECT, desc = "分页")
    @GetMapping("/page")
    public Wrapper<IPage<TaskLog>> page(Page page, TaskConfigDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, taskLogService.page(page, dto));
    }

    @ApiOperation(value = "清空日志", notes = "清空日志")
    @Log(modul = "调度执行日志-清空日志", type = Constants.DELETE, desc = "清空日志")
    @GetMapping("/clear")
    public Wrapper<Object> clear(TaskConfigDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, taskLogService.clear(dto));
    }

}
