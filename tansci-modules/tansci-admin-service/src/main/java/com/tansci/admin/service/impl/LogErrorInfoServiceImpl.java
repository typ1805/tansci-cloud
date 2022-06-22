package com.tansci.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.admin.api.domain.LogErrorInfo;
import com.tansci.admin.mapper.LogErrorInfoMapper;
import com.tansci.admin.service.LogErrorInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @path：com.tansci.admin.service.impl.LogErrorInfoServiceImpl.java
 * @className：LogErrorInfoServiceImpl.java
 * @description：操作日志
 * @author：tanyp
 * @dateTime：2022/2/15 13:51
 * @editNote：
 */
@Slf4j
@Service
public class LogErrorInfoServiceImpl extends ServiceImpl<LogErrorInfoMapper, LogErrorInfo> implements LogErrorInfoService {
}
