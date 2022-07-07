package com.graduation.nemtr.vo;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Map;

@Data
public class CollegeMapScoreVO {
    List<BatchPointLevelVO> map;
    String studentNum;
    Integer isNull;
    String provinceName;
    Long provinceId;

    @Data
    public static class BatchPointLevelVO{
        String batchName;
        String pointAndLevel;
        public BatchPointLevelVO(String batchName, String pointAndLevel){
            this.batchName = batchName;
            this.pointAndLevel = pointAndLevel;
        }
    }
}
