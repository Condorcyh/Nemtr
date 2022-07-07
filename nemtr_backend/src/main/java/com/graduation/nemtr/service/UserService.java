package com.graduation.nemtr.service;

import com.graduation.nemtr.vo.RateDataVO;
import com.graduation.nemtr.vo.request.LoginVO;
import com.graduation.nemtr.vo.request.RateRequestVO;
import com.graduation.nemtr.vo.request.RegisterVO;
import com.graduation.nemtr.vo.UserVO;

import java.util.List;

public interface UserService {
    String[] login(LoginVO loginVO);
    void register(RegisterVO registerVO);
    UserVO getUserInfo(String userName);
    UserVO update(UserVO userVO);
    void rateMajor(RateRequestVO rateRequestVO);
    List<RateDataVO> getUserRates(String userId);
    Double getAverageRate(Long majorId);
    Long getUserNum(Long majorId);
}
