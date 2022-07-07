package com.graduation.nemtr.po.inner;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "college_major")
public class CollegeMajorSpecialInner {

    @Field("special_id")
    Long specialId;

    @Field("special_name")
    String specialName;

    @Field("is_nation")
    Long isNation;

    @Field("level_code")
    Long levelCode;

}
