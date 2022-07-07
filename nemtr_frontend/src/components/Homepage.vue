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
                <router-link to="/login">
                    <el-button class="signButton" type="primary" round style="position: absolute;top: -10px;left: 100px;z-index: 15">登录</el-button>
                </router-link>
                <router-link to="/register">
                    <el-button type="primary" plain round style="position: absolute;top: -10px;left: 190px;z-index: 15">注册</el-button>
                </router-link>
            </div>

            <el-avatar v-else :size="50" :src=circleUrl style="position: absolute;top: -15px;left: 220px"/>
            <router-link to="/main">
                <el-link type="primary" style="position: absolute;top: 0;left: 290px;">返回首页</el-link>
            </router-link>
        </el-row>

        <div style="position:absolute;height: 200px;width: 1200px;left: 190px;top: 150px">
            <el-card class="box-card" style="height: 200px;width: 1200px">
                <el-avatar :size="50" :src=circleUrl style="position: absolute"></el-avatar>
                <el-descriptions title="用户信息" style="margin-left: 90px;margin-top: 25px">
                    <el-descriptions-item label="用户名">{{userInfo.name}}</el-descriptions-item>
                    <el-descriptions-item label="手机号">{{userInfo.phone}}</el-descriptions-item>
                    <el-descriptions-item label="生源地">{{userInfo.provinceName}}</el-descriptions-item>
                    <el-descriptions-item label="就读高中">{{userInfo.highSchool}}</el-descriptions-item>
                    <el-descriptions-item label="所学科类">{{userInfo.entranceType}}</el-descriptions-item>
                </el-descriptions>
            </el-card>
        </div>

        <div style="position:absolute;height: 250px;width: 1200px;left: 190px;top: 400px">
            <el-card class="box-card" style="height: 150px;width: 1200px">
                <el-descriptions class="margin-top" title="修改个人信息" :column="3" border>
                    <template slot="extra">
                        <el-button type="primary" size="small" @click="updateUserInfo">保存</el-button>
                    </template>

                    <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-location-outline"/>生源地
                        </template>
                        <el-select v-model="user.provinceId" placeholder="请选择">
                            <el-option-group
                                v-for="group in options"
                                :key="group.label"
                                :label="group.label">
                                <el-option
                                    v-for="item in group.options"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value"/>
                            </el-option-group>
                        </el-select>
                    </el-descriptions-item>

                    <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-office-building"/>就读高中
                        </template>
                        <el-input v-model="user.highSchool" placeholder="就读高中名称" style="width: 150px" clearable/>
                    </el-descriptions-item>

                    <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-tickets"/>所学科类
                        </template>
                        <el-select v-model="user.entranceType" placeholder="请选择">
                            <el-option
                                v-for="item in optionsSub"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value"/>
                        </el-select>
                    </el-descriptions-item>
                </el-descriptions>
            </el-card>
        </div>

        <div style="position:absolute;height: 300px;width: 1200px;left: 190px;top: 570px">
            <el-card class="box-card" style="height: 300px;width: 1200px">
                <div style="float: left; font-weight: bold">我评分过的专业</div>
                <br>
                <br>
                <el-table :data="userInfo.userRateData" stripe style="width: 100%;">
                    <el-table-column prop="school" label="学校" width="180"/>
                    <el-table-column prop="province" label="省市" width="80"/>
                    <el-table-column prop="major" label="专业" width="360"/>
                    <el-table-column prop="rate" label="我的评分">
                        <template slot-scope="scope">
                            <el-rate v-model="scope.row.userRate" disabled show-score text-color="#ff9900" score-template="{value}"/>
                        </template>
                    </el-table-column>
                    <el-table-column prop="rate" label="平均得分">
                        <template slot-scope="scope">
                            <el-rate v-model="scope.row.averageRate" disabled show-score text-color="#ff9900" score-template="{value}"/>
                        </template>
                    </el-table-column>
                </el-table>
            </el-card>
        </div>
    </div>
</template>

<script>
import {getAllRateAPI, updateAPI} from "../api/users";
import {mapActions, mapGetters, mapMutations} from "vuex";
import avatar from "../assets/avatar.jpg"

export default {
    name: "Homepage",
    data() {
        return {
            circleUrl: avatar,
            options: [
                {
                    label: '自主命题',
                    options: [
                        {
                            value: 31,
                            label: '上海'
                        },
                        {
                            value: 11,
                            label: '北京'
                        },
                        {
                            value: 33,
                            label: '浙江'
                        },
                        {
                            value: 12,
                            label: '天津'
                        }]
                },
                {
                    label: '新高考II卷',
                    options: [
                        {
                            value: 46,
                            label: '海南'
                        },
                        {
                            value: 21,
                            label: '辽宁'
                        },
                        {
                            value: 50,
                            label: '重庆'
                        }
                    ]
                },
                {
                    label: '新高考I卷',
                    options: [
                        {
                            value: 37,
                            label: '山东'
                        },
                        {
                            value: 13,
                            label: '河北'
                        },
                        {
                            value: 32,
                            label: '江苏'
                        },
                        {
                            value: 42,
                            label: '湖北'
                        },
                        {
                            value: 43,
                            label: '湖南'
                        },
                        {
                            value: 44,
                            label: '广东'
                        },
                        {
                            value: 35,
                            label: '福建'
                        }
                    ]
                },
                {
                    label: '全国乙卷',
                    options: [
                        {
                            value: 62,
                            label: '甘肃'
                        }, {
                            value: 63,
                            label: '青海'
                        }, {
                            value: 14,
                            label: '山西'
                        },
                        {
                            value: 15,
                            label: '内蒙古'
                        },
                        {
                            value: 23,
                            label: '黑龙江'
                        },
                        {
                            value: 22,
                            label: '吉林'
                        },
                        {
                            value: 34,
                            label: '安徽'
                        },
                        {
                            value: 64,
                            label: '宁夏'
                        },
                        {
                            value: 65,
                            label: '新疆'
                        },
                        {
                            value: 61,
                            label: '陕西'
                        },
                        {
                            value: 41,
                            label: '河南'
                        },
                        {
                            value: 36,
                            label: '江西'
                        },
                    ]
                },
                {
                    label: '全国甲卷',
                    options: [
                        {
                            value: 53,
                            label: '云南'
                        },
                        {
                            value: 45,
                            label: '广西'
                        },
                        {
                            value: 52,
                            label: '贵州'
                        },
                        {
                            value: 51,
                            label: '四川'
                        },
                        {
                            value: 54,
                            label: '西藏'
                        }
                    ]
                },
            ],
            optionsSub: [
                {
                    value: 'LI',
                    label: '理科(物理类)'
                },
                {
                    value: 'WEN',
                    label: '文科(历史类)'
                },
                {
                    value: 'ZONGHE',
                    label: '综合'
                }
            ],
            user: {
                provinceId: '',  //生源地
                entranceType: '',
                highSchool: '',
            },

            //评分表格
            tableData: []
        }
    },
    computed: {
        ...mapGetters([
            'collegeId',
            'provinceId',
            'isSearch',
            'userInfo',
            'isLogin',
        ]),
    },

    mounted() {
        this.getAllRate()
    },

    methods: {
        ...mapMutations([
            'set_userInfo'
        ]),

        updateUserInfo() {
            this.user.name = this.userInfo.name
            this.user.phone = this.userInfo.phone
            this.user.id = this.userInfo.id
            this.user.pwd = this.userInfo.pwd
            updateAPI(this.user).then(res => {
                this.set_userInfo(res.data.data.userInfo)
                this.getAllRate()
            })
        },

        //获得该用户的所有评分
        getAllRate() {
            if (!this.userInfo) {
                getAllRateAPI(this.userInfo.id).then(res => {
                    this.tableData = res.data.data.rates
                })
            }
        },
    },
}
</script>

<style scoped>
.topLink {
    margin-left: 50px;
    margin-top: -80px;
}

a {
    text-decoration: none;
}
</style>
