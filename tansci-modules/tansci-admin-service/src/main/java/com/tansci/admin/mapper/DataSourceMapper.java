package com.tansci.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.admin.api.domain.DataSource;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName： DataSourceMapper.java
 * @ClassPath： com.tansci.admin.mapper.DataSourceMapper.java
 * @Description： 数据源
 * @Author： tanyp
 * @Date： 2022/5/31 15:16
 **/
@Mapper
public interface DataSourceMapper extends BaseMapper<DataSource> {
}
