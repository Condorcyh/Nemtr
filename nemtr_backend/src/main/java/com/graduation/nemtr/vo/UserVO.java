package com.graduation.nemtr.vo;

import com.graduation.nemtr.enums.EntranceType;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

@Data
public class UserVO {
    private String id;
    private String phone;
    private String pwd;
    private String name;
    private String highSchool;
    private String provinceName;
    private Long provinceId;
    private String entranceType;
    private List<RateDataVO> userRateData;
}
