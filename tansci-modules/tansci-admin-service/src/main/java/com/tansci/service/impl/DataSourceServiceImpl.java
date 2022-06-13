package com.tansci.service.impl;

import com.alibaba.nacos.common.utils.Objects;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.DataSourceFactory;
import com.tansci.DbQuery;
import com.tansci.constants.DbQueryProperty;
import com.tansci.domain.DataSource;
import com.tansci.dto.SourceDto;
import com.tansci.exception.BusinessException;
import com.tansci.mapper.DataSourceMapper;
import com.tansci.service.DataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName： DataSourceServiceImpl.java
 * @ClassPath： com.tansci.service.impl.DataSourceServiceImpl.java
 * @Description： 数据源
 * @Author： tanyp
 * @Date： 2022/5/31 15:21
 **/
@Slf4j
@Service
public class DataSourceServiceImpl extends ServiceImpl<DataSourceMapper, DataSource> implements DataSourceService {

    @Autowired
    private DataSourceFactory dataSourceFactory;

    @Override
    public IPage<DataSource> page(Page page, SourceDto dto) {
        return this.baseMapper.selectPage(page,
                Wrappers.<DataSource>lambdaQuery()
                        .eq(Objects.nonNull(dto.getStatus()), DataSource::getStatus, dto.getStatus())
                        .eq(Objects.nonNull(dto.getDbType()), DataSource::getType, dto.getDbType())
                        .like(Objects.nonNull(dto.getName()), DataSource::getName, dto.getName())
                        .orderByDesc(DataSource::getUpdateTime)
        );
    }

    @Override
    public List<DataSource> list(SourceDto dto) {
        return this.baseMapper.selectList(
                Wrappers.<DataSource>lambdaQuery()
                        .eq(Objects.nonNull(dto.getStatus()), DataSource::getStatus, dto.getStatus())
                        .eq(Objects.nonNull(dto.getDbType()), DataSource::getType, dto.getDbType())
                        .like(Objects.nonNull(dto.getName()), DataSource::getName, dto.getName())
                        .orderByDesc(DataSource::getUpdateTime)
        );
    }

    @Override
    public boolean save(DataSource source) {
        source.setUpdateTime(LocalDateTime.now());
        source.setCreateTime(LocalDateTime.now());
        this.baseMapper.insert(source);
        return true;
    }

    @Override
    public Object update(DataSource source) {
        source.setUpdateTime(LocalDateTime.now());
        return this.baseMapper.updateById(source);
    }

    @Transactional
    @Override
    public Object delete(SourceDto dto) {
        return this.baseMapper.deleteById(dto.getId());
    }

    @Override
    public Object checkConnection(SourceDto dto) {
        try {
            DataSource source = this.baseMapper.selectById(dto.getId());
            if (Objects.isNull(source) || Objects.equals(1, source.getStatus())) {
                throw new BusinessException("数据源不存在或已禁用，请核实！");
            }

            DbQueryProperty dbQueryProperty = new DbQueryProperty(source.getType(), source.getHost(), source.getUsername(), source.getPassword(), Integer.parseInt(source.getPort()), source.getName(), source.getId());
            DbQuery dbQuery = dataSourceFactory.createDbQuery(dbQueryProperty);

            Boolean valid = dbQuery.valid();
            return valid ? "检测数据库连通性成功！" : "数据库连接有误，请检查数据库配置是否正确！";
        } catch (Exception e) {
            log.error("=====检测数据库连通性失败：{}", e);
            return "数据库连接有误，请检查数据库配置是否正确！";
        }
    }

    @Override
    public Object getDbTables(SourceDto dto) {
        DataSource source = this.baseMapper.selectById(dto.getId());
        DbQueryProperty dbQueryProperty = new DbQueryProperty(source.getType(), source.getHost(), source.getUsername(), source.getPassword(), Integer.parseInt(source.getPort()), source.getName(), source.getId());
        DbQuery dbQuery = dataSourceFactory.createDbQuery(dbQueryProperty);
        return dbQuery.getTables(source.getName());
    }

    @Override
    public Object getDbTableColumns(SourceDto dto) {
        DataSource source = this.baseMapper.selectById(dto.getId());
        DbQueryProperty dbQueryProperty = new DbQueryProperty(source.getType(), source.getHost(), source.getUsername(), source.getPassword(), Integer.parseInt(source.getPort()), source.getName(), source.getId());
        DbQuery dbQuery = dataSourceFactory.createDbQuery(dbQueryProperty);
        return dbQuery.getTableColumns(source.getName(), dto.getTableName());
    }

}
