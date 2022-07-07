package com.graduation.nemtr.po.predict;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PredictInfo {
    @Field("pre_batch_score")
    Long preBatchScore;
    @Field("pre_min")
    Long preMin;
    @Field("pre_sub")
    Long preSub;
    @Field("pre_min_level")
    Long preMinLevel;
    @Field("pre_batch_name")
    String preBatchName;
    @Field("label")
    Long label;
}
