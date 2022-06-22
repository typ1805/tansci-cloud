package com.tansci.admin.service.impl;

import com.tansci.admin.api.dto.HomeDto;
import com.tansci.admin.api.vo.StatisticsVo;
import com.tansci.admin.mapper.HomeMapper;
import com.tansci.admin.service.HomeService;
import com.tansci.common.web.utils.UserInfoContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @ClassName： HomeServiceImpl.java
 * @ClassPath： com.tansci.admin.service.impl.HomeServiceImpl.java
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
