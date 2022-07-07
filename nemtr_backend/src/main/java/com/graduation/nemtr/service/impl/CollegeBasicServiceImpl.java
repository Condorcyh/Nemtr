package com.graduation.nemtr.service.impl;

import com.graduation.nemtr.enums.Provinces;
import com.graduation.nemtr.enums.RankType;
import com.graduation.nemtr.po.CollegeDescription;
import com.graduation.nemtr.po.CollegeMajor;
import com.graduation.nemtr.po.CollegeScore;
import com.graduation.nemtr.po.inner.CollegeScoreInner;
import com.graduation.nemtr.po.temp.MapTemp;
import com.graduation.nemtr.po.temp.RequestCollegeRank;
import com.graduation.nemtr.repository.*;
import com.graduation.nemtr.service.CollegeBasicService;
import com.graduation.nemtr.transform.Transform;
import com.graduation.nemtr.utils.Methods;
import com.graduation.nemtr.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CollegeBasicServiceImpl implements CollegeBasicService {
    @Autowired
    private CollegeDescriptionRepository collegeDescriptionRepository;

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private CollegeScoreRepository collegeScoreRepository;

    @Autowired
    private RecommendTempRepository recommendTempRepository;

    @Autowired
    private CollegePlanRepository collegePlanRepository;

    @Autowired
    private CollegeMajorRepository collegeMajorRepository;

    @Override
    public String getDescription(Long schoolId){
        Optional<CollegeDescription> collegeDescription = collegeDescriptionRepository.findBySchoolId(schoolId);
        return collegeDescription.map(CollegeDescription::getDescription).orElse(null);
    }

    @Override
    public String getSchoolName(Long schoolId){
        Optional<CollegeDescription> collegeDescription = collegeDescriptionRepository.findBySchoolId(schoolId);
        return collegeDescription.map(CollegeDescription::getName).orElse(null);
    }

    @Override
    public List<RankVO> getUniversityRank(RankType rankType){
        String rankTypeName = rankType.toString();
        List<RequestCollegeRank> requestCollegeRanks = collegeRepository.findAllRank();
        requestCollegeRanks.sort(new Comparator<RequestCollegeRank>() {
            @Override
            public int compare(RequestCollegeRank o1, RequestCollegeRank o2) {
                String s1 = o1.getRank().get(rankTypeName);
                String s2 = o2.getRank().get(rankTypeName);
                int i1 = Methods.rankZero(Methods.getBetweenOrBelow(s1));
                int i2 = Methods.rankZero(Methods.getBetweenOrBelow(s2));
                return Integer.compare(i1, i2);
            }
        });
        List<RankVO> rankVOList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            rankVOList.add(Transform.RankVO(requestCollegeRanks.get(i), rankTypeName));
        }
        return rankVOList;
    }

    @Override
    public MapCountVO getMapProvinceCount(Long provinceId){
        MapCountVO mapCountVO = new MapCountVO();
        mapCountVO.setCountTotal(collegeRepository.countAllByProvinceId(provinceId));
        mapCountVO.setCount985(collegeRepository.countAllByProvinceIdAndIs985(provinceId,1L));
        mapCountVO.setCount211(collegeRepository.countAllByProvinceIdAndIs211(provinceId,1L));
        mapCountVO.setCountDualClassName(collegeRepository.countAllByProvinceIdAndDualClassName(provinceId,"双一流"));
        mapCountVO.setCountBen(collegeRepository.countAllByProvinceIdAndLevel(provinceId,2001L));
        mapCountVO.setCountZhuan(collegeRepository.countAllByProvinceIdAndLevel(provinceId,2002L));
        mapCountVO.setProvinceId(provinceId);
        mapCountVO.setName(Provinces.toString(provinceId.intValue()));
        return mapCountVO;
    }

    @Override
    public SexVO getUniversitySexRate(Long schoolId){
        return null;//todo
    }

    @Override
    public CollegeCardVO getCollegeCard(Long schoolId){
        Optional<CollegeMajor> major = collegeMajorRepository.findBySchoolId(schoolId);
            CollegeMajor collegeMajor = major.orElse(null);
        assert collegeMajor != null;
        return Transform.CollegeCardVO(collegeMajor);
    }

    @Override
    public List<CollegeMapScoreVO> getCollegeMapMessage(Long schoolId, Long year){
        List<CollegeMapScoreVO> collegeMapScoreVOs = new ArrayList<>();
        for(Provinces p:Provinces.values()){
            if(p.getId()==71||p.getId()==81||p.getId()==82){
                continue;
            }
            Map<String, MapTemp> batch = Objects.requireNonNull(recommendTempRepository.findBySchoolId(schoolId).orElse(null)).getByProvinceAndYear(p.getId(),year.intValue());
            int num = (Objects.requireNonNull(collegePlanRepository.findBySchoolId(schoolId).orElse(null))).getByProvinceAndYear(p.getId(),year.intValue());
//            collegeMapScoreVOs.add(getScoreVOBySchoolIdAndYear(schoolId, (long) p.getId(),year));
            collegeMapScoreVOs.add(Transform.CollegeScoreVO(num,batch,(long)p.getId()));
        }
        return collegeMapScoreVOs;
    }

    @Override
    public Long search(String schoolName){
        Optional<CollegeDescription> collegeDescription = collegeDescriptionRepository.findByName(schoolName);
        return collegeDescription.map(CollegeDescription::getSchoolId).orElse((long) -1);
    }

    @Override
    public List<Map<String,String>> getFuzzyName(String name){
        List<CollegeDescription> temps = collegeDescriptionRepository.findByNameContains(name);
        List<Map<String,String>> result = new ArrayList<>();
        for(CollegeDescription c:temps){
            Map<String,String> temp = new HashMap<>();
            temp.put("name",c.getName());
            result.add(temp);
        }
        return result;
    }

//    private CollegeMapScoreVO getScoreVOBySchoolIdAndYear(Long schoolId, Long provinceId, Long year){
//        Optional<CollegeScore> temp = collegeScoreRepository.findBySchoolId(schoolId);
//        if(temp.isPresent()){
//            List<CollegeScoreInner> scoreInners = temp.get().getByProvince(Math.toIntExact(provinceId));
//            List<CollegeScoreInner> scoreInnersResult = new ArrayList<>();
//            for(CollegeScoreInner collegeScoreInner:scoreInners){
//                if(collegeScoreInner.getYear().equals(year) && collegeScoreInner.getZslxName().equals("普通类")){
//                    scoreInnersResult.add(collegeScoreInner);
//                }
//            }
//            return Transform.CollegeScoreVO(scoreInnersResult,provinceId);
//        }
//        return null;
//    }

}
