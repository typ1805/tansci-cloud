package com.tansci.scheduled.utils;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tansci.scheduled.api.domain.TaskConfig;
import com.tansci.scheduled.service.TaskConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName： TaskApplicationRunner.java
 * @ClassPath： com.tansci.scheduled.task.TaskApplicationRunner.java
 * @Description： 任务自启动配置
 * @Author： tanyp
 * @Date： 2022/6/9 17:11
 **/
@Slf4j
@Component
public class TaskApplicationRunner implements ApplicationRunner {

    @Autowired
    private ScheduledTask scheduledTask;

    @Autowired
    private TaskConfigService taskConfigService;

    @Override
    public void run(ApplicationArguments args) {
        try {
            log.info("================项目启动初始化定时任务====开始===========");
            List<TaskConfig> tasks = taskConfigService.list(Wrappers.<TaskConfig>lambdaQuery().eq(TaskConfig::getStatus, 1));
            log.info("========初始化定时任务数为：{}=========", tasks.size());
            scheduledTask.refreshTask(tasks);
            log.info("================项目启动初始化定时任务====完成==========");
        } catch (Exception e) {
            log.error("================项目启动初始化定时任务====异常：{}", e);
        }
    }

}
