package com.tansci.common.database;

import com.tansci.common.database.core.DbColumn;
import com.tansci.common.database.core.DbTable;
import org.springframework.jdbc.core.RowMapper;

/**
 * @path：com.tansci.common.database.DbDialect.java
 * @className：DbDialect.java
 * @description：表数据查询接口
 * @author：tanyp
 * @dateTime：2022/06/13 11:33
 * @editNote：
 */
public interface DbDialect {

    RowMapper<DbTable> tableMapper();

    RowMapper<DbColumn> columnMapper();

    /**
     * 获取指定表的所有列
     *
     * @param dbName
     * @param tableName
     * @return
     */
    String columns(String dbName, String tableName);

    /**
     * 获取数据库下的 所有表
     *
     * @param dbName
     * @return
     */
    String tables(String dbName);

    /**
     * 构建 分页 sql
     *
     * @param sql
     * @param offset
     * @param count
     * @return
     */
    String buildPaginationSql(String sql, long offset, long count);

    /**
     * 包装 count sql
     *
     * @param sql
     * @return
     */
    String count(String sql);

}
