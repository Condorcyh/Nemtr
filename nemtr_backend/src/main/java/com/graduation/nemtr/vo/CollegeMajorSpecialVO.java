package com.graduation.nemtr.vo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class CollegeMajorSpecialVO {
    Long specialId;
    String specialName;
    Long isNation;
    Long levelCode;

}
