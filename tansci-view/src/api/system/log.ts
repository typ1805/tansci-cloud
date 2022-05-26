import axios from '@/utils/axios'

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