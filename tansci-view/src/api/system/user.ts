import axios from '@/utils/axios'

/**
 * 用户列表分页
 */
export function userPage(params: any) {
    return axios.get('/admin/sysUser/page', { params: params }).then(res => res.data);
}
/**
 * 添加用户
 */
export function addUser(params: any) {
    return axios.post('/admin/sysUser/save', params).then(res => res.data);
}
/**
 * 修改用户
 */
export function updateUser(params: any) {
    return axios.post('/admin/sysUser/update', params).then(res => res.data);
}
/**
 * 删除用户
 */
export function delUser(params: any) {
    return axios.get('/admin/sysUser/delete', { params: params }).then(res => res.data);
}
/**
 * 修改密码
 */
export function modifyPass(params: any) {
    return axios.post('/admin/sysUser/modifyPass', params).then(res => res.data);
}