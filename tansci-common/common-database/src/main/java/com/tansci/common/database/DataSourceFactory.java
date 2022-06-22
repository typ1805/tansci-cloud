package com.tansci.common.database;

import com.tansci.common.database.constants.DbQueryProperty;

/**
 * @path：com.tansci.common.database.DataSourceFactory.java
 * @className：DataSourceFactory.java
 * @description：数据源工厂
 * @author：tanyp
 * @dateTime：2022/06/13 11:33
 * @editNote：
 */
public interface DataSourceFactory {

    /**
     * 创建数据源实例
     *
     * @param property
     * @return
     */
    DbQuery createDbQuery(DbQueryProperty property);

}
