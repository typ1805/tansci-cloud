import axios from '@/utils/axios'

/**
 * 数据源分页
 */
export function sourcePage(params: any) {
    return axios.get('/admin/dataSource/page', { params: params }).then(res => res.data);
}
/**
 * 添加数据源
 */
export function addSource(params: any) {
    return axios.post('/admin/dataSource/save', params).then(res => res.data);
}
/**
 * 修改数据源
 */
export function updateSource(params: any) {
    return axios.post('/admin/dataSource/update', params).then(res => res.data);
}
/**
 * 删除数据源
 */
export function delSource(params: any) {
    return axios.get('/admin/dataSource/delete', { params: params }).then(res => res.data);
}

/**
 * 数据库连通性
 */
export function checkConnection(params: any) {
    return axios.post('/admin/dataSource/checkConnection', params).then(res => res.data);
}
