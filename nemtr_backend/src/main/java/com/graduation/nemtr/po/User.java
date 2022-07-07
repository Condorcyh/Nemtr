package com.graduation.nemtr.po;

import com.graduation.nemtr.enums.EntranceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class User {
    @Indexed
    @Field("_id")
    String id;

    @Field("name")
    String name;

    @Field("phone")
    String phone;

    @Field("password")
    String password;

    @Field("highSchool")
    String highSchool;

    @Field("provinceName")
    String provinceName;

    @Field("provinceId")
    Long provinceId;

    @Field("entranceType")
    EntranceType entranceType;

}
