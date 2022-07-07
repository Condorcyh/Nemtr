package com.graduation.nemtr.service;

import com.graduation.nemtr.enums.RankType;
import com.graduation.nemtr.vo.*;

import java.util.List;
import java.util.Map;

public interface CollegeBasicService {
    String getDescription(Long schoolId);

    String getSchoolName(Long schoolId);

    List<RankVO> getUniversityRank(RankType rankType);

    MapCountVO getMapProvinceCount(Long provinceId);

    SexVO getUniversitySexRate(Long schoolId);

    CollegeCardVO getCollegeCard(Long schoolId);

    List<CollegeMapScoreVO> getCollegeMapMessage(Long schoolId, Long year);

    Long search(String schoolName);

    List<Map<String,String>> getFuzzyName(String name);
}
