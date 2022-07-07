package com.graduation.nemtr.vo;

import lombok.Data;

@Data
public class RateDataVO {
    private String school;
    private String major;
    private String province;
    private Double averageRate;
    private Long userRate;
}
