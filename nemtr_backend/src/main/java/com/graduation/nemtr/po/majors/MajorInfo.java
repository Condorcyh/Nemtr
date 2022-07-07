package com.graduation.nemtr.po.majors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MajorInfo {
    @Field("min")
    Long min;
    @Field("min_section")
    Long minSection;
    @Field("spname")
    String spName;
    @Field("special_id")
    Long specialId;
    @Field("sub")
    Long sub;
}
