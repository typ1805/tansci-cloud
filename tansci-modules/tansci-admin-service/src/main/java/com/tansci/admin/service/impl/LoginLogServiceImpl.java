package com.tansci.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.admin.api.domain.LoginLog;
import com.tansci.admin.mapper.LoginLogMapper;
import com.tansci.admin.service.LoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName： LoginLogServiceImpl.java
 * @ClassPath： com.tansci.admin.service.impl.LoginLogServiceImpl.java
 * @Description： 登录日志
 * @Author： tanyp
 * @Date： 2022/6/1 15:12
 **/
@Slf4j
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {
}
