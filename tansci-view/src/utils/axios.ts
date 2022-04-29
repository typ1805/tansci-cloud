import axios, {AxiosInstance, AxiosResponse, AxiosRequestConfig } from 'axios'
import qs from 'qs'
import { showMessage } from "./status"
import { ElMessage } from 'element-plus'
import router from '../router'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

NProgress.inc(0.2)
NProgress.configure({ easing: 'ease', speed: 600, showSpinner: false })

let axiosInstance:AxiosInstance = axios.create({
    // baseURL: process.env.VUE_APP_BASE_URL + "/api/v1/",
    headers: {
        Accept: "application/json, text/plain, */*",
        "Content-Type": "application/x-www-form-urlencoded",
        "X-Requested-With": "XMLHttpRequest"
    },
    transformRequest: [
        function(data) {
            // 由于使用的 form-data传数据所以要格式化
            delete data.Authorization;
            data = qs.stringify(data);
            return data;
        }
    ]
})

// axios实例拦截请求
axiosInstance.interceptors.request.use(
    (config: AxiosRequestConfig) => {
        // const { user } = store.state
        // if (token) {
        //   config.headers.Authorization = `Bearer ${token}`
        // }

        // 启动进度条
        NProgress.start()
        return config;
    },
    (error:any) => {
        return Promise.reject(error);
    }
)

// axios实例拦截响应
axiosInstance.interceptors.response.use(
    (response: AxiosResponse) => {
        if (response.status === 200) {
            // 关闭进度条
            NProgress.done();
            return response;
        } else {
            showMessage(response.status);
            // 关闭进度条
            NProgress.done();
            if (response.data.code == 403 || response.data.code == 401) router.push({path: '/login'})
            return response;
        }
    },
    // 请求失败
    (error: any) => {
        const {response} = error;
        if (response) {
            // 请求已发出，但是不在2xx的范围
            showMessage(response.status);
            return Promise.reject(response.data);
        } else {
            ElMessage.warning('网络连接异常,请稍后再试!');
        }
    }
)

export default axiosInstance