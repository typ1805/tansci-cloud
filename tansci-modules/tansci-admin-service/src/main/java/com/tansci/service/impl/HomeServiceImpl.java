package com.tansci.service.impl;

import com.tansci.dto.HomeDto;
import com.tansci.mapper.HomeMapper;
import com.tansci.service.HomeService;
import com.tansci.utils.UserInfoContext;
import com.tansci.vo.StatisticsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @ClassName： HomeServiceImpl.java
 * @ClassPath： com.tansci.service.impl.HomeServiceImpl.java
 * @Description： 首页统计
 * @Author： tanyp
 * @Date： 2022/6/1 15:34
 **/
@Slf4j
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private HomeMapper homeMapper;

    @Override
    public StatisticsVo statistics(HomeDto dto) {
        if (Objects.equals(0, UserInfoContext.getUser().getType())) {
            dto.setUserId(UserInfoContext.getUser().getId());
        }
        return homeMapper.statistics(dto);
    }

}
