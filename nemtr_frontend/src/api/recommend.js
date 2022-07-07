import {axios} from "../utils/request";
const api = {
    recommendPre: '/api/recommend'
}

//高校优先
export function getRecommendCollegesAPI(formItem) {
    return axios({
        url: `${api.recommendPre}/getRecommendColleges/`,
        method: 'post',
        data: formItem,
    })
}

//专业优先
export function getRecommendMajorsAPI(formItem) {
    return axios({
        url: `${api.recommendPre}/getRecommendMajors/`,
        method: 'post',
        data: formItem,
    })
}

//位次匹配
export function getFuzzyLevelAPI(formItem) {
    return axios({
        url: `${api.recommendPre}/getFuzzyLevel/`,
        method: 'post',
        data: formItem,
    })
}
