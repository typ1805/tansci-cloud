package com.tansci.scheduled.service;

/**
 * @ClassName： TaskContextService.java
 * @ClassPath： com.tansci.scheduled.service.TaskContextService.java
 * @Description： 动态调用任务配置信息
 * @Author： tanyp
 * @Date： 2022/6/9 16:48
 **/
public interface TaskContextService {

    void execute(String taskServerName);

}
