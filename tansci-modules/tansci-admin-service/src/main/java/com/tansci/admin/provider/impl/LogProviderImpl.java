package com.tansci.admin.provider.impl;

import com.tansci.admin.api.domain.LogErrorInfo;
import com.tansci.admin.api.domain.LogInfo;
import com.tansci.admin.api.domain.LoginLog;
import com.tansci.admin.api.provider.LogProvider;
import com.tansci.admin.service.LogErrorInfoService;
import com.tansci.admin.service.LogInfoService;
import com.tansci.admin.service.LoginLogService;
import com.tansci.common.core.exception.BusinessException;
import com.tansci.common.core.utils.WrapMapper;
import com.tansci.common.core.utils.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName： LogProviderImpl.java
 * @ClassPath： com.tansci.admin.provider.impl.LogProviderImpl.java
 * @Description： 日志记录
 * @Author： tanyp
 * @Date： 2022/2/15 9:17
 **/
@Slf4j
@DubboService
public class LogProviderImpl implements LogProvider {

    @Autowired
    private LogInfoService logInfoService;

    @Autowired
    private LogErrorInfoService logErrorInfoService;

    @Autowired
    private LoginLogService loginLogService;

    @Override
    public Wrapper<Object> logInfoSave(LogInfo info) {
        try {
            boolean flag = logInfoService.save(info);
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, flag);
        } catch (BusinessException e) {
            log.error("{}", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, e.getMessage());
        } catch (Exception e) {
            log.error("{}", e);
            return WrapMapper.error();
        }
    }

    @Override
    public Wrapper<Object> logErrorInfoSave(LogErrorInfo info) {
        try {
            boolean flag = logErrorInfoService.save(info);
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, flag);
        } catch (BusinessException e) {
            log.error("{}", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, e.getMessage());
        } catch (Exception e) {
            log.error("{}", e);
            return WrapMapper.error();
        }
    }

    @Override
    public Wrapper<Object> loginLogSave(LoginLog loginLog) {
        try {
            boolean flag = loginLogService.save(loginLog);
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, flag);
        } catch (BusinessException e) {
            log.error("{}", e);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, e.getMessage());
        } catch (Exception e) {
            log.error("{}", e);
            return WrapMapper.error();
        }
    }

}
