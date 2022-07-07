package com.graduation.nemtr.transform;

import com.graduation.nemtr.enums.EntranceType;
import com.graduation.nemtr.enums.Provinces;
import com.graduation.nemtr.po.Rate;
import com.graduation.nemtr.po.User;
import com.graduation.nemtr.utils.MD5;
import com.graduation.nemtr.vo.RateDataVO;
import com.graduation.nemtr.vo.request.RateRequestVO;
import com.graduation.nemtr.vo.request.RegisterVO;
import com.graduation.nemtr.vo.UserVO;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;

public class UserSession {

    public static User UserPO(RegisterVO registerVO){
        User user = new User();
        user.setName(registerVO.getName());
        user.setPassword(MD5.encrypt(registerVO.getPwd()));
        user.setPhone(registerVO.getPhone());
        return user;
    }

    public static UserVO UserVO(User user){
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        userVO.setPwd(user.getPassword());
        if(user.getEntranceType() != null) {
            userVO.setEntranceType(user.getEntranceType().getName());
        }
        return userVO;
    }

    public static User UserPO(UserVO userVO){
        User user = new User();
        BeanUtils.copyProperties(userVO,user);
        user.setEntranceType(EntranceType.valueOf(userVO.getEntranceType()));
        user.setProvinceId(userVO.getProvinceId());
        user.setProvinceName(Provinces.toString(userVO.getProvinceId().intValue()));
        user.setPassword(userVO.getPwd());
        return user;
    }

    public static Rate Rate(RateRequestVO rateRequestVO){
        Rate rate = new Rate();
        BeanUtils.copyProperties(rateRequestVO,rate);
        return rate;
    }

    public static RateDataVO RateData(Rate rate,Double average){
        RateDataVO rateDataVO = new RateDataVO();
        rateDataVO.setMajor(rate.getMajorName());
        rateDataVO.setProvince(Provinces.toString(rate.getProvinceId().intValue()));
        rateDataVO.setUserRate(rate.getRate());
        rateDataVO.setAverageRate(average);
        rateDataVO.setSchool(rate.getSchoolName());
        return rateDataVO;
    }

}
