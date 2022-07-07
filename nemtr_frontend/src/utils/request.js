import axios from 'axios'
import { VueAxios } from './axios'
import cookie from 'js-cookie'

// 创建 axios 实例
const service = axios.create({
    baseURL: 'http://139.224.229.148:8010',
    // baseURL: 'http://localhost:8010',
    withCredentials: false,
})

// 创建拦截器，每次发送请求中，使用拦截器，用于传递token信息
service.interceptors.request.use(
    config => {
        // 判断cookie中是否有名称为nemtr_token的数据
        if (cookie.get('nemtr_token')) {
            // 将获取的cookie值放到header中
            config.headers['token'] = cookie.get('nemtr_token')
        }
        return config
    },
    err => {
        return Promise.reject(err)
    }
)

const installer = {
    vm: {},
    install (Vue) {
        Vue.use(VueAxios, service)
    }
}

export {
    installer as VueAxios,
    service as axios
}
