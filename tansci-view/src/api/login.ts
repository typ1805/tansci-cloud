import axios from '@/utils/axios'

/**
 * 登录
 */
export const login = (params: any) => {
    return axios.post('/auth/login',params).then(res => res.data);
};
