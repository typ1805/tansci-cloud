package com.tansci.common.database.query;

import com.tansci.common.database.cache.DefaultSqlCache;
import com.tansci.common.database.core.DbColumn;
import com.tansci.common.database.core.DbTable;
import com.tansci.common.database.core.PageResult;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @path：com.tansci.common.database.query.CacheDbQueryFactory.java
 * @className：CacheDbQueryFactory.java
 * @description：数据库查询缓存工厂
 * @author：tanyp
 * @dateTime：2022/06/13 11:33
 * @editNote：
 */
public class CacheDbQueryFactory extends AbstractDbQueryFactory {

    /**
     * 默认缓存5分钟
     */
    private static long DEFAULT_EXPIRE = 5 * 60 * 1000;
    private static DefaultSqlCache sqlCache = new DefaultSqlCache(100, DEFAULT_EXPIRE);

    private <T> T putCacheValue(String key, T value, long ttl) {
        sqlCache.put(key, value, ttl);
        return value;
    }

    @Override
    public List<DbColumn> getTableColumns(String dbName, String tableName) {
        Object[] args = new Object[]{dbName, tableName};
        Optional.ofNullable(sqlCache.get(sqlCache.buildSqlCacheKey(super.dataSource.toString() + ":getTableColumns", args)));
        return super.getTableColumns(dbName, tableName);
    }

    @Override
    public List<DbTable> getTables(String dbName) {
        Object[] args = new Object[]{dbName};
        String cacheKey = sqlCache.buildSqlCacheKey(super.dataSource.toString() + ":getTables", args);
        return (List<DbTable>) Optional.ofNullable(sqlCache.get(cacheKey)).orElse(putCacheValue(cacheKey, super.getTables(dbName), DEFAULT_EXPIRE));
    }

    @Override
    public int count(String sql) {
        String cacheKey = sqlCache.buildSqlCacheKey(super.dataSource.toString() + ":" + sql, null);
        return (int) Optional.ofNullable(sqlCache.get(cacheKey)).orElse(putCacheValue(cacheKey, super.count(sql), DEFAULT_EXPIRE));
    }

    @Override
    public int count(String sql, Object[] args) {
        String cacheKey = sqlCache.buildSqlCacheKey(super.dataSource.toString() + ":" + sql, args);
        return (int) Optional.ofNullable(sqlCache.get(cacheKey)).orElse(putCacheValue(cacheKey, super.count(sql, args), DEFAULT_EXPIRE));
    }

    @Override
    public int count(String sql, Map<String, Object> params) {
        String cacheKey = sqlCache.buildSqlCacheKey(super.dataSource.toString() + ":" + sql, params.values().toArray());
        return (int) Optional.ofNullable(sqlCache.get(cacheKey)).orElse(putCacheValue(cacheKey, super.count(sql, params), DEFAULT_EXPIRE));
    }

    @Override
    public List<Map<String, Object>> queryList(String sql) {
        String cacheKey = sqlCache.buildSqlCacheKey(super.dataSource.toString() + ":" + sql, null);
        return (List<Map<String, Object>>) Optional.ofNullable(sqlCache.get(cacheKey)).orElse(putCacheValue(cacheKey, super.queryList(sql), DEFAULT_EXPIRE));
    }

    @Override
    public List<Map<String, Object>> queryList(String sql, Object[] args) {
        String cacheKey = sqlCache.buildSqlCacheKey(super.dataSource.toString() + ":" + sql, args);
        return (List<Map<String, Object>>) Optional.ofNullable(sqlCache.get(cacheKey)).orElse(putCacheValue(cacheKey, super.queryList(sql, args), DEFAULT_EXPIRE));
    }

    @Override
    public PageResult<Map<String, Object>> queryByPage(String sql, long offset, long size) {
        Object[] args = new Object[]{offset, size};
        String cacheKey = sqlCache.buildSqlCacheKey(super.dataSource.toString() + ":" + sql, args);
        return (PageResult<Map<String, Object>>) Optional.ofNullable(sqlCache.get(cacheKey)).orElse(putCacheValue(cacheKey, super.queryByPage(sql, offset, size), DEFAULT_EXPIRE));
    }

    @Override
    public PageResult<Map<String, Object>> queryByPage(String sql, Object[] args, long offset, long size) {
        Object[] objects = Arrays.copyOf(args, args.length + 2);
        objects[args.length] = offset;
        objects[args.length + 1] = size;
        String cacheKey = sqlCache.buildSqlCacheKey(super.dataSource.toString() + ":" + sql, objects);
        return (PageResult<Map<String, Object>>) Optional.ofNullable(sqlCache.get(cacheKey)).orElse(putCacheValue(cacheKey, super.queryByPage(sql, args, offset, size), DEFAULT_EXPIRE));
    }

    @Override
    public PageResult<Map<String, Object>> queryByPage(String sql, Map<String, Object> params, long offset, long size) {
        Object[] args = params.values().toArray();
        Object[] objects = Arrays.copyOf(args, args.length + 2);
        objects[args.length] = offset;
        objects[args.length + 1] = size;
        String cacheKey = sqlCache.buildSqlCacheKey(super.dataSource.toString() + ":" + sql, objects);
        return (PageResult<Map<String, Object>>) Optional.ofNullable(sqlCache.get(cacheKey)).orElse(putCacheValue(cacheKey, super.queryByPage(sql, params, offset, size), DEFAULT_EXPIRE));
    }

}
