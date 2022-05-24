import axios, {AxiosInstance, AxiosRequestConfig, AxiosResponse} from 'axios'
import qs from 'qs'
import {showMessage} from "./status"
import {ElMessage} from 'element-plus'
import router from '../router'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

NProgress.inc(0.2)
NProgress.configure({easing: 'ease', speed: 600, showSpinner: false})

// 根据环境获得不同的代理模式
// const baseURL = import.meta.env.VITE_BASE_URL as string;
const axiosInstance: AxiosInstance = axios.create({
    // baseURL,
    timeout: 10 * 1000, // 30秒超时
    headers: {
        'Content-Type': 'application/json'
    },
})

// axios实例拦截请求
axiosInstance.interceptors.request.use((config: AxiosRequestConfig) => {
        // 设置token
        const obj = JSON.parse(sessionStorage.getItem('token'));
        if (obj && obj.token) {
            config.headers.Authorization = `Bearer ${obj.token}`
        }

        if(config.method === 'post' || config.method === 'put'){
            config.data = qs.stringify(config.data);
        } else if(config.method === 'get' || config.method === 'delete'){
            config.params = qs.parse(config.data);
            config.data = undefined;
        }

        // 启动进度条
        NProgress.start()
        return config;
    },
    (error: any) => {
        return Promise.reject(error);
    }
)

// axios实例拦截响应
axiosInstance.interceptors.response.use(
    (response: AxiosResponse) => {
        if (response.status === 200 && response.data.code == 200) {
            // 关闭进度条
            NProgress.done();
            return response;
        } else {
            ElMessage.warning(showMessage(response.status));
            // 关闭进度条
            NProgress.done();
            if (response.data.code == 403 || response.data.code == 401){
                sessionStorage.clear();
                router.push({path: 'login'});
            } 
            return response;
        }
    },
    // 请求失败
    (error: any) => {
        const {response} = error;
        if (response) {
            // 请求已发出，但是不在2xx的范围
            ElMessage.warning(showMessage(response.status));
            return Promise.reject(response.data);
        } else {
            ElMessage.warning('网络连接异常,请稍后再试!');
        }
    }
)

export default axiosInstance