package com.tansci.cache;

import com.tansci.utils.MD5Util;

import java.util.Arrays;

/**
 * @path：com.tansci.cache.SqlCache.java
 * @className：SqlCache.java
 * @description：SQL缓存接口
 * @author：tanyp
 * @dateTime：2022/06/13 11:33
 * @editNote：
 */
public interface SqlCache {

    /**
     * 计算key
     */
    default String buildSqlCacheKey(String sql, Object[] args) {
        return MD5Util.encrypt(sql + ":" + Arrays.toString(args));
    }

    /**
     * 存入缓存
     *
     * @param key   key
     * @param value 值
     */
    void put(String key, Object value, long ttl);

    /**
     * 获取缓存
     *
     * @param key key
     * @return
     */
    <T> T get(String key);

    /**
     * 删除缓存
     *
     * @param key key
     */
    void delete(String key);

}
