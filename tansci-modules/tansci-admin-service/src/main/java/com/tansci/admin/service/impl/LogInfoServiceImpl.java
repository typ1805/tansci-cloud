package com.tansci.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.admin.api.domain.LogInfo;
import com.tansci.admin.mapper.LogInfoMapper;
import com.tansci.admin.service.LogInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @path：com.tansci.admin.service.impl.LogInfoServiceImpl.java
 * @className：LogInfoServiceImpl.java
 * @description：操作日志
 * @author：tanyp
 * @dateTime：2022/2/15 13:51
 * @editNote：
 */
@Slf4j
@Service
public class LogInfoServiceImpl extends ServiceImpl<LogInfoMapper, LogInfo> implements LogInfoService {
}
