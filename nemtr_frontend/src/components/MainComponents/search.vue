<template>
    <el-row :gutter="20">
        <el-col :span="4" class="year-selector">
            <el-select v-model="year" placeholder="请选择年份" style="width: 120px">
                <el-option
                    v-for="item in years"
                    :key="item"
                    :label="item"
                    :value="item"/>
            </el-select>
        </el-col>
        <el-col :span="8" class="center">
            <el-autocomplete
                v-model="search"
                :fetch-suggestions="querySearch"
                placeholder="你感兴趣的院校是..."
                :trigger-on-focus="false"
                value-key="name"
                style="width: 300px">
                <el-button type="primary" slot="append" icon="el-icon-search" id="search" @click="searchHandler">搜索</el-button>
            </el-autocomplete>
        </el-col>
    </el-row>
</template>

<script>
import RandomUtil from "../../utils/randomUtil";
import {mapGetters, mapMutations} from "vuex";
import Store from "../../utils/store";
import {searchAPI, getFuzzyNameAPI} from "../../api/college"
import {Message} from "element-ui"

export default {
    data() {
        return {
            search: "", //当前输入框的值
            isFocus: false, //是否聚焦
            hotSearchList: ["暂无热门搜索"], //热门搜索数据
            historySearchList: [], //历史搜索数据
            searchList: ["暂无数据"], //搜索返回数据,
            history: false,
            types: ["", "success", "info", "warning", "danger"], //搜索历史tag式样
            year: '',
            years: [2021, 2020, 2019, 2018, 2017],
        };
    },

    computed: {
        ...mapGetters([
            'collegeId',
            'collegeName',
            'searchYear',
            'showFlag'
        ]),
        isHistorySearch() {
            return this.isFocus && !this.search;
        },
        isSearchList() {
            return this.isFocus && this.search;
        },
        isSearch() {
            return this.isFocus;
        }
    },

    methods: {
        ...mapMutations([
            'set_collegeId',
            'set_collegeName',
            'set_searchYear',
            'set_showFlag',
        ]),

        querySearch(queryString, cb) {
            getFuzzyNameAPI(queryString).then(res => {
                let results = res.data.data.fuzzy
                // 调用 callback 返回建议列表的数据
                cb(results);
            })
        },

        focus() {
            this.isFocus = true;
            this.historySearchList = Store.loadSchoolHistory() == null ? [] : Store.loadSchoolHistory();
            this.history = this.historySearchList.length !== 0;
        },
        blur() {
            var self = this;
            this.searchBoxTimeout = setTimeout(function () {
                self.isFocus = false;
            }, 300);
        },
        enterSearchBoxHanlder() {
            clearTimeout(this.searchBoxTimeout);
        },
        searchHandler() {
            this.startSearch()
            //随机生成搜索历史tag式样
            let n = RandomUtil.getRandomNumber(0, 5);
            let exist =
                this.historySearchList.filter(value => {
                    return value.name === this.search;
                }).length !== 0;
            if (!exist) {
                this.historySearchList.push({name: this.search, type: this.types[n]});
                Store.saveSchoolHistory(this.historySearchList);
            }
            this.history = this.historySearchList.length !== 0;
        },
        startSearch() {
            if (this.year === '' && this.search !== '') {
                Message({
                    type: 'error',
                    message: '年份不能为空'
                })
            } else if (this.year !== '' && this.search === '') {
                Message({
                    type: 'error',
                    message: '学校不能为空'
                })
            } else if (this.year === '' && this.search === '') {
                Message({
                    type: 'error',
                    message: '年份和学校不能为空'
                })
            } else {
                this.set_searchYear(this.year)
                this.set_collegeName(this.search)
                searchAPI(this.search).then(res => {
                    if (res.data.code === 200) {
                        this.set_collegeId(res.data.data.schoolId)
                        this.set_showFlag(this.showFlag + 1)
                    } else {
                        Message({
                            type: 'error',
                            message: '未找到该学校'
                        })
                    }
                })
            }
        },
        closeHandler(search) {
            this.historySearchList.splice(this.historySearchList.indexOf(search), 1);
            Store.saveSchoolHistory(this.historySearchList);
            clearTimeout(this.searchBoxTimeout);
            if (this.historySearchList.length === 0) {
                this.history = false;
            }
        },
        removeAllHistory() {
            Store.removeAllSchoolHistory();
        },

    },
};
</script>

<style scoped>
.center {
    margin-top: 15px;
}

.year-selector {
    margin-top: 15px;
    margin-left: 60px;
}

#search {
    color: white;
    background-color: #409EFF;
    border-radius: 0;
}

.search-title-history {
    color: #bdbaba;
    font-size: 15px;
    margin-top: -30px;
    margin-left: -300px;
}

.search-title-modern {
    color: #bdbaba;
    font-size: 15px;
    margin-top: -10px;
    margin-left: -300px;
}


.remove-history {
    color: #bdbaba;
    font-size: 15px;
    float: right;
    margin-top: -22px;
}

#search-box {
    width: 370px;
    height: 130px;
    margin-top: 0;
    padding-bottom: 20px;
}
</style>
