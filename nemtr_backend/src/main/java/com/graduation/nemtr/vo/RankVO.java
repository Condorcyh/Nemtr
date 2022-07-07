package com.graduation.nemtr.vo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class RankVO {
    private String name;
    private String rank;
    private Long schoolId;
    private String cityName;
    private String typeName;
    private String provinceName;
    private String img;
}
