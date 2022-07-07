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
            <div v-if="!isLogin">
                <el-button style="position: absolute;top: -10px;left: 100px;z-index: 15" type="primary" round @click="updateLink('/login')">登录</el-button>
                <el-button style="position: absolute;top: -10px;left: 180px;z-index: 15" type="primary" plain round @click="updateLink('/register')">注册</el-button>
            </div>

            <el-avatar v-else :size="50" :src=circleUrl style="position: absolute;top: -15px;left: 220px"/>

            <el-dropdown @command="handleCommand">
                <span class="el-dropdown-link">个人中心<i class="el-icon-arrow-down el-icon--right"/></span>
                <el-dropdown-menu slot="dropdown">
                    <router-link to="/homepage">
                        <el-dropdown-item command="userInfo">基本信息</el-dropdown-item>
                    </router-link>

                    <router-link to="/recpage">
                        <el-dropdown-item command="recommend">志愿推荐</el-dropdown-item>
                    </router-link>

                    <el-dropdown-item divided command="logout">登出系统</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </el-row>

        <div class="box-card">
            <el-card class="card">
                <div slot="header" class="clearfix">
                    <span>重点实验室和杰出学者</span>
                </div>
                <div v-if="!cardData.lab" style="margin-top: -10px">暂无地区信息</div>
                <div v-else style="margin-top: -10px">
                    重点实验室: {{cardData.lab}} 个<br>
                    杰出学者: {{cardData.academician}} 个
                </div>
            </el-card>

            <el-card class="card">
                <div slot="header" class="clearfix">
                    <span>开设学位点</span>
                </div>
                <div v-if="!cardData.lab" style="margin-top: -10px">暂无地区信息</div>
                <div v-else style="margin-top: -10px">
                    学士点: {{cardData.subject}} 个<br>
                    硕士点: {{cardData.master}} 个<br>
                    博士点: {{cardData.doctor}} 个
                </div>
            </el-card>

            <el-card class="card">
                <div slot="header" class="clearfix">
                    <span>国家重点学科</span>
                </div>
                <div v-if="!cardData.lab" style="margin-top: -10px">暂无地区信息</div>
                <div v-else style="margin-top: -10px">
                    重点学科: {{cardData.nation}} 个<br>
                    A类学科: {{cardData.a}} 个
                </div>
            </el-card>
        </div>

        <div class="search-box">
            <Search/>
        </div>

        <div style="position: absolute;top: 200px;width: 800px;left:400px;height: 600px;z-index: 1">
            <ChinaMap/>
        </div>

        <div class="rank-box">
            <i class="el-icon-trophy" style="margin-top: 10px"> 世界大学排名</i>
            <div style="margin-left: 25px; margin-top: 10px">
                <Bar/>
            </div>
        </div>

        <router-view/>
    </div>
</template>

<script>
import Bar from "./MainComponents/bar";
import ChinaMap from "./MainComponents/chinaMap";
import Search from "./MainComponents/search";
import {mapActions, mapGetters, mapMutations} from "vuex";
import {getCollegeCardAPI} from "../api/college"
import avatar from "../assets/avatar.jpg"

export default {
    components: {Search, ChinaMap, Bar},
    name: "Main",
    data() {
        return {
            circleUrl: avatar,
            cardData: {},
        }
    },

    computed: {
        ...mapGetters([
            'collegeId',
            'collegeName',
            'provinceId',
            'isSearch',
            'userInfo',
            'isLogin',
        ]),
        isChangeCollegeId() {
            return this.collegeId
        }
    },

    //监听collegeId的变化
    watch: {
        isChangeCollegeId: function () {
            getCollegeCardAPI(this.collegeId).then(res => {
                this.cardData = res.data.data.collegeCard
            })
        }
    },

    methods: {
        ...mapActions([
            'logout'
        ]),

        showCard() {
            getCollegeCardAPI(this.collegeId).then(res => {
                this.cardData = res.data.data.collegeCard
            })
        },

        updateLink(link) {
            // 返回到上一级
            // this.$router.go(-1)
            // 可以指定跳转到某一个页面
            // this.$router.push('/movie')

            // replace替换当前页面
            this.$router.replace(link)
        },
        handleCommand(command){
            if (command === 'logout') {
                this.logout()
            }
        }
    }
};
</script>

<style scoped>
.topLink {
    margin-left: 50px;
    margin-top: -80px;
}


.el-dropdown-link {
    margin-left: 250px;
    cursor: pointer;
    color: #409EFF;
}

.el-icon-arrow-down {
    font-size: 12px;
}

.clearfix:before,
.clearfix:after {
    display: table;
    content: "";
}

.clearfix:after {
    clear: both
}

.box-card {
    display: flex;
    flex-direction: column;
    margin-left: 50px;
    margin-top: 30px;
}

.card {
    width: 250px;
    height: 150px;
    margin-top: 30px;
}

.search-box {
    height: 60px;
    margin-left: 400px;
    margin-top: -500px;
}

a {
    text-decoration: none;
}

.rank-box {
    border: 1px solid #EBEEF5;
    border-radius: 4px;
    overflow: hidden;
    position: absolute;
    width: 400px;
    height: 730px;
    left: 1160px;
    top: 140px;
    box-shadow: 0 1px 12px 0 rgba(0,0,0,0.1);
}
</style>
