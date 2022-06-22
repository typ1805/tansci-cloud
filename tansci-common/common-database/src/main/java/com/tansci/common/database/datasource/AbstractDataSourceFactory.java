package com.tansci.common.database.datasource;

import com.tansci.common.database.DataSourceFactory;
import com.tansci.common.database.DbDialect;
import com.tansci.common.database.DbQuery;
import com.tansci.common.database.DialectFactory;
import com.tansci.common.database.constants.DbQueryProperty;
import com.tansci.common.database.constants.DbType;
import com.tansci.common.database.exception.DataQueryException;
import com.tansci.common.database.query.AbstractDbQueryFactory;
import com.tansci.common.database.query.CacheDbQueryFactory;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * @path：com.tansci.common.database.datasource.AbstractDataSourceFactory.java
 * @className：AbstractDataSourceFactory.java
 * @description：数据源工厂
 * @author：tanyp
 * @dateTime：2022/06/13 11:33
 * @editNote：
 */
public abstract class AbstractDataSourceFactory implements DataSourceFactory {

    @Override
    public DbQuery createDbQuery(DbQueryProperty property) {
        property.viald();
        DbType dbType = DbType.getDbType(property.getDbType());
        DataSource dataSource = createDataSource(property);
        DbQuery dbQuery = createDbQuery(dataSource, dbType);
        return dbQuery;
    }

    public DbQuery createDbQuery(DataSource dataSource, DbType dbType) {
        DbDialect dbDialect = DialectFactory.getDialect(dbType);
        if (dbDialect == null) {
            throw new DataQueryException("该数据库类型正在开发中");
        }

        AbstractDbQueryFactory dbQuery = new CacheDbQueryFactory();
        dbQuery.setDataSource(dataSource);
        dbQuery.setJdbcTemplate(new JdbcTemplate(dataSource));
        dbQuery.setDbDialect(dbDialect);
        return dbQuery;
    }

    public DataSource createDataSource(DbQueryProperty property) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(trainToJdbcUrl(property));
        dataSource.setUsername(property.getUsername());
        dataSource.setPassword(property.getPassword());
        return dataSource;
    }

    protected String trainToJdbcUrl(DbQueryProperty property) {
        String url = DbType.getDbType(property.getDbType()).getUrl();
        if (StringUtils.isEmpty(url)) {
            throw new DataQueryException("无效数据库类型!");
        }
        url = url.replace("${host}", property.getHost());
        url = url.replace("${port}", String.valueOf(property.getPort()));
        if (DbType.ORACLE.getDb().equals(property.getDbType()) || DbType.ORACLE_12C.getDb().equals(property.getDbType())) {
            url = url.replace("${sid}", property.getSid());
        } else {
            url = url.replace("${dbName}", property.getDbName());
        }
        return url;
    }

}
