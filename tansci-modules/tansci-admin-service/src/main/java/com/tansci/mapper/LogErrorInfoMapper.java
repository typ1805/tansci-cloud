package com.tansci.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.LogErrorInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @path：com.tansci.mapper.LogErrorInfoMapper.java
 * @className：LogErrorInfoMapper.java
 * @description：操作日志异常信息
 * @author：tanyp
 * @dateTime：2022/2/15 11:42
 * @editNote：
 */
@Mapper
public interface LogErrorInfoMapper extends BaseMapper<LogErrorInfo> {
}