import {loginAPI, registerAPI, getUserInfoAPI, getAllRateAPI} from '../../api/users';
import {Message} from "element-ui";
import cookie from 'js-cookie';
import router, {resetRouter} from '../../router';
import {setToken, removeToken} from '../../utils/auth'

const user = {
    state: {
        isLogin: false,
        name: '',
        userInfo: {
            id: '',
            phone: '',
            pwd: '',
            name: '',
            provinceName: '',
            entranceType: '',
            highSchool: '',
            userRateData: []
        },
        modifyUserInfoParams: {},
        resetPwdDialogVisible: false,
        topUpVipDialogVisible: false,
    },
    mutations: {
        reset_state:function(state){
            state.token = ''
            state.isLogin = false
            state.userInfo = {}
        },
        set_token: function(state, token){
            state.token = token
        },
        set_isLogin: function (state, data) {
            state.isLogin = data;
        },
        set_name: function (state, data) {
            state.name = data;
        },
        set_userInfo: function (state, data) {
            state.userInfo = data;
        },
        set_modifyUserInfoParams: function (state, data) {
            state.modifyUserInfoParams = data;
        },
        set_resetPwdDialogVisible: function (state, data) {
            state.resetPwdDialogVisible = data;
        },
        set_topUpVipDialogVisible: function (state, data) {
            state.topUpVipDialogVisible = data;
        }
    },
    actions: {
        login: async ({commit, dispatch}, loginData) => {
            const res = await loginAPI(loginData);
            if (res.data.code === 200) {
                setToken(res.data.data.name)
                commit('set_token', res.data.data.token)
                commit('set_isLogin', true)
                Message({
                    type: 'success',
                    message: '登录成功'
                })
                cookie.set('nemtr_token', res.data.data.token)
                dispatch('getUserInfo')
                router.push('/')
            } else {
                Message({
                    type: 'error',
                    message: res.data.message
                })
            }
        },

        register: async ({state, commit, dispatch}, registerData) => {
            const res = await registerAPI(registerData);
            if (res.data.code === 200) {
                Message({
                    message: '注册成功',
                    type: 'success'
                })
                // 注册成功后直接登陆
                let loginData = {
                    checkName: registerData.name,
                    pwd: registerData.pwd,
                }
                dispatch('login', loginData);
            } else {
                Message({
                    message: res.data.message,
                    type: 'error'
                })
            }
        },

        getUserInfo: async ({state, commit}) => {
            return new Promise((resolve, reject) => {
                getUserInfoAPI().then(response => {
                    const data = response.data.data.userInfo
                    if (!data) {
                        reject('登录已过期，请重新登录')
                        router.push('/login')
                    }
                    commit('set_userInfo', data)
                    commit('set_modifyUserInfoParams', data)
                    commit('set_name', data.name)
                    cookie.set('nemtr_user', state.userInfo)
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },

        logout: async({ commit }) => {
            removeToken()
            resetRouter()
            commit('reset_state')
        },
    }
}

export default user
