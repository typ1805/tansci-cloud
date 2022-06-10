package com.tansci.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.TaskLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName： TaskLogMapper.java
 * @ClassPath： com.tansci.mapper.TaskLogMapper.java
 * @Description： 调度执行日志
 * @Author： tanyp
 * @Date： 2022/6/10 9:17
 **/
@Mapper
public interface TaskLogMapper extends BaseMapper<TaskLog> {
}
