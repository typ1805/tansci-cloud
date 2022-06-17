package com.tansci.service;

import com.tansci.dto.HomeDto;
import com.tansci.vo.StatisticsVo;

/**
 * @ClassName： HomeService.java
 * @ClassPath： com.tansci.service.HomeService.java
 * @Description： 首页统计
 * @Author： tanyp
 * @Date： 2022/6/1 15:34
 **/
public interface HomeService {

    StatisticsVo statistics(HomeDto dto);

}
