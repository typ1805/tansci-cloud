package com.tansci.common.database.constants;

import com.tansci.common.database.exception.DataQueryException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @path：com.tansci.common.database.constants.DbQueryProperty.java
 * @className：DbQueryProperty.java
 * @description：数据库类型
 * @author：tanyp
 * @dateTime：2022/06/13 11:33
 * @editNote：
 */
@Data
@AllArgsConstructor
public class DbQueryProperty implements Serializable {

    private String dbType;

    private String host;

    private String username;

    private String password;

    private Integer port;

    private String dbName;

    private String sid;

    /**
     * 参数合法性校验
     */
    public void viald() {
        if (Objects.isNull(dbType) || Objects.isNull(host) || Objects.isNull(username) || Objects.isNull(password) || Objects.isNull(port)) {
            throw new DataQueryException("参数不完整");
        }
        if (DbType.OTHER.getDb().equals(dbType)) {
            throw new DataQueryException("不支持的数据库类型");
        }
    }

}
