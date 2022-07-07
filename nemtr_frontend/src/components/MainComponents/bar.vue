<template>
    <el-tabs v-model="rankType" @tab-click="handleClick">
        <el-tab-pane label="QS" name="QS" style="width: 25%"></el-tab-pane>
        <el-tab-pane label="U.S. News" name="US" style="width: 25%"></el-tab-pane>
        <el-tab-pane label="泰晤士（中国）" name="TIMESCHINA" style="width: 25%"></el-tab-pane>
        <el-tab-pane label="软科" name="RUANKE" style="width: 25%"></el-tab-pane>
        <el-table :data="tableData" style="width: 100%; height: 670px;">
            <el-table-column
                prop="rank"
                label="排名"
                width="60">
                <template slot-scope="scope">
                    <el-tag type='warning' disable-transitions>{{scope.row.rank}}</el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="name" label="学校名称" width="170">
                <template slot-scope="scope" style="text-align: center">
                    <img :src="scope.row.img" height="23px" width="25px" alt="">
                    <span>{{scope.row.name}}</span>
                </template>
            </el-table-column>
            <el-table-column prop="provinceName" label="省市" width="70"></el-table-column>
            <el-table-column prop="typeName" label="类型">
                <template slot-scope="scope">
                    <el-tag :type="scope.row.typeName === '综合类' ? 'primary' : 'success'" disable-transitions>
                        {{scope.row.typeName}}
                    </el-tag>
                </template>
            </el-table-column>
        </el-table>
    </el-tabs>

</template>

<script>
import {getUniversityRankAPI} from '../../api/college'

export default {
    data() {
        return {
            rankType: 'QS',
            tableData: [
                {
                    "name": "清华大学",
                    "rank": "17",
                    "schoolId": 140,
                    "cityName": "北京市",
                    "typeName": "综合类",
                    "provinceName": "北京",
                    "img": "https://static-data.gaokao.cn/upload/logo/140.jpg"
                },
                {
                    "name": "北京大学",
                    "rank": "18",
                    "schoolId": 31,
                    "cityName": "北京市",
                    "typeName": "综合类",
                    "provinceName": "北京",
                    "img": "https://static-data.gaokao.cn/upload/logo/31.jpg"
                },
                {
                    "name": "复旦大学",
                    "rank": "31",
                    "schoolId": 132,
                    "cityName": "上海市",
                    "typeName": "综合类",
                    "provinceName": "上海",
                    "img": "https://static-data.gaokao.cn/upload/logo/132.jpg"
                },
                {
                    "name": "浙江大学",
                    "rank": "45",
                    "schoolId": 114,
                    "cityName": "杭州市",
                    "typeName": "综合类",
                    "provinceName": "浙江",
                    "img": "https://static-data.gaokao.cn/upload/logo/114.jpg"
                },
                {
                    "name": "上海交通大学",
                    "rank": "50",
                    "schoolId": 125,
                    "cityName": "上海市",
                    "typeName": "综合类",
                    "provinceName": "上海",
                    "img": "https://static-data.gaokao.cn/upload/logo/125.jpg"
                },
                {
                    "name": "中国科学技术大学",
                    "rank": "98",
                    "schoolId": 66,
                    "cityName": "合肥市",
                    "typeName": "理工类",
                    "provinceName": "安徽",
                    "img": "https://static-data.gaokao.cn/upload/logo/66.jpg"
                },
                {
                    "name": "南京大学",
                    "rank": "131",
                    "schoolId": 111,
                    "cityName": "南京市",
                    "typeName": "综合类",
                    "provinceName": "江苏",
                    "img": "https://static-data.gaokao.cn/upload/logo/111.jpg"
                },
                {
                    "name": "同济大学",
                    "rank": "211",
                    "schoolId": 73,
                    "cityName": "上海市",
                    "typeName": "综合类",
                    "provinceName": "上海",
                    "img": "https://static-data.gaokao.cn/upload/logo/73.jpg"
                },
                {
                    "name": "武汉大学",
                    "rank": "225",
                    "schoolId": 42,
                    "cityName": "武汉市",
                    "typeName": "综合类",
                    "provinceName": "湖北",
                    "img": "https://static-data.gaokao.cn/upload/logo/42.jpg"
                },
                {
                    "name": "哈尔滨工业大学",
                    "rank": "236",
                    "schoolId": 34,
                    "cityName": "哈尔滨市",
                    "typeName": "理工类",
                    "provinceName": "黑龙江",
                    "img": "https://static-data.gaokao.cn/upload/logo/34.jpg"
                }
            ],
        };
    },

    methods: {
        handleClick() {
            getUniversityRankAPI(this.rankType).then(res => {
                this.tableData = res.data.data.rank
            })
        },
    }
}
</script>
<style scoped>

</style>

