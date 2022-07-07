package com.graduation.nemtr.vo.request;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class RateRequestVO {
    private Long schoolId;
    private Long majorId;
    private String majorName;
    private String schoolName;
    private Long provinceId;
    private Long rate;
    private String userId;
}
