package com.tansci.common.database.dialect;

/**
 * @path：com.tansci.common.database.dialect.Oracle12cDialect.java
 * @className：Oracle12cDialect.java
 * @description：ORACLE Oracle12c+数据库方言
 * @author：tanyp
 * @dateTime：2022/06/13 11:33
 * @editNote：
 */
public class Oracle12cDialect extends OracleDialect {

    @Override
    public String buildPaginationSql(String originalSql, long offset, long count) {
        StringBuilder sqlBuilder = new StringBuilder(originalSql);
        sqlBuilder.append(" OFFSET ").append(offset).append(" ROWS FETCH NEXT ").append(count).append(" ROWS ONLY ");
        return sqlBuilder.toString();
    }

}
