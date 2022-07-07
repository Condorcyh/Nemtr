package com.graduation.nemtr.po.inner;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "province")
public class ProvinceBatchInner {

    @Field("score")
    String score;

    @Field("major_score")
    String majorScore;

    @Field("type_name")
    String typeName;

    @Field("batch_name")
    String batchName;

}
