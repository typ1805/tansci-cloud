package com.tansci.service.impl.task;

import com.tansci.service.TaskRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName： TaskTestServiceImpl.java
 * @ClassPath： com.tansci.service.task.TaskTestServiceImpl.java
 * @Description： 自定义任务测试1
 * @Author： tanyp
 * @Date： 2022/2/25 10:08
 **/
@Slf4j
@Service("taskTestService")
public class TaskTestServiceImpl implements TaskRegisterService {

    @Override
    public void register() {
        log.info("===========自定义任务测试【TaskTestServiceImpl】====【1】=========");
    }

}
