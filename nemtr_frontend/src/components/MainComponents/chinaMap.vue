<template>
    <div id="china_map_box">
        <div id="china_map"/>
    </div>
</template>

<script>
import echarts from 'echarts'
import 'echarts/map/js/china.js'
import {mapGetters} from 'vuex'
import {getCollegeMapMessageAPI} from '../../api/college'
import {Message} from "element-ui"

export default {
    data() {
        return {
            //echarts配置option
            options: {
                tooltip: {
                    triggerOn: "mousemove",   //mousemove、click
                    padding: 8,
                    borderWidth: 1,
                    borderColor: '#409eff',
                    backgroundColor: 'rgba(255,255,255,0.7)',
                    textStyle: {
                        color: '#000000',
                        fontSize: 13
                    },
                    formatter: function (params) {
                        let data = params.data
                        let map = data.map
                        let listItem = ''
                        for (let i = 0; i < map.length; i++) {
                            listItem = listItem +
                                '<span class="tooltip_left">' + map[i].batchName + ':&nbsp&nbsp</span>' +
                                '<span class="tooltip_right">' + map[i].pointAndLevel + '</span><br>'
                        }
                        let res = '<div>' +
                                  '<p><b style="font-size:15px;">' + data.collegeName + '</b>&nbsp&nbsp' + data.year +'</p>' +
                                  '<p class="tooltip_style"><span class="tooltip_left">招生人数:&nbsp&nbsp</span><span class="tooltip_right">' + data.value +'</span></p>' +
                                  '<p class="tooltip_style">' +
                                  listItem +
                                  '</p>' +
                                  '</div>'
                        return res
                    }
                },
                visualMap: {
                    show: true,
                    left: 26,
                    bottom: 40,
                    showLabel: true,
                    pieces: [
                        {
                            gte: 500,
                            label: ">= 500",
                            color: "rgb(102,177,255)"
                        },
                        {
                            gte: 100,
                            lt: 499,
                            label: "100 - 499",
                            color: "rgb(121,187,255)"
                        },
                        {
                            gte: 50,
                            lt: 99,
                            label: "50 - 99",
                            color: "rgb(160,207,255)"
                        },
                        {
                            gte: 10,
                            lt: 49,
                            label: "10 - 49",
                            color: "rgb(179,216,255)"
                        },
                        {
                            lt: 10,
                            label: '<10',
                            color: "rgb(217,236,255)"
                        }
                    ]
                },
                geo: {
                    map: "china",
                    scaleLimit: {
                        min: 1,
                        max: 2
                    },
                    zoom: 1,
                    top: 120,
                    label: {
                        normal: {
                            show: true,
                            fontSize: "14",
                            color: "rgba(0,0,0,0.7)"
                        }
                    },
                    itemStyle: {
                        normal: {
                            borderColor: "rgba(0, 0, 0, 0.2)"
                        },
                        emphasis: {
                            areaColor: "#f2d5ad",
                            shadowOffsetX: 0,
                            shadowOffsetY: 0,
                            borderWidth: 0
                        }
                    }
                },
                series: [
                    {
                        name: "突发事件",
                        type: "map",
                        geoIndex: 0,
                        data: []
                    }
                ]
            },

            //echarts data
            dataList: [
                // {
                //     name: "南海诸岛",
                //     value: 100,
                //     eventTotal: 100,
                //     specialImportant: 10,
                //     import: 10,
                //     compare: 10,
                //     common: 40,
                //     specail: 20
                // },
                {
                    name: "北京",
                    value: 540,
                },
                {
                    name: "天津",
                    value: 130,
                },
                {
                    name: "上海",
                    value: 400,
                },
                {
                    name: "重庆",
                    value: 750,
                },
                {
                    name: "河北",
                    value: 130,
                },
                {
                    name: "河南",
                    value: 830,
                },
                {
                    name: "云南",
                    value: 110,
                },
                {
                    name: "辽宁",
                    value: 19,
                },
                {
                    name: "黑龙江",
                    value: 150,
                },
                {
                    name: "湖南",
                    value: 690,
                },
                {
                    name: "安徽",
                    value: 60,
                },
                {
                    name: "山东",
                    value: 39,
                },
                {
                    name: "新疆",
                    value: 4,
                },
                {
                    name: "江苏",
                    value: 31,
                },
                {
                    name: "浙江",
                    value: 104,
                },
                {
                    name: "江西",
                    value: 36,
                },
                {
                    name: "湖北",
                    value: 52,
                },
                {
                    name: "广西",
                    value: 33,
                },
                {
                    name: "甘肃",
                    value: 7,
                },
                {
                    name: "山西",
                    value: 5,
                },
                {
                    name: "内蒙古",
                    value: 778,
                },
                {
                    name: "陕西",
                    value: 22,
                },
                {
                    name: "吉林",
                    value: 4,
                },
                {
                    name: "福建",
                    value: 18,
                },
                {
                    name: "贵州",
                    value: 5,
                },
                {
                    name: "广东",
                    value: 98,
                },
                {
                    name: "青海",
                    value: 1,
                },
                {
                    name: "西藏",
                    value: 0,
                },
                {
                    name: "四川",
                    value: 44,
                },
                {
                    name: "宁夏",
                    value: 4,
                },
                {
                    name: "海南",
                    value: 22,
                },
                // {
                //     name: "台湾",
                //     value: 3
                // },
                // {
                //     name: "香港",
                //     value: 5
                // },
                // {
                //     name: "澳门",
                //     value: 555
                // }
            ]
        };
    },
    computed: {
        ...mapGetters([
            'collegeId',
            'collegeName',
            'isSearch',
            'searchYear',
            'showFlag',
        ]),
        isChangeShowFlag() {
            return this.showFlag
        }
    },
    //监听collegeId的变化
    watch: {
        isChangeShowFlag: function () {
            this.showProvinceMap(this.collegeId, this.searchYear)
        }
    },
    methods: {
        //初始化中国地图
        initEchartsMap() {
            let mapDiv = document.getElementById('china_map');
            let myChart = echarts.init(mapDiv);
            myChart.setOption(this.options);
        },
        //修改echarts配置
        setEchartsOption() {
            this.options.series[0]['data'] = this.dataList;
        },

        async showProvinceMap(collegeId, year) {
            let res = await getCollegeMapMessageAPI(collegeId, year)
            let collegeMap = res.data.data.collegeMapMessage
            for (let i = 0; i < 31; i++) {
                this.dataList[i].name = collegeMap[i].provinceName
                this.dataList[i].value = collegeMap[i].studentNum
                this.dataList[i].map = collegeMap[i].map
                this.dataList[i].collegeName = res.data.data.collegeName
                this.dataList[i].year = year
            }
            this.setEchartsOption()
            this.initEchartsMap()
            Message({
                type: 'success',
                message: '查找成功'
            })
        }
    },
    mounted() {
        this.$nextTick(() => {
            this.initEchartsMap()
        })

    }
};
</script>

<style scoped>
#china_map_box {
    height: 100%;
    position: relative;
}

#china_map_box #china_map {
    height: 100%;
}

#china_map_box .china_map_logo {
    position: absolute;
    top: 0;
    left: 0;
    width: 45px;
}

#china_map .tooltip_style {
    line-height: 1.7;
    overflow: hidden;
}

#china_map .tooltip_left {
    float: left;
}

#china_map .tooltip_right {
    float: right;
}

</style>

