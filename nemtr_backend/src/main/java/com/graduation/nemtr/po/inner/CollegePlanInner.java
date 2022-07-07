package com.graduation.nemtr.po.inner;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
public class CollegePlanInner {

    @Field("2017")
    int year2017;

    @Field("2018")
    int year2018;

    @Field("2019")
    int year2019;

    @Field("2020")
    int year2020;

    @Field("2021")
    int year2021;

    public int get(Integer year){
        switch (year) {
            case 2017:
                return year2017;
            case 2018:
                return year2018;
            case 2019:
                return year2019;
            case 2020:
                return year2020;
            case 2021:
                return year2021;
        }
        return -1;
    }
}
