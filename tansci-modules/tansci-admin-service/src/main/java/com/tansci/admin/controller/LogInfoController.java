package com.tansci.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.admin.api.domain.LogErrorInfo;
import com.tansci.admin.api.domain.LogInfo;
import com.tansci.admin.api.domain.LoginLog;
import com.tansci.admin.service.LogErrorInfoService;
import com.tansci.admin.service.LogInfoService;
import com.tansci.admin.service.LoginLogService;
import com.tansci.common.core.utils.WrapMapper;
import com.tansci.common.core.utils.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @path：com.tansci.admin.controller.LogInfoController.java
 * @className：LogInfoController.java
 * @description：操作日志
 * @author：tanyp
 * @dateTime：2022/2/15 9:34
 * @editNote：
 */
@Slf4j
@RestController
@RequestMapping("/log")
@Api(value = "log", tags = "操作日志")
public class LogInfoController {

    @Autowired
    private LogInfoService logInfoService;

    @Autowired
    private LogErrorInfoService logErrorInfoService;

    @Autowired
    private LoginLogService loginLogService;

    @ApiOperation(value = "操作日志分页", notes = "操作日志分页")
    @GetMapping("/logInfoPage")
    public Wrapper<IPage<LogInfo>> logInfoPage(Page page) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, logInfoService.page(page,
                Wrappers.<LogInfo>lambdaQuery().orderByDesc(LogInfo::getCreateTime))
        );
    }

    @ApiOperation(value = "异常日志分页", notes = "异常日志分页")
    @GetMapping("/logErrorPage")
    public Wrapper<IPage<LogErrorInfo>> logErrorPage(Page page) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, logErrorInfoService.page(page,
                Wrappers.<LogErrorInfo>lambdaQuery().orderByDesc(LogErrorInfo::getCreateTime))
        );
    }

    @ApiOperation(value = "登录日志分页", notes = "登录日志分页")
    @GetMapping("/loginLogPage")
    public Wrapper<IPage<LoginLog>> loginLogPage(Page page) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, loginLogService.page(page,
                Wrappers.<LoginLog>lambdaQuery().orderByDesc(LoginLog::getCreateTime))
        );
    }

}
