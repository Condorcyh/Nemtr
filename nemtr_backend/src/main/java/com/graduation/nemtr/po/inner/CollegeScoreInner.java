package com.graduation.nemtr.po.inner;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "college_score")
public class CollegeScoreInner {

    @Field("answerurl")
    String answerUrl;

    @Field("average")
    String average;//这个数据有时候有数值，但数据库默认的是'-'

    @Field("avg_section")
    String avgSection;//这个数据有时候有数值，但数据库默认的是'-'

    @Field("filing")
    String filing;//这个数据有时候有数值，但数据库默认的是'-'

    @Field("local_batch_name")
    String localBatchName;

    @Field("local_type_name")
    String LocalTypeName;

    @Field("major_score")
    String majorScore;//这个数据有时候有数值，但数据库默认的是'-'

    @Field("max")
    String max;//这个数据有时候有数值，但数据库默认的是'-'

    @Field("min")
    String min;//这个数据有时候有数值，但数据库默认的是'-'

    @Field("min_section")
    String minSection;//这个数据有时候有数值，但数据库默认的是'-'

    @Field("num")
    String num;//这个数据有时候有数值，但数据库默认的是'-'

    @Field("proscore")
    String proScore;//这个数据有时候有数值，但数据库默认的是'-'

    @Field("sg_fxk")
    String sgFxk;

    @Field("sg_info")
    String sgInfo;

    @Field("sg_name")
    String sgName;

    @Field("sg_sxk")
    String sgSxk;

    @Field("sg_type")
    String sgType;

    @Field("xclevel_name")
    String xclevelName;

    @Field("zslx_name")
    String zslxName;

    @Field("year")
    Long year;

}
