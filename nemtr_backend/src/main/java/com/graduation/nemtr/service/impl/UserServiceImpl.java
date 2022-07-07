package com.graduation.nemtr.service.impl;

import com.graduation.nemtr.enums.Provinces;
import com.graduation.nemtr.exceptionhandler.NemtrException;
import com.graduation.nemtr.po.Rate;
import com.graduation.nemtr.po.User;
import com.graduation.nemtr.repository.RateRepository;
import com.graduation.nemtr.repository.UserRepository;
import com.graduation.nemtr.service.UserService;
import com.graduation.nemtr.transform.UserSession;
import com.graduation.nemtr.utils.JwtUtils;
import com.graduation.nemtr.utils.MD5;
import com.graduation.nemtr.vo.RateDataVO;
import com.graduation.nemtr.vo.request.LoginVO;
import com.graduation.nemtr.vo.request.RateRequestVO;
import com.graduation.nemtr.vo.request.RegisterVO;
import com.graduation.nemtr.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String[] login(LoginVO loginVO){
        String checkName = loginVO.getCheckName();
        String pwd = MD5.encrypt(loginVO.getPwd());
        Optional<User> user = userRepository.findByNameAndPassword(checkName, pwd);
        if(user.isPresent()){
            UserVO userVO = UserSession.UserVO(user.get());
            return getRes(userVO);
        }
        else {
            Optional<User> userCheck = userRepository.findByPhoneAndPassword(checkName, pwd);
            if(userCheck.isPresent()){
                UserVO userVO = UserSession.UserVO(userCheck.get());
                return getRes(userVO);
            }
        }
        return null;
    }

    private static String[] getRes(UserVO userVO) {
        String name = userVO.getName();
        String pwd = userVO.getPwd();
        String token = JwtUtils.getJwtToken(name, pwd);
        String[] res = new String[2];
        res[0] = token;
        res[1] = name;
        return res;
    }

    @Override
    public void register(RegisterVO registerVO){
        Optional<User> user = userRepository.findByNameAndPassword(registerVO.getName(), MD5.encrypt(registerVO.getPwd()));
        if (!user.isPresent()) {
            userRepository.insert(UserSession.UserPO(registerVO));
        } else {
            throw new NemtrException(201, "该用户已存在");
        }
    }

    @Override
    public UserVO getUserInfo(String userName){
        Optional<User> user = userRepository.findByName(userName);
        return user.map(UserSession::UserVO).orElse(null);
    }

    @Override
    public UserVO update(UserVO userVO){
        User user = UserSession.UserPO(userVO);
        Query query = Query.query(Criteria.where("_id").is(user.getId()));
        Update update = new Update();
        update.set("phone", user.getPhone());
        update.set("name", user.getName());
        update.set("highSchool", user.getHighSchool());
        update.set("provinceName", user.getProvinceName());
        update.set("provinceId", user.getProvinceId());
        update.set("entranceType", user.getEntranceType());
        mongoTemplate.updateFirst(query,update,"user");
        return UserSession.UserVO(user);
    }

    @Override
    public void rateMajor(RateRequestVO rateRequestVO){
        if(!rateRepository.existsByUserIdAndMajorId(rateRequestVO.getUserId(),rateRequestVO.getMajorId())){
            rateRepository.save(UserSession.Rate(rateRequestVO));
        }
        else {
            Query query = new Query();
            query.addCriteria(Criteria.where("userId").is(rateRequestVO.getUserId()));
            query.addCriteria(Criteria.where("majorId").is(rateRequestVO.getMajorId()));
            Update update = new Update();
            update.set("rate",rateRequestVO.getRate());
            mongoTemplate.updateFirst(query,update,"rate");
        }
    }

    @Override
    public List<RateDataVO> getUserRates(String userId){
        List<Rate> rates =  rateRepository.findAllByUserId(userId);
        List<RateDataVO> result = new ArrayList<>();
        for(Rate r:rates){
            result.add(UserSession.RateData(r,getAverageRate(r.getMajorId())));
        }
        return result;
    }

    @Override
    public Double getAverageRate(Long majorId){
        Long n = getUserNum(majorId);
        if(n==0){
            return null;
        }
        double total = 0.0;
        for(Rate r:rateRepository.findAllByMajorId(majorId)){
            total = total + r.getRate().intValue();
        }
        BigDecimal b = new BigDecimal(total/n);
        return b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    @Override
    public Long getUserNum(Long majorId){
        return rateRepository.countRatesByMajorId(majorId);
    }

}
