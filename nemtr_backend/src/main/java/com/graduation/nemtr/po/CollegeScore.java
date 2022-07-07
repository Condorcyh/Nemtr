package com.graduation.nemtr.po;

import com.graduation.nemtr.po.inner.CollegeScoreInner;
import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "college_score")
public class CollegeScore {
    @Indexed
    @Field("_id")
    String id;

    @Field("name")
    String name;

    @Field("school_id")
    Long schoolId;

    @Field("北京")
    List<CollegeScoreInner> beijing;

    @Field("天津")
    List<CollegeScoreInner> tianjin;

    @Field("河北")
    List<CollegeScoreInner> hebei;

    @Field("山西")
    List<CollegeScoreInner> shanxi;

    @Field("内蒙古")
    List<CollegeScoreInner> neimenggu;

    @Field("辽宁")
    List<CollegeScoreInner> liaoning;

    @Field("吉林")
    List<CollegeScoreInner> jilin;

    @Field("黑龙江")
    List<CollegeScoreInner> heilongjiang;

    @Field("上海")
    List<CollegeScoreInner> shanghai;

    @Field("江苏")
    List<CollegeScoreInner> jiangsu;

    @Field("浙江")
    List<CollegeScoreInner> zhejiang;

    @Field("安徽")
    List<CollegeScoreInner> anhui;

    @Field("福建")
    List<CollegeScoreInner> fujian;

    @Field("江西")
    List<CollegeScoreInner> jiangxi;

    @Field("山东")
    List<CollegeScoreInner> shandong;

    @Field("河南")
    List<CollegeScoreInner> henan;

    @Field("湖北")
    List<CollegeScoreInner> hubei;

    @Field("湖南")
    List<CollegeScoreInner> hunan;

    @Field("广东")
    List<CollegeScoreInner> guangdong;

    @Field("广西")
    List<CollegeScoreInner> guangxi;

    @Field("海南")
    List<CollegeScoreInner> hainan;

    @Field("重庆")
    List<CollegeScoreInner> chongqing;

    @Field("四川")
    List<CollegeScoreInner> sichuan;

    @Field("贵州")
    List<CollegeScoreInner> guizhou;

    @Field("云南")
    List<CollegeScoreInner> yunnan;

    @Field("西藏")
    List<CollegeScoreInner> tibet;

    @Field("陕西")
    List<CollegeScoreInner> shaanxi;

    @Field("甘肃")
    List<CollegeScoreInner> gansu;

    @Field("青海")
    List<CollegeScoreInner> qinghai;

    @Field("宁夏")
    List<CollegeScoreInner> ningxia;

    @Field("新疆")
    List<CollegeScoreInner> xinjiang;

    public List<CollegeScoreInner> getByProvince(Integer provinceId){
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

}
