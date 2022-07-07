package com.graduation.nemtr.vo.request;

import com.graduation.nemtr.enums.EntranceType;
import lombok.Data;

@Data
public class RecommendInputVO {
    private Long provinceId;
    private Long score;
    private Long level;
    private String entranceType;
}
