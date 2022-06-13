package com.tansci;

import com.tansci.constants.DbType;
import com.tansci.dialect.DialectRegistry;

/**
 * @path：com.tansci.DialectFactory.java
 * @className：DialectFactory.java
 * @description：方言工厂类
 * @author：tanyp
 * @dateTime：2022/06/13 11:33
 * @editNote：
 */
public class DialectFactory {

    private static final DialectRegistry DIALECT_REGISTRY = new DialectRegistry();

    public static DbDialect getDialect(DbType dbType) {
        return DIALECT_REGISTRY.getDialect(dbType);
    }

}
