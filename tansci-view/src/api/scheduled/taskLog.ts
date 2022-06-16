import axios from '@/utils/axios'

/**
 * 调度执行日志分页
 */ 
export function taskLogPage(params: any) {
    return axios.get('/scheduled/taskLog/page', { params: params }).then(res => res.data);
}
/**
 * 清空日志
 */
export function clearTaskLog(params: any) {
    return axios.get('/scheduled/taskLog/clear', { params: params }).then(res => res.data);
}
