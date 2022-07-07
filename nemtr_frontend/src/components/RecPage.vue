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
                <el-link type="primary" style="position: absolute;top: 0px;left: 290px;">返回首页</el-link>
            </router-link>
        </el-row>

        <div style="position:absolute;height: 100px;width: 1200px;left: 190px;top: 150px">
            <el-card class="box-card" style="height: 100px;width: 1200px">
                <el-form :inline="true" class="demo-form-inline" style="margin-top: 10px">
                    <el-form-item label="高考分数">
                        <el-input v-model="score" placeholder="总分" clearable style="width: 100px"/>
                    </el-form-item>
                    <el-form-item label="所学科类">
                        <el-select v-model="entranceType" placeholder="请选择" style="width: 160px">
                            <el-option
                                v-for="item in optionsSub"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value"/>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="生源地">
                        <el-select v-model="provinceId" placeholder="请选择" style="width: 160px">
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
                    </el-form-item>
                    <el-form-item label="省内排名">
                        <el-input v-model="level" clearable style="width: 100px"/>
                    </el-form-item>
                    <el-form-item>
                        <el-radio v-model="recType" label="1">学校优先</el-radio>
                        <el-radio v-model="recType" label="2">专业优先</el-radio>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="onSubmit">推荐</el-button>
                    </el-form-item>
                </el-form>
            </el-card>
        </div>

        <div style="position:absolute;height: 500px;width: 1200px;left: 190px;top: 270px;">
            <el-card class="box-card" style="height: 500px;width: 1200px;">
                <div style="width: 1200px;height: 100px">
                    <el-form :inline="true" :model="recommendInput" class="demo-form-inline" style="position:absolute;left: 120px;top: 50px;">
                        <el-form-item label="校址 ">
                        <el-select
                            v-model="searchProvinceId"
                            collapse-tags
                            style="width: 150px"
                            placeholder="筛选学校所在地">
                            <el-option
                                v-for="item in provinceList"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value"/>
                        </el-select>
                        </el-form-item>

                        <el-form-item v-if="showType === 2" label="专业 ">
                            <el-select
                                v-model="searchMajorCode"
                                collapse-tags
                                style="width: 150px"
                                placeholder="筛选专业方向">
                                <el-option
                                    v-for="item in majorList"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value"/>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="院校特色">
                            <el-checkbox-group v-model="checkList" style="margin-left: 10px">
                                <el-checkbox label="985"/>
                                <el-checkbox label="211"/>
                                <el-checkbox label="教育部直属"/>
                            </el-checkbox-group>
                        </el-form-item>

                        <el-button type="primary" @click="secondSearch">筛选</el-button>
                        <el-button type="primary" @click="reset">复原</el-button>

                    </el-form>
                </div>

                <div v-if="collegeList.length" class="collegeCard">
                    <div v-for="(item, i) in currentData" :key="i">
                        <div class="college">
                            <img :src="item.img" height="100%" width="100%" alt="">
                            <i class="el-icon-star-off" style="font-size: 8px;font-weight: bold">{{item.name}}</i>
                            <el-button style="z-index: 15;font-size: 8px;margin-left: 10px;margin-top: 3px" type="text" @click="moreDetails(item)">详情></el-button>
                            <div style="font-size: 8px;font-weight: bold">成功概率: {{item.recommendType}}</div>
                            <div v-if="item.recType === 2" style="font-size: 8px;font-weight: bold;margin-top: 10px">首选专业: {{item.firstMajor}}</div>
                        </div>
                    </div>
                </div>

                <div v-else>暂无数据</div>

                <el-pagination
                    small
                    layout="total, prev, pager, next"
                    :total="collegeList.length"
                    @current-change="handleCurrentChange"
                    :current-page="currentPage"
                    :page-size="pageSize"
                    style="margin-top: 120px"/>
            </el-card>
        </div>

        <el-dialog title="详情" :visible.sync="outerDialogVisible" width="30%" :before-close="handleClose">
            <div class="dialog-element">地址: {{details.address}}</div>
            <br>
            <div class="dialog-element">建校年份: {{details.createDate}}</div>
            <br>
            <div class="dialog-element">高校层次:
                <el-tag type="success" v-if="details.natureName">{{details.natureName}}</el-tag>
                <el-tag type="info" v-if="details.levelName">{{details.levelName}}</el-tag>
                <el-tag type="warning" v-if="details.dualClassName">双一流</el-tag>
                <el-tag type="danger" v-if="details.is985 === 1">985</el-tag>
                <el-tag v-if="details.is211 === 1">211</el-tag>
            </div>
            <br>
            <div class="dialog-element">学校类型: {{details.typeName}}</div>
            <br>
            <div class="dialog-element">联系电话: {{details.phone}}</div>
            <br>
            <div class="dialog-element">联系邮箱: {{details.email}}</div>
            <br>
            <div v-if="details.recType === 2" class="dialog-element">
                <div style="display: flex; flex-direction: row">
                    <span>首选专业评分:&nbsp</span>
                    <el-rate v-model="details.rate == null ? 0 : details.rate" disabled show-score text-color="#ff9900" score-template="{value}"/>
                    &nbsp&nbsp&nbsp<span>评分人数:&nbsp{{details.userNum}}人</span>
                </div>
            </div>
            <div v-if="details.recType === 2"  class="dialog-element">
                <el-button type="text" @click="beforeUserRate">我也要评分</el-button>
                <el-dialog title="首选专业评分" :visible.sync="innerDialogVisible" width="20%" append-to-body>
                    <div class="dialog-element">
                        <div style="display: flex; flex-direction: row">
                            专业名称: {{details.firstMajor}}
                        </div>
                        <br>
                        <el-rate v-model="userRate"></el-rate>
                    </div>
                    <span slot="footer" class="dialog-footer">
                        <el-button type="primary" @click="submitUserRate(details.provinceId, details.schoolId, details.name, details.majorId, details.firstMajor)">提交</el-button>
                    </span>
                </el-dialog>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="outerDialogVisible = false">关闭</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import {mapGetters} from "vuex";
import avatar from "../assets/avatar.jpg";
import {getRecommendCollegesAPI, getRecommendMajorsAPI, getFuzzyLevelAPI} from "../api/recommend";
import {rateMajorAPI} from "../api/users";
import {Message} from "element-ui";

export default {
    name: "RecPage",

    data() {
        return {
            score: '',
            level: '',
            entranceType: '',
            provinceId: '',

            //推荐条件
            recommendInput: {
                score: '',
                level: '',
                entranceType: '',
                provinceId: '',
            },
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
            circleUrl: avatar,
            collegeList: {},      //用于展示的推荐学校名单
            storeCollegeList: {}, //用于存储原始的推荐名单

            //分页
            currentPage: 1,       //当前页码
            pageSize: 4,          //每页显示的学校总数
            currentData: {},      //当前页显示的学校数据
            outerDialogVisible: false, //是否显示详情
            details: {},          //学校详情

            //二级搜索
            searchProvinceId: '',
            searchMajorCode: '',
            provinceIdArray: [],
            provinceList: [],
            majorList: [
                {
                    value: "01",
                    label: "哲学"
                },
                {
                    value: "02",
                    label: "经济学"
                },
                {
                    value: "03",
                    label: "法学"
                },
                {
                    value: "04",
                    label: "教育学"
                },
                {
                    value: "05",
                    label: "文学"
                },
                {
                    value: "06",
                    label: "历史学"
                },
                {
                    value: "07",
                    label: "理学"
                },
                {
                    value: "08",
                    label: "工学"
                },
                {
                    value: "09",
                    label: "农学"
                },
                {
                    value: "10",
                    label: "医学"
                },
                {
                    value: "11",
                    label: "军事学"
                },
                {
                    value: "12",
                    label: "管理学"
                },
                {
                    value: "13",
                    label: "艺术学"
                },
            ],     //专业大类
            collegeList_province: [],
            collegeList_major: [],

            //多选框
            checkList: [],

            //推荐模式
            recType: '1',
            //显示模式：学校优先情况下，二级搜索不显示专业筛选
            showType: 1,

            //专业评分
            rateValue: 3.7,
            //用户打分
            userRate: 0,
            //用户打分对话框
            innerDialogVisible: false,
            //用户打分包装对象
            rateData: {
                userId: '',
                provinceId: '',
                schoolId: '',
                schoolName: '',
                majorId: '',
                majorName: '',
                rate: 0
            }
        }
    },

    computed: {
        ...mapGetters([
            'isLogin',
            'userInfo'
        ]),

        recInput() {
            const {score, entranceType, provinceId} = this
            return {score, entranceType, provinceId}
        }
    },

    watch: {
        recInput(val) {
            if (val.score !== '' && val.entranceType !== '' && val.provinceId !== '') {
                this.recommendInput.score = this.score
                this.recommendInput.provinceId = this.provinceId
                this.recommendInput.entranceType = this.entranceType
                getFuzzyLevelAPI(this.recommendInput).then(res => {
                    this.level = res.data.data.level
                    this.recommendInput.level = this.level
                })
            }
        }
    },

    methods: {
        onSubmit() {
            if (this.recType === '1') {   //学校优先
                getRecommendCollegesAPI(this.recommendInput).then(res => {
                    this.collegeList = res.data.data.collegeList
                    this.storeCollegeList = this.collegeList
                    this.collegeList_major = this.collegeList
                    this.collegeList_province = this.collegeList
                    this.initCurrentData(this.collegeList)
                    //处理一下筛选
                    this.handleSecondSearch()
                    Message({
                        type: 'success',
                        message: '查找成功'
                    })
                    this.showType = 1
                })
            } else {
                getRecommendMajorsAPI(this.recommendInput).then(res => {
                    this.collegeList = res.data.data.collegeList
                    this.storeCollegeList = this.collegeList
                    this.collegeList_major = this.collegeList
                    this.collegeList_province = this.collegeList
                    this.initCurrentData(this.collegeList)
                    //处理一下筛选
                    this.handleSecondSearch()
                    Message({
                        type: 'success',
                        message: '查找成功'
                    })
                    this.showType = 2
                })
            }
        },

        handleSecondSearch() {
            for (let i = 0; i < this.collegeList.length; i++) {
                let provinceId = this.collegeList[i].provinceId
                if (!this.provinceIdArray.includes(provinceId)) {
                    this.provinceIdArray.push(provinceId)
                    let obj = {}
                    obj["value"] = provinceId
                    obj["label"] = this.collegeList[i].provinceName
                    this.provinceList.push(obj)
                }
            }
        },

        secondSearch() {
            this.collegeList = this.storeCollegeList
            let tmp = this.collegeList
            let res = []
            //筛选省份
            if (this.searchProvinceId !== '') {
                for (let i = 0; i < tmp.length; i++) {
                    if (tmp[i].provinceId === this.searchProvinceId) {
                        res.push(tmp[i]);
                    }
                }
                tmp = res
            }
            //筛选大类
            if (this.searchMajorCode !== '') {
                res = []
                for (let i = 0; i < tmp.length; i++) {
                    if (tmp[i].firstMajorCode === this.searchMajorCode) {
                        res.push(tmp[i]);
                    }
                }
                tmp = res
            }
            //筛选院校特色
            if (this.checkList.length !== 0) {
                let is985 = 0
                let is211 = 0
                let belong = 0
                if (this.checkList.includes('985')) {
                    is985 = 1
                }
                if (this.checkList.includes('211')) {
                    is211 = 1
                }
                if (this.checkList.includes('教育部直属')) {
                    belong = 1
                }
                res = []
                if (is985 === 1) {
                    for (let i = 0; i < tmp.length; i++) {
                        if (tmp[i].is985 === 1) {
                            res.push(tmp[i]);
                        }
                    }
                    tmp = res
                }
                if (is211 === 1) {
                    res = []
                    for (let i = 0; i < tmp.length; i++) {
                        if (tmp[i].is211 === 1) {
                            res.push(tmp[i]);
                        }
                    }
                    tmp = res
                }
                if (belong === 1) {
                    res = []
                    for (let i = 0; i < tmp.length; i++) {
                        if (tmp[i].belong === '教育部') {
                            res.push(tmp[i]);
                        }
                    }
                    tmp = res
                }
            }
            this.collegeList = tmp
            this.initCurrentData(this.collegeList)
        },

        reset() {
            this.searchProvinceId = ''
            this.searchMajorCode = ''
            this.checkList = []
            this.collegeList = this.storeCollegeList
            this.initCurrentData(this.collegeList)
        },

        //==========分页相关=====================
        //初始化
        initCurrentData(collegeList) {
            //currentData初始化
            this.currentData = {}
            for (let i = 0; i < this.pageSize && i < collegeList.length; i++) {
                this.currentData[i] = collegeList[i]
            }
            //复原页码
            this.currentPage = 1
        },

        //当前页发生改变时触发
        handleCurrentChange(val) {
            this.currentPage = val
            //从collegeList中提取出当前页的数据
            this.currentData = {}
            let index = 0
            for (let i = (this.currentPage - 1) * this.pageSize; i < this.currentPage * this.pageSize && i < this.collegeList.length; i++) {
                this.currentData[index] = this.collegeList[i]
                index++
            }
        },

        moreDetails(item) {
            this.outerDialogVisible = true
            this.details = item
        },

        handleClose(done) {
            done()
        },

        //====================评分相关================
        //判断用户是否登录
        beforeUserRate() {
            if (!this.isLogin) {
                Message({
                    type: 'warning',
                    message: '请先登录'
                })
            } else {
                this.innerDialogVisible = true
            }
        },

        //提交用户评分
        submitUserRate(provinceId, schoolId, schoolName, majorId, majorName) {
            this.innerDialogVisible = false
            this.rateData.userId = this.userInfo.id
            this.rateData.provinceId = provinceId
            this.rateData.schoolId = schoolId
            this.rateData.schoolName = schoolName
            this.rateData.majorId = majorId
            this.rateData.majorName = majorName
            this.rateData.rate = this.userRate
            rateMajorAPI(this.rateData).then(res => {
                Message({
                    type: 'success',
                    message: '提交成功'
                })
                this.userRate = 0
            })
        }
    }
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

.collegeCard {
    height: 184px;
    display: flex;
    align-items: center;
    flex-direction: row;
    margin-left: 20px;
    margin-top: 30px;
}

.college {
    border: 1px solid #EBEEF5;
    border-radius: 4px;
    width: 180px;
    height: 184px;
    top: 100px;
    margin-left: 80px;
    box-shadow: 0 2px 12px 0 rgba(0,0,0,0.1);
}

.dialog-element {
    text-align: left;
}

</style>
