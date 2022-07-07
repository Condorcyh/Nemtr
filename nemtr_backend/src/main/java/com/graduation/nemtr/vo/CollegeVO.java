package com.graduation.nemtr.vo;
import com.graduation.nemtr.enums.RecommendType;
import lombok.Data;

import java.util.Map;

@Data
public class CollegeVO {
    String address;
    String belong;
    String cityName;
    Long codeEnroll;
    String dualClassName;
    Long is211;
    Long is985;
    String levelName;
    String name;
    String natureName;
    Long provinceId;
    String provinceName;
    Long schoolId;
    String typeName;
    String createDate;
    String email;
    String phone;
    String postcode;
    String img;
    String recommendType;
    String firstMajor;
    String firstMajorSpecial;
    String firstMajorCode;//这个code是给院校的
    Long majorId;//这个id才是真正的major的id
    Double rate;//平均评分
    Long userNum;//用户数量
    Long recType;
}
