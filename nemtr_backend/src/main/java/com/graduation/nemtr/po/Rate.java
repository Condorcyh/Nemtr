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
@Document(collection = "rate")
public class Rate {
    @Indexed
    @Field("_id")
    String id;

    @Field("provinceId")
    Long provinceId;

    @Field("majorId")
    Long majorId;

    @Field("schoolId")
    Long schoolId;

    @Field("rate")
    Long rate;

    @Field("userId")
    String userId;

    @Field("majorName")
    String majorName;

    @Field("schoolName")
    String schoolName;

}
