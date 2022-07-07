package com.graduation.nemtr.po;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Data
@Document(collection = "college")
public class College {
    @Indexed
    @Field("_id")
    String id;

    @Field("address")
    String address;

    @Field("admissions")
    Long admissions;

    @Field("belong")
    String belong;

    @Field("central")
    Long central;

    @Field("city_id")
    Long cityId;

    @Field("city_name")
    String cityName;

    @Field("code_enroll")
    Long codeEnroll;

    @Field("colleges_level")
    String collegesLevel;

    @Field("county_id")
    Long countyId;

    @Field("county_name")
    String countyName;

    @Field("department")
    Long department;

    @Field("dual_class")
    Long dualClass;

    @Field("dual_class_name")
    String dualClassName;

    @Field("f211")
    Long is211;

    @Field("f985")
    Long is985;

    @Field("id")
    String gkSchoolId;

    @Field("level")
    Long level;

    @Field("level_name")
    String levelName;

    @Field("name")
    String name;

    @Field("nature")
    Long nature;

    @Field("nature_name")
    String natureName;

    @Field("province_id")
    Long provinceId;

    @Field("province_name")
    String provinceName;

    @Field("school_id")
    Long schoolId;

    @Field("school_type")
    Long schoolType;

    @Field("type")
    Long type;

    @Field("type_name")
    String typeName;

    @Field("view_month_number")
    Long viewMonth;

    @Field("view_total_number")
    Long viewTotal;

    @Field("view_week_number")
    Long viewWeek;

    @Field("view_year_number")
    Long viewYear;

    @Field("create_date")
    String createDate;

    @Field("email")
    String email;

    @Field("phone")
    String phone;

    @Field("postcode")
    String postcode;

    @Field("rank")
    Map<String,String> rank;

    @Field("short")
    String shortName;

    @Field("website")
    Map<String,String> website;

    @Field("img")
    String img;

}
