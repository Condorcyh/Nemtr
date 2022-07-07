package com.graduation.nemtr.service;

import com.graduation.nemtr.vo.*;
import com.graduation.nemtr.vo.request.RecommendInputVO;

import java.util.List;

public interface RecommendService {
   List<CollegeVO> getRecommendColleges(RecommendInputVO recommendInputVO);
   List<CollegeVO> getRecommendMajors(RecommendInputVO recommendInputVO);
   Long getFuzzyLevel(RecommendInputVO recommendInputVO);
}
