package com.graduation.nemtr.vo;

import lombok.Data;

@Data
public class MapCountVO {
    private String name;
    private Long provinceId;
    private Long countTotal;
    private Long count985;
    private Long count211;
    private Long countDualClassName;
    private Long countBen;
    private Long countZhuan;
}
