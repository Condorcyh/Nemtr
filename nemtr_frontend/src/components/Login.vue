<template>
    <div>
        <div>
            <el-link href="https://www.chsi.com.cn/" target="_blank" class="topLink">学信网</el-link>
            <el-link href="https://my.chsi.com.cn/archive/index.jsp" target="_blank" class="topLink">学籍查询</el-link>
            <el-link href="https://www.chsi.com.cn/xlcx/" target="_blank" class="topLink">学历查询</el-link>
            <el-link href="https://www.chsi.com.cn/xlcx/bgcx.jsp" target="_blank" class="topLink">在线验证</el-link>
            <el-link href="https://my.chsi.com.cn/archive/index.jsp#txjd" target="_blank" class="topLink">图像校对</el-link>
            <el-link href="https://my.chsi.com.cn/archive/index.jsp" target="_blank" class="topLink">学信档案</el-link>
            <el-link href="https://gaokao.chsi.com.cn/" target="_blank" class="topLink">高考</el-link>
            <el-link href="https://yz.chsi.com.cn/" target="_blank" class="topLink">研招</el-link>
            <el-link href="https://www.gatzs.com.cn/" target="_blank" class="topLink">港澳台招生</el-link>
            <el-link href="https://www.gfbzb.gov.cn/" target="_blank" class="topLink">征兵</el-link>
            <el-link href="https://www.ncss.cn/" target="_blank" class="topLink">就业</el-link>
            <el-link href="https://xz.chsi.com.cn/home.action" target="_blank" class="topLink">学职平台</el-link>
        </div>

        <el-row style="position:absolute;left: 1000px;width: 400px">
            <el-dropdown>
                <span class="el-dropdown-link">个人中心<i class="el-icon-arrow-down el-icon--right"/></span>
                <el-dropdown-menu slot="dropdown">
                    <router-link to="/homepage">
                        <el-dropdown-item>基本信息</el-dropdown-item>
                    </router-link>
                    <router-link to="/recpage">
                        <el-dropdown-item>志愿推荐</el-dropdown-item>
                    </router-link>
                    <el-dropdown-item divided>登出系统</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
            <router-link to="/main">
                <el-link type="primary" style="margin-left: 20px">返回首页</el-link>
            </router-link>
        </el-row>

        <div class="login-container">
            <div class="login-box">
                <div class="login-title">
                    <img src="../assets/welcome.png" alt="">
                </div>
                <el-form :model="form" class="login-form">
                    <div class="inputBox">
                        <el-form-item>
                            <el-input v-model="form.checkName"
                                      prefix-icon="el-icon-mobile-phone"
                                      placeholder="请输入手机号/用户名/邮箱"/>
                        </el-form-item>
                        <span class="promptMsg" id="userMsg">请输入手机号/用户名/邮箱</span>
                    </div>
                    <div class="inputBox">
                        <el-form-item>
                            <el-input v-model="form.pwd"
                                      prefix-icon="el-icon-lock"
                                      placeholder="请输入密码"
                                      type="password"/>
                        </el-form-item>
                        <span class="promptMsg" id="pwdMsg">请输入密码</span>
                    </div>
                    <el-button type="primary" class="login-btn" @click="handleLogin" style="width: 100%; margin-bottom: 10px" :loading="loginLoading">
                        登录
                    </el-button>
                    <div class="login-choice">
                        <router-link to="/register">没有账号？立即注册</router-link>
                        <span @click="forgetPwd">忘记密码</span>
                    </div>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script>
import {mapActions} from "vuex";

export default {
    name: "login",
    data() {
        return {
            form: {
                checkName: '',
                pwd: '',
            },
            loginLoading: false,
            resetPwdForm: {
                checkName: '',
                pwd: '',
            }
        }
    },

    watch: {
        $route: {
            handler: function (route) {
                this.redirect = route.query && route.query.redirect
            },
            immediate: true
        },
    },
    methods: {
        ...mapActions([
            'login'
        ]),
        handleLogin() {
            if (this.checkPwd()) {
                this.loginLoading = true
                this.login(this.form)
                this.loginLoading = false
                this.form = {
                    checkName: '',
                    pwd: '',
                }
            } else {
                this.checkPwd()
            }
        },
        checkPwd(){
            var pwd = this.form.pwd.trim();  // 获取字段值
            pwd = pwd.replace(/(^\s*)|(\s*$)/g, '');
            if (pwd === '' || pwd === undefined || pwd == null) {
                document.getElementById("pwdMsg").style.display = "inline";
                return false;
            } else {
                document.getElementById("pwdMsg").style.display = "none";
                return true;
            }
        },
        forgetPwd() {
            this.set_resetPwdDialogVisible(true)
        },
    }
}
</script>

<style scoped>
.topLink {
    margin-left: 50px;
    margin-top: -80px;
}

.el-dropdown-link {
    margin-left: 20px;
    cursor: pointer;
    color: #409EFF;
}

.el-icon-arrow-down {
    font-size: 12px;
}

a {
    text-decoration: none;
}

.login-container {
    /*absolute而不是fixed，否则对话框弹出整个页面会变灰*/
    position: absolute;
    top: 100px;
    left: 0;
    bottom: 0;
    right: 0;
    width: 100%;
    height: 80%;
    background-size: 100% 100%;
}

.login-box {
    background-color: white;
    position: absolute;
    display: flex;
    flex-direction: column;
    left: 50%;
    top: 40%;
    transform: translate(-50%, -50%);
    width: 33%;
    min-width: 300px;
    max-height: 400px;
    border-radius: 15px;
    box-shadow: 0 0 10px gray;
}

.login-title {
    width: 100%;
    height: 20%;
    line-height: 20%;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-top: 3.8%;
    margin-bottom: 4%;
}

.login-title img {
    width: 50%;
    min-height: 60%;
}

.login-form {
    width: 91.1%;
    flex: 1;
    padding: 0 4.4%;
}

.inputBox {
    height: 14.6%;
    margin-bottom: 6%;
}

.login-choice {
    display: flex;
    align-items: center;
    justify-content: space-between;
    width: 100%;
    margin-top: 2.5%;
    margin-bottom: 9.0%;
}

.login-choice a {
    font-size: 13px;
    text-decoration: none;
    color: gray;
}

.login-choice a:hover {
    color: #65acff;
}

.login-choice span {
    font-size: 13px;
    text-decoration: none;
    color: gray;
    cursor: pointer;
}

.login-choice span:hover {
    color: #65acff;
}

.promptMsg {
    line-height: 14px;
    color: #ff463f;
    font-size: 14px;
    display: none;
}
</style>
