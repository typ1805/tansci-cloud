import axios from '@/utils/axios'

/**
 * 数据源list
 */
export function sourceList(params: any) {
    return axios.get('/admin/dataSource/list', { params: params }).then(res => res.data);
}

/**
 * 根据数据源的id来获取指定数据库表
 */
export function getDbTables(params: any) {
    return axios.post('/admin/dataSource/getDbTables', params).then(res => res.data);
}

/**
 * 根据数据源的id来获取指定数据库表的表结构
 */
export function getDbTableColumns(params: any) {
    return axios.post('/admin/dataSource/getDbTableColumns', params).then(res => res.data);
}