package com.tansci.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.TaskLog;
import com.tansci.dto.TaskConfigDto;

/**
 * @ClassName： TaskLogService.java
 * @ClassPath： com.tansci.service.TaskLogService.java
 * @Description： 调度执行日志
 * @Author： tanyp
 * @Date： 2022/6/10 9:18
 **/
public interface TaskLogService extends IService<TaskLog> {

    IPage<TaskLog> page(Page page, TaskConfigDto dto);

    Object clear(TaskConfigDto dto);

}
