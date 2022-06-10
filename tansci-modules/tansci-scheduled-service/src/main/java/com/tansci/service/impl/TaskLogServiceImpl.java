package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.TaskConfig;
import com.tansci.domain.TaskLog;
import com.tansci.dto.TaskConfigDto;
import com.tansci.mapper.TaskLogMapper;
import com.tansci.service.TaskConfigService;
import com.tansci.service.TaskLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName： TaskLogServiceImpl.java
 * @ClassPath： com.tansci.service.impl.TaskLogServiceImpl.java
 * @Description： 调度执行日志
 * @Author： tanyp
 * @Date： 2022/6/10 9:19
 **/
@Slf4j
@Service
public class TaskLogServiceImpl extends ServiceImpl<TaskLogMapper, TaskLog> implements TaskLogService {

    @Autowired
    private TaskConfigService taskConfigService;

    @Override
    public IPage<TaskLog> page(Page page, TaskConfigDto dto) {
        IPage<TaskLog> iPage = this.baseMapper.selectPage(page,
                Wrappers.<TaskLog>lambdaQuery()
                        .eq(Objects.nonNull(dto.getStatus()), TaskLog::getStatus, dto.getStatus())
                        .eq(Objects.nonNull(dto.getTaskCode()), TaskLog::getTaskId, dto.getTaskCode())
                        .like(Objects.nonNull(dto.getServerName()), TaskLog::getServerName, dto.getServerName())
                        .orderByDesc(TaskLog::getExecutionTime)
        );
        if (Objects.nonNull(iPage.getRecords()) && iPage.getRecords().size() > 0) {
            List<String> serverNames = iPage.getRecords().stream().map(TaskLog::getServerName).distinct().collect(Collectors.toList());
            List<TaskConfig> configList = taskConfigService.list(Wrappers.<TaskConfig>lambdaQuery().in(TaskConfig::getServerName, serverNames));
            iPage.getRecords().forEach(item -> {
                Optional<TaskConfig> cOptional = configList.stream().filter(c -> Objects.equals(c.getServerName(), item.getServerName())).findFirst();
                if (cOptional.isPresent()) {
                    item.setTaskId(cOptional.get().getId());
                    item.setTaskCode(cOptional.get().getTaskCode());
                    item.setTaskName(cOptional.get().getName());
                    item.setCreater(cOptional.get().getCreater());
                }

            });
        }
        return iPage;
    }

    @Override
    public Object clear(TaskConfigDto dto) {
        return this.baseMapper.delete(
                Wrappers.<TaskLog>lambdaQuery()
                        .eq(Objects.nonNull(dto.getStatus()), TaskLog::getStatus, dto.getStatus())
                        .eq(Objects.nonNull(dto.getServerName()), TaskLog::getServerName, dto.getServerName())
                        .eq(Objects.nonNull(dto.getTaskCode()), TaskLog::getTaskId, dto.getTaskCode())
        );
    }

}
