package com.graduation.nemtr.po.inner;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "college_major")
public class CollegeMajorInner {

    @Field("major_code")
    String majorCode;

    @Field("major_name")
    String majorName;

    @Field("major_special")
    List<CollegeMajorSpecialInner> majorSpecial;

}
