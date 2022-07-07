package com.graduation.nemtr.controller;

import com.graduation.nemtr.enums.Provinces;
import com.graduation.nemtr.enums.RankType;
import com.graduation.nemtr.service.CollegeBasicService;
import com.graduation.nemtr.utils.Result;
import com.graduation.nemtr.vo.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/basic")
@CrossOrigin
public class CollegeBasicController {
    @Autowired
    private CollegeBasicService collegeBasicService;

    @GetMapping("/getDescription/{schoolId}")
    public Result getDescription(@PathVariable Long schoolId){
        String description = collegeBasicService.getDescription(schoolId);
        return Result.ok().data("description", description);
    }

    @GetMapping("/getUniversityRank/{type}")
    public Result getUniversityRank(@PathVariable String type){
        RankType rankType = RankType.valueOf(type);
        String name = rankType.getName();
        List<RankVO> rankVOList = collegeBasicService.getUniversityRank(rankType);
        return Result.ok().data("rank", rankVOList).data("name", name);
    }

    @GetMapping("/getMapProvinceCount/{provinceId}")
    public Result getUniversityCount(@PathVariable Long provinceId){
        MapCountVO mapCountVO = collegeBasicService.getMapProvinceCount(provinceId);
        String province = Provinces.toString(provinceId.intValue());
        return Result.ok().data("count", mapCountVO).data("province", province);
    }

    @GetMapping("getCollegeCard/{schoolId}")
    public Result getCollegeCard(@PathVariable Long schoolId) {
        CollegeCardVO collegeCard = collegeBasicService.getCollegeCard(schoolId);
        return Result.ok().data("collegeCard", collegeCard);
    }

    @GetMapping("getCollegeMapMessage/{schoolId}/{year}")
    public Result getCollegeMapMessage(@PathVariable Long schoolId, @PathVariable Long year) {
        List<CollegeMapScoreVO> collegeMapMessage = collegeBasicService.getCollegeMapMessage(schoolId, year);
        String schoolName = collegeBasicService.getSchoolName(schoolId);
        return Result.ok().data("collegeMapMessage", collegeMapMessage).data("collegeName",schoolName);
    }

    @GetMapping("search/{schoolName}")
    public Result search(@PathVariable String schoolName){
        Long id = collegeBasicService.search(schoolName);
        return id != -1 ? Result.ok().data("schoolId", id) : Result.error().message("未找到该学校");
    }

    @GetMapping("getFuzzyName/{schoolName}")
    public Result getFuzzyName(@PathVariable String schoolName){
        List<Map<String,String>> result = collegeBasicService.getFuzzyName(schoolName);
        return result.size()!=0?Result.ok().data("fuzzy",result):Result.error().message("找不到目标关键词");
    }
}
