import {axios} from "../utils/request";
const api = {
    userPre: '/api/user'
}

//登录
export function loginAPI(userInfo) {
    return axios({
        url: `${api.userPre}/login`,
        method: 'post',
        data: userInfo
    })
}

//注册
export function registerAPI(formItem) {
    return axios({
        url: `${api.userPre}/register`,
        method: 'post',
        data: formItem
    })
}

//根据token获取用户信息
export function getUserInfoAPI() {
    return axios({
        url: `${api.userPre}/getUserInfo`,
        method: 'get',
    })
}

//更新用户信息
export function updateAPI(formItem) {
    return axios({
        url: `${api.userPre}/update/`,
        method: 'post',
        data: formItem
    })
}

//用户评分
export function rateMajorAPI(formItem) {
    return axios({
        url: `${api.userPre}/rateMajor/`,
        method: 'post',
        data: formItem
    })
}

//用户评分
export function getAllRateAPI(userId) {
    return axios({
        url: `${api.userPre}/getAllRate/${userId}`,
        method: 'get'
    })
}
