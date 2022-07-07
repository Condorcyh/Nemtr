package com.graduation.nemtr.po.temp;

import com.graduation.nemtr.enums.EntranceType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RecommendReturn {
    List<Long> risk;
    List<Long> normal;
    List<Long> safe;
    EntranceType entranceType;
    public RecommendReturn(List<Long> risk,List<Long> normal,List<Long> safe,EntranceType entranceType){
        this.risk = risk;
        this.normal = normal;
        this.safe = safe;
        this.entranceType = entranceType;
    }
}
