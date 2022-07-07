package com.graduation.nemtr.po.temp;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Data
public class MapTemp {

    @Field("min")
    String min;

    @Field("min_level")
    String minLevel;

    @Field("batch")
    String batch;

}
