import axios from '@/utils/axios'

/**
 * 分页列表
 */
export function dicList(params: any) {
    return axios.get('/admin/dic/dicList', { params: params }).then(res => res.data);
}
/**
 * 字典列表
 */
export function getGroupNameByList(params: any) {
    return axios.get('/admin/dic/getGroupNameByList', { params: params }).then(res => res.data);
}
/**
 * 添加字典
 */
export function saveDic(params: any) {
    return axios.post('/admin/dic/save', params).then(res => res.data);
}
/**
 * 修改字典
 */
export function updateDic(params: any) {
    return axios.post('/admin/dic/update', params).then(res => res.data);
}
/**
 * 删除字典
 */
export function delDic(params: any) {
    return axios.get('/admin/dic/del', { params: params }).then(res => res.data);
}