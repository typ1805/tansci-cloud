package com.tansci.common.database.core;

import lombok.Data;

import java.io.Serializable;

/**
 * @path：com.tansci.common.database.core.DbTable.java
 * @className：DbTable.java
 * @description：数据库
 * @author：tanyp
 * @dateTime：2022/06/13 11:33
 * @editNote：
 */
@Data
public class DbTable implements Serializable {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表注释
     */
    private String tableComment;

}
