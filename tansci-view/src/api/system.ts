import axios from '@/utils/axios'

// =====================菜单管理==================================
/**
 * 获取菜单列表
 */
export const menuList = (params: any) => {
    return axios.get('/admin/sysMenu/list', {params: params}).then(res => res.data);
}
/**
 * 添加菜单信息
 */
export const saveMenu = (params: any) => {
    return axios.post('/admin/sysMenu/save', params).then(res => res.data);
}
/**
 * 更新菜单信息
 */
export const updateMenu = (params: any) => {
    return axios.post('/admin/sysMenu/update', params).then(res => res.data);
}
/**
 * 删除菜单信息
 */
export const delMenu = (params: any) => {
    return axios.get('/admin/sysMenu/delete', {params: params}).then(res => res.data);
} 

// =================数据字典============================
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

// =================用户信息===========================
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

// =================角色管理===========================
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

// ==============日志===============================================
/**
 * 操作日志分页
 */
export function logInfoPage(params: any) {
    return axios.get('/admin/log/logInfoPage', { params: params }).then(res => res.data);
}
/**
 * 异常日志分页
 */
export function logErrorPage(params: any) {
    return axios.get('/admin/log/logErrorPage', { params: params }).then(res => res.data);
}
