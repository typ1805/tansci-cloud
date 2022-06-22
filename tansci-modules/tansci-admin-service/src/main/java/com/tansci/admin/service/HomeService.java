package com.tansci.admin.service;

import com.tansci.admin.api.dto.HomeDto;
import com.tansci.admin.api.vo.StatisticsVo;

/**
 * @ClassName： HomeService.java
 * @ClassPath： com.tansci.admin.service.HomeService.java
 * @Description： 首页统计
 * @Author： tanyp
 * @Date： 2022/6/1 15:34
 **/
public interface HomeService {

    StatisticsVo statistics(HomeDto dto);

}
