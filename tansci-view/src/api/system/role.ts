import axios from '@/utils/axios'

/**
 * 角色列表分页
 */
export function rolePage(params: any) {
    return axios.get('/admin/sysRole/page', { params: params }).then(res => res.data);
}
/**
 * 角色列表
 */
export function roleList(params: any) {
    return axios.get('/admin/sysRole/list', { params: params }).then(res => res.data);
}
/**
 * 添加角色
 */
export function addRole(params: any) {
    return axios.post('/admin/sysRole/save', params).then(res => res.data);
}
/**
 * 修改角色
 */
export function updateRole(params: any) {
    return axios.post('/admin/sysRole/update', params).then(res => res.data);
}
/**
 * 删除角色
 */
export function delRole(params: any) {
    return axios.get('/admin/sysRole/delete', { params: params }).then(res => res.data);
}
/**
 * 添加用户角色
 */
export function userRoleSave(params: any) {
    return axios.post('/admin/sysRole/userRoleSave', params).then(res => res.data);
}