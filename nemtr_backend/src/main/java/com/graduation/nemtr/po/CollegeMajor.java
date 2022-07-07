package com.graduation.nemtr.po;

import com.graduation.nemtr.po.inner.CollegeMajorInner;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Map;

@Data
@Document(collection = "college_major")
public class CollegeMajor {
    @Indexed
    @Field("_id")
    String id;

    @Field("name")
    String name;

    @Field("school_id")
    Long schoolId;

    @Field("num_academician")
    String numAcademician;

    @Field("num_doctor")
    String numDoctor;

    @Field("num_lab")
    String numLab;

    @Field("num_master")
    String numMaster;

    @Field("num_subject")
    String numSubject;

    @Field("major")
    List<CollegeMajorInner> major;

    @Field("nation_major")
    List<String> nationMajor;

    @Field("abc_rank")
    Map<String,String> abcRank;

}
