package com.graduation.nemtr.po;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Data
@Document(collection = "simple_major")
public class Major {
    @Indexed
    @Field("_id")
    String id;

    @Field("degree")
    String degree;

    @Field("id")
    String gkSpecialId;

    @Field("level1_name")
    String level1Name;

    @Field("level2_name")
    String level2Name;

    @Field("level3_name")
    String level3Name;

    @Field("limit_year")
    String limitYear;

    @Field("name")
    String name;

    @Field("rank")
    Long rank;

    @Field("rank_type")
    Long rankType;

    @Field("rankall")
    Long rankAll;

    @Field("spcode")
    String spcode;

    @Field("special_id")
    Long specialId;

    @Field("tag")
    Long tag;

    @Field("view_month_number")
    Long viewMonth;

    @Field("view_total_number")
    Long viewTotal;

    @Field("view_week_number")
    Long viewWeek;

}
