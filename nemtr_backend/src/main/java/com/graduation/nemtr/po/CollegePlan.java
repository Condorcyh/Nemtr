package com.graduation.nemtr.po;

import com.graduation.nemtr.po.inner.CollegePlanInner;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "college_plan")
public class CollegePlan {
    @Indexed
    @Field("_id")
    String id;

    @Field("name")
    String name;

    @Field("school_id")
    Long schoolId;

    @Field("北京")
    CollegePlanInner beijing;

    @Field("天津")
    CollegePlanInner tianjin;

    @Field("河北")
    CollegePlanInner hebei;

    @Field("山西")
    CollegePlanInner shanxi;

    @Field("内蒙古")
    CollegePlanInner neimenggu;

    @Field("辽宁")
    CollegePlanInner liaoning;

    @Field("吉林")
    CollegePlanInner jilin;

    @Field("黑龙江")
    CollegePlanInner heilongjiang;

    @Field("上海")
    CollegePlanInner shanghai;

    @Field("江苏")
    CollegePlanInner jiangsu;

    @Field("浙江")
    CollegePlanInner zhejiang;

    @Field("安徽")
    CollegePlanInner anhui;

    @Field("福建")
    CollegePlanInner fujian;

    @Field("江西")
    CollegePlanInner jiangxi;

    @Field("山东")
    CollegePlanInner shandong;

    @Field("河南")
    CollegePlanInner henan;

    @Field("湖北")
    CollegePlanInner hubei;

    @Field("湖南")
    CollegePlanInner hunan;

    @Field("广东")
    CollegePlanInner guangdong;

    @Field("广西")
    CollegePlanInner guangxi;

    @Field("海南")
    CollegePlanInner hainan;

    @Field("重庆")
    CollegePlanInner chongqing;

    @Field("四川")
    CollegePlanInner sichuan;

    @Field("贵州")
    CollegePlanInner guizhou;

    @Field("云南")
    CollegePlanInner yunnan;

    @Field("西藏")
    CollegePlanInner tibet;

    @Field("陕西")
    CollegePlanInner shaanxi;

    @Field("甘肃")
    CollegePlanInner gansu;

    @Field("青海")
    CollegePlanInner qinghai;

    @Field("宁夏")
    CollegePlanInner ningxia;

    @Field("新疆")
    CollegePlanInner xinjiang;

    public CollegePlanInner getByProvince(int id, Integer provinceId){
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

    public int getByProvinceAndYear(Integer provinceId,Integer year){
        CollegePlanInner c = getByProvince(provinceId, provinceId);
        return (int)c.get(year);
    }

}
