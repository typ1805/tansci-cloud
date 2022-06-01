package com.tansci.mapper;

import com.tansci.dto.HomeDto;
import com.tansci.vo.LogStatisticsVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName： HomeMapper.java
 * @ClassPath： com.tansci.mapper.HomeMapper.java
 * @Description： 首页统计
 * @Author： tanyp
 * @Date： 2022/6/1 15:43
 **/
@Mapper
public interface HomeMapper {

    LogStatisticsVo logStatistics(HomeDto dto);

}
