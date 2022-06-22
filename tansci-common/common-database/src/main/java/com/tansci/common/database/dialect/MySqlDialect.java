package com.tansci.common.database.dialect;

import com.tansci.common.database.core.DbColumn;
import com.tansci.common.database.core.DbTable;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

/**
 * @path：com.tansci.common.database.dialect.MySqlDialect.java
 * @className：MySqlDialect.java
 * @description：MySql 数据库方言
 * @author：tanyp
 * @dateTime：2022/06/13 11:33
 * @editNote：
 */
public class MySqlDialect extends AbstractDbDialect {

    @Override
    public RowMapper<DbColumn> columnMapper() {
        return (ResultSet rs, int rowNum) -> {
            DbColumn entity = new DbColumn();
            entity.setColName(rs.getString("COLNAME"));
            entity.setDataType(rs.getString("DATATYPE"));
            entity.setDataLength(rs.getString("DATALENGTH"));
            entity.setDataPrecision(rs.getString("DATAPRECISION"));
            entity.setDataScale(rs.getString("DATASCALE"));
            entity.setColKey("PRI".equals(rs.getString("COLKEY")) ? true : false);
            entity.setNullable("YES".equals(rs.getString("NULLABLE")) ? true : false);
            entity.setColPosition(rs.getInt("COLPOSITION"));
            entity.setDataDefault(rs.getString("DATADEFAULT"));
            entity.setColComment(rs.getString("COLCOMMENT"));
            return entity;
        };
    }

    @Override
    public RowMapper<DbTable> tableMapper() {
        return (ResultSet rs, int rowNum) -> {
            DbTable entity = new DbTable();
            entity.setTableName(rs.getString("TABLENAME"));
            entity.setTableComment(rs.getString("TABLECOMMENT"));
            return entity;
        };
    }
}
