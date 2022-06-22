package com.tansci.scheduled.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.scheduled.api.domain.TaskLog;
import com.tansci.scheduled.api.dto.TaskConfigDto;

/**
 * @ClassName： TaskLogService.java
 * @ClassPath： com.tansci.scheduled.service.TaskLogService.java
 * @Description： 调度执行日志
 * @Author： tanyp
 * @Date： 2022/6/10 9:18
 **/
public interface TaskLogService extends IService<TaskLog> {

    IPage<TaskLog> page(Page page, TaskConfigDto dto);

    Object clear(TaskConfigDto dto);

}
