package com.tansci.admin.controller;

import com.tansci.admin.api.dto.HomeDto;
import com.tansci.admin.api.vo.StatisticsVo;
import com.tansci.admin.service.HomeService;
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
 * @path：com.tansci.admin.controller.HomeController.java
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

    @ApiOperation(value = "指标统计", notes = "指标统计")
    @GetMapping("/statistics")
    public Wrapper<StatisticsVo> statistics(HomeDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, homeService.statistics(dto));
    }


}
