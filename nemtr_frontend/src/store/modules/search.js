const search = {
    state: {
        collegeId: '111',
        collegeName: '南京大学',
        provinceId: '',
        searchYear: 2021,
        isSearch: false,
        cardData: {},
        showFlag: 0, //计数器，改变的话就表明可以开始显示地图
    },
    mutations: {
        set_collegeId: function (state, data) {
            state.collegeId = data
        },
        set_collegeName: function (state, data) {
            state.collegeName = data
        },
        set_provinceId: function (state, data) {
            state.provinceId = data
        },
        set_searchYear: function (state, data) {
            state.searchYear = data
        },
        set_isSearch: function (state, data) {
            state.isSearch = data
        },
        set_cardData: function (state, data) {
            state.cardData = data
        },
        set_showFlag: function (state, data) {
            state.showFlag = data
        },
    },
    actions: {}
}

export default search
