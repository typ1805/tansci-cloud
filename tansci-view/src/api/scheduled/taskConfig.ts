import axios from '@/utils/axios'

/**
 * 调度分页
 */ 
export function taskConfigPage(params: any) {
    return axios.get('/scheduled/taskConfig/page', { params: params }).then(res => res.data);
}
/**
 * 添加调度
 */
export function addTaskConfig(params: any) {
    return axios.post('/scheduled/taskConfig/save', params).then(res => res.data);
}
/**
 * 修改调度
 */
export function updateTaskConfig(params: any) {
    return axios.post('/scheduled/taskConfig/update', params).then(res => res.data);
}
/**
 * 删除调度
 */
export function delTaskConfig(params: any) {
    return axios.get('/scheduled/taskConfig/del', { params: params }).then(res => res.data);
}