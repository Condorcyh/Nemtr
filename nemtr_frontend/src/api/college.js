import {axios} from "../utils/request";

const api = {
    collegePre: '/api/basic'
}

//获得高校描述
export function getDescriptionAPI(schoolId) {
    return axios({
        url: `${api.collegePre}/getDescription/${schoolId}`,
        method: 'get',
    })
}

//获得高校排名
export function getUniversityRankAPI(type) {
    return axios({
        url: `${api.collegePre}/getUniversityRank/${type}`,
        method: 'get',
    })
}

//获得某省大学统计
export function getUniversityCountAPI(provinceId) {
    return axios({
        url: `${api.collegePre}/getUniversityCount/${provinceId}`,
        method: 'get',
    })
}

//获得高校性别比
export function getUniversitySexRateAPI(schoolId) {
    return axios({
        url: `${api.collegePre}/getUniversitySexRate/${schoolId}`,
        method: 'get',
    })
}

//获得高校卡片
export function getCollegeCardAPI(schoolId) {
    return axios({
        url: `${api.collegePre}/getCollegeCard/${schoolId}`,
        method: 'get',
    })
}

//获得搜索地图
export function getCollegeMapMessageAPI(schoolId, year) {
    return axios({
        url: `${api.collegePre}/getCollegeMapMessage/${schoolId}/${year}`,
        method: 'get',
    })
}

//搜索学校id
export function searchAPI(schoolName) {
    return axios({
        url: `${api.collegePre}/search/${schoolName}`,
        method: 'get',
    })
}

//学校名称模糊搜索
export function getFuzzyNameAPI(schoolName) {
    return axios({
        url: `${api.collegePre}/getFuzzyName/${schoolName}`,
        method: 'get',
    })
}
