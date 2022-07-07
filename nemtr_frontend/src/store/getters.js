const getters = {
    // user
    isLogin: state => state.user.isLogin,
    name: state => state.user.name,
    userInfo: state => state.user.userInfo,
    modifyUserInfoParams: state => state.user.modifyUserInfoParams,
    resetPwdDialogVisible: state => state.user.resetPwdDialogVisible,
    topUpVipDialogVisible: state => state.user.topUpVipDialogVisible,

    // search
    collegeName: state => state.search.collegeName,
    collegeId: state => state.search.collegeId,
    searchYear: state => state.search.searchYear,
    provinceId: state => state.search.provinceId,
    isSearch: state => state.search.isSearch,
    cardData: state => state.search.cardData,
    showFlag: state => state.search.showFlag,
}

export default getters
