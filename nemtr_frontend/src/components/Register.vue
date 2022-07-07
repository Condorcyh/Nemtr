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
                <span class="el-dropdown-link">
                    个人中心<i class="el-icon-arrow-down el-icon--right"/>
                </span>
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

        <div class="register-container">
            <el-button icon="el-icon-back" type="primary" plain round size="small" @click="backToLogin" style="margin: 10px 20px">返回</el-button>
            <div class="register-box">
                <div class="register-title">
                    <img src="../assets/register.png" alt="">
                </div>
                <el-form :model="form" class="register-form">
                    <div class="inputBox">
                        <el-form-item>
                            <el-input v-model="form.phone"
                                      prefix-icon="el-icon-mobile-phone"
                                      placeholder="请输入手机号"/>
                        </el-form-item>
                        <span class="promptMsg" id="mobileMsg">请输入正确格式的手机号</span>
                    </div>
                    <div class="inputBox">
                        <el-form-item>
                            <el-input v-model="form.name"
                                      prefix-icon="el-icon-user"
                                      placeholder="请输入用户名"/>
                        </el-form-item>
                        <span class="promptMsg" id="nameMsg">请输入用户名</span>
                    </div>
                    <div class="inputBox">
                        <el-form-item>
                            <el-input v-model="form.pwd"
                                      prefix-icon="el-icon-lock"
                                      placeholder="请输入密码"
                                      type="password"/>
                        </el-form-item>
                        <span class="promptMsg" id="pwdMsg">密码最少6位，至少包括一个字母、一个数字、一个特殊字符</span>
                    </div>
                    <el-button type="primary" @click="handleRegister" style="margin-top:5px;margin-bottom:10%;width: 100%" :loading="registerLoading">
                        注册
                    </el-button>
                </el-form>
            </div>
        </div>
    </div>

</template>

<script>
import {mapActions} from "vuex";

export default {
    name: "register",
    data() {
        return {
            form: {
                phone: '',
                name: '',
                pwd: '',
            },
            registerLoading: false,
        }
    },
    methods: {
        ...mapActions([
            'register'
        ]),
        backToLogin() {
            this.$router.push('/login');
        },
        handleRegister() {
            if (this.checkPhone() && this.checkName()) {
                this.registerLoading = true
                this.register(this.form)
                this.registerLoading = false
                this.form = {
                    phone: '',
                    name: '',
                    pwd: ''
                }
            } else {
                this.checkPhone()
                this.checkName()
                this.checkPwd()
            }
        },
        checkPhone() {
            // 检查注册手机号格式
            var reg = /^[1][3,4,5,7,8][0-9]{9}$/;
            var mobile = this.form.phone.trim();
            if (!reg.test(mobile)) {
                document.getElementById("mobileMsg").style.display = "inline";
                return false;
            } else {
                document.getElementById("mobileMsg").style.display = "none";
                return true;
            }
        },
        checkName() {
            let name = this.form.name.trim();  // 获取字段值
            name = name.replace(/(^\s*)|(\s*$)/g, '');
            if (name === '' || name === undefined) {
                document.getElementById("nameMsg").style.display = "inline";
                return false;
            } else {
                document.getElementById("nameMsg").style.display = "none";
                return true;
            }
        },
        checkPwd() {
            var reg = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*?[#?!@$%^&*-_]).{6,}$/;
            var password = this.form.pwd.trim();
            if (!reg.test(password)) {
                document.getElementById("pwdMsg").style.display = "inline";
                return false;
            } else {
                document.getElementById("pwdMsg").style.display = "none";
                return true;
            }
        }
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

.register-container {
    position: absolute;
    top: 100px;
    left: 0;
    bottom: 0;
    right: 0;
    width: 100%;
    height: 80%;
    background-size: 100% 100%;
}

.register-box {
    background-color: white;
    position: absolute;
    left: 50%;
    top: 40%;
    transform: translate(-50%, -50%);
    width: 33%;
    min-width: 300px;
    border-radius: 15px;
    box-shadow: 0 0 10px gray;
}

.register-title {
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-top: 4%;
    margin-bottom: 4%;
}

.register-title img {
    width: 50%;
    min-height: 60%;
}

.register-form {
    width: 91.1%;
    flex: 1;
    padding: 0 4.4%;
}

.inputBox {
    height: 12.7%;
    margin-bottom: 3.3%;
}

.promptMsg {
    color: #ff463f;
    font-size: 14px;
    display: none;
}
</style>
