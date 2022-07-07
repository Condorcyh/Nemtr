import Vue from 'vue'
import Router from 'vue-router'
//导入组件
import Main from "../components/Main";
import Login from "../components/Login";
import Register from "../components/Register";
import Homepage from "../components/Homepage";
import RecPage from "../components/RecPage";

Vue.use(Router)

const routes = [
    //  默认首页
    {
        path: '/',
        redirect: '/main'
    },
    {
        //首页
        path: '/main',
        component: Main
    },
    //登录页
    {
        path: '/login',
        component: Login
    },
    //注册页
    {
        path: '/register',
        component: Register
    },
    //个人主页
    {
        path: '/homepage',
        component: Homepage
    },
    //推荐页面
    {
        path: '/recpage',
        component: RecPage
    },
]
const createRouter = () => new Router({
    // mode: 'history', // require service support
    scrollBehavior: () => ({ y: 0 }),
    routes
})
const router = createRouter()

export function resetRouter() {
    const newRouter = createRouter()
    router.matcher = newRouter.matcher // reset router
}

export default router
