package com.tansci.scheduled.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.scheduled.api.domain.TaskLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName： TaskLogMapper.java
 * @ClassPath： com.tansci.scheduled.mapper.TaskLogMapper.java
 * @Description： 调度执行日志
 * @Author： tanyp
 * @Date： 2022/6/10 9:17
 **/
@Mapper
public interface TaskLogMapper extends BaseMapper<TaskLog> {
}
