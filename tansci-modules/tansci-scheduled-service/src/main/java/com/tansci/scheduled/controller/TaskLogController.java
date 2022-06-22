package com.tansci.scheduled.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.common.core.constants.Constants;
import com.tansci.common.core.utils.WrapMapper;
import com.tansci.common.core.utils.Wrapper;
import com.tansci.common.web.annotation.Log;
import com.tansci.scheduled.api.domain.TaskLog;
import com.tansci.scheduled.api.dto.TaskConfigDto;
import com.tansci.scheduled.service.TaskLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName： TaskLogController.java
 * @ClassPath： com.tansci.scheduled.controller.TaskLogController.java
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
