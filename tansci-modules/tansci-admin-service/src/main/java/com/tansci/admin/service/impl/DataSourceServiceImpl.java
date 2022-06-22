package com.tansci.admin.service.impl;

import com.alibaba.nacos.common.utils.Objects;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.admin.api.domain.DataSource;
import com.tansci.admin.api.domain.SysDic;
import com.tansci.admin.api.dto.SourceDto;
import com.tansci.admin.mapper.DataSourceMapper;
import com.tansci.admin.service.DataSourceService;
import com.tansci.admin.service.SysDicService;
import com.tansci.common.core.enums.Enums;
import com.tansci.common.core.exception.BusinessException;
import com.tansci.common.database.DataSourceFactory;
import com.tansci.common.database.DbQuery;
import com.tansci.common.database.constants.DbQueryProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName： DataSourceServiceImpl.java
 * @ClassPath： com.tansci.admin.service.impl.DataSourceServiceImpl.java
 * @Description： 数据源
 * @Author： tanyp
 * @Date： 2022/5/31 15:21
 **/
@Slf4j
@Service
public class DataSourceServiceImpl extends ServiceImpl<DataSourceMapper, DataSource> implements DataSourceService {

    @Autowired
    private DataSourceFactory dataSourceFactory;

    @Autowired
    private SysDicService sysDicService;

    @Override
    public IPage<DataSource> page(Page page, SourceDto dto) {
        IPage<DataSource> iPage = this.baseMapper.selectPage(page,
                Wrappers.<DataSource>lambdaQuery()
                        .eq(Objects.nonNull(dto.getStatus()), DataSource::getStatus, dto.getStatus())
                        .eq(Objects.nonNull(dto.getDbType()), DataSource::getType, dto.getDbType())
                        .like(Objects.nonNull(dto.getName()), DataSource::getName, dto.getName())
                        .orderByDesc(DataSource::getUpdateTime)
        );

        if (Objects.nonNull(iPage.getRecords())) {
            List<SysDic> dics = sysDicService.list(Wrappers.<SysDic>lambdaQuery().eq(SysDic::getGroupName, "data_source").ne(SysDic::getDicValue, -1));
            iPage.getRecords().forEach(item -> {
                Optional<SysDic> dOptional = dics.stream().filter(d -> Objects.equals(d.getDicValue(), item.getType())).findFirst();
                if (dOptional.isPresent()) {
                    item.setTypeName(dOptional.get().getDicLabel());
                }

                if (Objects.nonNull(item.getStatus())) {
                    item.setStatusName(Enums.getVlaueByGroup(item.getStatus(), "status"));
                }
            });
        }
        return iPage;
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
            if (Objects.isNull(source) || Objects.equals(2, source.getStatus())) {
                throw new BusinessException("数据源不存在或已禁用，请核实！");
            }

            DbQueryProperty dbQueryProperty = new DbQueryProperty(String.valueOf(source.getType()), source.getHost(), source.getUsername(), source.getPassword(), Integer.parseInt(source.getPort()), source.getName(), source.getId());
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
        DbQueryProperty dbQueryProperty = new DbQueryProperty(String.valueOf(source.getType()), source.getHost(), source.getUsername(), source.getPassword(), Integer.parseInt(source.getPort()), source.getName(), source.getId());
        DbQuery dbQuery = dataSourceFactory.createDbQuery(dbQueryProperty);
        return dbQuery.getTables(source.getName());
    }

    @Override
    public Object getDbTableColumns(SourceDto dto) {
        DataSource source = this.baseMapper.selectById(dto.getId());
        DbQueryProperty dbQueryProperty = new DbQueryProperty(String.valueOf(source.getType()), source.getHost(), source.getUsername(), source.getPassword(), Integer.parseInt(source.getPort()), source.getName(), source.getId());
        DbQuery dbQuery = dataSourceFactory.createDbQuery(dbQueryProperty);
        return dbQuery.getTableColumns(source.getName(), dto.getTableName());
    }

    @Override
    public Object sqlExecutor(SourceDto dto) {
        if (Objects.isNull(dto.getSql()) || Objects.isNull(dto.getId())) {
            throw new BusinessException("请求参数有误，请核实！");
        }

        DataSource source = this.baseMapper.selectById(dto.getId());
        DbQueryProperty dbQueryProperty = new DbQueryProperty(String.valueOf(source.getType()), source.getHost(), source.getUsername(), source.getPassword(), Integer.parseInt(source.getPort()), source.getName(), source.getId());
        DbQuery dbQuery = dataSourceFactory.createDbQuery(dbQueryProperty);
        if (Objects.nonNull(dto.getSize()) && Objects.nonNull(dto.getOffset())) {
            return dbQuery.queryByPage(dto.getSql(), dto.getOffset(), dto.getSize());
        } else {
            return dbQuery.queryList(dto.getSql());
        }
    }

}
