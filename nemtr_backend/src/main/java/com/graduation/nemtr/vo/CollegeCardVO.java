package com.graduation.nemtr.vo;

import com.graduation.nemtr.po.inner.CollegeMajorInner;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Map;

@Data
public class CollegeCardVO {
    private Integer academician;
    private Integer doctor;
    private Integer lab;
    private Integer master;
    private Integer subject;
    private Integer nation;
    private Integer A;
}
