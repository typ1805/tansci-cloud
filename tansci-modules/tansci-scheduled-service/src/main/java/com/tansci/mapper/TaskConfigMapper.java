package com.tansci.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.TaskConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName： TaskConfigMapper.java
 * @ClassPath： com.tansci.mapper.TaskConfigMapper.java
 * @Description： 调度配置
 * @Author： tanyp
 * @Date： 2022/6/9 16:41
 **/
@Mapper
public interface TaskConfigMapper extends BaseMapper<TaskConfig> {
}
