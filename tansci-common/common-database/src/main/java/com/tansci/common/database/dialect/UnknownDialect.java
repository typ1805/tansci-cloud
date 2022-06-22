package com.tansci.common.database.dialect;

import com.tansci.common.database.core.DbColumn;
import com.tansci.common.database.core.DbTable;
import com.tansci.common.database.exception.DataQueryException;
import org.springframework.jdbc.core.RowMapper;

/**
 * @path：com.tansci.common.database.dialect.UnknownDialect.java
 * @className：UnknownDialect.java
 * @description：未知 数据库方言
 * @author：tanyp
 * @dateTime：2022/06/13 11:33
 * @editNote：
 */
public class UnknownDialect extends AbstractDbDialect {

    @Override
    public String columns(String dbName, String tableName) {
        throw new DataQueryException("不支持的数据库类型");
    }

    @Override
    public String tables(String dbName) {
        throw new DataQueryException("不支持的数据库类型");
    }

    @Override
    public String buildPaginationSql(String sql, long offset, long count) {
        throw new DataQueryException("不支持的数据库类型");
    }

    @Override
    public String count(String sql) {
        throw new DataQueryException("不支持的数据库类型");
    }

    @Override
    public RowMapper<DbColumn> columnMapper() {
        throw new DataQueryException("不支持的数据库类型");
    }

    @Override
    public RowMapper<DbTable> tableMapper() {
        throw new DataQueryException("不支持的数据库类型");
    }

}
