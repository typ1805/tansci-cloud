package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.TaskConfig;
import com.tansci.dto.TaskConfigDto;
import com.tansci.mapper.TaskConfigMapper;
import com.tansci.service.TaskConfigService;
import com.tansci.utils.ScheduledTask;
import com.tansci.utils.UUIDUtil;
import com.tansci.utils.UserInfoContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;

/**
 * @ClassName： TaskConfigServiceImpl.java
 * @ClassPath： com.tansci.service.impl.TaskConfigServiceImpl.java
 * @Description： 调度配置
 * @Author： tanyp
 * @Date： 2022/6/9 16:42
 **/
@Slf4j
@Service
public class TaskConfigServiceImpl extends ServiceImpl<TaskConfigMapper, TaskConfig> implements TaskConfigService {

    @Autowired
    private ScheduledTask scheduledTask;

    @Override
    public IPage<TaskConfig> page(Page page, TaskConfigDto dto) {
        IPage<TaskConfig> iPage = this.baseMapper.selectPage(page,
                Wrappers.<TaskConfig>lambdaQuery()
                        .eq(Objects.nonNull(dto.getStatus()), TaskConfig::getStatus, dto.getStatus())
                        .like(Objects.nonNull(dto.getName()), TaskConfig::getName, dto.getName())
                        .orderByDesc(TaskConfig::getUpdateTime)
        );
        return iPage;
    }

    @Override
    public boolean save(TaskConfig taskConfig) {
        taskConfig.setUpdateTime(LocalDateTime.now());
        taskConfig.setCreateTime(LocalDateTime.now());
        taskConfig.setCreater(UserInfoContext.getUser().getUsername());
        taskConfig.setTaskCode(UUIDUtil.getUUID().toUpperCase(Locale.ROOT));
        int row = this.baseMapper.insert(taskConfig);
        if (row > 0 && Objects.equals(1, taskConfig.getStatus())) {
            scheduledTask.refreshTask(this.list(Wrappers.<TaskConfig>lambdaQuery().eq(TaskConfig::getStatus, 1)));
            return true;
        }
        return true;
    }

    @Override
    public boolean update(TaskConfig taskConfig) {
        taskConfig.setUpdateTime(LocalDateTime.now());
        int row = this.baseMapper.updateById(taskConfig);
        if (row > 0) {
            scheduledTask.refreshTask(this.list(Wrappers.<TaskConfig>lambdaQuery().eq(TaskConfig::getStatus, 1)));
            return true;
        }
        return false;
    }

    @Override
    public boolean del(TaskConfigDto dto) {
        int row = this.baseMapper.deleteById(dto.getId());
        if (row > 0) {
            scheduledTask.refreshTask(this.list(Wrappers.<TaskConfig>lambdaQuery().eq(TaskConfig::getStatus, 1)));
            return true;
        }
        return false;
    }

}
