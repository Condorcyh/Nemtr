package com.graduation.nemtr.po;

import com.graduation.nemtr.po.inner.CollegePlanInner;
import com.graduation.nemtr.po.inner.CollegeScoreSimpleInner;
import com.graduation.nemtr.po.temp.MapTemp;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Data
@Document(collection = "recommend_temp")
public class CollegeScoreSimple {
    @Indexed
    @Field("_id")
    String id;

    @Field("name")
    String name;

    @Field("school_id")
    Long schoolId;

    @Field("北京")
    CollegeScoreSimpleInner beijing;

    @Field("天津")
    CollegeScoreSimpleInner tianjin;

    @Field("河北")
    CollegeScoreSimpleInner hebei;

    @Field("山西")
    CollegeScoreSimpleInner shanxi;

    @Field("内蒙古")
    CollegeScoreSimpleInner neimenggu;

    @Field("辽宁")
    CollegeScoreSimpleInner liaoning;

    @Field("吉林")
    CollegeScoreSimpleInner jilin;

    @Field("黑龙江")
    CollegeScoreSimpleInner heilongjiang;

    @Field("上海")
    CollegeScoreSimpleInner shanghai;

    @Field("江苏")
    CollegeScoreSimpleInner jiangsu;

    @Field("浙江")
    CollegeScoreSimpleInner zhejiang;

    @Field("安徽")
    CollegeScoreSimpleInner anhui;

    @Field("福建")
    CollegeScoreSimpleInner fujian;

    @Field("江西")
    CollegeScoreSimpleInner jiangxi;

    @Field("山东")
    CollegeScoreSimpleInner shandong;

    @Field("河南")
    CollegeScoreSimpleInner henan;

    @Field("湖北")
    CollegeScoreSimpleInner hubei;

    @Field("湖南")
    CollegeScoreSimpleInner hunan;

    @Field("广东")
    CollegeScoreSimpleInner guangdong;

    @Field("广西")
    CollegeScoreSimpleInner guangxi;

    @Field("海南")
    CollegeScoreSimpleInner hainan;

    @Field("重庆")
    CollegeScoreSimpleInner chongqing;

    @Field("四川")
    CollegeScoreSimpleInner sichuan;

    @Field("贵州")
    CollegeScoreSimpleInner guizhou;

    @Field("云南")
    CollegeScoreSimpleInner yunnan;

    @Field("西藏")
    CollegeScoreSimpleInner tibet;

    @Field("陕西")
    CollegeScoreSimpleInner shaanxi;

    @Field("甘肃")
    CollegeScoreSimpleInner gansu;

    @Field("青海")
    CollegeScoreSimpleInner qinghai;

    @Field("宁夏")
    CollegeScoreSimpleInner ningxia;

    @Field("新疆")
    CollegeScoreSimpleInner xinjiang;

    public CollegeScoreSimpleInner getByProvince(Integer provinceId){
        switch (provinceId){
            case 11: return beijing;
            case 12: return tianjin;
            case 13: return hebei;
            case 14: return shanxi;
            case 15: return neimenggu;
            case 21: return liaoning;
            case 22: return jilin;
            case 23: return heilongjiang;
            case 31: return shanghai;
            case 32: return jiangsu;
            case 33: return zhejiang;
            case 34: return anhui;
            case 35: return fujian;
            case 36: return jiangxi;
            case 37: return shandong;
            case 41: return henan;
            case 42: return hubei;
            case 43: return hunan;
            case 44: return guangdong;
            case 45: return guangxi;
            case 46: return hainan;
            case 50: return chongqing;
            case 51: return sichuan;
            case 52: return guizhou;
            case 53: return yunnan;
            case 54: return tibet;
            case 61: return shaanxi;
            case 62: return gansu;
            case 63: return qinghai;
            case 64: return ningxia;
            case 65: return xinjiang;
            default: return null;
        }
    }

    public Map<String, MapTemp> getByProvinceAndYear(Integer provinceId,Integer year){
        CollegeScoreSimpleInner c = getByProvince(provinceId);
        return c.get(year);
    }

}
