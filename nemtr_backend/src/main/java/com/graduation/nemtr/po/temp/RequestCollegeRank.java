package com.graduation.nemtr.po.temp;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Data
@Document(collection = "college")
public class RequestCollegeRank {
    @Indexed
    @Field("_id")
    String id;

    @Field("name")
    String name;

    @Field("school_id")
    Long schoolId;

    @Field("city_name")
    String cityName;

    @Field("type_name")
    String typeName;

    @Field("province_name")
    String provinceName;

    @Field("rank")
    Map<String,String> rank;

    @Field("img")
    String img;

}
