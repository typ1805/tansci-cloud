package com.tansci.service;

import com.tansci.dto.HomeDto;
import com.tansci.vo.LogStatisticsVo;

/**
 * @ClassName： HomeService.java
 * @ClassPath： com.tansci.service.HomeService.java
 * @Description： 首页统计
 * @Author： tanyp
 * @Date： 2022/6/1 15:34
 **/
public interface HomeService {

    LogStatisticsVo logStatistics(HomeDto dto);

}
