package com.tansci.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.domain.LogErrorInfo;
import com.tansci.domain.LogInfo;
import com.tansci.service.LogErrorInfoService;
import com.tansci.service.LogInfoService;
import com.tansci.utils.WrapMapper;
import com.tansci.utils.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @path：com.tansci.controller.LogInfoController.java
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

}