package com.graduation.nemtr.controller;

import com.graduation.nemtr.service.UserService;
import com.graduation.nemtr.utils.JwtUtils;
import com.graduation.nemtr.utils.Result;
import com.graduation.nemtr.vo.request.LoginVO;
import com.graduation.nemtr.vo.request.RateRequestVO;
import com.graduation.nemtr.vo.request.RegisterVO;
import com.graduation.nemtr.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginVO loginVO) {
        String[] res = userService.login(loginVO);
        if(res == null){
            return Result.error().message("登录失败");
        }
        else {
            return Result.ok().data("token", res[0]).data("name", res[1]);
        }
    }

    @PostMapping("/register")
    public Result register(@RequestBody RegisterVO registerVO) {
        userService.register(registerVO);
        return Result.ok();
    }

    @GetMapping("/getUserInfo")
    public Result getUserInfo(HttpServletRequest request){
        String userName = JwtUtils.getNameByJwtToken(request);
        UserVO userVO = userService.getUserInfo(userName);
        if(userVO == null){
            return Result.error().message("未找到用户信息");
        }
        else {
            userVO.setUserRateData(userService.getUserRates(userVO.getId()));
            return Result.ok().data("userInfo", userVO);
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody UserVO userVO) {
        UserVO newUser = userService.update(userVO);
        return Result.ok().data("userInfo", newUser);
    }

    @PostMapping("/rateMajor")
    public Result rateMajor(@RequestBody RateRequestVO rateRequestVO) {
        userService.rateMajor(rateRequestVO);
        return Result.ok().message("评分成功");
    }

    @GetMapping("/getAllRate/{userId}")
    public Result getAllRate(@PathVariable String userId){
        return Result.ok().data("rates",userService.getUserRates(userId));
    }

}
