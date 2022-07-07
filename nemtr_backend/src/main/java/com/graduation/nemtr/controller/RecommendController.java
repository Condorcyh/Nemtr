package com.graduation.nemtr.controller;

import com.graduation.nemtr.service.RecommendService;
import com.graduation.nemtr.utils.Result;
import com.graduation.nemtr.vo.CollegeVO;
import com.graduation.nemtr.vo.request.RecommendInputVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommend")
@CrossOrigin
public class RecommendController {
    @Autowired
    private RecommendService recommendService;

    @PostMapping("/getFuzzyLevel")
    public Result getFuzzyLevel(@RequestBody RecommendInputVO recommendInputVO){
        return Result.ok().data("level",recommendService.getFuzzyLevel(recommendInputVO));
    }

    @PostMapping("/getRecommendColleges")
    public Result getRecommendColleges(@RequestBody RecommendInputVO recommendInputVO) {
        List<CollegeVO> collegeVOList = recommendService.getRecommendColleges(recommendInputVO);
        return Result.ok().data("collegeList", collegeVOList);
    }

    @PostMapping("/getRecommendMajors")
    public Result getRecommendMajors(@RequestBody RecommendInputVO recommendInputVO) {
        List<CollegeVO> collegeVOList = recommendService.getRecommendMajors(recommendInputVO);
        return Result.ok().data("collegeList", collegeVOList);
    }

}
