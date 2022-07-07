package com.graduation.nemtr.service.impl;

import com.graduation.nemtr.config.MajorDocumentConfig;
import com.graduation.nemtr.config.PredictDocumentConfig;
import com.graduation.nemtr.enums.EntranceType;
import com.graduation.nemtr.enums.Provinces;
import com.graduation.nemtr.enums.RecommendType;
import com.graduation.nemtr.po.College;
import com.graduation.nemtr.po.CollegeMajor;
import com.graduation.nemtr.po.ProvinceLayer;
import com.graduation.nemtr.po.inner.CollegeMajorInner;
import com.graduation.nemtr.po.inner.CollegeMajorSpecialInner;
import com.graduation.nemtr.po.majors.MajorInfo;
import com.graduation.nemtr.po.majors.MajorProvince;
import com.graduation.nemtr.po.predict.PredictProvince;
import com.graduation.nemtr.po.temp.RecommendReturn;
import com.graduation.nemtr.po.temp.RequestCollegeRank;
import com.graduation.nemtr.repository.*;
import com.graduation.nemtr.service.RecommendService;
import com.graduation.nemtr.service.UserService;
import com.graduation.nemtr.transform.Transform;
import com.graduation.nemtr.utils.Methods;
import com.graduation.nemtr.vo.CollegeMapScoreVO;
import com.graduation.nemtr.vo.CollegeVO;
import com.graduation.nemtr.vo.request.RecommendInputVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.EOFException;
import java.util.*;

@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    UserService userService;

    @Autowired
    CollegeRepository collegeRepository;

    @Autowired
    ProvinceLayerRepository provinceLayerRepository;

    @Autowired
    PredictRepository predictRepository;

    @Autowired
    CollegeMajorRepository collegeMajorRepository;

    @Autowired
    PredictDocumentConfig predictDocumentConfig;

    @Autowired
    MajorDocumentConfig majorDocumentConfig;

    @Autowired
    CollegeMajorScoreRepository collegeMajorScoreRepository;

    @Override
    public Long getFuzzyLevel(RecommendInputVO recommendInputVO){
        ProvinceLayer provinceLayer = provinceLayerRepository.findByProvinceId(recommendInputVO.getProvinceId()).orElse(null);
        if(provinceLayer==null){
            return 0L;
        }
        return provinceLayer.getSameLevel(getEntranceTypeByRecommendVO(recommendInputVO.getEntranceType(),recommendInputVO.getProvinceId()),recommendInputVO.getScore());
    }

    @Override
    public List<CollegeVO> getRecommendColleges(RecommendInputVO recommendInputVO){
        RecommendReturn recommendReturn = getSimpleRecommendCollege(recommendInputVO);
        return getRecommendByThreeLevels(recommendReturn.getRisk(),recommendReturn.getNormal(),recommendReturn.getSafe(),recommendReturn.getEntranceType());
    }

    @Override
    public List<CollegeVO> getRecommendMajors(RecommendInputVO recommendInputVO){
        RecommendReturn recommendReturn = getSimpleRecommendCollege(recommendInputVO);
        return getRecommendMajorsByThreeLevels(recommendReturn.getRisk(),recommendReturn.getNormal(),recommendReturn.getSafe(),recommendReturn.getEntranceType());
    }

    /**
     * 核心推荐院系的方法
     * @param recommendInputVO
     * @return
     */
    private RecommendReturn getSimpleRecommendCollege(RecommendInputVO recommendInputVO){
        Long provinceId = recommendInputVO.getProvinceId();
        Long score = recommendInputVO.getScore();
        Long degree = recommendInputVO.getLevel();
        EntranceType entranceType = getEntranceTypeByRecommendVO(recommendInputVO.getEntranceType(),provinceId);
        List<Long> risk = new ArrayList<>();
        List<Long> normal = new ArrayList<>();
        List<Long> safe = new ArrayList<>();
        String provinceName = Provinces.toEnglishString(provinceId.intValue());
        provinceName=Character.toLowerCase(provinceName.charAt(0))+provinceName.substring(1);
        predictDocumentConfig.setName("predict_"+provinceName);
        majorDocumentConfig.setName("major_"+provinceName);
        List<PredictProvince> total = predictRepository.findAll();
        for(PredictProvince n:total){
            try {
                Long min = n.getType(entranceType).getPreMin();
                String batchName = n.getType(entranceType).getPreBatchName();
                if(batchName.contains("提前")){
                    continue;
                }
                if(risk.contains(n.getSchoolId())||normal.contains(n.getSchoolId())||safe.contains(n.getSchoolId())){
                    continue;
                }
                if ( Math.abs(score-min) <= 7) {
                    risk = addLabel(n.getSchoolId(),risk,entranceType,n.getType(entranceType).getLabel());
                } else if (score - min > 7 && score - min <= 20) {
                    normal = addLabel(n.getSchoolId(),normal,entranceType,n.getType(entranceType).getLabel());
                } else if (score - min > 20 && score - min < 70) {
                    safe = addLabel(n.getSchoolId(),safe,entranceType,n.getType(entranceType).getLabel());
                }
            }
            catch (Exception e){
//                System.out.println(n.getName()+" "+n.getSchoolId());
            }
        }
        return new RecommendReturn(risk,normal,safe,entranceType);
    }

    /**
     * 簇类寻找
     * @param schoolId
     * @param from
     * @param entranceType
     * @param label
     * @return
     */
    private List<Long> addLabel(Long schoolId,List<Long> from, EntranceType entranceType,Long label){
        if(label==null){
            from.add(schoolId);
            return from;
        }
        List<PredictProvince> total = predictRepository.findBySame(entranceType.getName(),label);
        for(PredictProvince i:total){
            from.add(i.getSchoolId());
        }
        return from;
    }

    /**
     * 院校推荐的首选推荐
     * @param schoolId
     * @param entranceType
     * @return
     */
    private List<String> getFirstMajor(Long schoolId, EntranceType entranceType){
        CollegeMajor collegeMajor = collegeMajorRepository.findBySchoolId(schoolId).orElse(null);
        List<String> nations = collegeMajor.getNationMajor();
        List<CollegeMajorInner> inners = collegeMajor.getMajor();
        String majorName=null,superName = null,majorCode = null;
        List<String> result = new ArrayList<>();
        for(int i=0;i<5;i++){
            if(nations.isEmpty()){
                break;
            }
            majorName = nations.get(new Random().nextInt(nations.size()));
            loop:for(CollegeMajorInner collegeMajorInner:inners){
                for(CollegeMajorSpecialInner collegeMajorSpecialInner:collegeMajorInner.getMajorSpecial()){
                    if(majorName.equals(collegeMajorSpecialInner.getSpecialName())){
                        superName=collegeMajorInner.getMajorName();
                        majorCode=collegeMajorInner.getMajorCode();
                        break loop;
                    }
                }
            }
            if(superName==null){
                continue;
            }
            if(admissionCheck(entranceType,majorCode)){
                break;
            }
        }
        if(superName==null){
            return defaultMajor(inners);
        }
        result.add(superName);
        result.add(majorName);
        result.add(majorCode.substring(0,2));
        return result;
    }

    /**
     * 院校推荐的合并返回
     * @param risk
     * @param normal
     * @param safe
     * @param entranceType
     * @return
     */
    private List<CollegeVO> getRecommendByThreeLevels(List<Long> risk,List<Long> normal,List<Long> safe,EntranceType entranceType){
        List<CollegeVO> collegeVOS = new ArrayList<>();
        for(Long o:risk){
            College college = collegeRepository.findBySchoolId(o).orElse(null);
            List<String> firstMajor = getFirstMajor(college.getSchoolId(),entranceType);
            collegeVOS.add(Transform.CollegeVO(college,RecommendType.RISK,firstMajor,1L,0.0,0L));
        }
        for(Long o:normal){
            College college = collegeRepository.findBySchoolId(o).orElse(null);
            List<String> firstMajor = getFirstMajor(college.getSchoolId(),entranceType);
            collegeVOS.add(Transform.CollegeVO(college,RecommendType.NORMAL,firstMajor,1L,0.0,0L));
        }
        for(Long o:safe){
            College college = collegeRepository.findBySchoolId(o).orElse(null);
            List<String> firstMajor = getFirstMajor(college.getSchoolId(),entranceType);
            collegeVOS.add(Transform.CollegeVO(college,RecommendType.SAFE,firstMajor,1L,0.0,0L));
        }
        return collegeVOS;
    }

    /**
     * 推荐的合并返回
     * @param risk
     * @param normal
     * @param safe
     * @param entranceType
     * @return
     */
    private List<CollegeVO> getRecommendMajorsByThreeLevels(List<Long> risk,List<Long> normal,List<Long> safe,EntranceType entranceType){
        List<CollegeVO> collegeVOS = new ArrayList<>();
        for (Long o : risk) {
                College college = collegeRepository.findBySchoolId(o).orElse(null);
                MajorProvince allMajors = collegeMajorScoreRepository.findBySchoolId(o).orElse(null);
                if (allMajors == null) {
                    List<String> firstMajor = getFirstMajor(college.getSchoolId(), entranceType);
                    collegeVOS.add(Transform.CollegeVO(college, RecommendType.RISK, firstMajor,2L,
                            userService.getAverageRate(Long.parseLong(firstMajor.get(2))),
                            userService.getUserNum(Long.parseLong(firstMajor.get(2)))
                    ));
                } else {
                    List<MajorInfo> allInfos = allMajors.getType(entranceType);
                    allInfos.sort(new Comparator<MajorInfo>() {
                        @Override
                        public int compare(MajorInfo o1, MajorInfo o2) {
                            return Long.compare(o1.getMin(), o2.getMin());
                        }
                    });
                    Collections.reverse(allInfos);
                    int j = 0;
                    for (int i = 0; i < allInfos.size(); i++) {
                        if (allInfos.get(i).getSub() <= 7) {
                            collegeVOS.add(Transform.CollegeVO(college,
                                    RecommendType.RISK,
                                    allInfos.get(i).getSpName(),
                                    allInfos.get(i).getSpecialId(),
                                    userService.getAverageRate(allInfos.get(i).getSpecialId()),
                                    userService.getUserNum(allInfos.get(i).getSpecialId())
                                    ));
                            j++;
                        }
                        if (j >= 4) {
                            break; //我不到哇
                        }
                    }
                }
            }
        for (Long o : normal) {
                College college = collegeRepository.findBySchoolId(o).orElse(null);
                MajorProvince allMajors = collegeMajorScoreRepository.findBySchoolId(o).orElse(null);
                if (allMajors == null) {
                    List<String> firstMajor = getFirstMajor(college.getSchoolId(), entranceType);
                    collegeVOS.add(Transform.CollegeVO(college, RecommendType.NORMAL, firstMajor,2L,
                            userService.getAverageRate(Long.parseLong(firstMajor.get(2))),
                            userService.getUserNum(Long.parseLong(firstMajor.get(2)))
                    ));
                } else {
                    List<MajorInfo> allInfos = allMajors.getType(entranceType);
                    allInfos.sort(new Comparator<MajorInfo>() {
                        @Override
                        public int compare(MajorInfo o1, MajorInfo o2) {
                            return Long.compare(o1.getMin(), o2.getMin());
                        }
                    });
                    Collections.reverse(allInfos);
                    int j = 0;
                    for (int i = 0; i < allInfos.size(); i++) {
                        if (allInfos.get(i).getSub() <= 20) {
                            collegeVOS.add(Transform.CollegeVO(
                                    college,
                                    RecommendType.NORMAL,
                                    allInfos.get(i).getSpName(),
                                    allInfos.get(i).getSpecialId(),
                                    userService.getAverageRate(allInfos.get(i).getSpecialId()),
                                    userService.getUserNum(allInfos.get(i).getSpecialId())
                            ));
                            j++;
                        }
                        if (j >= 4) {
                            break;
                        }
                    }
                }
            }
        for (Long o : safe) {
                College college = collegeRepository.findBySchoolId(o).orElse(null);
                MajorProvince allMajors = collegeMajorScoreRepository.findBySchoolId(o).orElse(null);
                if (allMajors == null) {
                    List<String> firstMajor = getFirstMajor(college.getSchoolId(), entranceType);
                    collegeVOS.add(Transform.CollegeVO(college, RecommendType.SAFE, firstMajor,2L,
                            userService.getAverageRate(Long.parseLong(firstMajor.get(2))),
                            userService.getUserNum(Long.parseLong(firstMajor.get(2)))
                    ));
                } else {
                    List<MajorInfo> allInfos = allMajors.getType(entranceType);
                    allInfos.sort(new Comparator<MajorInfo>() {
                        @Override
                        public int compare(MajorInfo o1, MajorInfo o2) {
                            return Long.compare(o1.getMin(), o2.getMin());
                        }
                    });
                    Collections.reverse(allInfos);
                    int n = Math.min(allInfos.size(), 3);
                    for (int i = 0; i < n; i++) {
                        collegeVOS.add(Transform.CollegeVO(
                                college,
                                RecommendType.SAFE,
                                allInfos.get(i).getSpName(),
                                allInfos.get(i).getSpecialId(),
                                userService.getAverageRate(allInfos.get(i).getSpecialId()),
                                userService.getUserNum(allInfos.get(i).getSpecialId()))
                        );
                    }
                }
            }
        return collegeVOS;
    }


    /**
     *
     * 这边以下方法为上面private方法的调用
     */

    private EntranceType getEntranceTypeByRecommendVO(String input,Long provinceId){
        Long[] newPolics= {13L,21L,32L,35L,42L,43L,44L,46L,50L};
        int index = Arrays.binarySearch(newPolics,provinceId);
        if(index<0) {
            return EntranceType.valueOf(input);
        }
        else {
            String result = input.equals("LI")?"PHYSICS":"HISTORY";
            return EntranceType.valueOf(result);
        }
    }

    private Boolean admissionCheck(EntranceType type,String code){
        if(type.compareTo(EntranceType.PHYSICS)==0 || type.compareTo(EntranceType.LI)==0){
            return code.startsWith("02") || code.startsWith("07") || code.startsWith("08") || code.startsWith("10") || code.startsWith("12") || code.startsWith("09")|| code.startsWith("0301");
        }
        else if(type.compareTo(EntranceType.HISTORY)==0 || type.compareTo(EntranceType.WEN)==0){
            return code.startsWith("01") || code.startsWith("02") || code.startsWith("03") || code.startsWith("04") || code.startsWith("05") || code.startsWith("06")|| code.startsWith("12")|| code.startsWith("13")|| code.startsWith("14");
        }
        else return type.compareTo(EntranceType.ZONGHE) == 0;
    }

    private List<String> defaultMajor(List<CollegeMajorInner> inners){
        List<String> res = new ArrayList<>();
        res.add(inners.get(0).getMajorName());
        res.add(inners.get(0).getMajorSpecial().get(0).getSpecialName());
        res.add(inners.get(0).getMajorCode().substring(0,2));
        return res;
    }

}
