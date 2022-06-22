package com.tansci.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.admin.api.domain.DataSource;
import com.tansci.admin.api.dto.SourceDto;

import java.util.List;

/**
 * @ClassName： DataSourceService.java
 * @ClassPath： com.tansci.admin.service.DataSourceService.java
 * @Description： 数据源
 * @Author： tanyp
 * @Date： 2022/5/31 15:19
 **/
public interface DataSourceService extends IService<DataSource> {

    IPage<DataSource> page(Page page, SourceDto dto);

    List<DataSource> list(SourceDto dto);

    boolean save(DataSource source);

    Object update(DataSource source);

    Object delete(SourceDto dto);

    Object checkConnection(SourceDto dto);

    Object getDbTables(SourceDto dto);

    Object getDbTableColumns(SourceDto dto);

    Object sqlExecutor(SourceDto dto);

}
