package com.tansci.dialect;

/**
 * @path：com.tansci.dialect.SQLServerDialect.java
 * @className：SQLServerDialect.java
 * @description：SQLServer 数据库方言
 * @author：tanyp
 * @dateTime：2022/06/13 11:33
 * @editNote：
 */
public class SQLServerDialect extends SQLServer2008Dialect {

    @Override
    public String buildPaginationSql(String originalSql, long offset, long count) {
        StringBuilder sqlBuilder = new StringBuilder(originalSql);
        sqlBuilder.append(" OFFSET ").append(offset).append(" ROWS FETCH NEXT ").append(count).append(" ROWS ONLY ");
        return sqlBuilder.toString();
    }

}
