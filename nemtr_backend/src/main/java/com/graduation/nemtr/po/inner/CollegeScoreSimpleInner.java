package com.graduation.nemtr.po.inner;

import com.graduation.nemtr.po.temp.MapTemp;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Data
public class CollegeScoreSimpleInner {

    @Field("2017")
    Map<String, MapTemp> year2017;

    @Field("2018")
    Map<String, MapTemp> year2018;

    @Field("2019")
    Map<String, MapTemp> year2019;

    @Field("2020")
    Map<String, MapTemp> year2020;

    @Field("2021")
    Map<String, MapTemp> year2021;

    public Map<String, MapTemp> get(Integer year){
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
        return null;
    }
}
