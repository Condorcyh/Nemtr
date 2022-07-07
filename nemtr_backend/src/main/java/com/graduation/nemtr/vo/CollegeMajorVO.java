package com.graduation.nemtr.vo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
public class CollegeMajorVO {
    String majorCode;
    String majorName;
    List<CollegeMajorSpecialVO> majorSpecial;

}
