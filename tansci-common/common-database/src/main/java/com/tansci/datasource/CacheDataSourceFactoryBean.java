package com.tansci.datasource;

import com.tansci.constants.DbQueryProperty;

import javax.sql.DataSource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @path：com.tansci.datasource.CacheDataSourceFactoryBean.java
 * @className：CacheDataSourceFactoryBean.java
 * @description：数据源工厂
 * @author：tanyp
 * @dateTime：2022/06/13 11:33
 * @editNote：
 */
public class CacheDataSourceFactoryBean extends AbstractDataSourceFactory {

    /**
     * 数据源缓存
     */
    private static Map<String, DataSource> dataSourceMap = new ConcurrentHashMap<>();

    @Override
    public DataSource createDataSource(DbQueryProperty property) {
        String key = property.getHost() + ":" + property.getPort() + ":" + property.getUsername() + ":" + property.getDbName();
        String s = compress(key);
        DataSource dataSource = dataSourceMap.get(s);
        if (null == dataSource) {
            synchronized (CacheDataSourceFactoryBean.class) {
                if (null == dataSource) {
                    dataSource = super.createDataSource(property);
                    dataSourceMap.put(s, dataSource);
                }
            }
        }
        return dataSource;
    }

    // 压缩
    public static String compress(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(str.getBytes());
        byte b[] = md.digest();
        int i;
        StringBuffer buf = new StringBuffer();
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        return buf.toString().substring(8, 24).toUpperCase();
    }

}
