package com.tansci.controller;

import com.tansci.dto.HomeDto;
import com.tansci.service.HomeService;
import com.tansci.utils.WrapMapper;
import com.tansci.utils.Wrapper;
import com.tansci.vo.LogStatisticsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @path：com.tansci.controller.HomeController.java
 * @className：HomeController.java
 * @description：首页统计
 * @author：tanyp
 * @dateTime：2022/2/15 9:34
 * @editNote：
 */
@Slf4j
@RestController
@RequestMapping("/home")
@Api(value = "home", tags = "首页统计")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @ApiOperation(value = "日志统计", notes = "日志统计")
    @GetMapping("/logStatistics")
    public Wrapper<LogStatisticsVo> logStatistics(HomeDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, homeService.logStatistics(dto));
    }


}
