package com.tansci.mapper;

import com.tansci.dto.HomeDto;
import com.tansci.vo.StatisticsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName： HomeMapper.java
 * @ClassPath： com.tansci.mapper.HomeMapper.java
 * @Description： 首页统计
 * @Author： tanyp
 * @Date： 2022/6/1 15:43
 **/
@Mapper
public interface HomeMapper {

    StatisticsVo statistics(@Param("dto") HomeDto dto);

}
