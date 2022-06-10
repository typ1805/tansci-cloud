package com.tansci.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.TaskConfig;
import com.tansci.dto.TaskConfigDto;

/**
 * @ClassName： TaskConfigService.java
 * @ClassPath： com.tansci.service.TaskConfigService.java
 * @Description： 调度配置
 * @Author： tanyp
 * @Date： 2022/6/9 16:41
 **/
public interface TaskConfigService extends IService<TaskConfig> {

    IPage<TaskConfig> page(Page page, TaskConfigDto dto);

    boolean save(TaskConfig taskConfig);

    boolean update(TaskConfig taskConfig);

    boolean del(TaskConfigDto dto);

}
