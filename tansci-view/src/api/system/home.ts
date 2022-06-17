import axios from '@/utils/axios'

/**
 * 指标统计
 */
export function statistics(params: any) {
    return axios.get('/admin/home/statistics ', { params: params }).then(res => res.data);
}