import axios from '@/utils/axios'

/**
 * 登录
 */
interface ILogin {
    token: string;
    expires: number;
}
export const login = (params: ILogin) => {
    return axios.post('/auth/login',params).then(res => res.data);
};
