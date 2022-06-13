package com.tansci;

import com.tansci.constants.DbQueryProperty;

/**
 * @path：com.tansci.DataSourceFactory.java
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
