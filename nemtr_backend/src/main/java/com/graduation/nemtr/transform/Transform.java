package com.graduation.nemtr.transform;

import com.graduation.nemtr.enums.EntranceType;
import com.graduation.nemtr.enums.Provinces;
import com.graduation.nemtr.enums.RecommendType;
import com.graduation.nemtr.po.College;
import com.graduation.nemtr.po.CollegeMajor;
import com.graduation.nemtr.po.inner.CollegeScoreInner;
import com.graduation.nemtr.po.temp.MapTemp;
import com.graduation.nemtr.po.temp.RequestCollegeRank;
import com.graduation.nemtr.vo.*;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Transform {

    public static RankVO RankVO(RequestCollegeRank requestCollegeRank, String rankName){
        RankVO rankVO = new RankVO();
        rankVO.setName(requestCollegeRank.getName());
        rankVO.setSchoolId(requestCollegeRank.getSchoolId());
        rankVO.setRank(requestCollegeRank.getRank().get(rankName));
        rankVO.setCityName(requestCollegeRank.getCityName());
        rankVO.setProvinceName(requestCollegeRank.getProvinceName());
        rankVO.setTypeName(requestCollegeRank.getTypeName());
        rankVO.setImg(requestCollegeRank.getImg());
        return rankVO;
    }

//    public static CollegeMapScoreVO CollegeScoreVO(List<CollegeScoreInner> collegeScore,Long provinceId){
//        CollegeMapScoreVO collegeMapScoreVO = new CollegeMapScoreVO();
//            collegeMapScoreVO.setIsNull(0);
//            collegeMapScoreVO.setProvinceId(provinceId);
//            collegeMapScoreVO.setProvinceName(Provinces.toString(provinceId.intValue()));
//            List<CollegeMapScoreVO.BatchPointLevelVO> map = new ArrayList<>();
//            Map<String, Integer> tempMap1 = new HashMap<>();
//            Map<String, Integer> tempMap2 = new HashMap<>();
//            int total = 0;
//            boolean totalCheck = true;
//            for (CollegeScoreInner c : collegeScore) {
//                String keyName = c.getLocalTypeName() + c.getLocalBatchName() + "/名次";
//                int min = !c.getMin().equals("-") ? (int)Double.parseDouble(c.getMin()) : -1;
//                int level = !c.getMinSection().equals("-") ? Integer.parseInt(c.getMinSection()) : -1;
//                if (tempMap1.containsKey(keyName)) {
//                    min = tempMap1.get(keyName) != -1 ? Math.min(min, tempMap1.get(keyName)) : -1;
//                }
//                if (tempMap2.containsKey(keyName)) {
//                    level = tempMap2.get(keyName) != -1 ? Math.max(level, tempMap2.get(keyName)) : -1;
//                }
//                tempMap1.put(keyName, min);
//                tempMap2.put(keyName, level);
//                if (!c.getNum().equals("-") && totalCheck) {
//                    total = total + Integer.parseInt(c.getNum());
//                } else{
//                    total = -1;
//                    totalCheck=!totalCheck;
//                }
//            }
//            for (Map.Entry<String, Integer> entry : tempMap1.entrySet()) {
//                String showMin = entry.getValue() != -1 ? entry.getValue().toString() : "-";
//                String showLevel = tempMap2.get(entry.getKey()) != -1 ? tempMap2.get(entry.getKey()).toString() : "-";
//                CollegeMapScoreVO.BatchPointLevelVO batchPointLevelVO = new CollegeMapScoreVO.BatchPointLevelVO(entry.getKey(),showMin+"/"+showLevel);
//                map.add(batchPointLevelVO);
//            }
//            collegeMapScoreVO.setStudentNum(total>0?total+"":"-");
//            collegeMapScoreVO.setMap(map);
//            if(map.isEmpty()){
//                collegeMapScoreVO.setIsNull(1);
//            }
//            return collegeMapScoreVO;
//    }

    public static CollegeMapScoreVO CollegeScoreVO(int num, Map<String, MapTemp> batch, Long provinceId){
        CollegeMapScoreVO collegeMapScoreVO = new CollegeMapScoreVO();
        collegeMapScoreVO.setProvinceId(provinceId);
        collegeMapScoreVO.setProvinceName(Provinces.toString(provinceId.intValue()));
        collegeMapScoreVO.setIsNull(num<=0?1:0);
        if(batch==null){
            collegeMapScoreVO.setIsNull(1);
            return collegeMapScoreVO;
        }
        collegeMapScoreVO.setStudentNum(num+"");
        List<CollegeMapScoreVO.BatchPointLevelVO> map = new ArrayList<>();
        for(String type:batch.keySet()){
            String typeName = EntranceType.getNameById(Integer.parseInt(type));
            MapTemp temp = batch.get(type);
            String min = temp.getMin();
            String batchName = temp.getBatch();
            String level = temp.getMinLevel();
            if(level.equals("-1") || level.equals("-") || level.equals("0")){
                level="-";
            }
            CollegeMapScoreVO.BatchPointLevelVO batchPointLevelVO = new CollegeMapScoreVO.BatchPointLevelVO(typeName+batchName+"/名次",min+"/"+level);
            map.add(batchPointLevelVO);
        }
        collegeMapScoreVO.setMap(map);
        return collegeMapScoreVO;
    }

    public static CollegeCardVO CollegeCardVO(CollegeMajor collegeMajor){
        CollegeCardVO collegeCardVO = new CollegeCardVO();
        collegeCardVO.setAcademician(Integer.parseInt(collegeMajor.getNumAcademician()));
        collegeCardVO.setDoctor(Integer.parseInt(collegeMajor.getNumDoctor()));
        collegeCardVO.setLab(Integer.parseInt(collegeMajor.getNumLab()));
        collegeCardVO.setMaster(Integer.parseInt(collegeMajor.getNumMaster()));
        collegeCardVO.setSubject(Integer.parseInt(collegeMajor.getNumSubject()));
        collegeCardVO.setNation(collegeMajor.getNationMajor().size());
        Map<String,String> abcRank = collegeMajor.getAbcRank();
        Integer Aplus = abcRank.containsKey("A+")?Integer.parseInt(abcRank.get("A+")):0;
        Integer A = abcRank.containsKey("A+")?Integer.parseInt(abcRank.get("A")):0;
        Integer Aminus = abcRank.containsKey("A-")?Integer.parseInt(abcRank.get("A-")):0;
        collegeCardVO.setA(A+Aminus+Aplus);
        return collegeCardVO;
    }

    public static CollegeVO CollegeVO(College college,RecommendType recommendType,
                                      List<String> firstMajor,
                                      Long recType,
                                      Double rate,
                                      Long userNum){
        CollegeVO collegeVO = new CollegeVO();
        BeanUtils.copyProperties(college,collegeVO);
        collegeVO.setRecommendType(recommendType.toString());
        collegeVO.setFirstMajor(firstMajor.get(0));
        collegeVO.setFirstMajorSpecial(firstMajor.get(1));
        collegeVO.setFirstMajorCode(firstMajor.get(2));
        collegeVO.setMajorId(Long.parseLong(firstMajor.get(2)));
        collegeVO.setRecType(recType);
        collegeVO.setUserNum(userNum);
        collegeVO.setRate(rate);
        return collegeVO;
    }

    public static CollegeVO CollegeVO(College college,
                                      RecommendType recommendType,
                                      String firstMajor,
                                      Long specialId,
                                      Double rate,
                                      Long userNum
    ) {
        CollegeVO collegeVO = new CollegeVO();
        BeanUtils.copyProperties(college,collegeVO);
        collegeVO.setMajorId(specialId);
        collegeVO.setRecommendType(recommendType.toString());
        collegeVO.setFirstMajor(firstMajor);
        collegeVO.setFirstMajorSpecial(firstMajor);
        collegeVO.setRate(rate);
        collegeVO.setUserNum(userNum);
        collegeVO.setRecType(2L);
        return collegeVO;
    }

}
