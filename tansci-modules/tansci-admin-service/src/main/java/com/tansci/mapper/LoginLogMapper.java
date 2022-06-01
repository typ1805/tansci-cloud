package com.tansci.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.LoginLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName： LoginLogMapper.java
 * @ClassPath： com.tansci.mapper.LoginLogMapper.java
 * @Description： 登录日志
 * @Author： tanyp
 * @Date： 2022/6/1 15:09
 **/
@Mapper
public interface LoginLogMapper extends BaseMapper<LoginLog> {
}
