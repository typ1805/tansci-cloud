import axios from '@/utils/axios'

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
    return axios.get('/admin/sysMenu/save', params).then(res => res.data);
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
export const deleteMenu = (params: any) => {
    return axios.get('/admin/sysMenu/delete', {params: params}).then(res => res.data);
} 
