import axios from '@/utils/axios'
/**
 * 登录
 */
export const login = (params: any) => {
    // return axios.post('/auth/login',params).then(res => res.data);
    return axios({
        url:'/auth/login',
        method:'post',
        params:params
    })
}

/**
 * 登出
 */
export function logout() {
    return axios.post('/auth/signout');
}
