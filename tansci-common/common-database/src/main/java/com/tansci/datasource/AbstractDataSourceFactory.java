package com.tansci.datasource;

import com.tansci.DataSourceFactory;
import com.tansci.DbDialect;
import com.tansci.DbQuery;
import com.tansci.DialectFactory;
import com.tansci.constants.DbQueryProperty;
import com.tansci.constants.DbType;
import com.tansci.exception.DataQueryException;
import com.tansci.query.AbstractDbQueryFactory;
import com.tansci.query.CacheDbQueryFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * @path：com.tansci.datasource.AbstractDataSourceFactory.java
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

        AbstractDbQueryFactory dbQuery = new CacheDbQueryFactoryBean();
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
