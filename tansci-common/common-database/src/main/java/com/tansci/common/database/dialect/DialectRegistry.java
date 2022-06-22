package com.tansci.common.database.dialect;

import com.tansci.common.database.DbDialect;
import com.tansci.common.database.constants.DbType;

import java.util.EnumMap;
import java.util.Map;

/**
 * @path：com.tansci.common.database.dialect.DialectRegistry.java
 * @className：DialectRegistry.java
 * @description：方言注册
 * @author：tanyp
 * @dateTime：2022/06/13 11:33
 * @editNote：
 */
public class DialectRegistry {

    private final Map<DbType, DbDialect> dialect_enum_map = new EnumMap<>(DbType.class);

    public DialectRegistry() {
        dialect_enum_map.put(DbType.MARIADB, new MariaDBDialect());
        dialect_enum_map.put(DbType.MYSQL, new MySqlDialect());
        dialect_enum_map.put(DbType.ORACLE_12C, new Oracle12cDialect());
        dialect_enum_map.put(DbType.ORACLE, new OracleDialect());
        dialect_enum_map.put(DbType.POSTGRE_SQL, new PostgreDialect());
        dialect_enum_map.put(DbType.SQL_SERVER2008, new SQLServer2008Dialect());
        dialect_enum_map.put(DbType.SQL_SERVER, new SQLServerDialect());
        dialect_enum_map.put(DbType.OTHER, new UnknownDialect());
    }

    public DbDialect getDialect(DbType dbType) {
        return dialect_enum_map.get(dbType);
    }
}
