package com.graduation.nemtr.po;

import com.graduation.nemtr.po.inner.ProvinceBatchInner;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Map;

@Data
@Document(collection = "province")
public class ProvinceInfo {
    @Indexed
    @Field("_id")
    String id;

    @Field("province_id")
    Long provinceId;

    @Field("province_name")
    String provinceName;

    @Field("province_name_whole")
    String provinceNameWhole;

    @Field("2014")
    List<ProvinceBatchInner> batch2014;

    @Field("2015")
    List<ProvinceBatchInner> batch2015;

    @Field("2016")
    List<ProvinceBatchInner> batch2016;

    @Field("2017")
    List<ProvinceBatchInner> batch2017;

    @Field("2018")
    List<ProvinceBatchInner> batch2018;

    @Field("2019")
    List<ProvinceBatchInner> batch2019;

    @Field("2020")
    List<ProvinceBatchInner> batch2020;

    @Field("2021")
    List<ProvinceBatchInner> batch2021;

}
