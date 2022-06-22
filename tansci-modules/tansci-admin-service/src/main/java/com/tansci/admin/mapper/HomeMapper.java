package com.tansci.admin.mapper;

import com.tansci.admin.api.dto.HomeDto;
import com.tansci.admin.api.vo.StatisticsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName： HomeMapper.java
 * @ClassPath： com.tansci.admin.mapper.HomeMapper.java
 * @Description： 首页统计
 * @Author： tanyp
 * @Date： 2022/6/1 15:43
 **/
@Mapper
public interface HomeMapper {

    StatisticsVo statistics(@Param("dto") HomeDto dto);

}
